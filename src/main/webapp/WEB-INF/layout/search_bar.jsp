<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form modelAttribute="searchString" action="searchBySubstring" method="post">
    <p>
        <label for="search_bar">Search item or user</label>
        <form:input path="searchSubstring" id="search_bar" />
    </p>
    <p>
        <button type=submit>Search</button>
    </p>
</form:form>