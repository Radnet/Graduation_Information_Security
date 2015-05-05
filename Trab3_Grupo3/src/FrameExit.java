import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class FrameExit extends JFrame{

	public JFrame ThisFrame;
	
	public FrameExit(String Title)
	{
		super(Title);
		ThisFrame = this;
		setLayout(null);
		
                //Create DaoLog object
                DaoLog daoLog = new DaoLog();
                //LOG
                daoLog.SistemaEncerrado();
                
		User user = User.GetUserObj();
                
		/*****  Setting the attributes of the Frame *****/
		
  		JLabel LB_Login     = new JLabel("Login: " + user.getLogin());
  		JLabel LB_Grupo     = new JLabel("Grupo: " + user.getGrupo());
  		JLabel LB_Decricao  = new JLabel("Nome: " + user.getDescricao());
  		JLabel LB_Access    = new JLabel("Total de Acessos: " + user.getAccess());
  		JLabel LB_MainMenu  = new JLabel("Pressione o botao Sair para confirmar");
  		
  		JButton BTN_Exit = new JButton("Sair");
  		JButton BTN_Back = new JButton("Voltar ao Menu");
  		
		/***********************************************/
  		
  		Container Panel =  getContentPane();
  		
  		/*****  Adjusting the size of attributes *****/
  		
  		LB_Login.setBounds    (10,5,  350,25);
  		LB_Grupo.setBounds    (10,25, 350,25);
  		LB_Decricao.setBounds (10,45, 350,25);
  		LB_Access.setBounds   (10,65, 350,25);
  		LB_MainMenu.setBounds (10,105,350,25);
  		
  		BTN_Exit.setBounds    (10,130,350,25);
  		BTN_Back.setBounds    (10,155,350,25);
  		
		/*********************************************/

  		/*****  Adding attributes to the  panel *****/
  		
  		Panel.add(LB_Login);
  		Panel.add(LB_Grupo);
  		Panel.add(LB_Decricao);
  		Panel.add(LB_Access);
  		Panel.add(LB_MainMenu);
  		
  		Panel.add(BTN_Exit);
  		Panel.add(BTN_Back);
  		
  		/********************************************/
  		
  		/*******************  Setting listeners *************************/
  	
  		BTN_Exit.addActionListener( new ActionListener () {
  			
  		    public void actionPerformed(ActionEvent e) 
  		    {
  		    	// Close this frame
	    		ThisFrame.dispose();
	    		
	    		// End program
	    		System.exit(0);
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
