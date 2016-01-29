<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp" %>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp" %>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp" %>
<!DOCTYPE html>
<html xmlns:h="http://java.sun.com/jsf/html">
    <head>
        <script type='text/javascript'
        src='${contextPath}/assets/javascripts/user_ticket.js'></script>
        <title>User Tickets</title>
    </head>
    <body>
        <section id="content_ticket">
            <div class="ticket-wrapper container-fluid">
                <div class="row page-title">
                    <h1>TICKETS</h1>
                </div>
                <div class="row tickets-content">
                    <c:forEach items="${tickets}" var="ticket">
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
                    </c:forEach>                                        
                </div>
                <a href="#" class="load-questions"><i class="icon-refresh"></i>Load More Tickets</a>
            </div>
        </section>
    </body>
</html>
