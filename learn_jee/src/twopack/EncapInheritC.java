package twopack;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class EncapInheritC {
	private int private_nonneg_int;
	
	public EncapInheritC() {
		this.setPrivate_int(-1);
	}

	public int getPrivate_int() {
		return private_nonneg_int;
	}

	public void setPrivate_int(int private_int) {
		if (private_int >= 0) {
			this.private_nonneg_int = private_int;
		} else {
			System.out.println("Input ignored: must not be negative");
		}
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
