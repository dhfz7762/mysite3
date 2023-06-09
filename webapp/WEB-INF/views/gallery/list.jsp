<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->

		<!-- nav -->
		<c:import url="/WEB-INF/views/include/nav.jsp"></c:import>
		<!-- //nav -->

		<c:import url="/WEB-INF/views/include/galleryAside.jsp"></c:import>
		<!-- //aside -->


		<div id="content">

			<div id="content-head">
				<h3>갤러리</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>갤러리</li>
						<li class="last">갤러리</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->


			<div id="gallery">
				<div id="list">

					<c:choose>
						<c:when test="${sessionScope.authUser!=null}">
							<button id="btnImgUpload">이미지올리기</button>
						</c:when>
					</c:choose>
					<div class="clear"></div>


					<ul id="viewArea">

						<!-- 이미지반복영역 -->
						<c:forEach items="${GalleryList}" var="GalleryVo">
						<div id="t-${GalleryVo.no}">
								<div class="view">
								<input type="hidden" id="delno" value="">
									<img class="imgItem" data-num="${GalleryVo.no}" data-delno="${GalleryVo.user_no}" data-img="${pageContext.request.contextPath }/upload/${GalleryVo.saveName}" src="${pageContext.request.contextPath }/upload/${GalleryVo.saveName}">
									<div class="imgWriter">
										작성자: <strong>${GalleryVo.username}</strong>
									</div>
								</div>
						    </div>
						</c:forEach>
						<!-- 이미지반복영역 -->


					</ul>
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



	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지등록</h4>
				</div>

				<form method="post" action="upload" enctype="multipart/form-data">
					<div class="modal-body">
					<input type="hidden" id="" name="user_no" value="${sessionScope.authUser.no}">
						<div class="form-group">
							<label class="form-text">글작성</label> <input id="addModalContent" type="text" name="text" value="">
						</div>
						<div class="form-group">
							<label class="form-text">이미지선택</label> <input id="file" type="file" name="file" value="">
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
				</form>


			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->



	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">

					<div class="formgroup">
						<img id="viewModelImg" src="">
					</div>

					<div class="formgroup">
						<p id="viewModelContent"></p>
					</div>

				</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						<input id="modalNo" type="hidden" name="no">
						<input id="modalNum" type="hidden" name="no">
					</div>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->


</body>

<script type="text/javascript">
$("#btnImgUpload").on("click", function(){
	$('button.btnDel').remove();
	$("#addModal").modal("show");
});

$("#viewArea").on("click",".imgItem",function(){
	$('button.btnDel').remove();
	var no =$(this).data("delno");
	var num =$(this).data("num");
	var img=$(this).data("img");
	$("#modalNo").val(no);
	$("#modalNum").val(num);
	if(no==${sessionScope.authUser.no}){
		var str ="";
		str += '<button type="button" class="btn btn-danger btnDel" id="btnDel">삭제</button>';
		$(".modal-footer").append(str);
	}
	$("#viewModelImg").attr("src", img);
	$("#viewModal").modal("show");
});

$(".modal-footer").on("click","#btnDel",function(){	
	var no= $("#modalNum").val();
	//객체로 만들기
	var galleryVo = {
			no : no
	};
	console.log(galleryVo);
//요청
$.ajax({
		
		url : "${pageContext.request.contextPath }/gallery/remove",		
		type : "post",
		data : galleryVo,

		dataType : "json",
		success : function(jsonResult){
			console.log(jsonResult)
			if(jsonResult.data>0){
				$("#t-"+galleryVo.no).remove();
				$("#viewModal").modal("hide");
				//성공시 처리해야될 코드
				}
			else{
				alert("오류");
			}
			
			
		},
		error : function(XHR, status, error) { 
			console.error(status + " : " + error);
		}
    });
});
</script>




</html>

