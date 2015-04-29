
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FrameTanList extends JFrame {

    public JFrame ThisFrame;
    public int trys = 3;

    public FrameTanList(String Title) {
        super(Title);

        ThisFrame = this;

        setLayout(null);

        /**
         * *** Setting the attributes of the Frame ****
         */
        JButton BUT_Ok = new JButton("OK");
        final JTextField TXT_OneTimePsw = new JTextField();
        JLabel LB_OneTimePsw = new JLabel("");

        /**
         * ********************************************
         */
        Container Panel = getContentPane();

        /**
         * *** Adjusting the size of attributes ****
         */
        TXT_OneTimePsw.setBounds(150, 50, 80, 25);
        BUT_Ok.setBounds(90, 150, 65, 25);
        LB_OneTimePsw.setBounds(40, 49, 100, 25);

        /**
         * ******************************************
         */
        /**
         * *** Adding attributes to the panel ****
         */
        Panel.add(BUT_Ok);
        Panel.add(TXT_OneTimePsw);
        Panel.add(LB_OneTimePsw);

        /**
         * *****************************************
         */
        /**
         * ********Get User Infos************************************
         */
        // Create DAO object
        Dao dao = new Dao();
        // get Otp List and user Salt
        final String UserLogin = User.GetUserObj().getLogin();
        final ArrayList<String> OtpList = dao.GetOneTimePswsHashs(UserLogin);
        final String userSalt = dao.GetUserSalt(UserLogin);

        /**
         * **********************************************************
         */
        /**
         * ********Random one of the Otps from list*******************
         */
        final int OTPposition_index = SharedLibrary.randInt(0, OtpList.size() - 1);
        int OTPposition = OTPposition_index + 1;
        LB_OneTimePsw.setText("Digite a OTP " + OTPposition + ": ");

        /**
         * **********************************************************
         */
        /**
         * ***************** Setting listeners ************************
         */
        BUT_Ok.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    // Create DAO object
                    Dao dao = new Dao();
                    
                    //Generating digest for OneTimePW try
                    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                    messageDigest.update((TXT_OneTimePsw.getText() + userSalt).getBytes());
                    byte[] oneTimeDigest = messageDigest.digest();
                    
                    String oneTimeHex = SharedLibrary.GetHexadecimal(oneTimeDigest);
                    
                    // VERIFY OTP
                    if (OtpList.get(OTPposition_index).equals(oneTimeHex)) {
                        // Increment access count
                        dao.IncrementAccess(UserLogin);
                        
                        // Fill user props from the db infos
                        SharedLibrary.FillUserProps(UserLogin);
                        
                        // Go to menu!
                        FrameMenu FM_Menu = new FrameMenu("Menu");
                        FM_Menu.setVisible(true);
                        
                        // Close this frame
                        ThisFrame.dispose();
                    } else {
                        trys--;
                        if (trys == 0) {
                            // Block User
                            dao.BlockUser(User.GetUserObj().getLogin());
                            
                            JOptionPane.showMessageDialog(ThisFrame, "One time password errada. Suas tentaivas acabaram. Usu�rio bloqueado por 2 minutos.");
                            
                            // Open Login frame
                            FrameLogin FM_Login = new FrameLogin("Etapa 1 - Login");
                            FM_Login.setVisible(true);
                            
                            // Close this frame
                            ThisFrame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(ThisFrame, "One time password errada, voc� possui " + trys + " tentativas.");
                        }
                    }
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });

        /**
         * *************************************************************
         */
        /**
         * ********************* Centralizing the frame on the screen
         * ********************
         */
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 490) / 2, (screenSize.height - 370) / 2, 400, 250);

        /**
         * ********************************************************************************
         */
        // Makes the size of the screen unchangeable
        setResizable(false);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
