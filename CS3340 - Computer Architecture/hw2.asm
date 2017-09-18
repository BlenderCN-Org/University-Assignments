#	Matthew McMillian
#	mgm160130@utdallas.edu
#	CS 3340.003 - Computer Architecture

	.data	
# Var input Storage
ain:	.word	0
bin:	.word	0
cin:	.word	0

# Var output Storage
ans1:	.word	0
ans2:	.word	0
ans3:	.word	0

# User's Name
username:	.space		64
# Name Prompt
messagePrompt1:	.asciiz		"Please Enter Your Name:: "
# Integer(s) Prompt
messagePrompt2:	.asciiz		"Please Enter a Integer:: "
# Message for Results
messagePrompt3:	.ascii		"Your Results Are: "

# Space Buffer
spaceBuffer:	.ascii		" "

	.text
NamePrompt:
	# Prompts User to enter 'Name'
	la $a0, messagePrompt1
	li $v0, 4
	syscall
	
	# Saves and stores user input to 'username'
	la $a0, username
	la $a1, 64
	li $v0, 8
	syscall
	
IntPrompts:
	# Integer Prompt 1
	la $a0, messagePrompt2
	li $v0, 4
	syscall 	
	# Saves Value		
	li $v0, 5
	syscall
	sw $v0, ain
	
	# Integer Prompt 2
	la $a0, messagePrompt2
	li $v0, 4
	syscall	
	# Saves Value	
	li $v0, 5
	syscall
	sw $v0, bin
	
	# Integer Prompt 3
	la $a0, messagePrompt2
	li $v0, 4
	syscall	
	# Saves Value	
	li $v0, 5
	syscall
	sw $v0, cin
	
Calculation:
	# Variable Setup
	lw $t1, ain
	lw $t2, bin
	lw $t3, cin

	# First Calculation	
	add $t0, $zero, $zero
	add $t0, $t1, $t2	
	addi $t0, $t0, 2
	subi $t0, $t0, 5
	sw $t0, ans1	
	
	# Second Calculation
	add $t0, $zero, $zero
	li $t4, 5
	mult $t1, $t4
	mflo $t0
	addi $t0, $t0, 10
	sub $t0, $t0, $t2
	sw $t0, ans2
	
	# Third Calculation
	add $t0, $zero, $zero
	li $t4, 2
	div $t2, $t4
	mflo $t0
	add $t0, $t0, $t1
	sw $t0, ans3	

Output:
	# Outprints 'username'
	la $a0, username
	li $v0, 4
	syscall
	
	# Outprints 'messagePrompt3'
	la $a0, messagePrompt3
	li $v0, 4
	syscall	
	
	# Outprint 'ans1'
	lw $a0, ans1
	li $v0, 1
	syscall
	
	# Spaces
	la $a0, spaceBuffer
	li $v0, 4
	syscall
	
	# Outprint 'ans2'
	lw $a0, ans2
	li $v0, 1
	syscall
	
	# Spaces
	la $a0, spaceBuffer
	li $v0, 4
	syscall
	
	# Outprint 'ans3'
	lw $a0, ans3
	li $v0, 1
	syscall
Exit:
	 li $v0, 10
	 syscall
	
	
COMMENTS:
# 	Given Input: 
#	       Matthew McMillian
#	       1
#	       2
#	       3

#	Expected Output:
#	       Matthew McMillian
#	       Your Results Are: 0 13 2
	
	
	
