.data

color_red:		.word	0x00FF0000 # Red's HexCode
# 0x10010000 = startpos
.text

	li $a2, 0x00FF0000 #Color
	li $t1, 0x10000100 #Starting Pos
		# Move Left -> (CurrentPos) - ($ * Number of moves)
		# Move Right -> (CurrentPos) + ($ * Number of moves)
		# Move Up -> (CurrentPos)  ($ * 128)
		# Move Down -> (CurrentPos) * ($ * 128)
loop:
	sw $a2, ($t1)
	addi $t1, $t1, 
	j loop
	