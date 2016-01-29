var CarrerPage = {
    currentItem: 'career',
    init: function() {
        var _self = this;
        try {
            _self.currentItem = _self.getParameterByName('page');
        } catch (e) {
            _self.currentItem = 'career';
        }
        _self.initEvent();
    },
    initEvent: function() {
        var _self = this;
        $('li.active').removeClass('active');        
        $('li.' + _self.currentItem).addClass('active');        
        $('article.right-content').addClass('hide');
        $('article.' + _self.currentItem).removeClass('hide');
        
        $('div#career_nav').find('li').each(function() {
            $(this).hover(function() {
                $('li.active').removeClass('active');
                $(this).addClass('active');
                $('article#career_right').addClass('hide');
                $('article.' + $(this).attr('name')).removeClass('hide');
            });
        });

    },
    getParameterByName: function(name) {
        name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
                results = regex.exec(location.search);
        return results === null ? "career" : decodeURIComponent(results[1].replace(/\+/g, " "));
    }

};
$(document).ready(function() {
    CarrerPage.init();
});
