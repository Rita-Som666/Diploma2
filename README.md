# Для запука автотестов необходимо

1. Установить (если нет) и запустить Docker Desktop
2. Клонировать репозиторий в директорию на пути к которой нет ни одной директории с кирилицей в названии
3. Запустить [docker-compose](https://github.com/Rita-Som666/diploma/blob/temp/docker-compose.yml) командой ```docker-compose up --build ```
4. Запустить [jar-файл](https://github.com/Rita-Som666/diploma/blob/temp/artifacts/aqa-shop.jar) командой ```java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar```
5. Запустить тесты командой ```./gradlew test```
6. (При необходимости) Сгенерировать отчёт командой ```./gradlew allureServe``` (Отчёт будет находиться в папке build\reports\allure-report\allureReport)  
