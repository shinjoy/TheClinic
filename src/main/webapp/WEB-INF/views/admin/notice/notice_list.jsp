<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		
	<table class="list">
	<colgroup>
		<col width="10%">
		<col width="10%">
		<col width="*">
		<col width="20%">				
	</colgroup>
	<thead>
		<tr>
			<th>Status</th>
			<th>No</th>
			<th>Title</th>
			<th>Registration Date</th>
		</tr>
	</thead>
	<tbody class="rl">
	<c:choose>
		<c:when test="${list.size() > 0}">
			<c:forEach var="it" items="${list}">
				<c:choose>
				  	<c:when test="${it.type ==1 or it.type==2}">
				  		<tr ${it.notiType==1 ? 'style=\"background-color:#FFD700; ;\"' :''};">	
				  		<td>Posted in</td>
				  		<td ><a href="/admin/notice/notice_edit.go?noticeSeq=${it.noticeSeq}">${it.noticeSeq}</a></td>
						<td style="text-align:left; padding-left:5px;">
						        <a href="/admin/notice/notice_edit.go?noticeSeq=${it.noticeSeq}">
						       <b>${it.notiType==1 ? '<h2>' : ''}   ${it.title}</a>
						</td>
				  	</c:when>
				  	<c:when test="${it.type ==3 or it.type ==4 }">
				  		<tr ${it.notiType==1 ? 'style=\"background-color:#FFE08C; color=#BCE55C;\"' :''};">	
				  		<td>Soon</td>
				  		<td><a href="/admin/notice/notice_edit.go?noticeSeq=${it.noticeSeq}">${it.noticeSeq}</a></td>
						<td style="text-align:left; padding-left:5px;"><a href="/admin/notice/notice_edit.go?noticeSeq=${it.noticeSeq}">
						   ${it.notiType==1 ? '<b>' : ''} ${it.title}</a></td>
				  	</c:when>
				  	<c:otherwise>
				  		<tr>
				  		<td>Deadline</td>
				  		<td><a href="/admin/notice/notice_edit.go?noticeSeq=${it.noticeSeq}">${it.noticeSeq}</a></td>
						<td style="text-align:left; padding-left:5px;"><a href="/admin/notice/notice_edit.go?noticeSeq=${it.noticeSeq}">
						  ${it.title}</a></td>
				  	</c:otherwise>
				  </c:choose>
				<%-- <tr ${it.notiType==1 ? 'style=\"background-color:#FFD700; color=#BCE55C;\"' : ''}> --%>
					<td >${it.regDate.substring(0,16)}</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="7" class="empty-data"> No-Data !</td>
			</tr>
		</c:otherwise>
	</c:choose>
	</tbody>
	</table>
		
	${paging}

	
