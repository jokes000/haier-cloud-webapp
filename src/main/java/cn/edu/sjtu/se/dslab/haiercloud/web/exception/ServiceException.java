package cn.edu.sjtu.se.dslab.haiercloud.web.exception;

/**
 * service layer exception
 * 
 * @author smile
 *
 */
@SuppressWarnings("serial")
public class ServiceException extends RuntimeException {
	
	public ServiceException() {
		super();
	}
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}