package com.sist.dao;
import java.util.*;
import com.sist.vo.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
public class ReserveDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf=CreateSqlSessionFactory.getSsf();
	}
	
	public static int reserveCheck(ReserveVO vo) {
		int count=0;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			count=session.selectOne("reserveCheck", vo);
		}catch(Exception ex) {
			
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return count;
	}
	
	public static void reserveInsert(ReserveVO vo) {
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.insert("reserveInsert", vo);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
	}
	
	public static List<ReserveVO> reserveExist(Map map) {
		List<ReserveVO> list=new ArrayList<ReserveVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("reserveExist", map);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return list;
	}
	// 마이페이지 예약 내역 출력 
	public static List<ReserveVO> hallReserveMypageData(String id){
		List<ReserveVO> list=new ArrayList<ReserveVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("hallReserveMypageData",id);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	public static List<ReserveVO> suitReserveMypageData(String id){
		List<ReserveVO> list=new ArrayList<ReserveVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("suitReserveMypageData",id);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	public static List<ReserveVO> dressReserveMypageData(String id){
		List<ReserveVO> list=new ArrayList<ReserveVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("dressReserveMypageData",id);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	public static List<ReserveVO> studioReserveMypageData(String id){
		List<ReserveVO> list=new ArrayList<ReserveVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("studioReserveMypageData",id);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	public static List<ReserveVO> hairmakeupReserveMypageData(String id){
		List<ReserveVO> list=new ArrayList<ReserveVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("hairmakeupReserveMypageData",id);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	// 마이페이지 예약 취소 
	   public static void reserveCancel(int rno) {
		   SqlSession session=null; //Connection
		   try
		   {
			   session=ssf.openSession(true);
			   session.delete("reserveCancel",rno);
		   }catch(Exception ex)
		   {
			   ex.printStackTrace();
		   }
		   finally
		   {
			   if(session!=null)
				   session.close();
		   }
	   }
	   
// ************************ 여기부터는 관리자 예약 내역 입니다. ^^	 ***********************************
		public static List<ReserveVO> hallReserveAdminpageData(){
			List<ReserveVO> list=new ArrayList<ReserveVO>();
			SqlSession session=null;
			try {
				session=ssf.openSession();
				list=session.selectList("hallReserveAdminpageData");
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally {
				if(session!=null)
					session.close();
			}
			return list;
		}
		public static List<ReserveVO> suitReserveAdminpageData(){
			List<ReserveVO> list=new ArrayList<ReserveVO>();
			SqlSession session=null;
			try {
				session=ssf.openSession();
				list=session.selectList("suitReserveAdminpageData");
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally {
				if(session!=null)
					session.close();
			}
			return list;
		}
		public static List<ReserveVO> dressReserveAdminpageData(){
			List<ReserveVO> list=new ArrayList<ReserveVO>();
			SqlSession session=null;
			try {
				session=ssf.openSession();
				list=session.selectList("dressReserveAdminpageData");
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally {
				if(session!=null)
					session.close();
			}
			return list;
		}
		public static List<ReserveVO> studioReserveAdminpageData(){
			List<ReserveVO> list=new ArrayList<ReserveVO>();
			SqlSession session=null;
			try {
				session=ssf.openSession();
				list=session.selectList("studioReserveAdminpageData");
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally {
				if(session!=null)
					session.close();
			}
			return list;
		}
		public static List<ReserveVO> hairmakeupReserveAdminpageData(){
			List<ReserveVO> list=new ArrayList<ReserveVO>();
			SqlSession session=null;
			try {
				session=ssf.openSession();
				list=session.selectList("hairmakeupReserveAdminpageData");
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally {
				if(session!=null)
					session.close();
			}
			return list;
		}
		
		/*
		 * <update id="adminReserveOK" parameterType="int">
				UPDATE reserve SET
				confirm = 'y'
				WHERE resno = #{resno}
			</update>
		 * 
		 */
		public static void adminReserveOK(int resno) {
			SqlSession session = null;
			try {
				session = ssf.openSession();
				session.update("adminReserveOK", resno);
				session.commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (session != null)
					session.close();
			}
		}
		
		/*	예약 삭제
		 * 	<delete id="adminReserveDelete" parameterType="int">
				DELETE FROM reserve
				WHERE resno=#{resno}
			</delete>
		 * 	
		 */
		public static void adminReserveDelete(int resno) {
			SqlSession session = null;
			try {
				session = ssf.openSession();
				session.delete("adminReserveDelete", resno);
				session.commit();
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				session.close();
			}
		}
}
