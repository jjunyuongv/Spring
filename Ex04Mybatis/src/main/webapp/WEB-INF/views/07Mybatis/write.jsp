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
	function writeValidate(f)
	{
		if(f.name.value==""){
			alert("작성자 이름을 입력하세요");
			f.name.focus();
			return false;
		}
		if(f.contents.value==""){
			alert("내용을 입력하세요");
			f.contents.focus(); 
			return false;
		} 
	}
	</script>
	
	<body>
	<div class="container">
		<h3>방명록(글쓰기) - 
			<small>Mybatis로 제작한 방명록입니다.</small></h3>
		
		<form name="writeFrm" method="post" 
			onsubmit="return writeValidate(this);"
			action="<c:url value="/mybatis/writeAction.do" />" >
			
		<table class="table table-bordered">
		<colgroup>
			<col width="20%"/>
			<col width="*"/>
		</colgroup>
		<tbody>
			<tr>
				<th class="text-center" 
					style="vertical-align:middle;">작성자</th>
				<td>
					<input type="text" class="form-control" 
						style="width:100px;" name="name" 
						value="${sessionScope.siteUserInfo.name }" />
				</td>
			</tr>
			<tr>
				<th class="text-center" 
					style="vertical-align:middle;">내용</th>
				<td>
					<textarea rows="10" class="form-control" 
					name="contents"></textarea>
				</td>
			</tr>	
		</tbody>
		</table>
		
		<div class="row text-center" style="">
			<!-- 각종 버튼 부분 -->
			
			<button type="submit" class="btn btn-danger">전송하기</button>
			<button type="reset" class="btn">Reset</button>
			<button type="button" class="btn btn-warning" 
				onclick="location.href='list.do';">리스트보기</button>
		</div>
		</form> 
	</div>
	
	</body>
</html>