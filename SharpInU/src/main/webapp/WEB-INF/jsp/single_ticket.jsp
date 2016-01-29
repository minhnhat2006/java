<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp" %>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp" %>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Single Ticket</title>
        <script type='text/javascript'
        src='${contextPath}/assets/javascripts/user_comment.js'></script>
    </head>
    <body>
        <section id="content_ticket">
            <div class="ticket-wrapper container-fluid">
                <div class="row page-title">
                    <h1>DETAIL TICKET</h1>
                </div>
                <div class="row tickets-content">
                    <article class="question">
                        <h2>
                            <a href="${contextPath}/ticket/${ticket.ticketId}/single_ticket.in"> ${ticket.ticketId} - ${ticket.title}</a>
                        </h2>
                        <div class="question-author">
                            <a href="#" original-title="ahmed" class="question-author-img tooltip-n"><span></span><img alt="" src="http://2code.info/demo/html/ask-me/images/demo/avatar.png"></a>
                        </div>
                        <div class="question-inner">
                            <div class="clearfix"></div>
                            <div class="question-desc">${ticket.content}</div>
                            <div class="question-details">
                                <c:choose>
                                    <c:when test="${ticket.status==0}">
                                        <span class="question-open"><i class="icon-ok"></i>open</span>
                                    </c:when>
                                    <c:when test="${ticket.status==1}">
                                        <span class="question-answered"><i class="icon-ok"></i>in progress</span>
                                    </c:when>
                                    <c:when test="${ticket.status==2}">
                                        <span class="question-answered question-answered-done"><i class="icon-ok"></i>solved</span>
                                    </c:when>
                                </c:choose>                                 
                            </div>                            
                            <span class="question-date"><i class="icon-time"></i>${ticket.createdDate}</span>
                            <div class="clearfix"></div>
                        </div>
                    </article>
                    <div class="page-content" id="commentlist">
                        <div class="boxedtitle ticket-title"><h2>Comments ( <span class="color">${total}</span> )</h2></div>
                        <ol class="commentlist clearfix">
                            <c:forEach items="${comments}" var="comment">
                                <li class="comment">
                                    <div class="comment-body comment-body-answered clearfix"> 
                                        <div class="avatar"><img src="http://2code.info/demo/html/ask-me/images/demo/admin.jpeg" alt=""></div>
                                        <div class="comment-text">
                                            <div class="author clearfix">
                                                <div class="comment-author"><a href="#">${comment.userName}</a></div>
                                                <div class="comment-meta">
                                                    <div class="date"><i class="icon-time"></i>${comment.createdDate}</div> 
                                                </div>
                                            </div>
                                            <div class="text"><p>${comment.content}</p>
                                            </div>

                                        </div>
                                    </div>

                                </li>
                            </c:forEach> 
                            
                        </ol><!-- End commentlist -->
                        <form id="add_comment_form">
                            <div id="respond-textarea">
                                <p>
                                    <label for="comment" class="required">Comment<span>*</span></label>
                                    <textarea rows="8" cols="58" aria-required="true" name="content" id="comment"></textarea>
                                </p>

                            </div>
                            
                            <p>
                                <button type="button" class="btn-comment btn btn-default btn-lg">Submit</button>
                            </p>
                            <input type="hidden" value="${ticket.ticketId}" name="ticketId"/>
                            <input type="hidden" value="${owner}" name="owner"/>
                            <input id="comment_count" type="hidden" value="${total}" name="commnetCount"/>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
