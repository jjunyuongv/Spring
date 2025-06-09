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
		
		<style type="text/css">
		a{ text-decoration: none; color: black; }
     	a:hover { text-decoration: underline; color: blue; }
		</style>
	</head>
	<body>
	<div class="container">	
		<h2>비회원제 답변형 게시판</h2>
		<h3>전체페이지:${totalPage } / 현제페이지:${nowPage }</h3>
	
		<!-- 검색폼 -->
		<form method="get">
		<table class="table table-borderd" >
		<tr>
			<td align="center">
				<select name="searchColumn">
					<option value="title">제목</option>
					<option value="contents">내용</option>
				</select>
				<input type="text" name="searchWord" />
				<input type="submit" value="검색하기" />
			</td>
		</tr>	
		</table>	
		</form>	
		<!-- 글쓰기버튼 -->
		<table class="table table-borderd" width="90%">
			<tr>
				<td align="right">
					<button type="button" 
					onclick="location.href='./write.do';">글쓰기</button>
				</td>
			</tr>
		</table>	
		<!-- table>tr*2>td*5 -->
		<table class="table table-borderd" width="90%">
			<tr>
				<th width="10%">번호</th>
				<th width="*">제목</th>
				<th width="15%">작성자</th>
				<th width="10%">조회수</th>
				<th width="15%">작성일</th>
				<!-- <th width="5%">첨부</th> -->
			</tr> 
			<c:choose>
				<c:when test="${empty listRows }">
					<tr>
						<td colspan="6" class="text-center">
							등록된 게시물이 없습니다 ^^*
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${listRows }" var="row" 
						varStatus="loop">
						<!-- 리스트반복시작 -->
						<tr>
							<td class="text-center">${row.virtualNum }</td>
							<td class="text-left">
								<a href="./view.do?idx=${row.idx}
									&nowPage=${nowPage}">${row.title}</a>
							</td>
							<td class="text-center">${row.name }</td>
							<td class="text-center">${row.hits }</td>
							<td class="text-center">${row.postdate }</td>
							<!-- <td class="text-center">--</td> -->
						</tr>
						<!-- 리스트반복끝 -->
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		<table class="table table-borderd">
			<tr>
				<td align="center">
					${ pagingImg }
				</td>
			</tr>
		</table>
	</div>
	
	</body>
</html>