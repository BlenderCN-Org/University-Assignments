public class Duelist extends MainGameEngine
{
	private int unitValue = 3;
	private int maxMove;
	private int HP;

    public Duelist(int Health) // Starting HP: 60
   	{
		HP = Health;
    }

    public int getHP()
    {
    	return HP;
    }

    public int maxMove()
    {
    	maxMove = 3;
    }

    public void Slash() // HP - 40 (0-4)
    {

    }

    public void Stab() //HP - 50 (5-9)
    {

    }


}