import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.*;
import java.util.ArrayList;
import java.util.List;

public class DigestCalculator {
	
	public static String 		   	   DigestCalculationType;
	public static List<Archive>        Files;
	public static String 		       DigestListFilePath;
	public static List<DigestFileLine> DigestsFileList;
	
	public static void main (String[] args) throws Exception 
	{
		// Check arguments+	
		if(!ValidateArgs(args))
			System.exit(1);
		// Get DigestCalculationType and DigestListFilePath from args
		DigestCalculationType = args[0];
		DigestListFilePath    = args[args.length - 1];
		
		// Get Archive list
		Files = new ArrayList<Archive>();
		for (int i = 1; i < args.length - 1; i++) 
		{
			Archive file = new Archive();
			file.Path    = args[i];
			// add file to list
			Files.add(file);
		}
		
		// Validate archives
		for(Archive file : Files)
		{
			File f = new File(file.Path);
			// If file doesn't exists or it is a directory, alert and exit
			if(!f.exists() || f.isDirectory())
			{
				System.err.println("The file \"" + file.Path + "\" does not exist.");
				System.exit(1);
			}
		}
		// "exit(1)" not fired? arguments are ok though!
		System.out.println("\nArguments OK!\n");
		
		// Get Name from path
		for (Archive singleFile : Files)
		{
			singleFile.Name = singleFile.Path.substring(singleFile.Path.lastIndexOf("\\") + 1, singleFile.Path.length());
		}
		
		// Print console info
		System.out.println("\nDigest Calculation Type: " + DigestCalculationType);
		System.out.println("\n" + Files.size() + " File(s) Path(s) found.");
		System.out.println("\nDigest List Path: " + DigestListFilePath);
		
		
		
		
		
		// DIGEST CALCULATION FOR FILES !!!!!!!!!!!!!!!!!!!!!!!!!
		
		
		
		
		
		// Read Digest List File and LOAD info in memory LIST
		LoadDigestListFile();
		
		
		// COMPARE CALCULATED DIGESTS WITH THOSE ONES IN THE DIGEST LIST FILE
		// PS.: REMEMBER EVERYTHING IS IN MEMORY!!
		
		
	}
	
	public static void LoadDigestListFile()
	{
		try ( BufferedReader br = new BufferedReader(new FileReader(DigestListFilePath)) )
		{
			DigestsFileList = new ArrayList<DigestFileLine>();
			String sCurrentLine;
			
			while ((sCurrentLine = br.readLine()) != null) 
			{
				DigestFileLine newLine = new DigestFileLine();
				// Split current line
				String[] digestLine = sCurrentLine.split(" ");
				newLine.Name 		= digestLine[0];
				newLine.DigestType1 = digestLine[1];
				newLine.Digest1HEX	= digestLine[2];
				
				// if there is another digest for this line
				if(digestLine.length > 3)
				{
					newLine.DigestType2 = digestLine[3];
					newLine.Digest2HEX  = digestLine[4];
				}
				
				// add newLine to list
				DigestsFileList.add(newLine);
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
	}
	
	public static boolean ValidateArgs(String[] args)
	{
		// Check arguments absence
		if(args.length < 1)
		{
			System.err.println("\nNao foram passados argumentos!");
			return false;
		}
		
		// Verify quantity of commands
		if(args.length < 3)
		{
			System.err.println("\nQuantidade de argumentos insuficiente!!");
			System.err.println("\nA linha de comando deve conter pelo menos 3 argumentos.");
			System.err.println("\nFormato: DigestCalculator <SP> Tipo_Digest <SP>Caminho_Arq1... <SP>Caminho_ArqN<SP>Caminho_ArqListaDigest  ");
			return false;
		}
		
		// Verify first command
		else if( !args[0].equals("MD5") && !args[0].equals("SHA1"))
		{
			System.err.println("\nPrimeiro argumento invalido!!");
			System.err.println("\nO primeiro argumento deve ser o tipo do digest (MD5 ou SHA1)");
			return false;
		}
		
		return true;
	}
	
	public static String GetHexadecimal(byte [] info)
	{
		// convert to hexadecimal
		StringBuffer buf = new StringBuffer();
		for(int i = 0; i < info.length; i++) {
			String hex = Integer.toHexString(0x0100 + (info[i] & 0x00FF)).substring(1);
			buf.append((hex.length() < 2 ? "0" : "") + hex);
		}
		return buf.toString();
	}

}

