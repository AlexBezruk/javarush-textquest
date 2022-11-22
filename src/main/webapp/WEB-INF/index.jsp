<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Страница приветствия</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>
<body class="m-3">
<div class="row">
    <div class="col">
        <h1 style="color: green">Пролог</h1>
        <p class="ms-3">
            Ты стоишь в космическом порту и готов подняться на борт <br>
            своего корабля. Разве ты не об этом мечтал? Стать капитаном <br>
            галактического судна с экипажем, который будет совершать <br>
            подвиги под твоим командованием. <br>
            Так что вперед!
        </p>
        <br/>
        <h1 style="color: green">Знакомство с экипажем</h1>
        <p class="ms-3">
            Когда ты поднялся на борт корабля, тебя поприветствовала девушка с черной папкой в руках: <br>
            - Здравствуйте, коммандир! Я Зинаида - ваша помощница. Видите? Там в углу пьет кофе <br>
            наш штурман - сержант Перегарный Шлейф, под штурвалом спит наш бортмеханик - Чёрный Богдан, <br>
            а фотографирует его Сергей Стальная Пятка - наш навигатор. <br>
            А как обращаться к вам?
        </p>
        <br>
        <form action="${pageContext.request.contextPath}/initial">
            <label for="name"></label>
            <input type="text" id="name" name="name" required size="20" maxlength="20">
            <button type="submit" class="btn btn-success">Представиться</button>
        </form>
    </div>
    <div class="col">
        <img src=${pageContext.request.contextPath}/img/space_port.jpg>
    </div>
</div>
</body>
</html>
