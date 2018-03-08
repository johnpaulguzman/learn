// Current page: https://www.javatpoint.com/spring-tutorial-inheriting-bean-in-spring

package packMaster;

import java.util.List;

// This is a java bean : the standard model or format in java that stores and provides data
public class Student implements java.io.Serializable {
	private String name;

	public Student() {
	}

	public Student(String name) {
		this.name = name;
		System.out.println("Constructed student: " + name);
	}

	public Student(String name, int ordName) {
		this.name = name + ", " + String.valueOf(ordName) + "th of his/her kind";
		System.out.println("Constructed student: " + name);
	}

	public Student(nameordtuple tup) {
		this.name = tup.getName() + ", " + String.valueOf(tup.getOrdName()) + "th of his/her kind";
		System.out.println("Constructed student: " + name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void displayInfo() {
		System.out.println("Hello: " + name);
	}
}

class nameordtuple implements java.io.Serializable {
	private String name;
	private int ordName;

	public nameordtuple(String name, int ordName) {
		this.name = name;
		this.ordName = ordName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrdName() {
		return ordName;
	}

	public void setOrdName(int ordName) {
		this.ordName = ordName;
	}
}

class beantup implements java.io.Serializable {
	private List<String> strs;

	public beantup(List<String> strs) {
		this.strs = strs;
	}

	public List<String> getStrs() {
		return strs;
	}

	public void setStrs(List<String> strs) {
		this.strs = strs;
	}

	public void printMe() {
		for (String str : this.strs) {
			System.out.print(str);
		}
	}
}