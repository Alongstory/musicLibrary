package musicLibrary;
import java.sql.*;
import java.util.*;


public class Client{
	private int id;
	private String name;
	private String password;
	private List<Music> cMusic = new ArrayList<Music>();

	public List<Music> getcMusic() {
		return cMusic;
	}
	public void setcMusic(List<Music> cMusic) {
		this.cMusic = cMusic;
	}
		
	public int getId(Connection con) throws Exception {
		PreparedStatement ps = con.prepareStatement("select id from Client where name = ?");
		SQLWarning warning = ps.getWarnings();
		while(warning != null)
			System.err.println("Database Warning:" + warning);		
		try{
			ps.setString(1,name);
			ResultSet rs = ps.executeQuery();
			SQLWarning queryWarning = ps.getWarnings();
			while(queryWarning != null)
				System.err.println("Query Warning " + queryWarning);
			while(rs.next())
			{
				this.setId(rs.getInt(1));
			}
		}		
		catch(SQLException e){
			e.printStackTrace();	
		}
		finally {
			ps.close();
		}
		return id;
	}
	
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
	
	
	
	public List<Music> searchMusic(String field, String type, Connection con) throws Exception{
		int[] idIndex = new int[100];
		int musicCount = 0;
		
		PreparedStatement ps = con.prepareStatement("select * from Music where name like ?");
		
		switch(type) 
		{	case "1":
				ps = con.prepareStatement("select * from Music where name like ?");
				break;
			case "2":
				ps = con.prepareStatement("select * from Music where singer like ?");
				break;
			case "3":
				ps = con.prepareStatement("select * from Music where writer like ?");
				break;
			case "4":
				ps = con.prepareStatement("select * from Music where time like ?");
				break;
			case "5":
				ps = con.prepareStatement(" select * from Music where style like ?");
			
		}
		
		SQLWarning warning = ps.getWarnings();
		while(warning != null)
			System.err.println("Database Warning:" + warning);		
		try{
			ps.setString(1,"%"+field+"%");
			ResultSet rs = ps.executeQuery();
			SQLWarning queryWarning = ps.getWarnings();
			while(queryWarning != null)
				System.err.println("Query Warning " + queryWarning);
			
			while(rs.next())
			{
				idIndex[musicCount] = rs.getInt(1);
				Music mus = new Music();
				mus.setName(rs.getString(2));
				mus.setSinger(rs.getString(3));
				mus.setWriter(rs.getString(4));
				mus.setTime(rs.getString(5));
				mus.setStyle(rs.getString(6));
				mus.setLyrics(rs.getString(7));
				this.cMusic.add(mus);
				System.out.println(mus.toString());
				musicCount = musicCount + 1;
			}
			if(musicCount == 0){
				throw new Exception("No music has field:" + field);		
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			ps.close();
		}
		
		for (int i = 0;i<musicCount;i++)
		{
			UpdateCount(idIndex[i], this.getId(con), con);					
		}
		
		return this.cMusic;	
		
	}

	
	public void UpdateCount(int MusicId, int ClientId, Connection con) throws Exception{
		PreparedStatement ps = con.prepareStatement("insert into searchCount(search, searchedBy, count) select ?,?,1 from dual where not exists (select * from searchCount where search = ? and searchedBy = ?)");
		SQLWarning warning = ps.getWarnings();
		while(warning != null)
			System.err.println("Database Warning:" + warning);		
		try{
			ps.setInt(1,MusicId);
			ps.setInt(2,ClientId);
			ps.setInt(3,MusicId);
			ps.setInt(4,ClientId);
			int insertCount = ps.executeUpdate();
			SQLWarning queryWarning = ps.getWarnings();
			while(queryWarning != null)
				System.err.println("Query Warning " + queryWarning);
			if(insertCount == 0)
			{
				UpdateSearchCount(MusicId, ClientId, con);	
			}
			/*
			else{
				System.out.println("Insert SearchCount success.");   //调试完之后这句可以注释掉
			}*/
			
		}
		catch(SQLException e){
			e.printStackTrace();	
		}
		finally {
			ps.close();
		}
		
		PreparedStatement ps1 = con.prepareStatement("update Music set count = count + 1 where id = ?");
		SQLWarning updateWarning = ps1.getWarnings();
		while(updateWarning != null)
			System.err.println("Database Warning:" + warning);		
		try{
			ps1.setInt(1,MusicId);
			int updateCount = ps1.executeUpdate();
			SQLWarning queryWarning = ps1.getWarnings();
			while(queryWarning != null)
				System.err.println("Query Warning " + queryWarning);
			if(updateCount != 1)
			{
				throw new Exception("Update MCount error.\n");	
			}
		}
		catch(SQLException e){
			e.printStackTrace();	
		}
		finally {
			ps1.close();
		}
		
		
		
	}
		
		
	public void UpdateSearchCount(int MusicId, int ClientId, Connection con) throws Exception{		
		PreparedStatement ps = con.prepareStatement("update searchCount set Count = Count + 1 where search = ? and searchedBy = ?");
		SQLWarning warning = ps.getWarnings();
		while(warning != null)
			System.err.println("Database Warning:" + warning);		
		try{
			ps.setInt(1,MusicId);
			ps.setInt(2,ClientId);
			int updateCount = ps.executeUpdate();
			SQLWarning queryWarning = ps.getWarnings();
			while(queryWarning != null)
				System.err.println("Query Warning " + queryWarning);
			if(updateCount != 1)
			{
				throw new Exception("Update SearchCount error.\n");	
			}
		}
		catch(SQLException e){
			e.printStackTrace();	
		}
		finally {
			ps.close();
		}
	}
		
		
	public List<Music> RecomMusic(Connection con) throws Exception{
		int[] idIndex = new int[100];
		int musicCount = 0;
		PreparedStatement ps = con.prepareStatement("select * from Music "
				+ "where count =(select max(count) from  Music m "
				+ "where not exists(select * from Searchcount s "
				+ "where m.id = s.search and not exists"
				+ "(select * from Client c where c.id = s.searchedBy and c.name <> ?)))");
		
		SQLWarning warning = ps.getWarnings();
		while(warning != null)
			System.err.println("Database Warning:" + warning);		
		try{
			ps.setString(1,getName());
			ResultSet rs = ps.executeQuery();
			SQLWarning queryWarning = ps.getWarnings();
			while(queryWarning != null)
				System.err.println("Query Warning " + queryWarning);
			while(rs.next())
			{
				idIndex[musicCount] = rs.getInt(1);
				Music mus = new Music();
				mus.setName(rs.getString(2));
				mus.setSinger(rs.getString(3));
				mus.setWriter(rs.getString(4));
				mus.setTime(rs.getString(5));
				mus.setStyle(rs.getString(6));
				mus.setLyrics(rs.getString(7));
				this.cMusic.add(mus);
				System.out.println(mus.toString());
				musicCount = musicCount + 1;
			}
			if(musicCount == 0){
				throw new Exception("You have saerch all the music in the system !");			
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			ps.close();
		}		
	
		return this.cMusic;	
	}	
	
	
	

	
	
	

	
}
