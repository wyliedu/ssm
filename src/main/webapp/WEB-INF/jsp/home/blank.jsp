<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../base/taglib.jsp"%>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>121师徒帮</title>

   <%@ include file="../base/importCss.jsp"%>
   <%@ include file="../base/importJs.jsp"%>
</head>
<body>
    <div id="wrapper">
		<%@ include file="../base/pageHeader.jsp"%>
		
		<%@ include file="../base/sidebarMenu.jsp"%>
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                    
                        <h1 class="page-head-line">${requestScope.permissionMenu.rootName}
                  >${requestScope.permissionMenu.subName}</h1>
                        <h1 class="page-subhead-line">This is dummy text , you can replace it with your original text. </h1>

                    </div>
                </div>
				<div class="row">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-heading"></div>
						<div class="panel-body">

						</div>
					</div></div>
				</div>
            </div>
            <!-- /. PAGE INNER  -->
        </div>
        <!-- /. PAGE WRAPPER  -->
    </div>
    <!-- /. WRAPPER  -->
	<%@ include file="../base/pageFooter.jsp"%>
    <!-- /. FOOTER  -->
</body>
<script type="text/javascript">
</script>
</html>
