package pobj.util;

public class InvalidMultiSetFormat extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1582867692750538659L;

	public InvalidMultiSetFormat(String message) {
		super(message);
	}
	
	public InvalidMultiSetFormat(String message, Throwable cause) {
		System.out.println(message);
		System.out.println(cause);
	}
	
}
