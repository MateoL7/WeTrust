package myExceptions;
/** 
 *@author: Mateo Loaiza
 *ExceptionClass EmployeeNotRegisteredException
 */
@SuppressWarnings("serial")
public class EmployeeNotRegisteredException extends Exception{
	public EmployeeNotRegisteredException(){
		super("The employee is not registered");
	}

}
