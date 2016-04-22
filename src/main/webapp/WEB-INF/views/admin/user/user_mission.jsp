<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">
	
	$(document).ready(function() {
		aside.setActive(1,1);
	});
	
	function searchList(searchForm,page) {

		
	
		var userId = searchForm.userId.value;

		document.location.href = "/admin/user/user_mission.go?userId="+userId+"&page="+page;
		

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
				■ Home > Member Management > Member List 
			</header>
		
			<div class="contents-block">
			
				<h1>Member View Details</h1>

				<%@ include file="/WEB-INF/views/admin/user/user_detail.jsp"%>
						
				<div class="tab-bar">
					<button type="button" class="tab" onclick="document.location.href='/admin/user/user_view.go?userId=${user.userId}';"><span>Allergy</span></button>
					<button type="button" class="tab" onclick="document.location.href='/admin/user/user_diary.go?userId=${user.userId}';"><span>Diary</span></button>
					<button type="button" class="tab active" onclick="document.location.href='/admin/user/user_mission.go?userId=${user.userId}';"><span>Participation of Mission</span></button>
				</div>
				<form method="post" name="listForm" id="listForm" onsubmit="return searchList(this,1); return false;">
				<input type="hidden" name="userId" value="${user.userId }"></input>
				<ul class="detail-tab">
						<c:choose>	
										<c:when test="${list.size() > 0}">
											<c:forEach var="it" items="${list}" varStatus="i">
												<li class="imglist bbs">

													<a href="/admin/mission/mission_view.go?missionSeq=${it.missionSeq}">
														<div class="round-box" style="width:220px; padding:10px;">
															<table>
															<colgroup>
																<col width="60">
																<col width="*">
															</colgroup>
															<tr>
																
																<td>
																	<h2><b>${it.title} </b></h2>
																	<span class="good"><img src='/images/icon_userJoin.png' style="height:12px;"> ${it.countUser}</span>
																	<br> 
																
																	<span class="good"><img src='/images/icon_alarm.png' style="height:12px;">  ${fn:substring(it.missionPushTime,0,8)}</span>
																	<br>
																	[${it.missionPushDate1!=0 ? 'Mon':'' }
																	${it.missionPushDate2!=0 ? 'Tue':'' }
																	${it.missionPushDate3!=0 ? 'Wed':'' }
																	${it.missionPushDate4!=0 ? 'Thu':'' }
																	${it.missionPushDate5!=0 ? 'Fri':'' }
																	${it.missionPushDate6!=0 ? 'Sat':'' }
																	${it.missionPushDate7!=0 ? 'Sun':'' }]
																	<br>
																	${fn:substring(it.missionStartDate,0,16)} ~ ${fn:substring(it.missionEndDate,0,16)}
																	<br>
																	
																</td>
															</tr>
															</table>
															
															<hr>
															
															<div class="bbs-contents-preview">
																<c:choose>
																	<c:when test="${it.contentsText.length() > 90}">
																		${it.contentsText.substring(0,80)} ...
																	</c:when>
																	<c:otherwise>
																		${it.contentsText}
																	</c:otherwise>
																</c:choose>
																
															</div>
																  <div class="img-thumb">
																	   <c:forEach var="it2" items="${fileList}" varStatus="p">
																			<c:if test="${i.index eq p.index }">

																				<c:if test="${it2 !='' }">

																						<c:forEach var="it3" items="${it2.items}">
																						<%-- <c:set var="fileName" value="${fn:split(it3.thumb, '/')}" /> --%>
																								<div class="photo-talk" style="background-image:url('${it3.thumb}') "></div>
																						</c:forEach>
																					</c:if>

																			</c:if>
																		</c:forEach>
																	</div>
																	<hr>
															${fn:substring(it.regDate,0,16)}
					
														</div>
													</a>	
												</li>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<li style="height:100px; text-align:center; border-bottom:1px solid #ddd; padding-top:80px; "> No-Data ! </li>
										</c:otherwise>												
									</c:choose>
					
						</ul>
						<div style="clear:both;"></div>
							${paging}
					</form>
				</div>
				
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>