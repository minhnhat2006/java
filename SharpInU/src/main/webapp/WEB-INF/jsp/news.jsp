<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp" %>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp" %>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Info</title>
        <script type='text/javascript'
        src='${contextPath}/assets/javascripts/news.js'></script>
    </head>
    <body>
        <section id="content_news">
            <div class="news-wrapper container-fluid">
                <div class="row page-title">
                    <h1>NEWS</h1>
                </div>
                <div class="news-container row">
                    <div class="col-xs-7 news-detail">
                        <div class="news-title warp-text"><h3>${newsBean.title}</h3></div>
                        <div class="news-content">
                            ${newsBean.content}
                        </div>
                    </div>
                    <div class="col-xs-5 news-list">
                        <a class="fillter-news"><h3>Most Recent</h3></a>
                        <c:forEach items="${newss}" var="news">
                            <div class="news-item col-xs-12">
                                <a class="news-title warp-text" href="#" news-id="${news.newsId}"
                                   onclick=""><u><h4>${news.title}</h4></u></a>
                                <p class="news-summary text-justify">${news.summary}</p>

                            </div>
                        </c:forEach>
                        <ul class="pagination pagination-md">
                            <c:url var="prevUrl"
                                   value="/news/${currentIndex - 1}/list.in" />
                            <c:url var="nextUrl"
                                   value="/news/${currentIndex + 1}/list.in" />
                            <c:choose>
                                <c:when test="${currentIndex == 1}">
                                    <li class="disabled"><span>&laquo;</span></li>
                                    </c:when>
                                    <c:otherwise>
                                    <li><a href="${prevUrl}"><span>&laquo;</span></a></li>
                                    </c:otherwise>
                                </c:choose>
                                <c:forEach var="i" begin="${beginIndex}"
                                           end="${endIndex}">
                                    <c:url var="pageUrl"
                                           value="/news//${i}/list.in" />
                                    <c:choose>
                                        <c:when test="${i == currentIndex}">
                                        <li class="active"><a href="${pageUrl}"><c:out
                                                    value="${i}" /></a></li>
                                            </c:when>
                                            <c:otherwise>
                                        <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <c:choose>
                                    <c:when
                                        test="${currentIndex == endIndex}">
                                    <li class="disabled" ><span>&raquo;</span></li>
                                    </c:when>
                                    <c:otherwise>
                                    <li><a href="${nextUrl}"><span>&raquo;</span></a></li>
                                    </c:otherwise>
                                </c:choose>
                        </ul>
                    </div>
                </div>

            </div>
        </section>
    </body>
</html>
