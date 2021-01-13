package com.alpha.prts;

import java.sql.*;

public class Conn{
	static Connection con;
	public static Connection makeConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql:///prts","root","");
		}
		catch(Exception e){
			System.out.println("Exception in getConnection of Conn class"+e);
		}	
		return con;
	}		
}