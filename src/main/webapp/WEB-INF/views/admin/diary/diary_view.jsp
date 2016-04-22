<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<script type="text/javascript">
	
	$(document).ready(function() {
		aside.setActive(2,1);
		
	});
	
	function searchList(searchForm,page) {

		
		var bbsSeq = searchForm.bbsSeqq.value;
		var userId = searchForm.userId.value;

		document.location.href = "/admin/diary/diary_view.go?userId="+userId+"&bbsSeq="+bbsSeq+"&page="+page;
		

		return false;
	}
	
	
	function deleteGo(bbsSeq,bbsCommentSeq,userId) {
		if(confirm("Are you sure you want to delete ?")) {
			var param = {
					bbsCommentSeq	:	bbsCommentSeq,
					bbsSeq : bbsSeq,
					userId : userId,
				
			};
			
			$.ajax({
				type:"POST",
				url :"/m/bbs_delete.go",
				dataType:"json",
				data:param,
				success:function(json){
					alert(json.message);
					if (json.result) {
						if(json.type!=1){
							document.location.reload();
						}else{
							document.location.href="/admin/diary/diary.go";
						}
						
					}
				}
			});
		}
		return false;

	}
	
</script>

<style>
.tbl-list TD {
	border-bottom: 1px solid #ddd;
	padding: 5px;
}
</style>
</head>
<body>

	<%@ include file="/WEB-INF/views/admin/admin_header.jsp"%>

	<section class="main-cover main-row">
		<section id="main">

			<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"%>

			<section id="contents">
				<header class="panel"> ■ Home > Diary Management > Diary
					List </header>

				<div class="contents-block">

					<h1>Diary View Details</h1>

					<div style="text-align: right; margin-bottom: 10px;">
						<button type="button" class="btn"
							onclick="document.location.href='/admin/diary/diary.go';"
							style="width: 100px; background-color: #ae4;">Back</button>
					</div>
					<form method="post" name="searchForm" id="searchForm"
						onsubmit="return searchList(this,1); return false;">
						<input type="hidden" name="bbsSeqq" value="${bbs.bbsSeq}">
						<input type="hidden" name="userId" value="${USER_ID}">
						<table class="register">
							<tr>

								<th>ID</th>
								<td>${bbs.userId}</td>
								<th>Registration Date</th>
								<td>${fn:substring(bbs.regDate,0,16)}</td>


							</tr>
							<tr>
								<th>Name</th>
								<td>${bbs.userName}</td>

							</tr>
							<tr>

								<th>Count</th>
								<td><span style="">♥ Good :${bbs.likeCount}</span> <span
									style="">♠ Comment :${bbs.commentCount}</span></td>
								<th>Delete Diary</th>
								<td>
									<form method="post" name="talkForm">
										<input type="hidden" name="bbsSeq" value="${bbs.bbsSeq}">
										<button type="button" class="btn-red"
											onclick="deleteGo(${bbs.bbsSeq},0,'${bbs.userId}');">Delete</button>
									</form>
								</td>
							</tr>
						</table>
						<div class="bbs-contents">
							<%
								pageContext.setAttribute("LF", "\n");
							%>
							<h3>${fn:replace(bbs.bbsContents, LF,'<br>')}</h3>

							<br>

							<c:choose>
								<c:when test="${fileList.size() > 0 }">
									<c:forEach var="it2" items="${fileList}" varStatus="p">
										<td><c:if test="${it2 !='' }">
												<c:forEach var="it3" items="${it2.items}" varStatus="s">
													<div class="photo-talk2"
														style="background-image:url('${it3.file}')"
														onclick="pop.zoom('${it3.file}')"></div>
												</c:forEach>
											</c:if></td>
									</c:forEach>
								</c:when>
							</c:choose>


						</div>

						<h2 style="margin-top: 20px;">Comment List</h2>

						<div class="tab-list">

							<ul class="reply-list">
								<c:choose>
									<c:when test="${list.size() > 0}">
										<form method="post" name="commentForm">
											<c:forEach var="it" items="${list}" varStatus="i">
												<input type="hidden" name="bbsCommentSeq"
													value="${it.bbsCommentSeq}">
												<input type="hidden" name="bbsSeq1" value="${bbs.bbsSeq}">
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
															${fn:substring(it.regDate,0,16)}
															<button style="margin-left: 10px;" type="button"
																class="btn-red"
																onclick="deleteGo(${bbs.bbsSeq},${it.bbsCommentSeq},'${bbs.userId}');">Delete</button>

															<div style="margin: 10px 0;">
																${it.bbsContents}
																<c:choose>

																	<c:when test="${fileList2.size() > 0 }">
																		<c:forEach var="it2" items="${fileList2}"
																			varStatus="p">
																			<c:if test="${i.index eq p.index }">
																				<td><c:if test="${it2 !='' }">
																						<c:forEach var="it3" items="${it2.items}"
																							varStatus="s">
																							<div class="photo-talk2"
																								style="background-image:url('${it3.file}')"
																								onclick="pop.zoom('${it3.file}')"></div>
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
										</form>
									</c:when>
									<c:otherwise>
										<li
											style="height: 100px; padding-top: 80px; text-align: center;">
											No-data!</li>
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

	<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"%>

</body>
</html>