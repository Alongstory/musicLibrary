package musicLibrary;
import java.sql.*;
import java.util.*;


public class User {
	private int id;
	private String name;
	private String password;
	
	private List<Client> Client = new ArrayList<Client>();
	private List<Admin> Admin = new ArrayList<Admin>();

	
	//check username and password for client
	public boolean clientLogIn(String name, String password, Connection con) throws Exception{
		boolean state = false;
		PreparedStatement ps = con.prepareStatement("select password from Client where name = ?");
		SQLWarning warning = ps.getWarnings();
		while(warning != null)
			System.err.println("Database Warning:" + warning);		
		try{
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			SQLWarning queryWarning = ps.getWarnings();
			while(queryWarning != null)
				System.err.println("Query Warning " + queryWarning);
			while(rs.next())
			{
				if (rs.getString(1).equals(password)){
					System.out.println("Welcome back " + name);
					state = true;
				}
				else{
					System.out.println("Sorry, your password is incorrect.");
				}
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new Exception("Sorry, there is no user name as :" + name);		
		}
		finally {
			ps.close();
		}
		return state;
	}
	
	//check username and password for admin
	public boolean adminLogIn(String name, String password, Connection con) throws Exception{
		boolean state = false;
		PreparedStatement ps = con.prepareStatement("select password from Admin where name = ?");
		SQLWarning warning = ps.getWarnings();
		while(warning != null)
			System.err.println("Database Warning:" + warning);		
		try{
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			SQLWarning queryWarning = ps.getWarnings();
			while(queryWarning != null)
				System.err.println("Query Warning " + queryWarning);
			while(rs.next())
			{
				if (rs.getString(1).equals(password)){
					System.out.println("Welcome back " + name);
					state = true;
				}
				else{
					System.out.println("Sorry, your password is incorrect.");
				}
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new Exception("Sorry, there is no user name as :" + name);		
		}
		finally {
			ps.close();
		}
		return state;
	}
	
	//register process
	public void SignUp(String name, String password, Connection con) throws Exception{
		PreparedStatement ps = con.prepareStatement("insert into Client(name, password) select ?,? from dual where not exists (select * from Client where name = ?)");
		SQLWarning warning = ps.getWarnings();
		while(warning != null)
			System.err.println("Database Warning:" + warning);		
		try{
			ps.setString(1,name);
			ps.setString(2,password);
			ps.setString(3,name);
			int updateCount = ps.executeUpdate();
			SQLWarning queryWarning = ps.getWarnings();
			while(queryWarning != null)
				System.err.println("Query Warning " + queryWarning);
			if(updateCount != 1)
			{
				throw new Exception("Sorry, the name '" + name + "' has already been used.\n");	
			}
			else{
				System.out.println("your account has been successfully registered!");
			}
		}		
		catch(SQLException e){
			e.printStackTrace();	
		}
		finally {
			ps.close();
		}
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public List<Client> getClient() {
		return Client;
	}
	public void setClient(List<Client> client) {
		Client = client;
	}

	public List<Admin> getAdmin() {
		return Admin;
	}
	public void setAdmin(List<Admin> admin) {
		Admin = admin;
	}
	
	
	

}
