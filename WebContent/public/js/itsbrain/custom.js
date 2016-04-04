// 暂时废弃
$(function() {

	//===== ToTop =====//

	$().UItoTop({ easingType: 'easeOutQuart' });


	//===== Breadcrumbs =====//	
	// not used 现在为静态
	// $("#breadCrumb").jBreadCrumb();

	
	//===== Form elements styling =====//
	// js动态改值太矬比 (在itsbrain/forms/forms.js)
	// $('form').jqTransform({imgPath:'/public/images/forms'});
	

	//===== Tooltip =====//
	// not used
	/*$('.leftDir').tipsy({fade: true, gravity: 'e'});
	$('.rightDir').tipsy({fade: true, gravity: 'w'});
	$('.topDir').tipsy({fade: true, gravity: 's'});
	$('.botDir').tipsy({fade: true, gravity: 'n'});*/

	
	//===== Information boxes =====//
	// not used
	/*$(".hideit").click(function() {
		$(this).fadeOut(400);
	});*/


	//===== User nav dropdown =====//		
	// not used
	/*$('.dd').click(function () {
		$('ul.menu_body').slideToggle(100);
	});
	
	$('.acts').click(function () {
		$('ul.actsBody').slideToggle(100);
	});*/
	

	//===== Left navigation submenu animation =====//	
	// not used
	/*$("ul.sub li a").hover(function() {
	$(this).stop().animate({ color: "#3a6fa5" }, 400);
	},function() {
	$(this).stop().animate({ color: "#494949" }, 400);
	});*/
	
	
	//===== Image gallery control buttons =====//
	// not used
	/*$(".pics ul li").hover(
		function() { $(this).children(".actions").show("fade", 200); },
		function() { $(this).children(".actions").hide("fade", 200); }
	);*/
	

	//===== jQuery UI sliders =====//	
	// not used
	/*$( ".uiSlider" ).slider();
	
	$( ".uiSliderInc" ).slider({
		value:100,
		min: 0,
		max: 500,
		step: 50,
		slide: function( event, ui ) {
			$( "#amount" ).val( "$" + ui.value );
		}
	});
	$( "#amount" ).val( "$" + $( ".uiSliderInc" ).slider( "value" ) );
	
	$( ".uiRangeSlider" ).slider({
		range: true,
		min: 0,
		max: 500,
		values: [ 75, 300 ],
		slide: function( event, ui ) {
			$( "#rangeAmount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
		}
	});
	$( "#rangeAmount" ).val( "$" + $( ".uiRangeSlider" ).slider( "values", 0 ) +" - $" + $( ".uiRangeSlider" ).slider( "values", 1 ));
	
	$( ".uiMinRange" ).slider({
		range: "min",
		value: 37,
		min: 1,
		max: 700,
		slide: function( event, ui ) {
			$( "#minRangeAmount" ).val( "$" + ui.value );
		}
	});
	$( "#minRangeAmount" ).val( "$" + $( ".uiMinRange" ).slider( "value" ) );
	
	$( ".uiMaxRange" ).slider({
		range: "max",
		min: 1,
		max: 100,
		value: 20,
		slide: function( event, ui ) {
			$( "#maxRangeAmount" ).val( ui.value );
		}
	});
	$( "#maxRangeAmount" ).val( $( ".uiMaxRange" ).slider( "value" ) );	
	
	$( "#eq > span" ).each(function() {
		var value = parseInt( $( this ).text(), 10 );
		$( this ).empty().slider({
			value: value,
			range: "min",
			animate: true,
			orientation: "vertical"
		});
	});*/
	

	//===== Tabs =====//
	// not used
	/*$.fn.simpleTabs = function(){ 
	
		//Default Action
		$(this).find(".tabContent").hide(); //Hide all content
		$(this).find("ul.tabs li:first").addClass("activeTab").show(); //Activate first tab
		$(this).find(".tabContent:first").show(); //Show first tab content
	
		//On Click Event
		$("ul.tabs li").click(function() {
			$(this).parent().parent().find("ul.tabs li").removeClass("activeTab"); //Remove any "active" class
			$(this).addClass("activeTab"); //Add "active" class to selected tab
			$(this).parent().parent().find(".tabContent").hide(); //Hide all tab content
			var activeTab = $(this).find("a").attr("href"); //Find the rel attribute value to identify the active tab + content
			$(activeTab).show(); //Fade in the active content
			return false;
		});
	
	};//end function

	$("div[class^='widget']").simpleTabs(); //Run function on any div with class name of "Simple Tabs"
	*/


	//===== Accordion =====//		
	// not used
	/*$('div.menu_body:eq(0)').show();
	$('.acc .head:eq(0)').show().css({color:"#2B6893"});
	
	$(".acc .head").click(function() {	
		$(this).css({color:"#2B6893"}).next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
		$(this).siblings().css({color:"#404040"});
	});*/


	//===== Placeholder for all browsers =====//
	
	$("input").each(
		function(){
			if($(this).val()=="" && $(this).attr("placeholder")!=""){
			$(this).val($(this).attr("placeholder"));
			$(this).focus(function(){
				if($(this).val()==$(this).attr("placeholder")) $(this).val("");
			});
			$(this).blur(function(){
				if($(this).val()=="") $(this).val($(this).attr("placeholder"));
			});
		}
	});

});