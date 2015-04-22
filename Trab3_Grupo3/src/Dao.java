import java.sql.*;
import java.util.Calendar;
import java.util.TimeZone;

public class Dao {
	
	private String url;
	private String driver;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement pstmt;
	
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
			System.out.println("Erro na Conexao");
			return null;
		}
	}
	
	public void ExecuteUpdate(String query)
	{
		Connection con = getConnection();
		try
		{	
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
		}
		catch(SQLException e)
		{
			pstmt = null;
			System.out.println("Erro ao executar UPDATE.");
		}
		finally 
		{
			try 
			{
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
				System.out.println("Erro ao fechar conexões de update.");
			}
		}
	}
	
	public boolean IsLoginNameOK(String Login)
	{
		String query = "SELECT COUNT(*) as count FROM usuarios WHERE login = '" + Login + "'";
		Connection con = getConnection();
		try
		{
			st = con.createStatement(); 
			//st.setQueryTimeout(30);
			rs = st.executeQuery(query);
			rs.next();
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
		        if (st != null) 
		        {
		            st.close();
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
		
		String query = "SELECT blocked FROM usuarios WHERE login = '" + Login + "'";
		Connection con = getConnection();
		try
		{
			st = con.createStatement(); 
			//st.setQueryTimeout(30);
			rs = st.executeQuery(query);
		
			rs.next();
			boolean response = rs.getBoolean("blocked");
			rs.close();
			
			// Verify if blocked time is over
			if(response == true)
			{
				query = "SELECT blockedDateTime FROM usuarios WHERE login = '" + Login + "'";
				st = con.createStatement(); 
				//st.setQueryTimeout(30);
				rs = st.executeQuery(query);
				
				rs.next();
				Timestamp  blockedDateTime =  rs.getTimestamp("blockedDateTime",tzUTC);
				rs.close();
				
				Calendar cal = Calendar.getInstance();
				
				// Verify if waiting time is over. (120000 milliseconds = 2 minutes)
				if( (cal.getTime().getTime() - blockedDateTime.getTime()) > 120000 )
				{
					String updateString = "UPDATE usuarios SET  blocked = false WHERE login = '" + Login + "'";
					ExecuteUpdate(updateString);
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
		        if (st != null) 
		        {
		            st.close();
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
