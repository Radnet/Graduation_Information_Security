/*
 * 
 Singleton class for single user manipulation
 
*/
public class User {
	
	private String      login;
	private boolean		isAdmin;
	private static User userObj;
	private int			access;
	private String		grupo;
	private String		descricao;
	
	private User()
	{
		isAdmin = false;
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
	
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public int getAccess() {
		return access;
	}

	public void setAccess(int access) {
		this.access = access;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
