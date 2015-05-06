import java.util.List;

import javax.swing.table.AbstractTableModel;

public class LogTableModel extends AbstractTableModel {
	  private List<Log> logList ;
	  private String[] columns ; 
	
	  public LogTableModel(List<Log> logList){
	    super();
	    this.logList = logList ;
	    columns = new String[] {"TimeStamp","Registro"};
	  }
	
	  // Number of column of your table
	  public int getColumnCount() {
	    return columns.length ;
	  }
	
	  // Number of row of your table
	  public int getRowCount() {
	    return logList.size();
	  }
	
	  // The object to render in a cell
	  public Object getValueAt(int row, int col) {
		if(col == 0)
			return logList.get(row).time;
		else
			return logList.get(row).text;
	  }
	
	  // Optional, the name of your column
	  public String getColumnName(int col) {
	    return columns[col] ;
	  }
}
