<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

			<table class="list">
				<colgroup>
					<col width="10%">
					<col width="*">
				
					<col width="10%">
					<col width="15%">
					<col width="14%">
					<col width="10%">
				</colgroup>
			
			<tbody class="rl">
			<c:choose>
				<c:when test="${list.size() > 0}">
					<c:forEach var="it" items="${list}" varStatus="i">
								<tr onclick="location.href='/admin/mission/mission_view.go?missionSeq=${it.missionSeq}';">
									<td>
									<c:if test="${fileList.size() > 0 }">
												<c:forEach var="it2" items="${fileList}" varStatus="p">
													<c:if test="${i.index eq p.index }">	
															<c:if test="${it2 !='' }">
																<c:forEach var="it3" items="${it2.items}" varStatus="s">
																		<c:if test="${s.index==0}">
																			<div class="photo-missionList" style="background-image:url('${it3.thumb}');" onclick="pop.zoom('${it3.file}')"></div>
																		</c:if>
																</c:forEach>
															</c:if>
													</c:if>	
												</c:forEach>
									</c:if>
									</td>
									<td class="text-left">${it.title}</td>
									
									<td align="center">${it.countUser}</td>
									<td class="text-left">${fn:substring(it.missionPushTime,0,8)}
									[${it.missionPushDate1!=0 ? 'Mon':'' }
									${it.missionPushDate2!=0 ? 'Tue':'' }
									${it.missionPushDate3!=0 ? 'Wed':'' }
									${it.missionPushDate4!=0 ? 'Thu':'' }
									${it.missionPushDate5!=0 ? 'Fri':'' }
									${it.missionPushDate6!=0 ? 'Sat':'' }
									${it.missionPushDate7!=0 ? 'Sun':'' }]
									</td>
									<td class="text-left">${fn:substring(it.missionStartDate,0,16)} ~ ${fn:substring(it.missionEndDate,0,16)}</td>
									<td align="center">${fn:substring(it.regDate,0,16)}</td>
								</tr>
					</c:forEach>
					</c:when>
					<c:otherwise>
						<td colspan="10" style="height:200px;"> No-Data !</td>
					</c:otherwise>
					 	
			</c:choose>
			</tbody>
		</table>
			
		${paging}
		
	
	