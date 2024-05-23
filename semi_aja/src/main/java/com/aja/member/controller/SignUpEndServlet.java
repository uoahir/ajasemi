package com.aja.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aja.member.model.dto.Customer;
import com.aja.member.service.MemberService;

/**
 * Servlet implementation class SignUpEndServlet
 */
@WebServlet("/member/signupend.do")
public class SignUpEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
//    public class Member {
//    	private int custKey;
//    	private String custEmail;
//    	private String custPw;
//    	private String custNickname;
//    	private String custPhone;
//    	private String custGender;
//    	private String custBirth;
//    	private String custAddress;
//    	private String custDetailAddress;
//    	private String custDelete;
//    	private Date custEnrollDate;
//    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 다,, input태그로 받아올 거니까.. 파라미터로 가저오기 .. 
		String custEmail = request.getParameter("custEmail");
		String custPw = request.getParameter("custPw");
		String custNickname = request.getParameter("custNickname");
		String custPhone = request.getParameter("custPhone");
		String custGender = request.getParameter("custGender");
		String custBirth = request.getParameter("custBirth");
		String custAddress = request.getParameter("custAddress");
		String custDetailAdress = request.getParameter("custDetailAddress");
		String custName = request.getParameter("custName");
		// custDelete -> 탈퇴한회원 X로 표시
		// custEnrollDate -> sysdate 로 insert
		
		Customer ct = new Customer().builder()
					.custEmail(custEmail)
					.custPw(custPw)
					.custNickname(custNickname)
					.custPhone(custPhone)
					.custGender(custGender)
					.custBirth(custBirth)
					.custAddress(custAddress)
					.custDetailAddress(custDetailAdress)
					.custName(custName)
					.build();
		
		new MemberService().signUp(ct);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
