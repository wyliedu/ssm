<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- BEGIN SIDEBAR -->
<!-- /. NAV TOP  -->
<nav class="navbar-default navbar-side" role="navigation">
	<div class="sidebar-collapse">
		<ul class="nav" id="main-menu">
			<li>
				<div class="user-img-div">
					<!-- <img src="assets/img/user.png" class="img-thumbnail" /> -->

					<div class="inner-text">
						当前登陆人员：${sessionScope.accountAuth.name} <br/> <p>用户角色：${sessionScope.accountAuth.accountRole.name}</p>
					</div>
				</div>

			</li>
			<c:forEach items="${sessionScope.accountAuth.accountRole.authorityMenus}" var="item" varStatus="status">
			<li><c:choose>
					<c:when test="${item.id eq requestScope.permissionMenu.subId}">
						<a class="active-menu" href="<c:url value='${ item.url }'/>">
					</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${item.id eq requestScope.permissionMenu.rootId}">
							<a href="#" class="active-menu-top">
						</c:when>
						<c:otherwise>
							<a href="<c:url value='${ item.url }'/>" class="">
						</c:otherwise>
					</c:choose>
				</c:otherwise>
				</c:choose> 
			    <i class="${ empty item.itemIcon?"icon-list":item.itemIcon}"></i>${ item.name }
				<c:if test="${!empty item.childrens}"><span class="fa arrow"></span></c:if></a>
				<c:forEach items="${item.childrens}" var="subItem" varStatus="subStatus">
					<c:if test="${subStatus.first}">
						<c:if test="${item.id eq requestScope.permissionMenu.rootId}">
							<ul class="nav nav-second-level  collapse in">
						</c:if>
							<ul class="nav nav-second-level">
						</c:if>
						<li><c:choose>
								<c:when test="${subItem.id eq requestScope.permissionMenu.subId}">
									<a class="active-menu" href="<c:url value='${ subItem.url }'/>">${ subItem.name }</a>
								</c:when>
								<c:otherwise>
									<a href="<c:url value='${ subItem.url }'/>">${ subItem.name}</a>
								</c:otherwise>
							</c:choose></li>
					<c:if test="${subStatus.last}">
						</ul>
					</c:if>
				</c:forEach>
			</li>
		</c:forEach>
		<li><a class="" href="/ssm/home/login"><i class="fa fa-power-off"></i>安全退出</a></li>
		</ul>
	</div>
</nav>
<!-- END SIDEBAR -->