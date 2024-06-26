<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<html>
<head>
    <meta charset="UTF-8">
    <title>admin</title>
<style>
	    /* 헤더전체 */
	  #admin_header {
	    height: 80px;
	    width: 100%;
	    display: flex;
	    align-items: center;
	    justify-content: space-between;
	}
	
	/* 헤더 로고 div */
	#admin_header_logo {
	    display: flex;
	    align-items: center;
	    margin-left: 1%;
	}
	
	/* 헤더 타이틀 */
	#admin_header_title {
	    display: flex;
	    align-items: center;
	    justify-content: center;
	    font-size: 30px;
	    flex-grow: 1;
	    text-align: center;
	}
	
	/* 미들 전체 */
	#admin_middle {
	    display: flex;
	}
	
	/* 미들 카테고리(왼쪽) */
	#admin_middle_category {
	    width: 11%;
	}
	
	/* 미들 내용물 */
	#admin_middle_content {
	    width: 120%;
	}
	
	/* 미들 카테고리 리스트전체 */
	.list {
	    list-style-type: none;
	    display: flex;
	    flex-direction: column;
	    justify-content: center;
	}
	
	/* 미들 카테고리 a태그 */
	.list a {
	    color: black;
	    text-decoration-line: none;
	}
	
	/* 미들 카테고리 p태그 - 줄 바꿈 방지 */
	.list p {
	    white-space: nowrap;
	}
</style>
</head>
<body>
    <div> <!-- 전체 -->
        <div  id="admin_header"> <!-- 상단 바 -->
            <div id="admin_header_logo">
            <a href="<%=request.getContextPath()%>">
                <img src="<%=request.getContextPath() %>/images/logo(apricot).png" alt="logo" width="150" height="50">
            </a>
            </div>
            <div id="admin_header_title">
                <p>관리자페이지</p>
            </div>
        </div>
       
        <div id="admin_middle"> <!-- 중간 카테고리 내용부분 -->
            <div id="admin_middle_category">  <!-- 왼쪽 카테고리 -->
                <div>  <!-- 상품 -->
                    <ul class="list">
                        <h2>상품</h2>
                        <li><a href="<%=request.getContextPath()%>/product/categorylist.do"><p>키워드/카테고리등록</p></a></li>
                        <li><a href="<%=request.getContextPath() %>/product/optionlist.do"><p>옵션관리</p></a></li>
                        <li><a href="<%=request.getContextPath()%>/product/productlist.do"><p>상품관리</p></a></li>
                    </ul>
                </div>

                <div>  <!-- 배송/주문 -->
                    <ul class="list">
                        <h2>배송/주문</h2>
                        <li><a href="<%=request.getContextPath()%>/order/orderlist.do"><p>배송/주문관리</p></a></li>
                    </ul>
                </div>

                <div>  <!-- CS/혜택 -->
                    <ul class="list">
                        <h2>CS/혜택</h2>
                        <li><a href="<%=request.getContextPath()%>/coupon/couponlist.do"><p>쿠폰관리</p></a></li>
                        <li><a href=""><p>1 : 1 답변관리(아직..)</p></a></li>
                        <li><a href=""><p>Q&A 관리(나중에..)</p></a></li>
                        <li><a href=""><p>공지사항관리(천천히..)</p></a></li>
                    </ul>
                </div>
            </div>