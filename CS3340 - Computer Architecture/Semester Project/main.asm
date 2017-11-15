	.data
filename:	.asciiz	"data.txt"
newline:	.asciiz "\n"
buffer:		.space 2

		.align	2
red:		.word	0xFFDC143C
yellow:		.word	0xFFFFFF66
purple:		.word	0xFF800080
green:		.word	0xFF006400
white:		.word	0xFFF0FFFF

startAddress:   .word 	0x10010000

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
	bltz $v0, create_vert_graph
	
	jal proc	
	bge $t8, $t9, create_vert_graph
	
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
	
create_vert_graph: 	
	
	# Clearing values before use
	add $a0, $zero, $zero	
	add $a1, $zero, $zero	
	add $a2, $zero, $zero
	add $t0, $zero, $zero	
	add $t1, $zero, $zero
	add $t2, $zero, $zero	
	add $t3, $zero, $zero
	add $t4, $zero, $zero
	add $t5, $zero, $zero	
	add $t6, $zero, $zero
	add $t7, $zero, $zero
	add $t8, $zero, $zero	
	add $t9, $zero, $zero
		
	# Normalizing the Averages
	li $t9, 512
	sub $s1, $t9, $s1
	sub $s2, $t9, $s2
	sub $s3, $t9, $s3
	sub $s4, $t9, $s4
		
	# Loading the Colors
	lw $t5, red
	lw $t6, yellow
	lw $t7, purple
	lw $t8, green
	lw $t9, white
	
	li $t0, 0 # X-Pos
	li $t1, 0 # Total Iterator
	li $a1, 1024 # X Moduluo Value
	li $a2, 32 # Y Moduluo Value
	li $t3,-1 # Y-Pos
	
	la $a0, startAddress # Address of the Board	
	
	
create_vert_graph_loop:	
	
	create_vert_graph_preloop:
		bgt $t3, 512, exit
		
		div $t1, $a1
		mfhi $t0
		
		div $t3, $a2
		mfhi $t2
		
	create_vert_graph_bounds:
		bne $t2, 0, create_vert_graph_slyth
		bgt $t0, 15, create_vert_graph_slyth
		
		sw  $t9, ($a0)
	
	create_vert_graph_slyth: 
		bgt $t0, 896, create_vert_graph_postloop
		blt $t0, 800, create_vert_graph_raven
		blt $t3, $s4, create_vert_graph_postloop
		
		sw $t8, ($a0)
		j create_vert_graph_postloop
			
	create_vert_graph_raven:
		bgt $t0, 672, create_vert_graph_postloop
		blt $t0, 576, create_vert_graph_huffl
		blt $t3, $s3, create_vert_graph_postloop
		
		sw $t7, ($a0)
		j create_vert_graph_postloop
	
	create_vert_graph_huffl:
		bgt $t0, 448, create_vert_graph_postloop
		blt $t0, 352, create_vert_graph_gryph
		blt $t3, $s2, create_vert_graph_postloop
		
		sw $t6, ($a0)
		j create_vert_graph_postloop	
		
	create_vert_graph_gryph:
		bgt $t0, 224, create_vert_graph_postloop
		blt $t0, 128, create_vert_graph_postloop
		blt $t3, $s1, create_vert_graph_postloop
		
		sw $t5, ($a0)
		j create_vert_graph_postloop
	
	create_vert_graph_postloop:
		addi $t1, $t1, 1
		addi $a0, $a0, 4
		bne $t0, 0, create_vert_graph_postjump
		addi $t3, $t3, 1		
	
	create_vert_graph_postjump:
		j create_vert_graph_preloop
		
exit:
#Syscall to close the file
# More lienspl commit
	li   $v0, 16	
	move $a0, $s0     
	syscall            
	
#Syscall to exit the program
	li $v0, 10
	syscall
