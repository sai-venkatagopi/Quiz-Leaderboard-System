package com.quiz.client;

import com.quiz.model.ApiResponse;
import com.quiz.model.LeaderboardEntry;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class QuizApiClient {

    private static final String BASE_URL = "https://devapigw.vidalhealthtpa.com/srm-quiz-task";
    private static final String REG_NO = "AP23110010994"; // CHANGE THIS

    private final RestTemplate restTemplate = new RestTemplate();

    public ApiResponse fetchPoll(int pollIndex) {
        String url = BASE_URL + "/quiz/messages?regNo=" + REG_NO + "&poll=" + pollIndex;
        return restTemplate.getForObject(url, ApiResponse.class);
    }

    public Object submitLeaderboard(List<LeaderboardEntry> leaderboard) {
        String url = BASE_URL + "/quiz/submit";

        Map<String, Object> request = new HashMap<>();
        request.put("regNo", REG_NO);
        request.put("leaderboard", leaderboard);

        return restTemplate.postForObject(url, request, Object.class);
    }
}
