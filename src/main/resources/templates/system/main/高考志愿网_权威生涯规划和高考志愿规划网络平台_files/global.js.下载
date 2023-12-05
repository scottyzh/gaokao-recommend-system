/*-------------------------------------------------------------------------------------------------------------------------------*/
/*This is main JS file that contains custom style rules used in this template*/
/*-------------------------------------------------------------------------------------------------------------------------------*/
/* Template Name: CrtveHost*/
/* Version: 1.0 */
/* Author: Creative Template*/
/* Copyright: (C) 2016 */
/*-------------------------------------------------------------------------------------------------------------------------------*/

/*--------------------------------------------------------*/
/* TABLE OF CONTENTS: */
/*--------------------------------------------------------*/
/* 01 - VARIABLES */
/* 02 - page calculations */
/* 03 - function on document ready */
/* 04 - function on page load */
/* 05 - function on page resize */
/* 06 - function on page scroll */
/* 07 - swiper sliders */
/* 08 - buttons, clicks, hovers */
/* 09 - form elements - checkboxes and radiobuttons */
/*-------------------------------------------------------------------------------------------------------------------------------*/

;(function($, window, document, undefined) {


	"use strict";

	/*================*/
	/* 01 - VARIABLES */
	/*================*/
	var swipers = [], winW, winH, winScroll, _isresponsive, xsPoint = 767, smPoint = 991, mdPoint = 1199;


	/*========================*/
	/* 02 - page calculations */
	/*========================*/
	function pageCalculations(){
		winW = $(window).width();
		winH = $(window).height();
		if($('.open-icon').is(':visible')) _isresponsive = true;
		else _isresponsive = false;
		if($('.main-banner-height').length) $('.main-banner-height').css({'height':winH - $('header').height() - $('.container-above-header').height() - $('.banner-tabs').height()});
	}


	/*=================================*/
	/* 03 - function on document ready */
	/*=================================*/

	//init bootstrap popovers
	$('[data-toggle="popover"]').popover();

	//center all images inside containers
	$('.center-image').each(function(){
		var bgSrc = $(this).attr('src');
		$(this).parent().addClass('background-block').css({'background-image':'url('+bgSrc+')'});
		$(this).hide();
	});


	/*============================*/
	/* 04 - function on page load */
	/*============================*/
	$(window).load(function(){
		//scroll page to top by default
		$('body, html').animate({'scrollTop':'0'}, 2);
		$('body').addClass('loaded');
		pageCalculations();
		initSwiper();
		$('#loader-wrapper').fadeOut(500);
	});


	/*==============================*/
	/* 05 - function on page resize */
	/*==============================*/
	function resizeCall(){
		pageCalculations();
		$('.swiper-container.initialized').each(function(){
			swipers['swiper-'+$(this).attr('id')].reInit();
		});
	}
	$(window).resize(function(){
		resizeCall();
	});
	window.addEventListener("orientationchange", function() {
		resizeCall();
	}, false);


	/*==============================*/
	/* 06 - function on page scroll */
	/*==============================*/
	var _buffer = null, subHeaderHeight = ($('.subheader').length)?$('.subheader').height():0;
	$(window).scroll(function(){
		winScroll = $(window).scrollTop();
		if($('body').hasClass('header-moved') && !_isresponsive) if(winScroll>=122) $('header').addClass('fixed-top');
		else $('header').removeClass('fixed-top');

		if(winScroll>300 && !$('.header-floated').length) $('header').addClass('reduced');
		else $('header').removeClass('reduced');

		if($('.subheader').length){

			if(winScroll>500) $('.subheader').addClass('active');
			else $('.subheader').removeClass('active');

			if ( !_buffer ) {
				_buffer = setTimeout(function () {
	 
	         	$('.scroll-to-block').each(function( index, element ) {
					if($(element).offset().top<=winScroll+subHeaderHeight && $(element).offset().top+$(element).height()>winScroll+subHeaderHeight){
						$('.scroll-to-link.active').removeClass('active');
						$('.scroll-to-link').eq(index).addClass('active');
					}
				});

	            _buffer = null;
	 
	        	}, 500);
	      	}
		}
	});


	/*=====================*/
	/* 07 - swiper sliders */
	/*=====================*/
	function initSwiper(){
		var initIterator = 0;
		$('.swiper-container').each(function(){								  
			var $t = $(this);								  

			var index = 'swiper-unique-id-'+initIterator;

			$t.addClass('swiper-'+index + ' initialized').attr('id', index);
			$t.find('.pagination').addClass('pagination-'+index);

			var autoPlayVar = parseInt($t.attr('data-autoplay'));
			var centerVar = parseInt($t.attr('data-center'));
			var simVar = ($t.closest('.circle-description-slide-box').length)?false:true;
			var slidesPerViewVar = $t.attr('data-slides-per-view');
			var xsValue, smValue, mdValue, lgValue;
			if(slidesPerViewVar == 'responsive'){
				slidesPerViewVar = 1;
				xsValue = $t.attr('data-xs-slides');
				smValue = $t.attr('data-sm-slides');
				mdValue = $t.attr('data-md-slides');
				lgValue = $t.attr('data-lg-slides');
			}
			else if(slidesPerViewVar != 'auto'){
				slidesPerViewVar = parseInt(slidesPerViewVar);
			}
			var loopVar = parseInt($t.attr('data-loop'));
			var speedVar = parseInt($t.attr('data-speed'));


			swipers['swiper-'+index] = new Swiper('.swiper-'+index,{
				speed: speedVar,
				pagination: '.pagination-'+index,
				loop: loopVar,
				paginationClickable: true,
				autoplay: autoPlayVar,
				slidesPerView: slidesPerViewVar,
				keyboardControl: true,
				calculateHeight: true, 
				simulateTouch: simVar,
				centeredSlides: centerVar,
				onInit: function(swiper){
					if($t.attr('data-slides-per-view')=='responsive') updateSlidesPerView(xsValue, smValue, mdValue, lgValue, swiper);	
					if($t.find('.swiper-slide').length>swiper.params.slidesPerView) $t.removeClass('hide-pagination');
					else $t.addClass('hide-pagination');
				},
				onSlideChangeStart: function(swiper){
					var activeIndex = (loopVar===true)?swiper.activeIndex:swiper.activeLoopIndex;
					if($t.closest('.testimonials-container').length){
						$t.closest('.testimonials-wrapper').find('.testimonials-icons .entry div.active').removeClass('active');
						$t.closest('.testimonials-wrapper').find('.testimonials-icons .entry div').eq(activeIndex).addClass('active');
					}
					if($t.closest('.block.type-10').length){
						$t.closest('.block.type-10').find('.tab-entry.active').removeClass('active');
						$t.closest('.block.type-10').find('.tab-entry').eq(activeIndex).addClass('active');
					}
				},
				onSlideClick: function(swiper){
					if($t.closest('.circle-slide-box').length) swiper.swipeTo(swiper.clickedSlideIndex);
				}
			});
			swipers['swiper-'+index].reInit();
			//swipers['swiper-'+index].resizeFix();
			if($t.find('.default-active').length) swipers['swiper-'+index].swipeTo($t.find('.swiper-slide').index($t.find('.default-active')), 0);

			initIterator++;
		});

		$('.swiper-container.connected-to-bottom-swiper').each(function(){
			var $t = $(this);
			if($t.closest('.block').find('.connected-to-top-swiper').length){
				swipers['swiper-'+$t.attr('id')].addCallback('SlideChangeStart', function(swiper){
					swipers['swiper-'+$t.closest('.block').find('.connected-to-top-swiper').attr('id')].swipeTo(swiper.activeIndex);
				});
			}
		});

	}


	function updateSlidesPerView(xsValue, smValue, mdValue, lgValue, swiper){
		if(winW>mdPoint) swiper.params.slidesPerView = lgValue;
		else if(winW>smPoint) swiper.params.slidesPerView = mdValue;
		else if(winW>xsPoint) swiper.params.slidesPerView = smValue;
		else swiper.params.slidesPerView = xsValue;
	}

	//swiper arrows
	$('.swiper-arrow.left').click(function(){
		swipers['swiper-'+$(this).parent().attr('id')].swipePrev();
	});

	$('.swiper-arrow.right').click(function(){
		swipers['swiper-'+$(this).parent().attr('id')].swipeNext();
	});

	$('.testimonials-arrow.left').click(function(){
		swipers['swiper-'+$(this).closest('.testimonials-wrapper').find('.testimonials-container .swiper-container').attr('id')].swipePrev();
	});

	$('.testimonials-arrow.right').click(function(){
		swipers['swiper-'+$(this).closest('.testimonials-wrapper').find('.testimonials-container .swiper-container').attr('id')].swipeNext();
	});


	/*==============================*/
	/* 08 - buttons, clicks, hovers */
	/*==============================*/

	//menu click in responsive
	$('.submenu-icon').click(function(){
		$(this).parent().toggleClass('opened');
	});

	//open and close responsive menu
	$('.open-icon, .close-icon').click(function(){
		$('header').toggleClass('active');
	});

	//accordeon
	$('.accordeon-entry .title').click(function(){
		$(this).parent().toggleClass('active');
		$(this).next().slideToggle(300);
	});

	//testimonials
	$('.testimonials-icons .entry div').click(function(){
		if($(this).hasClass('active')) return false;
		var val = $(this).parent().parent().find('.entry').index($(this).parent());
		swipers['swiper-'+$(this).closest('.testimonials-wrapper').find('.testimonials-container .swiper-container').attr('id')].swipeTo(val);

		var parentSwiper = $(this).closest('.testimonials-wrapper').find('.testimonials-icons').parent();
		if(parentSwiper.hasClass('swiper-container')) swipers['swiper-'+parentSwiper.attr('id')].swipeTo(val);
		$(this).parent().parent().find('div.active').removeClass('active');
		$(this).addClass('active');
	});

	//circle block - mouseovers
	$('.circle-entry').hover(function(){
		$(this).closest('.circle-wrapper').find('.big-circle-entry[data-rel="'+$(this).attr('data-rel')+'"]').addClass('visible');
	}, function(){
		$('.big-circle-entry.visible').removeClass('visible');
	});

	//main banner tabs
	$('.tab-entry').click(function(){
		if($(this).hasClass('active')) return false;
		var val = $(this).parent().find('.tab-entry').index(this);
		swipers['swiper-'+$(this).closest('.block').find('.swiper-container').attr('id')].swipeTo(val);
		$(this).parent().find('.tab-entry.active').removeClass('active');
		$(this).addClass('active');
	});

	//change prefix select in block "TYPE-8"
	$('.prefix .selected-text').click(function(){
		$(this).parent().find('.prefix-drop-down').toggle();
	});

	$('.prefix-drop-down div').click(function(){
		$(this).parent().find('.active').removeClass('active');
		$(this).addClass('active');
		$(this).closest('.prefix').find('.selected-text').text($(this).text());
		$(this).parent().hide();
	});

	$('.prefix').mouseleave(function(){
		$('.prefix-drop-down').hide();
	});

	//tabs switch
	$('.tabs-desktop div').click(function(){
		var $t = $(this);
		var curVal = $t.parent().find('div').index(this);

		$t.closest('.tabs-wrapper').find('.tabs-container:visible').fadeOut(300, function(){
			$t.closest('.tabs-wrapper').find('.tabs-container').eq(curVal).fadeIn(300);	
		});
		
		$t.parent().find('.active').removeClass('active');
		$t.addClass('active');
		$t.parent().parent().find('.tabs-select-text .text').text($t.text());
	});

	$('.tabs-switch-box select').change(function(){
		var curValue = $(this).val();
		$(this).parent().find('.tabs-desktop div').filter(function (){return $(this).text() == curValue;}).click();
	});

	$('.faq-switcher .side-menu-item').click(function(){
		if($(this).hasClass('active')) return false;

		var $t = $(this);
		var curVal = $t.parent().find('.switch').index(this);

		$(this).closest('.row').find('.switch-container:visible').fadeOut(300, function(){
			$t.closest('.row').find('.switch-container').eq(curVal).fadeIn(300);	
		});

		$t.parent().find('.active').removeClass('active');
		$t.addClass('active');
	});

	//video player
	$(document).on('click', '.video-open', function(){
		$('.video-player').addClass('active');
		var videoSource = $(this).attr('data-src');
		setTimeout(function(){$('.video-player iframe').attr('src', videoSource);}, 1000);
	});

	$('.video-player .close-iframe').click(function(){
		$('.video-player iframe').attr('src', '');
		setTimeout(function(){$('.video-player').removeClass('active');}, 1000);
		
	});

	//scrolling to some block
	$('.scroll-to-link').click(function(){
		var index = $('.scroll-to-link').index(this);
		$('body, html').animate({'scrollTop':$('.scroll-to-block').eq(index).offset().top - subHeaderHeight}, 500);
	});

	//theme config - changing color scheme
	$('.theme-config .open').click(function(){
		$('.theme-config').toggleClass('active');
	});

	$('.theme-config .colours-wrapper .entry').click(function(){
		var newColour = $(this).attr('data-colour');
		if($(this).hasClass('active')) return false;
		$(this).parent().find('.active').removeClass('active');
		$(this).addClass('active');
		$('body').removeClass('colour-1 colour-2 colour-3').addClass(newColour);
		$('#logo img').attr('src', 'img/logo'+newColour+'.png');
	});

	/*==================================================*/
	/* 09 - form elements - checkboxes and radiobuttons */
	/*==================================================*/
	$('.checkbox-entry.checkbox label').click(function(){
		$(this).parent().toggleClass('active');
		$(this).parent().find('input').click();
	});

	$('.checkbox-entry.radio label').click(function(){
		$(this).parent().find('input').click();
		if(!$(this).parent().hasClass('active')){
			var nameVar = $(this).parent().find('input').attr('name');
			$('.checkbox-entry.radio input[name="'+nameVar+'"]').parent().removeClass('active');
			$(this).parent().addClass('active');
		}
	});

	$('.js-contact-form').submit(function(e){

    	$('.ajax-loader').show();

    	var url = 'mail.php',
    		form = this;

    	$(form).find('[name="fields[code]"]').remove();

    	function result(class_key, data){
      		setTimeout(function(){
        		$('.ajax-loader').hide();
        		$('.ajax-result').find(class_key).show().text(data);
      		},500);
    	}

    	$.ajax({
      		type: "POST",
      		url: url,
      		data: $(form).serialize(),
    	})
    	.done(function(data) {
      		result('.success', data);
    	}).error(function(data){
      		result('.error', data);
    	})

    	e.preventDefault(); 

  	});
})(jQuery, window, document);