	.data
filename:	.asciiz	"data.txt"
newline:	.asciiz "\n"
buffer:		.space	2

red:		.word	0x00FF0000
yellow:		.word	0x00FFFF00
purple:		.word	0x00006400
green:		.word	0x0000FFFF

startAddress:   .word 	0x10040000

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
	
	#Syscall to close the file
	li   $v0, 16	
	move $a0, $s0     
	syscall 
	
	# Resettting all the registers I uses in this next part
	add $a0, $zero, $zero
	add $a1, $zero, $zero
	add $a2, $zero, $zero
	add $t0, $zero, $zero	
	add $t1, $zero, $zero
	add $t3, $zero, $zero	
	add $t4, $zero, $zero
	add $t5, $zero, $zero	
	add $t6, $zero, $zero
	add $t7, $zero, $zero	
	add $t8, $zero, $zero
	
	# Loading the Colors
	lw $t4, red
	lw $t5, yellow
	lw $t6, purple
	lw $t7, green
	
	# Load the board
	la $a0, startAddress
			
	li $t1, 0 # Iterator
	li $t2, 1024 # MAX ROW LEN
	li $t3, 0 # Current Height

create_graph:

	bgt $t3, 511, exit
	preloop:
		div $t1, $t2
		mfhi $t0
	slyth:
		bgt $t0, 896, postloop
		blt $t0, 800, raven
		blt $t3, $s4, postloop
		sw $t7, ($a0)
		j postloop
	raven:
		bgt $t0, 672, postloop
		blt $t0, 576, huffl
		blt $t3, $s3, postloop
		sw $t6, ($a0)
		j postloop
	huffl:
		bgt $t0, 448, postloop
		blt $t0, 352, gryph
		blt $t3, $s2, postloop
		sw $t5, ($a0)
		j postloop
	gryph:
		bgt $t0, 224, postloop
		blt $t0, 128, postloop
		blt $t3, $s1, postloop
		sw $t4, ($a0)
		j postloop
	
	postloop:
		addi $a0, $a0, 4
		addi $t1, $t1, 1
		bne $t0, 0, postjump
		addi $t3, $t3, 1
	postjump:
		j create_graph


exit:
	#Syscall to exit the program
	li $v0, 10
	syscall
