# Matthew McMillian
# mgm160130

# Program prompts user for 
#	Name
#	Height (in inches)
#	Weight
# then calculates BMI and outprints result

	.data
	
height:	.word 	0	
weight:	.word	0
bmi:	.double	0.0
name:	.asciiz	""

buffer:	.space	1024
space:	.asciiz "\n"

p1: 	.asciiz	"What is your name?\n"
p2:	.asciiz	"Please enter your height in INCHES: "
p3:	.asciiz	"Now enter your weight in pounds (round to a whole number): "
p4:	.asciiz	"Your BMI is: "

fval:	.double 18.5
sval:	.double	25.0
tval:	.double	30.0

unde:	.asciiz "\nThis is considered underweight. \n"
norm:	.asciiz "\nThis is a normal weight. \n"
over:	.asciiz "\nThis is considered overweight. \n"
obes:	.asciiz "\nThis is considered obese. \n"

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
	move $s0, $t0
	
	# p2 output
	li $v0, 4
	la $a0, p2	
	syscall	
	# height input
	li $v0, 5		
	syscall
	# save to height
	sw $v0, height
	
	# p3 output
	li $v0, 4
	la $a0, p3	
	syscall	
	# weight input
	li $v0, 5		
	syscall
	# save to height
	sw $v0, weight
		
	# Calculates and saves weight
	lw $t0, weight
	li $t1, 703
	mult $t0, $t1
	mflo $t0
	sw $t0, weight
	
	# Calculates and saves height
	lw $t0, height
	mult $t0, $t0
	mflo $t0
	sw $t0, height
	
	# Converts weight var to a double
	lw $t0, weight
	mtc1.d $t0, $f12
	cvt.d.w $f2, $f12
	
	# Converts height var to a double
	lw $t1, height
	mtc1.d $t1, $f12
	cvt.d.w $f4, $f12
	
	# Divides and saves result to bmi
	div.d $f12, $f2, $f4
	sdc1 $f12, bmi
	
	# Spacing
	li $v0, 4
	la $a0, space
	syscall
	
	# Prints out name
	li $v0, 4
	move $a0, $s0
	syscall
	
	# Prints out 4th prompt
	li $v0, 4
	la $a0, p4
	syscall
	
	# Prints out BMI
	li $v0, 3
	ldc1 $f12, bmi
	syscall	
	
	# Loading values for IF
	ldc1 $f2, fval
	ldc1 $f4, sval
	ldc1 $f6, tval
		
	c.lt.d $f12, $f2 #If BMI < 18.5
	bc1t underweight
	
	c.lt.d $f12, $f4 #ELIF BMI < 25.0
	bc1t normalweight
	
	c.lt.d $f12, $f6 #ELIF BMI < 30.0
	bc1t overweight	
	
obeseweight: # Prints out obese message then exits
	li $v0, 4
	la $a0, obes
	syscall
	
	j exit	
	
underweight: # Prints out underweight message then exits
	li $v0, 4
	la $a0, unde
	syscall
	
	j exit

normalweight: # Prints out normalweight message then exits
	li $v0, 4
	la $a0, norm
	syscall
	
	j exit
	
overweight: # Prints out overweight message then exits
	li $v0, 4
	la $a0, over
	syscall
	
	j exit
	
exit: # Exits
	li $v0, 10
	syscall
