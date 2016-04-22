<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/jquery.ui.datepicker-ko.js"></script>


<script type="text/javascript">

$(document).ready(function() {
	aside.setActive(1,1);
	searchList(listForm,1);
});


function searchList(listForm,page) {

	var param = {
		page		:	page,
		keyword : listForm.keyword.value,
		gender : listForm.gender.value,
		colname : listForm.colname.value,
		sort : listForm.sort.value
	};
	
	$.ajax({
		type:"POST",
		url:"/admin/user/user_list.go",
		dataType:"html",
		data:param,
		success:function(msg){
			$("#contents-list").html(msg);
		}
	});
	return false;
}

function listOrder(colname, sort) {

	listForm.colname.value = colname;
	listForm.sort.value = sort;
	searchList(listForm, 1);
	var cSort ="asc";
	var point ="▼";
	if(sort=='asc'){
		cSort="desc";
		point="▲";
	}
	
	var str = "<button type='button' class='"+colname+"' onclick=\"listOrder('"+colname+"','"+cSort+"');\">"+point+"</button>";
	$("#"+colname).html(str);
	

	
}


</script>

<style>
.select-search { border:1px solid #ddd; padding-bottom:4px;}
</style>


</head>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ Home > Member Management > Member List
			</header>
		
			<div class="contents-block">
			
				<h1> Member List</h1>
				
				<div class="contents-main">
					<form method="post" name="listForm" id="listForm" onsubmit="return searchList(this,1); return false;">
						<input type="hidden" name="gen" value="${gen}">
						<input type="hidden" name="colname" value="reg_date">
						<input type="hidden" name="sort" value="desc">
						<div class="contents-top">
							<div class="top-tools">
								<div class="search-tool">		
									<input type="text" name="keyword" value="${keyword}" placeholder="ID/Name" class="itext">
									
									<select name="gender" class="select-search">
										<option value="0">=Gender=</option>
										<option value="1">Man</option>
										<option value="2">Woman</option>								
									</select>
									
									<button type="submit" class="btn">Search</button>
								</div>
							
							</div>
						</div>
						<table class="list">
							<colgroup>
								<col width="10%">
								<col width="5%">
								<%-- <col width="10%"> --%>
								<col width="10%">
								<col width="5%">
								<col width="10%">
								<col width="10%">
								<col width="10%">
							</colgroup>
								<thead>
									<th>Image </th>	
									<th>ID
										<span id="user_id">
											 <button type="button" onclick="listOrder('user_id','asc');" class="user_id" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<!-- <th>cellphone 
										<span id="phone_number">
											 <button type="button" onclick="listOrder('phone_number','asc');" class="phone_number" style="margin-top:3px;">▼</button>
										</span>
									</th> -->
									<th>Name 
										<span id="user_name">
										<button type="button" onclick="listOrder('user_name','asc');" class="user_name" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>Age
										<span id="birthday">
										 <button type="button" onclick="listOrder('birthday','asc');" class="birthday" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>gender
										<span id="gender">
										<button type="button" onclick="listOrder('gender','asc');" class="gender" style="margin-top:3px;">▼</button>
										</span>
									</th>
								
									<th>Last-login Date 
										<span id="last_logindate">
										<button type="button" onclick="listOrder('last_logindate','asc');" class="last_logindate" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>Registration Date
										<span id="reg_date">
										<button type="button" onclick="listOrder('reg_date','asc');" class="reg_date" style="margin-top:3px;">▼</button>
										</span>
									</th>
								</thead>
							</table>	
								<div id="contents-list">
								</div>
					
					</form>
				</div>
			</div>
		</section>
	</section>
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>

</body>
</html>