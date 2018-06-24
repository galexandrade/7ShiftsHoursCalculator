package com.shifts.api.hours.calculator.shifts.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shifts.api.hours.calculator.shifts.model.Location;
import com.shifts.api.hours.calculator.shifts.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex P. Andrade on 23/06/2018.
 */
@Service
public class LocationServiceImpl implements LocationService {
    @Value("${7shifts.api.locations}")
    String apiUrl = "https://shiftstestapi.firebaseio.com/locations.json";

    public LocationServiceImpl() {
    }

    public List<Location> loadData() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response
                = restTemplate.getForEntity(apiUrl, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        List<Location> locations = new ArrayList<>();
        root.forEach(locationNode -> {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                Location location = objectMapper.readValue(locationNode.toString(), Location.class);
                locations.add(location);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return locations;
    }

}
