package com.cisco.interntest.StrHandle;
import java.sql.*;
public class PrintTest {
	public PrintTest()
	{
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://127.0.0.1:3306/mysql";
		String user="root";
		String password="1234";
		try
		{
			Class.forName(driver);
			Connection conn=DriverManager.getConnection(url,user,password);
			if(!conn.isClosed())
				System.out.println("Success!");
			Statement statement=conn.createStatement();
		}
		catch(Exception e)
		{
			System.out.println("The Connection is error!!!"+e.getMessage());
		}
		System.out.println("cisco test!!!");
	}
}
