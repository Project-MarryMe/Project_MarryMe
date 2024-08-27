<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Page</title>
<link rel="stylesheet" type="text/css" href="../css/mypage.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.3/themes/base/jquery-ui.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="https://code.jquery.com/ui/1.13.3/jquery-ui.js"></script>
<style type="text/css">
#myh1 {
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

#memoForm {
    display: none; /* 기본적으로 숨김 */
    margin-bottom: 20px;
    transition: max-height 0.3s ease-out, padding 0.3s ease-out; /* 부드러운 전환 효과 */
    overflow: hidden; /* 내용이 넘치면 숨김 */
    max-height: 0; /* 기본 최대 높이 설정 */
    padding: 0; /* 기본 패딩 설정 */
}

#memoForm.open {
    display: block; /* 폼을 보이게 함 */
    max-height: 150px; /* 입력 폼의 최대 높이 설정 */
    padding: 10px; /* 폼 내부 여백 설정 */
}

#submitButton {
    padding: 10px 20px;
    font-size: 16px;
    background-color: #b5e7b5; /* 연녹색 배경 */
    color: #fff;
    border: none;
    border-radius: 50px; /* 둥근 버튼 */
    cursor: pointer;
    box-shadow: 0 2px 5px rgba(0,0,0,0.2);
    transition: background-color 0.3s ease; /* 부드러운 색상 변화 */
}

#submitButton:hover {
    background-color: #a3d3a1; /* 호버 시 더 연한 색상 */
}

#addMemoButton {
    padding: 10px 20px;
    font-size: 16px;
    background-color: #007bff; /* 기본 버튼 색상 */
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    box-shadow: 0 2px 5px rgba(0,0,0,0.2);
    transition: background-color 0.3s ease; /* 부드러운 색상 변화 */
}

#addMemoButton:hover {
    background-color: #0056b3; /* 호버 시 색상 변화 */
}

#memoList {
    display: flex;
    flex-wrap: wrap; /* 메모를 두 줄씩 배치 */
    gap: 20px; /* 메모 간의 간격 설정 */
    padding: 0;
    list-style: none;
}

#memoList li {
    background: #f0fdf0; /* 연한 연두색 배경 */
    border: 1px solid #b5e7b5; /* 연한 연두색 테두리 */
    padding: 10px;
    border-radius: 10px; /* 테두리 둥글게 */
    box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1);
    font-family: 'Courier New', Courier, monospace;
    width: calc(50% - 20px); /* 메모의 너비를 50%로 설정하고 간격을 고려해 조정 */
}

.memo-date {
    font-size: 0.9em;
    color: #666;
    margin-top: 5px; /* 날짜와 메모 내용 간의 간격 */
}

.memo-header {
    font-size: 1.1em;
    margin-bottom: 5px;
    color: #333;
}

.memo-content {
    font-size: 1em;
    color: #666;
}

.memoListItem {
    position: relative; /* 상대 위치 지정 */
    padding: 10px;
    border: 1px solid #ddd;
    margin-bottom: 10px;
    border-radius: 4px;
    background: #f9f9f9;
    box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1);
}

.actions {
    margin-top: 10px;
    text-align: right;
    display: none; /* 기본적으로 감춤 */
}


.actions button {
    margin-left: 10px;
    padding: 5px 10px;
    font-size: 14px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    color: #fff;
}

.actions .editMemo {
    background-color: #007bff;
}

.actions .editMemo:hover {
    background-color: #0056b3;
}

.actions .deleteMemo {
    background-color: #dc3545;
}

.actions .deleteMemo:hover {
    background-color: #c82333;
}


</style>
<script>
// 서버에서 전달된 날짜 문자열을 JavaScript 변수에 할당
var weddingdayString = "<%= request.getAttribute("weddingday") %>";

function showWeddingDay() {
    var weddingday = new Date(weddingdayString + "T00:00:00");
    var today = new Date();
    
    var timeDiff = weddingday.getTime() - today.getTime();
    var daysLeft = Math.ceil(timeDiff / (1000 * 60 * 60 * 24));
    
    var message = "";
    if (daysLeft > 0) {
        message = '<div id="merry">설레는 그 날까지 <span style="color:#006400; font-weight:bold; font-size:24px;">' + daysLeft + '</span>일!<br><span id="ppm">Project MerryMe</span>가 돕겠습니다</div>';
    } else if (daysLeft === 0) {
        message = "오늘은 두 사람의 특별한 날, 결혼을 진심으로 축하드립니다!!";
    } else {
        message = "웨딩이 끝나셨군요. 행복한 결혼생활이 되시길 바랍니다.";
    }

    document.getElementById("weddingDayInfo").innerHTML = message;
}

window.onload = showWeddingDay;



function loadMemoList() {
    const sessionId = $('#sessionId').val(); 

    $.ajax({
        type: 'POST',
        url: '../memo/list.do',
        data: { "id": sessionId }, // 세션 ID를 서버에 전달
        success: function(response) {
            try {
                var json = typeof response === 'string' ? JSON.parse(response) : response;

                var html = '';

                // 데이터가 없을 경우 처리
                if (Array.isArray(json) && json.length === 0) {
                    html += '<li><h3>작성된 메모가 없습니다</h3></li>';
                } else {
                    // JSON 데이터 배열을 map을 사용하여 HTML로 변환
                    for (var i = 0; i < json.length; i++) {
                        var memo = json[i];
                        html += '<li class="memoListItem" data-mno="' + memo.mno + '">';
                        html += '    <div class="top">';
                        html += '        <div class="profile">';
                        html += '        </div>';
                        html += '    </div>';
                        html += '    <div class="memo-content">' + memo.msg + '</div>'; // 메모 내용
                        html += '    <div class="memo-date" style="display:none;">' + memo.dbday + '</div>'; // 메모 날짜 숨김
                        html += '    <div class="actions" id="memoBtn" style="display:none;">';
                        html += '        <button class="editMemo">수정</button>';
                        html += '        <button class="deleteMemo">삭제</button>';
                        html += '    </div>';
                        html += '</li>';
                    }
                }

                // HTML 업데이트
                $('#memoList').html(html);
            } catch (e) {
                console.error('JSON 처리 오류:', e);
                $('#memoList').html('<li><h3>메모 목록을 불러오는 데 오류가 발생했습니다</h3></li>');
            }
        },
        error: function(xhr, status, error) {
            console.error('AJAX 오류:', status, error);
            $('#memoList').html('<li><h3>메모 목록을 불러오는 데 오류가 발생했습니다</h3></li>');
        }
    });
}

$(document).ready(function() {
    loadMemoList();

    $(document).on('click', '.memoListItem', function() {
        $(this).find('.actions, .memo-date').slideToggle('slow'); // 버튼과 날짜가 함께 부드럽게 나타남
    });
    $('#addMemoButton').click(function() {
        $('#memoForm').toggleClass('open'); // 'open' 클래스 토글
        $(this).hide(); // 버튼 숨기기
    });


});


</script>

</head>
<body>
    <section class="mycontent">
        <div class="sixth" id="myh1">
            <h1>💐${sessionScope.name}&nbsp;${sessionScope.gender}님 마이페이지💐</h1>
        </div>
        <div id="weddingDayInfo">
            <!-- 메시지가 여기 출력됩니다. -->
        </div>
        <!-- 개인정보 출력 부분 -->
        <div class="personal-info">
            <div class="info-item">
                <label for="id" class="infos">아이디</label>
                <div class="info-value">${sessionScope.id}</div>
	        	<input type="hidden" id="sessionId" value="${sessionScope.id}">
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
        </div>
		<div class="memocontainer">
			<div id="memoForm">
			    <input type="text" id="memoInput" placeholder="여기에 메모를 입력하세요">
			    <button id="submitButton">메모 추가</button>
			</div>
			<!-- 추가 버튼 -->
			<button id="addMemoButton">메모장</button>
		</div>
		<div>
        	<h3>나의 메모</h3>
	        <ul id="memoList">
	            <!-- 메모 내용이 여기에 추가됩니다 -->
	        </ul>
       </div>
    </section>
</body>
</html>