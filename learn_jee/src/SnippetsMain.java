/* 4 Core Concept of OOP:
 * - 1. ) Encapsulation
 * - 2. ) Inheritance
 * - 3. ) Polymorphism
 * - 4. ) Abstraction
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Objects;


public class SnippetsMain {
	// Instructions and control flow

	private void demo_Operations() {
		System.out.println("\n===== SECTION Operations =====");
		int test = 0;
		System.out.println(String.format("%d++ | reads as %d | ends up as %d", test, test++, test));
		System.out.println(String.format("++%d | reads as %d | ends up as %d", test, ++test, test));
		System.out.println("Math.sqrt(Math.pow(4,6)) = " + Math.sqrt(Math.pow(4,6)));
	}

	private void demo_InOut() {
		System.out.println("\n===== SECTION Input/Output =====");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter input: ");
		System.out.println("Entered input: " + "dummy input"); // scanner.nextLine());
	}

	private void demo_Comparison() {
		System.out.println("\n===== SECTION Comparisons =====");
		int int_a=1;
		int int_b=2-1;
		
		String string_a="lolm2k";
		String string_b="lolm2kDELETTHIS".replace("DELETTHIS", "");
		
		class Equaler{
			int identifier1;
			int identifier2;
			
			public Equaler(int identifier1, int identifier2) {
				this.identifier1 = identifier1;
				this.identifier2 = identifier2;
			}
			
			@Override
			public boolean equals(Object obj) { // used when comparing variables that are references
				try {
					return (this.identifier1 == ((Equaler)obj).identifier1 &&
							this.identifier2 == ((Equaler)obj).identifier2);
				} catch(Exception e) {
					return false;
				}
			}
			
			@Override
			public int hashCode() { // must return the same value for equivalent objects
				return Objects.hashCode(this.identifier1, this.identifier2);
			}
		}
		
		Equaler equaler_a = new Equaler(42, 29);
		Equaler equaler_b = new Equaler(42, 29);
		
		System.out.println("a == b: " + (int_a == int_b));
		System.out.println("c == d: " + (string_a == string_b));
		System.out.println("string_a.equals(string_b) : " + (string_a.equals(string_b)));
		System.out.println("equaler_a == equaler_b : " + (equaler_a == equaler_b));
		System.out.println("equaler_a.equals(equaler_b) : " + (equaler_a.equals(equaler_b)));

	}
	
	private void demo_Conditional() {
		System.out.println("\n===== SECTION Conditional Statements =====");
		System.out.println(true ? "my ternary statement trued" : "i falsed");
		String input = "N";
		if (input == null) {
			System.out.println("With if: input is null");
		} else if (input.equals("Y")) {
			System.out.println("With if: that's a yes");
		} else {
			System.out.println("With if: wut");
		}
		int swicherer = 30;
		switch (swicherer) {
		case 30:
			System.out.println("my case 30");
			break;
		default:
			System.out.println("my case is not 30 :c");
			break;
		}
	}

	private void demo_Loops() {
		System.out.println("\n===== SECTION Loops =====");
		int ubound = 3;
		int[] indices = new int[ubound];
		for (int i = 0; i < ubound; i++) {
			indices[i] = i;
			System.out.println("With normal for loop: " + i);
		}
		for (int i : indices) {
			System.out.println("With enhanced for loop: " + i);
		}

		int i = 0;
		while (i < 0) {
			System.out.println("With while loop: " + i);
			i++;
		}
		do {
			System.out.println("With do while loop: " + i);
			i++;
		} while (i < 0);
	}

	private void demo_Arrays() {
		System.out.println("\n===== SECTION Arrays =====");
		int[][] myArr = { { 1, 2, 3 }, { 4 }, { 5, 6, 7 } };
		System.out.println("myArr[1][0] is: " + myArr[1][0]);
		System.out.println("myArr.length = " + myArr.length);
		System.out.println("myArr[0].length = " + myArr[0].length);
	}

	private class InnerClass {
		public void innerRun() {
			System.out.println("Running inner method");
		}
	}
	
	public void demo_InnerClass() {
		System.out.println("\n===== SECTION Nested Inner Class =====");
		System.out.println("An outer method accessing a nested inner class method");
		InnerClass IC = new InnerClass();
		IC.innerRun();
	}
	
	public void anonymousRun() {
		System.out.println("Running original anonymous run");
	}
	
	public void demo_AnonymousClass() {
		System.out.println("\n===== SECTION Anonymous Class =====");
		this.anonymousRun();
		SnippetsMain AnonedSM = new SnippetsMain() {
			@Override
			public void anonymousRun() {
				System.out.println("Running anonymized run");
			}
		};
		AnonedSM.anonymousRun();
	}
	
	enum Choices { // an Enum is a definite collection of options and it is used when a variable can only take one out of a small set of possible values
		CHOICE_A, CHOICE_B, CHOICE_C
	}
	
	private void demo_Enum() {
		System.out.println("\n===== SECTION Enum =====");
		Choices choice = Choices.CHOICE_C;
		switch (choice) {
		case CHOICE_A:
			System.out.println("I chose A");
			break;
		case CHOICE_B:
			System.out.println("I chose B");
			break;
		default:
			System.out.println("I didn't chose A or B");
			break;
		}
	}

	private void demo_Exceptions() {
		System.out.println("\n===== SECTION Exceptions =====");
		
		class Diver {
			int div(int a, int b) throws ArithmeticException {
				System.out.println("Attempting to divide " + a + " by " + b);
				if (b == 0) {
					throw new ArithmeticException("Division by Zero");
				} else {
					return a / b;
				}
			}
		}
		
		Diver D = new Diver();
		try {
			System.out.println(D.div(10, 2));
			System.out.println(D.div(1, 0));
		} catch (ArithmeticException ae){
			System.out.println(ae);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	volatile int threadRunCount = 0;  // volatile allows threads to safely modify the variable concurrently by caching multiple local copies
	private void demo_Threads() {
		System.out.println("\n===== SECTION Threads =====");
		final int sleepTimeMS = 100;
		
		class ThreadedClass_A implements Runnable {
			synchronized public void run() { // synchronized locks the class method to only 1 thread at a time
				for(int i = 0; i<3; i++) {
					threadRunCount++;
					System.out.println("Hello from ThreadedClass_A");
					try { Thread.sleep(sleepTimeMS); } 
					catch (InterruptedException e) {e.printStackTrace(); }
				}
			}
		}
		
		class ThreadedClass_B implements Runnable {
			synchronized public void run() {
				for(int i = 0; i<3; i++) {
					threadRunCount++;
					System.out.println("Hello from ThreadedClass_B");
					try { Thread.sleep(sleepTimeMS); } 
					catch (InterruptedException e) {e.printStackTrace(); }
				}
			}
		}
		
		ThreadedClass_A A = new ThreadedClass_A();
		ThreadedClass_B B = new ThreadedClass_B();
		Thread threadA = new Thread(A);
		Thread threadB = new Thread(B);
		threadA.start();
		threadB.start();
		
		Thread[] threads = new Thread[Thread.activeCount()];
		Thread.enumerate(threads);
		for (Thread t: threads) {
			System.out.println("Active thread_priority : " + t.getName() + "_" +t.getPriority());
		}
		
		try { // wait until threadA and threadB finish run()
			threadA.join();
			threadB.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("threadRunCount = " + threadRunCount);
	}
	
	private void demo_Regex() {
		System.out.println("\n===== SECTION Regex =====");
		
		System.out.println("** TODO **");
		String regex = "match";
		Pattern p = Pattern.compile(regex); // precompile regexp to avoid recompiling regex for every check
		Matcher m = p.matcher("match");
		System.out.println(m.matches());
		while (m.find()) {
			System.out.println(m.group() + " at " + m.start());
		}
	}
	
	private void demo_DataStructures() {
		System.out.println("\n===== SECTION Data Structure =====");
		
		ArrayList<String> AL_data = new ArrayList<String>(); // a resizeable array; used for storing and accessing data
		AL_data.add("1"); // adds an element at the tail end of the list
		AL_data.addAll(Arrays.asList(new String[] {"2", "2", "3"}));
		AL_data.remove("2"); // removes an element
		System.out.println("ArrayList contents:");
		for (String s: AL_data) {
			System.out.println("> " + s);
		}
		System.out.println("data.contains(\"4\") = " + AL_data.contains("4")); // returns true if the list contains the element 
		System.out.println("data.get(2) = " + AL_data.get(2)); // returns the element at the specified position in the list
		System.out.println("data.size() = " + AL_data.size()); // returns the number of elements in the list
		AL_data.clear(); // removes all of the elements from the list
		
		LinkedList<String> LL_data = new LinkedList<String>(); // a linked list; used for manipulating data
		LL_data.add("1"); // adds an element at the tail end of the list
		LL_data.addAll(Arrays.asList(new String[] {"2", "2", "3"}));
		LL_data.remove("2"); // removes an element
		System.out.println("LinkedList contents:");
		for (String s: LL_data) {
			System.out.println("> " + s);
		}
		System.out.println("data.contains(\"4\") = " + LL_data.contains("4")); // returns true if the list contains the element 
		System.out.println("data.get(2) = " + LL_data.get(2)); // returns the element at the specified position in the list
		System.out.println("data.size() = " + LL_data.size()); // returns the number of elements in the list
		LL_data.clear(); // removes all of the elements from the list
		
	    HashMap<String, String> dict = new HashMap<String, String>(); // can do contains or search/reverse (depending on the choice for keys) search in O(1)
	    dict.put("Key1", "Value1");
	    dict.put("Key2", "Value2");
	    dict.put("Key3", "Value3");
	    System.out.println("HashMap contents:");
		for (String s: dict.keySet()) {
			System.out.println("> " + s + " : " + dict.get(s));
		}
	    System.out.println("dict.containsValue(\"Value3\") = " + dict.containsValue("Value3"));
	    System.out.println("dict.containsKey(\"Key4\") = " + dict.containsKey("Key4"));
	    System.out.println("dict.get(\"Key4\") = " + dict.get("Key4")); 
	    
	    HashSet<String> set = new HashSet<String>();
	    set.addAll(Arrays.asList(new String[] {"A", "B", "D", "C", "A"}));
	    System.out.println("HashSet contents: " + set);
	    
	    LinkedHashSet<String> linkedSet = new LinkedHashSet<String>();
	    linkedSet.addAll(Arrays.asList(new String[] {"A", "B", "D", "C", "A"}));
	    System.out.println("LinkedHashSet contents: " + linkedSet);
	}
	
	private void demo_FileHandling() {
		System.out.println("\n===== SECTION File Handling =====");
		try {
			String path = "src//textin.txt";
			System.out.println("Reading " + path + " : ");
			Scanner sc = new Scanner(new File(path));
			while (sc.hasNext()) {
				System.out.println("> " + sc.nextLine());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		
		try {
			String path = "src//textout.txt";
			System.out.println("Writing " + path + " : ");
			Formatter f = new Formatter(path);
			System.out.println("> f.format(\"%s\\t%s\\t%s\", \"1\", \"John\", \"Smith \\n\")");
			System.out.println("> f.format(\"%d\\t%f\\t%s\", 3+3, 3.14, \"Mathmaster \\n\")");
			f.format("%s\t%s\t%s", "1", "John", "Smith \n");
			f.format("%d\t%f\t%s", 3+3, 3.14, "Mathmaster \n");
			f.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private class GenericsClass<T1 extends Number & Comparable<T1>, T2 extends Iterable> {
		private T1 t1;
		private T2 t2;
		
		public void set(T1 t1, T2 t2){
			this.t1=t1; // t1 must be some object that is a subclass of Number and implements Comparable of its type
			this.t2=t2; // t2 must be some object that implements Iterable
		}
		
		public void displayValues(){
			System.out.println("> t1 = " + t1.toString());
			System.out.println("> t2 = " + t2.toString());
		}
		
		public <A extends Number & Comparable<A>, B extends Iterable> boolean isEqualTo(GenericsClass<A, B> gc){ // generic method
			return (this.t1.equals(gc.t1) &&
					this.t2.equals(gc.t2));
		}
	}
	
	private void demo_Generics() {
		System.out.println("\n===== SECTION Generics =====");
		
		GenericsClass<Integer, List<String>> gcA = new GenericsClass<Integer, List<String>>();
		GenericsClass<Integer, List<String>> gcB = new GenericsClass<Integer, List<String>>();
		// gcA.set("1st", new List<String>()); -- Error: "1st" cannot be typed as Integer
		gcA.set(1, Arrays.asList(new String[] {"a", "b", "c"}));
		gcB.set(5-4, Arrays.asList(new String[] {"a", "b", "c"}));
		
		System.out.println("Displaying gcA: ");
		gcA.displayValues();
		System.out.println("Displaying gcB: ");
		gcB.displayValues();
		System.out.println("gcA.isEqualTo(gcB) = " + gcA.isEqualTo(gcB));
	}
	
	private void demo_Serialization() {
		System.out.println("\n===== SECTION Serialization =====");
		
		System.out.println("** TODO **");
	}
	
	public static void main(String[] args) {
		SnippetsMain SM = new SnippetsMain();		
		SM.demo_Operations();
		SM.demo_InOut();
		SM.demo_Comparison();
		SM.demo_Conditional();
		SM.demo_Loops();
		SM.demo_Arrays();
		SM.demo_InnerClass();
		SM.demo_AnonymousClass();
		SM.demo_Enum();
		SM.demo_Exceptions();
		SM.demo_Threads();
		SM.demo_Regex();
		SM.demo_DataStructures();
		SM.demo_FileHandling();
		SM.demo_Generics();
		SM.demo_Serialization();
	}
}