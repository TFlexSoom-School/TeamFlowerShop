import java.util.Stack;

public class GTest {
	public static void main (String[] args){
		String View = "bla";
		Boolean blank = true;
	
		Stack<String> S = new Stack<String>();
		S.push("Derp");
		S.push("Hello");
		S.push("I am Tomm");
		S.push("I dont give candy");
		S.push("To strangers");
		while (blank == true)
		{
			View = S.peek();
		if (View .equals("Derp"))
		{
			blank = false;
		}
		else
		{
			System.out.println(S.pop());
		}
		}

		
	}
}
