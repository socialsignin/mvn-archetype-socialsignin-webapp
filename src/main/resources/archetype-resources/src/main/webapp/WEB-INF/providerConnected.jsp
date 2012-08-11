<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<tiles:insertAttribute name="providerName" /> is now connected.  

<c:choose>
<c:when test="${not empty springSocialSecurityConnectInterceptorSavedRequestUrl}">
    <a href="${springSocialSecurityConnectInterceptorSavedRequestUrl}">Continue</a>
</c:when>
<c:otherwise>
	<a href="/">Home</a>
</c:otherwise>
</c:choose> 