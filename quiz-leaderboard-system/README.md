# Quiz Leaderboard System (Backend Integration)

## Features
- Poll external API 10 times
- Deduplicate responses (roundId + participant)
- Aggregate scores
- Generate sorted leaderboard
- Submit results

## Run
mvn spring-boot:run

Then open:
http://localhost:8080/api/quiz/run

## Note
Replace REG_NO in QuizApiClient.java
