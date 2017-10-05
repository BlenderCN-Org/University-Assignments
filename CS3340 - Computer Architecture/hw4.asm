	.data
	
height:	.word 	0	
weight:	.word	0
bmi:	.float	0.0
name:	.asciiz	""

buffer:	.space	1024

p1: 	.asciiz	"What is your name?\n"
p2:	.asciiz	"Please enter yourr height in INCHES: "
p3:	.asciiz	"Now enter your eight in pounds (round to a whole number): "
p4:	.asciiz	"Your BMI is: "

unde:	.asciiz ""
norm:	.asciiz ""
over:	.asciiz ""
obes:	.asciiz ""

	.text
main:
	# p1 output
	li $v0, 4
	la $a0, p1	
	syscall	
	# name input
	li $v0, 8	
	li $a1, 100
	move $t0, $a0
	syscall
	# save to name
	sb $t0, name
	
	# p2 output
	li $v0, 4
	la $a0, p2	
	syscall	
	# height input
	li $v0, 5		
	syscall
	# save to height
	sb $v0, height
	
	# p3 output
	li $v0, 4
	la $a0, p3	
	syscall	
	# weight input
	li $v0, 5		
	syscall
	# save to height
	sb $v0, weight
	
	# Calculates and saves weight
	lw $t0, weight
	li $t1, 703
	mult $t0, $t1
	mfhi $t0
	sb $t0, weight
	
	# Calculates and saves height
	lw $t0, height
	mult $t0, $t0
	mfhi $t0
	sb $t0, height
	
	lwc1 $f2, weight
	lwc1 $f4, height
	div.d $f0, $f2, $f4
	sdc1 $f0, bmi
	
	li $v0, 2
	lwc1 $f12, bmi
	syscall
	
	
