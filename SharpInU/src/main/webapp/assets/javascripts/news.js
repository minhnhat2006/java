var News={
    urlReq: contextPath+'/ajax/news/get-news',
    pageDetail:null,
    init: function(){
        this.pageDetail=$('.news-detail');
        this.initEvents();
    },
    initEvents:function(){
        var _self = this;
        $(document).on('click', '.news-title', function (event) {
            var id=$(this).attr('news-id');
            $.ajax({
                    url: _self.urlReq,
                    data: {'id':id},                    
                    type: 'POST',
                    success: function (data) {
                        if(typeof data!==undefined){
                           $(_self.pageDetail).find("div.news-title >h3").html(data.title);                         
                           $(_self.pageDetail).find("div.news-content").html(data.content); 
                        }
                    }
                });
        });
    }
}
$(document).ready(function() {    
    News.init();
});

