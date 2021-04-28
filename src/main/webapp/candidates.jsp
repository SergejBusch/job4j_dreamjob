<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>--%>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Dream job</title>
</head>
<body>
<div class="container w-50 pt-3">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href = "<c:url value = "/"/>">Home</a>
            </li>
        </ul>
    </div>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Candidates
            </div>
            <div class="card-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Titles</th>
                        <th scope="col">Photos</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${candidates}" var="can">
                    <tr>
                        <td class="w-50">
                            <a href='<c:url value="candidate/edit.jsp?id=${can.id}"/>'>
                                <i class="fa fa-edit mr-3"></i>
                            </a>
                            <c:out value="${can.name}"/>
                        </td>

                        <td class="w-50 h-50">

                            <jsp:useBean id="list" class="java.util.ArrayList" scope="page"/>

                            <c:forEach items="${images}" var="image">
                                <c:if test = "${fn:startsWith(image, can.id)}">
                                    <c:set var="noUse" value="${list.add(image)}"/>
                                </c:if>

                            </c:forEach>

                            <c:if test = "${list.size() > 0}">
                                <div id="carousel${can.id}" class="carousel slide"  style=" width:200px; height: 200px !important;">
                                    <div class="carousel-inner" style=" width:200px; height: 200px !important;">
                                        <c:set var="first" value="true" />
                                        <c:forEach items="${images}" var="image" varStatus="i">
                                            <c:if test = "${fn:startsWith(image, can.id)}">
                                                <div class="carousel-item ${first ? 'active' : ''} ">
                                                    <c:set var="first" value="false"/>
                                                    <img class="d-block" src="
                                                        <c:url value='/download'>
                                                             <c:param name = "name" value = '${image}'/>
                                                             <c:param name = "id" value = '${can.id}'/>
                                                        </c:url>"
                                                        width="200px" height="200px"/>
                                                    <div class="carousel-caption d-none d-md-block">
                                                        <a href="<c:url value='/delete?name=${image}'/>" class="badge badge-pill badge-danger">Delete photo</a>
                                                    </div>
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                    <a class="carousel-control-prev" href="#carousel${can.id}" role="button" data-slide="prev">
                                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                        <span class="sr-only">Previous</span>
                                    </a>
                                    <a class="carousel-control-next" href="#carousel${can.id}" role="button" data-slide="next">
                                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                        <span class="sr-only">Next</span>
                                    </a>
                                </div>
                            </c:if>
                            ${list.clear()}
                            <a href="<c:url value="photoupload.jsp?id=${can.id}"/>" class="badge badge-pill badge-success">Add photo</a>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>