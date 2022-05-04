<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date"%>
<!DOCTYPE html>
	<head>
	    <meta charset="UTF-8">
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
		<script src="/webjars/jquery/jquery.min.js"></script>
		<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	    <title>View Name | JoyBundler</title>
	</head>
	<body class="bg-black text-light">
		<div class="container">
			<div class="row w-75 mx-auto justify-content-between border-bottom border-dark py-2 align-items-center">
		        <div class="col-lg-auto">
		            <h2>View Name</h2>
		        </div>
		        <div class="col-lg-auto">
			        <div class="row">
				        <div class="col">
				            <a href="/home" class="form-control btn-success text-decoration-none">Back to the Shelves</a>
				        </div>
			        </div>
		        </div>
		    </div>
		    <div class="row w-50 my-3 mx-auto p-3 justify-content-center align-items-center border border-dark bg-dark rounded">
				<div class="row text-center">
		    		<h2><c:out value="${name.name}"/></h2>
		    	</div>
		    	<div class="row text-center mt-0">
		    		<p>( Added by 
		    			<c:choose>
                       		<c:when test="${name.poster.id==user_id}">you</c:when>
                       		<c:otherwise><c:out value="${name.poster.name}"/></c:otherwise>
                       	</c:choose>
                       )</p>
		    	</div>
		    	<div class="row text-start">
		    		<p><b>Typical Gender: </b>
						<c:choose>
                       		<c:when test="${name.typicalGender==0}">
                       			Neutral
                       		</c:when>
                       		<c:when test="${name.typicalGender==1}">
                       			Female
                       		</c:when>
                       		<c:when test="${name.typicalGender==2}">
                       			Male
                       		</c:when>
                       	</c:choose>
					</p>
		    	</div>
		    	<div class="row text-start">
		    		<p><b>Origin:</b> <c:out value="${name.origin}"/></p>
		    	</div>
		    	<c:if test="${name.didVote}">
			    	<div class="row text-start">
			    		<p class="text-info"><b>You voted for this name</b></p>
			    	</div>
		    	</c:if>
		    	<div class="row text-start">
		    		<p><em>Meaning: <c:out value="${name.meaning}" /></em></p>
		    	</div>
		    	<c:choose>
		            <c:when test="${user_id==name.poster.id}">
			            <div class="row text-start">
			            	<a class="form-control btn-success text-decoration-none text-center" href="/names/${name.id}/edit">Edit This Name</a>
			            </div>
		            </c:when>
              		<c:when test="${name.didVote}">
              			<form action="/home" method="post">
	               			<input type="hidden" name="_method" value="put"/>
	               			<input type="hidden" name="name_id" value="${name.id}"/>
	               			<input type="hidden" name="action" value="downvote"/>
	               			<input class="form-control btn-primary px-0" type="submit" value="Downvote"/>
	               		</form>
              		</c:when>
              		<c:otherwise>
               		<form action="/home" method="post">
               			<input type="hidden" name="_method" value="put"/>
               			<input type="hidden" name="name_id" value="${name.id}"/>
               			<input type="hidden" name="action" value="upvote"/>
               			<input class="form-control btn-info px-0" type="submit" value="Upvote"/>
               		</form>
              		</c:otherwise>
	            </c:choose>
		    </div>
        </div>
	</body>
</head>