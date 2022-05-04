<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date"%>
<!DOCTYPE html>
	<head>
	    <meta charset="UTF-8">
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
		<script src="/webjars/jquery/jquery.min.js"></script>
		<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	    <title>Add Name | JoyBundler</title>
	</head>
	<body class="bg-black text-light">
		<div class="container">
			<div class="row w-75 mx-auto justify-content-between border-bottom border-dark py-2 align-items-center">
		        <div class="col-lg-auto">
		            <h2>Edit this Name</h2>
		        </div>
		        <div class="col-lg-auto">
			        <div class="row">
				        <div class="col">
				            <a href="/home" class="form-control btn-success text-center text-decoration-none">Back</a>
				        </div>
			        </div>
		        </div>
		    </div>
		    <div class="row w-50 my-3 mx-auto p-3 justify-content-center align-items-center text-center border border-dark bg-dark rounded">
		    	<form:form action="/names/${nameForm.id}" method="post" modelAttribute="nameForm">
		    		<input type="hidden" name="_method" value="put"/>
		    		<!-- 
			    	<div class="row text-start">
			    		<form:label path="name">Name:</form:label>
			    		<form:errors class="badge bg-danger mb-2" path="name"/>
		    			<form:input class="form-control" type="text" path="name"  />
			    	</div>
			    	 -->
			    	 <h2><c:out value="${nameForm.name}"/></h2>
			    	<div class="row text-start mt-2">
				    	<form:label path="typicalGender">Typical Gender:</form:label>
				    	<form:errors class="badge bg-danger mb-2" path="typicalGender"/>
			    		<form:select class="form-select" type="text" path="typicalGender">
			    			<form:option path="typicalGender" value="0">Neutral</form:option>
			    			<form:option path="typicalGender" value="1">Feminine</form:option>
			    			<form:option path="typicalGender" value="2">Masculine</form:option>
			    		</form:select>
			    	</div>
			    	<div class="row text-start">
			    		<form:label path="origin">Origin:</form:label>
			    		<form:errors class="badge bg-danger mb-2" path="origin"/>
		    			<form:input class="form-control" type="text" path="origin"  />
			    	</div>
			    	<div class="row text-start mt-2">
			    		<form:label path="meaning">Meaning:</form:label>
			    		<form:errors class="badge bg-danger mb-2" path="meaning"/>
			    		<form:textarea class="form-control" type="text" path="meaning"></form:textarea>
			    	</div>
			    	<div class="row mt-2">
			    		<input class="form-control btn-success" type="submit" value="Save Name"/>
			    	</div>
		    	</form:form>
            	<form class="mx-0 px-0" action="/names/${nameForm.id}" method="post">
            		<input type="hidden" name="_method" value="delete" />
            		<div class="row mt-2 mx-0 px-0">
            			<input class="form-control btn-danger px-0" type="submit" value="Delete Name"/>
            		</div>
            	</form>
		    </div>
        </div>
	</body>
</head>