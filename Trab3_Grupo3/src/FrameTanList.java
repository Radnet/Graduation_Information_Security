import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class FrameTanList extends JFrame{
	public JFrame ThisFrame;
	public int trys = 3; 
	
	public FrameTanList(String Tiltle)
	{
		super(Tiltle);
		
		ThisFrame = this;
		
		setLayout(null);
		
		/*****  Setting the attributes of the Frame *****/
		
		JButton BUT_Ok 			  = new JButton("OK");
  		JTextField TXT_OneTimePsw = new JTextField();
  		JLabel LB_OneTimePsw      = new JLabel("");
  		  		
		/***********************************************/
  		
  		Container Panel =  getContentPane();
  		
  		/*****  Adjusting the size of attributes *****/
  		
  		TXT_OneTimePsw.setBounds(150,50,80,25);
  		BUT_Ok.setBounds		(90,150,65,25);
  		LB_OneTimePsw.setBounds (40,49,100,25);
  		
		/*********************************************/

  		/*****  Adding attributes to the  panel *****/
  		
  		Panel.add(BUT_Ok);
  		Panel.add(TXT_OneTimePsw);
  		Panel.add(LB_OneTimePsw);
  		
  		/********************************************/
  		
  		/**********Get User Infos*************************************/
  		
  		// Create DAO object
    	Dao dao = new Dao();
    	// get Otp List and user Salt
    	String UserLogin 		  = User.GetUserObj().getLogin();
    	ArrayList<String> OtpList = dao.GetOneTimePswsHashs(UserLogin);
    	String userSalt 		  = dao.GetUserSalt(UserLogin);
    	
    	/*************************************************************/
    	
    	/**********Random one of the Otps from list********************/
    	
    	int OTPposition_index = SharedLibrary.randInt(0, OtpList.size()-1);
    	int OTPposition = OTPposition_index + 1;
    	LB_OneTimePsw.setText("Digite a OTP " + OTPposition + ": ");
  		
  		/*************************************************************/
  		
  		/*******************  Setting listeners *************************/
  	
  		BUT_Ok.addActionListener( new ActionListener () {
  			
  		    public void actionPerformed(ActionEvent e) 
  		    {
  		    	// Create DAO object
  		    	Dao dao = new Dao();
  		    	  		    	
  		    	// VERIFY OTP
  		    	if(OtpList.get(OTPposition_index).equals(TXT_OneTimePsw.getText() + userSalt))
  		    	{
  		    		// Go to menu!
  		    		
  		    		// Close this frame
  		    		ThisFrame.dispose();
  		    	}
				else
				{
					trys--;
  		    		if(trys == 0)
  		    		{
  		    			// Block User
  		    			dao.BlockUser(User.GetUserObj().getLogin());
  		    			
  		    			JOptionPane.showMessageDialog(ThisFrame , "One time password errada. Suas tentaivas acabaram. Usuário bloqueado por 2 minutos.");
  		    			  		    			
  		    			// Open Login frame
	  					FrameLogin FM_Login = new FrameLogin("Etapa 1 - Login");
	  					FM_Login.setVisible(true);
	  					
	  					// Close this frame
	  		    		ThisFrame.dispose();
  		    		}
  		    		else
  		    		{	
  		    			JOptionPane.showMessageDialog(ThisFrame , "One time password errada, você possui " + trys + " tentativas.");
  		    		}
				}
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
