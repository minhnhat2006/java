StayOTheEdge = {
	templateContent : '<h3>{summary}</h3><div id="edge_left_container"><p>{content}</p></div>',
	templateMorePostsContent : '<p class="post-name"> <a href="{href}" onclick="javascript:StayOTheEdge.getPost({postId}); return false;"> <u>{postSummary}</u></a></p>',
	templateMorePostsContentForAll : '<p class="post-name"> <a href="javascript:StayOTheEdge.All.removeOtherPostItems();StayOTheEdge.getPost({postId});"> <u>{postSummary}</u></a></p>',
	templateMorePostsAllContent : '<div class="post-item"><a href="#" onclick="javascript:StayOTheEdge.All.removeOtherPostItems();StayOTheEdge.getPost({postId});"><u>{postTitle}</u></a><div id="edge_left_container"><p class="post-content">{postSummary}</p</div</div>',
	templateMoreCategoriesContent : '<div class="edge-container"><div class="edge-text"><span class="category-name">{name}</span>{posts}</div></div>',

	search : function(search) {
		if (search && $.trim(search) !== '') {
			window.location.href = contextPath + "/stay_on_the_edge/posts/"
					+ search + "/search.in";
		} else {
			window.location.href = contextPath
					+ "/stay_on_the_edge/categories/posts/1/list.in";
		}
	},

	getPost : function(postId) {
		document.body.style.cursor = "progress";

		DwrStayOnEdgeService.getPostById(postId, {
			callback : function(post) {
				if (post.status == 401) {
					window.location = '/advisor/stay_on_the_edge.in';
					return;
				}

				var postCnt = StayOTheEdge.templateContent.replace('{summary}',
						post.summary).replace('{content}', post.content);
				$('#edge_left > .post-item').empty().html(postCnt);
				document.body.style.cursor = "auto";

				var newWindowLocation = '/stay_on_the_edge/' + post.slug
						+ '/view.in';
				window.history.pushState({
					"pageTitle" : post.summary
				}, post.summary, newWindowLocation);
			},
			errorHandler : function(message) {
				console.log(message);
			}
		});
	},

	getPostBySlug : function(slug) {
		document.body.style.cursor = "progress";

		DwrStayOnEdgeService.getPostBySlug(slug, {
			callback : function(post) {
				var postCnt = StayOTheEdge.templateContent.replace('{summary}',
						post.summary).replace('{content}', post.content);
				$('#edge_left > .post-item').empty().html(postCnt);
				document.body.style.cursor = "auto";
			},
			errorHandler : function(message) {
				console.log(message);
			}
		});
	},

	loadMorePosts : function(categoryId, element) {
		element = $(element);
		var pageIndex = parseInt(element.attr('page')) + 1;
		var loadMoreElement = element.parent();
		document.body.style.cursor = "progress";

		DwrStayOnEdgeService
				.loadMorePosts(
						categoryId,
						pageIndex,
						{
							callback : function(posts) {
								if (posts && posts.length > 0) {
									for (var i = 0; i < posts.length; i++) {
										var post = posts[i];
										var postCnt = (typeof StayOTheEdge.All !== "undefined") ? StayOTheEdge.templateMorePostsContentForAll
												.replace('{postId}',
														post.postId).replace(
														'{postSummary}',
														post.summary)
												: StayOTheEdge.templateMorePostsContent
														.replace(
																'{href}',
																'/stay_on_the_edge/'
																		+ post.slug
																		+ '/view.in')
														.replace('{postId}',
																post.postId)
														.replace(
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

	loadMorePostsMostRecent : function(element) {
		element = $(element);
		var offset = parseInt(element.attr('offset'));
		var loadMoreElement = element.parent().parent();
		var postsParentElement = loadMoreElement.prev().find(
				'.edge-container > .edge-text');
		document.body.style.cursor = "progress";

		DwrStayOnEdgeService.loadMorePostsRecent(offset, {
			callback : function(posts) {
				if (posts && posts.length > 0) {
					var newOffset = 0;

					for (var i = 0; i < posts.length; i++) {
						var post = posts[i];
						var postCnt = StayOTheEdge.templateMorePostsContent
								.replace(
										'{href}',
										'/stay_on_the_edge/' + post.slug
												+ '/view.in').replace(
										'{postId}', post.postId).replace(
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
	},

	loadMorePostsAll : function(element) {
		element = $(element);
		var offset = parseInt(element.attr('offset'));
		var loadMoreElement = element.parent().parent();
		var postsParentElement = loadMoreElement.parent();
		document.body.style.cursor = "progress";

		DwrStayOnEdgeService.loadMorePostsRecent(offset, {
			callback : function(posts) {
				if (posts && posts.length > 0) {
					var newOffset = 0;

					for (var i = 0; i < posts.length; i++) {
						var post = posts[i];
						var postCnt = StayOTheEdge.templateMorePostsAllContent
								.replace('{postId}', post.postId).replace(
										'{postTitle}', post.summary).replace(
										'{postSummary}', post.content);
						loadMoreElement.before(postCnt);
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
	},

	loadMoreCategories : function(element) {
		element = $(element);
		var loadMoreElement = element.parent().parent();
		var prevElement = loadMoreElement.prev();
		var offset = parseInt(element.attr('offset'));
		document.body.style.cursor = "progress";

		DwrStayOnEdgeService
				.getCategories(
						offset,
						5,
						{
							callback : function(categories) {
								if (categories && categories.length > 0) {
									var newOffset = 0;

									for (var c = 0; c < categories.length; c++) {
										var category = categories[c];
										var categoryCnt = StayOTheEdge.templateMoreCategoriesContent
												.replace('{name}',
														category.categoryName);
										var postCnt = '';

										for (var i = 0; i < category.postBeans.length; i++) {
											var post = category.postBeans[i];
											postCnt = postCnt
													+ ((typeof StayOTheEdge.All !== "undefined") ? StayOTheEdge.templateMorePostsContentForAll
															.replace(
																	'{postId}',
																	post.postId)
															.replace(
																	'{postSummary}',
																	post.summary)
															: StayOTheEdge.templateMorePostsContent
																	.replace(
																			'{href}',
																			'/stay_on_the_edge/'
																					+ post.slug
																					+ '/view.in')
																	.replace(
																			'{postId}',
																			post.postId)
																	.replace(
																			'{postSummary}',
																			post.summary));
										}

										categoryCnt = categoryCnt.replace(
												'{posts}', postCnt);
										prevElement.append(categoryCnt);
										
										newOffset = category.createdDate;
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
	}
}

$(document).ready(function() {
	$("#search > input").keyup(function(e) {
		if (e.keyCode == 13) {
			StayOTheEdge.search($(this).val());
		}
	});

	$("#search a").click(function() {
		var term = $("#search > input").val();
		StayOTheEdge.search(term);
	});

	var slug = window.location.hash.substring(1);

	if (slug) {
		if (typeof StayOTheEdge.All !== "undefined") {
			StayOTheEdge.All.removeOtherPostItems();
			StayOTheEdge.getPostBySlug(slug);
		} else {
			StayOTheEdge.getPostBySlug(slug);
		}
	}
});