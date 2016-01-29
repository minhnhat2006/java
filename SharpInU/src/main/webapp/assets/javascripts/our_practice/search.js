OurPractice.Search = {
	templateMorePracticesContent : '<p class="post-name"> <a href="javascript:OurPractice.getPractice({practiceId});"> <u>{practiceSummary}</u></a></p>',

	searchPractices : function(term, element) {
		element = $(element);
		var offset = parseInt(element.attr('offset'));
		var loadMoreElement = element.parent().parent();
		var practicesParentElement = loadMoreElement.prev().find(
				'.edge-container > .edge-text');
		document.body.style.cursor = "progress";

		DwrStayOnEdgeService.searchPractices(term, offset, {
			callback : function(practices) {
				if (practices && practices.length > 0) {
					var newOffset = 0;

					for (var i = 0; i < practices.length; i++) {
						var practice = practices[i];
						var practiceCnt = OurPractice.templateMorePracticesContent
								.replace('{practiceId}', practice.ourPracticeId).replace(
										'{practiceSummary}', practice.summary);
						practicesParentElement.append(practiceCnt);
						newOffset = practice.createdDate;
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