package packMaster;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/*
The IoC (dependency injection) container is responsible to instantiate, configure and assemble the objects from the XML file. The main tasks performed by IoC container are:
- to instantiate the application class
- to configure the object
- to assemble the dependencies between the objects

There are two types of IoC containers:
1. BeanFactory
2. ApplicationContext - The ApplicationContext interface is built on top of the BeanFactory interface. It adds some extra functionality than BeanFactory such as simple integration with Spring's AOP, message resource handling (for I18N), event propagation, application layer specific context (e.g. WebApplicationContext) for web application. So it is better to use ApplicationContext than BeanFactory.
*/

public class Test {
	public static void main(String[] args) {
		Resource resource = new ClassPathResource("applicationContext.xml");
		// The Resource is the interface and the ClassPathResource is the implementation class of the Resource interface
		BeanFactory factory = new XmlBeanFactory(resource);
		// The BeanFactory is responsible to return the bean. The XmlBeanFactory is the implementation class of the BeanFactory for Xml.
		Student student = (Student) factory.getBean("studentbean");
		student.displayInfo();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); 
		Student studenter = (Student) context.getBean("studentbeaner");
		studenter.displayInfo();
		
		Student constructed = (Student) context.getBean("constructorMaster");
		constructed.displayInfo();
		
		Student constructedjr = (Student) context.getBean("constructorMasterJr");
		constructedjr.displayInfo();
		
		Student constructedjrjr = (Student) context.getBean("constructorMasterJrJr");
		constructedjrjr.displayInfo();
		
		//We can inject collection values by constructor through the (list, set, map) elements inside the constructor-arg element.
		beantup strl = (beantup) context.getBean("strlister");
		strl.printMe();
	}
}