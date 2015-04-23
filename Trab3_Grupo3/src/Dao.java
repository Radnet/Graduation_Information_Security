import java.sql.*;
import java.util.Calendar;
import java.util.TimeZone;

public class Dao {
	
	private String url;
	private String driver;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// Date time zone definition
	public static final Calendar tzUTC = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

	// Creates a new connection every time the constructor is called
	public Dao() {
		
		driver = "org.sqlite.JDBC";
		url    = "jdbc:sqlite:trab3_grupo3.db";
	
		try {
	
		Class.forName(driver);
	
		} catch (ClassNotFoundException objErroDriver) {
		System.out.println("Erro ao carregar o driver JDBC");
	
		} 
	}
	
	public Connection getConnection()
	{
		try
		{
			return DriverManager.getConnection(url);
		}
		catch (SQLException objErroConexao) 
		{
			System.out.println("Erro no getConnection.");
			return null;
		}
	}
	
	public boolean IsLoginNameOK(String Login)
	{
		String query = "SELECT COUNT(*) as count FROM usuarios WHERE login = ?";
		Connection con = getConnection();
		try
		{
			// Prepare query statement and avoid SQL injection
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, Login);
			
			// Execute query
			rs = pstmt.executeQuery(query);
			
			// Get first query return row
			rs.next();
			
			// Verify if the user exists on DB and close the result set
			if(rs.getInt("count") > 0)
			{
				rs.close();
				return true;
			}
			else
			{
				rs.close();
				return false;
			}
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao executar query de Login.");
			return false;
		}
		finally 
		{
			try 
			{
				if (rs != null)
				{
					rs.close();
				}
		        if (pstmt != null) 
		        {
		            pstmt.close();
		        }
		        if (con != null) 
		        {
		            con.close();
		        }
			} 
			catch (SQLException ex) 
			{
				System.out.println("Erro ao fechar conexões.");
			}
		}
	}

	public boolean IsUserBlocked(String Login) {
		
		String query = "SELECT blocked FROM usuarios WHERE login = ?";
		Connection con = getConnection();
		try
		{
			// Prepare query statement and avoid SQL injection
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, Login);
			
			// Execute query
			rs = pstmt.executeQuery(query);
		    
			// Get first query return row
			rs.next();
			
			// Get blocked boolean
			boolean response = rs.getBoolean("blocked");
			
			// Close result set
			rs.close();
			
			// Verify if blocked time is over
			if(response == true)
			{
				query = "SELECT blockedDateTime FROM usuarios WHERE login = ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, Login);
				
				// Execute query
				rs = pstmt.executeQuery(query);
				
				// Get first result 
				rs.next();
				
				// Get the time user was blocked 
				Timestamp  blockedDateTime =  rs.getTimestamp("blockedDateTime",tzUTC);
				
				// CLose result set
				rs.close();
				
				// Get dateTime now for comparison
				Calendar cal = Calendar.getInstance();
				
				// Verify if blocked time is over. (120000 milliseconds = 2 minutes)
				if( (cal.getTime().getTime() - blockedDateTime.getTime()) > 120000 )
				{
					// Change blocked flag for false
					String updateString = "UPDATE usuarios SET  blocked = 0 WHERE login = ?";
					pstmt = con.prepareStatement(updateString);
					pstmt.setString(1, Login);
					pstmt.executeUpdate();
					response = false;
				}
			}
			
			return response;
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao executar query de Login.");
			return false;
		}
		finally 
		{
			try 
			{
				if (rs != null)
				{
					rs.close();
				}
		        if (pstmt != null) 
		        {
		            pstmt.close();
		        }
		        if (con != null) 
		        {
		            con.close();
		        }
			} 
			catch (SQLException ex) 
			{
				System.out.println("Erro ao fechar conexões.");
			}
		}
	}
}
