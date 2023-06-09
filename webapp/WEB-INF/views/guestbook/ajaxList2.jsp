<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>
	
</head>

<body>
	<div id="wrap">
        <c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->

		<c:import url="/WEB-INF/views/include/nav.jsp"></c:import>
		<!-- //nav -->

		<div id="aside">
			<h2>방명록</h2>
			<ul>
				<li>일반방명록</li>
				<li><a href="${pageContext.request.contextPath}/api/guestbook/addList">ajax방명록</a></li>
			</ul>
		</div>
		<!-- //aside -->

		<div id="content">
			
			<div id="content-head">
            	<h3>일반방명록</h3>
            	<div id="location">
            		<ul>
            			<li>홈</li>
            			<li>방명록</li>
            			<li class="last">일반방명록</li>
            		</ul>
            	</div>
                <div class="clear"></div>
            </div>
            <!-- //content-head -->

			<div id="guestbook">
				<%-- <form action="${pageContext.request.contextPath}/api/guestbook/add" method="get"> --%>
					<table id="guestAdd">
						<colgroup>
							<col style="width: 70px;">
							<col>
							<col style="width: 70px;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th><label class="form-text" for="input-uname">이름</label></td>
								<td><input id="input-uname" type="text" name="name"></td>
								<th><label class="form-text" for="input-pass">패스워드</label></td>
								<td><input id="input-pass" type="password" name="password"></td>
							</tr>
							<tr>
								<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
							</tr>
							<tr class="button-area">
								<td colspan="4"><button id="btnSubmit" type="submit">등록</button></td>
							</tr>
						</tbody>
						
					</table>
					<!-- //guestWrite -->
					<input type="hidden" name="action" value="add">
					
				<!-- </form>	 -->
				<div id="guestbookListArea">
					출력
				</div>	
				<!-- //guestRead -->
				
			</div>
			<!-- //guestbook -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>
		
        <c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->
	
	
	<!-- 모달창 -->
	<!-- Button trigger modal -->

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">삭제 모달창</h4>
      </div>
      <div class="modal-body">
        <input id="modalPassword" type="password" name=""><br>
        <input id="modalNo" type="text" name="no">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
        <button id="btnDel" type="button" class="btn btn-danger">삭제</button>
      </div>
    </div>
  </div>
</div>
	

</body>

<script type="text/javascript">

$(document).ready(function(){
	//전체리스트 호출 가져오기
	fetchList();

});	
function fetchList(){
$.ajax({
		
		url : "${pageContext.request.contextPath }/api/guestbook/list",		
		type : "post",

		dataType : "json",
		success : function(jsonResult){
			
			var guestList = jsonResult.data;
			for(var i=0; i<guestList.length; i++){
				render(guestList[i],"down");
				
			}
		},
		error : function(XHR, status, error) { 
			console.error(status + " : " + error);
		}
    });
};	

function render(guestVo,dir){
	
	var str ="";
	str += '<table id="t-'+guestVo.no+'"class="guestRead">';
	str += '  <colgroup>';
	str += '	  <col style="width: 10%;">';
	str += '	  <col style="width: 40%;">';
	str += '	  <col style="width: 40%;">';
	str += '	  <col style="width: 10%;">';
	str += '  </colgroup>';
	
	str += '  <tr>';
	str += '      <td>' + guestVo.no +'</td>';
	str += '      <td>' + guestVo.name + '</td>';
	str += '      <td>' + guestVo.regDate + '</td>';
	str += '      <td><button type="button" class="btn btn-primary btn-sm btnModal" data-delno="'+guestVo.no+'">삭제</button></td>';
	str += '  </tr>';

	str += '  <tr>';
	str += '      <td colspan=4 class="text-left">' + guestVo.content + '</td>';
	str += '  </tr>';
	str += '</table>';
	
	if(dir == 'up'){
		$("#guestbookListArea").prepend(str);
	}
	else if (dir == 'down'){
		$("#guestbookListArea").append(str);
	}
	else{
		console.log("ㅈㅈ");
	}
	
}
	
	





$("#btnDel").on("click",function(){
	console.log("Dd");
	
	//서버에 데이터 보내기
	
	//password, no --> 모달창
	
	//데이터 모이기
	var password = $("#modalPassword").val();
	var no= $("#modalNo").val();
	//객체로 만들기
	var guestVo={
			password:password,
			no:no
	};
	console.log(guestVo);
//요청
$.ajax({
		
		url : "${pageContext.request.contextPath }/api/guestbook/remove",		
		type : "post",
		data : guestVo,

		dataType : "json",
		success : function(jsonResult){
			console.log(jsonResult)
			if(jsonResult.data>0){
				//화면에서 지우기
				$("#t-"+guestVo.no).remove();
				$("#myModal").modal("hide");
				//성공시 처리해야될 코드
				}
			else{
				alert("비밀번호가 틀렸습니다.");
			}
			
			
		},
		error : function(XHR, status, error) { 
			console.error(status + " : " + error);
		}
    });
    
    console.log("test입니다. "+guestVo.no);
});
//삭제 모달창 호출 버튼
$("#guestbookListArea").on("click",".btnModal",function(){
	console.log("모달창 버튼");
	//초기화
	$("#modalPassword").val("");
	$("#modalNo").val("");
	//방명록 글번호 input창
	var no =$(this).data("delno");	
	console.log(no);

	//모달창 input 태그에 no값 넣기
	$("#modalNo").val(no);
	//모달창 호출
	$("#myModal").modal("show");
});



//방명록 저장 버튼 클릭할때
$("#btnSubmit").on("click", function(){
	console.log("버튼클릭");
	
	//데이타 수집
	var name = $("[name='name']").val();
	var password = $("[name='password']").val();
	var content = $("[name='content']").val();
	
	var guestbookVo ={
	    name: name,
	    password: password,
	    content: content
	};
	

	
	//ajax통신 ->요청은 같은 기술 응답이 데이터만 온다
	$.ajax({
		
		url : "${pageContext.request.contextPath }/api/guestbook/add2",		
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(guestbookVo),

		dataType : "json",
		success : function(jsonResult){
			/*성공시 처리해야될 코드 작성*/
			console.log(jsonResult);
			
			if(jsonResult.result == "success"){
				//정상처리
				
				render(jsonResult.data); //리스트에 추가
				
				//등록폼 비우기
				$("[name='name']").val("");
				$("[name='password']").val("");
				$("[name='content']").val("");
			}else{
				//오류처리
			}

		},
		error : function(XHR, status, error) { 
			console.error(status + " : " + error);
		}
    });
	
	
	//방명록 리스트 그리기
	function render(guestVo){
		
		var str ="";
		str += '<table id="t-'+guestVo.no+'"class="guestRead">';
		str += '  <colgroup>';
		str += '	  <col style="width: 10%;">';
		str += '	  <col style="width: 40%;">';
		str += '	  <col style="width: 40%;">';
		str += '	  <col style="width: 10%;">';
		str += '  </colgroup>';
		
		str += '  <tr>';
		str += '      <td>' + guestVo.no +'</td>';
		str += '      <td>' + guestVo.name + '</td>';
		str += '      <td>' + guestVo.regDate + '</td>';
		str += '      <td><button type="button" class="btn btn-primary btn-sm btnModal" data-delno="'+guestVo.no+'">삭제</button></td>';
		str += '  </tr>';

		str += '  <tr>';
		str += '      <td colspan=4 class="text-left">' + guestVo.content + '</td>';
		str += '  </tr>';
		str += '</table>';
		
		$("#guestbookListArea").prepend(str);
	}
	
	
	
});

</script>
</html>