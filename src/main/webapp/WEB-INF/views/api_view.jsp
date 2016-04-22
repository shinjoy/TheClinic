<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> -->
<!--[if IE 6]><html lang="ko" class="no-js old ie6"><![endif]-->
<!--[if IE 7]><html lang="ko" class="no-js old ie7"><![endif]-->
<!--[if IE 8]><html lang="ko" class="no-js old ie8"><![endif]-->
<!--[if IE 9]><html lang="ko" class="no-js ie9"><![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--><html lang="ko" class="no-js modern"><!--<![endif]-->

 <% request.setCharacterEncoding("utf-8"); %>
<%
 response.setHeader("Cache-Control","no-cache"); 
 response.setHeader("Pragma","no-cache"); 
 response.setDateHeader("Expires",0); 
%> 

<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<meta http-equiv="Content-Script-Type" content="text/javascript" />
	<meta http-equiv="Content-Style-Type" content="text/css" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>The Clinic</title>
	
	<link rel="stylesheet" type="text/css" href="/css/bb-1.0.2.css" />
	<link rel="stylesheet" type="text/css" href="/css/bb-form-1.0.2.css" />
	<link rel="stylesheet" type="text/css" href="/css/bb-popup-1.0.1.css" />
	<link rel="stylesheet" type="text/css" href="/css/bb-popup-2.0.1.css" />
	<link rel="stylesheet" type="text/css" href="/css/bb-tab-1.0.1.css" />
	<link rel="stylesheet" type="text/css" href="/css/bb-table-1.0.2.css" />
	<link rel="stylesheet" type="text/css" href="/css/bb-admin-1.0.3.css" />
	<link rel="stylesheet" type="text/css" href="/css/bb-calendar-1.0.1.css" />

	<script type="text/javascript" src="/lib/jquery/jquery-2.1.1.js"></script>
	<script type="text/javascript" src="/lib/jquery/jquery.form.js"></script>


<style>
	body { padding:20px; background-color:#fff; }
	h1 {font-weight:bold;font-size:14px;}
	h2 {font-weight:bold;font-size:14px; margin-bottom:5px; }
	ol.my-form li {
		list-style:decimal;
		font-weight:bold;
		font-size:16px;
		margin-left:30px;
	}
	.div-title { border-bottom:1px solid #000; font-size:30px; font-weight:bold; margin-top:20px; color : red ;}
	.api { min-width:1100px; clear:both; margin-top:20px; }
	.api dt { width:200px; }
	.api .in { float:left; }
	.api .out { float:left; width:600px; }
	.api .in dt { clear:left; float:left; font-size:12px; }
	.api .in dd { float:left; font-size:12px; font-weight:normal; }
	.result-view { border:1px solid #aaa; padding:10px; clear:both; word-break:break-all; font-size:12px; font-weight:normal; line-height:20px; }
	.real { color:#f40; min-height:26px; }
	input { width:264px !important; }
</style>

<script>

	$(document).ready(function() {
		/* 폼 ajax전송 : http://malsup.com/jquery/form/#ajaxForm */
		var options = {
			//target :		'#user-join-result',
			beforeSubmit :	formCheck,
			success :		formSuccess
		};
		$('#join').ajaxForm(options);
		$('#login').ajaxForm(options);
		$('#logout').ajaxForm(options);
		$('#drop').ajaxForm(options);
		$('#user_info').ajaxForm(options);
		$('#user_view').ajaxForm(options);
		
		$('#photo_upload').ajaxForm(options);
		$('#bbs_list').ajaxForm(options);
		$('#bbs_view').ajaxForm(options);
		$('#bbs_comment_view').ajaxForm(options);
		$('#bbs_comment_edit').ajaxForm(options);
		$('#bbs_edit').ajaxForm(options);
		$('#bbs_delete').ajaxForm(options);
		$('#bbs_good').ajaxForm(options);
	
		
		$('#main_list').ajaxForm(options);
		$('#search_food').ajaxForm(options);
		
		$('#mission_list').ajaxForm(options);
		$('#mission_join').ajaxForm(options);
		$('#mission_view').ajaxForm(options);
		$('#mission_comment_view').ajaxForm(options);
		$('#mission_comment_edit').ajaxForm(options);
		$('#mission_delete').ajaxForm(options);
		$('#mission_edit_do').ajaxForm(options);
		
		$('#search_food_view').ajaxForm(options);
		
		
		
	});
	
	function formCheck(formData, jqForm, options) {
		$("#"+resultDiv+"-result").html("");
		return true; 
	}
	function formSuccess(responseText, statusText, xhr, $form) {
		//alert('status: ' + statusText + '\n\nresponseText: \n' + responseText );
		var json = JSON.parse(responseText);
		try {
			$("#"+resultDiv+"-result").html(responseText);
		} catch (e) {
            alert(json.message); 
		}
	}

	var resultDiv;
	function formSubmit(div) {
		resultDiv = div;
	}

</script>

</head>
<body>

<h1 class="ad_title">The Clinic API</h1>

<div class="div-title">
	회원관리
</div>

 <ol class="my-form">
	<li class="api">
		<c:set var="url" value="join"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>회원가입</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt>	<dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>userName</dt><dd><input type="text" name="userName" placeholder="userName" class="bb"></input></dd>
				<dt>birthday</dt><dd><input type="text" name="birthday" placeholder="나이" class="bb"></input></dd>
				<dt>gender</dt><dd><input type="text" name="gender" placeholder="gender" class="bb"></input></dd>
				<dt>osType</dt><dd><input type="text" name="osType" placeholder="osType" class="bb"></input></dd>
				<dt>phoneNumber</dt><dd><input type="text" name="phoneNumber" placeholder="페이스북키" class="bb"></input></dd>
				<dt>pushKey</dt><dd><input type="text" name="pushKey" placeholder="pushKey" class="bb"></input></dd>
				<dt>deviceName</dt><dd><input type="text" name="deviceName" placeholder="deviceName" class="bb"></input></dd>
				<dt>deviceId</dt><dd><input type="text" name="deviceId" placeholder="deviceId" class="bb"></input></dd>
				<dt>osVersion</dt><dd><input type="text" name="osVersion" placeholder="osVersion" class="bb"></input></dd>
				<dt>appVersion</dt><dd><input type="text" name="appVersion" placeholder="appVersion" class="bb"></input></dd>
				<dt>loginFacebook</dt><dd><input type="text" name="loginFacebook" placeholder="페이스북 로그인 1" class="bb"></input></dd>
				<dt>usePushservice</dt><dd><input type="text" name="usePushservice" placeholder="푸시사용 1 안함 0" class="bb"></input></dd>
				<dt>token</dt><dd><input type="text" name="token" placeholder="token" class="bb"></input></dd>
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
	<li class="api">
		<c:set var="url" value="login"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>모바일 로그인</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt>	<dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>pushKey</dt><dd><input type="text" name="pushKey" placeholder="pushKey" class="bb"></input></dd>
				<dt>deviceName</dt><dd><input type="text" name="deviceName" placeholder="deviceName" class="bb"></input></dd>
				<dt>phoneNumber</dt><dd><input type="text" name="phoneNumber" placeholder="페이스북키" class="bb"></input></dd>
				<dt>deviceId</dt><dd><input type="text" name="deviceId" placeholder="deviceId" class="bb"></input></dd>
				<dt>osType</dt><dd><input type="text" name="osType" placeholder="osType" class="bb"></input></dd>
				<dt>token</dt><dd><input type="text" name="token" placeholder="token" class="bb"></input></dd>
			
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	<li class="api">
		<c:set var="url" value="logout"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>모바일 로그아웃</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt>	<dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
			
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
	<li class="api">
		<c:set var="url" value="drop"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>탈퇴</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt>	<dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
			
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	<li class="api">
		<c:set var="url" value="user_view"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>정보보기</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt>	<dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
			
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	<li class="api">
		<c:set var="url" value="user_profile"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>프로필 이미지 수정/삭제</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt>	<dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				
				<dt>photo</dt><dd><input type="text" name="photo" placeholder="photo" class="bb"></input></dd>
				<dt>thumb</dt><dd><input type="text" name="thumb" placeholder="thumb" class="bb"></input></dd>
				
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				 삭제일 경우 photo 에 빈값 , 수정일경우 파일 경로	
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	<li class="api">
		<c:set var="url" value="user_info"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>문진등록/수정</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt>	<dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>userName</dt><dd><input type="text" name="userName" placeholder="userName" class="bb"></input></dd>
				<dt>birthday</dt><dd><input type="text" name="birthday" placeholder="나이" class="bb"></input></dd>
				<dt>area</dt><dd><input type="text" name="area" placeholder="area" class="bb"></input></dd>
				<dt>gender</dt><dd><input type="text" name="gender" placeholder="gender" class="bb"></input></dd>
				<dt>height</dt><dd><input type="text" name="height" placeholder="height" class="bb"></input></dd>
				<dt>weight</dt><dd><input type="text" name="weight" placeholder="weight" class="bb"></input></dd>
				<dt>bloodGroups</dt><dd><input type="text" name="bloodGroups" placeholder="bloodGroups" class="bb"></input></dd>
				
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	<li class="api">
		<c:set var="url" value="user_info"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>알러지 문진 등록/수정</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt>	<dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>frequentUrination</dt><dd><input type="text" name="잦은배뇨" placeholder="userName" class="bb"></input></dd>
				<dt>hotUrine</dt><dd><input type="text" name="hotUrine" placeholder="소변시뜨거운느낌" class="bb"></input></dd>
				<dt>urinaryTractInfection</dt><dd><input type="text" name="urinaryTractInfection" placeholder="요로감염" class="bb"></input></dd>
				<dt>vaginitis</dt><dd><input type="text" name="vaginitis" placeholder="질염" class="bb"></input></dd>
				<dt>pruritus</dt><dd><input type="text" name="pruritus" placeholder="가려움증" class="bb"></input></dd>
				<dt>acne</dt><dd><input type="text" name="acne" placeholder="여드름" class="bb"></input></dd>
				<dt>skinRash</dt><dd><input type="text" name="skinRash" placeholder="피부발진" class="bb"></input></dd>
				<dt>hives</dt><dd><input type="text" name="hives" placeholder="두드러기" class="bb"></input></dd>
				<dt>pallorDry</dt><dd><input type="text" name="pallorDry" placeholder="창백하고건조" class="bb"></input></dd>
				<dt>crack</dt><dd><input type="text" name="crack" placeholder="머리카락과손톱 갈라짐" class="bb"></input></dd>
				<dt>whim</dt><dd><input type="text" name="whim" placeholder="변덕" class="bb"></input></dd>
				<dt>anger</dt><dd><input type="text" name="anger" placeholder="화" class="bb"></input></dd>
				<dt>stress</dt><dd><input type="text" name="stress" placeholder="스트레스" class="bb"></input></dd>
				<dt>depression</dt><dd><input type="text" name="depression" placeholder="의기소침" class="bb"></input></dd>
				
				<dt>craving</dt><dd><input type="text" name="craving" placeholder="갈망" class="bb"></input></dd>
				<dt>voracity</dt><dd><input type="text" name="voracity" placeholder="폭식" class="bb"></input></dd>
				<dt>tiredness</dt><dd><input type="text" name="tiredness" placeholder="피로" class="bb"></input></dd>
				<dt>decreasedConcentration</dt><dd><input type="text" name="decreasedConcentration" placeholder="집중력감소" class="bb"></input></dd>
				<dt>hypersensitivity</dt><dd><input type="text" name="hypersensitivity" placeholder="과민" class="bb"></input></dd>
				<dt>severeEmotional</dt><dd><input type="text" name="severeEmotional" placeholder="감정기복심함" class="bb"></input></dd>
				<dt>severeWeight</dt><dd><input type="text" name="severeWeight" placeholder="심각한체중변화" class="bb"></input></dd>
				<dt>eczema</dt><dd><input type="text" name="eczema" placeholder="습진" class="bb"></input></dd>
				<dt>rash</dt><dd><input type="text" name="rash" placeholder="발진" class="bb"></input></dd>
				<dt>pimple</dt><dd><input type="text" name="pimple" placeholder="뾰루지" class="bb"></input></dd>
				<dt>sweat</dt><dd><input type="text" name="sweat" placeholder="과도한땀" class="bb"></input></dd>
				<dt>muscleTremors</dt><dd><input type="text" name="muscleTremors" placeholder="근육떨림" class="bb"></input></dd>
				<dt>musclePain</dt><dd><input type="text" name="musclePain" placeholder="근육통증" class="bb"></input></dd>
				<dt>lessMuscle</dt><dd><input type="text" name="lessMuscle" placeholder="저근육형" class="bb"></input></dd>
				
				
				<dt>hyperglycemia</dt><dd><input type="text" name="hyperglycemia" placeholder="고혈당증" class="bb"></input></dd>
				<dt>hypoglycemia</dt><dd><input type="text" name="hypoglycemia" placeholder="저혈당증" class="bb"></input></dd>
				<dt>headache</dt><dd><input type="text" name="headache" placeholder="두통" class="bb"></input></dd>
				<dt>dizziness</dt><dd><input type="text" name="dizziness" placeholder="현기증" class="bb"></input></dd>
				<dt>rhinitis</dt><dd><input type="text" name="rhinitis" placeholder="비염" class="bb"></input></dd>
				<dt>migraine</dt><dd><input type="text" name="migraine" placeholder="편두통" class="bb"></input></dd>
				<dt>tiredness2</dt><dd><input type="text" name="tiredness2" placeholder="피로" class="bb"></input></dd>
				<dt>earInfections</dt><dd><input type="text" name="earInfections" placeholder="귀의감염" class="bb"></input></dd>
				<dt>cloggedNose</dt><dd><input type="text" name="cloggedNose" placeholder="코막힘" class="bb"></input></dd>
				<dt>soreThroat</dt><dd><input type="text" name="soreThroat" placeholder="목의통증" class="bb"></input></dd>
				<dt>sinusitis</dt><dd><input type="text" name="sinusitis" placeholder="축농증" class="bb"></input></dd>
				<dt>cough</dt><dd><input type="text" name="cough" placeholder="잔기침" class="bb"></input></dd>
				<dt>eyelid</dt><dd><input type="text" name="eyelid" placeholder="부분눈꺼풀" class="bb"></input></dd>
				<dt>cold</dt><dd><input type="text" name="cold" placeholder="잦은감기" class="bb"></input></dd>
				
				<dt>asthma</dt><dd><input type="text" name="asthma" placeholder="천식" class="bb"></input></dd>
				<dt>arrhythmias</dt><dd><input type="text" name="arrhythmias" placeholder="부정맥" class="bb"></input></dd>
				<dt>wheeze</dt><dd><input type="text" name="wheeze" placeholder="숨쉴때 씨근거리는소리" class="bb"></input></dd>
				<dt>indigestion</dt><dd><input type="text" name="indigestion" placeholder="소화불량" class="bb"></input></dd>
				<dt>burp</dt><dd><input type="text" name="burp" placeholder="잦은트림" class="bb"></input></dd>
				<dt>throwUp</dt><dd><input type="text" name="throwUp" placeholder="구토" class="bb"></input></dd>
				<dt>irritableBowelSyndrome</dt><dd><input type="text" name="과민성대장증후군" placeholder="bloodGroups" class="bb"></input></dd>
				<dt>diarrhea</dt><dd><input type="text" name="diarrhea" placeholder="잦은설사" class="bb"></input></dd>
				<dt>constipation</dt><dd><input type="text" name="constipation" placeholder="변비" class="bb"></input></dd>
				<dt>abdomenGas</dt><dd><input type="text" name="abdomenGas" placeholder="복부가스" class="bb"></input></dd>
				<dt>ulcer</dt><dd><input type="text" name="ulcer" placeholder="위궤양" class="bb"></input></dd>
				<dt>stomachCramps</dt><dd><input type="text" name="stomachCramps" placeholder="잦은위경련" class="bb"></input></dd>
				<dt>anusItch</dt><dd><input type="text" name="anusItch" placeholder="항문주변 가려움증" class="bb"></input></dd>
				<dt>stomachache</dt><dd><input type="text" name="stomachache" placeholder="복부통증" class="bb"></input></dd>
				
				<dt>babyStomachache</dt><dd><input type="text" name="babyStomachache" placeholder="경련성통증과복통" class="bb"></input></dd>
				<dt>myalgia</dt><dd><input type="text" name="myalgia" placeholder="근육통" class="bb"></input></dd>
				<dt>arthralgia</dt><dd><input type="text" name="arthralgia" placeholder="관절통" class="bb"></input></dd>
				<dt>arthritis</dt><dd><input type="text" name="arthritis" placeholder="관절염" class="bb"></input></dd>
				
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>

</ol> 

<div class="div-title">
	파일업로드
</div>
<ol class="my-form">
	<li class="api">
		<c:set var="url" value="photo_upload"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go" enctype="multipart/form-data">
			<h1>이미지등록</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>photo</dt>		<dd><input type="file" name="photo" placeholder="photo" class="bb"></input></dd>
				<dt>path</dt>		<dd><input type="text" name="path" placeholder="path" class="bb" value="profile"></input></dd>
				<dt>isThumb</dt>		<dd><input type="text" name="isThumb" placeholder="0:원본 1:썸네일" class="bb" value=""></input></dd>
				<dt>addThumb(웹전용)</dt>		<dd><input type="text" name="addThumb" placeholder="0:저장안함 1:섬네일저장" class="bb" value=""></input></dd>
				<dt>userId</dt>		<dd><input type="text" name="userId" placeholder="userId" class="bb" ></input></dd>
				<dt>&nbsp;</dt>		<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"사진이 등록되었습니다.","result":true,"path":"/files/profile/201509/","photo":"aa47dde63331b4015e629b7788ff6e9a.jpg"} <br>
					{"message":"e.getMessage().","result":false}
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
</ol>

<div class="div-title">
	메인화면
</div>

<ol class="my-form">
	<li class="api">
		<c:set var="url" value="main_list"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go" >
			<h1>메인화면</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>benefitpage</dt>		<dd><input type="text" name="benefitpage" placeholder="benefitpage" class="bb"></input></dd>
				<dt>neutralpage</dt>		<dd><input type="text" name="neutralpage" placeholder="neutralpage" class="bb" ></input></dd>
				<dt>toxicpage</dt>		<dd><input type="text" name="toxicpage" placeholder="toxicpage" class="bb"></input></dd>
		
				<dt>userId</dt>		<dd><input type="text" name="userId" placeholder="userId" class="bb" ></input></dd>
				<dt>&nbsp;</dt>		<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	<li class="api">
		<c:set var="url" value="search_food"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go" >
			<h1>음식 검색 화면</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				
				<dt>foodName</dt>		<dd><input type="text" name="foodName" placeholder="foodName" class="bb" ></input></dd>
			
				<dt>userId</dt>		<dd><input type="text" name="userId" placeholder="userId" class="bb" ></input></dd>
				<dt>&nbsp;</dt>		<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	<li class="api">
		<c:set var="url" value="search_food_view"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go" >
			<h1>음식 검색 상세화면</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				
				<dt>foodSeq</dt>		<dd><input type="text" name="foodSeq" placeholder="foodSeq" class="bb" ></input></dd>
			
				<dt>userId</dt>		<dd><input type="text" name="userId" placeholder="userId" class="bb" ></input></dd>
				<dt>&nbsp;</dt>		<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
</ol>
<div class="div-title">
	다이어리
</div>
<ol class="my-form">
	
	
	<li class="api">
		<c:set var="url" value="bbs_list"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>다이어리 리스트 </h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>page</dt>	<dd><input type="text" name="page" placeholder="page" class="bb"></input></dd>
				<dt>userId</dt>	<dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>goodCount</dt>	<dd><input type="text" name="goodCount" placeholder="goodCount" class="bb"></input></dd>
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"PAGE_COUNT_PER_PAGING":10,"currentPage":1,"itemCount":2.0,"bbsList":[{"bbsCategory":"","bbsContents":"dd","bbsContentsText":"","bbsHeader":"","bbsSeq":2,"bbsTitle":"","birthday":"","blindCount":0,"commentCount":0,"commentList":[],"dislikeCount":0,"extraContents":"","fileCount":1,"files":"{\"items\":[{\"type\":\"img\",\"width\":740,\"height\":576,\"thumb\":\"/files/bbs/thumb/201602/8778120160219163118.jpg\",\"file\":\"/files/bbs/201602/8778120160219163118.jpg\"}]}","gender":0,"likeCount":0,"linkUrl":"","regDate":"2016-02-19 16:36:52.227","reportCount":0,"userId":"0","userName":"","userType":0,"viewCount":0},{"bbsCategory":"","bbsContents":"ddd","bbsContentsText":"","bbsHeader":"","bbsSeq":1,"bbsTitle":"","birthday":"1988","blindCount":0,"commentCount":2,"commentList":[],"dislikeCount":0,"extraContents":"","fileCount":1,"files":"","gender":0,"likeCount":0,"linkUrl":"","regDate":"2016-02-19 16:29:27.71","reportCount":0,"userId":"tt","userName":"쭈","userType":0,"viewCount":0}],"ITEM_COUNT_PER_PAGE":30}
				</div>
				<div id="${url}-result" class="result-view real">
				
				</div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	<li class="api">
		<c:set var="url" value="bbs_view"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>다이어리 상세화면</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>page</dt>	<dd><input type="text" name="page" placeholder="page" class="bb"></input></dd>
				<dt>userId</dt>	<dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>bbsSeq</dt>	<dd><input type="text" name="bbsSeq" placeholder="bbsSeq" class="bb"></input></dd>
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"PAGE_COUNT_PER_PAGING":10,"currentPage":1,"itemCount":1,"bbs":{"bbsCategory":"","bbsContents":"ddd","bbsContentsText":"","bbsHeader":"","bbsSeq":1,"bbsTitle":"","birthday":"1988","blindCount":0,"commentCount":2,"commentList":[{"RCommentSeq":0,"RLevel":0,"RRegDate":"2016-02-19 17:11:18.767","bbsCategory":"","bbsCommentSeq":1,"bbsContents":"큐큐큐","bbsHeader":"","bbsSeq":1,"bbsTitle":"","birthday":"1988","commentCount":0,"commentList":[],"dislikeCount":0,"extraContents":"","fileCount":1,"files":"{\"items\":[{\"type\":\"img\",\"width\":993,\"height\":575,\"thumb\":\"/files/bbs/thumb/201602/1447520160219170905.png\",\"file\":\"/files/bbs/201602/1447520160219170905.png\"}]}","gender":0,"likeCount":0,"linkUrl":"","regDate":"2016-02-19 17:11:18.767","reportCount":0,"userId":"tt","userName":"쭈","userType":0,"viewCount":0}],"dislikeCount":0,"extraContents":"","fileCount":1,"files":"","gender":0,"likeCount":0,"linkUrl":"","regDate":"2016-02-19 16:29:27.71","reportCount":0,"userId":"tt","userName":"쭈","userType":0,"viewCount":0},"ITEM_COUNT_PER_PAGE":30}
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	<li class="api">
		<c:set var="url" value="bbs_comment_view"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>다이어리 댓글 상세화면</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>page</dt>	<dd><input type="text" name="page" placeholder="page" class="bb"></input></dd>
		
				<dt>bbsCommentSeq</dt>	<dd><input type="text" name="bbsCommentSeq" placeholder="bbsCommentSeq" class="bb"></input></dd>
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"PAGE_COUNT_PER_PAGING":10,"currentPage":1,"itemCount":1,"bc":{"RCommentSeq":0,"RLevel":0,"RRegDate":"2016-02-19 17:11:18.767","bbsCategory":"","bbsCommentSeq":1,"bbsContents":"큐큐큐","bbsHeader":"","bbsSeq":1,"bbsTitle":"","birthday":"1988","commentCount":0,"commentList":[{"RCommentSeq":1,"RLevel":1,"RRegDate":"2016-02-19 17:13:02.49","bbsCategory":"","bbsCommentSeq":2,"bbsContents":"튜튜","bbsHeader":"","bbsSeq":1,"bbsTitle":"","birthday":"1988","commentCount":0,"commentList":[],"dislikeCount":0,"extraContents":"","fileCount":1,"files":"{\"items\":[{\"type\":\"img\",\"width\":993,\"height\":575,\"thumb\":\"/files/bbs/thumb/201602/1447520160219170905.png\",\"file\":\"/files/bbs/201602/1447520160219170905.png\"}]}","gender":0,"likeCount":0,"linkUrl":"","regDate":"2016-02-19 17:13:02.49","reportCount":0,"userId":"tt","userName":"쭈","userType":0,"viewCount":0}],"dislikeCount":0,"extraContents":"","fileCount":1,"files":"{\"items\":[{\"type\":\"img\",\"width\":993,\"height\":575,\"thumb\":\"/files/bbs/thumb/201602/1447520160219170905.png\",\"file\":\"/files/bbs/201602/1447520160219170905.png\"}]}","gender":0,"likeCount":0,"linkUrl":"","regDate":"2016-02-19 17:11:18.767","reportCount":0,"userId":"tt","userName":"쭈","userType":0,"viewCount":0},"ITEM_COUNT_PER_PAGE":30}
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>

	<li class="api">
		<c:set var="url" value="bbs_edit"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>다이어리 쓰기</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt>	<dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>bbsSeq</dt>	<dd><input type="text" name="bbsSeq" placeholder="(등록: 0이하)" class="bb"></input></dd>
				<!-- <dt>bbsHeader</dt>	<dd><input type="text" name="bbsHeader" placeholder="bbsHeader" class="bb"></input></dd> -->
				<dt>bbsTitle</dt>	<dd><input type="text" name="bbsTitle" placeholder="bbsTitle" class="bb"></input></dd>
				<dt>contents</dt>	<dd><input type="text" name="contents" placeholder="내용" class="bb"></input></dd>
				<dt>photo</dt>	<dd><input type="text" name="photo" placeholder="photo (여러개일 경우 ,로 구분해서)" class="bb"></input></dd>
				<dt>thumb</dt>	<dd><input type="text" name="thumb" placeholder="thumb(여러개일 경우 ,로 구분해서)" class="bb"></input></dd>
				<dt>linkUrl</dt>	<dd><input type="text" name="linkUrl" placeholder="linkUrl" class="bb"></input></dd>
			
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	<li class="api">
		<c:set var="url" value="bbs_comment_edit"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>다이어리 댓글 쓰기</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt>	<dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>bbsSeq</dt>	<dd><input type="text" name="bbsSeq" placeholder="bbsSeq" class="bb"></input></dd>
				<dt>bbsCommentSeq</dt>	<dd><input type="text" name="bbsCommentSeq" placeholder="(등록: 0이하)" class="bb"></input></dd>
			<!-- 	<dt>bbsHeader</dt>	<dd><input type="text" name="bbsHeader" placeholder="bbsHeader" class="bb"></input></dd>
				<dt>bbsTitle</dt>	<dd><input type="text" name="bbsTitle" placeholder="bbsTitle" class="bb"></input></dd> -->
				<dt>contents</dt>	<dd><input type="text" name="contents" placeholder="내용" class="bb"></input></dd>
				<dt>photo</dt>	<dd><input type="text" name="photo" placeholder="photo (여러개일 경우 ,로 구분해서)" class="bb"></input></dd>
				<dt>thumb</dt>	<dd><input type="text" name="thumb" placeholder="thumb(여러개일 경우 ,로 구분해서)" class="bb"></input></dd>
				<dt>linkUrl</dt>	<dd><input type="text" name="linkUrl" placeholder="linkUrl" class="bb"></input></dd>
				<dt>rlevel</dt>	<dd><input type="text" name="rlevel" placeholder="댓글 0 댓글의 댓글 1" class="bb"></input></dd>
				<dt>rCommentSeq</dt>	<dd><input type="text" name="rCommentSeq" placeholder="댓글의 댓글일 경우 댓글의 seq를 보낸다 댓글일경우는 -1" class="bb"></input></dd>
			
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
	<li class="api">
		<c:set var="url" value="bbs_delete"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>다이어리 삭제</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt>	<dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>bbsSeq</dt>	<dd><input type="text" name="bbsSeq" placeholder="bbsSeq" class="bb"></input></dd>
				<dt>bbsCommentSeq</dt>	<dd><input type="text" name="bbsCommentSeq" placeholder="본글 삭제시 0 " class="bb"></input></dd>
				
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	<li class="api">
		<c:set var="url" value="bbs_good"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>다이어리 좋아요</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt>	<dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>bbsSeq</dt>	<dd><input type="text" name="bbsSeq" placeholder="bbsSeq" class="bb"></input></dd>
		
				
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
<div class="div-title">
	미션
</div>
<ol class="my-form">
	<li class="api">
		<c:set var="url" value="mission_list"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>미션리스트 </h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>page</dt>	<dd><input type="text" name="page" placeholder="page" class="bb"></input></dd>
				<dt>userId</dt>	<dd><input type="text" name="userId" placeholder="내 참가 미션리스트  유저아이디 이외 빈값." class="bb"></input></dd>
				
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"PAGE_COUNT_PER_PAGING":10,"missionList":[{"appVersion":"","birthday":"","commentList":[],"contentsHtml":"","contentsText":"하루에 커피 2잔만 마시어요","countUser":1,"deviceId":"","deviceName":"","fileName":"","gender":0,"linkUrl":"","missionEndDate":"2016-05-22","missionPushTime":"12:00:00.0000000","missionSeq":3,"missionStartDate":"2016-02-22","osType":"","osVersion":"","pushkey":"","regDate":"2016-02-23","sendPush":0,"title":"하루에 커피 2잔만 자시기","usePushservice":0,"userId":"","userName":"","viewCount":0,"visible":0},{"appVersion":"","birthday":"","commentList":[],"contentsHtml":"","contentsText":"6시 이후 굶자","countUser":0,"deviceId":"","deviceName":"","fileName":"","gender":0,"linkUrl":"","missionEndDate":"2016-04-22","missionPushTime":"18:00:00.0000000","missionSeq":1,"missionStartDate":"2016-02-22","osType":"","osVersion":"","pushkey":"","regDate":"2016-02-22","sendPush":0,"title":"6시 이후 저녁 먹지 않기","usePushservice":0,"userId":"admin","userName":"","viewCount":0,"visible":1},{"appVersion":"","birthday":"","commentList":[],"contentsHtml":"","contentsText":"아침에 과일 한조각으로 버티기","countUser":0,"deviceId":"","deviceName":"","fileName":"","gender":0,"linkUrl":"","missionEndDate":"2016-05-01","missionPushTime":"08:00:00.0000000","missionSeq":2,"missionStartDate":"2016-01-24","osType":"","osVersion":"","pushkey":"","regDate":"2016-01-22","sendPush":0,"title":"아침 과일 한조각 먹기","usePushservice":0,"userId":"admin","userName":"","viewCount":0,"visible":1}],"currentPage":1,"itemCount":3.0,"ITEM_COUNT_PER_PAGE":30}
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
		<li class="api">
		<c:set var="url" value="mission_join"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>미션참여하기 </h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>missionSeq</dt>	<dd><input type="text" name="missionSeq" placeholder="missionSeq" class="bb"></input></dd>
				<dt>userId</dt>	<dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"message":"complete","result":true}
					{"message":"you are already registered","result":false}
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	<li class="api">
		<c:set var="url" value="mission_view"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>미션 상세화면</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>page</dt>	<dd><input type="text" name="page" placeholder="page" class="bb"></input></dd>
				<dt>missionSeq</dt>	<dd><input type="text" name="missionSeq" placeholder="missionSeq" class="bb"></input></dd>
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
					{"PAGE_COUNT_PER_PAGING":10,"ms":{"appVersion":"","birthday":"","commentList":[],"contentsHtml":"","contentsText":"아침에 과일 한조각으로 버티기","countUser":0,"deviceId":"","deviceName":"","fileName":"","gender":0,"linkUrl":"","missionEndDate":"2016-05-01","missionPushTime":"08:00:00.0000000","missionSeq":2,"missionStartDate":"2016-01-24","osType":"","osVersion":"","pushkey":"","regDate":"2016-01-22","sendPush":0,"title":"아침 과일 한조각 먹기","usePushservice":0,"userId":"admin","userName":"","viewCount":0,"visible":1},"currentPage":1,"itemCount":0,"ITEM_COUNT_PER_PAGE":30}
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	<li class="api">
		<c:set var="url" value="mission_comment_view"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>미션 댓글 상세화면</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>page</dt>	<dd><input type="text" name="page" placeholder="page" class="bb"></input></dd>
				<dt>missionCommentSeq</dt>	<dd><input type="text" name="missionCommentSeq" placeholder="missionCommentSeq" class="bb"></input></dd>
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>

	<li class="api">
		<c:set var="url" value="mission_edit_do"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>미션 쓰기(관리자용)</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>missionSeq</dt>	<dd><input type="text" name="missionSeq" placeholder="0등록" class="bb"></input></dd>
				<dt>sendPush</dt>	<dd><input type="text" name="sendPush" placeholder="sendPush" class="bb"></input></dd>
				<dt>title</dt>	<dd><input type="text" name="title" placeholder="title" class="bb"></input></dd>
				<dt>contentsText</dt>	<dd><input type="text" name="contentsText" placeholder="contentsText" class="bb"></input></dd>
				<dt>visible</dt>	<dd><input type="text" name="visible" placeholder="visible" class="bb"></input></dd>
				<dt>fileName</dt>	<dd><input type="text" name="fileName" placeholder="fileName" class="bb"></input></dd>
				<dt>missionStartDate</dt>	<dd><input type="text" name="missionStartDate" placeholder="missionStartDate" class="bb"></input></dd>
			
				<dt>missionEndDate</dt>	<dd><input type="text" name="missionEndDate" placeholder="missionEndDate" class="bb"></input></dd>
				<dt>missionPushTime</dt>	<dd><input type="text" name="missionPushTime" placeholder="missionPushTime" class="bb"></input></dd>
				<dt>thumb</dt>	<dd><input type="text" name="thumb" placeholder="thumb(여러개일 경우 ,로 구분해서)" class="bb"></input></dd>
				<dt>linkUrl</dt>	<dd><input type="text" name="linkUrl" placeholder="linkUrl" class="bb"></input></dd>
			
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li> 
	<li class="api">
		<c:set var="url" value="mission_comment_edit"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>미션 댓글 쓰기</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt>	<dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>missionSeq</dt>	<dd><input type="text" name="missionSeq" placeholder="missionSeq" class="bb"></input></dd>
				<dt>missionCommentSeq</dt>	<dd><input type="text" name="missionCommentSeq" placeholder="(등록: 0이하)" class="bb"></input></dd>
<!-- 				<dt>bbsHeader</dt>	<dd><input type="text" name="bbsHeader" placeholder="bbsHeader" class="bb"></input></dd>
				<dt>missionTitle</dt>	<dd><input type="text" name="missionTitle" placeholder="missionTitle" class="bb"></input></dd> -->
				<dt>missionContents</dt>	<dd><input type="text" name="missionContents" placeholder="내용" class="bb"></input></dd>
				<dt>photo</dt>	<dd><input type="text" name="photo" placeholder="photo (여러개일 경우 ,로 구분해서)" class="bb"></input></dd>
				<dt>thumb</dt>	<dd><input type="text" name="thumb" placeholder="thumb(여러개일 경우 ,로 구분해서)" class="bb"></input></dd>
				<dt>linkUrl</dt>	<dd><input type="text" name="linkUrl" placeholder="linkUrl" class="bb"></input></dd>
				<dt>rlevel</dt>	<dd><input type="text" name="rlevel" placeholder="댓글 0 댓글의 댓글 1" class="bb"></input></dd>
				<dt>rCommentSeq</dt>	<dd><input type="text" name="rCommentSeq" placeholder="댓글의 댓글일 경우 댓글의 seq를 보낸다 댓글일경우는 -1" class="bb"></input></dd>
			
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
	<li class="api">
		<c:set var="url" value="mission_delete"></c:set>
		<form method="post" id="${url}" name="${url}Form" action="/m/${url}.go">
			<h1>미션 삭제</h1>
			<h2 class="page-title">/m/${url}.go</h2>
			<dl class="in">
				<dt>userId</dt>	<dd><input type="text" name="userId" placeholder="userId" class="bb"></input></dd>
				<dt>missionSeq</dt>	<dd><input type="text" name="missionSeq" placeholder="missionSeq" class="bb"></input></dd>
				<dt>missionCommentSeq</dt>	<dd><input type="text" name="missionCommentSeq" placeholder="본글 삭제시 0 " class="bb"></input></dd>
				
				<dt>&nbsp;</dt>	<dd><button type="submit" onclick="formSubmit('${url}');" class="bb round green" style="width:274px;">확인</button></dd>
			</dl>
			<div class="out">
				<div class="result-view">
				
				</div>
				<div id="${url}-result" class="result-view real"></div>
			</div>
			<div class="clear"></div>
		</form>
	</li>
	
	
	
</ol>	
	
	
	
	

</ol>
</body>
</html>