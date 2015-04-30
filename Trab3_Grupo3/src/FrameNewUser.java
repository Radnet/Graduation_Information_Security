import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;


public class FrameNewUser extends JFrame{

	public JFrame ThisFrame;
	public byte[] Kpubbuffer = new byte[1024];
	
	public JTextField TXT_UserName   ;
	public JTextField TXT_UserLogin  ;
	public JTextField TXT_UserGroup  ;
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
		
		Dao dao = new Dao();
		
		/*****  Setting the attributes of the Frame *****/
		
		//********** HEADER*******************************
  		JLabel LB_Login     = new JLabel("Login: " + user.getLogin());
  		JLabel LB_Grupo     = new JLabel("Grupo: " + user.getGrupo());
  		JLabel LB_Decricao  = new JLabel("Descrição: " + user.getDescricao());
  		JLabel LB_Access    = new JLabel("Total de Acessos: " + user.getAccess());
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
  		TXT_UserGroup    = new JTextField();
  		TXT_UserPsw      = new JPasswordField();
  		TXT_UserPswConf  = new JPasswordField();
  		TXT_UserTanSize  = new JTextField();
  		
  		JButton BTN_NewUser = new JButton("Cadastrar");
  		JButton BTN_Chooser = new JButton(">");
  		JButton BUT_Back = new JButton("Voltar");
  		
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
  		TXT_UserGroup   .setBounds (160,185,350,25);
  		TXT_UserPsw     .setBounds (160,215,350,25);
  		TXT_UserPswConf .setBounds (160,245,350,25);
  		BTN_Chooser     .setBounds (160,275, 50,25);
  		TXT_UserTanSize .setBounds (160,305,350,25);
  		
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
  		Panel.add(TXT_UserGroup);
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
  		    	if(IsAllFieldsOK())
  		    	{
  		    		
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
		if(Kpubbuffer.length == 0)
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
			ErrorMessage = "Confirmação de Senha do usuario deve conter no maximo 10 caracteres";
			return false;
		}
		if(TXT_UserTanSize.getText().length() > 2)
		{
			ErrorMessage = "Tamanho da TAN list nao pode ter mais de dois digitos";
			return false;
		}
		
		// Verify digits on tan list size
		String tanSize = TXT_UserTanSize.getText();
		tanSize.replaceAll("\\D++", "T");
		if(tanSize.contains("T"))
		{
			ErrorMessage = "Tamanho da TAN list deve conter apenas numeros";
			return false;
		}
		
		// Verify is password is the same as the confirmation
		if(! TXT_UserPsw.getText().equals(TXT_UserPswConf.getText()))
		{
			ErrorMessage = "Senha digitada esta diferente da confirmacao de senha";
			return false;
		}
		
		return true;
	}
}
