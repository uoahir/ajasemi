package com.aja.product.model.dao;

import static com.aja.admin.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import com.aja.product.model.dto.Product;
import com.aja.product.model.dto.Product2;
import com.oreilly.servlet.MultipartRequest;

public class ProductDao {
	Properties sql = new Properties();
	{
		String path = ProductDao.class.getResource("/sql/product/sql.properties").getPath();
		try(FileReader fr = new FileReader(path)){
			sql.load(fr);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public int[] enrollProduct(Connection conn,Product p) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int[] result = new int[2];
		try {
			pstmt = conn.prepareStatement(sql.getProperty("enrollProduct"));
			pstmt.setInt(1, p.getOptionKey());
			pstmt.setInt(2,p.getKeywordKey());
			pstmt.setInt(3,p.getCateKey());
			pstmt.setString(4,p.getProdName());
			pstmt.setInt(5, p.getProdPrice());
			pstmt.setInt(6, p.getProdStock());
			pstmt.setString(7, p.getProdContent());
			pstmt.setString(8,p.getProdDetailCon());
			pstmt.setString(9, p.getProdComponent());
			int enrollResult = pstmt.executeUpdate();
			result[0] = enrollResult;
			pstmt = conn.prepareStatement(sql.getProperty("searchProduct"));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int prodKey = rs.getInt("PROD_KEY");
				result[1] = prodKey;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
//		if(result[0] == 1) {
//			try {
//				pstmt = conn.prepareStatement(sql.getProperty("searchProduct"));
//				rs = pstmt.executeQuery();
//				if(rs.next()) {
//					int prodKey = rs.getInt("PROD_KEY");
//					result[1] = prodKey;
//				}
//			}catch(SQLException e) {
//				e.printStackTrace();
//			}
//			finally {
//				close(rs);
//				close(pstmt);
//			}
//		}
//		return result;
	}
	public int selectOption(Connection conn,String flavor,int size) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int optionKey= 0;
		try {
			pstmt= conn.prepareStatement(sql.getProperty("selectOption"));
			pstmt.setString(1, flavor);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				optionKey=rs.getInt("OPTION_KEY");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return optionKey;
	}
	
	public int updateImages(Connection conn,MultipartRequest mr,int prodKey) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql1="";
		int count = 0;
		
		Enumeration files = mr.getFileNames();
		while(files.hasMoreElements()) {
			files.nextElement();
			count++;
		}
		switch(count){
			case 0: return result = 1; 
			case 1: sql1 = "UPDATE PROD_IMAGE SET PROD_IMAGE1 = ?, PROD_IMAGE2 = DEFAULT, PROD_IMAGE3 = DEFAULT, PROD_IMAGE4 = DEFAULT, PROD_IMAGE5 = DEFAULT WHERE PROD_KEY=?";break;
			case 2: sql1 = "UPDATE PROD_IMAGE SET PROD_IMAGE1 = ?, PROD_IMAGE2 = ?, PROD_IMAGE3 = DEFAULT, PROD_IMAGE4 = DEFAULT, PROD_IMAGE5 = DEFAULT WHERE PROD_KEY=?";break;
			case 3: sql1 = "UPDATE PROD_IMAGE SET PROD_IMAGE1 = ?, PROD_IMAGE2 = ?, PROD_IMAGE3 = ?, PROD_IMAGE4 = DEFAULT, PROD_IMAGE5 = DEFUALT WHERE PROD_KEY=?";break;
			case 4: sql1 = "UPDATE PROD_IMAGE SET PROD_IMAGE1 = ?, PROD_IMAGE2 = ?, PROD_IMAGE3 = ?, PROD_IMAGE4 = ?, PROD_IMAGE5 = DEFAULT WHERE PROD_KEY=?";break;
			case 5: sql1 = "UPDATE PROD_IMAGE SET PROD_IMAGE1 = ?, PROD_IMAGE2 = ?, PROD_IMAGE3 = ?, PROD_IMAGE4 = ?, PROD_IAMGE5 =? WHERE PROD_KEY=?";break;
			default : return result;
		}
		try {
			pstmt = conn.prepareStatement(sql1);
			files = mr.getFileNames();
			int index=1;
			while(files.hasMoreElements()) {
				String fileName = (String)files.nextElement();
				String fileSystemName = mr.getFilesystemName(fileName);
				pstmt.setString(index,fileSystemName);
				index++;
			}
			pstmt.setInt(index, prodKey);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int enrollImages(Connection conn,MultipartRequest mr, int prodKey) {
		PreparedStatement pstmt= null;
		int result = 0 ;
		String sql1 = "";
		int count = 0;
		
		Enumeration files = mr.getFileNames();
		while(files.hasMoreElements()) {
			files.nextElement();
			count++;
		}
		switch(count) {
		case 0 : sql1 = "INSERT INTO PROD_IMAGE VALUES(SEQ_PROD_IMAGE.NEXTVAL,?,DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT)"; break;
		case 1 : sql1 = "INSERT INTO PROD_IMAGE VALUES(SEQ_PROD_IMAGE.NEXTVAL,?,?,DEFAULT,DEFAULT,DEFAULT,DEFAULT)"; break;
		case 2 : sql1 = "INSERT INTO PROD_IMAGE VALUES(SEQ_PROD_IMAGE.NEXTVAL,?,?,?,DEFAULT,DEFAULT,DEFAULT)"; break;
		case 3 : sql1 = "INSERT INTO PROD_IMAGE VALUES(SEQ_PROD_IMAGE.NEXTVAL,?,?,?,?,DEFAULT,DEFAULT)"; break;
		case 4 : sql1 = "INSERT INTO PROD_IMAGE VALUES(SEQ_PROD_IMAGE.NEXTVAL,?,?,?,?,?,DEFAULT)"; break;
		case 5 : sql1 = "INSERT INTO PROD_IMAGE VALUES(SEQ_PROD_IMAGE.NEXTVAL,?,?,?,?,?,?)"; break;
		default : return result;
		}
		System.out.println(sql1);
		try {
			pstmt = conn.prepareStatement(sql1);
			System.out.println(prodKey);
			int index =2;
			pstmt.setInt(1, prodKey);
			files = mr.getFileNames();
			while(files.hasMoreElements()) {
				String fileName = (String)files.nextElement();
				String fileSystemName = mr.getFilesystemName(fileName);
				System.out.println(fileSystemName);
				pstmt.setString(index,fileSystemName);
				index++;
			}
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	
	}
	public int selectProductCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectProductCount"));
			rs = pstmt.executeQuery();
			if(rs.next()) result = rs.getInt(1);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public Product2 selectProduct(Connection conn,int prodKey) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Product2 p= null;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectProduct"));
			pstmt.setInt(1, prodKey);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				p = getProductWithImage(rs);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return p;
		
		
	}
	public List<Product2> searchAllProduct(Connection conn,int cPage,int numPerpage){
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		List<Product2> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("searchAllProduct"));
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(getProduct(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	public int deleteProduct(Connection conn,int prodKey) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("deleteProduct"));
			pstmt.setInt(1, prodKey);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int updateProduct(Connection conn,Product p) {
		PreparedStatement pstmt= null;
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("updateProduct"));
			pstmt.setInt(1, p.getOptionKey());
			pstmt.setInt(2, p.getKeywordKey());
			pstmt.setInt(3, p.getCateKey());
			pstmt.setString(4, p.getProdName());
			pstmt.setInt(5,p.getProdPrice());
			pstmt.setInt(6, p.getProdStock());
			pstmt.setString(7, p.getProdContent());
			pstmt.setString(8, p.getProdDetailCon());
			pstmt.setString(9, p.getProdComponent());
			pstmt.setInt(10, p.getProdKey());
			
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	private Product2 getProduct(ResultSet rs) throws SQLException{
		return Product2.builder()
				.prodKey(rs.getInt("PROD_KEY"))
				.prodName(rs.getString("PROD_NAME"))
				.optionFlavor(rs.getString("OPTION_FLAVOR"))
				.optionSize(rs.getInt("OPTION_SIZE"))
				.optionPrice(rs.getInt("OPTION_PRICE"))
				.cateName(rs.getString("CATE_NAME"))
				.keywordName(rs.getString("KEYWORD_NAME"))
				.prodPrice(rs.getInt("PROD_PRICE"))
				.prodStock(rs.getInt("PROD_STOCK"))
				.prodContent(rs.getString("PROD_CONTENT"))
				.prodDetailCon(rs.getString("PROD_DETAILCON"))
				.prodComponent(rs.getString("PROD_COMPONENT"))
				.prodEnrollDate(rs.getDate("PROD_ENROLLDATE"))
				.prodDeleted(rs.getBoolean("PROD_DELETED"))
				.build();
	}
	private Product2 getProductWithImage(ResultSet rs) throws SQLException{
		return Product2.builder()
				.prodKey(rs.getInt("PROD_KEY"))
				.prodName(rs.getString("PROD_NAME"))
				.optionFlavor(rs.getString("OPTION_FLAVOR"))
				.optionSize(rs.getInt("OPTION_SIZE"))
				.optionPrice(rs.getInt("OPTION_PRICE"))
				.cateName(rs.getString("CATE_NAME"))
				.keywordName(rs.getString("KEYWORD_NAME"))
				.prodPrice(rs.getInt("PROD_PRICE"))
				.prodStock(rs.getInt("PROD_STOCK"))
				.prodContent(rs.getString("PROD_CONTENT"))
				.prodImage1(rs.getString("PROD_IMAGE1"))
				.prodImage2(rs.getString("PROD_IMAGE2"))
				.prodImage3(rs.getString("PROD_IMAGE3"))
				.prodImage4(rs.getString("PROD_IMAGE4"))
				.prodImage5(rs.getString("PROD_IMAGE5"))
				.prodDetailCon(rs.getString("PROD_DETAILCON"))
				.prodComponent(rs.getString("PROD_COMPONENT"))
				.prodEnrollDate(rs.getDate("PROD_ENROLLDATE"))
				.prodDeleted(rs.getBoolean("PROD_DELETED"))
				.build();
	}
	
	
}
