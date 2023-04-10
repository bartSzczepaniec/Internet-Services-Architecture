package aui.laboratorium.lab2;

import aui.laboratorium.lab2.entity.Club;
import aui.laboratorium.lab2.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {
    private ClubService clubService;

    @Autowired
    public DataInitializer(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostConstruct
    private synchronized void init() {
        Club fcbarcelona = Club.builder()
                .name("FC Barcelona")
                .year_of_foundation(1899)
                .teams_market_value(450.5)
                .build();

        Club realmadrit = Club.builder()
                .name("Real Madryt FC")
                .year_of_foundation(1902)
                .teams_market_value(500.50)
                .build();

        Club lechiagdansk = Club.builder()
                .name("Lechia Gdansk")
                .year_of_foundation(1945)
                .teams_market_value(20.75)
                .build();

        clubService.create(fcbarcelona);
        clubService.create(lechiagdansk);
        clubService.create(realmadrit);

    }
}
