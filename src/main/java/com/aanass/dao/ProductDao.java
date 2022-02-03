package com.aanass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aanass.connection.DBConnection;
import com.aanass.model.Cart;
import com.aanass.model.Product;

public class ProductDao {
	
	public static List<Product> getAllProducts() throws ClassNotFoundException{
		List<Product> products = new ArrayList<Product>();
		Connection con = DBConnection.getConnection();
		String query="SELECT * FROM products;";
		try (PreparedStatement preparedStatement = con.prepareStatement(query)){
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Product product = new Product(rs.getInt("id"), rs.getString("name"), rs.getString("category"), rs.getDouble("price"), rs.getString("image"));
//				product.setId(rs.getInt("id"));
//				product.setName(rs.getString("name"));
//				product.setCategory(rs.getString("category"));
//				product.setPrice( rs.getString("price"));
//				product.setImage(query);
				products.add(product);
			}
			
		} catch (SQLException se) {
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
		return products;
	}
	
	public static List<Cart> getCartProducts(ArrayList<Cart> cartList) throws ClassNotFoundException, SQLException{
		List<Cart> products = new ArrayList<>();
		Connection con = DBConnection.getConnection();
		String query ="SELECT * FROM products WHERE id=?;";
		if(con==null) {
			return null;
		}
		try(PreparedStatement preparedStatement= con.prepareStatement(query)){
			if(cartList.size()>0) {
				for(Cart item:cartList) {
					preparedStatement.setInt(1, item.getId());
					ResultSet rs = preparedStatement.executeQuery();
					while(rs.next()) {
						Cart row= new Cart();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setCategory(rs.getString("category"));
						row.setPrice(rs.getDouble("price")*item.getQuantity());
						row.setQuantity(item.getQuantity());
						products.add(row);
					}
				}
			}
		}catch (SQLException se) {
			se.printStackTrace();
			// TODO: handle exception
		}
		
		return products;
	}
	
	public static double getTotalCartPrice(ArrayList<Cart> cartList) throws ClassNotFoundException {
		double sum = 0.0;
		Connection con = DBConnection.getConnection();
		String query ="SELECT price FROM products WHERE id=?;";
		if(con==null) {
			return 0;
		}
		try(PreparedStatement preparedStatement = con.prepareStatement(query)){
			if(cartList.size()>0) {
				for(Cart item:cartList) {
					preparedStatement.setInt(1,item.getId());
					ResultSet rs = preparedStatement.executeQuery();
					while(rs.next()) {
						sum+=rs.getDouble("price")*item.getQuantity();	
					}
					
				}
			}
			 

		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException se) {
				se.printStackTrace();
				// TODO: handle exception
			}
		}
		return sum;
	}
	
}
