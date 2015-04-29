import java.util.Random;


public class SharedLibrary {
	public static int randInt(int min, int max) 
	{
	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public static void FillUserProps(String Login)
	{
		Dao dao = new Dao();
		
		User user = User.GetUserObj();
		
		boolean isAdmin = dao.IsAdmin(Login);
		user.setAdmin(isAdmin);
		if(isAdmin)
			user.setGrupo("Administrador");
		else
			user.setGrupo("Usuário");
		user.setDescricao(dao.GetName(Login));
		user.setAccess(dao.GetAccess(Login));
	}
}
