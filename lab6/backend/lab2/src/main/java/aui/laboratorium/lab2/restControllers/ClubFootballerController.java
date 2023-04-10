package aui.laboratorium.lab2.restControllers;

import aui.laboratorium.lab2.entity.Club;
import aui.laboratorium.lab2.entity.Footballer;
import aui.laboratorium.lab2.footballerDTO.*;
import aui.laboratorium.lab2.service.ClubService;
import aui.laboratorium.lab2.service.FootballerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/clubs/{clubname}/footballers")
public class ClubFootballerController {

    private ClubService clubService;

    private FootballerService footballerService;

    @Autowired
    public ClubFootballerController(ClubService clubService, FootballerService footballerService){
        this.clubService = clubService;
        this.footballerService = footballerService;
    }

    @GetMapping
    public ResponseEntity<GetFootballersResponse> getFootballers(@PathVariable("clubname") String clubname) {
        Optional<Club> club = clubService.findByName(clubname);
        return club.map(value -> ResponseEntity.ok(GetFootballersResponse.entityToDtoMapper().apply(footballerService.findAllByClub(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{footballer}")
    public ResponseEntity<GetFootballerResponse> getFootballer(@PathVariable("footballer") Long id, @PathVariable("clubname") String clubname) {
        return footballerService.findByClubAndId(clubname, id)
                .map(value -> ResponseEntity.ok(GetFootballerResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping(value = "{footballer}")
    public ResponseEntity<Void> deleteFootballer(@PathVariable("footballer") long id, @PathVariable("clubname") String clubname) {
        Optional<Footballer> footballer = footballerService.findByClubAndId(clubname, id);
        if (footballer.isPresent()) {
            footballerService.deleteById(footballer.get().getId());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createFootballer(@PathVariable("clubname") String clubname,
                                                @RequestBody CreateFootballerToClubRequest request,
                                                UriComponentsBuilder builder) {
        Optional<Club> club = clubService.findByName(clubname);
        if (club.isPresent()) {
            Footballer footballer = CreateFootballerToClubRequest
                    .dtoToEntityMapper(club::get)
                    .apply(request);
            footballerService.saveNew(footballer);
            return ResponseEntity.created(builder.pathSegment("api", "clubs", "{clubname}", "footballers", "{id}")
                    .buildAndExpand(club.get().getName(), footballer.getId()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{footballer}")
    public ResponseEntity<Void> updateFootballer(@RequestBody UpdateFootballerRequest request, @PathVariable("footballer") Long id, @PathVariable("clubname") String clubname) {
        Optional<Club> club = clubService.findByName(clubname);
        if(club.isPresent()) {
            Optional<Footballer> footballer = footballerService.findByClubAndId(clubname,id);
            if (footballer.isPresent()) {
                UpdateFootballerRequest.dtoToEntityUpdater(cname -> clubService.findByName(cname).orElseThrow()).apply(footballer.get(), request);
                footballerService.update(footballer.get());
                return ResponseEntity
                        .accepted()
                        .build();
            } else {
                return ResponseEntity
                        .notFound()
                        .build();
            }
        }
        else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}
