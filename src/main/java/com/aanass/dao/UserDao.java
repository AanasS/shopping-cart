package com.aanass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aanass.connection.DBConnection;
import com.aanass.model.User;

public class UserDao {
	
	public static User userLogin(String email,String password) throws ClassNotFoundException {
		User user = null;
	    Connection con = DBConnection.getConnection();
	    if(con == null ) {
	    	return null;
	    }
	    String query = "Select * FROM users WHERE email=? and password=?";
	    try(PreparedStatement preparedStatement = con.prepareStatement(query)){
	    	preparedStatement.setString(1, email);
	    	preparedStatement.setString(2, password);
	    	ResultSet rs= preparedStatement.executeQuery();
	    	while(rs.next()) {
	    		user=new User();
	    		user.setId(rs.getInt("id"));
	    		user.setName(rs.getString("name"));
	    		user.setEmail(rs.getString("email"));
	    		
	    	}
	    	
	    }catch (SQLException se) {
	    	se.printStackTrace();
			// TODO: handle exception
		}finally {
			try {
				con.close();
			} catch (SQLException se) {
				se.printStackTrace();
				// TODO: handle exception
			}
		}
		return user;
	}
}
