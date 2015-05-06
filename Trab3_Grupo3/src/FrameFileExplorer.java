import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.bouncycastle.jce.provider.BouncyCastleProvider;



public class FrameFileExplorer extends JFrame{

	public JFrame ThisFrame;
	public Container Panel;
	public File KprivFile;
	public File FilePath;
	
	public File IndexEnv;
	public File IndexAss;
	public File IndexEnc;
	
	public JPasswordField TXT_SecretPhrase   ;
	
	public String ErrorMessage;
	
        public Boolean decrpytFlag = true;
        
	User user = User.GetUserObj();
	
        //Create Dao and DaoLog object
        final DaoLog daoLog = new DaoLog();
	Dao dao = new Dao();
	
	public FrameFileExplorer(String Title)
	{
		super(Title);
		ThisFrame = this;
		setLayout(null);
		
                //LOG
                daoLog.TelaConsulta(user.getLogin());
		
		/*****  Setting the attributes of the Frame *****/
		
		//********** HEADER*******************************
  		JLabel LB_Login         = new JLabel("Login: " + user.getLogin());
  		JLabel LB_Grupo         = new JLabel("Grupo: " + user.getGrupo());
  		JLabel LB_Decricao      = new JLabel("Descricao: " + user.getDescricao());
  		final JLabel LB_Consults      = new JLabel("Total de Consultas: " + dao.GetUserConsults(user.getLogin()));
  		JLabel LB_ArchiveSystem = new JLabel("Sistema de Arquivos Secretos");
  		//************************************************
  		
  		//**************** FORM **************************
  		
  		JLabel LB_UserKPrivPath = new JLabel("Caminho Chave Privada:");
  		JLabel LB_SecretPhrase  = new JLabel("Frase Secreta:");                
  		JLabel LB_FolderPath    = new JLabel("Caminho da Pasta:");
  		final JLabel LB_Instructions  = new JLabel("De um duplo click no nome do arquivo para decriptografa-lo");     
  		final JLabel LB_KprivPath     = new JLabel("");     
  		final JLabel LB_FilesPath     = new JLabel("");     
  		
  		TXT_SecretPhrase        = new JPasswordField();  		
  		
  		JButton BTN_ShowFiles    = new JButton("Listar");
  		JButton BTN_KprivChooser = new JButton(">");
  		JButton BTN_FileChooser  = new JButton(">");
  		
  		JButton BTN_Back         = new JButton("Voltar");
  		
  		final JFileChooser KprivChooser    = new JFileChooser();
  		final JFileChooser FilePathChooser = new JFileChooser();
  		
  		FilePathChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
  		
  		//***********************************************
  		
  		
		/***********************************************/
  		
  		Panel =  getContentPane();
  		
  		/*****  Adjusting the size of attributes *****/
  		
  		LB_Login         .setBounds (10,5,  350,25);
  		LB_Grupo         .setBounds (10,25, 350,25);
  		LB_Decricao      .setBounds (10,45, 350,25);
  		LB_Consults      .setBounds (10,65, 350,25);
  		LB_ArchiveSystem .setBounds (10,105,350,25);
  		                
  		LB_UserKPrivPath .setBounds (10,125,350,25);    
  		LB_SecretPhrase  .setBounds (10,155,350,25);   
  		LB_FolderPath    .setBounds (10,185,350,25); 
  		
  		BTN_KprivChooser .setBounds (160,125,50,25);
  		LB_KprivPath     .setBounds (220,125,570,25);
  		TXT_SecretPhrase .setBounds (160,155,350,25);
  		BTN_FileChooser  .setBounds (160,185,50,25);
  		LB_FilesPath     .setBounds (220,185,570,25);
  		
  		BTN_ShowFiles    .setBounds (10 ,220,100,25);
  		BTN_Back         .setBounds (120,220,100,25);
  		LB_Instructions  .setBounds (230,240,350,25);
  		
		/*********************************************/

  		/*****  Adding attributes to the  panel *****/
  		
  		Panel.add(LB_Login);
  		Panel.add(LB_Grupo);
  		Panel.add(LB_Decricao);
  		Panel.add(LB_Consults);
  		Panel.add(LB_ArchiveSystem);
  		   
  		Panel.add(LB_SecretPhrase);   
  		Panel.add(LB_UserKPrivPath);
  		Panel.add(LB_FolderPath);
  		
  		Panel.add(BTN_KprivChooser);
  		Panel.add(LB_KprivPath);
  		Panel.add(TXT_SecretPhrase);
  		Panel.add(BTN_FileChooser);
  		Panel.add(LB_FilesPath);
  		
  		Panel.add(BTN_ShowFiles);
  		Panel.add(BTN_Back);
  		
  		/********************************************/
  		
  		/*******************  Setting listeners *************************/
  		
  		BTN_KprivChooser.addActionListener( new ActionListener () {
  		    public void actionPerformed(ActionEvent e) 
  		    {
  		      if (KprivChooser.showOpenDialog(ThisFrame) == JFileChooser.APPROVE_OPTION) { 
  		    	 
	    	  		KprivFile = KprivChooser.getSelectedFile();
	    	  		LB_KprivPath.setText(KprivFile.getAbsolutePath());
	    	  		
	    	  		Panel.revalidate();
			    	Panel.repaint();
  		        }
  		    }
  		    
  		  });
  		
  		BTN_FileChooser.addActionListener( new ActionListener () {
  		    public void actionPerformed(ActionEvent e) 
  		    {
  		      if (FilePathChooser.showOpenDialog(ThisFrame) == JFileChooser.APPROVE_OPTION) { 
  		    	  	try {
  		    	  		
						FilePath = FilePathChooser.getSelectedFile();
						LB_FilesPath.setText(FilePath.getAbsolutePath());
						
						Panel.revalidate();
				    	Panel.repaint();
						
					} catch(Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
  		        }
  		    }
  		    
  		  });
  		
  		BTN_Back.addActionListener( new ActionListener () {
  		    public void actionPerformed(ActionEvent e) 
  		    {
                        //LOG
                        daoLog.VoltarDeConsulta(user.getLogin());
                        
    			// Open new user frame
				FrameMenu FM_Menu = new FrameMenu("Frame Menu");
				FM_Menu.setVisible(true);
				
				// Close this frame
	    		ThisFrame.dispose();
  		    }
  		    
  		  });
  		
  		BTN_ShowFiles.addActionListener( new ActionListener () {
  		    public void actionPerformed(ActionEvent e) 
  		    {
                        //LOG
                        daoLog.ListarConsulta(user.getLogin());
                        
  		    	// Verify fields
  		    	if(IsAllFormOK())
  		    	{
  		    		try
  		    		{
				    	// Increment consult on DB
				    	dao.IncrementConsult(user.getLogin());
				    	LB_Consults.setText("Total de Consultas: " + dao.GetUserConsults(user.getLogin()));
				    	
				    	// Get Private key
				    	PrivateKey Kpriv = GetPrivateKey(TXT_SecretPhrase.getText());
				    	// Get symmetric key
				    	Key  symmetricKey = GetSymetricKey(Kpriv, IndexEnv);
				    	// Get File decrypted bytes
				    	byte[] decryptedFileBytes = GetDecriptedEncBytes(IndexEnc, symmetricKey);
				    	
				    	// Verify Signature
				    	if(VerifySignature(decryptedFileBytes, IndexAss ))
				    	{
                                                //LOG
                                                daoLog.ListaArquivos(user.getLogin());
                                                
				    		// Load File list to memory
				    		final ArrayList<SecretFile> IndexFileList = new ArrayList<SecretFile>();
				    		LoadIndexFileToList(decryptedFileBytes, IndexFileList);

				    		// Set Status for each file
				    		SetStatusFileList(IndexFileList);
				    		
				    		// Set HexaDecimal for each file
				    		SetFilesHexadecimal(IndexFileList);
				    		
				    		TableModel model = new FileTableModel(IndexFileList);
					    	final JTable table = new JTable(model);
					    	table.addMouseListener(new MouseAdapter(){
					    	     public void mouseClicked(MouseEvent e){
					    	         if (e.getClickCount() == 2){
					    	        	 int[] selRows = table.getSelectedRows();
					    	        	 TableModel tm = table.getModel();
					    	             String code = (String) tm.getValueAt(selRows[0],0);
					    	             CreateDecryptedFile(IndexFileList, code);
					    	            }
					    	         }
					    	        } );
					    	
					    	table.setFillsViewportHeight(true);
					    	JScrollPane scrollPane = new JScrollPane(table);
					    	scrollPane.setBounds (10,260,780,300);
					    	Panel.add(scrollPane);
					    	Panel.add(LB_Instructions);
					    	
					    	Panel.revalidate();
					    	Panel.repaint();
				    	}
				    	else
				    	{
				    		JOptionPane.showMessageDialog(ThisFrame, "O arquivo Index.enc nao passou no teste de integridade/autenticidade");
				    	}
				    	
  		    		}
  		    		catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
  		    	}
  		    	else
  		    	{
  		    		JOptionPane.showMessageDialog(ThisFrame, ErrorMessage);
  		    	}
  		    	
  		    }

		  });
  	
  		/****************************************************************/
  	
  		/*********************** Centralizing the frame on the screen  *********************/
  		
  		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 
  		setBounds((screenSize.width-890)/2, (screenSize.height-670)/2, 800, 600);

  		/***********************************************************************************/
  		
  		// Makes the size of the screen unchangeable
  		setResizable(false);  
  		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public boolean IsAllFormOK()
	{
		if(KprivFile == null)
		{
                        //LOG
                        daoLog.CaminhoPKInvalido(user.getLogin());
                        
			ErrorMessage = "Por favor, selecione o caminho da chave privada";
			return false;
		}
		if(TXT_SecretPhrase.getText().equals(""))
		{
                        //LOG
                        daoLog.FSInvalida(user.getLogin());
                        
			ErrorMessage = "Por favor, insira a frase secreta";
			return false;
		}
		if(FilePath == null)
		{
                        //LOG
                        daoLog.CaminhoPastaInvalido(user.getLogin());
                        
			ErrorMessage = "Por favor, selecione o caminho da pasta";
			return false;
		}
		
		// Verify if the 3 index files are on the specified directory
		IndexEnv = new File(FilePath.getAbsolutePath() + "\\index.env");
		if(!IndexEnv.exists())
		{
			ErrorMessage = "index.env nao esta presente na pasta selecionada";
			return false;
		}
		IndexAss = new File(FilePath.getAbsolutePath() + "\\index.asd");
		if(!IndexAss.exists())
		{
			ErrorMessage = "index.ass nao esta presente na pasta selecionada";
			return false;
		}
		IndexEnc = new File(FilePath.getAbsolutePath() + "\\index.enc");
		if(!IndexEnc.exists())
		{
			ErrorMessage = "index.enc nao esta presente na pasta selecionada";
			return false;
		}
		
		return true;
	}
	
	
	public PrivateKey GetPrivateKey(String secretPhrase)
	{
            Security.addProvider( new BouncyCastleProvider() );
		try 
		{	
			// Generate symmetric DES key from the secretPhrase 
			SecureRandom prng   = SecureRandom.getInstance("SHA1PRNG", "SUN");
			prng.setSeed(secretPhrase.getBytes("UTF8"));
			KeyGenerator keyGen = KeyGenerator.getInstance("DES");
			keyGen.init(56, prng);
			
			Key prngKey = keyGen.generateKey();
			
			// define DES cipher object
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding", "BC");
			cipher.init(Cipher.DECRYPT_MODE, prngKey);
			
			// Decrypt private key file
			String path       = KprivFile.getAbsolutePath();
			byte[] KprivBytes = cipher.doFinal(getBytes(path));
			
			// get private key from key bytes read above
                        KeyFactory keyFactory        = KeyFactory.getInstance("RSA");
                        PKCS8EncodedKeySpec keyPKCS8 = new PKCS8EncodedKeySpec(KprivBytes);
                        PrivateKey key               = keyFactory.generatePrivate(keyPKCS8);
                        return key;
					
		} catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
		}
		return null;
	}
	
	
	private Key GetSymetricKey(PrivateKey Kpriv, File file) 
	{
		try 
		{
			// define DES cipher object
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
			cipher.init(Cipher.DECRYPT_MODE, Kpriv);
			
			// Decrypt file
			byte[] symetricKeySEEDBytes = cipher.doFinal(getBytes(file.getAbsolutePath()));
			
			SecureRandom prng   = SecureRandom.getInstance("SHA1PRNG", "SUN");
			prng.setSeed(symetricKeySEEDBytes);
			KeyGenerator keyGen = KeyGenerator.getInstance("DES");
			keyGen.init(56, prng);
			
			return keyGen.generateKey();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	private PublicKey GetPublicKey(String login)
	{
		PublicKey kPub = null;
		
		try {
			KeyFactory kf = KeyFactory.getInstance("RSA");
			
			// For private keys use PKCS8EncodedKeySpec; for public keys use X509EncodedKeySpec
			X509EncodedKeySpec ks = new X509EncodedKeySpec(dao.getKpub(login));
			kPub = kf.generatePublic(ks);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return kPub;
	}
	
	private boolean VerifySignature(byte[] decryptedFileBytes, File AsdFile)
	{
		boolean response = false;
		try
		{
			// Initialise Signature 
			Signature sig = Signature.getInstance("MD5WithRSA");
			sig.initVerify(GetPublicKey(user.getLogin()));
			
			// Supply the Signature
			sig.update(decryptedFileBytes);
			
			// Get signature byte[]
			byte[] signatureBytes = new byte[(int) AsdFile.length()];
			FileInputStream fileInputStream=null;
			//convert file into array of bytes
		    fileInputStream = new FileInputStream(AsdFile);
		    fileInputStream.read(signatureBytes);
		    fileInputStream.close();
			
			// Verify
			response = sig.verify(signatureBytes);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	
	public byte[] getBytes (String filePath) {
		try {
			File file = new File(filePath);
	    	FileInputStream fileInputStream;
			fileInputStream = new FileInputStream(file);
			int fileLenght = (int) file.length();
	    	byte[] fileBytes = new byte[fileLenght];
	    	fileInputStream.read(fileBytes);
	    	fileInputStream.close();
	    	return fileBytes;
		} catch (IOException e) {
			System.out.println("FileHandle getBytes(): Unable to get bytes from file '" + filePath + "'. " + e.getMessage());
			return null;
		}
	}
	
	private byte[] GetDecriptedEncBytes(File indexEnc,Key key) {
		decrpytFlag = true;
                byte[] fileBytes = getBytes(indexEnc.getAbsolutePath());
		if (fileBytes == null) {
			return null;
		}
		try {
	    	Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
	    	cipher.init(Cipher.DECRYPT_MODE, key);
	    	return cipher.doFinal(fileBytes);
		} catch (Exception e) {
                        decrpytFlag = false;
                        e.printStackTrace();
			return null;
		}
	}
	
    
	public void LoadIndexFileToList(byte[] indexFileBytes, ArrayList<SecretFile> IndexFileList) {
		
		if(IndexFileList == null)
		{
			IndexFileList = new ArrayList<SecretFile>();
		}
		
		try
		{
			InputStream input = new ByteArrayInputStream(indexFileBytes);
	        BufferedReader bfReader = null;
	        
            bfReader = new BufferedReader(new InputStreamReader(input));
            String temp = null;
            while((temp = bfReader.readLine()) != null)
            {
            	if(!temp.equals(""))
            	{
	            	SecretFile newSecretFile = new SecretFile();
					// Split current line
					String[] line      = temp.split(" ");
					newSecretFile.Name = line[0];
					newSecretFile.Code = line[1];
					newSecretFile.env = new File(FilePath.getAbsolutePath() + "\\" + newSecretFile.Code + ".env");
					newSecretFile.enc = new File(FilePath.getAbsolutePath() + "\\" + newSecretFile.Code + ".enc");
					newSecretFile.asd = new File(FilePath.getAbsolutePath() + "\\" + newSecretFile.Code + ".asd");
					IndexFileList.add(newSecretFile);
            	}
            }
			
			input.close();    
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	private void SetStatusFileList(ArrayList<SecretFile> indexFileList) {
		// Get Private key
                PrivateKey Kpriv = GetPrivateKey(TXT_SecretPhrase.getText());
    	
		for(SecretFile secretFile : indexFileList)
		{
	    	// Get symmetric key
	    	Key  symmetricKey = GetSymetricKey(Kpriv, secretFile.env);
	    	
	    	// Get File decrypted bytes
                boolean success = false;
	    	byte[] decryptedFileBytes = GetDecriptedEncBytes(secretFile.enc, symmetricKey);
	    	
	    	if(VerifySignature(decryptedFileBytes, secretFile.asd ))
	    	{
                        //LOG
                        daoLog.VerificacaoOK(secretFile.Code, user.getLogin());
                        
	    		secretFile.Status = "OK";
	    	}
	    	else
	    	{
                        //LOG
                        daoLog.VerificacaoNotOK(secretFile.Code, user.getLogin());
                        
	    		secretFile.Status = "NOT OK";
	    	}
		}
    	
	}

	private void SetFilesHexadecimal(ArrayList<SecretFile> indexFileList) {
		for(SecretFile scretFile : indexFileList)
		{
			scretFile.HexAss = SharedLibrary.GetHexadecimal(getBytes(scretFile.asd.getAbsolutePath()));
			scretFile.HexEnv = SharedLibrary.GetHexadecimal(getBytes(scretFile.env.getAbsolutePath()));
		}
	}
	
	public void CreateDecryptedFile(ArrayList<SecretFile> indexFileList, String code)
	{
		SecretFile secretFile = null;
                
                //LOG
                daoLog.ArquivoSelecionado(code,user.getLogin());

		for(int i = 0 ; i < indexFileList.size() ; i++)
		{
			if(indexFileList.get(i).Code.equals(code))
			{
				secretFile = indexFileList.get(i); 
			}
		}
		if(secretFile == null)
		{
                        //LOG
                        daoLog.DecriptacaoNotOK(code,user.getLogin());
                
			JOptionPane.showMessageDialog(ThisFrame,"Nao foi possivel decripitar o arquivo de codigo: " + code);
		}
		else
		{
			 try {
			// Get Private key
	    	PrivateKey Kpriv = GetPrivateKey(TXT_SecretPhrase.getText());
	    	// Get symmetric key
	    	Key  symmetricKey = GetSymetricKey(Kpriv, secretFile.env);
	    	// Get File decrypted bytes
	    	byte[] decryptedFileBytes = GetDecriptedEncBytes(secretFile.enc, symmetricKey);
	    	
                //LOG
                if(!decrpytFlag){
                    daoLog.DecriptacaoNotOK(code,user.getLogin());
                }
                
	    	String outPath = secretFile.enc.getParent() + "\\" + secretFile.Name;
	    	FileOutputStream fileOuputStream =  new FileOutputStream(outPath); 
		   
			fileOuputStream.write(decryptedFileBytes);
		    fileOuputStream.close();
                    
                    //LOG
                    daoLog.DecriptacaoOK(code, user.getLogin());
                    
		    JOptionPane.showMessageDialog(ThisFrame, "Arquivo foi decriptogrado e salvo com sucesso");
			 } 
			 catch (IOException e) {
					e.printStackTrace();
			}
		}
	}
}
