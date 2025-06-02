<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
		<div>
			<h2>@RequestMapping 어노테이션의 속성들</h2>
			<script>
			//jQuery의 Entry point선언
			$(function(){
				//검색폼의 <form> 태그를 얻어와서 변수에 저장
				let f = document.searchFrm;
				//문서내 input태그중 type이 button인 엘리먼트를 클릭했을때 실행
				$('input[type=button]').click(function(){
					//입력된 검색어가 있는지 먼저 확인
					if(!f.searchWord.value){				
						//입력값이 없다면 경고창을 띄우고 실행을 중단한다. 
						alert('검색어를 입력하세요');
						f.searchWord.focus();
						return;
					}
					else{
						//여기서 this는 버튼 엘리먼트를 가리킨다. 
						if($(this).attr('id')=='btnGet'){					
							console.log("난 GET");
							//검색폼의 method속성에 get을 추가한 후 서브밋(전송)한다.
							$('#searchFrm').attr('method','get').submit();
						}
						else if($(this).attr('id')=='btnPost'){
							console.log("난 POST");	
							//post방식으로 전송한다. 
							$('#searchFrm').attr('method','post').submit();
						}
					}
				});
			});
			</script>
			
			<!-- form태그 작성시 method속성은 따로 부여하지 않고 jQuery에서
			부여한다. 그 외 속성도 동일하게 처리할 수 있다. -->
			<form action="../requestMapping/getSearch.do" name="searchFrm" id="searchFrm" >	
				<select name="searchColumn">
					<option value="title">제목</option>
					<option value="name">작성자</option>
					<option value="content">내용</option>
				</select>		
				<input type="text" name="searchWord" />
				<!-- 체크박스로 카테고리를 2개이상 선택할 수 있다.  -->
				<input type="checkbox" name="category" value="it" />IT		
				<input type="checkbox" name="category" value="pol" />정치
				<input type="checkbox" name="category" value="eco" />경제		
				<input type="checkbox" name="category" value="ent" />연예
				<br />		
				<input type="button" value="get검색" id="btnGet" />
				<input type="button" value="post검색" id="btnPost" />
			</form>	
		</div>
	</body>
</html>