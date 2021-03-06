var HomePage = {
	bxSlider : null,
	myCarousel : null,
	init : function() {
		var _self = this;
		_self.myCarousel = $('#myCarousel');
		_self.initBXSlider();
		_self.handleCarouselEvent();

	},
	handleCarouselEvent : function() {
		var _self = this;
		$(_self.myCarousel).carousel({
			interval : 5000,
		});
		$(_self.myCarousel).on('slide.bs.carousel', function(e) {
			var index = $(e.relatedTarget).attr('data-slide-index').toString();
			if (typeof _self.bxSlider !== 'undefined') {
				var currentItem = _self.bxSlider.getCurrentSlide().toString();
				if ($(this).find('div').hasClass('active-slide')) {
					return;
				} else {
					if (index === currentItem) {
						_self.bxSlider.goToPrevSlide();
					} else {
						_self.bxSlider.goToNextSlide();

					}
				}
			}
		});

	},
	handleLeftMenuEvent : function() {
		var _self = this;
	},
	initBXSlider : function() {
		var _self = this;
		_self.bxSlider = $('.menuSlider').bxSlider(
				{
					mode : 'vertical',
					slideMargin : 0,
					minSlides : 3,
					moveSlides : 1,
					controls : false,
					onSlideAfter : function(currentSlideNumber, totalSlideQty,
							currentSlideHtmlObject) {
						$('.active-slide').removeClass('active-slide');
						if (currentSlideHtmlObject === 2) {
							$('.menuSlider>li.return-default').find('div')
									.addClass('active-slide');
						} else {
							$('.menuSlider>li:not(.bx-clone)').eq(
									currentSlideHtmlObject + 1).find('div')
									.addClass('active-slide');
						}
					}
				});
		// custom pager control of bxSlider
		$('.custom-link').each(function(index, elem) {
			$(elem).click(function() {
				var index = $(this).attr('data-slide-index').toString();
				var currentItem = _self.bxSlider.getCurrentSlide().toString();
				if ($(this).find('div').hasClass('active-slide')) {
					return;
				} else {
					if (index === currentItem) {
						_self.bxSlider.goToPrevSlide();
						$(_self.myCarousel).carousel('prev');
					} else {
						_self.bxSlider.goToNextSlide();
						$(_self.myCarousel).carousel('next');
					}
				}

			});
		});

	}

};
$(document).ready(function() {
	HomePage.init();
});
