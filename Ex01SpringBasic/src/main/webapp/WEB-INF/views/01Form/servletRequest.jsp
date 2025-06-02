<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>servletRequest</title>
		<link rel="stylesheet" href="./static/bootstrap-5.1.3/css/bootstrap.min.css" />
		<script src="./static/bootstrap-5.1.3/js/bootstrap.bundle.min.js"></script>
		<script src="./static/jquery/jquery-3.7.1.min.js"></script>
	</head>
	<body>
		<div class="container">
			<h2>Form값 받기</h2>
			<h3>HttoServletRequest로 폼값 받기</h3>
			<ul>
				<li>아이디 : ${id }</li>
				<li>패스워드 : ${pw }</li>
				<li>메세지 : ${message }</li>
			</ul>
		</div>
	</body>
</html>