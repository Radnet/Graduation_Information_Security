import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import sun.security.x509.X509AttributeName;



public class FrameFileExplorer extends JFrame{

	public JFrame ThisFrame;
	public Container Panel;
	public byte[] KprivFileBuffer;
	public File FilePath;
	
	public File IndexEnv;
	public File IndexAss;
	public File IndexEnc;
	
	public JPasswordField TXT_SecretPhrase   ;
	
	public String ErrorMessage;
	
	User user = User.GetUserObj();
	
	Dao dao = new Dao();
	
	public FrameFileExplorer(String Title)
	{
		super(Title);
		ThisFrame = this;
		setLayout(null);
		
		
		/*****  Setting the attributes of the Frame *****/
		
		//********** HEADER*******************************
  		JLabel LB_Login         = new JLabel("Login: " + user.getLogin());
  		JLabel LB_Grupo         = new JLabel("Grupo: " + user.getGrupo());
  		JLabel LB_Decricao      = new JLabel("Descrição: " + user.getDescricao());
  		final JLabel LB_Consults      = new JLabel("Total de Consultas: " + dao.GetUserConsults(user.getLogin()));
  		JLabel LB_ArchiveSystem = new JLabel("Sistema de Arquivos Secretos");
  		//************************************************
  		
  		//**************** FORM **************************
  		
  		JLabel LB_UserKPrivPath = new JLabel("Caminho Chave Privada:");
  		JLabel LB_SecretPhrase  = new JLabel("Frase Secreta:");                
  		JLabel LB_FolderPath    = new JLabel("Caminho da Pasta:");     
  		
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
  		TXT_SecretPhrase .setBounds (160,155,350,25);
  		BTN_FileChooser  .setBounds (160,185,50,25);
  		
  		BTN_ShowFiles    .setBounds (10 ,220,100,25);
  		BTN_Back         .setBounds (120,220,100,25);
  		
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
  		Panel.add(TXT_SecretPhrase);
  		Panel.add(BTN_FileChooser);
  		
  		Panel.add(BTN_ShowFiles);
  		Panel.add(BTN_Back);
  		
  		/********************************************/
  		
  		/*******************  Setting listeners *************************/
  		
  		BTN_KprivChooser.addActionListener( new ActionListener () {
  		    public void actionPerformed(ActionEvent e) 
  		    {
  		      if (KprivChooser.showOpenDialog(ThisFrame) == JFileChooser.APPROVE_OPTION) { 
  		    	  	try {
  		    	  		KprivFileBuffer = new byte[1024];
  		    	  		
						InputStream is = new FileInputStream(KprivChooser.getSelectedFile());
						is.read(KprivFileBuffer);
						
						is.close();
						
					} catch(IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
  		        }
  		    }
  		    
  		  });
  		
  		BTN_FileChooser.addActionListener( new ActionListener () {
  		    public void actionPerformed(ActionEvent e) 
  		    {
  		      if (FilePathChooser.showOpenDialog(ThisFrame) == JFileChooser.APPROVE_OPTION) { 
  		    	  	try {
  		    	  		
						FilePath = FilePathChooser.getSelectedFile();
						
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
  		    	// Verify fields
  		    	if(IsAllFieldsOK())
  		    	{
  		    		try
  		    		{
				    	// Increment consult on DB
				    	dao.IncrementConsult(user.getLogin());
				    	LB_Consults.setText("Total de Consultas: " + dao.GetUserConsults(user.getLogin()));
				    	
				    	// Get Private key
				    	PrivateKey Kpriv       = GetPrivateKey(TXT_SecretPhrase.getText(), KprivFileBuffer);
				    	SecretKey  symetricKey = GetSymetricKey(Kpriv, IndexEnv);
				    	
				    	// Verify Signature
				    	if(VerifySignature(IndexEnc, IndexAss ))
				    	{
				    		String[] columnNames = {"Nome", "Hexa AssD", "Hexa EnvD", "Status"};
					    	
					  		Object[][] data = {	};
					  		
					    	JTable table = new JTable(data, columnNames);
					    	table.setFillsViewportHeight(true);
					    	JScrollPane scrollPane = new JScrollPane(table);
					    	scrollPane.setBounds (10,260,780,300);
					    	Panel.add(scrollPane);
					    	
					    	
					    	Panel.revalidate();
					    	Panel.repaint();
				    	}
				    	else
				    	{
				    		JOptionPane.showMessageDialog(ThisFrame, "O arquivo Inde.enc nao passou no teste de integridade/autenticidade");
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
	
	public boolean IsAllFieldsOK()
	{
		if(KprivFileBuffer == null)
		{
			ErrorMessage = "Por favor, selecione o caminho da chave privada";
			return false;
		}
		if(TXT_SecretPhrase.getText().equals(""))
		{
			ErrorMessage = "Por favor, insira a frase secreta";
			return false;
		}
		if(FilePath == null)
		{
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
	
	public PrivateKey GetPrivateKey(String secretPhrase, byte[] buffer)
	{
		try 
		{	
			// Generate symmetric DES key from the secretPhrase 
			
			SecureRandom prng = new SecureRandom(secretPhrase.getBytes("UTF8"));
			KeyGenerator keyGen = KeyGenerator.getInstance("DES", prng.getProvider());
			
			SecretKey prngKey = keyGen.generateKey();
			
			// define DES cipher object
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, prngKey);
			
			// Decrypt private key file
			byte[] KprivBytes = cipher.doFinal(buffer);
			
			KeyFactory kf = KeyFactory.getInstance("DES");
			// For private keys use PKCS8EncodedKeySpec; for public keys use X509EncodedKeySpec
			PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(KprivBytes);
			return kf.generatePrivate(ks);
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private SecretKey GetSymetricKey(PrivateKey Kpriv, File file) 
	{
		try 
		{
			// define DES cipher object
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, Kpriv);
			
			// Decrypt private key file
			InputStream is = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			is.read(buffer);
			is.close();
			
			byte[] symetricKeySEEDBytes = cipher.doFinal(buffer);
			
			SecureRandom prng = new SecureRandom(symetricKeySEEDBytes);
			KeyGenerator keyGen = KeyGenerator.getInstance("DES", prng.getProvider());
			
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
			
			KeyFactory kf = KeyFactory.getInstance("DES");
			
			// For private keys use PKCS8EncodedKeySpec; for public keys use X509EncodedKeySpec
			X509EncodedKeySpec ks = new X509EncodedKeySpec(dao.getKpub(login));
			kPub = kf.generatePublic(ks);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return kPub;
	}
	
	private boolean VerifySignature(File EncFile, File AsdFile)
	{
		boolean response = false;
		try
		{
			// Initialise Signature 
			Signature sig = Signature.getInstance("MD5withDES");
			sig.initVerify(GetPublicKey(user.getLogin()));
			
			// Supply the Signature
			FileInputStream datafis = new FileInputStream(EncFile);
			BufferedInputStream bufin = new BufferedInputStream(datafis);

			byte[] buffer = new byte[1024];
			int len;
			while (bufin.available() != 0) {
			    len = bufin.read(buffer);
			    sig.update(buffer, 0, len);
			};

			bufin.close();
			
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
	
}
