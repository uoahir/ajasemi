package com.aja.productprint.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aja.member.model.dto.Customer;
import com.aja.productprint.model.dto.Product;
import com.aja.productprint.service.ProductListService;

/**
 * Servlet implementation class ProductListPrintServelt
 */
@WebServlet("/product/productlistprint.do")
public class ProductListPrintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductListPrintServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {}
		
		int numPerpage = 6;
		try {
			numPerpage = Integer.parseInt(request.getParameter("numPerpage"));
		}catch(NumberFormatException e) {}
		
		
		int cateKey = Integer.parseInt(request.getParameter("cateKey"));
		
//		System.out.println(cateKey);
		List<Product> result = new ProductListService().selectAllProduct(cateKey,cPage,numPerpage);
//		for(int i=0;i<result.size();i++) {
//			Product p = new ProductListService().selectProductImage(result.get(i).getProdKey());
//			result.get(i).setProdImage1(p.getProdImage1());
//			result.get(i).setProdImage2(p.getProdImage2());
//			result.get(i).setProdImage3(p.getProdImage3());
//			result.get(i).setProdImage4(p.getProdImage4());
//			result.get(i).setProdImage5(p.getProdImage5());
//		};
//		System.out.println(result);
		request.setAttribute("productlist", result);
		
		int totalData = new ProductListService().selectAllProductCount(cateKey);
//		System.out.println(totalData);
		int totalPage = (int)Math.ceil((double)totalData/numPerpage);
		
		request.setAttribute("totalPage", totalPage);
		
		HttpSession session = request.getSession();
		Customer loginMember = (Customer)session.getAttribute("loginMember"); //아이디가 session에 있어서 접근 가능
		
		int custKey = 0;
		
		if(loginMember != null) {
			custKey = loginMember.getCustKey();			
		}
		List<Integer> wishNumber = new ProductListService().selectWishProduct(custKey);
		
		
		request.setAttribute("wishNumber", wishNumber);
				
		
		request.getRequestDispatcher("/WEB-INF/views/product/productList.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
