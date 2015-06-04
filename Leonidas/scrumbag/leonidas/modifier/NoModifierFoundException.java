<<<<<<< HEAD
package scrumbag.leonidas.modifier;

public class NoModifierFoundException extends Exception {
	private static final long serialVersionUID = -2594315229532820159L;
	private String message;
	
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
=======
package scrumbag.leonidas.modifier;

public class NoModifierFoundException extends Exception {
	private static final long serialVersionUID = -2594315229532820159L;
	private String message;
	
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
>>>>>>> 5c7e126b334cd885a5154cf42e7a4662abf935b2
