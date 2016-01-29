OurPractice.All = {
	templateFullContent : '<div class="post-content-full">{content}</div>',

	showFullContent : function(postId, el) {
		document.body.style.cursor = "progress";

		$('.post-item').each(function() {
			$(this).find('.post-content:hidden').show();
			$(this).find('.post-content-full:visible').hide();
		});

		var $post = $(el).parent();
		if ($post.find('.post-content-full:hidden').length > 0) {
			$post.find('.post-content').hide();
			$post.find('.post-content-full:hidden').show();
			document.body.style.cursor = "auto";
			return;
		}

		DwrOurPracticeService.getOurPracticeById(postId, {
			callback : function(post) {
				var postCnt = OurPractice.All.templateFullContent.replace(
						'{content}', post.content);
				$post.find('.post-content').after(postCnt);
				$post.find('.post-content').hide();
				document.body.style.cursor = "auto";
			},
			errorHandler : function(message) {
				console.log(message);
			}
		});
	},
	removeOtherPostItems : function() {
		$('#edge_left').empty();

		$('#edge_left').append('<div class="post-item"></div>');
	}
}