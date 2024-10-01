# Для запука автотестов необходимо

1. Установить (если нет) и запустить Docker Desktop
2. Запустить [docker-compose](https://github.com/Rita-Som666/diploma/blob/temp/docker-compose.yml) командой ```docker-compose up --build ```
3. Запустить [jar-файл](https://github.com/Rita-Som666/diploma/blob/temp/artifacts/aqa-shop.jar) командой ```java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar```
4. Запустить тесты командой ```./gradlew test```
5. (При необходимости) Сгенерировать отчёт командой ```./gradlew allureServe``` (Отчёт будет находиться в папке build\reports\allure-report\allureReport)  
