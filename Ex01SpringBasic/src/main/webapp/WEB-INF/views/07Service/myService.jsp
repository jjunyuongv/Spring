<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>myService</title>
		<link rel="stylesheet" href="../static/bootstrap-5.1.3/css/bootstrap.min.css" />
		<script src="../static/bootstrap-5.1.3/js/bootstrap.bundle.min.js"></script>
		<script src="../static/jquery/jquery-3.7.1.min.js"></script>
	</head>
	<body>
		<h2>Service객체 알아보기</h2>
		<p>
			Service객체는 Controller 와 Model 사이에서
			<br />
			중재자 역할을 한다.
			<ul>
				<li>Controller : 요청 분석 후 서비스 객체 호출</li>
				<li>Service : 요청을 처리할 비즈니스 로직 실행</li>
				<li>Model : DB관련 CRUD 작업</li>
			</ul>
		</p>
	</body>
</html>