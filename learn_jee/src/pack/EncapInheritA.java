package pack;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class EncapInheritA {
	public int public_int; // accessible by all classes
	protected int protected_int; // accessible by classes within the package or outside the package by subclass
	int default_int; // accessible by classes within the package
	private int private_int; // accessible only within the same class
	
	public static int static_int = 1; // shared among all instances
	public final int final_int = 2; // immutable / unchangeable / can't be overridden

	public EncapInheritA() {
		this.default_int = 0;
		this.public_int = 1;
		this.protected_int = 2;
		this.private_int = 3;
	}
	
	// -- IGNORE UTIL CODE
	public List<Field> getAllFields(List<Field> fields, Class<?> type) {
		fields.addAll(Arrays.asList(type.getDeclaredFields()));
		if (type.getSuperclass() != null)
			getAllFields(fields, type.getSuperclass());
		return fields;
	}
	public void printall(Object obj) {
		System.out.println("\n==Accessing: ((" + obj.getClass().getName() + ")) from: ((" + this.getClass().getName() + "))");
		List<Field> fields = getAllFields(new LinkedList<Field>(), obj.getClass());
		for (Field field : fields) {
			try {
				System.out.println(">> " + field.getName() + " = " + field.get(obj));
			} catch (IllegalAccessException ex) {
				System.out.println("!>> " + field.getName() + " <== " + ex.getMessage());
			}
		}
	}
}
