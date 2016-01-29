var Ticket = {
    urlReq: contextPath + '/ajax/ticket/load-more',
    stopLoad: 0,
    init: function () {
        this.initEvent();
    },
    initEvent: function () {
        var _self = this;
        $(document).on('click', '.load-questions', function (event) {
            var offset = $('.question').length;
            $.ajax({
                url: _self.urlReq,
                data: {'offset': offset},
                type: 'POST',
                success: function (data) {
                    for (var i = 0; i < data.length; i++) {
                        var item = _self.createArticle(data[i]);
                        $('.tickets-content').append(item);
                    }

                }
            });
        });
    },
    createArticle: function (article) {
        var _setf = this;
        var spanEle = "";
        switch (article.status) {
            case 0:
                spanEle = '<span class=\"question-open\" > <i class=\"icon-ok\"> </i>open</span>';
                break;
            case 1:
                spanEle = '<span class="question-answered"><i class="icon-ok"></i>in progress</span>';
                break;
            case 2:
                spanEle = '<span class="question-answered question-answered-done"><i class="icon-ok"></i>solved</span>';
                break;
        }

        var elem = '<article class=\"question\">' +
                '<h2><a href="1/single_ticket.in">' + article.ticketId + '-' + article.title + '</a></h2>' +
                '<div class="question-author"><a class="question-author-img tooltip-n" original-title="ahmed" href="#"><span></span><img src="http://2code.info/demo/html/ask-me/images/demo/avatar.png" alt=""></a></div>' +
                '<div class="question-inner"><div class="clearfix"></div>' +
                '<p class="question-desc">' + article.content + '</p>' +
                ' <div class="question-details">' + spanEle + '</div>' +
                '<span class= "question-date"><i class = "icon-time"> </i>' + article.createdDate + '</span >' +
                '<div class="clearfix"></div></div></article>'

        return elem;
    }
}
$(document).ready(function () {
    Ticket.init();
});

