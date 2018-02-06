package musicLibrary;
import java.sql.*;
import java.util.*;

public class musicLib {
	private static Connection con;
	//getter and setter of con
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		musicLib.con = con;
	}
	public musicLib(String driver, String url, String user, String password) throws Exception {
		try {
		    Class.forName(driver);
		    con = DriverManager.getConnection(url, user, password);
		    if(!con.isClosed())
		    	System.out.println("succeeded connecting to the Database!");
		    this.setCon(con);
		}catch (SQLException e1) {
				e1.printStackTrace();
				throw new Exception("CONNECTION ERROR!");
			}
		}
	
	public static void main(String[] args) throws Exception{
	//connect the database
	String driver = "com.mysql.jdbc.Driver";
	//String driver = "org.gjt.mm.mysql.Driver";
	String url = "jdbc:mysql://localhost:3306/musicrecommendationsystem";
	String user = "root";
	String password = "information";
	musicLib logInDB = new musicLib(driver, url, user, password);
	//to be modified
	
	
	//define some variables
	Scanner scan = new Scanner(System.in);
	//login and register status
	String logOrReg;
	
	//username and password
	String UandP;
	String[] splitUandP;
	
	//
	String cmd;
	String[] splitCmd;
	
	int userType = 0; //1 is admin, 2 is client
	
	//Log in loop
	while(true){
		User newUser = new User();
		System.out.println("If you already have an account, please type 'login', if you didn't have one, please type 'register'.");
		if(scan.hasNextLine()){
			logOrReg = scan.nextLine();
			if (logOrReg.equals("login")){
				//Log in process
				System.out.println("Please enter your username, password, and your status('admin' or 'client'), seperate them by whitespace");
				if(scan.hasNextLine()){
					//the first element of splitUandP is username, second is password
					UandP = scan.nextLine();
					splitUandP = UandP.split(" ");
					
					//TODO input check should be insert here
					
					
					
					//check the username, password and status of user
					if (splitUandP[2].equals("admin")){
						//check the UandP for admin
						try {
							if (newUser.adminLogIn(splitUandP[0], splitUandP[1], con) == true){
								userType = 1;
								break;
							}
						} catch (Exception e) {
							e.printStackTrace();
							continue;
						};
					}
					else if (splitUandP[2].equals("client")){
						//check the UandP for client
						try {
							if (newUser.clientLogIn(splitUandP[0], splitUandP[1], con) == true){
								userType = 2;
								break;
							}
						} catch (Exception e) {
							e.printStackTrace();
							continue;
						};
					}
					else{
						System.out.println("Input error!");
						continue;
					}
					
					//clear the string array
					splitUandP = null;
				}
			}
			//TODO the register part
			else if (logOrReg.equals("register")){
				System.out.println("Please enter your username, password");
				if(scan.hasNextLine()){
					UandP = scan.nextLine();
					splitUandP = UandP.split(" ");
					try{
						newUser.SignUp(splitUandP[0], splitUandP[1],con);
					}catch (Exception e){
						e.printStackTrace();
						continue;
					}
						
				}
				
			}
			
			
			else{
				continue;
			}
		}
		
		
	}
	
	System.out.println("You have already Logged in!");
	
	
	String mField = null;
//------query loop-----
	
	switch (userType){
	case 0: 
		System.out.println("user type is undefined");
		break;
	case 1:
		while(true){
			Admin newAdmin = new Admin();
			System.out.println("please input your");
			if(scan.hasNext()){
				//get the command
				cmd = scan.next();
				//judge the command and save them as an string array
				
				
				System.out.println("The input is:"+ cmd);
				
				//clear the string array
				cmd = null;
			}
		}
	case 2:
		while(true){
			Client newClient = new Client();
			//newClient.setName(splitUandP[0]);
			System.out.println("Please input your commend: search, recommend or exit");
			if(scan.hasNextLine()){
				//get the command
				cmd = scan.nextLine();
				//judge the command and save them as an string array
				if (cmd.equals("search")){
					System.out.println("1.name");
					System.out.println("2.singer");
					System.out.println("3.writer");
					System.out.println("4.time");
					System.out.println("5.style");
					System.out.println("please choose a tag(number) you want to search by:");
					if(scan.hasNextLine()){
					cmd = scan.nextLine();
					}
					switch (cmd){
					case "1":
						System.out.println("please input the name");
						if(scan.hasNextLine()){
							mField = scan.nextLine();
						}						
						newClient.searchMusic(mField, "1", con);
						System.out.println("step finished");
						break;
						
					case "2":
						System.out.println("please input the singer");
						if(scan.hasNextLine()){
							mField = scan.nextLine();
						}
						newClient.searchMusic(mField, cmd, con);
						break;
					case "3":
						System.out.println("please input the writer");
						if(scan.hasNextLine()){
							mField = scan.nextLine();
						}
						newClient.searchMusic(mField, cmd, con);
						break;
						
					case "4":
						System.out.println("please input the time");
						if(scan.hasNextLine()){
							mField = scan.nextLine();
						}
						newClient.searchMusic(mField, cmd, con);
						break;
						
					case "5":
						System.out.println("please input the style");
						if(scan.hasNextLine()){
							mField = scan.nextLine();
						}
						newClient.searchMusic(mField, cmd, con);
						break;
						
					case "":
						
						
					}
					
				}
				else if (cmd.equals("recommend")){
					
					
				}
				else if (cmd.equals("exit")){
					
					
				}
				else{
					System.out.println("please input valid instruction like 'search' or recommend");
					continue;
				}
				
				System.out.println("The input is:" + cmd);

				//clear the string array
				cmd = null;
			}
		}
	
	}
	
	
	
	
	}
	
	
	
}
