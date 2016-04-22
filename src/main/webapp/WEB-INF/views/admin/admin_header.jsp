<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
	$(document).ready(function() {
		
		$.ajax({
			type:"POST",
			url:"/admin/count.go",
			dataType:"json",
			
			success:function(json){
				$('#user-count').append(json.Count);
				$('#access-count').append(json.accessCount);
				$('#mission-count').append(json.missionCount);
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
				<a href="/admin/main.go">
				<span style="font-weight:bold; font-size:3.8px; color:#fff;">E-FOODI</span>
				</a>
			</div>
		</aside>
		<section>
			<div class="head">
				<div class="info-block">
					<ul>
						<li>
							<dl>
								<dt><a href="/admin/user/user.go"><img src="/images/img_user.png"></a></dt>
								<dd>
									
									Member Count	<br>
									<span id="user-count"></span>persons
									
									
								</dd>
							</dl>
						</li>
						<li>
							<dl>
								<dt><a href="/admin/user/user.go"><img src="/images/now.png"></a></dt>
								<dd>
									Connect Member Count<br>
									<span id="access-count"></span>persons
								</dd>
							</dl>
						</li>
						<li>
							<dl>
								<dt><a href="/admin/mission/mission.go"><img src="/images/mission.png"></a></dt>
								<dd>
									
									Progress Mission Count<br>
									<span id="mission-count"></span>Count
								</dd>
							</dl>
						</li>
					</ul>
				</div>

				<div class="user-block">
					<div class="head-tool" onclick="user.togleCombo();">
						<dl>
							<dt class="user-info">
								<p style="color:#FFFFFF; text-align:left;">${USER_NAME}<br>${USER_ID}</p>
							</dt>
							<dd>
								<div class="arrow">▼</div>
							</dd>
						</dl>
					</div>
					<ul class="user-tool-combo">
						<li><button type="button" onclick="user.changePw();"><span>Change Password</span></button></li>
						<li><button type="button" onclick="user.logout('admin');"><span>LogOut</span></button></li>
					</ul>
				</div>
				
			</div>
		</section>
	</header>
</section>