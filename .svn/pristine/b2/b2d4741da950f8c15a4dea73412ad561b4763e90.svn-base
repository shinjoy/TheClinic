<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/views/common/header.jsp"  %>


<script type="text/javascript">
	
	$(document).ready(function() {
		aside.setActive(1,1);
	});
	
	
	function submitForm(frm) {
		
		
		if (frm.userId.value == "") {
			alert("Input ID, please.");
			return false;
		}
		
		if (frm.userName.value == "") {
			alert("Input name, please.");
			return false;
		}	
	
		
		if(frm.birthday.value == ""){
			alert("Input Age, please.")
			return false;
		}
	/* 	if(frm.phoneNumber.value == ""){
			alert("Input cellphone, please.")
			return false;
		} */
		if(frm.area.value == ""){
			alert("Input area, please.")
			return false;
		}

		if(frm.birthday.value == ""){
			alert("Input birthday, please.")
			return false;
		}
		var param = {
			userId	: frm.userId.value,
		//	phoneNumber : frm.phoneNumber.value,
			gender :  $('input:radio[name="gender"]:checked').val(),
			bloodGroups :  $('input:radio[name="bloodGroups"]:checked').val(),
			userName : frm.userName.value,
			area : frm.area.value,
			height : frm.height.value,
		
			weight : frm.weight.value,
			birthday : frm.birthday.value,
			usePushservice : $('input:radio[name="usePushservice"]:checked').val(),
	
		};
		
		$.ajax({
			type:"POST",
			url:"/m/user_info.go",
			dataType:"json",
			data:param,
			success:function(json){
				alert(json.message);
				if (json.result) {
					document.location.href = "/admin/user/user_view.go?userId="+json.userId;
				}
			}
		});

		
		return false;
	}
	
	
	
	function deleteUser(userId) {
		
		if(confirm("Are you sure you want to delete a member? The deleted information can not be recovered.")) {
			var param = {
					userId	:	userId
			};
			
			$.ajax({
				type:"POST",
				url:"/m/drop.go",
				dataType:"json",
				data:param,
				success:function(json){
					alert(json.message);
					if (json.result) {
						document.location.href = "/admin/user/user.go";
					}
				}
			});
		}
		return false;
	}


</script>

<style>
</style>
<body>

<%@ include file="/WEB-INF/views/admin/admin_header.jsp"  %>

<section class="main-cover main-row">
	<section id="main">
		
		<%@ include file="/WEB-INF/views/admin/admin_menu.jsp"  %>

		<section id="contents">
			<header class="panel">
				  ■ Home > Member Management > Member List 
			</header>
		
			<div class="contents-block" >
			
				<h1>Member Modified</h1>

				<form name="userForm"  onSubmit="return submitForm(this); return false;">
				<input type="hidden" name="userId" value="${user.userId}">
				
					<table class="register">
					<colgroup>
						<col width="70">
						<col width="*">
						<col width="70">
						<col width="70">
					</colgroup>
					<tr>
						<th>ID</th><td colspan="2" >${user.userId}</td>
				
					</tr>
					<tr>
						<th>Name</th><td colspan="2"><input type="text" name="userName" value="${user.userName}"></td>
						
					</tr>
					<tr>
						<th>Gender</th><td colspan="2">
							<input type="radio" name="gender" value="1" ${user.gender==1 ? 'checked=\"checked\"' :""}>Man</input>
							<input type="radio" name="gender" value="2" ${user.gender==2 ? 'checked=\"checked\"' :""}>Woman</input>
						</td>
					
					</tr>
					<tr>
						<th>Age</th><td  colspan="2"> <input type="text" name="birthday" value="${user.userAge}"></td>
						
					
					</tr>
					<%-- <tr>
						<th>cellphone</th><td  colspan="2"><input type="text" name="phoneNumber" value="${user.phoneNumber}"></td>
						
					</tr> --%>
					<tr>
						<th>Area</th><td  colspan="2"><input type="text" name="area" value="${user.area}"></td>
						
					</tr>
					<tr>
						<th>height</th><td  colspan="2"><input type="text" name="height" value="${user.height}"></td>
						
					</tr>
					<tr>
						<th>weight</th><td  colspan="2"><input type="text" name="weight" value="${user.weight}"></td>
						
					</tr>
					<tr>
						<th>bloodGroups</th><td  colspan="2">
						<input type="radio" name="bloodGroups" value="A" ${user.bloodGroups=='A' ? 'checked=\"checked\"' :""}>A</input>
						<input type="radio" name="bloodGroups" value="B" ${user.bloodGroups=='B' ? 'checked=\"checked\"' :""}>B</input>
						<input type="radio" name="bloodGroups" value="O" ${user.bloodGroups=='O' ? 'checked=\"checked\"' :""}>O</input>
						<input type="radio" name="bloodGroups" value="AB" ${user.bloodGroups=='AB' ? 'checked=\"checked\"' :""}>AB</input>
						</td>
					</tr>
					<tr>
						<th>Use Push Service</th>
						<td  colspan="2">
							<input type="radio" name="usePushservice" value="0" ${user.usePushservice==0 ? 'checked=\"checked\"' :""}>No</input>
							<input type="radio" name="usePushservice" value="1" ${user.usePushservice==1 ? 'checked=\"checked\"' :""}>Yes</input>
						</td>
						
					</tr>
					</table>
					<div class="btn-tools">
						<button type="submit" class="btn-blue">Save</button>
						<button type="button" class="btn-red"onclick="deleteUser('${user.userId}');">Delete</button>
						<button type="button" class="btn"onclick="document.location.href='/admin/user/user.go';">Back</button>
					</div>
		
				</form>

				
			</div>
		</section>
	</section>
	
</section>

<%@ include file="/WEB-INF/views/admin/admin_footer.jsp"  %>
</body>
				

