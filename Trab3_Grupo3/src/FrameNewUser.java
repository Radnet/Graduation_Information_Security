import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class FrameNewUser extends JFrame{

	public JFrame ThisFrame;
	
	public FrameNewUser(String Title)
	{
		super(Title);
		ThisFrame = this;
		setLayout(null);
		
		User user = User.GetUserObj();
		
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
  		
  		JTextField TXT_UserName     = new JTextField();
  		JTextField TXT_UserLogin    = new JTextField();
  		JTextField TXT_UserGroup    = new JTextField();
  		JTextField TXT_UserPsw      = new JTextField();
  		JTextField TXT_UserPswConf  = new JTextField();
  		JTextField TXT_UserTanSize  = new JTextField();
  		
  		JButton BTN_NewUser = new JButton("Cadastrar");
  		JButton BTN_Chooser = new JButton(">");
  		
  		JFileChooser KpubChooser = new JFileChooser();
  		
  		//***********************************************
  		
  		JButton BUT_Back = new JButton("Voltar");
  		
		/***********************************************/
  		
  		Container Panel =  getContentPane();
  		
  		/*****  Adjusting the size of attributes *****/
  		
  		LB_Login.setBounds    (10,5,  350,25);
  		LB_Grupo.setBounds    (10,25, 350,25);
  		LB_Decricao.setBounds (10,45, 350,25);
  		LB_Access.setBounds   (10,65, 350,25);
  		LB_userForm.setBounds (10,105,350,25);
  		
  		LB_UserName    .setBounds (10,125,350,25);    
  		LB_UserLogin   .setBounds (10,155,350,25);   
  		LB_UserGroup   .setBounds (10,185,350,25);   
  		LB_UserPsw     .setBounds (10,215,350,25);     
  		LB_UserPswConf .setBounds (10,245,350,25); 
  		LB_UserKPubPath.setBounds (10,275,350,25);
  		LB_UserTanSize .setBounds (10,305,350,25);
  		
  		TXT_UserName    .setBounds (160,125,350,25);
  		TXT_UserLogin   .setBounds (160,155,350,25);
  		TXT_UserGroup   .setBounds (160,185,350,25);
  		TXT_UserPsw     .setBounds (160,215,350,25);
  		TXT_UserPswConf .setBounds (160,245,350,25);
  		BTN_Chooser     .setBounds (160,275, 50,25);
  		TXT_UserTanSize .setBounds (160,305,350,25);
  		
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
  		
  		/********************************************/
  		
  		/*******************  Setting listeners *************************/
  		
  		BTN_Chooser.addActionListener( new ActionListener () {
  			
  		    public void actionPerformed(ActionEvent e) 
  		    {
  		    	KpubChooser.showOpenDialog(ThisFrame);
  		    }
  		    
  		  });
  		
  		BTN_NewUser.addActionListener( new ActionListener () {
  			
  		    public void actionPerformed(ActionEvent e) 
  		    {
    			// Open new user frame
				FrameNewUser FM_NewUser = new FrameNewUser("Novo Usuário");
				FM_NewUser.setVisible(true);
				
				// Close this frame
	    		ThisFrame.dispose();
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
}
