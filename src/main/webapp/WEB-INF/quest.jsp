<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Текстовый квест</title>
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
<h1>${currentQuestion.getText()}</h1>

<c:if test="${currentQuestion.isWin() || currentQuestion.isLoose()}">
    <form action="${pageContext.request.contextPath}/">
        <button type="submit">Начать заново</button>
    </form>
</c:if>

<c:if test="${!currentQuestion.isWin() && !currentQuestion.isLoose()}">
    <form action="${pageContext.request.contextPath}/quest">
        <c:forEach var="i" begin="1" end="${currentQuestion.getAnswers().size()}">
            <input type="radio" name="numberAnswer"
                   value="${i - 1}"/>${currentQuestion.getAnswers().get(i - 1).getText()}
            <br>
        </c:forEach>
        <button type="submit">Ответить</button>
    </form>
</c:if>

<div>
    <br><br><br><br><br><br><br><br><br><br>
    Статистика:<br>
    IP address: ${pageContext.getRequest().getRemoteAddr()} <br>
    Имя в игре: ${name} <br>
    Количество игр: ${count}
</div>
</body>
</html>
