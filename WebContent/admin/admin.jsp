<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
		
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理 | MyBlog</title>


<!-- Bootstrap core CSS -->
<link	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="signin.css" rel="stylesheet">

<link type="text/css" rel="stylesheet" href="./css/public.css" />
<link type="text/css" rel="stylesheet" href="./css/admin.css" />

<script src="./js/admin.js"></script>

</head>
<body>
	<div class="head_line"></div>
	<div class="container" id="main">
				
		<div id="header">
			<div>
				<h2><a href="/Blog/login.html">MyBlog-管理</a></h2>
				<h5 class="text-muted">有点粗糙 但能用就行</h5>
				</div>		
		</div>
				 
				 <div class="admin_div">
				<h4 class="btn btn-default">统计</h4>
				<h5> 被访问了 ${visited } 次</h5>
				<h5> 一共有 ${member}个访问者</h5>    
				
				</div>
				<div class="admin_div">
				<h4 class="btn btn-default">管理日志</h4>
				
				<c:forEach var="a" items="${articles}">				
				<div class="list-group-item">						
						<span>${a.title}</span>
						 <div class="r_div">	
						 <span>${a.time}</span>
						 
						 <a href="/Blog/AdminDataServlet?op=edit_article&&article_id=${a.id}">
						 <button class="btn btn-default">&nbsp;<span class="glyphicon glyphicon-pencil" style="color:#5bc0de" >编辑</span>&nbsp;</button>
						 </a>
						 
						 <button class="btn btn-default">&nbsp;
						 <span class="glyphicon glyphicon-trash" style="color:#d9534f" onclick="delete_article(this,'${a.id}')"> 删除</span>&nbsp;</button>
						 </div>										 
				</div>							
				</c:forEach>					
				</div>
				
				<div class="admin_div">
				<h4 class="btn btn-default">管理分类</h4><h5 style="color:#d9534f" >删除分类会删除所有的文章</h5>
				<c:forEach var="s" items="${sort}">				
				<div class="list-group-item">						
						<input type="text"  class="sort" value="${s}"   disabled="disabled" style="border:0px;"  >
						 <div class="r_div">							
						 <button class="btn btn-default">&nbsp;<span class="glyphicon glyphicon-pencil" style="color:#5bc0de" onclick="edit_sort(this,'${s}')">编辑</span>&nbsp;</button>
						 <button class="btn btn-default">&nbsp;<span class="glyphicon glyphicon-trash" style="color:#d9534f" onclick="delet_sort(this,'${s}')">删除</span>&nbsp;</button>
						 </div>										 
				</div>							
				</c:forEach>
				</div>
				
				<div class="admin_div">
				<h4 class="btn btn-default">管理标签</h4>
				<c:forEach var="t" items="${tags}">				
				<div class="list-group-item">						
						<input type="text" class="tags"  value="${t.tag}"  disabled="disabled"  style="border:0px">
						 <div class="r_div">	
						 <button class="btn btn-default">&nbsp;<span class="glyphicon glyphicon-pencil" style="color:#5bc0de" onclick="edit_tag(this,'${t.tag}')" >编辑</span>&nbsp;</button>
						 <button class="btn btn-default">&nbsp;<span class="glyphicon glyphicon-trash" style="color:#d9534f" onclick="delet_tag(this,'${t.tag}')">删除</span>&nbsp;</button>
						 </div>										 
				</div>							
				</c:forEach>
				
				</div>
			
				
				



		<div class="foot_line"></div>		
		</div><!-- container -->		
		
		
		
		
		
		
	<div id="footer">		
		<a href="#">&nbsp;&nbsp;MyBlog</a>	
	</div> 		
	
</body>
</html>