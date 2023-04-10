package aui.laboratorium.lab2.event.repository;

import aui.laboratorium.lab2.entity.Club;
import aui.laboratorium.lab2.event.dto.PostClubRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class ClubEventRepository {
    private RestTemplate restTemplate;

    @Autowired
    public ClubEventRepository(@Value("${lab.footballers.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Club club) {
        restTemplate.delete("/clubs/{clubname}", club.getName());
    }

    public void create(Club club) {
        restTemplate.postForLocation("/clubs", PostClubRequest.entityToDtoMapper().apply(club));
    }
}
