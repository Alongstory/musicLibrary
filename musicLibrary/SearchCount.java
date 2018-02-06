package musicLibrary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLWarning;

public class SearchCount {	
	
	public void InsertSearchCount(int MusicId, int ClientId, Connection con) throws Exception{
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
			else{
				System.out.println("Insert SearchCount success.");   //调试完之后这句可以注释掉
			}
			
		}
		catch(SQLException e){
			e.printStackTrace();	
		}
		finally {
			ps.close();
		}
		
	}
	
	
	
	
	
	public void UpdateSearchCount(int MusicId, int ClientId, Connection con) throws Exception{
		PreparedStatement ps1 = con.prepareStatement("update searchCount set Count = Count + 1 where search = ? and searchedBy = ?");
		SQLWarning warning = ps1.getWarnings();
		while(warning != null)
			System.err.println("Database Warning:" + warning);		
		try{
			ps1.setInt(1,MusicId);
			ps1.setInt(1,ClientId);
			int updateCount = ps1.executeUpdate();
			SQLWarning queryWarning = ps1.getWarnings();
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
			ps1.close();
		}
	}
	

}
