import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class FramePassword extends JFrame{

	public JFrame             ThisFrame;
	public ArrayList<Integer> list_psw_1;
	public ArrayList<Integer> list_psw_2;
	
	public FramePassword(String Tiltle)
	{
		super(Tiltle);
		
		ThisFrame = this;
		
		setLayout(null);
		
		/*****  Setting the attributes of the Frame *****/
		
		JButton BUT_OK  		= new JButton("OK");
		
		// 5 buttons representing the password numbers
		JButton BTN_1   		= new JButton();
		JButton BTN_2   		= new JButton();
		JButton BTN_3   		= new JButton();
		JButton BTN_4   		= new JButton();
		JButton BTN_5   		= new JButton();
		
		JPasswordField PAW_Pass = new JPasswordField();
  		JLabel LB_Password    	= new JLabel("Senha:");
  		  		
		/***********************************************/
  		
  		Container Panel =  getContentPane();
  		  		
  		/*****  Adjusting the size of attributes *****/
  		
  		PAW_Pass.setBounds   (90,50,150,25);
  		BUT_OK.setBounds     (90,170,65,25);
  		
  		BTN_1.setBounds      (10, 100,70,40);
  		BTN_2.setBounds      (85, 100,70,40);
  		BTN_3.setBounds      (160,100,70,40);
  		BTN_4.setBounds      (235,100,70,40);
  		BTN_5.setBounds      (310,100,70,40);
  		
  		LB_Password.setBounds(40,49,65,25);
  		
  		
		/*********************************************/

  		PopulateNumberBTNs(BTN_1, BTN_2, BTN_3, BTN_4, BTN_5);
  		
  		/*****  Adding attributes to the  panel *****/
  		
  		Panel.add(BUT_OK);
  		
  		Panel.add(BTN_1);
  		Panel.add(BTN_2);
  		Panel.add(BTN_3);
  		Panel.add(BTN_4);
  		Panel.add(BTN_5);
  		
  		Panel.add(PAW_Pass);
  		Panel.add(LB_Password);
  		
  		/********************************************/
  		
  		
  		
  		// Instantiating password lists
		list_psw_1 = new ArrayList<Integer>();
		list_psw_2 = new ArrayList<Integer>();
		
		
  		
  		/*******************  Setting listeners *************************/
  		
  		ActionListener BTN_Listener = new ActionListener () {
  			
  		    public void actionPerformed(ActionEvent e) 
  		    {
  		    	if (e.getSource() == BTN_1) 
  		    	{
  		    		list_psw_1.add( (Integer) BTN_1.getClientProperty("value1") );
  		    		list_psw_2.add( (Integer) BTN_1.getClientProperty("value2") );
  		    	}
  		    	else if (e.getSource() == BTN_2) 
  		    	{
  		    		list_psw_1.add( (Integer) BTN_2.getClientProperty("value1") );
  		    		list_psw_2.add( (Integer) BTN_2.getClientProperty("value2") );
  		    	}
  		    	else if (e.getSource() == BTN_3) 
  		    	{
  		    		list_psw_1.add( (Integer) BTN_3.getClientProperty("value1") );
  		    		list_psw_2.add( (Integer) BTN_3.getClientProperty("value2") );
  		    	}
  		    	else if (e.getSource() == BTN_4) 
  		    	{
  		    		list_psw_1.add( (Integer) BTN_4.getClientProperty("value1") );
  		    		list_psw_2.add( (Integer) BTN_4.getClientProperty("value2") );
  		    	}
  		    	else if (e.getSource() == BTN_5) 
  		    	{
  		    		list_psw_1.add( (Integer) BTN_5.getClientProperty("value1") );
  		    		list_psw_2.add( (Integer) BTN_5.getClientProperty("value2") );
  		    	}
  		    }
  		    
  		};
  		
  		// Adding listeners
  		BTN_1.addActionListener(BTN_Listener);
  		BTN_2.addActionListener(BTN_Listener);
  		BTN_3.addActionListener(BTN_Listener);
  		BTN_4.addActionListener(BTN_Listener);
  		BTN_5.addActionListener(BTN_Listener);
  	
  		BUT_OK.addActionListener( new ActionListener () {
  			
  		    public void actionPerformed(ActionEvent e) 
  		    {  		    	
  		    	// Create possible password list from  list_psw_1 and list_psw_2
  		    	
  		    	
  		    	
  		    	// Verify all possibilities for a match of the one in DB
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
	
	private void PopulateNumberBTNs(JButton BTN_1, JButton BTN_2, JButton BTN_3, JButton BTN_4, JButton BTN_5)
	{
		// Create a shuffle number list
		ArrayList<Integer> listValue = new ArrayList<Integer>();
		
		listValue.add(0);
		listValue.add(1);
		listValue.add(2);
		listValue.add(3);
		listValue.add(4);
		listValue.add(5);
		listValue.add(6);
		listValue.add(7);
		listValue.add(8);
		listValue.add(9);
		
		Collections.shuffle(listValue);
		
		// Set buttons names
		BTN_1.setText( Integer.toString(listValue.get(0)) + " ou " + Integer.toString(listValue.get(1)) );
		BTN_2.setText( Integer.toString(listValue.get(2)) + " ou " + Integer.toString(listValue.get(3)) );
		BTN_3.setText( Integer.toString(listValue.get(4)) + " ou " + Integer.toString(listValue.get(5)) );
		BTN_4.setText( Integer.toString(listValue.get(6)) + " ou " + Integer.toString(listValue.get(7)) );
		BTN_5.setText( Integer.toString(listValue.get(8)) + " ou " + Integer.toString(listValue.get(9)) );
		
		
		BTN_1.putClientProperty( "value1", listValue.get(0) );
		BTN_1.putClientProperty( "value2", listValue.get(1) );
		BTN_2.putClientProperty( "value1", listValue.get(2) );
		BTN_2.putClientProperty( "value2", listValue.get(3) );
		BTN_3.putClientProperty( "value1", listValue.get(4) );
		BTN_3.putClientProperty( "value2", listValue.get(5) );
		BTN_4.putClientProperty( "value1", listValue.get(6) );
		BTN_4.putClientProperty( "value2", listValue.get(7) );
		BTN_5.putClientProperty( "value1", listValue.get(8) );
		BTN_5.putClientProperty( "value2", listValue.get(9) );
		
	}
}
