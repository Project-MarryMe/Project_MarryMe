<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>기본 레이아웃</title>
    <!-- 외부 CSS 파일 링크 -->
    <link rel="stylesheet" type="text/css" href="styles.css">
   <style type="text/css">
      .h3{
         text-align: center;
      }
      #my_title{
         font-size: 20px;
         font-family: 'SCoreDream', sans-serif;
         font-weight:500;
         margin-bottom: 20px;
      }
      #my_title::before {
        content: "▶ ";
        color: #0b3a1e;
}
      #my_title::after{
        content: "◀";
        color: #0b3a1e;
}
   </style>

</head>
<body>
    <div class="mycontainer">
        <section class="mypage_menu">
            <!-- 사이드바 메뉴 영역 -->
            <div>
            <h1 id="my_title">${title }</h1>
                <h3>개인정보</h3>
                <ul>
                    <li><a href="../mypage/mypage_main.do">정보확인</a></li>
                    <li><a href="../member/join_update.do">회원수정</a></li>
                    <li><a href="#">회원탈퇴</a></li>
                    <li><a href="">비밀번호 변경</a></li>
                </ul>
            </div>
            <div>
                <h3>관리정보</h3>
                <ul>
                    <li><a href="../mypage/mypage_wish.do">나의 위시리스트</a></li>
                    <li><a href="#">예약내역 조회</a></li>
                    <li><a href="#">게시판관리</a></li>
                    <li><a href="#">댓글관리</a></li>
                    <li><a href="#">장바구니 관리</a></li>
                </ul>
            </div>
        </section>
    <jsp:include page="${mypage_jsp }"></jsp:include>
    </div>
</body>
</html>
