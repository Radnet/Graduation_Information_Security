/*
 * 
 Singleton class for single user manipulation
 
*/
public class User {
	
	private String      login;
	private static User userObj;
	
	private User()
	{
		
	}
	
	public static User GetUserObj()
	{
		if(userObj == null)
		{
			userObj = new User();
		}
		return userObj;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
