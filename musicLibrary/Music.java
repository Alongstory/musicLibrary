package musicLibrary;
import java.sql.*;
import java.util.*;


public class Music
{
	private int id;

	public String toString() {
		return "[ Song Title:" + name + ", Artist:" + singer + ", Writer:" + writer + ", Time:" + time + ", style:"
				+ style + ", lyrics=" + lyrics + "]";
	}
	private String name;
	private String singer;
	private String writer;
	private String time;
	private String style;
	private String lyrics;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getLyrics() {
		return lyrics;
	}
	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}
	

	
	
	
	
	/*
	private String name;
	private String year;
	private String writer;
	private String singer;
	private String style;
	private String lyrics;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getLyrics() {
		return lyrics;
	}
	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}	
	
	@Override
	public String toString() {
		return "Music [name=" + name + ", year=" + year + ", writer=" + writer + ", singer=" + singer + ", style="
				+ style + ", lyrics=" + lyrics + "]";
	}
*/
	
	public int getId(Connection con) throws Exception {
		PreparedStatement ps = con.prepareStatement("select id from Music where name = ?)");
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
	
	public void setId(int id) {
		this.id = id;
	}

}
