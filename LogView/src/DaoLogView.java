import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class DaoLogView 
{
    private String url;
    private String driver;
    private PreparedStatement pstmt;
    private ResultSet rs;

    // Date time zone definition
    public static final Calendar tzUTC = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

    // Creates a new connection every time the constructor is called
    public DaoLogView() {

        driver = "org.sqlite.JDBC";
        url = "jdbc:sqlite:trab3_grupo3.db";

        try {

            Class.forName(driver);

        } catch (ClassNotFoundException objErroDriver) {
            System.out.println("Erro ao carregar o driver JDBC");

        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException objErroConexao) {
            System.out.println("Erro no getConnection.");
            return null;
        }
    }

    public ArrayList<Register> GetRegisters() {
        String query = "SELECT * FROM registros";
        Connection con = getConnection();
        ArrayList<Register> response = new ArrayList<Register>();
        
        try {
        	
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);

            // Execute query
            rs = pstmt.executeQuery();

            // Get query return
            while(rs.next()){
            	Register newRegister = new Register();
            	newRegister.time      = rs.getString("time");
            	newRegister.codigo    = rs.getInt("codigo") ;
            	newRegister.loginName = rs.getString("loginName");
            	newRegister.arqName   = rs.getString("arqName");
            	
            	response.add(newRegister);
            }

            // Verify if the user exists on DB and close the result set
            return response;
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de registers.");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexoes.");
            }
        }
        return response;
    }

    public ArrayList<Message> GetMessages() {
        String query = "SELECT * FROM mensagens";
        Connection con = getConnection();
        ArrayList<Message> response = new ArrayList<Message>();
        
        try {
        	
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);

            // Execute query
            rs = pstmt.executeQuery();

            // Get query return
            while(rs.next()){
            	Message newRegister   = new Message();
            	newRegister.codigo    = rs.getInt("codigo") ;
            	newRegister.menssagem = rs.getString("mensagem");
            	
            	response.add(newRegister);
            }

            // Verify if the user exists on DB and close the result set
            return response;
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de messages.");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexoes.");
            }
        }
        return response;
    }

}
