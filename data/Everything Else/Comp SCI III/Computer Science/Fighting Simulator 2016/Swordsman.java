public class Swordsman extends MainGameEngine
{
	private int unitValue = 1;
	private int maxMove;
	private int HP;

    public Swordsman(int Health) // Starting HP: 80
   	{
		HP = Health;
    }

    public int getHP()
    {
    	return HP;
    }

    public int maxMove()
    {
    	maxMove = 1;
    }

    public void Swing() // HP - 20 (0-4)
    {

    }

    public void Lunge() // HP - 40 (5-8)
    {

    }

    public void Execute() // HP - 100 (9)
    {
    	
    }


}