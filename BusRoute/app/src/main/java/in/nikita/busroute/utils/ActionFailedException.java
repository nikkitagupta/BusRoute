package in.nikita.busroute.utils;

@SuppressWarnings("serial")
public class ActionFailedException extends Exception {
	public ActionFailedException() {

	}

	public ActionFailedException(String ex) {
		super(ex);
	}

	public ActionFailedException(String className, Exception e) {

		super(className, e);
	}

	public ActionFailedException(Throwable cause) {

		super(cause);
	}

	public ActionFailedException(String message, Throwable cause) {

		super(message, cause);
	}
}
