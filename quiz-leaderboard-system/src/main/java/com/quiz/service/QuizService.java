package com.quiz.service;

import com.quiz.client.QuizApiClient;
import com.quiz.model.ApiResponse;
import com.quiz.model.Event;
import com.quiz.model.LeaderboardEntry;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {

    private final QuizApiClient apiClient;

    public QuizService(QuizApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<LeaderboardEntry> execute() throws InterruptedException {

        Map<String, Integer> scoreMap = new HashMap<>();
        Set<String> uniqueEvents = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            ApiResponse response = apiClient.fetchPoll(i);

            if (response != null && response.getEvents() != null) {
                for (Event e : response.getEvents()) {

                    String key = e.getRoundId() + "_" + e.getParticipant();

                    if (!uniqueEvents.contains(key)) {
                        uniqueEvents.add(key);
                        scoreMap.put(e.getParticipant(),
                                scoreMap.getOrDefault(e.getParticipant(), 0) + e.getScore());
                    }
                }
            }

            Thread.sleep(5000);
        }

        List<LeaderboardEntry> leaderboard = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : scoreMap.entrySet()) {
            leaderboard.add(new LeaderboardEntry(entry.getKey(), entry.getValue()));
        }

        leaderboard.sort((a, b) -> b.getTotalScore() - a.getTotalScore());

        apiClient.submitLeaderboard(leaderboard);

        return leaderboard;
    }
}
