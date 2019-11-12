<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form modelAttribute="searchString" action="searchBySubstring" method="post">
    <p>
        <label for="search_bar">Search item or user</label>
        <form:input path="searchSubstring" id="search_bar" value="" />
    </p>
    <div class="btn-group">
        <input type="submit" value="Search item" name="itemSearch" class="btn btn-primary">
        <input type="submit" value="Search user" name="userSearch" class="btn btn-primary">
    </div>
</form:form>