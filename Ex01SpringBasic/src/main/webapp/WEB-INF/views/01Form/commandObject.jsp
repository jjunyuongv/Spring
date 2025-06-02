<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>modelAttribute</title>
		<link rel="stylesheet" href="../static/bootstrap-5.1.3/css/bootstrap.min.css" />
		<script src="../static/bootstrap-5.1.3/js/bootstrap.bundle.min.js"></script>
		<script src="../static/jquery/jquery-3.7.1.min.js"></script>
	</head>
	<body>
		<h2>Form값 받기</h2>
		<h3>@ModelAttribute 어노테이션을 사용하여 커멘드객체명 변경</h3>
		<ul>
			<li>이름 : ${dto.name }</li>
			<li>아이디 : ${dto.id }</li>
			<li>패스워드 : ${dto.pw }</li>
			<li>이메일 : ${dto.email }</li>
		</ul>
	</body>
</html>