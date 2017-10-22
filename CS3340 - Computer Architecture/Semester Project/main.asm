	.data

color_red:		.word	0x00FF0000 # Red's HexCode
bitmap_origin_addr:	.word	0x10010000 # Starting Position of the Bitmap Display

Player_Spawn:		.word	0x1004fc04 # Center of Screen

KeyCode.w:		.asciiz	"w"
KeyCode.a:		.asciiz	"a"
KeyCode.s:		.asciiz	"s"
KeyCode.d:		.asciiz	"d"

	.text
	
	lw $a0, Player_Spawn
	
	# Loads the keycodes into $t0 - $t4
	lw $t0, KeyCode.w
	lw $t1, KeyCode.a
	lw $t2, KeyCode.s
	lw $t3, KeyCode.d

loop:
	beq $a3, $t0, Traverse_Up
	beq $a3, $t1, Traverse_Left
	beq $a3, $t2, Traverse_Down
	beq $a3, $t3, Traverse_Right
	

Traverse_Up:

Traverse_Left:
	

Traverse_Down:

Traverse_Right: