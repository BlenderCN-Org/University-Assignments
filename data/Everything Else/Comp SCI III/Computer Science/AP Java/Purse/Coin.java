
public class Coin implements Comparable
{
	private int value;

    public Coin()
    {
    	value = 5;

    }
    public Coin( int v )
    {
    	value = v;
    }
    public int getValue()
    {
    	return value;
    }

    public int compareTo( Object c )
    {


    return value = ((Coin)c).getValue();

    }


}