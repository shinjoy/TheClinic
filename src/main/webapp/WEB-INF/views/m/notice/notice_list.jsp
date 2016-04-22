<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul class="m1">
	<c:forEach var="dl" items="${list}">
		<li onclick="document.location.href='/m/notice/notice_view.go?seq=${dl.noticeSeq}';" ${dl.notiType==1 ? 'style=\"background-color:#FFD700; color=#FF0000;\"' : ''}>
			<dl>
				<dt class="bullet">
					<div class="bullet-image">
						<img src="/images/icon_arrow_right.png" class="arrow"
							style="width: 10px; margin-top: 15px;">
					</div>
				</dt>
				<dd class="menu-name">
					<div style="font-size: 14px; float: left; width:300px; ">
					${dl.title}</div>
					<%-- <div
						style="float: left; font-size: 10px; margin-left: 5px; color: #3b5998;">
						${dl.startDate.substring(5,7)}/${dl.startDate.substring(8,10)}~${dl.endDate.substring(5,7)}/${dl.endDate.substring(8,10)}
					</div> --%>
				</dd>
			</dl>
		</li>
	</c:forEach>
</ul>



<!-- 	<ul class="contents-list"> -->
<%-- 		<c:forEach var="dl" items="${list}"> --%>
<%-- 			<li onclick="='+${dl.noticeSeq}"> --%>
<!-- 				<div class="contents-arrow"> -->
<!-- 					<img src="/images/bullet_arrow_right.png" class="arrow" style="width:30px;"> -->
<!-- 				</div> -->
<!-- 				<div class="contents-info"> -->
<%-- 					<div class="contents-title-short ellipsis">${dl.title}</div> --%>
<%-- 					<span class="contents-meta round">${dl.regDate.substring(0,16)}</span> --%>
<!-- 				</div> -->
<!-- 				<div class="clear"></div> -->
<!-- 				<div class="contents"> -->
<!-- 				</div> -->
<!-- 				<div class="clear"></div> -->
<!-- 			</li> -->
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->

<!-- 	
	<div class="temp-more">
		<div class="read-more">
			다음 페이지를 불러오고 있습니다.
		</div>
	</div>
	 -->
${paging}

