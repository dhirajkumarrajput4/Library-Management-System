<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@include file="components/All_Js_css.jsp"%>
</head>
<body>
	<!--navbar-->
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@include file="components/nav.jsp"%>

	<div class="container mt-2"
		style="background-color: #f7f8f9; height: 560px;">

		<div class="card"
			style="color: white; background: rgb(188, 230, 199); font-size: 21px;">
			<div class="card-header">
				<h4 style="position: absolute;">Issue Books</h4>
				<div class="container text-right" style="position: relative;">
					<a href="${pageContext.request.contextPath}/"
						class="btn btn-outline-secondary">Home</a> <a
						href="${pageContext.request.contextPath}/"
						class="btn btn-outline-secondary">Back</a>
				</div>
			</div>

			<div class="card-body background">
				<c:if test="${student.studentid==0}">
					<form action="${pageContext.request.contextPath}/getStudent">
						<div class="form-group">
							<label for="Student id">Student ID</label> <input
								name="studentid" class="form-control"
								placeholder="Search Student" /> <input name="bookid"
								class="form-control" type="hidden"
								placeholder="Search Student by Id" value="${book.bookid}" />
						</div>
						<div class="container text-center mt-2">

							<button type="submit" class="btn btn-success">Search</button>
						</div>
					</form>
				</c:if>
				<c:if test="${student.studentid!=0}">
					<b>ID:</b>  ${student.studentid}<br>
					<b>Name:</b>	${student.studentname}<br>
					<b>Father's Name:</b> 	${student.fathername}<br>
					<b>Collage:</b>		${student.collage}<br>
				</c:if>
				<h3>
					Issue Book:<span style="color: red;"> ${book.booktitle}</span>
				</h3>


				<form action="${pageContext.request.contextPath}/issue">
					<div class="form-group">
						<label for="Student id">Student ID</label> <input name="studentid"
							class="form-control" placeholder="Search Student" value=${student.studentid}/>
						 <input
							name="bookid" class="form-control" type="hidden"
							placeholder="Search Student by Id" value="${book.bookid}" />
					</div>
					<div class="container text-center mt-2">

						<button type="submit" class="btn btn-success">Issue Now</button>
					</div>
				</form>
			</div>
		</div>


	</div>

</body>
</html>