<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>



<script type="text/javascript">

$(document).ready(function() {
	aside.setActive(8,1);
	searchList(listForm,1);
});


function searchList(listForm,page) {
	
	var param = {
		
		keyword		: 	listForm.keyword.value,
		startDate   :   listForm.startDate.value,
		endDate     :   listForm.endDate.value,
		colname : listForm.colname.value,
		sort : listForm.sort.value,
		page		:	page
		
	};
	
	$.ajax({
		type:"POST",
		url:"/admin/mission/mission_list.go",
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


$(function() {
    $.datepicker.setDefaults( $.datepicker.regional[ "ko" ] );
    $( ".datepicker" ).datepicker(
        {
        	dateFormat: 'yy-mm-dd',
 	       showButtonPanel: true
        }
    );
}); 


</script>

<style>
table.menuedit th {
   vertical-align: top;
   text-align :left;
   padding:5px;
   font-weight: bold;
}

table.menuedit td {
	padding:5px;
	text-align :left;
    vertical-align: top;
    padding: 3px;
    border-bottom: 1px solid #ddd;
}	
#btn-area{
	float: right;
}


</style>


</head>
<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ Home > Mission Management > Mission List
			</header>
		
			<div class="contents-block">
			
				<h1>Mission List</h1>
				
				<div class="contents-main">

					<form method="post" name="listForm" id="listForm" onsubmit="return searchList(this,1); return false;">
						<input type="hidden" name="colname" value="mission_seq">
						<input type="hidden" name="sort" value="desc">
						<div class="contents-top">
						
							<div class="top-tools">
								<div class="search-tool">
									<input type="text" name="keyword" value="${keyword}" placeholder="Title/Contents " class="itext">
									

									
									Registration Date
									<input type="text" name="startDate" class="itext datepicker" value="${startDate}" placeholder="Start-date" style="width:80px;" onchange="startChange(listForm);">
									<input type="text" name="endDate" class="itext datepicker" value="${endDate}" placeholder="End-date" style="width:80px;">
									<button type="submit" class="btn">Search</button>
								</div>
								<div id="btn-area">
									<button type="button" class="btn-blue"  onclick="document.location.href='/admin/mission/mission_edit.go?missionSeq=0';">New</button>	
								</div>
							</div>
							
						</div>
					
						<table class="list">
							<colgroup>
								<col width="10%">
								<col width="*">
								
								<col width="10%">
								<col width="15%">
								<col width="14%">
								<col width="10%">
							
							</colgroup>
								<thead>
									
									<th>Image</th>
										
									<th>Title
									</th>
									<th>The number of people involved  
										<span id="countUser">
										<button type="button" onclick="listOrder('countUser','asc');" class="countUser" style="margin-top:3px;">▼</button>
										</span>
									</th>
									<th>Notifications</th>
									<th>Mission Start Date/Mission End Date </th>
									<th>Registration Date
										<span id="reg_date">
										 <button type="button" onclick="listOrder('reg_date','asc');" class="reg_date" style="margin-top:3px;">▼</button>
										</span>
									</th>
									
								</thead>
								
							</table>
							
						<div id="contents-list" style="background-color:#eee;">
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