<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>uploadForm</title>
		<link rel="stylesheet" href="../static/bootstrap-5.1.3/css/bootstrap.min.css" />
		<script src="../static/bootstrap-5.1.3/js/bootstrap.bundle.min.js"></script>
		<script src="../static/jquery/jquery-3.7.1.min.js"></script>
	</head>
	<body>
	<div class="container">
	    <h2>파일업로드 폼</h2>
	    <!--  
	 	파일 업로드를 위한 <form>태그 구성
	 	1.전송방식은 post로 지정해야한다. 
	 	2.enctype(인코딩방식)은 multipart/form-data로 지정해야한다.
	 	-->
	    <form name="fileFrm" method="post" action="uploadAction.do" 
	               enctype="multipart/form-data">
	   	 
	    <table class="table table-bordered" style="width:500px;">
	   	 <colgroup>
	   		 <col width="20%" />
	   		 <col width="*" />   		 
	   	 </colgroup>
	   	 <tr>
	   		 <th>제목</th>
	   		 <td>
	   			 <input type="text" name="title"
	   				 value="제목입니다." />
	   		 </td>   		 
	   	 </tr>
	   	 <tr>
	   		 <th>첨부파일1</th>
	   		 <td>
	   			 <input type="file" name="userfile1" />
	   		 </td>   		 
	   	 </tr>
	   	 <tr>
	   		 <th>첨부파일2</th>
	   		 <td>
	   			 <input type="file" name="userfile2" />
	   		 </td>   		 
	   	 </tr>
	   	 <tr>
	   		 <td colspan="2" style="text-align:center;">
	   			 <button type="submit" class="btn btn-danger">파일업로드</button>
	   		 </td>   		 
	   	 </tr>
	    </table>
	    </form>
	</div>
	</body>

</html>