public class Palidan extends MainGameEngine
{
	private int unitValue = 5;
	private int maxMove;
	private int HP;

    public Palidan(int Health) // Starting HP: 130
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

    public void Slash() // HP - 30 (0-5)
    {

    }

    public void Heal() //If uHP > 60, HP + 15 (6-8)
    {

    }

    public void Holy Light() // If uHP < 40 && HP < 40, uHP + 20 && HP - 40 (9)
    {

    }


}