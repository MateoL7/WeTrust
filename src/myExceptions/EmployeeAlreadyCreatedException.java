package myExceptions;
/** 
 *@author: Mateo Loaiza
 *ExceptionClass EmployeeAlreadyCreatedException
 */
@SuppressWarnings("serial")
public class EmployeeAlreadyCreatedException extends Exception {
	public EmployeeAlreadyCreatedException() {
		super("Please provide the information correclty.");
	}

}
