Краткое описание программы
Небольшой текстовый квест

Запуск проекта:
Запусти приложение out/artifacts/textquest-1.0-SNAPSHOT.war

Исходные значения
Логика вопросов и ответов находится в файле src/main/java/ua/com/javarush/alexbezruk/textquest/data/Quest.java

Краткое описание классов
В корневом пакете проекта src/main/java/ua/com/javarush/alexbezruk/textquest находятся классы:

AppContextListener, инициализируемый при загрузке приложения

IndexServlet - начальный сервлет

InitialServlet - сервлет, отвечающий за инициализацию пользователя и первого вопроса квеста

QuestServlet - сервлет, отвечающий за смену вопросов квеста

В пакете data содержаться классы:

Answer - класс, описывающий ответ на вопрос

Quest - класс, описывающий логики квеста (взаимосвязь вопросов с ответами)

Question - класс, описывающий вопрос квеста

User - класс, описывающий пользователя

UserRepository - класс, описывающий хранилище пользователей

В пакете service содержиться пакет exception c классом GameException, описывающим собствееное исключение приложения, и  классы:

InitialService - класс-сервис, отвечающий за взаимодействие класса InitialServlet с классом UserRepository

QuestService - класс-сервис, отвечающий за взаимодействие класса QuestServlet с классом UserRepository, обновление статистики и смену вопросов

В пакете src/main/webapp/WEB-INF содержаться:

index.jsp - отвечающий за построение приветсвенной (точки входа в квест) html-страницы

quest.jsp - отвечающий за построение html-страницы с вопросом и ответами