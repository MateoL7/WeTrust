package myExceptions;

@SuppressWarnings("serial")
public class EmployeeAlreadyCreatedException extends Exception {
	/** 
	 *@author: Mateo Loaiza
	 *@version: 26/05/2019
	 *ExceptionClass EmployeeAlreadyCreatedException
	 */
	public EmployeeAlreadyCreatedException() {
		super("Please provide the information correclty.");
	}

}
