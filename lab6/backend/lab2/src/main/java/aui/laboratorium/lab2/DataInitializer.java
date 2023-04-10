package aui.laboratorium.lab2;

import aui.laboratorium.lab2.entity.Club;
import aui.laboratorium.lab2.entity.File;
import aui.laboratorium.lab2.entity.Footballer;
import aui.laboratorium.lab2.service.ClubService;
import aui.laboratorium.lab2.service.FileService;
import aui.laboratorium.lab2.service.FootballerService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;

@Component
public class DataInitializer {
    private ClubService clubService;
    private FootballerService footballerService;

    private FileService fileService;
    @Autowired
    public DataInitializer(ClubService clubService, FootballerService footballerService, FileService fileService) {
        this.footballerService = footballerService;
        this.clubService = clubService;
        this.fileService = fileService;
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

        /**File file1 = File.builder()
                .author("Testowy autor")
                .description("Testowy opis pliku numer 1")
                .type("jpeg")
                .stored_file(getResourceAsByteArray("/files/file3.jpeg"))
                .build();

        File file2 = File.builder()
                .author("Testowy autor")
                .description("Testowy opis pliku numer 2")
                .type("txt")
                .stored_file(getResourceAsByteArray("/files/file2.txt"))
                .build();

        fileService.saveNew(file1);
        fileService.saveNew(file2);*/
    }

    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            return is.readAllBytes();
        }
    }
}
