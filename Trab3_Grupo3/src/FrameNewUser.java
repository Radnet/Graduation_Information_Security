import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class FrameNewUser extends JFrame{

	public JFrame ThisFrame;
	public byte[] Kpubbuffer;
	
	public JTextField TXT_UserName   ;
	public JTextField TXT_UserLogin  ;
	public JPasswordField TXT_UserPsw    ;
	public JPasswordField TXT_UserPswConf;
	public JTextField TXT_UserTanSize;
	
	public String ErrorMessage;
	
	
	public FrameNewUser(String Title)
	{
		super(Title);
		ThisFrame = this;
		setLayout(null);
		
		User user = User.GetUserObj();
		
		final Dao dao = new Dao();
		
		/*****  Setting the attributes of the Frame *****/
		
		//********** HEADER*******************************
  		JLabel LB_Login     = new JLabel("Login: " + user.getLogin());
  		JLabel LB_Grupo     = new JLabel("Grupo: " + user.getGrupo());
  		JLabel LB_Decricao  = new JLabel("Descrição: " + user.getDescricao());
  		JLabel LB_Access    = new JLabel("Total de usuários no sistema: " + dao.NumberOfUsers());
  		JLabel LB_userForm  = new JLabel("Formulário de Cadastro");
  		//************************************************
  		
  		//**************** FORM **************************
  		
  		JLabel LB_UserName          = new JLabel("Nome:");                 
  		JLabel LB_UserLogin         = new JLabel("Login:");                
  		JLabel LB_UserGroup         = new JLabel("Grupo:");                
  		JLabel LB_UserPsw           = new JLabel("Senha:");                
  		JLabel LB_UserPswConf       = new JLabel("Confirmação de senha:"); 
  		JLabel LB_UserKPubPath      = new JLabel("Caminho Chave Publica:");
  		JLabel LB_UserTanSize       = new JLabel("Tamanho TAN list:");     
  		
  		TXT_UserName     = new JTextField();
  		TXT_UserLogin    = new JTextField();
  		TXT_UserPsw      = new JPasswordField();
  		TXT_UserPswConf  = new JPasswordField();
  		TXT_UserTanSize  = new JTextField();
  		
  		String[] opts = { "Usuario", "Administrador" };
  		final JComboBox<String> JCB_UserGroup    = new JComboBox<String> (opts);
  		
  		JButton BTN_NewUser = new JButton("Cadastrar");
  		JButton BTN_Chooser = new JButton(">");
  		final JButton BUT_Back = new JButton("Voltar");
  		
  		final JFileChooser KpubChooser = new JFileChooser();
  		
  		//***********************************************
  		
  		
		/***********************************************/
  		
  		Container Panel =  getContentPane();
  		
  		/*****  Adjusting the size of attributes *****/
  		
  		LB_Login        .setBounds (10,5,  350,25);
  		LB_Grupo        .setBounds (10,25, 350,25);
  		LB_Decricao     .setBounds (10,45, 350,25);
  		LB_Access       .setBounds (10,65, 350,25);
  		LB_userForm     .setBounds (10,105,350,25);
  		                
  		LB_UserName     .setBounds (10,125,350,25);    
  		LB_UserLogin    .setBounds (10,155,350,25);   
  		LB_UserGroup    .setBounds (10,185,350,25);   
  		LB_UserPsw      .setBounds (10,215,350,25);     
  		LB_UserPswConf  .setBounds (10,245,350,25); 
  		LB_UserKPubPath .setBounds (10,275,350,25);
  		LB_UserTanSize  .setBounds (10,305,350,25);
  		
  		TXT_UserName    .setBounds (160,125,350,25);
  		TXT_UserLogin   .setBounds (160,155,350,25);
  		JCB_UserGroup   .setBounds (160,185,350,25);
  		TXT_UserPsw     .setBounds (160,215,350,25);
  		TXT_UserPswConf .setBounds (160,245,350,25);
  		BTN_Chooser     .setBounds (160,275, 50,25);
  		TXT_UserTanSize .setBounds (160,305, 20,25);
  		
  		BTN_NewUser     .setBounds (160,345,100,25);
  		BUT_Back        .setBounds (300,345,100,25);
  		
		/*********************************************/

  		/*****  Adding attributes to the  panel *****/
  		
  		Panel.add(LB_Login);
  		Panel.add(LB_Grupo);
  		Panel.add(LB_Decricao);
  		Panel.add(LB_Access);
  		Panel.add(LB_userForm);
  		
  		Panel.add(LB_UserName);    
  		Panel.add(LB_UserLogin);   
  		Panel.add(LB_UserGroup);   
  		Panel.add(LB_UserPsw);     
  		Panel.add(LB_UserPswConf);
  		Panel.add(LB_UserKPubPath);
  		Panel.add(LB_UserTanSize);
  		
  		Panel.add(TXT_UserName);
  		Panel.add(TXT_UserLogin);
  		Panel.add(JCB_UserGroup);
  		Panel.add(TXT_UserPsw);
  		Panel.add(TXT_UserPswConf);
  		Panel.add(BTN_Chooser);
  		Panel.add(TXT_UserTanSize);
  		
  		Panel.add(BTN_NewUser);
  		Panel.add(BUT_Back);
  		
  		/********************************************/
  		
  		/*******************  Setting listeners *************************/
  		
  		BTN_Chooser.addActionListener( new ActionListener () {
  			
  		    public void actionPerformed(ActionEvent e) 
  		    {
  		      if (KpubChooser.showOpenDialog(ThisFrame) == JFileChooser.APPROVE_OPTION) { 
  		    	  	try {
  		    	  		Kpubbuffer = new byte[1024];
  		    	  		
						InputStream is = new FileInputStream(KpubChooser.getSelectedFile());
						is.read(Kpubbuffer);
						
						is.close();
						
					} catch(IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
  		        }
  		    }
  		    
  		  });
  		
  		BTN_NewUser.addActionListener( new ActionListener () {
  			
  		    public void actionPerformed(ActionEvent e) 
  		    {
  		    	// Verify errors on form
  		    	if(IsAllFieldsOK())
  		    	{
  		    		// OK, create new user
  		    		
  		    		// Generate Random STRING Salt with 9 digits
  		    		String newUserSalt = "";
  		    		Random generator = new Random();
  		    		for(int i=0 ; i< 9 ; i++)
  		    		{
  		    			newUserSalt = newUserSalt + generator.nextInt(10);
  		    		}
  		    		
  		    		// Hash psw with salt
  		    		String newUserPsw = SharedLibrary.HashWithSalt(TXT_UserPsw.getText(), newUserSalt);

                    // Get group
                    int isAdmin = 0;
                    if(JCB_UserGroup.getSelectedItem().toString().equals("Administrador"))
                    	isAdmin = 1;
                    
                    // Generate TAN list
                    ArrayList<String> tanList = new ArrayList<String>();
                    char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
                    for(int i=0 ; i < Integer.parseInt(TXT_UserTanSize.getText()) ; i++)
                    {
                    	String otp = "";
                    	for(int j=0 ; j < 5 ; j++)
                    		otp = otp + chars[generator.nextInt(chars.length)];
                    	tanList.add(otp);
                    }
                    
                    // Write tanList o file
                    try
                    {
	                    PrintWriter writer = new PrintWriter("TanList_" + TXT_UserLogin.getText() + ".txt", "UTF-8");
	                    
	        			int counter = 0;
	        			for(String otp : tanList)
	        			{
	        				counter++;
	        				writer.println("OTP" + counter + ":" + otp);
	        			}
	        			writer.close();
                    }
                    catch(Exception ex)
                    {
                    	ex.printStackTrace();
                    }
                    
                    // Mount tanList string
                    String finalOtpString = "";
               	 
	               	// Transform ArrayList into string comma delimited
	               	for(String otp : tanList)
	               	{
	               		String hashedOtp = SharedLibrary.HashWithSalt(otp, newUserSalt);
	               		finalOtpString = finalOtpString + hashedOtp + ",";
	               	}
	                // Remove last comma
	               	finalOtpString = finalOtpString.substring(0,finalOtpString.length()-1);
                    
                    // Insert on tanList DB
                    dao.InsertNewTanList(TXT_UserLogin.getText(), finalOtpString);
                    
                    // Inserting USER on DB
                    dao.CreateUser(TXT_UserName.getText(), TXT_UserLogin.getText(), isAdmin, newUserPsw, Kpubbuffer, newUserSalt);
                    
                    JOptionPane.showMessageDialog(ThisFrame, "Novo usuario criado com sucesso!");
                    
                    // Back to main menu 
                    BUT_Back.doClick();
                    
  		    	}
  		    	else
  		    	{
  		    		JOptionPane.showMessageDialog(ThisFrame, ErrorMessage);
  		    	}
  		    }
  		    
  		  });
  		
  		BUT_Back.addActionListener( new ActionListener () {
  			
  		    public void actionPerformed(ActionEvent e) 
  		    {
    			// Open new user frame
				FrameMenu FM_Menu = new FrameMenu("Frame Menu");
				FM_Menu.setVisible(true);
				
				// Close this frame
	    		ThisFrame.dispose();
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
		Dao dao = new Dao();
		
		// empty verifications
		if(TXT_UserName.getText().equals(""))
		{
			ErrorMessage = "Por favor, preencha o Nome do Usuario";
			return false;
		}
		if(TXT_UserLogin.getText().equals(""))
		{
			ErrorMessage = "Por favor, preencha o Login do Usuario";
			return false;
		}   
		if(TXT_UserPsw.getText().equals(""))
		{
			ErrorMessage = "Por favor, preencha a Senha do Usuario";
			return false;
		}    
		if(TXT_UserPswConf.getText().equals(""))
		{
			ErrorMessage = "Por favor, preencha a Confirmacao de Senha do Usuario";
			return false;
		}
		if(TXT_UserTanSize.getText().equals(""))
		{
			ErrorMessage = "Por favor, preencha o Tamanho da TAN list do Usuario";
			return false;
		}
		if(Kpubbuffer == null)
		{
			ErrorMessage = "Por favor, selecione o caminho da chave publica do Usuario";
			return false;
		}
		
		// Max length verifications
		if(TXT_UserName.getText().length() > 50)
		{
			ErrorMessage = "Nome do usuario deve conter no maximo 50 caracteres";
			return false;
		}
		if(TXT_UserLogin.getText().length() > 20)
		{
			ErrorMessage = "Login do usuario deve conter no maximo 50 caracteres";
			return false;
		}   
		if(TXT_UserPsw.getText().length() > 10)
		{
			ErrorMessage = "Senha do usuario deve conter no maximo 10 caracteres";
			return false;
		}    
		if(TXT_UserPswConf.getText().length() > 10)
		{
			ErrorMessage = "Confirma��o de Senha do usuario deve conter no maximo 10 caracteres";
			return false;
		}
		if(TXT_UserTanSize.getText().length() > 2)
		{
			ErrorMessage = "Tamanho da TAN list nao pode ter mais de dois digitos";
			return false;
		}
		
		// Verify alphabetic chars on password
		String psw = TXT_UserPsw.getText();
		psw = psw.replaceAll("\\D++", "T");
		if(psw.contains("T"))
		{
			ErrorMessage = "A senha deve conter apenas numeros";
			return false;
		}
		
		// Verify psw size
		if(psw.length() < 8 || psw.length() > 10)
		{
			ErrorMessage = "A senha deve ter entre 8 e 10 digitos nao sequenciais";
			return false;
		}
		
		// Verify sequences on password
		for(int i=1 ; i < psw.length() ; i++)
		{
			if(Math.abs(psw.charAt(i) - psw.charAt(i-1)) == 1 || psw.charAt(i) == psw.charAt(i-1))
			{
				ErrorMessage = "A senha nao pode conter nenhum tipo de sequencia ou repeticao";
				return false;
			}
		}
		
		// Verify is password is the same as the confirmation
		if(! TXT_UserPsw.getText().equals(TXT_UserPswConf.getText()))
		{
			ErrorMessage = "Senha digitada esta diferente da confirmacao de senha";
			return false;
		}
		
		// Verify digits on tan list size
		String tanSize = TXT_UserTanSize.getText();
		tanSize = tanSize.replaceAll("\\D++", "T");
		if(tanSize.contains("T"))
		{
			ErrorMessage = "Tamanho da TAN list deve conter apenas numeros";
			return false;
		}
		
		// Verify if login is in use already
		if(dao.IsLoginInUse(TXT_UserLogin.getText()))
		{
			ErrorMessage = "O Login escolhido j� est� em uso, por favor escolha outro";
			return false;
		}
		
		return true;
	}
}
