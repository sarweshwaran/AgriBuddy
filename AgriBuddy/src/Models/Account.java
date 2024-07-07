package Models;
import java.util.HashMap;

public class Account {
	private int userId;
	private String password;
	static private HashMap<Integer, String> accList = new HashMap<Integer, String>();
	
	public Account(int id, String password) {
		this.userId = id;
		this.password = password;
		accList.put(this.userId, this.password);
	}
	
	public static boolean validate(int userId, String password) {
		String pass = accList.get(userId);
		if(pass == null || !(pass.equals(password))) {
			return false;
		}
		else{
			return true;
		}
	}
	
	public int getUserId() {
		return this.userId;
	}
	
}
