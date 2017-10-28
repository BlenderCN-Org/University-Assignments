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

KeyCode.w:		.byte	'w'
KeyCode.a:		.byte	'a'
KeyCode.s:		.byte	's'
KeyCode.d:		.byte	'd'

zero:			.word 	0
one:			.word 	1

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
	
InputCheck:
	# Pauses Slightly to apply keyboard input and so it doesnt go too fast :/
	lw $a0, GameSpeed
	jal Pause
	
	# Keyboard Input
	li $t0, 0xFFFF0000 		# Reciever Controller for 'Keyboard and Display', check if input has channged
	beqz $t0, InputCheck 		# Checks if the ready bit has been toggled to 1	

Input: # Draws Line in path
	lb $t0, 0xFFFF0004 # Gets Key From Input	
	
MoveUp: # If Movement is UP:
	bne $t0, $t6, MoveLeft	# IF NOT UP, CHECK LEFT
	jal ValidUp
MoveLeft: # If Movement is LEFT:
	bne $t0, $t7, MoveDown # IF FNOT LEFT, CHECK DOWN
	jal ValidLeft
MoveDown: # If Movement is DOWN:
	bne $t0, $t8, MoveRight #IF NOT DOWN, CHECK RIGHT
	jal ValidDown
MoveRight: # If Movement is RIGHT: 
	jal ValidRight
		
	# Debugging Print
	li $v0, 11 			
	lb $a0, 0xFFFF0004
	syscall
	
	# Looper
	j InputCheck			# Repeats
		
Pause:
	li $v0, 32 #syscall value for sleep
	syscall
	jr $ra
	
ValidUp: # Checks if you can move up, if not it ends

ValidLeft:  # Checks if you can move left, if not it ends

ValidDown:  # Checks if you can move down, if not it ends

ValidRight:  # Checks if you can move right, if not it ends
	
exit:
	li $v0, 10
	syscall
	
	
	
