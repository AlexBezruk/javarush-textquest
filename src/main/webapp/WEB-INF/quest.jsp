<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Текстовый квест</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body class="m-3">
<div class="row">
    <div class="col">
        <h1 style="color: green">${currentQuestion.getText()}</h1>

        <c:if test="${currentQuestion.isWin() || currentQuestion.isLoose()}">
            <form action="${pageContext.request.contextPath}/">
                <button class="btn btn-danger btn-lg" type="submit">Начать заново</button>
            </form>
        </c:if>

        <c:if test="${!currentQuestion.isWin() && !currentQuestion.isLoose()}">
            <form  action="${pageContext.request.contextPath}/quest">
                <c:forEach var="i" begin="1" end="${currentQuestion.getAnswers().size()}">
                    <input class="ms-3 me-1" type="radio" name="numberAnswer"
                           value="${i - 1}"/>${currentQuestion.getAnswers().get(i - 1).getText()}
                    <br>
                </c:forEach>
                <button class="mt-3 btn btn-success" type="submit">Ответить</button>
            </form>
        </c:if>
    </div>
    <div class="col">
        <img src=${pageContext.request.contextPath}/img/${currentQuestion.getImg()}>
    </div>
    <footer>
        <p>
            <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#collapseWidthExample" aria-expanded="false" aria-controls="collapseWidthExample">
                Статистика
            </button>
        </p>
        <div style="min-height: 120px">
            <div class="collapse collapse-horizontal" id="collapseWidthExample">
                <div class="card card-body" style="width: 300px">
                    <b>IP address:</b> <i>${pageContext.getRequest().getRemoteAddr()}</i>
                    <br>
                    <b>Имя в игре:</b> <i>${name}</i>
                    <br>
                    <b>Количество сыгранных игр:</b> <i>${count}</i>
                </div>
            </div>
        </div>
    </footer>
</div>
</body>
</html>
