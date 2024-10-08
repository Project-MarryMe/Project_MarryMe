package com.sist.model;
import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;
public class QnaModel {
	@RequestMapping("qna/userInsert.do")
	public void qna_userinsert_ok(HttpServletRequest request,HttpServletResponse response) {
		try
		{
		  request.setCharacterEncoding("UTF-8");
		}catch(Exception ex){}
		  
		  String msg=request.getParameter("msg");
		  String pwd=request.getParameter("pwd");
		  String pno=request.getParameter("pno");
		  String cate=request.getParameter("cate");
		  HttpSession session=request.getSession();
		  String id=(String)session.getAttribute("id");
		  String tab=request.getParameter("tab");
		  // a.jpg
		  // a.jpg => a1.jpg
		  
		  QnaVO vo=new QnaVO();
		  
		  vo.setId(id);
		  vo.setMsg(msg);
		  vo.setPwd(pwd);
		  vo.setPno(Integer.parseInt(pno));
		  vo.setCate(Integer.parseInt(cate));
		  // user = tab(0) admin = tab(1)
		  vo.setTab(Integer.parseInt(tab));
		  String result="";
		  
		  try {
			QnaDAO.qnaUserInsert(vo);
			result="OK";
		 } catch (Exception e) {
			result="NO";
			// TODO: handle exception
		 }
		  
		  try {
				
				response.setContentType("text/plain;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.write(result);
		   } catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
		   }
		
	}
	 
	
	@RequestMapping("qna/list.do")
	public void qna_list(HttpServletRequest request,HttpServletResponse response) {
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		String pno=request.getParameter("pno");
		String cate=request.getParameter("cate");
		int curpage=Integer.parseInt(page);
		int rowSize=3;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		int tab=0; // 사용자
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("tab", tab);
		map.put("pno", pno);
		map.put("cate", cate);
		List<QnaVO> ulist=QnaDAO.qnaUserListData(map);
		tab=1; // 어드민
		map.put("tab" , tab);
		
		List<QnaVO> qnaList=new ArrayList<QnaVO>();
		
		for(QnaVO vo:ulist) {
			qnaList.add(vo);
			map.put("group_id", vo.getGroup_id());
			
			QnaVO avo=QnaDAO.qnaAdminData(map);
			if(avo!=null) {
				qnaList.add(avo);
			}
		}
		
		int total=0;
		if(qnaList.size()!=0){
			total=QnaDAO.qnaTotalPage(map);
		}
		int totalpage=(int)(Math.ceil(total/(double)rowSize));

		// 페이지 개수 표시
		final int BLOCK=5;
		
		
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endpage>totalpage)
			endpage=totalpage;
		
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		  Date date=new Date();
		  String today=sdf.format(date);
		  // new SimpleDateFormat("yyyy-MM-dd").format(new Date())
		JSONArray arr=new JSONArray();
		HttpSession session=request.getSession();
		String sessionId=(String)session.getAttribute("id");
		int h=0;
		try {
		for(QnaVO vo:qnaList) {
			JSONObject obj=new JSONObject();
		//SELECT qna_no,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,cate,group_id,tab,name,num
			obj.put("qno", vo.getQna_no());
			obj.put("name", vo.getMvo().getName());
			obj.put("dbday", vo.getDbday().substring(0,vo.getDbday().indexOf(":")));
			obj.put("dayDetail", vo.getDbday().substring(vo.getDbday().indexOf(":")+1));
			obj.put("cate", vo.getCate());
			obj.put("msg", vo.getMsg());
			obj.put("today", today);
			obj.put("group_id", vo.getGroup_id());
			obj.put("tab", vo.getTab());
			obj.put("id", vo.getId());
			obj.put("sessionId", sessionId);
			obj.put("total", total);
			if(h==0) {
			obj.put("startpage", startpage);
			obj.put("endpage", endpage);
			obj.put("totalpage", totalpage);
			obj.put("curpage", curpage);
			
			h=1;
			}
			arr.add(obj);
		}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		try {
			
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.write(arr.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	@RequestMapping("qna/password.do")
	public void qna_password(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			String result="";
			String qno=request.getParameter("qno");
			String pwd=request.getParameter("pwd");
			String dbpwd=QnaDAO.passwordData(Integer.parseInt(qno));
			if(pwd.equals(dbpwd)) {
				result="OK";
			}else {
				result="NO";
			}
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("qna/userUpdate.do")
	public void qna_userUpdate(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
		}
		String qno=request.getParameter("qno");
		String msg=request.getParameter("msg");
		String pwd=request.getParameter("pwd");
		
		QnaVO vo=new QnaVO();
		vo.setQna_no(Integer.parseInt(qno));
		vo.setMsg(msg);
		vo.setPwd(pwd);
		String result="";
		try {
			QnaDAO.qnaUpdate(vo);
			result="OK";
		}catch(Exception ex){
			result="NO";
		}
		try {
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.write(result);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@RequestMapping("qna/delete.do")
	public void qna_delete(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
		}
		String qno=request.getParameter("qno");
		
		String result="";
		try {
			QnaDAO.qnaDelete(Integer.parseInt(qno));
			result="OK";
		}catch(Exception ex){
			result="NO";
		}
		try {
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.write(result);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@RequestMapping("adminpage/adminpage_qna_insert.do")
	public String adminpage_qna_insert(HttpServletRequest request, HttpServletResponse response) {
		String qna_no = request.getParameter("qna_no");
		QnaVO vo = QnaDAO.adminQnaDetail2(Integer.parseInt(qna_no));
		
		request.setAttribute("vo", vo);
		request.setAttribute("admin_jsp", "../adminpage/adminpage_qna_insert.jsp");
		request.setAttribute("main_jsp", "../adminpage/adminpage_main.jsp");
		return "../main/main.jsp";
	}

	@RequestMapping("adminpage/adminpage_qna_insert_ok.do")
	public String adminpage_qna_insert_ok(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
		}
		String qna_no = request.getParameter("qno");
		String msg = request.getParameter("content");
		
		
		QnaVO vo = QnaDAO.adminQnaDetail2(Integer.parseInt(qna_no));
		QnaVO vvo = new QnaVO();
		vvo.setMsg(msg);
		vvo.setCate(vo.getCate());
		vvo.setGroup_id(vo.getGroup_id());
		vvo.setPno(vo.getPno());
		vvo.setPwd(vo.getPwd());
		vvo.setTab(1);
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		vvo.setId(id);

		QnaDAO.adminQnaInsert(vvo);
		
		return "redirect:../adminpage/adminpage_qna.do";
	}
	
	@RequestMapping("adminpage/adminpage_qna_update.do")
	public String adminpage_qna_update(HttpServletRequest request, HttpServletResponse response) {
		String qna_no = request.getParameter("qna_no");
		QnaVO vo = QnaDAO.adminQnaDetail2(Integer.parseInt(qna_no));
		
		String Remsg = QnaDAO.adminQnaMessage(vo.getGroup_id());
		
		request.setAttribute("Remsg", Remsg);
		request.setAttribute("vo", vo);
		request.setAttribute("admin_jsp", "../adminpage/adminpage_qna_update.jsp");
		request.setAttribute("main_jsp", "../adminpage/adminpage_main.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("adminpage/adminpage_qna_update_ok.do")
	public String adminpage_qna_update_ok(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
		}
		String qna_no = request.getParameter("qno");
		String msg = request.getParameter("content");

		QnaVO vo = QnaDAO.adminQnaDetail2(Integer.parseInt(qna_no));
		
		Map map = new HashMap();
		map.put("msg", msg);
		map.put("group_id", vo.getGroup_id());
		
		
		QnaDAO.adminQnaUpdate(map);
		return "redirect:../adminpage/adminpage_qna.do";
	}
}
