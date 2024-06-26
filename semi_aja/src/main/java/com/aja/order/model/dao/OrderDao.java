package com.aja.order.model.dao;

import static com.aja.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.aja.order.model.dto.CustomerOrder;
import com.aja.order.model.dto.OrderDetail;
import com.aja.order.model.dto.OrderRefund;
import com.aja.order.model.dto.OrderStock;

public class OrderDao {
	Properties sql = new Properties();
	{
		String path = OrderDao.class.getResource("/sql/order/sql.properties").getPath();
		try(FileReader fr = new FileReader(path)){
			sql.load(fr);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public int updateDeliveryStatus(Connection conn,int orderKey) {
		PreparedStatement pstmt = null;
		int result = 0;
		String deliveryStatus = "배송중";
		try {
			pstmt = conn.prepareStatement(sql.getProperty("updateDeliveryStatus"));
			pstmt.setString(1, deliveryStatus);
			pstmt.setInt(2, orderKey);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	public List<CustomerOrder> selectOrderAll(Connection conn,int cPage,int numPerpage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CustomerOrder> orderList = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectOrderAll"));
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				orderList.add(getOrder(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return orderList;
		
	}
	public List<CustomerOrder> selectOrderAll(Connection conn,String type){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CustomerOrder> orderList = new ArrayList<>();
		try {
			if(type.equals("전체")) {
				pstmt= conn.prepareStatement(sql.getProperty("selectOrderStatusAll"));
			}
			else {
				pstmt = conn.prepareStatement(sql.getProperty("selectOrderStatusTypeAll"));
				pstmt.setString(1, type);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				orderList.add(getOrder(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}	
		return orderList;
	}
	public List<CustomerOrder> selectOrderAll(Connection conn){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CustomerOrder> orderList = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectOrderStatusAll"));
			rs = pstmt.executeQuery();
			while(rs.next()) {
				orderList.add(getOrder(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return orderList;
	}
	public List<CustomerOrder> selectOrderAll1(Connection conn, String status){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CustomerOrder> orderList = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectOrderDeliveryAll"));
			pstmt.setString(1, status);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				orderList.add(getOrder(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return orderList;
	}
	public List<OrderDetail> selectOrderDetailAll(Connection conn,int orderKey){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<OrderDetail> orderDetailList = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectOrderDetailAll"));
			pstmt.setInt(1, orderKey);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				orderDetailList.add(getOrderDetail(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return orderDetailList;
	}
	public int updateDelivery(Connection conn,String type,int[] realOrders) {
		PreparedStatement pstmt = null;
		int result =0;
		String order="";
		try {
			String query = sql.getProperty("updateDelivery");
			
			for(int i=0;i<realOrders.length;i++) {
				if(i==realOrders.length-1)
					order += realOrders[i];
				else {
					order += realOrders[i]+",";
				}
			}
			query = query.replace("order", order);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, type);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int deliveryComplete(Connection conn,int orderKey) {
		PreparedStatement pstmt = null;
		int result = 0;
		String complete = "배송완료";
		try {
			pstmt=conn.prepareStatement(sql.getProperty("deliveryComplete"));
			pstmt.setString(1, complete);
			pstmt.setInt(2, orderKey);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int selectOrderCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectOrderCount"));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public int selectOrderCount(Connection conn,String status) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectOrderDeliveryCount"));
			pstmt.setString(1, status);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public OrderRefund orderRefundInfo(Connection conn, int orderKey){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderRefund or = null;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("orderRefundInfo"));
			pstmt.setInt(1, orderKey);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				or = getOrderRefund(rs);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return or;
	}
	public List orderDetailList(Connection conn, int orderKey) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Integer> orderDetailList = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("orderDetailList"));
			pstmt.setInt(1,orderKey);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				orderDetailList.add(rs.getInt("OD_KEY"));
			} 
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return orderDetailList;
	}
	public int refundPoint(Connection conn,int orderPoint,int custKey) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("refundPoint"));
			pstmt.setInt(1, custKey);
			pstmt.setInt(2, orderPoint);
			result = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql.getProperty("updatePoint"));
			pstmt.setInt(1, orderPoint);
			pstmt.setInt(2, custKey);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}
	public int refundCoupon(Connection conn,int dcKey) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("refundCoupon"));
			pstmt.setInt(1, dcKey);
			result = pstmt.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	public int orderStatusUpdate(Connection conn, int osKey) {
		PreparedStatement pstmt = null;
		int result = 0;
		String refund = "환불완료";
		try {
			pstmt = conn.prepareStatement(sql.getProperty("orderStatusUpdate"));
			pstmt.setString(1, refund);
			pstmt.setInt(2, osKey);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public OrderStock orderStockRollback(Connection conn, int odKey) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderStock os = new OrderStock();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("orderStockRollback"));
			pstmt.setInt(1, odKey);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				os.setProdKey(rs.getInt("PROD_KEY"));
				os.setQuantity(rs.getInt("OD_QUANTITY"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return os;
	}
	public int orderStockUpdate(Connection conn,int prodKey,int quantity) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("orderStockUpdate"));
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, prodKey);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	private OrderRefund getOrderRefund(ResultSet rs)throws SQLException{
		return OrderRefund.builder()
				.dcKey(rs.getInt("DC_KEY"))
				.custKey(rs.getInt("CUST_KEY"))
				.orderPoint(rs.getInt("ORDER_POINT"))
				.osKey(rs.getInt("OS_KEY"))
				.build();
	}
	private CustomerOrder getOrder(ResultSet rs) throws SQLException{
		return CustomerOrder.builder()
				.orderKey(rs.getInt("ORDER_KEY"))
				.custKey(rs.getInt("CUST_KEY"))
				.custName(rs.getString("CUST_NAME"))
				.orderPrice(rs.getInt("ORDER_PRICE"))
				.orderDate(rs.getDate("ORDER_DATE"))
				.orderDelivery(rs.getString("ORDER_DELIVERY"))
				.orderStatus(rs.getString("ORDER_STATUS"))
				.build();
	}
	private OrderDetail getOrderDetail(ResultSet rs) throws SQLException{
		return OrderDetail.builder()
				.odKey(rs.getInt("OD_KEY"))
				.optionFlavor(rs.getString("OPTION_FLAVOR"))
				.prodName(rs.getString("PROD_NAME"))
				.odPrice(rs.getInt("OD_PRICE"))
				.odQuantity(rs.getInt("OD_QUANTITY"))
				.build();
	}
}	
