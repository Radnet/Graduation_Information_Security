
public class Archive {
	
	public String  Name;
	public String  Path;
	public String  DigestType;
	public byte [] CalculatedDigest;
	public String  CalculatedDigestHEX;
	
	public String  Status;
	
	public Archive()
	{
		Name 		= "";
		Path 		= "";     
		DigestType  = "";
		Status		= "";
	}
}
