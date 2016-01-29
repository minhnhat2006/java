OurPractice = {
	templateContent : '<h3>{summary}</h3><div id="edge_left_container"><p>{content}</p></div>',
	templateMoreOurPracticesContent : '<p class="post-name"><a href="javascript:OurPractice.getOurPractice({postId});"> <u>{postSummary}</u></a></p>',
	templateMoreOurPracticesAllContent : '<div class="post-item"><a href="#" onclick="OurPractice.All.showFullContent({postId}, this); return false;"><u>{postTitle}</u></a><div id="edge_left_container"><p class="post-content">{postSummary}</p</div</div>',
	templateMoreCategoriesContent : '<div class="edge-container"><div class="edge-text"><span class="category-name">{name}</span>{posts}</div></div>',

	search : function(search) {
		if (search && $.trim(search) !== '') {
			window.location.href = contextPath + "/our_practice/our_practice/"
					+ search + "/search.in";
		} else {
			window.location.href = contextPath
					+ "/our_practice/our_practice/1/list.in";
		}
	},

	getOurPractice : function(postId) {
		document.body.style.cursor = "progress";

		DwrOurPracticeService.getOurPracticeById(postId, {
			callback : function(post) {
				var postCnt = OurPractice.templateContent.replace('{summary}',
						post.summary).replace('{content}', post.content);
				$('#edge_left > .post-item').empty().html(postCnt);
				document.body.style.cursor = "auto";
			},
			errorHandler : function(message) {
				console.log(message);
			}
		});
	},

	loadMoreOurPractices : function(categoryId, element) {
		element = $(element);
		var pageIndex = parseInt(element.attr('page')) + 1;
		var loadMoreElement = element.parent();
		document.body.style.cursor = "progress";

		DwrOurPracticeService
				.loadMoreOurPractices(
						categoryId,
						pageIndex,
						{
							callback : function(posts) {
								if (posts && posts.length > 0) {
									for (var i = 0; i < posts.length; i++) {
										var post = posts[i];
										var postCnt = OurPractice.templateMoreOurPracticesContent
												.replace('{postId}',
														post.postId).replace(
														'{postSummary}',
														post.summary);
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

	loadMoreOurPracticesMostRecent : function(element) {
		element = $(element);
		var offset = parseInt(element.attr('offset'));
		var loadMoreElement = element.parent().parent();
		var postsParentElement = loadMoreElement.prev().find(
				'.edge-container > .edge-text');
		document.body.style.cursor = "progress";

		DwrOurPracticeService
				.loadMoreOurPracticesRecent(
						offset,
						{
							callback : function(posts) {
								if (posts && posts.length > 0) {
									var newOffset = 0;

									for (var i = 0; i < posts.length; i++) {
										var post = posts[i];
										var postCnt = OurPractice.templateMoreOurPracticesContent
												.replace('{postId}',
														post.postId).replace(
														'{postSummary}',
														post.summary);
										postsParentElement.append(postCnt);
										newOffset = post.createdDate;
									}

									element.attr('offset', Date
											.parse(newOffset));
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

	loadMoreOurPracticesAll : function(element) {
		element = $(element);
		var offset = parseInt(element.attr('offset'));
		var loadMoreElement = element.parent().parent();
		var postsParentElement = loadMoreElement.parent();
		document.body.style.cursor = "progress";

		DwrOurPracticeService
				.loadMoreOurPracticesRecent(
						offset,
						{
							callback : function(posts) {
								if (posts && posts.length > 0) {
									var newOffset = 0;

									for (var i = 0; i < posts.length; i++) {
										var post = posts[i];
										var postCnt = OurPractice.templateMoreOurPracticesAllContent
												.replace('{postId}',
														post.postId).replace(
														'{postTitle}',
														post.summary).replace(
														'{postSummary}',
														post.content);
										loadMoreElement.before(postCnt);
										newOffset = post.createdDate;
									}

									element.attr('offset', Date
											.parse(newOffset));
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

	loadMoreOurPracticeCategories : function(element) {
		element = $(element);
		var loadMoreElement = element.parent().parent();
		var prevElement = loadMoreElement.prev();
		var offset = prevElement.find('.category-name:last').text();
		document.body.style.cursor = "progress";

		DwrOurPracticeService
				.getOurPracticeCategories(
						offset,
						5,
						{
							callback : function(categories) {
								if (categories && categories.length > 0) {
									for (var c = 0; c < categories.length; c++) {
										var category = categories[c];
										var categoryCnt = OurPractice.templateMoreCategoriesContent
												.replace('{name}',
														category.categoryName);
										var postCnt = '';

										for (var i = 0; i < category.postBeans.length; i++) {
											var post = category.postBeans[i];
											postCnt = postCnt
													+ OurPractice.templateMoreOurPracticesContent
															.replace(
																	'{postId}',
																	post.postId)
															.replace(
																	'{postSummary}',
																	post.summary);
										}

										categoryCnt = categoryCnt.replace(
												'{posts}', postCnt);
										prevElement.append(categoryCnt);
									}
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
			OurPractice.search($(this).val());
		}
	});

	$("#search a").click(function() {
		var term = $("#search > input").val();
		OurPractice.search(term);
	});
});