<%@ taglib prefix="authz"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:useAttribute name="registeredProviderRoleNamesByProviderName"/>
<tiles:useAttribute name="userName"/>
<tiles:useAttribute name="securityLevel"/>

<p>Hello World : <c:choose>
	<c:when test="${securityLevel eq 'Protected'}">
Protected Area.
</c:when>
	<c:when test="${securityLevel eq 'Public'}">

Public Area. 
<p><a href="/protected">Attempt to access</a> a protected resource</p>
	</c:when>
</c:choose></p>



<c:forEach var="entry"
	items="${registeredProviderRoleNamesByProviderName}">


	<authz:authorize access="hasRole('${entry.value}')">
		<p>You are connected with <c:out value="${entry.key}" /></p>
	</authz:authorize>

</c:forEach>

<c:if test="${not empty profileUrls}">

<p>Your profile urls:</p>

<c:forEach var="profileUrl"
	items="${profileUrls}">
		<p><c:out value="${profileUrl}" /></p>
</c:forEach>

</c:if>

<c:forEach var="entry"
	items="${registeredProviderRoleNamesByProviderName}">

	<authz:authorize
		access="hasRole('ROLE_USER') and !hasRole('${entry.value}')">
		<p><a href="/connectWithProvider">Connect</a> your account with <c:out
			value="${entry.key}" /></p>
	</authz:authorize>

</c:forEach>

