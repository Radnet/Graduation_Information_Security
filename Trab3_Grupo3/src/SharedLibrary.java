import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
	
	public static String GetHexadecimal(byte[] info) {
		// convert to hexadecimal
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < info.length; i++) {
			String hex = Integer.toHexString(0x0100 + (info[i] & 0x00FF))
					.substring(1);
			buf.append((hex.length() < 2 ? "0" : "") + hex);
		}
		return buf.toString();
	}
	
	public static String HashWithSalt(String toBeHashed, String Salt)
	{
		String result = "";
		MessageDigest messageDigest;
		try {
			
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update((toBeHashed + Salt).getBytes());
			byte[] pwDigest = messageDigest.digest();
			
			// Converting to hexadecimal
			result = SharedLibrary.GetHexadecimal(pwDigest);
			
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		
		return result;
	}

}
