package com.aja.productprint.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.aja.productprint.model.dto.Product;
import static com.aja.common.JDBCTemplate.*;



public class ProductListDao {

	private Properties sql = new Properties();
	{
		String path = ProductListDao.class.getResource("/sql/product/productprint.properties").getPath();
		try (FileReader fr = new FileReader(path)) {	
			sql.load(fr);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	//select * from product
	public List<Product> selectAllProduct(Connection conn, int cateKey ,int cPage, int numPerpage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> result = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectAllProduct"));
			pstmt.setInt(1, cateKey);
			pstmt.setInt(2, (cPage-1)*numPerpage+1);
			pstmt.setInt(3, cPage*numPerpage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result.add(getProduct(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
//			System.out.println(result);
//			System.out.println(cateKey);
//			System.out.println((cPage-1)*numPerpage+1);
//			System.out.println(cPage*numPerpage);
		}return result;
	}
	
	
	public int selectAllProductCount(Connection conn, int cateKey) {
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectAllProductCount"));
			pstmt.setInt(1, cateKey);
			rs = pstmt.executeQuery();
			if(rs.next()) result = rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
	
	
	
	
	
	
	
	
	//Product객체
	private Product getProduct(ResultSet rs) throws SQLException{
		return Product.builder()
				.prodKey(rs.getInt("PROD_KEY"))
//				.optionKey(rs.getInt("OPTION_KEY"))
				.cateKey(rs.getInt("CATE_KEY"))
//				.keywordKey(rs.getInt("KEYWORD_KEY"))
				.prodName(rs.getString("PROD_NAME"))
				.prodPrice(rs.getInt("PROD_PRICE"))
				.prodStock(rs.getInt("PROD_STOCK"))
				.prodContent(rs.getString("PROD_CONTENT"))
				.prodDetailCon(rs.getString("PROD_DETAILCON"))
				.prodComponent(rs.getString("PROD_COMPONENT"))
				.prodEnrollDate(rs.getDate("PROD_ENROLLDATE"))
				.prodDeleted(rs.getBoolean("PROD_DELETED"))
				.keywordName(rs.getString("KEYWORD_NAME"))
				.cateName(rs.getString("CATE_NAME"))
				.optionFlavor(rs.getString("OPTION_FLAVOR"))
				.optionSize(rs.getString("OPTION_SIZE"))
				.optionPrice(rs.getInt("OPTION_PRICE"))
				.build();
	}
	
	
	
	
	
	
	
	
	
	
}