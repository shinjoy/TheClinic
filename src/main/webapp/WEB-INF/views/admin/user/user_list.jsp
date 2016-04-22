<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

			<table class="list">
				<colgroup>
					<col width="10%">
					<col width="5%">
					<col width="10%">
					<%-- <col width="10%"> --%>
					<col width="5%">
					<col width="10%">
					<col width="10%">
					<col width="10%">
				</colgroup>
			
			<tbody class="rl">
			<c:choose>
				<c:when test="${list.size() > 0}">
					<c:forEach var="it" items="${list}" varStatus="i">
								<tr onclick="location.href='/admin/user/user_view.go?userId=${it.userId}';">
									<td style="padding-left:35px;">
										
							
											 
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
									<td class="text-left"><%-- <a href="/admin/user/user_view.go?userId=${it.userId}"> --%>${it.userId}</td>
									<%-- <td class="text-left">${it.phoneNumber}</td> --%>
									<td class="text-left"><%-- <a href="/admin/user/user_view.go?userId=${it.userId}"> --%>${it.userName}</td>
									<td>${it.userAge}</td>
									<td>${it.genderText}</td>
									<td>${fn:substring(it.lastLogindate,0,16)}</td>
									<td>${fn:substring(it.regDate,0,16)} </td>
								</tr>
					</c:forEach>
					</c:when>
					<c:otherwise>
						<td colspan="10" style="height:200px;"> No-Data ! </td>
					</c:otherwise>
					 	
			</c:choose>
			</tbody>
		</table>
			
		${paging}
		
	
	