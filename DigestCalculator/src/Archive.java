
public class Archive {
	
	public String  Name;
	public String  Path;
	public String  DigestType;
	public byte [] CalculatedDigest;
	public byte [] FirstOriginalDigest;
	public byte [] SecondOriginalDigest;
	
	public String  Status;
	
	public Archive()
	{
		Name 		= "";
		Path 		= "";     
		DigestType  = "";
	}
}
