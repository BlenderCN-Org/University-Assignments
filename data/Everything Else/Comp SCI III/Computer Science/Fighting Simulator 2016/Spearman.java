public class Spearman extends MainGameEngine
{
	private int unitValue = 2;
	private int maxMove;
	private int HP;

    public Spearman(int Health) // Starting HP: 100
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

    public void Stab() // HP - 30 (0-4)
    {

    }

    public void Lunge() // HP - 50 (5-8)
    {

    }

    public void Impale() // If HP < 60, HP - 60 (9)
    {

    }


}