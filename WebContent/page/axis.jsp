<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>时间轴 | MyBlog</title>

<!-- Bootstrap core CSS -->
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="signin.css" rel="stylesheet">

<!-- 引入页面公共样式 -->
<link type="text/css" rel="stylesheet" href="/Blog/css/public.css" />
<!-- 引入本页面的特殊样式 -->
<link type="text/css" rel="stylesheet" href="/Blog/css/axis.css" />

</head>
<body>
	<div class="head_line"></div>

	<div class="container" id="main">

		<div id="header"></div>

		<div class="row c_center">
			<div class="col-md-3" id="left_content">

				<div id="title">
					<h2>
						<a href="/Blog/index.jsp">MyBlog</a>
					</h2>
					<h5 class="text-muted">Winner Winner Chicken Dinner!</h5>
				</div>

				<div class="c_center" id="person_info">
					<img src="/Blog/img/header.jpg" height="130" width="130"
						alt="what?" class="img-circle">
					<h5 class="text-muted">Chicken Dinner Again!</h5>
				</div>

				<div id="list">
					<table class="table table-hover c_center">
						<tr>
							<td><a href="/Blog/index.jsp	"><span class="glyphicon glyphicon-home"></span>
								&nbsp;&nbsp;首页</a></td>
						</tr>
						<tr>	
							<td><a href="/Blog/SortServlet?get=all"><span class="glyphicon glyphicon-list"></span>
								&nbsp;&nbsp;分类</a></td>
						</tr>
						<tr>
							<td><a href="/Blog/TagsServlet?get=all"><span class="glyphicon glyphicon-tags"></span>
								&nbsp;&nbsp;标签</a></td>
						</tr>						
						<tr>
							<td class="active"><a href="/Blog/AxisServlet"><span class="glyphicon glyphicon-book"></span>
								&nbsp;&nbsp;时间轴</a></td>
						</tr>
						<tr>
							<td><a href="/Blog/page/about.html"><span class="glyphicon glyphicon-user"></span>
								&nbsp;&nbsp;关于</a></td>
						</tr>
					</table>
				</div>
				<!-- list -->
				<br />
			</div>
			<div class="col-md-2" id="center_content"></div>
			
			<div class="col-md-7 " id="axis_div" >
				<!-- 这里初始化时间轴 -->		
				<c:forEach var="axis" varStatus="status" items="${axis_list}">
					<c:choose>
						<c:when test="${axis.id==0}">
							<div class="longline_div">
								<div> ${axis.year}</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="line_div">
								<div>
									<a href="/Blog/ArticleServlet?id=${axis.id}">&nbsp;&nbsp;${axis.month}-${axis.day}
									&nbsp;&nbsp;&nbsp;&nbsp;${axis.title}
									</a>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<!-- 初始化结束 -->
			</div>
			
		</div>
		
		<div class="foot_line"></div>
	</div>	
	<!-- container -->
	<div id="footer">
		<a href="#">&nbsp;&nbsp;MyBlog</a>
	</div>
	<!-- footer -->
</body>
</html>