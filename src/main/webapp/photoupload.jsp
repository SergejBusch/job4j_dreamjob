<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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

    <title>Dream job!</title>
</head>
<body class="min-vh-100">
    <div class="container">
        <div class="row">
            <ul class="nav">
                <li class="nav-item">
                    <a class="nav-link" href = "<c:url value = "/"/>">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href = "<c:url value = "/"/>">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/posts.do">Jobs</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/candidates.do">Candidates</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/post/edit.jsp">Add new job</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/candidate/edit.jsp">Add new candidate</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp"> <c:out value="${user.name}"/> | ??????????</a></a>
                </li>
            </ul>
    </div>
        <div class="min-vh-100 d-flex flex-column justify-content-center align-items-center align-self-center">
            <h2 class="m-3 p-1">Upload image</h2>
            <form class="m-3 p-1" action="
                    <c:url value='/upload'>
                        <c:param name = "id" value = '${param.get("id")}'/>
                    </c:url>" method="post" enctype="multipart/form-data">
                <div class="checkbox">
                    <input type="file" name="file">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </div>
</div>

</body>
</html>