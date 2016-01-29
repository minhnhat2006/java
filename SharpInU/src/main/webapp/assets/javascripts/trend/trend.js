Trend = {
	templateContent : '<h3>{summary}</h3><div id="edge_left_container"><p>{content}</p></div>',
	templateMoreTrendsContent : '<p class="post-name"><a href="javascript:Trend.getTrend({postId});"><u>{postSummary}</u></a></p>',
	templateMoreTrendsAllContent : '<div class="post-item"><a href="#" onclick="Trend.All.showFullContent({postId}, this); return false;"><u>{postTitle}</u></a><div id="edge_left_container"><p class="post-content">{postSummary}</p</div</div>',
	templateMoreSearchTrendsContent : '<p class="post-name"> <a href="javascript:Trend.getTrend({trendId});"> <u>{trendSummary}</u></a></p>',

	search : function(search) {
		if (search && $.trim(search) !== '') {
			window.location.href = contextPath + "/trend/" + search
					+ "/search.in";
		} else {
			window.location.href = contextPath + "/trend.in";
		}
	},

	getTrend : function(postId) {
		document.body.style.cursor = "progress";

		DwrTrendService.getTrendById(postId, {
			callback : function(post) {
				var postCnt = Trend.templateContent.replace('{summary}',
						post.summary).replace('{content}', post.content);
				$('#edge_left > .post-item').empty().html(postCnt);
				document.body.style.cursor = "auto";
			},
			errorHandler : function(message) {
				console.log(message);
			}
		});
	},

	loadMoreTrends : function(categoryId, element) {
		element = $(element);
		var pageIndex = parseInt(element.attr('page')) + 1;
		var loadMoreElement = element.parent();
		document.body.style.cursor = "progress";

		DwrTrendService.loadMoreTrends(categoryId, pageIndex, {
			callback : function(posts) {
				if (posts && posts.length > 0) {
					for (var i = 0; i < posts.length; i++) {
						var post = posts[i];
						var postCnt = Trend.templateMoreTrendsContent.replace(
								'{postId}', post.postId).replace(
								'{postSummary}', post.summary);
						loadMoreElement.before(postCnt);
					}

					element.attr('page', pageIndex);
				} else {
					loadMoreElement.remove();
				}

				document.body.style.cursor = "auto";
			},
			errorHandler : function(message) {
				console.log(message);
			}
		});
	},

	searchTrends : function(term, element) {
		element = $(element);
		var offset = parseInt(element.attr('offset'));
		var loadMoreElement = element.parent().parent();
		var trendsParentElement = loadMoreElement.prev().find(
				'.edge-container > .edge-text');
		document.body.style.cursor = "progress";

		DwrTrendService.searchTrends(term, offset, {
			callback : function(practices) {
				if (trends && trends.length > 0) {
					var newOffset = 0;

					for (var i = 0; i < trends.length; i++) {
						var trend = trends[i];
						var trendCnt = Trend.templateMoreSearchTrendsContent
								.replace('{trendId}', trend.trendId).replace(
										'{trendSummary}', trend.summary);
						trendsParentElement.append(trendCnt);
						newOffset = trend.createdDate;
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

$(document).ready(function() {
	$("#search > input").keyup(function(e) {
		if (e.keyCode == 13) {
			Trend.search($(this).val());
		}
	});

	$("#search a").click(function() {
		var term = $("#search > input").val();
		Trend.search(term);
	});
});