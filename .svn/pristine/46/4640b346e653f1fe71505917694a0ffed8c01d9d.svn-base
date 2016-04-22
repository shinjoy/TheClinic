<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header5.jsp"  %>

</head>

<body class="mobile">
	<div class="notice-view">
		<div class="contents-head">
			<table>
			<tr>
				<td style="" >
					<a href="javascript:history.back(-1);">
						<img src="/images/bullet_arrow_left.png" style=" margin-left:10px; margin-top:10px; width:30px;">
					</a>
				</td>
				<td>
					<div class="contents-title">
						${notice.title}
					</div>
					<div class="contents-meta">${notice.startDate.substring(5,7)}/${notice.startDate.substring(8,10)}~${notice.endDate.substring(5,7)}/${notice.endDate.substring(8,10)}</div>
				</td>
			</tr>
			</table>
		</div>
		<div class="contents-contents">
			${notice.contentsHtml}
		</div>
	</div>	
</body>

</html>
