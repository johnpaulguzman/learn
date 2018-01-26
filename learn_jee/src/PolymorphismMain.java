class ClassA { // acts like a default class
	protected String messageA = "general message A";
	
	public void talkA() {
		System.out.println(messageA);
	}
	
	public ClassA() {
	}
}

class ClassB extends ClassA {
	private String messageB = "specific to B message";
	
	public ClassB() {
		super();
		messageA = "overwritten message A";
	}
	
	public void talkB() {
		System.out.println("messageA + messageB = " + messageA + " (AND) " + messageB);
	}
	
	@Override 
	public void talkA() { 
		/** Method overriding is also known as runtime polymorphism
		 * An overriden method must be accessible in the subclass, mutable, and also have the same return type, arguments, and static modifier
		 * Use the @Override annotation for readability and compile-time checking
		 */
		System.out.println("overriden A");
	}
	
	public void talkA(boolean doSpeak) {
		/** Method overloading is also known as compile-time polymorphism
		 * An overloaded method must have a different argument list
		 */
		if (doSpeak || !doSpeak){
			System.out.println("overloaded A");
		} else {
			System.out.println("I broke logic");
		}
	}
}


public class PolymorphismMain {

	public static void main(String[] args) {
		ClassA A = new ClassA();
		ClassB B = new ClassB();
		
		A.talkA();
		B.talkA();
		System.out.println();
		
		B.talkB();
		B.talkA(false);
		System.out.println();
		
		ClassA AfromB = new ClassB(); // upcasting: a specific class can be typed as its general class
		AfromB.talkA();
		((ClassB)AfromB).talkB(); // downcasting: a general class can be typed as a specific class but only if it implements the needed specific behavior
	
	}
}