<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp" %>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp" %>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Ask for Advice</title>
        <script type="text/javascript"  src="${assetsPath}/javascripts/career.js"></script>
    </head>
    <body>
        <div id="career" class="container">
            <aside id="career_left">
                <div id="career_nav">
                    <ul>
                        <li class="career active" tabindex="0" name='career'><a href="#">
                                <div class="intro " id="left1_2">
                                    <h2>CAREER</h2>
                                    <h3>Ask for your development</h3>
                                </div>
                            </a></li>
                        <li class="resume" name='resume'><a href="#" ><div class="intro" id="left2_2">
                                    <h2>RESUME</span></h2>
                                    <h3>Ask for a better resume</span></h3>
                                </div></a></li>		
                        <li class="company" name='company'><a href="#"><div class="intro" id="left3_2">
                                    <h2>COMPANY</span></h2>
                                    <h3>Ask for a solution & approach</span></h3>
                                </div></a></li>
                    </ul>
                </div>

            </aside>

            <div id="career_right" class='right-content career'>
                <div id="career_intro">
                    <div id="career_intro_top"><p><b>Minding your Career Gaps</b></p></div>
                    <div id="career_intro_down"><p>Everyone has a specific talent and some strength. Someone is able to unlock them, but some one are not. We with vast of experiences will help you in growing 
                            your career. Don't hesitate to come with us - sharing your wish, telling yourselves and moving forward with us.</div>
                </div>
                <div id="career_explore">
                    <div id="career_explore_image">
                        <img src="${assetsPath}/images/twogirl.jpg">
                        <p>Do you believe in your inner strength<br/> and willing to help us to explore it?</p>
                    </div>
                </div>
                <div id="career_right_down"><a href="${contextPath}/career_form.in"><img src="${assetsPath}/images/ask.png"/></a></div>

            </div>

            <div id="career_right" class='right-content hide resume'>
                <div id="career_intro">
                    <div id="career_intro_top"><p><b>A New Look for your CV</b></p></div>
                    <div id="career_intro_down"><p>Have you spotted a job that you want? Do you believe in your CV is attracting employers? 
                            If you still concern on your CV, why don't you send it to us to get a new look CV.</div>
                </div>
                <div id="career_explore">
                    <div id="career_explore_image">
                        <img src="${assetsPath}/images/jobopp.jpg">
                        <p>All that stands between you and the job is your CV and covering letter. How good are they?</p>
                    </div>
                </div>
                <div id="career_right_down"><a href="${contextPath}/resume_form.in"><img src="${assetsPath}/images/ask.png"/></a></div>
            </div>

            <div id="career_right" class='right-content hide company'>
                <div id="career_intro">
                    <div id="career_intro_top"><p><b>When strategy must change</b></p></div>
                    <div id="career_intro_down"><p>Is your organization struggling with product quality and need to level up your testing? Or you want to establish a new strategy where your testing would be faster, less expensive, and more efficient? Let email us your story, we can tell you what we can help.</div>
                </div>
                <div id="career_explore">
                    <div id="career_explore_image">
                        <img src="${assetsPath}/images/man.png">
                        <p>A smart testing approach will make the testing more efficient</p>
                    </div>
                </div>
                <div id="career_right_down"><a href="${contextPath}/company_form.in"><img src="${assetsPath}/images/ask.png"/></a></div>
            </div>

        </div>
    </body>
</html>
