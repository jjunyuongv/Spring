<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>pathVariable</title>
	</head>
	<body>
		<h2>Form값 받기</h2>
		<h3>@PathVariable 어노테이션으로 폼값 받기</h3>
		<ul>
			<li>회원 아이디 : ${memberId }</li>
			<li>회원 이름 : ${memberName }</li>
		</ul>
		
		<!--  
			웹브라우저는 /로 구분되는 요청명을 디렉토리(경로)로 인식하므로
			현재 "/form/gildong/홍길동"에서는 ../를 추가하여 상위디렉토리로
			이동한 후 images 경로를 찾아야 한다. 
			현재 resources폴더의 매핑은 static으로 해둔 상태이다.
			-->
		<div>
			<h3>이미지 경로 확인</h3>
			<img src="../../static/images/4.png" alt="졸귀 강아지" />
		</div>
	</body>
</html>