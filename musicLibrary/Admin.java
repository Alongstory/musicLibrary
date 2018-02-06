package musicLibrary;
import java.sql.*;
import java.util.*;

public class Admin{
	
	
	private int id;
	private String name;
	private String password;
	private List<Music> aMusic = new ArrayList<Music>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
