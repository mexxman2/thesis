<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form modelAttribute="searchString" action="searchBySubstring" method="post">
    <div class="row">
        <div class="col-xs-5">
            <form:input class="form-control" path="searchSubstring" id="search_bar" placeholder="Search item or user" aria-label="Search" />
        </div>
        <div class="btn-group col-xs-6 offset-md-1">
            <input type="submit" value="Search item" name="itemSearch" class="btn btn-primary">
            <input type="submit" value="Search user" name="userSearch" class="btn btn-primary">
        </div>
    </div
</form:form>