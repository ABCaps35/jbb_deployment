<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date"%>
<!DOCTYPE html>
	<head>
	    <meta charset="UTF-8">
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
		<script src="/webjars/jquery/jquery.min.js"></script>
		<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	    <title>Home | JoyBundler</title>
	</head>
	<body class="bg-black text-light">
		<div class="container">
			<div class="row w-75 mx-auto justify-content-between border-bottom border-dark py-2 align-items-center">
		        <div class="col-lg">
		            <h2>Welcome, <c:out value="${username}"/>!</h2>
		        </div>
		        <div class="col-lg">
			        <div class="row">
				        <div class="col">
				            <a href="/names/new" class="form-control btn-success text-decoration-none text-center">Add New Name</a>
				        </div>
				        <div class="col">
				        	<form action="/logout">
				            	<input class="form-control btn-danger" type="submit" value="Sign Out"/>
				            </form>
				        </div>
			        </div>
		        </div>
		    </div>
		    <div class="row w-75 my-3 mx-auto p-3 justify-content-center align-items-center border border-dark bg-dark rounded">
		    	<c:if test="${!all_names.isEmpty()}">
			    	<h4>All Names</h4>
			    	<table class="table table-dark table-striped rounded">
		                <thead class="thead">
		                    <tr>
		                    	<th class="align-middle text-center" scope="col">Action</th>
		                        <th class="align-middle text-center" scope="col">Name</th>
		                        <th class="align-middle text-center" scope="col">Gender</th>
		                        <th class="align-middle text-center" scope="col">Origin</th>
		                        <th class="align-middle text-center" scope="col">Votes</th>
		                    </tr>
		                </thead>
		                <c:forEach var="name" items="${all_names}">
		                    <tr>
		                    	<td class="align-middle text-center">
		                    	<c:choose>
		                    	
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
		                    	</td>
		                        <td class="align-middle text-center">
		                        	<a class="text-info" href="/names/${name.id}"><b><c:out value="${name.name}" /></b></a>
		                        	</td>
		                        <td class="align-middle text-center">
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
		                        </td>
		                        <td class="align-middle text-center">Origin: <c:out value="${name.origin}" /></td>
		                       	<td class="align-middle text-center"><c:out value="${name.votedUsers.size()}"/>
		                    </tr>
		                </c:forEach>
		            </table>
	            </c:if>
		    </div>
        </div>
	</body>
</head>