<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>requestParam</title>
		<link rel="stylesheet" href="../static/bootstrap-5.1.3/css/bootstrap.min.css" />
		<script src="../static/bootstrap-5.1.3/js/bootstrap.bundle.min.js"></script>
		<script src="../static/jquery/jquery-3.7.1.min.js"></script>
	</head>
	<body>
		<div class="container">
			<h2>Form값 받기</h2>
			<h3>@RequestParam 어노테이션으로 파라미터 받기</h3>
			<!-- Model객체를 통해 저장된 MemberDTO객체를 EL로 출력한다. 
			EL로 getter() 메서드 호출시 멤버변수명만 기술하면 된다.  -->
			<ul>
				<li>이름 : ${memberDTO.name }</li>
				<li>아이디 : ${memberDTO.id }</li>
				<li>패스워드 : ${memberDTO.pw }</li>
				<li>이메일 : ${memberDTO.email }</li>
			</ul>
		</div>
	</body>
</html>