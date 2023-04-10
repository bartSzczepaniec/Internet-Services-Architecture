package aui.laboratorium.lab1;

import aui.laboratorium.lab1.entity.Club;
import aui.laboratorium.lab1.entity.Footballer;
import aui.laboratorium.lab1.service.ClubService;
import aui.laboratorium.lab1.service.FootballerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {
    private ClubService clubService;
    private FootballerService footballerService;

    @Autowired
    public DataInitializer(ClubService clubService, FootballerService footballerService) {
        this.footballerService = footballerService;
        this.clubService = clubService;
    }

    @PostConstruct
    private synchronized void init() {
        Club fcbarcelona = Club.builder()
                .name("FC Barcelona")
                .yearOfFoundation(1899)
                .teamsMarketValue(450.5)
                .build();

        Club realmadrit = Club.builder()
                .name("Real Madryt FC")
                .yearOfFoundation(1902)
                .teamsMarketValue(500.50)
                .build();

        Club lechiagdansk = Club.builder()
                .name("Lechia Gdansk")
                .yearOfFoundation(1945)
                .teamsMarketValue(20.75)
                .build();

        clubService.saveNew(fcbarcelona);
        clubService.saveNew(lechiagdansk);
        clubService.saveNew(realmadrit);

        Footballer lewandowski = Footballer.builder()
                .fullName("Robert Lewandowski")
                .age(34)
                .averageRating(9.4)
                .club(fcbarcelona)
                .id(2)
                .build();

        Footballer peszko = Footballer.builder()
                .fullName("Sławomir Peszko")
                .age(36)
                .averageRating(6.7)
                .club(lechiagdansk)
                .id(53)
                .build();

        footballerService.saveNew(lewandowski);
        footballerService.saveNew(peszko);
    }
}
