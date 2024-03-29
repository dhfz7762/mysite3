<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">
        <c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		
		<c:import url="/WEB-INF/views/include/nav.jsp"></c:import>
		<!-- //nav -->

		<div id="aside">
			<h2>게시판</h2>
			<ul>
				<li><a href="">일반게시판</a></li>
				<li><a href="">댓글게시판</a></li>
			</ul>
		</div>
		<!-- //aside -->

		<div id="content">

			<div id="content-head">
				<h3>게시판</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>게시판</li>
						<li class="last">일반게시판</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="board">
				<div id="list">
					<form action="${pageContext.request.contextPath}/board/list" method="get">
						<div class="form-group text-right">
							<input type="text" name="keyword">
							<button type="submit" id=btn_search >검색</button>
						</div>
					</form>
					<table >
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>글쓴이</th>
								<th>조회수</th>
								<th>작성일</th>
								<th>관리</th>
							</tr>
						</thead>
						
						<c:forEach items="${boardList}" var="boardVo">
						<tbody>
							<tr>
								<td>${boardVo.no}</td>
								<td class="text-left"><a href="${pageContext.request.contextPath}/board/modifyForm?no=${boardVo.no}">${boardVo.title}</a></td>
								<td>${boardVo.user_name}</td>
								<td>${boardVo.hit}</td>
								<td>${boardVo.regDate} / 글작성자번호:${boardVo.user_no}/ 세션:${sessionScope.authUser.no}</td>
								<td>
								    <!-- 글작성자 번호 세션의 사용자번호 같으면 삭제버튼이 보인다 -->
								    <c:if test='${boardVo.user_no == sessionScope.authUser.no}'>
								    <a href="${pageContext.request.contextPath}/board/delete?no=${boardVo.no}">[삭제]</a></td>
								    </c:if>
								</td>
							</tr>
						</tbody>
						</c:forEach>
					</table>
					<div id="paging">
						<ul>
							<li><a href="">◀</a></li>
							<li><a href="">1</a></li>
							<li><a href="">2</a></li>
							<li><a href="">3</a></li>
							<li><a href="">4</a></li>
							<li class="active"><a href="">5</a></li>
							<li><a href="">6</a></li>
							<li><a href="">7</a></li>
							<li><a href="">8</a></li>
							<li><a href="">9</a></li>
							<li><a href="">10</a></li>
							<li><a href="">▶</a></li>
						</ul>
						
						
						<div class="clear"></div>
					</div>
			    <c:choose>
			        <c:when test="${sessionScope.authUser!=null}">
					<a id="btn_write" href="${pageContext.request.contextPath}/board/writeForm">글쓰기</a>
					</c:when>
				</c:choose>
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

        <c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

</html>