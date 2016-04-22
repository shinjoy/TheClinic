<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<aside id="nav">

			<ul class="m1">
				<li class="menu" id="menu1">
					<a href="#" onclick="aside.togleSub(1);">
						<dl>
							<dt class="bullet">
								<div class="bullet-image"><img src="/images/user.png" style="height:20px;"></div>
							</dt>
							<dd class="menu-name">
								<span>Member Management</span>
							</dd>
							<dd class="arrow">
								<img src="/images/arrow_bottom.png">
							</dd>
						</dl>
					</a>
					<ul class="m2" id="u1">
						<li id="menu-sub11">
							<a href="/admin/user/user.go">
								<dl>
									<dt class="bullet">
										<div class="bullet-image"><img src="/images/arrow_right.png"></div>
									</dt>
									<dd class="menu-name">
										<span>Member List</span>
									</dd>
								</dl>
							</a>
						</li>
					</ul>
				</li>

				
				
				<li class="menu" id="menu2">
					<a href="#" onclick="aside.togleSub(2);">
						<dl>
							<dt class="bullet">
								<div class="bullet-image"><img src="/images/icon_board.png"></div>
							</dt>
							<dd class="menu-name">
								<span>Diary Management</span>
							</dd>
							<dd class="arrow">
								<img src="/images/arrow_bottom.png">
							</dd>
						</dl>
					</a>
					<ul class="m2" id="u2">
						<li id="menu-sub21">
							<a href="/admin/diary/diary.go">
								<dl>
									<dt class="bullet">
										<div class="bullet-image"><img src="/images/arrow_right.png"></div>
									</dt>
									<dd class="menu-name">
										<span>Diary List</span>
									</dd>
								</dl>
							</a>
						</li>
						
					</ul>
				</li>
				
				<li class="menu" id="menu8">
					<a href="#" onclick="aside.togleSub(8);">
						<dl>
							<dt class="bullet">
								<div class="bullet-image"><img src="/images/point.png"></div>
							</dt>
							<dd class="menu-name">
								<span>Mission Management</span>
							</dd>
							<dd class="arrow">
								<img src="/images/arrow_bottom.png">
							</dd>
						</dl>
					</a>
					<ul class="m2" id="u8">
						
						<li id="menu-sub82">
							<a href="/admin/mission/mission.go">
								<dl>
									<dt class="bullet">
										<div class="bullet-image"><img src="/images/arrow_right.png"></div>
									</dt>
									<dd class="menu-name">
										<span>Mission List</span>
									</dd>
								</dl>
							</a>
						</li>
					</ul>
				</li>
			<!-- 	
				<li class="menu" id="menu2">
					<a href="#" onclick="aside.togleSub(3);">
						<dl>
							<dt class="bullet">
								<div class="bullet-image"><img src="/images/content.png"></div>
							</dt>
							<dd class="menu-name">
								<span>Data Management</span>
							</dd>
							<dd class="arrow">
								<img src="/images/arrow_bottom.png">
							</dd>
						</dl>
					</a>
					<ul class="m2" id="u3">
						<li id="menu-sub31">
							<a href="/admin/chat/chat.go">
								<dl>
									<dt class="bullet">
										<div class="bullet-image"><img src="/images/arrow_right.png"></div>
									</dt>
									<dd class="menu-name">
										<span></span>
									</dd>
								</dl>
							</a>
						</li>
						<li id="menu-sub32">
							<a href="/admin/chat/chat_register.go">
								<dl>
									<dt class="bullet">
										<div class="bullet-image"><img src="/images/arrow_right.png"></div>
									</dt>
									<dd class="menu-name">
										<span></span>
									</dd>
								</dl>
							</a>
						</li>
					</ul>
				</li> -->
				<li class="menu" id="menu4">
					<a href="#" onclick="aside.togleSub(4);">
						<dl>
							<dt class="bullet">
								<div class="bullet-image"><img src="/images/chating.png"></div>
							</dt>
							<dd class="menu-name">
								<span>Notice Management</span>
							</dd>
							<dd class="arrow">
								<img src="/images/arrow_bottom.png">
							</dd>
						</dl>
					</a>
					<ul class="m2" id="u4">
						<li id="menu-sub41">
							<a href="/admin/notice/notice.go">
								<dl>
									<dt class="bullet">
										<div class="bullet-image"><img src="/images/arrow_right.png"></div>
									</dt>
									<dd class="menu-name">
										<span>Notice List</span>
									</dd>
								</dl>
							</a>
						</li>
					
					</ul>
				</li>

				
					</ul>
				</li>
			</ul>
		</aside>
