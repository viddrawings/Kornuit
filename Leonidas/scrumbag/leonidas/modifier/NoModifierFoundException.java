package scrumbag.leonidas.modifier;

public class NoModifierFoundException extends Exception {
	private static final long serialVersionUID = -2594315229532820159L;
	private String message;
	
	/**
	 * Custom exception when no modifier file has been found.
	 * @param message String message
	 */
	public NoModifierFoundException(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public void printStackTrace() {
		System.err.println(message);
		super.printStackTrace();
	}
}
