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
import javax.swing.JTextField;


public class FrameFileExplorer extends JFrame{

	public JFrame ThisFrame;
	public byte[] Kprivbuffer;
	
	public JTextField TXT_SecretPhrase   ;
	
	public FrameFileExplorer(String Title)
	{
		super(Title);
		ThisFrame = this;
		setLayout(null);
		
		User user = User.GetUserObj();
		
		Dao dao = new Dao();
		
		/*****  Setting the attributes of the Frame *****/
		
		//********** HEADER*******************************
  		JLabel LB_Login         = new JLabel("Login: " + user.getLogin());
  		JLabel LB_Grupo         = new JLabel("Grupo: " + user.getGrupo());
  		JLabel LB_Decricao      = new JLabel("Descrição: " + user.getDescricao());
  		JLabel LB_Access        = new JLabel("Total de Consultas: " + dao.getUserConsults());
  		JLabel LB_ArchiveSystem = new JLabel("Sistema de Arquivos Secretos");
  		//************************************************
  		
  		//**************** FORM **************************
  		
  		JLabel LB_UserKPrivPath = new JLabel("Caminho Chave Privada:");
  		JLabel LB_SecretPhrase  = new JLabel("Frase Secreta:");                
  		JLabel LB_FolderPath    = new JLabel("Caminho da Pasta:");     
  		
  		TXT_SecretPhrase        = new JTextField();  		
  		
  		JButton BTN_ShowFiles    = new JButton("Listar");
  		JButton BTN_KprivChooser = new JButton(">");
  		JButton BTN_FileChooser  = new JButton(">");
  		
  		JButton BUT_Back         = new JButton("Voltar");
  		
  		final JFileChooser KpubChooser = new JFileChooser();
  		
  		//***********************************************
  		
  		
		/***********************************************/
  		
  		Container Panel =  getContentPane();
  		
  		/*****  Adjusting the size of attributes *****/
  		
  		LB_Login         .setBounds (10,5,  350,25);
  		LB_Grupo         .setBounds (10,25, 350,25);
  		LB_Decricao      .setBounds (10,45, 350,25);
  		LB_Access        .setBounds (10,65, 350,25);
  		LB_ArchiveSystem .setBounds (10,105,350,25);
  		                
  		LB_UserKPrivPath .setBounds (10,125,350,25);    
  		LB_SecretPhrase  .setBounds (10,155,350,25);   
  		LB_FolderPath    .setBounds (10,185,350,25); 
  		
  		BTN_KprivChooser .setBounds (160,125,50,25);
  		TXT_SecretPhrase .setBounds (160,155,350,25);
  		BTN_FileChooser  .setBounds (160,185,50,25);
  		
  		BTN_ShowFiles    .setBounds (160,345,100,25);
  		BUT_Back         .setBounds (300,345,100,25);
  		
		/*********************************************/

  		/*****  Adding attributes to the  panel *****/
  		
  		Panel.add(LB_Login);
  		Panel.add(LB_Grupo);
  		Panel.add(LB_Decricao);
  		Panel.add(LB_Access);
  		Panel.add(LB_ArchiveSystem);
  		   
  		Panel.add(LB_SecretPhrase);   
  		Panel.add(LB_UserKPrivPath);
  		Panel.add(LB_FolderPath);
  		
  		Panel.add(BTN_KprivChooser);
  		Panel.add(TXT_SecretPhrase);
  		Panel.add(BTN_FileChooser);
  		
  		Panel.add(BTN_ShowFiles);
  		Panel.add(BUT_Back);
  		
  		/********************************************/
  		
  		/*******************  Setting listeners *************************/
  		
  		BTN_KprivChooser.addActionListener( new ActionListener () {
  		    public void actionPerformed(ActionEvent e) 
  		    {
  		      if (KpubChooser.showOpenDialog(ThisFrame) == JFileChooser.APPROVE_OPTION) { 
  		    	  	try {
  		    	  		Kprivbuffer = new byte[1024];
  		    	  		
						InputStream is = new FileInputStream(KpubChooser.getSelectedFile());
						is.read(Kprivbuffer);
						
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
  		      if (KpubChooser.showOpenDialog(ThisFrame) == JFileChooser.APPROVE_OPTION) { 
  		    	  	try {
  		    	  		Kprivbuffer = new byte[1024];
  		    	  		
						InputStream is = new FileInputStream(KpubChooser.getSelectedFile());
						is.read(Kprivbuffer);
						
						is.close();
						
					} catch(IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
}
