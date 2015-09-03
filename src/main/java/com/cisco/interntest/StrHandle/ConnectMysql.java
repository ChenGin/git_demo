package com.cisco.interntest.StrHandle;
import java.sql.*;
import java.util.List;
public class ConnectMysql {
	private String driver="com.mysql.jdbc.Driver";
	private String url="jdbc:mysql://127.0.0.1:3306/mysql";
	private String user="root";
	private String password="1234";
	private static Connection Con=null;
	private static ConnectMysql mysql=null;
	 ConnectMysql()
	{
		this.getCon();
	}
	public static ConnectMysql getmysql()
	{
		if(mysql==null)
		{
			return new ConnectMysql();
		}
		else
			return mysql;
	}
	public void getCon()
	{
		if(Con==null)
		{
			try
			{
				Class.forName(driver);
				 Con=DriverManager.getConnection(url,user,password);
				if(!Con.isClosed())
					System.out.println("Success!");
				Statement statement=Con.createStatement();
			}
			catch(Exception e)
			{
				System.out.println("The Connection is error!!!"+e.getMessage());
			}
		}
	}
	public String insertdata(List<Data> data) throws SQLException
	{		
		String sql = "insert into ciscotest(Router,Type,Node,Np,Value,Export_time) values (?, ?, ?, ?, ? ,?)";
		PreparedStatement ps = Con.prepareStatement(sql);
		final int batchSize =100;
		int count = 0;
		for (Data d: data) {
		    ps.setString(1, d.getRouter());
		    ps.setString(2, d.getType());
		    ps.setString(3, d.getNode());
		    ps.setString(4, d.getNp());
		    ps.setString(5, d.getValue());
		    ps.setString(6, d.getExport_time());
		    System.out.println(d.getExport_time());
		    ps.addBatch();
		    if(++count % batchSize == 0) {
		        ps.executeBatch();
		    }
		}
		
		ps.executeBatch(); // insert remaining records
		ps.close();
		//connection.close();
		return "updata success";
	}
	public void StopCon() throws SQLException
	{
		this.Con.close();
	}
}
