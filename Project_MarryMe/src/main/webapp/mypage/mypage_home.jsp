<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page</title>
<link rel="stylesheet" type="text/css" href="../css/mypage.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<style type="text/css">
   h1 {
        text-align: center;
        margin-bottom: 20px;
    }
    

    #weddingDayInfo {
        font-size: 18px;
        color: #000; /* 원하는 색상으로 변경 */
        text-align: center;
        margin-top: 20px;
    }

    #merry {
        background-color: #F5F5DC; /* 배경색 설정 */
        width: 500px; /* 너비 설정 */
        height: auto; /* 높이를 내용에 맞게 자동 조정 */
        padding: 20px; /* 내부 여백 추가 */
        border-radius: 10px; /* 모서리 둥글게 설정 */
        box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2); /* 그림자 추가 */
        margin: 20px auto; /* 상하 여백 및 수평 중앙 정렬 */
        line-height: 1.5; /* 줄 높이 설정 */
        font-family: 'SCoreDream', sans-serif;
    }
    
    #ppm{
      font-weight: bold;
    }
    
.personal-info {
    width: 60%;
    margin: 20px auto; /* 중앙 정렬 */
    padding: 15px;
    border: 2px solid #F5F5DC; /* 테두리 설정 */
    border-radius: 7px; /* 모서리 둥글게 설정 */
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* 그림자 추가 */
    font-family: 'SCoreDream', sans-serif;
}

.personal-info .info-item {
    display: flex; /* Flexbox 사용 */
    justify-content: space-between; /* 라벨과 텍스트 사이에 공간 분배 */
    margin-bottom: 15px;
}

.personal-info label {
    font-weight: bold;
    width: 150px; /* 라벨의 고정 폭 설정 */
    text-align: right; /* 라벨 텍스트를 오른쪽으로 정렬 */
    margin-right: 20px; /* 라벨과 텍스트 사이의 간격 조정 */
}

.personal-info .info-value {
    flex: 1; /* 텍스트가 남은 공간을 채우도록 설정 */
    text-align: left; /* 텍스트를 왼쪽으로 정렬 */
}

.infos::after {
        content: " ▶ ";
        color: #0b3a1e; /* 원하는 색상으로 설정 */
}


</style>
<script>
    // 서버에서 전달된 날짜 문자열을 JavaScript 변수에 할당
   	var weddingdayString = "<%= request.getAttribute("weddingday") %>";

    function showWeddingDay() {
        // 날짜 문자열의 형식이 'yyyy-MM-dd'일 때
        var weddingday = new Date(weddingdayString + "T00:00:00"); // 시간 부분을 추가하여 UTC 기준으로 날짜 생성
        var today = new Date();
        
        // 날짜 계산
        var timeDiff = weddingday.getTime() - today.getTime();
        var daysLeft = Math.ceil(timeDiff / (1000 * 60 * 60 * 24)); // 남은 일수 계산
        
        // 출력할 메시지 설정
        var message = "";
        if (daysLeft > 0) {
            message = '<div id="merry">설레는 그 날까지 <span style="color:#006400; font-weight:bold; font-size:24px;">' + daysLeft + '</span>일!<br><span id="ppm">Project MerryMe</span>가 돕겠습니다</div>';
        } else if (daysLeft === 0) {
            message = "오늘은 두 사람의 특별한 날, 결혼을 진심으로 축하드립니다!!";
        } else {
            message = "웨딩이 끝나셨군요. 행복한 하루하루를 보내세요.";
        }

        // 결과를 HTML 요소에 출력
        document.getElementById("weddingDayInfo").innerHTML = message;
    }
    
    // 페이지 로드 시 호출
    window.onload = showWeddingDay;
</script>
</head>
<body>
    <section class="mycontent">
        <div class="sixth">
            <h1>${sessionScope.name}&nbsp;${sessionScope.gender}님 마이페이지</h1>
        </div>
        <div id="weddingDayInfo">
            <!-- 메시지가 여기 출력됩니다. -->
        </div>
      <!-- 개인정보 출력 부분 -->
        <div class="personal-info">
            <div class="info-item">
                <label for="id" class="infos">아이디</label>
                <div class="info-value">${sessionScope.id}</div>
            </div>
            <div class="info-item">
                <label for="name" class="infos">이름</label>
                <div class="info-value">${sessionScope.name}</div>
            </div>
            <div class="info-item">
                <label for="phone" class="infos">핸드폰</label>
                <div class="info-value">${vo.phone}</div>
            </div>
            <div class="info-item">
                <label for="email" class="infos">이메일</label>
                <div class="info-value">${vo.email}</div>
            </div>
            <div class="info-item">
                <label for="post" class="infos">우편번호</label>
                <div class="info-value">${vo.post}</div>
            </div>
            <div class="info-item">
                <label for="address1" class="infos">주소</label>
                <div class="info-value">${vo.address1}</div>
            </div>
            <div class="info-item">
                <label for="address2" class="infos">상세주소</label>
                <div class="info-value">${vo.address2}</div>
            </div>
            <div class="info-item" class="infos">
                <label for="address2" class="infos">결혼 예정일</label>
                <div class="info-value">${weddingday}</div>
            </div>
        </div>
    </section>
</body>
</html>