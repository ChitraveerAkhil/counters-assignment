package se.techstack.countersassignment.exceptionHandling;

public class CounterNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 584478199930100751L;

	public CounterNotFoundException(String name) {
		super(String.format("Counter named %s is not found in the local memory.", name));
	}

}
