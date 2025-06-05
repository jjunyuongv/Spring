<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>패스워드 검증</title>
		<link rel="stylesheet" href="../static/bootstrap-5.1.3/css/bootstrap.min.css" />
		<script src="../static/bootstrap-5.1.3/js/bootstrap.bundle.min.js"></script>
		<script src="../static/jquery/jquery-3.7.1.min.js"></script>
		
		<script type="text/javascript">
			function checkValidate(f){
				if(f.pass.value==""){
					alert("패스워드를 입력하세요");
					f.pass.focus();
					return false;
				}
			}
		</script>
		
	</head>
	<body>
	<div class="container">
		<h2>회원제 게시판 - 패스워드검증폼</h2>	
		<!-- 패스워드 검증에 실패했을때 에러메세지 출력용 -->
		<span style="color:red; font-size:1.8em;">
			${isCorrMsg }
		</span>	
		<form name="writeFrm" method="post" 
			action="./passwordAction.do"
			onsubmit="return checkValidate(this);">		
		<input type="hidden" name="idx" value="${idx }" />
		<input type="hidden" name="mode" value="${param.mode }" />
		<input type="hidden" name="nowPage" value="${param.nowPage }" />		
		<table class="table table-borderd" >
		<colgroup>
			<col width="25%"/>
			<col width="*"/>
		</colgroup>
		
		<tr>
			<td>패스워드</td>
			<td>
				<input type="password" name="pass" style="width:30%;" />
			</td>
		</tr>
		 
		<tr>
			<td colspan="2" align="center">
				<button type="submit">작성완료</button>
				<button type="reset">RESET</button>
				<button type="button" onclick="location.href='./list.do?nowPage=${param.nowPage}';">
					리스트바로가기
				</button>
			</td>
		</tr>
		</table>	
		</form>
	</div>
	
	</body>
</html>