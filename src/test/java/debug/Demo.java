package debug;

public class Demo {
	private String str1;
	private String str2;
	private int num1;
	private int num2;
	public static final String STATIC_DATA = "hello world";

	private void sayHello1() {
		System.out.println("this is method1...");
	}

	private void sayHello2() {
		System.out.println("this is method2...");
	}

	public void sayHello3() {
		System.out.println("this is method3...");
	}

	public int getValue(int arg1, String arg2, double arg3) {
		return arg1;
	}

	public Demo getSelf() {
		return this;
	}

	public void testStackSize() {
		testStackSize1();
	}

	public void testStackSize1() {
		testStackSize2();
	}

	public void testStackSize2() {

	}

	public void testRecursive() {
		testRecursive();
	}
	
	public String test(){
		return str1;
	}
}
