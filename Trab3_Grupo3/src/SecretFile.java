import java.io.File;


public class SecretFile {
	
	public String Name;
	public String Code;
	public String HexAss;
	public String HexEnv;
	public String Status;
	
	public File env;
	public File enc;
	public File asd;
	               
	public SecretFile()
	{
		Name   = "";
		Code   = "";     
		HexAss = "";
		HexEnv = "";
		Status = "";
	}
}
