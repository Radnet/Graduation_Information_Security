
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import javax.sql.rowset.serial.SerialBlob;

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

    public boolean IsLoginNameOK(String Login) {
        String query = "SELECT COUNT(*) as count FROM usuarios WHERE login = ?";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, Login);

            // Execute query
            rs = pstmt.executeQuery();

            // Get first query return row
            rs.next();

            // Verify if the user exists on DB and close the result set
            if (rs.getInt("count") > 0) {
                rs.close();
                return true;
            } else {
                rs.close();
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de Login.");
            return false;
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
                System.out.println("Erro ao fechar conexcoes.");
            }
        }
    }

    public boolean IsUserBlocked(String Login) {

        String query = "SELECT blocked FROM usuarios WHERE login = ?";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, Login);

            // Execute query
            rs = pstmt.executeQuery();

            // Get first query return row
            rs.next();

            // Get blocked boolean
            boolean response = rs.getBoolean("blocked");

            // Close result set
            rs.close();

            // Verify if blocked time is over
            if (response == true) {
                query = "SELECT blockedDateTime FROM usuarios WHERE login = ?";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, Login);

                // Execute query
                rs = pstmt.executeQuery();

                // Get first result 
                rs.next();

                // Get the time user was blocked
                long blockedDateTime = rs.getLong("blockedDateTime");

                // CLose result set
                rs.close();

                // Get dateTime now for comparison
                Calendar cal = Calendar.getInstance();

                // Verify if blocked time is over. (120000 milliseconds = 2 minutes)
                if ((cal.getTime().getTime() - blockedDateTime) > 120000) {
                    // Change blocked flag for false
                    String updateString = "UPDATE usuarios SET  blocked = 0 WHERE login = ?";
                    pstmt = con.prepareStatement(updateString);
                    pstmt.setString(1, Login);
                    pstmt.executeUpdate();
                    response = false;
                }
            }

            return response;
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de Blocked.");
            return true;
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }

    public String GetPswHash(String Login) {
        String query = "SELECT senha FROM usuarios WHERE login = ?";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, Login);

            // Execute query
            rs = pstmt.executeQuery();

            // Get first query return row
            rs.next();

            // Verify if the user exists on DB and close the result set
            return rs.getString("senha");
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de PswHash.");
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
                System.out.println("Erro ao fechar conexcoes.");
            }
        }
        return "";
    }

    public String GetUserSalt(String Login) {
        String query = "SELECT salt FROM usuarios WHERE login = ?";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, Login);

            // Execute query
            rs = pstmt.executeQuery();

            // Get first query return row
            rs.next();

            // Verify if the user exists on DB and close the result set
            return rs.getString("salt");
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de salt.");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
        return "";
    }

    public void BlockUser(String Login) {
        Connection con = getConnection();
        // Get dateTime now for comparison
        Calendar cal = Calendar.getInstance();
        try {
            String updateString = "UPDATE usuarios SET  blocked = 1,blockedDateTime = " + cal.getTime().getTime() + "  WHERE login = ?";
            pstmt = con.prepareStatement(updateString);
            pstmt.setString(1, Login);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao executar update de Block.");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }

    public ArrayList<String> GetOneTimePswsHashs(String Login) {
        ArrayList<String> responseList = new ArrayList<String>();

        String query = "SELECT tanList FROM tanLists WHERE login = ?";
        Connection con = getConnection();

        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, Login);

            // Execute query
            rs = pstmt.executeQuery();

            // Get first query return row
            rs.next();

            String[] splitArray = rs.getString("tanList").split(",");

            for (String str : splitArray) {
                responseList.add(str);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de PswHash.");
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
                System.out.println("Erro ao fechar conexcoes.");
            }
        }
        return responseList;
    }

    public boolean IsAdmin(String Login) {
        String query = "SELECT isAdmin FROM usuarios WHERE login = ?";
        Connection con = getConnection();

        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, Login);

            // Execute query
            rs = pstmt.executeQuery();

            // Get first query return row
            rs.next();

            return rs.getBoolean("isAdmin");
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de IsAdmin.");
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
                System.out.println("Erro ao fechar conexcoes.");
            }
        }
        return false;
    }

    public void IncrementAccess(String Login) {
        Connection con = getConnection();

        try {
            String updateString = "UPDATE usuarios SET  acessos = acessos + 1  WHERE login = ?";
            pstmt = con.prepareStatement(updateString);
            pstmt.setString(1, Login);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de IncrementAccess.");
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
                System.out.println("Erro ao fechar conexcoes.");
            }
        }
    }

    public String GetName(String Login) {
        String query = "SELECT nome FROM usuarios WHERE login = ?";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, Login);

            // Execute query
            rs = pstmt.executeQuery();

            // Get first query return row
            rs.next();

            // Verify if the user exists on DB and close the result set
            return rs.getString("nome");
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de GetName.");
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
                System.out.println("Erro ao fechar conexcoes.");
            }
        }
        return "";
    }

    public int GetAccess(String Login) {
        String query = "SELECT acessos FROM usuarios WHERE login = ?";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, Login);

            // Execute query
            rs = pstmt.executeQuery();

            // Get first query return row
            rs.next();

            // Verify if the user exists on DB and close the result set
            return rs.getInt("acessos");
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de GetName.");
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
                System.out.println("Erro ao fechar conexcoes.");
            }
        }
        return 0;
    }

    public boolean UserHasOTP(String login) {
        String query = "SELECT tanList FROM tanLists WHERE login = ?";
        Connection con = getConnection();
        boolean hasOTP = false;
        try {

            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            //Execute the query
            rs = pstmt.executeQuery();

            // Get first query return row
            rs.next();

            String[] splitArray = rs.getString("tanList").split(",");

            for (String otp : splitArray) {
                if (!otp.equals("used")) {
                    hasOTP = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de UserHasOTP");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
        return hasOTP;
    }

    public void RemoveTAN(String Login, ArrayList<String> OtpList, int index) {
        String query = "UPDATE tanLists SET tanList = ? WHERE login = ?";
        Connection con = getConnection();
        try {
            String tanList = "";
            int count = 0;

            OtpList.set(index, "used");
            for (String tan : OtpList) {
                tanList += tan;

                count++;
                if (count != OtpList.size()) {
                    tanList += ",";
                }
            }

            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, tanList);
            pstmt.setString(2, Login);

            // Execute query
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de RemoveTan.");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }

    public boolean IsLoginInUse(String Login)
    {
    	 String query = "SELECT count(*) as count FROM usuarios WHERE login = ?";
         Connection con = getConnection();

         try {
             // Prepare query statement and avoid SQL injection
             pstmt = con.prepareStatement(query);
             pstmt.setString(1, Login);

             // Execute query
             rs = pstmt.executeQuery();

             // Get first query return row
             rs.next();

             return rs.getInt("count") > 0;
         } catch (SQLException e) {
             System.out.println("Erro ao executar query de IsAdmin.");
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
                 System.out.println("Erro ao fechar conexões.");
             }
         }
         return true;
    }
    
    public void CreateUser(String name, String login, int isAdmin, String password, byte[] pubkey, String salt ) {
        Connection con = getConnection();

        try {
        	
            String insertString = "INSERT INTO usuarios VALUES (?,?,?,?,?,?, 0, 0, 0)";
            pstmt = con.prepareStatement(insertString);
            
            pstmt.setString(1, name);
            pstmt.setString(2, login);
            pstmt.setInt   (3, isAdmin);
            pstmt.setString(4, password);
            pstmt.setBytes (5, pubkey);
            pstmt.setString(6, salt);
            
            pstmt.execute();
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar insert de CreateUser.");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }

    public void InsertNewTanList(String Login, ArrayList<String> tanList)
    {
    	//To-DO
    }
}
