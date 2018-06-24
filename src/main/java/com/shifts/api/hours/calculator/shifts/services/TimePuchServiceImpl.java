package com.shifts.api.hours.calculator.shifts.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shifts.api.hours.calculator.shifts.model.Location;
import com.shifts.api.hours.calculator.shifts.model.TimePunch;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex P. Andrade on 24/06/2018.
 */
@Service
public class TimePuchServiceImpl implements TimePuchService {
    @Value("${7shifts.api.timepunches}")
    String apiUrl = "https://shiftstestapi.firebaseio.com/timePunches.json";

    @Override
    public List<TimePunch> loadData() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response
                = restTemplate.getForEntity(apiUrl, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        List<TimePunch> timePunches = new ArrayList<>();
        root.forEach(timePuchNode -> {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                TimePunch timePunch = objectMapper.readValue(timePuchNode.toString(), TimePunch.class);
                timePunches.add(timePunch);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return timePunches;
    }
}
