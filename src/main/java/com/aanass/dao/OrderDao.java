package com.aanass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aanass.connection.DBConnection;
import com.aanass.model.Order;
import com.aanass.model.Product;

public class OrderDao {
	
	public static boolean insertOrder(Order model) throws ClassNotFoundException {
		boolean result = false;
		Connection con = DBConnection.getConnection();
		String query = "INSERT INTO orders (p_id, u_id, o_quantity, o_date) values(?, ?, ?, ?);";
		
		try(PreparedStatement preparedStatement = con.prepareStatement(query)) {
			preparedStatement.setInt(1, model.getId());
			preparedStatement.setInt(2, model.getUid());
			preparedStatement.setInt(3, model.getQunatity());
			preparedStatement.setString(4, model.getDate());
			preparedStatement.executeUpdate();
			result = true;
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
		return result;
	}
	
//	public static List<Order> userOrder(int id) throws ClassNotFoundException{
//        List<Order> list = new ArrayList<>();
//        Connection con = DBConnection.getConnection();
//        String query = "SELECT * FROM orders WHERE u_id=? ORDER BY orders.u_id desc;";
//        try(PreparedStatement preparedStatement = con.prepareStatement(query)){
//        	preparedStatement.setInt(1, id);
//        	ResultSet rs = preparedStatement.executeQuery();
//        	while(rs.next()) {
//                Order order = new Order();
////                ProductDao productDao = new ProductDao(this.con);
//                int pId = rs.getInt("p_id");
////                Product product = productDao.getSingleProduct(pId);
////                order.setOrderId(rs.getInt("o_id"));
////                order.setId(pId);
////                order.setName(product.getName());
////                order.setCategory(product.getCategory());
////                order.setPrice(product.getPrice()*rs.getInt("o_quantity"));
////                order.setQunatity(rs.getInt("o_quantity"));
////                order.setDate(rs.getString("o_date"));
////                list.add(order);
//        	}
//        	
//        }catch (SQLException se) {
//        	se.printStackTrace();
//			// TODO: handle exception
//		}finally {
//			try {
//				con.close();
//			} catch (SQLException se) {
//				se.printStackTrace();
//				// TODO: handle exception
//			}
//		}
//        return list;
//	}
}
