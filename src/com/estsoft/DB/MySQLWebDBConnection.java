package com.estsoft.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLWebDBConnection implements DBConnection {
@SuppressWarnings("finally")
public Connection getConnection() throws SQLException{
		Connection conn = null;	
		try{
			
		Class.forName( "com.mysql.jdbc.Driver" );

		//2. Connection ���
		String url = "jdbc:mysql://localhost/webdb";
		conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException ex) {
			System.out.println( "드라이버를 찾을 수 없습니다:" + ex );
		}finally{
		}
		return conn;
	}
}
