<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    <title>事件管理--运维管理系统</title>

    <link rel="icon" type="image/ico" href="favicon.ico"/>
    
    <link href="${contextPath }/resources/css/stylesheets.css" rel="stylesheet" type="text/css" />
    <!--[if lt IE 8]>
        <link href="${contextPath }/resources/css/ie7.css" rel="stylesheet" type="text/css" />
    <![endif]-->    
    <link rel='stylesheet' type='text/css' href='${contextPath }/resources/css/fullcalendar.print.css' media='print' />
    
      <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-1.10.2.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-ui-1.10.1.custom.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery-migrate-1.2.1.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/jquery/jquery.mousewheel.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cookie/jquery.cookies.2.2.0.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/bootstrap.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.stack.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.pie.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/charts/jquery.flot.resize.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/sparklines/jquery.sparkline.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fullcalendar/fullcalendar.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/select2/select2.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/uniform/uniform.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/maskedinput/jquery.maskedinput-1.3.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/languages/jquery.validationEngine-en.js' charset='utf-8'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/animatedprogressbar/animated_progressbar.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/qtip/jquery.qtip-1.0.0-rc3.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/cleditor/jquery.cleditor.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/dataTables/jquery.dataTables.min.js'></script>    
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/fancybox/jquery.fancybox.pack.js'></script>
        
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.gears.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.silverlight.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.flash.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.browserplus.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.html4.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/plupload.html5.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/plupload/jquery.plupload.queue/jquery.plupload.queue.js'></script>    
    
    <script type="text/javascript" src="${contextPath }/resources/js/plugins/elfinder/elfinder.min.js"></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/highlight/jquery.highlight-4.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/pnotify/jquery.pnotify.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/ibutton/jquery.ibutton.min.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/scrollup/jquery.scrollUp.min.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins/tagsinput/jquery.tagsinput.min.js'></script>
        <script type='text/javascript' src='${contextPath }/resources/js/plugins/multiselect/jquery.multi-select.js'></script>
    
    <script type='text/javascript' src='${contextPath }/resources/js/cookies.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/actions.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/charts.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/plugins.js'></script>
    <script type='text/javascript' src='${contextPath }/resources/js/settings.js'></script>    
    <script type='text/javascript' src='${contextPath }/resources/js/faq.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${contextPath }/resources/js/html5shiv.js"></script>
      <script src="${contextPath }/resources/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
            $(document).ready(function () {
            	$(".header").load("../header");
                $(".menu").load("../menu", function () { $(".navigation > li:eq(4)").addClass("active"); });
                $(".breadLine .buttons").load("../contentbuttons");
            });
    </script>
</head>
<body>
    <div class="wrapper"> 

        <!--header-->
        <div class="header"></div>
        <!--header end-->
        
        <!--menu-->
        <div class="menu"></div>
        <!--menu end-->

        <div class="content">
            <!--breadline-->
            <div class="breadLine">

                <ul class="breadcrumb">
                    <li><a href="#">运维管理系统</a> <span class="divider">></span></li>
                    <li><a href="#">资产管理</a> <span class="divider">></span></li>         
                    <li class="active">新资产</li>
                </ul>

                <ul class="buttons"></ul>

            </div>
            <!--breadline end-->

            <!--workplace-->
            <div class="workplace">

                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-10">
                        <div class="head clearfix">
                            <div class="isw-documents"></div>
                            <h1>资产信息录入</h1>
                        </div>
                        <div class="block-fluid">                        
                         <div class="row-form clearfix">
                                <div class="col-md-2">事件编号:</div>
                                <div class="col-md-10"><input type="text" value="M0950585"/></div>
                                
                            </div> 
                            <div class="row-form clearfix">
                                <div class="col-md-2">事件摘要:</div>
                                <div class="col-md-4"><input type="text" value="用户key锁死"/></div>
                                <div class="col-md-2">事件来源:</div>
                                <div class="col-md-4">
                                    <label class="checkbox checkbox-inline">
                                        <input type="radio" name="radio1" checked="checked"/> 电话报修
                                    </label>
                                    <label class="checkbox checkbox-inline">
                                        <input type="radio" name="radio1" /> 来访报修
                                    </label>
                                    <label class="checkbox checkbox-inline">
                                        <input type="radio" name="radio1" /> 工程师报修
                                    </label>
                                </div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-2">详细描述:</div>
                                <div class="col-md-10">
                                    <textarea>用户在登录时，输错5次密码，导致key被锁死，需重新解锁。</textarea>
                                    <input type="text" class="tags" value="key,锁死"/>
                                </div>
                            </div>
                            <div class="row-form clearfix">
                                <div class="col-md-2">联系人:</div>
                                <div class="col-md-4">
                                    <select name="select" id="s2_1" style="width: 100%;">
                                        <option value="0">选择联系人...</option>
                                        <optgroup label="办公厅">
                                            <option value="1">李伟</option>
                                            <option value="2">王小戈</option>
                                            <option value="3">杨海鹏</option>
                                        </optgroup>
                                        <optgroup label="发展规划部">
                                            <option value="4">王鹏宇</option>
                                            <option value="5">刘坤</option>
                                            <option value="6">杨涛</option>
                                        </optgroup>
                                        <optgroup label="党建群工局">
                                            <option value="8">段梦湉</option>
                                            <option value="9">刘路希</option>                                 
                                        </optgroup>                                 
                                    </select></div>
                                <div class="col-md-2">联系电话:</div>
                                <div class="col-md-4"><input type="text" value="68787220"/></div>
                            </div> 
                            <div class="row-form clearfix">
                                <div class="col-md-2">影响:</div>
                                <div class="col-md-4">
                                    <select name="select" id="Select11" style="width: 100%;">
                                    <option value="1">广泛/普遍</option>
                                    <option value="2">极大/大型</option>
                                    <option value="3">适度/受限</option>
                                    <option value="4" selected>次要/本地化</option>
                                  </select>
                                </div>
                                <div class="col-md-2">紧急性:</div>
                                <div class="col-md-4">
                                    <select name="select" id="Select12" style="width: 100%;">
                                    <option value="1">严重</option>
                                    <option value="2">高</option>
                                    <option value="3">中</option>
                                    <option value="4" selected>低</option>
                                  </select>
                                </div>
                            </div> 
                             <div class="row-form clearfix">
                                <div class="col-md-2">优先级:</div>
                                <div class="col-md-4">
                                    <select name="select" id="Select13" style="width: 100%;">
                                    <option value="1">严重</option>
                                    <option value="2">高</option>
                                    <option value="3">中</option>
                                    <option value="4" selected>低</option>
                                  </select>
                                </div>
                                <div class="col-md-2">事件分类:</div>
                                <div class="col-md-4">
                                    <select name="select" id="Select14" style="width: 100%;">
                                    <optgroup label="主机">
                                    <option value="1">小型机</option>
                                    <option value="2">PC服务器</option>
                                    <option value="3">VM服务器</option>
                                   </optgroup>
                                   <optgroup label="备份">
                                    <option value="4">磁带库机械臂</option>
                                    <option value="5">磁带库驱动器</option>
                                    <option value="6">虚拟带库</option>
                                    <option value="7">备份软件</option>
                                   </optgroup>
                                   <optgroup label="终端">
                                    <option value="8" selected>PC机</option>
                                    <option value="9">打印机</option>
                                   </optgroup>
                                  </select>
                                </div>
                            </div> 

                            <div class="row-form clearfix">
                                <div class="col-md-2">目标日期:</div>
                                <div class="col-md-4"><input type="text" value=""/></div>
                                <div class="col-md-2">附件:</div>
                                <div class="col-md-4"><input type="file" name="file"/></div>
                            </div>                                                               
  

                            <div class="footer tar">
                                <button class="btn btn-primary center-block"> 保 存 </button>
                            </div>                            
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

            </div>
            <!--workplace end-->
        </div>   
    </div>
</body>

</html>
