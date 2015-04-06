
public class Archive {
	
	public String  Name;
	public String  Path;
	public String  DigestType;
	public byte [] CalculatedDigest;
	public byte [] FirdtOriginalDigest;
	public byte [] SecondtOriginalDigest;
	
	public String  Status;
	
	public Archive()
	{
		Name 		= "";
		Path 		= "";     
		DigestType  = "";
	}
}
