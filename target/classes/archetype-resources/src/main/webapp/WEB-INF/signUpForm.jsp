 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<p>Please choose your user name</p>

<form:form action="" method="post" modelAttribute="signUpForm">
<form:input path="userName"/>
<input type="submit" />

</form:form>

