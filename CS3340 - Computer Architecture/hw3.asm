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
	
# First Print : Full string
	la $a0, message
	li $v0, 4
	syscall
	
	
# Start len(message, counter)		
	la $t0, message
	li $t1, -1	
	jal len
		
# Start wordcount(message, counter)
	la $t0, message
	la $t2, 1
	jal wordcount
	
#Outprinting Information
	move $a0,, $t2
	li $v0, 1
	syscall
	
	li $v0, 4
	la $a0, m1
	syscall
	
	move $a0, $t1
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
	
	
len: # Returns length of string
	len_loop:
		lb $a0, 0($t0)
		beqz $a0, len_done
		addi $t0, $t0, 1
		addi $t1, $t1, 1		
		j len_loop	
		
	len_done:					
		jr	$ra
		
wordcount: # Returns how many words are in the string
	wordcount_loop:		
		lb $a0, 0($t0)
		beqz $a0, wordcount_done
		beq $a0, 32, L1
		addi $t0, $t0, 1
		j wordcount_loop
	L1:
		addi $t2, $t2, 1
		addi $t0, $t0, 1
		j wordcount_loop
		
	wordcount_done:		
		jr 	$ra
		
