
public class TT {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("String".replace('g', 'G') == "String".replace('g', 'G'));
		System.out.println("String".replace('g', 'g') == new String("String").replace('g', 'g'));
		System.out.println("String".replace('g', 'G') == "StrinG");
		System.out.println("String".replace('g', 'g') == "String");
	}

}
