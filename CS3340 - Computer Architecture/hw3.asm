.data
# Input Data
message:	.space		128
messagePrompt:	.asciiz		"Please enter a string::"
messageBuffer:	.word		128

message_temp:	.space		128

# Output Data
m1:		.asciiz 	" words "
m2:		.asciiz 	" characters\n"

# Exit Data
exitPrompt:	.asciiz		"Leaving? :(\n"
exitMessage:	.asciiz		"Goodbye!"
.text

main:
# Prompting Message
	la $a0, messagePrompt
	la $a1, message
	la $a2, messageBuffer
	li $v0, 54
	syscall
	
	beq $a1, -2, exitprogram
	beq $a1, -3, exitprogram
	beq $a1, -4, exitprogram	
	
# Start len(message, counter)		
	la $t0, message
	li $t1, -1	
	li $t2, 1
	jal lenWC
	
#Outprinting Information
	# Prints String
	move $a0, $s0
	li $v0, 4
	syscall
	
	#Prints WC
	move $a0, $s2
	li $v0, 1
	syscall
	
	li $v0, 4
	la $a0, m1
	syscall
	
	#Prints Length
	move $a0, $s1
	li $v0, 1
	syscall 
	
	la $a0, m2
	li $v0, 4
	syscall

	j main

exitprogram:
	li $v0, 59
	la $a0, exitPrompt
	la $a1, exitMessage
	syscall
	
	
	li $v0, 10
	syscall
	
	
lenWC: # Returns length of string to $s1 and wordcount to $s2
	addi $sp, $sp, -4
	sw $t0, ($sp)
	# Main Loops
	loop:
		lb $s0, ($t0)		
		beqz $s0, done
		addi $t0, $t0, 1
		addi $t1, $t1, 1
		beq $s0, 32, L1		
		j loop
	
	# if (string[x] = " "), signifying more than 1 word
	L1:
		addi $t2, $t2, 1
		j loop	
	
	# Exits the loop	
	done:
		lw $s0, ($sp)
		addi $sp, $sp, 4	
		
		la $s1, ($t1)
		la $s2, ($t2)					
		jr	$ra

		
