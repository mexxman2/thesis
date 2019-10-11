<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h2>Add a new movie or series</h2>

<form:form modelAttribute="uploadItem" action="uploadItemPost">
    <p>
      <label for="input_title">Title</label>
      <form:input path="title" id="input_title" />
      <form:errors path="title" element="div" cssClass="validation-error" />
    </p>
    <p>
      <label for="input_description">Description</label>
      <form:textarea path="description" id="input_description" />
    </p>
    <p>
        <label for="movie">Movie</label>
        <form:radiobutton path="type" value="movie" id="movie" class="switch" checked="checked"/>
    </p>
    <p>
        <label for="series">Series</label>
        <form:radiobutton path="type" value="series" id="series" class="switch"/>
    </p>
    <form:errors path="type" element="div" cssClass="validation-error" />
    <div class="field_specific" id="series">
        <p>
          <label for="input_episodes">Number of episodes</label>
          <form:input path="numberOfEpisodes" id="input_episodes" />
        </p>
        <p>
          <label for="input_seasons">Number of seasons</label>
          <form:input path="numberOfSeasons" id="input_seasons" />
        </p>
    </div>

  <p>
    <button type="submit">Add</button>
  </p>
</form:form>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="/js/formSwitch.js"></script>