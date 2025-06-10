<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<!DOCTYPE html>
<html>
<head>
		<meta charset="UTF-8">
		<title>검색리스트</title>
		<link rel="stylesheet" href="../static/bootstrap-5.1.3/css/bootstrap.min.css" />
		<script src="../static/bootstrap-5.1.3/js/bootstrap.bundle.min.js"></script>
		<script src="../static/jquery/jquery-3.7.1.min.js"></script>
	</head>
	<body>
	<div class="container">
		<h3 class="text-center">방명록(한줄게시판)</h3>
		<div class="text-center">
		<form method="get">
			<select name="searchField">
				<option value="contents">내용</option>
				<option value="name">작성자</option>
			</select>
			<input type="text" name="searchTxt" />
			<input type="submit" value="검색" />
		</form>
		</div>
	</div>
	
	</body>
</html>