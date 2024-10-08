package com.sist.dao;
import java.util.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.sist.vo.*;
public class WishDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf=CreateSqlSessionFactory.getSsf();
	}
	
	public static void wishInsert(Map map) {
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.insert("wishInsert",map);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
	}
	// 위시
	public static int wishCheck(Map map) {
		int count=0;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			count=session.selectOne("wishCheck",map);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return count;
	}
	
	// 마이페이지
	// 홀 마이페이지 위시 리스트
	public static List<WishVO> hallWishMypageData(String id){
		List<WishVO> list=new ArrayList<WishVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("hallWishMypageData",id);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	// 수트 마이페이지 위시 리스트 
	public static List<WishVO> suitMypageData(String id){
		List<WishVO> list=new ArrayList<WishVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("suitMypageData",id);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	// 드레스 마이페이지 위시 리스트
	public static List<WishVO> dressMypageData(String id){
		List<WishVO> list=new ArrayList<WishVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("dressMypageData",id);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	// 헤어 메이크업 마이페이지 위시 리스트
	public static List<WishVO> hairmakeupMypageData(String id){
		List<WishVO> list=new ArrayList<WishVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("hairmakeupMypageData",id);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	// 스튜디오 마이페이지 위시 리스트
	public static List<WishVO> studioMypageData(String id){
		List<WishVO> list=new ArrayList<WishVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("studioMypageData",id);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	// 혼수 마이페이지 위시 리스트
	public static List<WishVO> goodsMypageData(String id){
		List<WishVO> list=new ArrayList<WishVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("goodsMypageData",id);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	// 위시 개수 증가
	public static void hallWishCountIncrement(Map map) {
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.update("hallWishCountIncrement",map);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
	}
	
	public static void suitCountIncrement(Map map) {
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.update("suitWishCountIncrement", map);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
	}
	public static void dressWishCountIncrement(Map map) {
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.update("dressWishCountIncrement", map);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
	}
	public static void studioWishCountIncrement(Map map) {
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.update("studioWishCountIncrement", map);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
	}
	public static void hairmakeupWishCountIncrement(Map map) {
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.update("hairmakeupWishCountIncrement", map);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
	}
	public static void goodsWishCountIncrement(Map map) {
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.update("goodsWishCountIncrement", map);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
	}
	
	// 위시 취소하기
	public static void wishCancel(int wno) {
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.delete("wishCancel",wno);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
	}
	
	public static void wishDetailDelete(Map map) {
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.delete("wishDetailDelete", map);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
	}
}
