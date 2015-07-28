package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtil 
{
	static
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch (ClassNotFoundException e)
		{
			
			e.printStackTrace();
		}
		
		
	}
	
	public static Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "123");
	}
	
	public static void  closeAll(ResultSet rs,Statement st , Connection con)
	{
		try
		{
			if(rs!=null)
			{
				rs.close();
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		try
		{
			if(st!=null)
			{
				st.close();
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		try
		{
			if(con!=null)
			{
				con.close();
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
}
