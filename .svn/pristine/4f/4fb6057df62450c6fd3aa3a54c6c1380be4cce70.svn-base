<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>

<script type="text/javascript">
	$(document).ready(function() {
		
		$.ajax({
			type:"POST",
			url:"/admin/count.go",
			dataType:"json",
			
			success:function(json){
				$('#user-count').append(json.manCount);
				$('#analyst-count').append(json.womanCount);
				$('#access-count').append(json.accessCount);
				$('#chatroom-count').append(json.chatroomCount);
			}
		});
	});
</script>

<form method="post" name="pageForm">
<input type="hidden" name="userId" value="${USER_ID}">
<input type="hidden" name="userName" value="${USER_NAME}">
<input type="hidden" name="userType" value="${USER_TYPE}">
</form>


<section class="main-cover">
	<header id="top-header">
		<aside>
			<div class="top-logo" style="margin-top:13px;">
				<a href="/admin/main.go"><img src="/images/top_facetalk.png" style="height:18px; margin-top:7px;"  ></a>
			</div>
		</aside>
		<section>
			<div class="head">
				<div class="info-block">
					<ul>
						<li>
							<dl>
								<dt><a href="/admin/user/user.go?gen=1"><img src="/images/man.png"></a></dt>
								<dd>
									남자 회원수<br>
									<span id="user-count"></span>명
									
									
								</dd>
							</dl>
						</li>
						<li>
							<dl>
								<dt><a href="/admin/user/user.go?gen=2"><img src="/images/woman.png"></a></dt>
								<dd>
									여자 회원수<br>
									<span id="analyst-count"></span>명
								</dd>
							</dl>
						</li>
						<li>
							<dl>
								<dt><a href="/admin/user/user_access.go"><img src="/images/now.png"></a></dt>
								<dd>
									접속중 회원<br>
									<span id="access-count"></span>명
								</dd>
							</dl>
						</li>
						<li>
							<dl>
								<dt><a href="/admin/chat/chat.go"><img src="/images/chat.png"></a></dt>
								<dd>
									개설된 채팅방 수<br>
									<span id="chatroom-count"></span>건
								</dd>
							</dl>
						</li>
					</ul>
				</div>

				<div class="user-block">
					<div class="head-tool" onclick="user.togleCombo();">
						<dl>
							<dt class="user-info">
								<p style="color:#fff; text-align:left;">${USER_NAME}<br>${USER_ID}</p>
							</dt>
							<dd>
								<div class="arrow">▼</div>
							</dd>
						</dl>
					</div>
					<ul class="user-tool-combo">
						<li><button type="button" onclick="user.changePw();"><span>비밀번호 변경</span></button></li>
						<li><button type="button" onclick="user.logout('admin');"><span>로그아웃</span></button></li>
					</ul>
				</div>
				
			</div>
		</section>
	</header>
</section>