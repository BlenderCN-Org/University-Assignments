public class Cat implements Comparable
{

	private int Weight;
	private int Height;
	private int Age;

    public Cat(int w, int h, int a)
    {
    	Weight = w;
    	Height = h;
    	Age = a;
    }

    public int getWeight()
    {
    	return Weight;
    }

    public int getHeight()
    {
    	return Height;
    }

    public int getAge()
    {
    	return Age;
    }

    public String setWeight(int w1)
    {
    	Weight = w1;
    	return "The new weight is " + Weight;
    }

    public String setHeight(int h1)
    {
    	Height = h1;
    	return "The new weight is " + Height;
    }

    public String setAge(int a1)
    {
    	Age = a1;
    	return "The new weight is " + Age;
    }

     public String toString()
    {
    	return "whishgnji";
    }






}

 class Cheetah extends Cat
{
	private int Speed;

	public Cheetah(int s)
	{
		Speed = s;
	}

	public int getSpeed()
    {
    	return Speed;
    }

    public String setWeight(int s1)
    {
    	Speed = s1;
    	return "The new weight is " + Speed;
    }

    public String toString()
    {
    	return "whishgnji";
    }


}