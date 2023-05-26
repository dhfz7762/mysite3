<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css"
	rel="stylesheet" type="text/css">

</head>
<script>
	function selChange() {
		var sel = document.getElementById('cntPerPage').value;
		location.href = "list?nowPage=${paging.nowPage}&cntPerPage=" + sel;
	}
</script>

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
			<div style="float: right;">
				<select id="cntPerPage" name="sel" onchange="selChange()">
					<option value="5"
						<c:if test="${paging.cntPerPage == 5}">selected</c:if>>5줄
						보기</option>
					<option value="10"
						<c:if test="${paging.cntPerPage == 10}">selected</c:if>>10줄
						보기</option>
					<option value="15"
						<c:if test="${paging.cntPerPage == 15}">selected</c:if>>15줄
						보기</option>
					<option value="20"
						<c:if test="${paging.cntPerPage == 20}">selected</c:if>>20줄
						보기</option>
				</select>
			</div>
			<!-- 옵션선택 끝 -->

			<div id="board">
				<div id="list">
					<form action="search" method="post">
						<div class="form-group text-right">
							<select name="selectOption">
								<option value="username">작성자</option>
								<option value="title">제목</option>
							</select> <input type="text" name="searchText">
							<button type="submit" id=btn_search>검색</button>
						</div>
					</form>
					<table>
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
						<c:forEach items="${boardList}" var="BoardVo">
							<tbody>
								<tr>
									<td>${BoardVo.no}</td>
									<td class="text-left"><a
										href="${pageContext.request.contextPath}/board/read?no=${BoardVo.no}&user_no=${BoardVo.user_no}">${BoardVo.title}</a></td>
									<td>${BoardVo.username}</td>
									<td>${BoardVo.hit}</td>
									<td>${BoardVo.regDate}</td>
									<c:if test="${BoardVo.user_no == sessionScope.authUser.no}">
										<td><a
											href="${pageContext.request.contextPath}/board/delete?no=${BoardVo.no}">[삭제]</a></td>
									</c:if>
								</tr>
							</tbody>
						</c:forEach>
					</table>

					<div style="display: block; text-align: center;">
						<c:if test="${paging.startPage != 1 }">
							<a href="${pageContext.request.contextPath}/board/list?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">&lt;</a>
						</c:if>
						<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
							<c:choose>
								<c:when test="${p == paging.nowPage }">
									<b>${p }</b>
								</c:when>
								<c:when test="${p != paging.nowPage }">
									<a
										href="${pageContext.request.contextPath}/board/list?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a>
								</c:when>
							</c:choose>
						</c:forEach>
						<c:if test="${paging.endPage != paging.lastPage}">
							<a href="${pageContext.request.contextPath}/board/list?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a>
						</c:if>
					


					<div class="clear"></div>
				</div>
				<c:if test="${sessionScope.authUser.no != null }">
					<a id="btn_write" href="${pageContext.request.contextPath}/board/writeForm">글쓰기</a>
				</c:if>
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
