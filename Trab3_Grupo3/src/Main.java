
public class Main {
	
	public static void main(String[] args) throws Exception {
		
		FrameLogin FrameLogin = new FrameLogin("Trab3 Segurança | Etapa 1 - Login");
		FrameLogin.setVisible(true);
		
                //LOG
                // Create DaoLog object
                DaoLog daoLog = new DaoLog();
		daoLog.SistemaIniciado();
                
		//******************************************************************
		//TESTE

		/*
		 
		User user = User.GetUserObj();
		user.setLogin("admin");
		
		SharedLibrary.FillUserProps("admin");
		
		FrameMenu frameMenu = new FrameMenu("Frame Menu");
		
		frameMenu.setVisible(true);
		
		*/
		//**********************************************

	}
}
