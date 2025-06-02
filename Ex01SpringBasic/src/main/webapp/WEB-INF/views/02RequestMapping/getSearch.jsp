<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="../static/bootstrap-5.1.3/css/bootstrap.min.css" />
		<script src="../static/bootstrap-5.1.3/js/bootstrap.bundle.min.js"></script>
		<script src="../static/jquery/jquery-3.7.1.min.js"></script>
	</head>
	<body>
		<div class="container">
			<h2>@RequestMapping 어노테이션</h2>
			<!-- request내장객체를 통해 전송방식을 출력한다. -->		
			<h3><%=request.getMethod() %>방식으로 전송된 검색 파라미터</h3>
			<!-- Model객체를 통해 저장한 속성값을 EL로 출력한다.  -->
			<ul>
				<li>검색필드 : ${sColumn }</li>
				<li>검색단어 : ${sWord }</li>
			</ul>
		</div>	
	</body>
</html>