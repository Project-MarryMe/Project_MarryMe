package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;
public class HallDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf=CreateSqlSessionFactory.getSsf();
	}
	
	//main_hall
	public static List<HallVO> mainHallData(){
		List<HallVO> list=new ArrayList<HallVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("mainHallData");
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return list;
	}
	
	public static List<HallVO> hallListData(Map map){
		List<HallVO> list=new ArrayList<HallVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("hallListData",map);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		} 
		return list;
	}
	
	
	public static int hallTotalPage(String ss) {
		int total=0;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			total=session.selectOne("hallTotalPage",ss);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return total;
	}
	
	public static HallVO hallDetailData(int hno) {
		HallVO vo=new HallVO();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			session.selectOne("hallHitIncrement",hno);
			session.commit();
			vo=session.selectOne("hallDetailData",hno);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return vo;
	}
}
