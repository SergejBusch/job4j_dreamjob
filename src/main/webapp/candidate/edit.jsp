<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="ru.job4j.dream.model.Candidate" %>
<%@ page import="ru.job4j.dream.store.PsqlStore" %>
<%@ page isELIgnored="false" %>
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
    <%
        String id = request.getParameter("id");
        Candidate candidate = new Candidate(0, "", 1);
        if (id != null) {
            candidate = PsqlStore.instOf().findCandidateById(Integer.parseInt(id));
        }
    %>
    <div class="container pt-3">
        <div class="row">
            <ul class="nav">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>">Home</a>
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
                    <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp"> <c:out value="${user.name}"/> | ??????????</a>
                </li>
            </ul>
        </div>
        <div class="row">
            <div class="card" style="width: 100%">
                <div class="card-header">
                    <% if (id == null) { %>
                    New Candidate.
                    <% } else { %>
                    Edit Candidate.
                    <% } %>
                </div>
                <div class="card-body">
                    <form class="main-form" action="<%=request.getContextPath()%>/candidates.do?id=<%=candidate.getId()%>" method="post">
                        <div class="form-group">
                            <label>Name</label>
                            <input type="text" class="form-control" name="name" value="<%=candidate.getName()%>">
                            <div class="choose mt-3">
                                <label for="cities">City: </label>
                                <select id="cities" name="city_id" data-city_id="<%=candidate.getCityId()%>"></select>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary">Save</button>
                        <p class="message"></p>
                    </form>
                    <% if (id != null) { %>
                    <form id="form-id" action="<%=request.getContextPath()%>/candidates.do?id=<%=candidate.getId()%>&delete=<%=true%>" method="post">
                        <a onclick="document.getElementById('form-id').submit();" href="#">
                            <i class="fa fa-user-times mr-3" style="color: red;"></i>
                        </a>
                    </form>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
    <script src="../js/validation.js"></script>
    <script src="../js/citiesAjax.js"></script>
</body>
</html>
