<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript">
	
	$(document).ready(function() {
		aside.setActive(1,1);
	});
	
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


		<form method="post" name="firmForm">
		<input type="hidden" name="userId" value="${user.userId}">
		
			<table class="register">
			<colgroup>
				<col width="70">
				<col width="*">
				<col width="70">
				<col width="70">
			</colgroup>
		<tr>
			<td rowspan="6">
			
			
					<c:forEach var="it2" items="${photo}" varStatus="p">
							<c:choose>
								<c:when test="${it2 !='' }">
									<c:forEach var="it3" items="${it2.items}" varStatus="s">
										<div class="photo-list" style="background-image:url('${it3.thumb}') " onclick="pop.zoom('${it3.file}')"></div>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<div class="photo-list"
										style="background-image: url('/images/user_default.png')"></div>
								</c:otherwise>	
							</c:choose>
					</c:forEach>
				
		
			</td>
			<th>ID</th><td colspan="4"> ${user.userId }</td>
		</tr>
		<tr>
				<th>Name</th><td colspan="2"> ${user.userName}</td>
				<th>area</th><td  colspan="2"> ${user.area}</td>
		
			</tr>
			<tr>
				
				<th></th><td  colspan="2"> <%-- ${user.phoneNumber} --%></td>
				<th>Height</th><td colspan="2" > ${user.height}</td>
				
			</tr>
			<tr>
				<th>Gender</th><td colspan="2"> ${user.genderText}</td>
				<th>weight</th><td colspan="2" > ${user.weight}</td>
				
			</tr>
			<tr>
			
				<th>Age</th><td  colspan="2"> ${user.userAge}</td>
				<th>BloodGroups</th><td colspan="2" > ${user.bloodGroups}</td>
			</tr>
			<tr>
				
				<th>Registration Date</th><td colspan="2">${fn:substring(user.regDate,0,16)}</td>
				<th>Last-login Date</th><td  colspan="2">${fn:substring(user.lastLogindate,0,16)}</td>
			</tr>
		
			</table>

		</form>

	<div class="btn-tools">
		<button type="button" class="btn-blue" onclick="document.location.href='/admin/user/user_edit.go?userId=${user.userId}';">Modified</button> 
		<button type="button" class="btn-red"onclick="deleteUser('${user.userId}');">Delete</button>
		<button type="button" class="btn"onclick="document.location.href='/admin/user/user.go';">Back</button>
	</div>

