package pack;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class EncapInheritB extends EncapInheritA {

	private String private_string;

	public EncapInheritB() {
		super();
		this.private_string = "(\")3";
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
