<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<script type="text/javascript">
	
	$(document).ready(function() {
		aside.setActive(8,1);
		
	});
	
	function searchList(searchForm,page) {

		
		var missionSeq = searchForm.missionSeq.value;
		

		document.location.href = "/admin/mission/mission_member.go?missionSeq="+missionSeq+"&page="+page;
		

		return false;
	}

	
	function deleteGo(missionSeq,missionCommentSeq,userId) {
		if(confirm("Are you sure you want to delete?")) {
			var param = {
					missionCommentSeq	:	missionCommentSeq,
					missionSeq : missionSeq,
					userId : userId
				
			};
			
			$.ajax({
				type:"POST",
				url :"/m/mission_delete.go",
				dataType:"json",
				data:param,
				success:function(json){
					alert(json.message);
					if (json.result) {
					
						if(json.type!="1"){
							document.location.reload();
						}else{
							document.location.href="/admin/mission/mission.go";
						}
						
					}
				}
			});
		}
		return false;

	}
	
	
	
</script>

<style>
.tbl-list TD{
	border-bottom: 1px solid #ddd;
	padding: 5px;
}
</style>
</head>
<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ Home > Mission Management > Mission List
			</header>
		
			<div class="contents-block">
			
				<h1>Mission View Details</h1>
				
				<%@ include file="/WEB-INF/views/admin/mission/mission_detail.jsp"  %>
				<div class="tab-bar">
					<button type="button" class="tab" onclick="document.location.href='/admin/mission/mission_view.go?missionSeq=${mission.missionSeq}';"><span>Comment</span></button>
					<button type="button" class="tab  active" onclick="document.location.href='/admin/mission/mission_member.go?missionSeq=${mission.missionSeq}';"><span>The number of people involved</span></button>
					
					
				</div>	
				<form method="post" name="listForm" id="listForm" onsubmit="return searchList(this,1); return false;">
				<div class="tab-list" >
				
					<ul class="reply-list">
								<table class="list">
									<colgroup>
										<col width="5%">
										<col width="10%">
										<%-- <col width="10%"> --%>
										<col width="5%">
										<col width="10%">
										<col width="10%">
									
									</colgroup>

									<tbody class="rl">
										<c:choose>
											<c:when test="${list.size() > 0}">
													<th>Image</th>
													<th>ID</th>
													<!-- <th>cellphone</th> -->
													<th>Name</th>
													<th>Age</th>
													<th>Gender</th>
													<th>Registration Date</th>
													
													
												<c:forEach var="it" items="${list}" varStatus="i">
													
													<tr
														onclick="location.href='/admin/user/user_view.go?userId=${it.userId}';">
														
														<td style="padding-left:10px;">
														<c:forEach var="it2" items="${photo}" varStatus="p">
															<c:if test="${i.index eq p.index }">
					
																<c:choose>
																    <c:when test="${it2 !='' }">
					
																		<c:forEach var="it3" items="${it2.items}" varStatus="s">
					
																			
																				<div class="photo-list"
																					style="background-image:url('${it3.thumb}') "></div>
																		
																		
																		</c:forEach>
																	</c:when>
																	<c:otherwise>	
																		<div class="photo-list" style="background-image:url('/images/user_default.png')"></div> 
													 				 </c:otherwise>
													 			</c:choose>
					
															</c:if>
														</c:forEach>
														</td>														
														<td class="text-left">
															<%-- <a href="/admin/user/user_view.go?userId=${it.userId}"> --%>${it.userId}</td>
													<%-- 	<td class="text-left">${it.phoneNumber}</td> --%>
														<td class="text-left">
															<%-- <a href="/admin/user/user_view.go?userId=${it.userId}"> --%>${it.userName}</td>
														<td>${it.userAge}</td>
														<td>${it.genderText}</td>
													
														<td>${fn:substring(it.regDate,0,16)}</td>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<td colspan="10" style="height: 200px;"> No-Data ! </td>
											</c:otherwise>

										</c:choose>
									</tbody>
								</table>
							</ul>
					${paging}
				</div>	
				</form>
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>