package com.sist.model;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;

public class AdminPageModel {
	private String[] types = {"", "공지1", "공지2", "공지3", "공지4"};
	
	@RequestMapping("adminpage/adminpage_main.do")
	public String adminpage_main(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
	    String id = (String) session.getAttribute("id");
	    MemberVO vo = MemberDAO.memberUpdateData(id);
	    
	    request.setAttribute("vo", vo);
	    request.setAttribute("title", "💐관리자 페이지💐");
		request.setAttribute("admin_jsp", "../adminpage/adminpage_home.jsp");
		request.setAttribute("main_jsp", "../adminpage/adminpage_main.jsp");
		
		return "../main/main.jsp";
	}
	
	@RequestMapping("adminpage/member_list.do")
	public String member_list(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("admin_jsp", "../adminpage/member_list.jsp");
		request.setAttribute("main_jsp", "../adminpage/adminpage_main.jsp");
		
		return "../main/main.do";
	}

	@RequestMapping("adminpage/notice_list.do")
	   public String notice_list(HttpServletRequest request,HttpServletResponse response)
	   {
		   String page=request.getParameter("page");
		   if(page==null)
			   page="1";
		   int curpage=Integer.parseInt(page);
		   int rowSize=15;
		   int start=(rowSize*curpage)-(rowSize-1);
		   int end=rowSize*curpage;
		   Map map=new HashMap();
		   map.put("start", start);
		   map.put("end", end);
		   
		   List<NoticeVO> list=NoticeDAO.noticeListData(map);
		   for(NoticeVO vo:list)
		   {
			   vo.setNotice_type(types[vo.getType()]);
		   }
		   int count=NoticeDAO.noticeTotal();
		   int totalpage=(int)(Math.ceil(count/15.0));
		   count=count-((curpage*rowSize)-rowSize);
		   
		   request.setAttribute("curpage", curpage);
		   request.setAttribute("totalpage", totalpage);
		   request.setAttribute("count", count);
		   request.setAttribute("noticeList", list);
		   
		   request.setAttribute("admin_jsp", "../notice/notice_list.jsp");
		   request.setAttribute("main_jsp", "../adminpage/adminpage_main.jsp");
		   
		   return "../main/main.jsp";
	   }
	   
	   @RequestMapping("adminpage/notice_insert.do")
	   public String notice_insert(HttpServletRequest request,HttpServletResponse response)
	   {
		   request.setAttribute("admin_jsp", "../notice/notice_insert.jsp");
		   request.setAttribute("main_jsp", "../adminpage/adminpage_main.jsp");
		   return "../main/main.jsp";
	   }
	   
	   @RequestMapping("adminpage/notice_insert_ok.do")
	   public String notice_insert_ok(HttpServletRequest request,HttpServletResponse response)
	   {
		   try
		   {
			   request.setCharacterEncoding("UTF-8");
		   }catch(Exception ex) {}
		   String type=request.getParameter("type");
		   String subject=request.getParameter("subject");
		   String content=request.getParameter("content");
		   NoticeVO vo=new NoticeVO();
		   vo.setType(Integer.parseInt(type));
		   vo.setSubject(subject);
		   vo.setContent(content);
		   NoticeDAO.noticeInsert(vo);
		   return "redirect:../adminpage/notice_list.do";
	   }
	   
	   @RequestMapping("adminpage/notice_update.do")
	   public String notice_update(HttpServletRequest request,HttpServletResponse response)
	   {
		   String no=request.getParameter("no");
		   NoticeVO vo=NoticeDAO.noticeUpdateData(Integer.parseInt(no));
		   request.setAttribute("vo", vo);
		   request.setAttribute("admin_jsp", "../notice/notice_update.jsp");
		   request.setAttribute("main_jsp", "../adminpage/adminpage_main.jsp");
		   return "../main/main.jsp";
	   }
	   
	   @RequestMapping("adminpage/notice_update_ok.do")
	   public String notice_update_ok(HttpServletRequest request,HttpServletResponse response)
	   {
		   try
		   {
			   request.setCharacterEncoding("UTF-8");
		   }catch(Exception ex) {}
		   String type=request.getParameter("type");
		   String subject=request.getParameter("subject");
		   String content=request.getParameter("content");
		   String no=request.getParameter("no");//update,delete
		   NoticeVO vo=new NoticeVO();
		   vo.setType(Integer.parseInt(type));
		   vo.setSubject(subject);
		   vo.setContent(content);
		   vo.setNo(Integer.parseInt(no));
		   // DB연동 
		   NoticeDAO.noticeUpdate(vo);
		   return "redirect:../adminpage/notice_list.do";
	   }
	   
	   @RequestMapping("adminpage/notice_delete.do")
	   public String notice_delete(HttpServletRequest request,HttpServletResponse response)
	   {
		   String no=request.getParameter("no");
		   //DB연동 
		   NoticeDAO.noticeDelete(Integer.parseInt(no));
		   return "redirect:../adminpage/notice_list.do";
	   }
	   
	   @RequestMapping("adminpage/reply_insert.do")
	   public String reply_insert(HttpServletRequest request,HttpServletResponse response)
	   {
		   String no=request.getParameter("no");
		   request.setAttribute("no", no);
		   request.setAttribute("admin_jsp", "../adminpage/adminpage_reply_insert.jsp");
		   request.setAttribute("main_jsp", "../adminpage/adminpage_main.jsp");

		   return "../main/main.jsp";
	   }
	   
	   @RequestMapping("adminpage/qna_list.do")
	   public String qna_list(HttpServletRequest request,HttpServletResponse response)
	   {
		  String no=request.getParameter("no");
		  String page=request.getParameter("page");
		  if(page==null) {
			  page="1";
		  }
		  int curpage=Integer.parseInt(page);
		   request.setAttribute("no", no);
		   request.setAttribute("pno", 2);
		   request.setAttribute("cate", 6);
		   request.setAttribute("curpate", curpage);
		   request.setAttribute("admin_jsp", "../adminpage/admin_qna.jsp");
		   request.setAttribute("main_jsp", "../adminpage/adminpage_main.jsp");

		   return "../main/main.jsp";
	   }
}
