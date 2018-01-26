abstract class AbstractClassD{ // an abstract class is a class that contains a method that is abstracted from the class definition
	int int_d1;
	
	public AbstractClassD() { // an abstract class's constructor can only be used by a subclass that implements all the abstracted methods 
		int_d1 = 1;
	}
	
	public void runD() {
		System.out.println("AbstractClassD's implementation of runD");
	}
	
	abstract public void runAbsD(); // an abstract method can be concretely defined within a concrete subclass
}

interface InterfaceC{ // an interface is a construct that cannot contain concrete methods
	int int_c1 = 2;

	public void runProtC(); // by definition, the abstract keyword is not necessary
}

class ClassE implements InterfaceC{

	@Override
	public void runProtC() {
		System.out.println("ClassE implementing runProtC");
	}

}

class ClassF extends AbstractClassD implements InterfaceC{
	
	public ClassF() {
		super();
		int_d1 = 10;
	}
	
	@Override
	public void runProtC() {
		System.out.println("ClassF implementing runProtC");
	}

	@Override
	public void runAbsD() {
		System.out.println("ClassF implementing runAbsD");
	}}

public class AbstractionMain {

	public static void main(String[] args) {
		ClassE E = new ClassE();
		System.out.println("E.int_c1 = " + E.int_c1);
		E.runProtC();
		
		ClassF F = new ClassF();
		System.out.println("F.int_c1, F.int_d1 = " + F.int_c1 + ", " + F.int_d1);
		F.runProtC();
		F.runD();
		F.runAbsD();
	}
}