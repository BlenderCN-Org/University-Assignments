	.data
filename:	.asciiz	"data.txt"
newline:	.asciiz "0xD"
one:		.asciiz "1"
zero:		.asciiz "0"
buffer:		.space 2

		.align	2
temp:		.word 	0xFFDC143C
red:		.word	0xFFDC143C
yellow:		.word	0xFFFFFF66
purple:		.word	0xFF800080
green:		.word	0xFF006400
white:		.word	0xFFF0FFFF

startAddress:   .word 	0x10010000

	.text
# 'open_file':
#	- Opens a new file, and saves the file pointer	
open_file:
	# Syscall to open file
	li $v0, 13
	la $a0, filename
	li $a1, 1
	li $a2, 0
	syscall
	
	# Saves File Pointer to $s0
	move $s0, $v0 
	li $t8, 0

# 'rand':
#	- Generates 4 random ints 700 times
#	- After 4 numbers, prints a newline
rand:
	bgt $t8, 2800, pre_load

	# Random Number Gen 1
	li	$a0, 1	
	li	$a1, 2	
	li	$v0, 42	
	syscall	

	subi $a0, $a0, 1
	bgezal $a0, addone
	bltzal $a0, addzero

	# Random Number Gen 2
	li	$a0, 1	
	li	$a1, 2	
	li	$v0, 42	
	syscall	

	subi $a0, $a0, 1
	bgezal $a0, addone
	bltzal $a0, addzero

	# Random Number Gen 3
	li	$a0, 1	
	li	$a1, 2	
	li	$v0, 42	
	syscall	

	subi $a0, $a0, 1
	bgezal $a0, addone
	bltzal $a0, addzero

	# Random Number Gen 4
	li	$a0, 1		
	li	$a1, 2	
	li	$v0, 42	
	syscall	

	subi $a0, $a0, 1
	bgezal $a0, addone
	bltzal $a0, addzero

	# Writes new line
	move $a0, $s0
	li $v0, 15
	la $a1, newline
	li $a2, 1
	syscall

	j rand
	# 'addzero':
#	- Writes zero to the file
addzero:
	## Writes to file
	move $a0, $s0
	li $v0, 15
	la $a1, zero
	li $a2, 1
	syscall
	addi $t8, $t8, 1
	jr $ra
# 'addone':
#	- Writes one to the file
addone:
	## Writes to file
	move $a0, $s0
	li $v0, 15
	la $a1, one
	li $a2, 1
	syscall 
	addi $t8, $t8, 1	
	jr $ra
	
pre_load:	
	# Closes Written File
	li $v0, 16
	move $a0, $s0
	syscall

	# Sets the Accumulators to 0 :: 
	# Gryffindor House Accumulator -> $s1
	# Hufflepuff House Accumulator -> $s2
	# Ravenclaw House Accumulator -> $s3
	# Slytherin House Accumulator -> $s4
	li $s1, 0 
	li $s2, 0
	li $s3, 0
	li $s4, 0

	# $t8 is the file iterator, used to parse through the file
	# $t9 is the max number of lines possible in the file 
	li $t8, 0
	li $t9, 700

	# Syscall to open the file, then move the filepointer from $v0, to $s0
	li $v0, 13
	la $a0, filename
	li $a1, 0
	li $a2, 0
	syscall
	
	move $s0, $v0

# 'read'::
#	- Opens the file 'data.txt'
#	- Parses throught the file with 'buffer' and the iterators previously defined
#	- Checs for issues / looping position, and moves on the graph editing if there exists a problem OR if done with file reading
#	- Calls PROCESS to process data after each line read
#	- Continues to call read (in a loop) unitl end requirements are met
read:

	# Resets Registers
	add $a0, $zero, $zero
	add $a1, $zero, $zero

	# Read File Syscall
	li $v0, 14
	move $a0, $s0
	la $a1, buffer
	li $a2, 4
	syscall	
	
	# Checks for issues / loops position
	bltz $v0, create_vert_graph
	
	jal proc	
	bge $t8, $t9, create_vert_graph
	
	# Adds to the accumulators
	addi $t8, $t8, 1
	j read

# 'proc':
#	- Loads the line into $a1
#	- Calculates and normalizes score for:
#		- Gryffindor House
#		- Hufflepuff House
#		- Ravenclaw House
#		- Slytherin House
#	- Returns updated values to previous function	
proc:
	# Loads the line into $a1
	la $a1, buffer
	
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
	
	# Returns to spot in function	
	jr $ra
	
# 'create_vert_graph':
#	- Creates a vertical graph representing the averages of the houses
#	  as well as the bounds each indicating 32 pixels of length
#	- Clears all values before funtion use
#	- Normalizes each house's average to fit graph specifications
#	- Loads correct colors into $t5-$t9 for graph
#	- Initializes framework variables
#	- Loads the Board into $a0
#	- Contains a loop, 'create_vert_graph_loop', that creates the vertical graph
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
	
	# Variable Initialization
	# $t0 -> XPOS
	# $t1 -> TOTAL ITERATOR
	# $a1 -> XPOS MODULUO VAL
	# $a2 -> YPOS MODULUO VAL
	# $t3 -> YPOS
	li $t0, 0
	li $t1, 0
	li $a1, 1024
	li $a2, 32
	li $t3, -1
	
	# Loading the Starting Address of the Board
	la $a0, startAddress		
	
	# 'create_vert_graph_loop':
	#	- Loops through and creates a graph until otherwise specified
	create_vert_graph_loop:	
		
		# 'create_vert_graph_preloop':
		#	- Checks if the current height is over the specified amount '512' 
		#	- Gets the Current Row Position -> $t0
		#	- Gets the Boundary Position -> $t2
		create_vert_graph_preloop:
			bgt $t3, 512, create_inter_bound
		
			div $t1, $a1
			mfhi $t0
		
			div $t3, $a2
			mfhi $t2
			
		# 'create_vert_graph_bounds':
		#	- Checks if the parameters $t2 and $t0 are in range
		#	- If so, creates a boundary line at the point (fill color in)
		create_vert_graph_bounds:
			bne $t2, 0, create_vert_graph_slyth
			bgt $t0, 15, create_vert_graph_slyth
		
			sw  $t9, ($a0)
	
		# 'create_vert_graph_slyth':
		#	- Checks if the current position is in the range [800, 896]
		#	- If in range, fills in the position on the graph
		#	- If not, either continues to postloop or next check
		create_vert_graph_slyth: 
			bgt $t0, 896, create_vert_graph_postloop
			blt $t0, 800, create_vert_graph_raven
			blt $t3, $s4, create_vert_graph_postloop
		
			sw $t8, ($a0)
			j create_vert_graph_postloop
		
		# 'create_vert_graph_raven':
		#	- Checks if the current position is in the range [576, 672]
		#	- If in range, fills in the position on the graph
		#	- If not, either continues to postloop or next check
		create_vert_graph_raven:
			bgt $t0, 672, create_vert_graph_postloop
			blt $t0, 576, create_vert_graph_huffl
			blt $t3, $s3, create_vert_graph_postloop
		
			sw $t7, ($a0)
			j create_vert_graph_postloop
		
		# 'create_vert_graph_huffl':
		#	- Checks if the current position is in the range [352, 448]
		#	- If in range, fills in the position on the graph
		#	- If not, either continues to postloop or next check
		create_vert_graph_huffl:
			bgt $t0, 448, create_vert_graph_postloop
			blt $t0, 352, create_vert_graph_gryph
			blt $t3, $s2, create_vert_graph_postloop
			
			sw $t6, ($a0)
			j create_vert_graph_postloop	
		
		# 'create_vert_graph_gryph':
		#	- Checks if the current position is in the range [128, 224]
		#	- If in range, fills in the position on the graph
		#	- If not, either continues to postloop
		create_vert_graph_gryph:
			bgt $t0, 224, create_vert_graph_postloop
			blt $t0, 128, create_vert_graph_postloop
			blt $t3, $s1, create_vert_graph_postloop
		
			sw $t5, ($a0)
			j create_vert_graph_postloop
		
		# 'create_vert_graph_postloop':
		#	- Updates values of...
		#		$t1 -> TOTAL ITERATOR
		# 		$a0 -> BOARD POSITION POINTER
		# 		$t3 -> YPOS
		create_vert_graph_postloop:
			addi $t1, $t1, 1
			addi $a0, $a0, 4
			bne $t0, 0, create_vert_graph_postjump
			addi $t3, $t3, 1		
		
		# 'create_vert_graph_postjump':
		#	- Returns to the preloop
		create_vert_graph_postjump:
			j create_vert_graph_preloop


# 'create_inter_bound':
#	- Creates a boundary between the 2 graphs
#	- Calls `create_inter_bound_loop`
create_inter_bound:
	
	# `create_inter_bound_loop`:
	#	- Loops throught the process
	create_inter_bound_loop:
		
		# 'create_inter_bound_preloop':
		#	- Checks if the current height is in the bounds of the function
		#	- Gets the current row position
		create_inter_bound_preloop:
			bgt $t3, 530, create_hori_graph
		
			div $t1, $a1
			mfhi $t0
		
		# 'create_inter_bound_check':
		#	- Checks if it needs to apply a color (white) to the specified board spot
		create_inter_bound_check:
			bne $t3, 530, create_inter_bound_postloop
			sw $t9, ($a0)
	
		# 'create_inter_bound_postloop':
		#	- Updates values of...
		#		$t1 -> TOTAL ITERATOR
		# 		$a0 -> BOARD POSITION POINTER
		# 		$t3 -> YPOS
		create_inter_bound_postloop:
			addi $t1, $t1, 1
			addi $a0, $a0, 4
			bne $t0, 0, create_inter_bound_postjump
			addi $t3, $t3, 1
		
		# 'create_inter_bound_postjump':
		#	- Returns to the preloop
		create_inter_bound_postjump:
			j create_inter_bound_preloop


# 'create_hori_graph':
#	- Creates a horizontal graph representing the averages of the houses
#	  as well as the bounds each indicating 55 pixels of length
#	- Normalizes each house's average to fit graph specifications
#	- Loads correct colors into $t9 for graph
#	- Contains a loop, 'create_hori_graph_loop', that creates the vertical graph
create_hori_graph:

	# Normalizing the Averages
	li $t9, 512
	sub $s1, $t9, $s1
	sub $s2, $t9, $s2
	sub $s3, $t9, $s3
	sub $s4, $t9, $s4
	
	# Re-Loading $t9 to be white
	lw $t9, white
		
	# 'create_hori_graph_loop':
	#	- Loops through and creates a graph until otherwise specified
	create_hori_graph_loop:	
		
		# 'create_hori_graph_preloop':
		#	- Checks if the current height is over the specified amount '1024' 
		#	- Gets the Current Row Position -> $t0
		#	- Gets the Boundary Position -> $t2
		create_hori_graph_preloop:
			bgt $t3, 1024, exit
			
			div $t1, $a1
			mfhi $t0
			
			div $t0, $a2
			mfhi $t2
		
		# 'create_hori_graph_bounds':
		#	- Checks if the parameters $t2 and $t0 are in range
		#	- If so, creates a boundary line at the point (fill color in)
		create_hori_graph_bounds:
			blt $t3, 1000, create_hori_graph_slyth
			bne $t2, 0, create_hori_graph_slyth
			
			sw $t9, ($a0)			
		
		# 'create_hori_graph_slyth':
		#	- Checks if the current position is in the range [915, 970]
		#	- If in range, fills in the position on the graph
		#	- If not, either continues to postloop or next check	
		create_hori_graph_slyth:
			bgt $t3, 970, create_hori_graph_postloop
			blt $t3, 915, create_hori_graph_raven
			bgt $t0, $s4, create_hori_graph_postloop
			
			sw $t8 ($a0)
			j create_hori_graph_postloop
		
		# 'create_hori_graph_raven':
		#	- Checks if the current position is in the range [805, 860]
		#	- If in range, fills in the position on the graph
		#	- If not, either continues to postloop or next check	
		create_hori_graph_raven:
			bgt $t3, 860, create_hori_graph_postloop
			blt $t3, 805, create_hori_graph_huffl
			bgt $t0, $s3, create_hori_graph_postloop
			
			sw $t7 ($a0)
			j create_hori_graph_postloop
		
		# 'create_hori_graph_huffl':
		#	- Checks if the current position is in the range [695, 750]
		#	- If in range, fills in the position on the graph
		#	- If not, either continues to postloop or next check	
		create_hori_graph_huffl:
			bgt $t3, 750, create_hori_graph_postloop
			blt $t3, 695, create_hori_graph_gryph
			bgt $t0, $s2, create_hori_graph_postloop
			
			sw $t6 ($a0)
			j create_hori_graph_postloop
		
		# 'create_hori_graph_gryph':
		#	- Checks if the current position is in the range [585, 640]
		#	- If in range, fills in the position on the graph
		#	- If not, either continues to postloop	
		create_hori_graph_gryph:
			bgt $t3, 640, create_hori_graph_postloop
			blt $t3, 585, create_hori_graph_postloop	
			bgt $t0, $s1, create_hori_graph_postloop
			
			sw $t5 ($a0)
			j create_hori_graph_postloop	
		
		# 'create_hori_graph_postloop':
		#	- Updates values of...
		#		$t1 -> TOTAL ITERATOR
		# 		$a0 -> BOARD POSITION POINTER
		# 		$t3 -> YPOS
		create_hori_graph_postloop:
			addi $t1, $t1, 1
			addi $a0, $a0, 4
			bne $t0, 0, create_hori_graph_postjump
			addi $t3, $t3, 1					
		
		# 'create_hori_graph_postjump':
		#	- Returns to the preloop
		create_hori_graph_postjump:
			j create_hori_graph_preloop	

						
# 'exit':
#	- Safely closes the file
#	- Safely exits the program
exit:
	#Syscall to close the file
	li   $v0, 16	
	move $a0, $s0     
	syscall            
	
	#Syscall to exit the program
	li $v0, 10
	syscall
