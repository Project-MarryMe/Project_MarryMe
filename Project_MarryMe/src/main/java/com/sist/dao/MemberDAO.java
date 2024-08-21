package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.sist.vo.*;
public class MemberDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf=CreateSqlSessionFactory.getSsf();
	}
	
	/*
	<select id="memberIdCheck" resultType="int" parameterType="string">
		SELECT COUNT(*) FROM member WHERE id=#{id}
	</select> 
	*/
	public static int memberIdCheck(String id) {
		int count=0;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			count=session.selectOne("memberIdCheck", id);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return count;
	}
	
	/*
	<insert id="memberInsert" parameterType="MemberVO">
		INSERT INTO member VALUES(#{id}, #{pw}, #{name}, #{phone}, #{email}, #{post}, #{address1}, #{address2}, #{gender}, #{weddingday}, 'n', SYSDATE)
	</insert> 
	 */
	public static void memberInsert(MemberVO vo) {
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.insert("memberInsert", vo);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
	}
	
	/*
	<select id="memberLoginData" resultType="MemberVO" parameterType="string">
		SELECT * FROM member WHERE id=#{id}
	</select>
	 */
	public static MemberVO memberLogin(String id, String pw) {
		MemberVO vo=new MemberVO();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			int count=session.selectOne("memberIdCheck", id);
			if(count==0) {
				vo.setMsg("noID");
			}else {
				vo=session.selectOne("memberLoginData", id);
				if(pw.equals(vo.getPw())) {
					vo.setMsg("correct");
				}else {
					vo.setMsg("noPW");
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return vo;
	}
	/*
	 *  <select id="memberUpdateData" resultType="MemberVO" parameterType="string">
	 		SELECT id,name,phone,email,post,address1,address2,gender,weddingday
	 		FROM member
	 		WHERE id=#{id}
 		</select>
	 */
	public static MemberVO memberUpdateData(String id) {
		MemberVO vo=new MemberVO();
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			vo=session.selectOne("memberUpdateData",id);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return vo;
	}
	
	/*
	<select id="memberIdFindCount" resultType="int" parameterType="MemberVO">
 		SELECT COUNT(*) FROM member WHERE name=#{name} AND email=#{email}
 	</select>
 	<select id="memberIdFindData" resultType="string" parameterType="MemberVO">
 		SELECT RPAD(SUBSTR(id,1,2),LENGTH(id),'*') FROM member
 		WHERE name=#{name} AND email=#{email}
 	</select> 
	 */
	public static String memberIdFind(MemberVO vo) {
		String result="";
		SqlSession session=null;
		try {
			session=ssf.openSession();
			int count=session.selectOne("memberIdFindCount", vo);
			if(count==0) {
				result="incorrect";
			}else {
				result=session.selectOne("memberIdFindData", vo);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return result;
	}
	   /*
	    *  <select id="memberGetPassword" resultType="string" parameterType="string">
	          SELECT pw FROM member
	          WHERE id=#{id}
	       </select>
	       <update id="memberUpdate" parameterType="MemberVO">
	          UPDATE member SET
	          name=#{name}, phone=#{phone}, email=#{email},
	          post=#{post}, address1=#{address1}, address2=#{address2}, gender=#{gender}, weddingday=#{weddingday}
	          WHERE id=#{id}
	       </update>
	    */   
	      public static boolean memberUpdate(MemberVO vo) {
	         boolean bCheck=false;
	         SqlSession session=null;
	         try {
	            session=ssf.openSession();
	            String db_pwd=session.selectOne("memberGetPassword",vo.getId());
	            
	            if(db_pwd != null && db_pwd.equals(vo.getPw())) {
	                bCheck=true;
	                session.update("memberUpdate", vo);
	                session.commit();
	            } else {
	                bCheck=false;
	            }
	         }catch(Exception ex) {
	            ex.printStackTrace();
	         }finally {
	            if(session!=null)
	               session.close();
	         }   
	         return bCheck;
	      }
}
