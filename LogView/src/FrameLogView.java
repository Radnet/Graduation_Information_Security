import java.awt.Container;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;


public class FrameLogView extends JFrame{

	public JFrame ThisFrame;
	public Container Panel;

	DaoLogView dao = new DaoLogView();
	
	public FrameLogView(String Title)
	{
		super(Title);
		ThisFrame = this;
		setLayout(null);
		
		/*****  Setting the attributes of the Frame *****/
		
		TableModel model = new LogTableModel(GetFullLog());
    	JTable table = new JTable(model);
    	table.setFillsViewportHeight(true);
    	
    	JScrollPane scrollPane = new JScrollPane(table);

		/***********************************************/
  		
  		Panel =  getContentPane();
  		
  		/*****  Adjusting the size of attributes *****/
  		
  		scrollPane .setBounds (10,10,780,550);

		/*********************************************/

  		/*****  Adding attributes to the  panel *****/
  		
  		Panel.add(scrollPane);

  		/*********************** Centralizing the frame on the screen  *********************/
  		
  		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 
  		setBounds((screenSize.width-890)/2, (screenSize.height-670)/2, 800, 600);

  		/***********************************************************************************/
  		
  		// Makes the size of the screen unchangeable
  		setResizable(false);  
  		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public ArrayList<Log> GetFullLog()
	{
		ArrayList<Log> fullLog = new ArrayList<Log>();
		
		DaoLogView dao = new DaoLogView();
		
		ArrayList<Register> registers = dao.GetRegisters();
		ArrayList<Message> messages = dao.GetMessages();
		
		for(Register reg : registers)
		{
			Log logRow = new Log();
			
			for(Message msg : messages)
			{
				if(reg.codigo == msg.codigo)
				{
					logRow.text = msg.menssagem;
					break;
				}
			}
			
			if(logRow.text.contains("<login_name>"))
			{
				logRow.text = logRow.text.replace("<login_name>", reg.loginName);
			}
			
			if(logRow.text.contains("<arq_name>"))
			{
				logRow.text = logRow.text.replace("<arq_name>", reg.arqName);
			}
			
			logRow.time = reg.time;
			
			fullLog.add(logRow);
		}
		
		return fullLog;
	}
}
