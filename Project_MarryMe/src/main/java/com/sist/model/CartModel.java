package com.sist.model;

import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.sist.controller.RequestMapping;
import com.sist.dao.CartDAO;
import java.util.*;
import com.sist.vo.*;

public class CartModel {
	/*
	   *   <input type="hidden" name="gno" value="${vo.no}">
			        <input type="hidden" name="type" value="${type }">
			        <input type="hidden" name="price" value="${vo.price}">
			        <input type="hidden" name="account" value="" id="account">
			데이터베이스 : 컬럼명 
			사용자 
			 = 1. 상세보기 => primary key (no) => detail.do?no=1 
			      서버 처리 
			        => SELECT * FROM table_name
			           WHERE no=1
			   2. 목록 => page 
			       => WHERE num BETWEEN 1 AND 10
			   3. insert 
			       => 데이터 전송 : id,name..... <form>
			   4. update 
			       => 데이터 전송 : id,name..... <form>
			   5. delete : primary key
			 = 검색 : 검색어 
			        WHERE subject LIKE '%?%'
			 = 로그인 : id , pwd 
			 ==================================================
			 Spring 
			 => 50일 
			    Spring / Vue 
			    Spring-Boot / React 
			    Python / Django => React 
			 
	   */
	  @RequestMapping("goods/cart_insert.do")
	  public String cart_insert(HttpServletRequest request,HttpServletResponse response)
	  {
		  String gno=request.getParameter("gno");
			/* String type=request.getParameter("type"); */
		  String price=request.getParameter("price");
		  String account=request.getParameter("account");
		  HttpSession session=request.getSession();
		  String id=(String)session.getAttribute("id");
		  int gPrice=Integer.parseInt(price.replaceAll("[^0-9]", ""));
		  System.out.println("gno="+gno);
		  System.out.println("price="+gPrice);
		  System.out.println("account="+account);
		  // DB연동
		  CartVO vo=new CartVO();
		  vo.setGno(Integer.parseInt(gno));
		  vo.setAccount(Integer.parseInt(account));
		  vo.setPrice(gPrice);
		  vo.setId(id);
		  
		  Map map=new HashMap();
		  map.put("gno", gno);
		  map.put("id", id);
		  int gnoCount=CartDAO.cartGetGnoCount(map);
		  
		  if(gnoCount==0)
		  {
			  CartDAO.cartInsert(vo);
		  }
		  else
		  {
			  int cno=CartDAO.cartGetGno(map);
			  Map map1=new HashMap();
			  map1.put("cart_no", cno);
			  map1.put("account", account);
			  CartDAO.cartGnoUpdate(map1);
		  }
		  
		  System.out.println("장바구니");
		  return "redirect:../mypage/mypage_cart.do";
	  }
	  @RequestMapping("mypage/mypage_cart.do")
	  public String mypage_cart(HttpServletRequest request,HttpServletResponse response)
	  {
		 
		  
		  HttpSession session=request.getSession();
		  String id=(String)session.getAttribute("id");
		  List<CartVO> list=CartDAO.cartListData(id);
		  String fPrice="";
		  for(CartVO vo:list) {
			  fPrice=new DecimalFormat("###,###,###").format(vo.getPrice());
			  vo.setWon(fPrice);
		  }
		  
		  request.setAttribute("cartList", list);
		  request.setAttribute("count", list.size());
		  request.setAttribute("title", "장바구니");
		  request.setAttribute("mypage_jsp", "../mypage/mypage_cart.jsp");
		  request.setAttribute("main_jsp","../mypage/mypage_main.jsp");
		  return "../main/main.jsp";
	  }
	  @RequestMapping("goods/buy_insert.do")
	  public void buy_insert(HttpServletRequest request,HttpServletResponse response)
	  {
		  String gno=request.getParameter("gno");
			/* String type=request.getParameter("type"); */
		  String price=request.getParameter("price");
		  String account=request.getParameter("account");
		  HttpSession session=request.getSession();
		  String id=(String)session.getAttribute("id");
		  System.out.println("gno="+gno);
		  System.out.println("price="+price);
		  System.out.println("account="+account);
		  // DB연동
		  CartVO vo=new CartVO();
		  vo.setGno(Integer.parseInt(gno));
		  vo.setAccount(Integer.parseInt(account));
		  vo.setPrice(Integer.parseInt(price));
		  vo.setId(id);
		  
		  CartDAO.buyInsert(vo);
		  
		  //JSON 
		  String name=(String)session.getAttribute("name");
		  String email=(String)session.getAttribute("email");
		  String address=(String)session.getAttribute("address");
		  String post=(String)session.getAttribute("post");
		  String phone=(String)session.getAttribute("phone");
		  
		  JSONObject obj=new JSONObject();
		  obj.put("name", name);
		  obj.put("email", email);
		  obj.put("address", address);
		  obj.put("post", post);
		  obj.put("phone", phone);
		  
		  PrintWriter out=null;
		  try
		  {
			  out=response.getWriter();
			  out.write(obj.toJSONString());
		  }catch(Exception ex)
		  {
			  out.write("FAIL");
		  }
	  }
	  @RequestMapping("mypage/mypage_buy.do")
	  public String mypage_buy(HttpServletRequest request,HttpServletResponse response)
	  {
		  HttpSession session=request.getSession();
		  String id=(String)session.getAttribute("id");
		  List<CartVO> list=CartDAO.buyListData(id);
		  String fPrice="";
		  for(CartVO vo:list) {
			  fPrice=new DecimalFormat("###,###,###").format(vo.getPrice());
			  vo.setWon(fPrice);
		  }
		  int total=CartDAO.buyTotalPrice(id);
		  String totalform=new DecimalFormat("###,###,###").format(total);
		  request.setAttribute("buyList", list);
		  request.setAttribute("count", list.size());
		  request.setAttribute("total", totalform);
		  request.setAttribute("mypage_jsp", "../mypage/mypage_buy.jsp");
		  request.setAttribute("main_jsp","../mypage/mypage_main.jsp");
		  request.setAttribute("title", "구매 내역");
		  return "../main/main.jsp";
	  }
	  @RequestMapping("mypage/mypage_cart_cancel.do")
	  public String mypage_cart_cancel(HttpServletRequest request,HttpServletResponse response)
	  {
		  String cno=request.getParameter("cno");
		  // DB 연동
		  CartDAO.cartCancel(Integer.parseInt(cno));
		  return "redirect:../mypage/mypage_cart.do";
	  }
	  @RequestMapping("mypage/mypage_cart_buy.do")
	  public void mypage_cart_buy(HttpServletRequest request,HttpServletResponse response)
	  {
		  String cno=request.getParameter("cno");
		  String account=request.getParameter("account");
		  CartVO vo=new CartVO();
		  vo.setCart_no(Integer.parseInt(cno));
		  vo.setAccount(Integer.parseInt(account));
		  CartDAO.cartBuyUpdate(vo);
		  HttpSession session=request.getSession();
		  String name=(String)session.getAttribute("name");
		  String email=(String)session.getAttribute("email");
		  String address=(String)session.getAttribute("address");
		  String post=(String)session.getAttribute("post");
		  String phone=(String)session.getAttribute("phone");
		  
		  JSONObject obj=new JSONObject();
		  obj.put("name", name);
		  obj.put("email", email);
		  obj.put("address", address);
		  obj.put("post", post);
		  obj.put("phone", phone);
		  
		  PrintWriter out=null;
		  try
		  {
			  out=response.getWriter();
			  out.write(obj.toJSONString());
		  }catch(Exception ex)
		  {
			  out.write("FAIL");
		  }
	  }
	  
	  @RequestMapping("main/cart_count.do")
	  public void main_cart_count(HttpServletRequest request,HttpServletResponse response) {
		  HttpSession session=request.getSession();
		  String id=(String)session.getAttribute("id");
		  if(id!=null) {
			  int count=CartDAO.cartListCount(id);
			  try {
				  response.setContentType("text/html;charset=UTF-8");
				  PrintWriter out=response.getWriter();
				  out.write(String.valueOf(count));
			  }catch(Exception ex) {}
		  }
	  }
}