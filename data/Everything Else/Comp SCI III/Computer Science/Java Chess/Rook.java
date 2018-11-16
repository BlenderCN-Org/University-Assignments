


public class Rook extends Piece
{
private int t = 0;
private int l = 0;
private int i = 0;
private int x;
private int Rule = 0;

 public Rook()
 {
 	x = 0;
 }

 public Rook(int StartingSpace)
 {
 	x = StartingSpace;
 }

 public String toString()
 {
  	return "";
 }

 public int getStarting()
 {
 	return x;
 }

 public int getValid()
 {
  	int x = 1;

  	return x;
 }

 public void setTeam(int set)
 {
 	t = set;
 }

 public int getTeam()
 {
 	return t;
 }

 public void setPosition(int Position)
 {
 	l = Position;
 }

 public void getPosition()
 {
 	return 	l;
 }

  public void moveFoward(int M) //M = number of spaces
 {
 	i = getTeam;

 	if(i=1)
 	{
	 	if(Chessboard[getPosition + (8*M)].getValid != 666) // Foward
	 	{
	 		Chessboard[8*M] = Chessboard[getPosition];
	 		Chessboard[getPosition] = //Nullspace
	 	}
	 	else
	 	{
	 		System.out.println("Invalid Action");
	 	}
 	}
 	else
 	{
 		if(Chessboard[getPosition - (8*M).getValid != 666) // Foward
	 	{
	 		Chessboard[getPosition - 8*M] = Chessboard[getPosition];
	 		Chessboard[getPosition] = //Nullspace
	 	}
	 	else
	 	{
	 		System.out.println("Invalid Action");
	 	}
 	}

 }

  public void moveLeft(int M) //M = number of spaces
 {
 	i = getTeam;

 	if(i=1)
 	{
	 	if(Chessboard[getPosition + (0-1)*M.getValid != 666) // Left
	 	{
	 		Chessboard[getPosition + (0-1)*M] = Chessboard[getPosition];
	 		Chessboard[getPosition] = //Nullspace
	 	}
	 	else
	 	{
	 		System.out.println("Invalid Action");
	 	}
 	}
 	else
 	{
 		if(Chessboard[getPosition + (0-1)*M].getValid != 666) // Left
	 	{
	 		Chessboard[getPosition + (0-1)*M] = Chessboard[getPosition];
	 		Chessboard[getPosition] = //Nullspace
	 	}
	 	else
	 	{
	 		System.out.println("Invalid Action");
	 	}
 	}

 }

 public void moveRight(int M) //M = number of spaces
 {
 	i = getTeam;

 	if(i=1)
 	{
	 	if(Chessboard[getPosition + 1*M].getValid != 666) // Right
	 	{
	 		Chessboard[getPosition + 1*M] = Chessboard[getPosition];
	 		Chessboard[getPosition] = //Nullspace
	 	}
	 	else
	 	{
	 		System.out.println("Invalid Action");
	 	}
 	}
 	else
 	{
 		if(Chessboard[getPosition + 1*M].getValid != 666) // Right
	 	{
	 		Chessboard[getPosition + 1*M] = Chessboard[getPosition];
	 		Chessboard[getPosition] = //Nullspace
	 	}
	 	else
	 	{
	 		System.out.println("Invalid Action");
	 	}
 	}

 }

  public void moveBack(int M) //M = number of spaces
 {
 	i = getTeam;

 	if(i=1)
 	{
	 	if(Chessboard[getPosition - (8*M)].getValid != 666 ) // Back
	 	{
	 		Chessboard[8*M] = Chessboard[getPosition];
	 		Chessboard[getPosition] = //Nullspace
	 	}
	 	else
	 	{
	 		System.out.println("Invalid Action");
	 	}
 	}
 	else
 	{
 		if(Chessboard[getPosition + (8*M)].getValid != 666) // Back
	 	{
	 		Chessboard[getPosition - 8*M] = Chessboard[getPosition];
	 		Chessboard[getPosition] = //Nullspace
	 	}
	 	else
	 	{
	 		System.out.println("Invalid Action");
	 	}
 	}

 }

 public void Take(int takePosition)
 {
 	for(int z = 0; z < 64;)

 }

 public void Castling()
 {



 }




}