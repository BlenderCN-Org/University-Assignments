	.data
filename:	.asciiz	"data.txt"
newline:	.asciiz "\n"
buffer:		.space	128

red:		.word	0x00FF0000
yellow:		.word	0x00FFFF00
purple:		.word	0x00006400
green:		.word	0x0000FFFF

startAddress:   .word 	0x10010004

	.text
	
	# Sets the Accumulators to 0
	li $s1, 0 # Gryffindor House	$s1
	li $s2, 0 # Hufflepuff House?	$s2
	li $s3, 0 # Ravenclaw House?	$s3
	li $s4, 0 # Slytherin House?	$s4

	li $t8, 0	# Iterator
	li $t9, 700	# Max number of lines in the file

# Opens Data File
	li $v0, 13
	la $a0, filename
	li $a1, 0
	li $a2, 0
	syscall
	move $s0, $v0 # File Pointer
	
read:	# Read from File

	# Resets Registers
	add $a0, $zero, $zero
	add $a1, $zero, $zero

	# Read File Syscall
	li $v0, 14
	move $a0, $s0
	la $a1, buffer
	li $a2, 6
	syscall	
	
	# Checks for issues / loops position
	bltz $v0, create
	
	jal proc	
	bge $t8, $t9, create
	
	# Adds to the accumulators
	addi $t8, $t8, 1
	j read
	
proc: # Processes the string, updates the school values
	
	la $a1, buffer	# Loads the line into $a1
	
	# Gryffindor House	
	lb $a0, 0($a1)	
	subi $a0, $a0, 48
	add $s1, $s1, $a0	
	
	# Hufflepuff House?
	lb $a0, 1($a1)	
	subi $a0, $a0, 48
	add $s2, $s2, $a0
	
	# Ravenclaw House?
	lb $a0, 2($a1)
	subi $a0, $a0, 48
	add $s3, $s3, $a0	
	
	# Slytherin House?
	lb $a0, 3($a1)	
	subi $a0, $a0, 48
	add $s4, $s4, $a0
	
	jr $ra # returns to spot in function
	
create: # Displays a on a 1024x512 graph

	# 128 units,  8 per space + 8 for a single side -> 40 total white-space
	# 1024 units (8x), 64 per space + 64 for a single side -> 320 total white space
	#	- 176 pixels per bar (width)
	#	- Height is going to be 1:1
	
	add $a0, $zero, $zero	
	add $a1, $zero, $zero	
	add $t0, $zero, $zero	
	add $t1, $zero, $zero
	
	li $t9, 512 # Normalize Value for Y-Values
	
	#  Sets the $S-Values to the new actual values
	sub $s1, $t9, $s1
	sub $s2, $t9, $s2
	sub $s3, $t9, $s3
	sub $s4, $t9, $s4	
	
	# Loading the Colors
	la $t3, red
	la $t4, yellow
	la $t5, purple
	la $t6, green
	
	li $t0, 0 # Iterator
	li $t2, 0 # Y Value
	li $s4, 1024 # Moduluo Val
	la $a0, startAddress # Address of the Board	
	
create_lp:
	# if x (> 63, and < 239) and y (< 511, and > {511-number}), Draw Gryffindor House (RED)
	# if x (> 303, and < 479) and y (< 511, and > {511-number}), Draw Hufflepuff House? (YELLOW)
	# if x (> 543, and < 719) and y (< 511, and > {511-number}), Draw Ravenclaw House? (BLUE)
	# if x (> 783, and < 959) and y (< 511, and > {511-number}), Draw Slytherin  House? (GREEN)
	
	div $t0, $s4 # Moduluo to get X
	mfhi $t1 # X Value
	
	gryph:
		# if (x > 63 && x < 239)
		bgt $t1, 239, huffl
		blt $t1, 63, end
		
		# if (y < 511, and y > {511-number})
		bgt $t2, 511, end
		blt $t2, $s1, end		
		sb $t3, ($a0)
		j end
	
	huffl:
		# if (x > 303 && x < 479)
		bgt $t1, 479, raven
		blt $t1, 303, raven
		
		# if (y < 511, and y > {511-number})
		bgt $t2, 511, end
		blt $t2, $s2, end		
		sb $t4, ($a0)
		j end

	raven:
		# if (x > 543 && x < 719)
		bgt $t1, 719, slyth
		blt $t1, 543, slyth
		
		# if (y < 511, and y > {511-number})
		bgt $t2, 511, end
		blt $t2, $s3, end		
		sb $t5, ($a0)
		j end
		
	slyth:
		# if (x > 783 && x959719)
		bgt $t1, 959, end
		blt $t1, 783, end
		
		# if (y < 511, and y > {511-number})
		bgt $t2, 511, end
		blt $t2, $s4, end		
		sb $t6, ($a0)
		j end
		
	end:
		# End
		bne $t1, 1023, noad # Checks the Next Row
		addi $t1, $t1, 1 # Adds 1 to ty height
	noad:
		# Still on the same Y
		addi $t0, $t0, 1
		addi $a0, $a0, 4
		j create_lp
exit:
	# Prints out the corresponding values per team
	li $v0, 1
	move $a0, $s1	
	syscall
	
	
	# Prints out the final points
	li $v0, 4
	la $a0, newline
	syscall

	li $v0, 1
	move $a0, $s2	
	syscall
	
	li $v0, 4
	la $a0, newline
	syscall
	
	li $v0, 1
	move $a0, $s3
	syscall
	
	li $v0, 4
	la $a0, newline
	syscall
	
	li $v0, 1
	move $a0, $s4
	syscall

#Syscall to close the file
	li   $v0, 16	
	move $a0, $s0     
	syscall            
	
#Syscall to exit the program
	li $v0, 10
	syscall
