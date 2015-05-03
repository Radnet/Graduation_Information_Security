import java.util.List;

import javax.swing.table.AbstractTableModel;

public class FileTableModel extends AbstractTableModel {
	  private List<SecretFile> files ;
	  private String[] columns ; 
	
	  public FileTableModel(List<SecretFile> aClubList){
	    super();
	    files = aClubList ;
	    columns = new String[] {"Codigo","Nome", "Hexa AssD", "Hexa EnvD", "Status"};
	  }
	
	  // Number of column of your table
	  public int getColumnCount() {
	    return columns.length ;
	  }
	
	  // Number of row of your table
	  public int getRowCount() {
	    return files.size();
	  }
	
	  // The object to render in a cell
	  public Object getValueAt(int row, int col) {
		SecretFile singleFile = files.get(row);
	    switch(col) {
	      case 0: return singleFile.Code;
	      case 1: return singleFile.Name;
	      case 2: return singleFile.HexAss;
	      case 3: return singleFile.HexEnv;
	      case 4: return singleFile.Status;
	      default: return null;
	    }
	  }
	
	  // Optional, the name of your column
	  public String getColumnName(int col) {
	    return columns[col] ;
	  }
	  
	  public boolean isCellEditable(int row, int column) { // custom isCellEditable function
		  if(column == 2 || column == 3)
			  return true;
		  else
			  return false;
	  }
}
