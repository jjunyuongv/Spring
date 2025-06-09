<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Insert title here</title>
		<link rel="stylesheet" href="../static/bootstrap-5.1.3/css/bootstrap.min.css" />
		<script src="../static/bootstrap-5.1.3/js/bootstrap.bundle.min.js"></script>
		<script src="../static/jquery/jquery-3.7.1.min.js"></script>
	</head>
	<script type="text/javascript">
	function loginValidate(f)
	{
		if(f.id.value==""){
			alert("아이디를 입력하세요");
			f.id.focus();
			return false;
		}
		if(f.pass.value==""){
			alert("패스워드를 입력하세요"); 
			f.pass.focus();
			return false;
		} 
	}
	</script>
	
	<body>
	<div class="container"> 
		<h3>방명록(로그인)</h3> 
		<c:choose>
			<c:when test="${not empty sessionScope.siteUserInfo }">
				<div class="row" style="border:2px solid #cccccc;padding:10px;">			
					<h4>아이디:${sessionScope.siteUserInfo.id }</h4>
					<h4>이름:${sessionScope.siteUserInfo.name }</h4>
					<br /><br />
					<button class="btn btn-danger" 
						onclick="location.href='logout.do';">
						로그아웃</button>
					&nbsp;&nbsp;
					<button class="btn btn-primary" 
						onclick="location.href='list.do';">
						방명록리스트</button>
				</div>
			</c:when>
			<c:otherwise>
				<span style="font-size:1.5em; color:red;">${LoginNG }</span>
				<form name="loginForm" method="post" action="./loginAction.do" onsubmit="return loginValidate(this);">
					<input type="hidden" name="backUrl" value="${param.backUrl }"/>
					<table class="table-bordered" style="width:50%;">
						<tr>
							<td><input type="text" class="form-control" name="id" placeholder="아이디" tabindex="1"></td>
							<td rowspan="2" style="width:80px;"><button type="submit" class="btn btn-primary" style="height:77px; width:77px;"  tabindex="3">로그인</button></td>
						</tr>
						<tr>
							<td><input type="password" class="form-control" name="pass" placeholder="패스워드" tabindex="2"></td>
						</tr>
					</table>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
	
	</body>
</html>