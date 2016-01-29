StayOTheEdge.Search = {
	templateMorePostsContent : '<p class="post-name"> <a href="{href}" onclick="javascript:StayOTheEdge.getPost({postId}); return false;"> <u>{postSummary}</u></a></p>',

	searchPosts : function(term, element) {
		element = $(element);
		var offset = parseInt(element.attr('offset'));
		var loadMoreElement = element.parent().parent();
		var postsParentElement = loadMoreElement.prev().find(
				'.edge-container > .edge-text');
		document.body.style.cursor = "progress";

		DwrStayOnEdgeService.searchPosts(term, offset, {
			callback : function(posts) {
				if (posts && posts.length > 0) {
					var newOffset = 0;

					for (var i = 0; i < posts.length; i++) {
						var post = posts[i];
						var postCnt = StayOTheEdge.templateMorePostsContent
								.replace('{href}', '/stay_on_the_edge/' + post.slug + '/view.in')
								.replace('{postId}', post.postId).replace(
										'{postSummary}', post.summary);
						postsParentElement.append(postCnt);
						newOffset = post.createdDate;
					}

					element.attr('offset', Date.parse(newOffset));
				} else {
					loadMoreElement.remove();
				}

				document.body.style.cursor = "auto";
			},
			errorHandler : function(message) {
				console.log(message);
			}
		});
	}
}