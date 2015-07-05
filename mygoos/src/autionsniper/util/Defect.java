package autionsniper.util;

public class Defect extends RuntimeException {
	private static final long serialVersionUID = 2473731584460897293L;

	public Defect() {
		super();
	}

	public Defect(String message) {
		super(message);
	}

	public Defect(Throwable cause) {
		super(cause);
	}

	public Defect(String message, Throwable cause) {
		super(message, cause);
	}
}
