/* Encapsulation: uses the concept of data hiding
 * - Emphasizes modularity by hiding the specific details of each class
 * - Grants control over the flow of data
 * - RULE: attributes must only be private or protected
 */

import pack.EncapInheritA;
import pack.EncapInheritB;
import twopack.EncapInheritC;

public class EncapInheritMain {

	public static void main(String[] args) {
		EncapInheritA A = new EncapInheritA();
		EncapInheritB B = new EncapInheritB();
		EncapInheritC C = new EncapInheritC();

		A.printall(B);
		B.printall(A);
		C.printall(A);
		C.printall(B);

		System.out.println("ClassA.static_int: " + EncapInheritA.static_int++ + "++ ==> A.static_int: " + A.static_int);
		// A.final_int = 1; -- forbidden by final modifier
		System.out.println(C.getPrivate_int()); 
		C.setPrivate_int(-1);
	}
}