.data

color_red:		.word	0x00FF0000 # Red's HexCode
GameSpeed:		.word	200
# 0x10010000 = startpos
.text
	
	li $t2, 0x00FF0000 #Color
	li $t1, 0x10000100 #Starting Pos
		# Move Left -> (CurrentPos) - ($ * Number of moves)
		# Move Right -> (CurrentPos) + ($ * Number of moves)
		# Move Up -> (CurrentPos)  ($ * 128)
		# Move Down -> (CurrentPos) * ($ * 128)
InputCheck:
	# Pauses Slightly to apply keyboard input and so it doesnt go too fast :/
	
	# Keyboard Input
	li $t0, 0xFFFF0000 		# Reciever Controller for 'Keyboard and Display', check if input has changed
	beqz $t0, InputCheck 		# Checks if the ready bit has been toggled to 1	

Input: # Draws Line in path
	lb $t0, 0xFFFF0004 # Gets Key From Input		
	sw $t2, ($t1)
	addi $t1, $t1, 4
	j InputCheck
	
Pause:
	li $v0, 32 #syscall value for sleep
	syscall
	jr $ra
