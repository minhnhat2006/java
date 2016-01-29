var Comment = {
	urlReq : contextPath + '/ajax/ticket/add-comment',
	commentCount : 0,
	init : function() {
		var total = $("#comment_count").val();
		this.commentCount = total === '' ? 0 : parseInt(total);
		this.initEvent();
	},
	initEvent : function() {
		var _self = this;
		$(document).on('click', '.btn-comment', function(event) {
			var formData = $('#add_comment_form').serialize();
			$.ajax({
				url : _self.urlReq,
				data : formData,
				type : 'POST',
				success : function(data) {
					if (data) {
						_self.commentCount += 1;
						_self.renderComment(data);

					}

					$('#add_comment_form #comment').val('');
				}
			});
		});
	},
	renderComment : function(comment) {
		var _self = this;
		var liElem = '<li class="comment">'
				+ '<div class="comment-body comment-body-answered clearfix">'
				+ '<div class="avatar"><img alt="" src="http://2code.info/demo/html/ask-me/images/demo/admin.jpeg"></div>'
				+ '<div class="comment-text">'
				+ '<div class="author clearfix">'
				+ '<div class="comment-author"><a href="#">' + comment.userName
				+ '</a></div>' + '<div class="comment-meta">'
				+ '<div class="date"><i class="icon-time"></i>'
				+ comment.createdDate + '</div>' + '</div>' + '</div>'
				+ '<div class="text"><p>' + comment.content + '</p>'
				+ '</div></div></div></li>';
		$('ol.commentlist').append(liElem);
		// update comment count
		$("div.ticket-title > h2").find("span").html(_self.commentCount);
	}
};
$(document).ready(function() {
	Comment.init();
});
