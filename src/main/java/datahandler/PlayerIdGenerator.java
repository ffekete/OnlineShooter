package datahandler;

/** Facilitates generating new ids when a new player is registered. */
public class PlayerIdGenerator {
	private static Long id = 0L; 
	
	/** Generates a unique id. (Long) */
	public static Long generateNewId(){
		id = id + 1;
		return id;
	}
}
