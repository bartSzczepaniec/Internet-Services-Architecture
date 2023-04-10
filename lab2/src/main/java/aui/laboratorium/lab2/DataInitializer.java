package aui.laboratorium.lab2;

import aui.laboratorium.lab2.entity.Club;
import aui.laboratorium.lab2.entity.Footballer;
import aui.laboratorium.lab2.service.ClubService;
import aui.laboratorium.lab2.service.FootballerService;
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

        clubService.saveNew(fcbarcelona);
        clubService.saveNew(lechiagdansk);
        clubService.saveNew(realmadrit);

        Footballer lewandowski = Footballer.builder()
                .full_name("Robert Lewandowski")
                .age(34)
                .average_rating(9.4)
                .club(fcbarcelona)
                .build();

        Footballer pedri = Footballer.builder()
                .full_name("Pedri")
                .age(22)
                .average_rating(9.2)
                .club(fcbarcelona)
                .build()
                ;
        Footballer pique = Footballer.builder()
                .full_name("Gerrard Pique")
                .age(33)
                .average_rating(8.6)
                .club(fcbarcelona)
                .build();

        Footballer peszko = Footballer.builder()
                .full_name("Sławomir Peszko")
                .age(36)
                .average_rating(6.7)
                .club(lechiagdansk)
                .build();

        footballerService.saveNew(lewandowski);
        footballerService.saveNew(peszko);
        footballerService.saveNew(pedri);
        footballerService.saveNew(pique);
    }
}
