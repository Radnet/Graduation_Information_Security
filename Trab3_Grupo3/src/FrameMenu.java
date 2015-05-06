import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class FrameMenu extends JFrame{

	public JFrame ThisFrame;
	
	public FrameMenu(String Title)
	{
		super(Title);
		ThisFrame = this;
		setLayout(null);
		
		User user = User.GetUserObj();
                
                //LOG
                //Create DaoLog object
                final DaoLog daoLog = new DaoLog();
                daoLog.TelaPrincipal(user.getLogin());
		
		/*****  Setting the attributes of the Frame *****/
		
  		JLabel LB_Login     = new JLabel("Login: " + user.getLogin());
  		JLabel LB_Grupo     = new JLabel("Grupo: " + user.getGrupo());
  		JLabel LB_Decricao  = new JLabel("Descricao: " + user.getLogin());
  		JLabel LB_Access    = new JLabel("Total de Acessos: " + user.getAccess());
  		JLabel LB_MainMenu  = new JLabel("Menu Principal:");
  		
  		JButton BUT_NewUser = new JButton("1 - Cadastrar Novo Usuario");
  		JButton BUT_Folder  = new JButton("2 - Consultar Pasta de Arquivos Secretos do Usuario");
  		JButton BUT_Exit    = new JButton("3 - Sair do Sistema");
  		
		/***********************************************/
  		
  		Container Panel =  getContentPane();
  		
  		/*****  Adjusting the size of attributes *****/
  		
  		LB_Login.setBounds    (10,5,  350,25);
  		LB_Grupo.setBounds    (10,25, 350,25);
  		LB_Decricao.setBounds (10,45, 350,25);
  		LB_Access.setBounds   (10,65, 350,25);
  		LB_MainMenu.setBounds (10,105,350,25);
  		
  		if(user.isAdmin())
  			BUT_NewUser.setBounds (10,130,350,25);
  		BUT_Folder.setBounds  (10,155,350,25);
  		BUT_Exit.setBounds    (10,180,350,25);
  		
		/*********************************************/

  		/*****  Adding attributes to the  panel *****/
  		
  		Panel.add(LB_Login);
  		Panel.add(LB_Grupo);
  		Panel.add(LB_Decricao);
  		Panel.add(LB_Access);
  		Panel.add(LB_MainMenu);
  		
  		Panel.add(BUT_NewUser);
  		Panel.add(BUT_Folder);
  		Panel.add(BUT_Exit);
  		
  		/********************************************/
  		
  		/*******************  Setting listeners *************************/
  	
  		BUT_NewUser.addActionListener( new ActionListener () {
  			
  		    public void actionPerformed(ActionEvent e) 
  		    {
                        //LOG
                        daoLog.MenuOpcao1(User.GetUserObj().getLogin());
                        
    			// Open new user frame
                        FrameNewUser FM_NewUser = new FrameNewUser("Novo Usuario");
                        FM_NewUser.setVisible(true);

                        // Close this frame
	    		ThisFrame.dispose();
  		    }
  		    
  		  });
  		
  		BUT_Folder.addActionListener( new ActionListener () {
  			
  		    public void actionPerformed(ActionEvent e) 
  		    {
                        //LOG
                        daoLog.MenuOpcao2(User.GetUserObj().getLogin());
                        
  		    	// Open folder frame
                        FrameFileExplorer FM_File = new FrameFileExplorer("Pasta de arquivos");
                        FM_File.setVisible(true);

                        // Close this frame
	    		ThisFrame.dispose();
  		    }
  		    
  		  });
  		
  		BUT_Exit.addActionListener( new ActionListener () {
  			
  		    public void actionPerformed(ActionEvent e) 
  		    {	
                        //LOG
                        daoLog.MenuOpcao3(User.GetUserObj().getLogin());
                        
                        // Open folder frame
                        FrameExit FM_Exit = new FrameExit("Sair do Sitema");
                        FM_Exit.setVisible(true);

                        // Close this frame
	    		ThisFrame.dispose();
  		    }
  		    
  		  });
  	
  		/****************************************************************/
  	
  		/*********************** Centralizing the frame on the screen  *********************/
  		
  		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 
  		setBounds((screenSize.width-490)/2, (screenSize.height-370)/2, 400, 250);

  		/***********************************************************************************/
  		
  		// Makes the size of the screen unchangeable
  		setResizable(false);  
  		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
