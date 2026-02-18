package com.recruitment.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recruitment.model.Profile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResumeParserService {

    private static final String API_URL = "https://api.apilayer.com/resume_parser/upload";

    @Value("${APILAYER_APIKEY}")
    private String apikey;
    
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    // Method convert arrays to string
    private String convertListToString(Object obj) {
    	if (obj == null) return null;
    	
    	if (obj instanceof List) {
    		List<?> list = (List<?>) obj;
    		return list.stream()
    				.map(item -> {
    					if (item instanceof Map) {
    						Object name = ((Map<?, ?>) item).get("name");
    						return name != null ? name.toString() : "";
    					}
    					return item.toString();	
    				})
    				.collect(Collectors.joining(", "));
     	} 
     	return obj.toString();
    }

    // Just calls API and returns raw JSON string
    private String callParserApi(MultipartFile file) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("apikey", apikey);

        HttpEntity<byte[]> requestEntity = new HttpEntity<>(file.getBytes(), headers);

        ResponseEntity<String> response = restTemplate.exchange(
                API_URL,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        return response.getBody();
    }

    // Parses JSON, maps to Profile, and saves to DB
    public void fillProfileFromResume(MultipartFile file, Profile profile) throws IOException {
    	
        // Call API
        String jsonResponse = callParserApi(file);

        // Parse JSON into Map
        Map<String, Object> map = objectMapper.readValue(jsonResponse, new TypeReference<Map<String, Object>>() {});
        
        
        // Create Profile object
        profile.setName((String) map.get("name"));
        profile.setEmail((String) map.get("email"));
        profile.setPhone((String) map.get("phone"));
        profile.setSkills(convertListToString(map.get("skills")));
        profile.setEducation(convertListToString(map.get("education")));
        profile.setExperience(convertListToString(map.get("experience")));

    }
}
