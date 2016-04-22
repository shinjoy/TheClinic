<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <%@ include file="/WEB-INF/views/common/header.jsp"  %> 
<!--    -->
<link rel="stylesheet" type="text/css" href="/css/timepicki.css">
<!--<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>  -->
<!-- <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script> -->
<!--<script src="/lib/jquery/jquery.min.js"></script>  -->
<!-- <script type="text/javascript" src="/lib/jquery/jquery.ui.datepicker-ko.js"></script> -->
<!-- <script src="/lib/jquery/timepicki.js"></script> -->
 <script src="/lib/jquery/bootstrap.min.js"></script> 

<style>
.add-img { display:table-cell; width:40px; padding:5px; text-align:center; }
.add-btn { display:table-cell; width:60px; padding:5px; text-align:center; }
.add-img .file-up { cursor:pointer; }
.add-img .file { position:relative; width:20px; height:18px; }
.add-img .file.long { width:200px; }
.add-img .file-btn { position:relative; margin-top:-20px; height:18px; opacity:0; cursor:pointer; }
.add-img .file-btn .hidden-submit-btn {width:0px; height:0px;}
li .photo-preview { width:80px; height:80px; background-size:cover; background-repeat:no-repeat; background-position:center; margin:10px 10px 10px 0; }
.file-add { padding-left:10px; }
.file-add li { float:left; }
.cancel-btn { width:15px; height:15px; font-size:10px; background-color:#f00; color:#fff; border:1px solid #fff; border-radius:10px; padding:2px; }
.cancel-btn:hover { background-color:#f55;  }
.img-thumb { height:50px;  padding:3px 0;}
</style>




<script type="text/javascript">
	$(document).ready(function() {
		
		$(".timepicker").timepicki({increase_direction:'up'});  
		 aside.setActive(8,1);  
	 	var options = {
				//target :		'#user-join-result',
				beforeSubmit :	formCheck,
				success :		formSuccess
			};
			
		$('#bbsEditForm').ajaxForm(options); 
	});
	
	 function formCheck(formData, jqForm, options) {
		return true;
	}
	function formSuccess(responseText, statusText, xhr, $form) {
		//alert('status: ' + statusText + '\n\nresponseText: \n' + responseText );
		
		$(".file-add").empty();
		var idx = 0;
		if (bbsEditForm.photo != undefined) {
			idx = 1;
		};
		var json = JSON.parse(responseText);
		try {
			if(json.photo!=""){
				var str = '';
				str += '<li class="thumb" id="new-img'+idx+'">';
				str += '	<div class="photo-preview" style="background-image:url(\''+ json.path + json.photo +'\');">';
				str += '		<button type="button" class="cancel-btn" onclick="delfileNow(\''+ json.path + json.photo +'\','+idx+',this.form)">X</button>';
				str += '	</div>';
				//str += '	<input type="text" name="photo" value="'+ json.path + json.photo +'">';
				//str += '	<input type="hidden" name="thumb" value="'+ json.path +"thumb/"+ json.photo +'">';
				str += '</li>';
				
				
				if(bbsEditForm.photo!=undefined){
					photo	=bbsEditForm.photo.value;
					thumb	=  bbsEditForm.thumb.value;
				}else{
					str+='  <input type="hidden"  name="photo" value='+json.path + json.photo+'> ';
					str+='  <input type="hidden"  name="thumb" value='+json.path +"thumb/"+ json.photo+'>  ';
				}
				
				$(".file-add").html(str);
			}
			
		} catch (e) {
			pop.openAlert('',json.message); 
		}
	}

	function delfile(num){
		if(confirm("Are you sure you want to delete?")){
			$("#li"+num).remove();
		}
		return false;
	}
	function delfileNow(filename,num,frm){
		if(confirm("Are you sure you want to delete?")){
			
			var param = {
				file : filename
			}
			$.ajax({
				type:"POST",
				url:"/m/file_delete.go",
				dataType:"json",
				data:param,
				success:function(json){
				
					$("#new-img"+num).remove();
					bbsEditForm.photo.value = '';
				}
			}); 
		}
		
		return false;
	}
	$(function() {
	
		$.datepicker.setDefaults( $.datepicker.regional[ "ko" ] );
		$( ".datepicker" ).datepicker(
			{
			dateFormat : "yy-mm-dd",	
			showButtonPanel: true
			}
		);
	
	});	 


	
	function submitForm(frm) {
		
		
	 	var start = parseInt(frm.missionStartDate.value.replace(/-/g, ""));
		var end = parseInt(frm.missionEndDate.value.replace(/-/g, "")); 
		var photo="";
		var thumb="";
		
		if(frm.photo!=undefined){
			photo	=frm.photo.value;
			thumb	=  frm.thumb.value;
		}
		
		if (frm.title.value == "") {
			alert("Please enter a title.");
			return false;
		}
		
		if (frm.missionStartDate.value == "") {
			alert("Please enter a posting period.");
			return false;
		}	
		
		if (frm.missionEndDate.value == "") {
			alert("Please enter a posting period.");
			return false;
		}
		
		if(start>end){
			alert("Please check the posting period.")
			return false;
		}
		
		
		
		
		var param = {
			missionSeq	: frm.missionSeq.value,
			userId	: frm.userId.value,
			missionPushTime : frm.missionPushTime.value,
			missionStartDate : frm.missionStartDate.value,
			missionEndDate : frm.missionEndDate.value,
			missionPushDate1	:  $('input:checkbox[name="missionPushDate1"]:checked').val(),
			missionPushDate2	:  $('input:checkbox[name="missionPushDate2"]:checked').val(),
			missionPushDate3	:  $('input:checkbox[name="missionPushDate3"]:checked').val(),
			missionPushDate4	:  $('input:checkbox[name="missionPushDate4"]:checked').val(),
			missionPushDate5	:  $('input:checkbox[name="missionPushDate5"]:checked').val(),
			missionPushDate6	:  $('input:checkbox[name="missionPushDate6"]:checked').val(),
			missionPushDate7	:  $('input:checkbox[name="missionPushDate7"]:checked').val(),
			title	: frm.title.value,
			contentsText	: frm.contentsText.value,
			photo	:photo,
			thumb	: thumb
		};
		
		$.ajax({
			type:"POST",
			url:"/admin/mission/mission_edit_do.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					document.location.href = "/admin/mission/mission.go";
				}
			}
		});

		
		return false;
	}

	
	function deleteNotice(missionSeq) {
		if(confirm("Are you sure you want to delete?")) {
			var param = {
					missionSeq	:	missionSeq,
					missionCommentSeq : 0
			};
			
			$.ajax({
				type:"POST",
				url:"/m/mission_delete.go",
				dataType:"json",
				data:param,
				success:function(json){
					alert(json.message);
					if (json.result) {
						document.location.href = "/admin/mission/mission.go";
					}
				}
			});
		}
		return false;

	}
	
</script>

</head>
<style>
.device { font-weight: bold; font-size:25px; color:#464242; padding-bottom:30px;}	
</style>

<body>

  <%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>  

<section class="main-cover main-row">
	<section id="main">
	 	
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %> 

		<section id="contents">
			<header class="panel">
				 ■ Home > Mission Management > Mission ${mission.missionSeq >0 ? 'Modified' : 'Enrollment' }
			</header>
		
			<div class="contents-block">
			
				<h1> Mission ${mission.missionSeq >0 ? 'Modified' : 'Enrollment' }</h1>
				
				<div class="contents-edit">

					<form method="post" name="bbsEditForm" id="bbsEditForm" enctype="multipart/form-data" action="/m/photo_upload.go" style="display:block;">
					<input type="hidden" name="missionSeq" value="${mission.missionSeq}"/>
					<input type="hidden" name="userId" value="${USER_ID}"/>
					<input type="hidden" name="path" value="mission">
				<!-- 	<input type="hidden"  name="photo" value=""> 
					<input type="hidden"  name="thumb" value=""> -->
					<input type="hidden" name="isThumb" value="0">
					<input type="hidden" name="addThumb" value="1">
					
			
						<table class="edit">
						<colgroup>
							<col width="100">
							<col width="*">
						</colgroup>
						<tr>
							<th>Title</th>
							<td><input type="text" name="title" class="itext" style="width:590px;" value="${mission.title}"></td>
						</tr>
						<tr>
							<th>Mission Start Date/Mission End Date </th>
							<td>
								<input type="text" name="missionStartDate" class="itext datepicker" style="width:150px;" value="${mission.missionStartDate.length() >= 10 ? fn:substring(mission.missionStartDate,0,10) : mission.missionStartDate}"> ~
								<input type="text" name="missionEndDate" class="itext datepicker" style="width:150px;" value="${mission.missionEndDate.length() >= 10 ? fn:substring(mission.missionEndDate,0,10) : mission.missionEndDate}">
							</td>
						</tr>
						
						<tr>
							<th>Notifications Time</th>
							<td><input type="text" name="missionPushTime" class="itext timepicker" style="width:150px;" value="${fn:substring(mission.missionPushTime,0,8)}" ></td>
						</tr>
						<tr>
							<th>Notifications Date</th>
							<td>
								<input type="checkbox" name="missionPushDate1" value="1" ${mission.missionPushDate1==1 ? 'checked=/"checked/"' :'' }>Mon
								<input type="checkbox" name="missionPushDate2" value="1" ${mission.missionPushDate2==1 ? 'checked=/"checked/"' :'' }>Tue
								<input type="checkbox" name="missionPushDate3" value="1" ${mission.missionPushDate3==1 ? 'checked=/"checked/"' :'' }>Wed
								<input type="checkbox" name="missionPushDate4" value="1" ${mission.missionPushDate4==1 ? 'checked=/"checked/"' :'' }>Thu
								<input type="checkbox" name="missionPushDate5" value="1" ${mission.missionPushDate5==1 ? 'checked=/"checked/"' :'' }>Fri
								<input type="checkbox" name="missionPushDate6" value="1" ${mission.missionPushDate6==1 ? 'checked=/"checked/"' :'' }>Sat
								<input type="checkbox" name="missionPushDate7" value="1" ${mission.missionPushDate7==1 ? 'checked=/"checked/"' :'' }>Sun
							</td>
						</tr>
						<tr>
							<th>Image</th>
							<td>
							
								<div class="add-img">
									<img src="/images/icon_picture_dw.png" class="file-up" style="cursor:pointer;">
									<div class="file-btn">
										<input type="file" name="filename" class="file" onchange="document.getElementById('upload-btn').click();" style="cursor:pointer;" title="Image">
										<button type="submit" id="upload-btn" class="hidden-submit-btn">a</button>
									</div>
								</div>
								
							</td>
						</tr>
						<tr><th></th><td>
								<div class="file-box-ed">
									<span class="file-add">
									<c:if test="${fileList.size() > 0 }">
												<c:forEach var="it2" items="${fileList}" varStatus="p">
															
															<c:if test="${it2 !='' }">
																<c:forEach var="it3" items="${it2.items}" varStatus="s">
																		<li class="thumb images" id="li${p.index}">
																			<span class ="cphoto p${p.index}"> 
																				
																			 	<input type="hidden"  name="photo" value="${it3.file}">
																				<input type="hidden"  name="thumb" value="${it3.thumb}"> 
																				
																					<div class="photo-preview" style="background-image:url('${it3.thumb}'); ">
																				
																						<button type="button" class="cancel-btn" onclick="delfile(${p.index},'${it3.thumb}')">X</button>
																					</div>
																				</span>
																			
																		</li> 
																		
																
																</c:forEach>
															</c:if>
															
												</c:forEach>
									</c:if>
									</span>	
								
								</div></td>
						</tr>
						<tr>
							<th style="vertical-align: top;">Contents</th>
							<td><textarea name="contentsText"  style="width:700px; height: 500px;" >${mission.contentsText}</textarea></td>
						</tr>
						</table>
						
					
					<div class="btn-tools">
						<button type="button" class="btn-blue" style="width:200px;" onclick="submitForm(this.form)">Save</button>
						<c:if test="${mission.missionSeq > 0}">
							<button type="button" class="btn-red" onclick="deleteNotice(${mission.missionSeq});">Delete</button>
						</c:if>
						<button type="button" class="btn" onclick="document.location.href='/admin/mission/mission.go';" >Back</button>
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