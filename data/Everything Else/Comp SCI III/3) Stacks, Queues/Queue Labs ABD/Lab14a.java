//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

public class Lab14a
{
	public static void main ( String[] args )
	{
		String listJuan = "one two three two one";
		String listTwo = "1 2 3 4 5 one two three four five";

		PalinList p = new PalinList(listJuan);
		p.setList(listJuan);

		p.toString();

		System.out.println(p.isPalin());

		p.setList(listTwo);

		p.toString();

		System.out.println(p.isPalin());


	}

}