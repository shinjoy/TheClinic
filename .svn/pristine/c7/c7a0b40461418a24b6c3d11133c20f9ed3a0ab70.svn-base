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
	
	function searchList(frm,page) {

		
		var missionSeq = frm.missionSeq1.value;
		

		document.location.href = "/admin/mission/mission_view.go?missionSeq="+missionSeq+"&page="+page;
		

		return false;
	}

	/* 
	function deleteGo(missionSeq,missionCommentSeq,userId) {
		if(confirm("삭제하시겠습니까?")) {
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
	 */
	
	
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
					<button type="button" class="tab active" onclick="document.location.href='/admin/mission/mission_view.go?missionSeq=${mission.missionSeq}&page=1';"><span>Comment</span></button>
					<button type="button" class="tab" onclick="document.location.href='/admin/mission/mission_member.go?missionSeq=${mission.missionSeq}';"><span>The number of people involved</span></button>
					
					
				</div>	
				<form method="post" name="listForm" id="listForm" onsubmit="return searchList(this,1); return false;">
				<input type="hidden" name="missionSeq1" value="${mission.missionSeq}">
				<div class="tab-list" >
				
					<ul class="reply-list">
				 	<c:choose>
						<c:when test="${list.size() > 0}">
					
							<c:forEach var="it" items="${list}" varStatus="i">
							
							
								<li>
									<dl style="padding-left:${(it.RLevel * 20) + 20}px;">
										<c:if test="${it.RLevel >0}">┕</c:if>
										<c:if test="${it.RLevel==0}">●</c:if>
										
										<dd>
														<c:forEach var="it2" items="${photo}" varStatus="p">
															<c:if test="${i.index eq p.index }">

																<c:choose>
																	<c:when test="${it2 !='' }">

																		<c:forEach var="it3" items="${it2.items}"
																			varStatus="s">


																			<div class="photo-list"
																				style="background-image:url('${it3.thumb}') "></div>


																		</c:forEach>
																	</c:when>
																	<c:otherwise>
																		<div class="photo-list"
																			style="background-image: url('/images/user_default.png')"></div>
																	</c:otherwise>
																</c:choose>

															</c:if>
														</c:forEach>
														${it.userName} (${it.userId})<br>
											${fn:substring(dl.regDate,0,16)}<button style="margin-left:10px;" type="button" class="btn-red" onclick="deleteGo(${mission.missionSeq},${it.missionCommentSeq},'${it.userId}');">Delete</button>
											<div style="margin:10px 0;">
												${it.missionContents}
												<c:choose>
													
													<c:when test="${fileList2.size() > 0 }">
																<c:forEach var="it2" items="${fileList2}" varStatus="p">
																	<c:if test="${i.index eq p.index }">	
																		<td><c:if test="${it2 !='' }">
																				<c:forEach var="it3" items="${it2.items}" varStatus="s">
																					<div class="photo-talk2" style="background-image:url('${it3.file}')" onclick="pop.zoom('${it3.file}')"></div>
																				</c:forEach>
																		</c:if></td>
																	</c:if>	
																</c:forEach>
													</c:when>
													
												</c:choose>
																							
											</div>
										</dd>
									</dl>
								</li>
							</c:forEach>
					
						</c:when>
						<c:otherwise>
							<li style="height:100px; padding-top:80px; text-align: center;"> No-Data !</li>
						</c:otherwise>
					</c:choose> 
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