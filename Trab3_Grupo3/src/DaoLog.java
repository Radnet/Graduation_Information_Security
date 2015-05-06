import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.TimeZone;

public class DaoLog extends Dao {
    
    private String url;
    private String driver;
    private PreparedStatement pstmt;
    private ResultSet rs;
    
    //Construct as super class
    public DaoLog(){ super();}
    
    public boolean SistemaIniciado(){
        String query = "INSERT INTO registros (time,codigo) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),1001)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao executar query de sistema iniciado");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean SistemaEncerrado(){
        String query = "INSERT INTO registros (time,codigo) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),1002)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de sistema encerrado");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean Autenticacao1Iniciada(){
        String query = "INSERT INTO registros (time,codigo) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),2001)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de autenticacao1 iniciado");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean Autenticacao1Encerrada(){
        String query = "INSERT INTO registros (time,codigo) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),2002)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de autenticacao1 encerrada");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean AcessoLiberado(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),2003,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);
            
            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de acesso liberado");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean AcessoBloqueadoEtapa1(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),2004,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);
            
            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de acesso bloqueado");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean LoginDesconhecido(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),2005,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);
            
            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de login desconhecido");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean Autenticacao2Iniciada(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),3001,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);
            
            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de autenticacao2 iniciada");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean Autenticacao2Encerrada(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),3002,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);
            
            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de autenticacao2 encerrada");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean SenhaPositiva(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),3003,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de senha positiva");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean SenhaNegativa(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),3004,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de senha negativa");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean SenhaPrimeiroErro(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),3005,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de primeiro erro da senha pessoal");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean SenhaSegundoErro(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),3006,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de segundo erro da senha pessoal");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean SenhaTerceiroErro(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),3007,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de terceiro erro da senha pessoal");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean AcessoBloqueadoEtapa2(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),3008,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de acesso bloqueado pela etapa2");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean Autenticacao3Iniciada(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),4001,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de autenticacao3 iniciada");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean Autenticacao3Encerrada(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),4002,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de autenticacao3 encerrada");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean OTPPositiva(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),4003,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de OTP positiva");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean OTPPrimeiroErro(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),4004,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de OTP primeiro erro");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean OTPSegundoErro(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),4005,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de OTP segundo erro");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean OTPTerceiroErro(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),4006,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de OTP terceiro erro");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean AcessoBloqueadoEtapa3(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),4007,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de acesso bloqueado etapa3");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean TelaPrincipal(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),5001,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de tela principal apresentada");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean MenuOpcao1(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),5002,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de menu - opção 1 selecionada");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean MenuOpcao2(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),5003,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de menu - opção 2 selecionada");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean MenuOpcao3(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),5004,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de menu - opção 3 selecionada");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean TelaCadastro(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),6001,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de tela de cadastro apresentada");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean Cadastrar(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),6002,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de botão cadastrar pressionado");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean VoltarDeCadastrar(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),6003,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de botão cadastrar pressionado");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean TelaConsulta(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),7001,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de tela de consulta apresentada");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean VoltarDeConsulta(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),7002,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de botão voltar de consulta pressionado");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean ListarConsulta(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),7003,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de botão listar consulta pressionado");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean CaminhoPKInvalido(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),7004,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de caminho para chave privada inválido");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean FSInvalida(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),7005,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de frase secreta inválida");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean CaminhoPastaInvalido(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),7006,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de caminho da pasta de arquivos inválido");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean ListaArquivos(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),7007,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de lista de arquivos apresentada");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean ArquivoSelecionado(String arquivo, String login){
        String query = "INSERT INTO registros (time,codigo,loginName,arqName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),7008,?,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);
            pstmt.setString(2,arquivo);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de arquivo selecionado para decriptação");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean DecriptacaoOK(String arquivo, String login){
        String query = "INSERT INTO registros (time,codigo,loginName,arqName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),7009,?,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);
            pstmt.setString(2,arquivo);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de arquivo decriptado com sucesso");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean VerificacaoOK(String arquivo, String login){
        String query = "INSERT INTO registros (time,codigo,loginName,arqName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),7010,?,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);
            pstmt.setString(2,arquivo);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de arquivo verificado(integro e auténtico) com sucesso");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean DecriptacaoNotOK(String arquivo, String login){
        String query = "INSERT INTO registros (time,codigo,loginName,arqName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),7011,?,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);
            pstmt.setString(2,arquivo);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de arquivo decriptado com falha");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    
    public boolean VerificacaoNotOK(String arquivo, String login){
        String query = "INSERT INTO registros (time,codigo,loginName,arqName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),7012,?,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);
            pstmt.setString(2,arquivo);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de arquivo verificado com falha");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean TelaSair(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),8001,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de tela de saída apresentada");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean Sair(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),8002,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar query de botão de sair pressionado");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
    
    public boolean VoltarSair(String login){
        String query = "INSERT INTO registros (time,codigo,loginName) VALUES(datetime(CURRENT_TIMESTAMP,'localtime'),8003,?)";
        Connection con = getConnection();
        try {
            // Prepare query statement and avoid SQL injection
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, login);

            // Execute query
            int count = pstmt.executeUpdate();
            
            return (count>0);
            
        } catch (SQLException e){
            System.out.println("Erro ao executar query de botão de voltar de sair pressionado");
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
                System.out.println("Erro ao fechar conexões.");
            }
        }
    }
}
