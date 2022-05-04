<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date"%>
<!DOCTYPE html>
	<head>
		<link rel="shortcut icon" href="#">
	    <meta charset="UTF-8">
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
		<script src="/webjars/jquery/jquery.min.js"></script>
		<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	    <title>Login | JoyBundler</title>
	</head>
	<body class="bg-black text-light">
		<div class="container">
		    <div class="row w-75 mx-auto text-start py-2 border-bottom border-dark">
		        <div class="col-lg-auto mt-4">
		            <h2 class="text-success">JoyBundler Names</h2>
		            <p><b>Sign in to discover a world of names!</b></p>
		        </div>
		    </div>
		    <div class="row my-3 mx-auto p-3">
			    <div class="col mx-4 p-4 d-flex flex-column justify-content-start border border-dark rounded bg-dark">
			    	<h2 class="p-0">Register</h2>
			    	<form:form action="/register" method="post" modelAttribute="newUser">
			    		<div class="row mt-2 text-start">
			    			<form:label path="name"><b>Name: </b></form:label>
			    			<form:errors class="badge bg-danger mb-2" path="name"/>
			    			<form:input class="form-control" type="text" path="name" />
			    		</div>
			    		<div class="row mt-2 text-start">
			    			<form:label path="email"><b>Email: </b></form:label>
			    			<form:errors class="badge bg-danger mb-2" path="email"/>
			    			<form:input class="form-control" type="email" path="email" />
			    		</div>
			    		<div class="row mt-2 text-start">
			    			<form:label path="password"><b>Password: </b></form:label>
			    			<form:errors class="badge bg-danger mb-2" path="password"/>
			    			<form:input class="form-control" type="password" path="password" />
			    		</div>
			    		<div class="row mt-2 text-start">
			    			<form:label path="confirm"><b>Confirm Password: </b></form:label>
			    			<form:errors class="badge bg-danger mb-2" path="confirm"/>
			    			<form:input class="form-control" type="password" path="confirm" />
			    		</div>
			    		<div class="row mt-2">
			    			<input class="form-control btn-success" type="submit"/>
			    		</div>
			    	</form:form>
			    </div>
			    <div class="col mx-4 p-4 border border-dark rounded bg-dark">
			    	<h2 class="p-0">Login</h2>
			    	<form:form action="/login" method="post" modelAttribute="newLogin">
			    		<div class="row mt-2 text-start">
			    			<form:label path="email"><b>Email: </b></form:label>
			    			<form:errors class="badge bg-danger mb-2" path="email"/>
			    			<form:input class="form-control" type="email" path="email" />
			    		</div>
			    		<div class="row mt-2 text-start">
			    			<form:label path="password"><b>Password: </b></form:label>
			    			<form:errors class="badge bg-danger mb-2" path="password"/>
			    			<form:input class="form-control" type="password" path="password" />
			    		</div>
			    		<div class="row mt-2">
			    			<input class="form-control btn-success" type="submit"/>
			    		</div>
			    	</form:form>
			    </div>
		    </div>
        </div>
	</body>
</head>