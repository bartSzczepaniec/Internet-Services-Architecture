package aui.laboratorium.lab2.restControllers;

import aui.laboratorium.lab2.clubDTO.CreateClubRequest;
import aui.laboratorium.lab2.clubDTO.GetClubResponse;
import aui.laboratorium.lab2.clubDTO.GetClubsResponse;
import aui.laboratorium.lab2.clubDTO.UpdateClubRequest;
import aui.laboratorium.lab2.entity.Club;
import aui.laboratorium.lab2.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/clubs")
public class ClubController {
    private ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    public ResponseEntity<GetClubsResponse> getClubs(){
        return ResponseEntity.ok(GetClubsResponse.entityToDtoMapper().apply(clubService.findAll()));
    }

    @GetMapping("{clubname}")
    public ResponseEntity<GetClubResponse> getClub(@PathVariable("clubname") String clubname) {
        return clubService.findByName(clubname)
                .map(value -> ResponseEntity
                        .ok(GetClubResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());
    }

    @DeleteMapping("{clubname}")
    public ResponseEntity<Void> deleteClub(@PathVariable("clubname") String name) {
        Optional<Club> club = clubService.findByName(name);
        if (club.isPresent()) {
            clubService.deleteByName(club.get().getName());
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
    public ResponseEntity<Void> createClub(@RequestBody CreateClubRequest request, UriComponentsBuilder builder) {
        Club club = CreateClubRequest
                .dtoToEntityMapper()
                .apply(request);
        clubService.saveNew(club);
        return ResponseEntity
                .created(builder
                        .pathSegment("api", "clubs", "{clubname}")
                        .buildAndExpand(club.getName()).toUri())
                .build();
    }

    @PutMapping("{clubname}")
    public ResponseEntity<Void> updateClub(@RequestBody UpdateClubRequest request, @PathVariable("clubname") String clubname) {
        Optional<Club> club = clubService.findByName(clubname);
        if (club.isPresent()) {
            UpdateClubRequest.dtoToEntityUpdater().apply(club.get(), request);
            clubService.update(club.get());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

}
