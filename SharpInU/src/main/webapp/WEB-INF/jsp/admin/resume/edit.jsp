<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<%@ include file="/WEB-INF/jsp/include/server_variables.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js_variables.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Resume Form</title>
        <script type="text/javascript" src="${assetsPath}/javascripts/admin/resumeadmin.js"></script>

    </head>
    <body>

        <div class="inner-content resume-content">
            <div class="form-header">
                <h1>Resume</h1>
            </div>
            
            <form class="form-horizontal" role="form" action="${contextPath}/ajax/admin/resume/upload"  method="POST" id="uploadForm" enctype="multipart/form-data">
                <div class="form-group">										
                    <label class="control-label col-sm-3" for="res_name">Name</label>
                    <div class="col-sm-6">
                        <input class='form-control' id='res_name'
                               placeholder='Your name'  value="${resume.name}" readonly/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-3" for="res_title">Current Title:</label>
                    <div class="col-sm-6">
                        <input class='form-control' id='res_current_title'
                               placeholder=''  value="${resume.currentTitle}" readonly/>

                    </div>
                </div>
                <div class="form-group">

                    <label class="control-label col-sm-3" for="res_contract">Contact
                        Information:</label>
                    <div class="col-sm-6">
                        <input class='form-control' id='res_contract_infomation'
                               value="${resume.contact}"  readonly/>

                    </div>
                </div>
                <div class="form-group">

                    <label class="control-label col-sm-3" for="res_summary">Summary
                        Skill:</label>
                    <div class="col-sm-6">
                        <textarea class='form-control' id='res_summary'
                                  placeholder='' rows='12'  readonly>${resume.summarySkill}</textarea>						
                    </div>
                </div>

                <div class="form-group">

                    <label class="control-label col-sm-3" for="res_objective">Objectives:</label>
                    <div class="col-sm-9">
                        <textarea class='form-control' id='res_objective'
                                  placeholder='' rows='12' readonly>${resume.objectives}</textarea>

                    </div>
                </div>

                <div class="form-group">					

                    <label class="control-label col-sm-3" for="res_education">Education:</label>
                    <div class="col-sm-9">
                        <input class='form-control' id='res_education' placeholder=''
                               path="education" value="${resume.education}" readonly/>						
                    </div>
                </div>
                <div class="form-group">					

                    <label class="control-label col-sm-3" for="res_experience">Experience:</label>
                    <div class="col-sm-9">
                        <input class='form-control' id='res_experience'
                               placeholder=''  value="${resume.experience}" readonly/>

                    </div>
                </div>
                <div class="form-group">										
                    <label class="control-label col-sm-3" for="res_certificate">Certificate:</label>
                    <div class="col-sm-9">
                        <input class='form-control' id='res_certificate'
                               placeholder='' value="${resume.certificate}" readonly/>

                    </div>
                </div>

                <div class="form-group">

                    <label class="control-label col-sm-3" for="res_additionalInfo">Additional Info:</label>
                    <div class="col-sm-9">
                        <textarea class='form-control' id='res_additionalInfo'
                                  placeholder='' rows='12'  readonly >${resume.additionalInfo}
                        </textarea>

                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-3" for="res_cv">CV
                        Attachment:</label>
                    <div class="col-sm-9 upload-cv">
                        <input id="res_id" type="hidden" value="${idEncrypt}">
                            <c:choose> 
                                <c:when test="${resume.cv!=''}">
                                    <!--<p class="form-control-static">-->
                                    <a href="${contextPath}/admin/resume/resume_cv.in?id=${idEncrypt}&isEncrypt=true" target="_blank">Your CV</a>


                                    <a class='btn btn-primary' href='javascript:;'>
                                        Choose File ...
                                        <input type="file" name="cvAttachment" style='width:120px;position:absolute;z-index:2;top:8px;left:71px;filter: alpha(opacity=0);-ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";opacity:0;background-color:transparent;color:transparent;' name="file_source" size="40">
                                    </a>

                                </c:when>
                                <c:otherwise>
                                    <a class='btn btn-primary' href='javascript:;'>
                                        Choose File ...
                                        <input type="file" name="cvAttachment" style='position:absolute;z-index:2;top:0;left:0;filter: alpha(opacity=0);-ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";opacity:0;background-color:transparent;color:transparent;' name="file_source" size="40">
                                    </a>

                                </c:otherwise>
                            </c:choose>
                       
                    </div>
                </div>


                <div class="res-photo">
                    <div class="photo-uploaded"
                         style="background-image: url('${contextPath}/admin/resume/resume_image.in?id=${idEncrypt}&isEncrypt=true')">
                    </div>

                </div>

            </form>
        </div>

    </body>
</html>
