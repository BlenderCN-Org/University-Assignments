	.data
# Bitmap Specifications
# Unit Width in Pixels: 2
# Unit Height in Pixels: 2
# Disply Width in Pixels: 128
# Display Height in Pixels: 128
# Base Address for display: 0x100100000 (static data)
color_red:		.word	0x00FF0000 # Red's HexCode

Player_Pos:		.word	8064 # Starts at Center if Screen

GameSpeed:		.word	200
Direction:		.word 	1
# Up:	 0
# Right: 1 
# Down:  2 
# Left:  3

Board:			.byte	0:16384

KeyCode.w:		.byte	'w'
KeyCode.a:		.byte	'a'
KeyCode.s:		.byte	's'
KeyCode.d:		.byte	'd'

zero:			.word 	0
one:			.word 	1

HeadPointer:		.word 	0 	# Keeps track  of the snake's back
TailPointer:		.word	0	# Keeps track of the snake's front

	.text
BeginGame:
	lw $s7, color_red
	
	lw $s0, zero	# Black
	lw $s1, one	# Snake
	
	lw $s2, Player_Pos
	
	# Snake Pointers in Array
	la $t3, Board	
	lw $t4, HeadPointer
	lw $t5, TailPointer
	
	# Loads the keycodes into $t0 - $t4
	lb $t6, KeyCode.w
	lb $t7, KeyCode.a
	lb $t8, KeyCode.s
	lb $t9, KeyCode.d
	
	sb $s7, () # Sets Pos to red
	
InputCheck:
	# Pauses Slightly to apply keyboard input and so it doesnt go too fast :/
	lw $a0, GameSpeed
	jal Pause
	
	# Keyboard Input
	li $t0, 0xFFFF0000 		# Reciever Controller for 'Keyboard and Display', check if input has channged
	beqz $t0, InputCheck 		# Checks if the ready bit has been toggled to 1	

Input: # Draws Line in path
	lb $t0, 0xFFFF0004 # Gets Key From Input

Up:
	bne $t0, $t6, Right # If not Up, try next
		
	subi $t4, $t4, 128 # Moves the HeadPointer up a row
	subi $s2, $s2, 128 # Adjusts Color on Board
	jal MoveHead # Adjusts Head Node
	jal MoveTail # Adjusts Tail Node
	j InputCheck # Loops again
Right:
	bne $t0, $t7, Down # If not Right, try next
	
	addi $t4, $t4, 4 # Moves the HeadPointer up a row
	addi $s2, $s2, 4 # Adjusts Color on Board
	jal MoveHead # Adjusts Head Node
	jal MoveTail # Adjusts Tail Node
	j InputCheck # Loops again
Down:
	bne $t0, $t8, Left # If not Left, try next
	
	addi $t4, $t4, 128 # Moves the HeadPointer up a row
	addi $s2, $s2, 128 # Adjusts Color on Board
	jal MoveHead # Adjusts Head Node
	jal MoveTail # Adjusts Tail Node
	j InputCheck # Loops again
	
	
	
Left:
	subi $t4, $t4, 4 # Moves the HeadPointer up a row
	subi $s2, $s2, 4 # Adjusts Color on Board
	jal MoveHead # Adjusts Head Node
	jal MoveTail # Adjusts Tail Node
	j InputCheck # Loops again
	
	# Debugging Print
	li $v0, 11 			
	lb $a0, 0xffff0004
	syscall
	
	# Looper
	j InputCheck			# Repeats
	
	
Pause:
	li $v0, 32 #syscall value for sleep
	syscall
	jr $ra
	
MoveHead:
	add $t2, $zero, $t4 # Makes sure $t2 is clear
	
	add $t2, $t2, $t2 # 2 x boardpos
	add $t2, $t2, $t2 # 2 x boardpos	
	add $t1, $t2, $t3 # Accesses Array [ boardpos ]
	sw $s1, 0($t1) # Makes the new space white
	sw $s7, ($s2)
	jr $ra
	
MoveTail:
	add $t2, $zero, $t5 # Makes sure $t2 is clear
	
	add $t2, $t2, $t2 # 2 x boardpos
	add $t2, $t2, $t2 # 2 x boardpos
	add $t1, $t2, $t3 # Accesses Array [ boardpos ]
	sw $s0, 0($t1) # Makes the old space black
	jal AdjustTailPosition
	jr $ra
			
AdjustTailPosition:
CheckUp:
	subi $t5, $t5, 128 # Moves the HeadPointer up a row
	add $t2, $zero, $t5 # Makes sure $t2 is clear
	add $t2, $t2, $t2 # 2 x boardpos
	add $t2, $t2, $t2 # 2 x boardpos
	add $t1, $t2, $t3 # Accesses Array [ boardpos ]
	lw $t1, 0($t1)	
	bne $t1, $s1, CheckDown
	jr $ra
CheckDown:
	addi $t5, $t5, 128 # Moves the HeadPointer down a row
	addi $t5, $t5, 128 # Moves the HeadPointer down a row
	add $t2, $zero, $t5 # Makes sure $t2 is clear
	add $t2, $t2, $t2 # 2 x boardpos
	add $t2, $t2, $t2 # 2 x boardpos
	add $t1, $t2, $t3 # Accesses Array [ boardpos ]
	lw $t1, 0($t1)	
	bne $t1, $s1, CheckRight
	jr $ra
CheckRight:
	subi $t5, $t5, 128 # Moves the HeadPointer down a row
	addi $t5, $t5, 4 # Moves the HeadPointer to next element
	add $t2, $zero, $t5 # Makes sure $t2 is clear
	add $t2, $t2, $t2 # 2 x boardpos
	add $t2, $t2, $t2 # 2 x boardpos
	add $t1, $t2, $t3 # Accesses Array [ boardpos ]
	lw $t1, 0($t1)	
	bne $t1, $s1, CheckLeft
	jr $ra
CheckLeft:
	subi $t5, $t5, 4 # Moves the HeadPointer down a row
	subi $t5, $t5, 4 # Moves the HeadPointer to next element
	add $t2, $zero, $t5 # Makes sure $t2 is clear
	add $t2, $t2, $t2 # 2 x boardpos
	add $t2, $t2, $t2 # 2 x boardpos
	add $t1, $t2, $t3 # Accesses Array [ boardpos ]
	lw $t1, 0($t1)	
	jr $ra


#	sw $t5, ($t6)
#	addi $t7, $t7, 1
#	addi $t6, $t6, 4

	
exit:
	li $v0, 10
	syscall
	
	
	
