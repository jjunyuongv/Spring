<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Ex01SpringBasic</title>
		<link rel="stylesheet" href="./static/bootstrap-5.1.3/css/bootstrap.min.css" />
		<script src="./static/bootstrap-5.1.3/js/bootstrap.bundle.min.js"></script>
		<script src="./static/jquery/jquery-3.7.1.min.js"></script>
	</head>
	<body>
		<h2>Spring Framework Home</h2>
		
		<!-- Spring MVC에서는 이미지와 같은 정적리소스를 사용하기 위해 별도의
			resources폴더를 제공한다. 해당 폴더의 매핑은 servlet-context.xml에서
			변경하거나 추가할 수 있다.  -->
		<h3>정적 리소스 사용을 위한 resources 폴더</h3>
		<!-- views폴더 하위에 있는 이미지는 출력되지 않는다. views폴더는 
			View파일, 즉 jsp만 추가할 수 있다. 안나옴. 에러	-->
		<img alt="엑박뜸" src="./img_avatar1.png" />
		<!-- 폴더의 원본명은 resources이나 개발자가 별도로 별칭을 부여할 수 
			있으므로 아래 2개의 이미지는 정상적으로 출력된다. -->
		<img alt="징징이" src="./resources/images/2.png" />
		<img alt="뚱뚱이" src="./static/images/3.png" />
		
	</body>
</html>