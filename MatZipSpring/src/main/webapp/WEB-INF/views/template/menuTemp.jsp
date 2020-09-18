<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
<link rel="stylesheet" type="text/css" href="/res/css/common.css">
<c:forEach items="${css}" var="item">
	<link rel="stylesheet" type="text/css" href="/res/css/${item}.css">
</c:forEach>
</head>
<body>
	<div id="container">
		<header>
			<div id="headerLeft">
				<div class="logo"><a href="/restaurant/restMap"><img alt="맛집" src="/res/img/matzip.logo.png"></a></div> 
				<div class="containerPImg">
						<c:choose>
							<c:when test="${loginUser.profile_img != null}">
								<img class="pImg" src="/res/img/user/${loginUser.i_user}/${loginUser.profile_img}">
							</c:when>
							<c:otherwise>
								<img class="pImg" src="/res/img/default_profile.jpg">
							</c:otherwise>
						</c:choose>
					</div>
				<div class="ml15">${loginUser == null ? '손' : loginUser.nm}님 안녕하세요.</div>
				<div class="ml15"><a href="/user/logout">로그아웃</a></div>
		 	</div>
		 	<div id="headerRight">
		 		<a href="/restaurant/restReg">등록</a>
		 		<div class="ml35"><a href="/user/restFavorite">북마크</a></div>
			</div>
		 </header>
		<section>
			<jsp:include page="/WEB-INF/view/${view}.jsp"></jsp:include>
		</section>
		<footer>
			<span>회사 정보</span>
		</footer>
	</div>
</body>
</html>