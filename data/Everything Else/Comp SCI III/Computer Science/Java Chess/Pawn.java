


public class Pawn
{
private int t = 0;
private int l = 0;
private int i = 0;
private int x;
private int PawnRule = 0;
private int r = 0;

 public Pawn()
 {
 	x = 0;
 }

 public Pawn(int StartingSpace)
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

 public void setPosition(int Position, int Row)
 {
 	l = Position;
	r= Row;
 }

 public int getPosition(int Check)
 {
	if(Check == 0)
	{
		return 	l;
	}
 	if(Check == 1)
	{
		return 	r;
	}
	if(Check == 2)
	{
		return 	l;
		return 	r;
	}
 }

 public int moveDouble()
 {
 	i = getTeam();


 	if(Chessboard[i].getValid == 666)
 	{

	 	if(i == 0)
	 	{
	 		Chessboard[getPosition(0)-2][getPosition(1)] = Chessboard[getPosition(0)][getPosition(1)];
	 		i++;
	 	}
	 	else
	 	{
	 		Chessboard[getPosition(0)+2][getPosition(1)] = Chessboard[getPosition(0)][getPosition(1)];
	 		i++;
	 	}
 	}
 	else
 	{
 		System.out.println("Invalid Action");
 	}

 }

  public void move()
 {
 	i = getPosition;

	if(ChessBoard[getPosition(0)][getPosition(1)].getValid = 666)
	{
	 	if(i = 0 || i<=1)
	 	{
	 		Chessboard[getPosition(0)+1][getPosition(1)] = Chessboard[getPosition(0)][getPosition(1)];
	 		i++;
	 	}
	 	else
	 	{
	 		Chessboard[getPosition(0)-1][getPosition(1)] = Chessboard[getPosition(0)][getPosition(1)];
	 		i++;
	 	}
	}
	else
	{
		System.out.println("Invalid Action");
	}

 }

 public void Take(int takePosition)
 {
 	if(getTeam = 0)
 	{
	 	if(ChessBoard[getPosition(0)+1][getPosition(1)-1].getTeam != 666 && ChessBoard[getPosition(0)+1][getPosition(0)-1].getTeam != getTeam)
	 	{
	 		ChessBoard[getPosition(0)][getPosition(1)] = ChessBoard[getPosition(0)+1][getPosition(1)-1];
	 	}
	 	else
	 	{
	 		System.out.println("Invalid Action");
	 	}
 	}
 	else
 	{
 		if(ChessBoard[getPosition(0)-1][getPosition(1)+1].getTeam != 666 && ChessBoard[getPosition(0)-1][getPosition(1)+1].getTeam != getTeam)
	 	{
	 		ChessBoard[getPosition(0)][getPosition(1)] = ChessBoard[getPosition(0)-1][getPosition(1)+1];
	 	}
	 	else
	 	{
	 		System.out.println("Invalid Action");
	 	}
 	}
 }

 /* public void EnPassant(Object o) //0 = object being taken WORK IN PROGRESS
 {
 	if(o.getTeam = 1)
 	{
	 	if(o.getPosition() = o.getStarting()+2 && getPosition() = o.getPosition() + 8 + 2 || getPosition() = o.getPosition() + 8 - 2)
	 	{
	 		Chessboard[o.getPosition] = Chessboard[getPosition];

	 	}
 	}

 	if(o.getTeam = 2)
 	{
	 	if(o.getPosition() = o.getStarting()-2 && getPosition() = o.getPosition() - 8 + 2 || getPosition() = o.getPosition() - 8 - 2)
	 	{
	 		Chessboard[o.getPosition] = Chessboard[getPosition];

	 	}
 	}

 }
*/




}