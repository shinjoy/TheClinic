<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>




<script type="text/javascript">

$(document).ready(function() {
	aside.setActive(2,1);
	searchList(listForm,1);
});


function searchList(listForm,page) {
	
	var param = {
		
		keyword		: 	listForm.keyword.value,
		gender : listForm.gender.value,
		startDate   :   listForm.startDate.value,
		endDate     :   listForm.endDate.value,
		page		:	page
		
	};
	
	$.ajax({
		type:"POST",
		url:"/admin/diary/diary_list.go",
		dataType:"html",
		data:param,
		success:function(msg){
			$("#contents-list").html(msg);
		}
	});
	return false;
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


</style>


</head>
<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				 ■ Home > Diary Management > Diary List
			</header>
		
			<div class="contents-block">
			
				<h1>Diary List</h1>
				
				<div class="contents-main">

					<form method="post" name="listForm" id="listForm" onsubmit="return searchList(this,1); return false;">
						
						<div class="contents-top">
						
							<div class="top-tools">
								<div class="search-tool">
									<input type="text" name="keyword" value="${keyword}" placeholder="contents/Name" class="itext">
									

									<select name="gender" id="gender" class="select-search">
										<option value="0">Gender</option>
										<option value="1">Man</option>
										<option value="2">Woman</option>								
									</select>

									
									Registration Date
									<input type="text" name="startDate" class="itext datepicker" value="${startDate}" placeholder="Start-date" style="width:80px;" onchange="startChange(listForm);">
									<input type="text" name="endDate" class="itext datepicker" value="${endDate}" placeholder="End-date" style="width:80px;">
									<button type="submit" class="btn">Search</button>
								</div>

							</div>
						</div>

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