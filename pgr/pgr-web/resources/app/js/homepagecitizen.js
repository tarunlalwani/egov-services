/*
 * eGov suite of products aim to improve the internal efficiency,transparency,
 * accountability and the service delivery of the government  organizations.
 *
 *  Copyright (C) 2016  eGovernments Foundation
 *
 *  The updated version of eGov suite of products as by eGovernments Foundation
 *  is available at http://www.egovernments.org
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program. If not, see http://www.gnu.org/licenses/ or
 *  http://www.gnu.org/licenses/gpl.html .
 *
 *  In addition to the terms of the GPL license to be adhered to in using this
 *  program, the following additional terms are to be complied with:
 *
 *      1) All versions of this program, verbatim or modified must carry this
 *         Legal Notice.
 *
 *      2) Any misrepresentation of the origin of the material is prohibited. It
 *         is required that all modified versions of this material be marked in
 *         reasonable ways as different from the original version.
 *
 *      3) This license does not grant any rights to any user of the program
 *         with regards to rights under trademark law for use of the trade names
 *         or trademarks of eGovernments Foundation.
 *
 *  In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */
var RequestInfo = new $.RequestInfo(localStorage.getItem("auth"));
var requestInfo = {};
requestInfo['RequestInfo'] = RequestInfo.requestInfo;
$(document).ready(function()
{	
	preventBack();

	var obj = {};
	
	var userArray = [localStorage.getItem('id')]

	obj['RequestInfo'] = RequestInfo.requestInfo;
	obj['id'] = userArray;
	obj['tenantId'] = 'default';

	$.ajax({
		url : "/user/_search",
		type : 'POST',
		processData : false,
		contentType: "application/json",
		data : JSON.stringify(obj),
		success : function(response){
			$('.profile-text').html(response.user[0].userName);
		},
		error : function(){
			bootbox.alert('User api failed');
		}
	});
	
	$(".ico-menu").bind('mouseover', function () {
		$(this).addClass('open');
	});
	
	$(".ico-menu").bind('mouseout', function () { 
		$(this).removeClass('open');
	});

	$('.menu-item').click(function(e){
		$('.citizen-screens').hide();
		$('.hr-menu li').removeClass('active');
		$(this).parent().addClass('active');
		$($(this).data('show-screen')).show();
	});

	loadComplaints();

	$('.inboxLoad').click(function(){
		loadComplaints();
	});
	
	$('.newServices').click(function(){
		getAllServices();
	});

	$(document).on('click','.open_popup',function(e){
		var srn = $(this).data('srn');
		openPopUp('view-complaint.html?srn='+srn,srn);
		e.stopPropagation();
	});

	var password = false;
	
	$('.check-password').blur(function(){
		if(($('#new-pass').val()!="") && ($('#retype-pass').val()!=""))
		{
			if ($('#new-pass').val() === $('#retype-pass').val()) {
					password = true;
					$('.password-error').hide();
				}else{
					password = false;
					$('.password-error').show();
					$('#retype-pass').addClass('error');
			}
		}
	});	

	$('#resetpassword').click(function(){
		
		$.validator.addMethod("passwordvalidate",function(value){
		    return /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[?!@$^*-`(){}])(?!.*[&<>#%"'/\\ ]).{8,32}$/.test(value);
		},translate('core.error.password'));

		jQuery.validator.addClassRules({
			passwordvalidate : { passwordvalidate : true }    
		});

		var reqObj = {};
		reqObj['RequestInfo'] = RequestInfo.requestInfo;
		reqObj['existingPassword'] = $('#old-pass').val();
		reqObj['newPassword'] = $('#new-pass').val();
		reqObj['tenantId'] = 'default';

		if($('#password-form').valid() && password){
			$.ajax({
				url: '/user/password/_update',
				type : 'POST',
				processData : false,
				contentType: "application/json",
				data : JSON.stringify( reqObj ),
				success : function(response){
					$('.change-password').modal('hide');
					bootbox.alert(translate('core.msg.success.password.updated'));
				},
				error : function(xhr, status, error){
					$('.change-password').modal('hide');
					var response = JSON.parse(xhr.responseText);
					var errorMsg = response.error.fields[0].code;
					bootbox.alert(translate(errorMsg));
				}
			});
		}else{

		}
	});

	$('.services .content').matchHeight();

	$('#search').keyup(function(e){
		var rule = '*'+$(this).val()+'*';
		if(e.keyCode == 8){
		  $('.services').show();
		}
		$(".services-item .services:visible").each(function(){
		  var testStr = $(this).find('.content').html().toLowerCase();
		  console.log(testStr, rule, matchRuleShort(testStr, rule))
		  if(matchRuleShort(testStr, rule))
		    $(this).show();
		  else
		    $(this).hide();
		});
	});
		
});

$(document).on('click','.services .content',function(){
	sCode = $(this).data('code');
	openPopUp('create-complaint.html?code='+sCode,sCode);
});

function loadComplaints(){
	$.ajax({
		url : "/pgr/seva/_search?tenantId=default&user_id="+localStorage.getItem("id"),
		type : 'POST',
		dataType: 'json',
		processData : false,
		contentType: "application/json",
		data : JSON.stringify(requestInfo),
		beforeSend : function(){
			showLoader();
		},
		success : function(response){
			//$("#grievance-template").html('');
			var source   = $("#grievance-template").html();
			var template = Handlebars.compile(source);
			var html = template(response.serviceRequests);
			$('.reloadtemplate').remove();
			$('.grievanceresponse').append(html);
		},
		error : function(){
			bootbox.alert('Error!')
		},
		complete : function(){
			hideLoader();
			search($('.searchinbox'));
		}
	});
}

function search(elem) {
	var searchText = $(elem).val(); 
	if($.trim(searchText)){
		$(".reloadtemplate").each(function() {
			console.log('searchText', $.trim(searchText));
	         var $this = $(this)
	         if ($this.find('div').text().toUpperCase().search(searchText.toUpperCase()) === -1) {
	             $this.hide();
	         }else {
		         $this.show();
		     }
	    });
	}else{
		$(".reloadtemplate").each(function() {
			var $this = $(this);
			$this.show();
		});
	}
}

function getAllServices(){
	$.ajax({
		url: "/pgr/services/_search?type=all&tenantId=default",
		type : 'POST',
		data : JSON.stringify(requestInfo),
		dataType: 'json',
		processData : false,
		contentType: "application/json",
		beforeSend : function(){
			showLoader();
		},
		success : function(data) {
			$('#service_list').html('');
			$.each(data.complaintTypes, function(i,obj){
				$('#service_list').append('<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 services"><a href="javascript:void(0)"> <div class="content a" data-code="'+obj.serviceCode+'">'+obj.serviceName+'</div> </a></div>');
			});
			$('.services .content').matchHeight();
		},
		complete : function(){
			hideLoader();
			search($('.searchinbox'));
		}
	});
}

Handlebars.registerHelper('contains', function(string, checkString) {
	var n = string.includes(checkString);
	return n;
});

//Short code
function matchRuleShort(str, rule) {
  return new RegExp("^" + rule.split("*").join(".*") + "$").test(str);
}