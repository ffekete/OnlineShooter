package datahandler;

public class PlayerIdGenerator {
	private static Long id = 0L; 
	
	public static Long generateNewId(){
		id = id + 1;
		return id;
	}
}
