package aui.laboratorium.lab2.restControllers;

import aui.laboratorium.lab2.clubDTO.CreateClubRequest;
import aui.laboratorium.lab2.clubDTO.GetClubResponse;
import aui.laboratorium.lab2.clubDTO.GetClubsResponse;
import aui.laboratorium.lab2.clubDTO.UpdateClubRequest;
import aui.laboratorium.lab2.entity.Club;
import aui.laboratorium.lab2.entity.Footballer;
import aui.laboratorium.lab2.footballerDTO.CreateFootballerRequest;
import aui.laboratorium.lab2.footballerDTO.GetFootballerResponse;
import aui.laboratorium.lab2.footballerDTO.GetFootballersResponse;
import aui.laboratorium.lab2.footballerDTO.UpdateFootballerRequest;
import aui.laboratorium.lab2.service.ClubService;
import aui.laboratorium.lab2.service.FootballerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/footballers")
public class FootballerController {

    private FootballerService footballerService;

    private ClubService clubService;

    @Autowired
    public FootballerController(FootballerService footballerService, ClubService clubService) {
        this.footballerService = footballerService;
        this.clubService = clubService;
    }

    @GetMapping
    public ResponseEntity<GetFootballersResponse> getFootballers(){
        return ResponseEntity.ok(GetFootballersResponse.entityToDtoMapper().apply(footballerService.findAll()));
    }

    @GetMapping("{footballer}")
    public ResponseEntity<GetFootballerResponse> getFootballer(@PathVariable("footballer") Long id) {
        return footballerService.findById(id)
                .map(value -> ResponseEntity
                        .ok(GetFootballerResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());
    }

    @DeleteMapping("{footballer}")
    public ResponseEntity<Void> deleteFootballer(@PathVariable("footballer") long id) {
        Optional<Footballer> footballer = footballerService.findById(id);
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
    public ResponseEntity<Void> createFootballer(@RequestBody CreateFootballerRequest request, UriComponentsBuilder builder) {
        Footballer footballer = CreateFootballerRequest
                .dtoToEntityMapper(clubname -> clubService.findByName(clubname).orElseThrow())
                .apply(request);
        footballerService.saveNew(footballer);
        return ResponseEntity
                .created(builder
                        .pathSegment("api", "footballers", "{id}")
                        .buildAndExpand(footballer.getId()).toUri())
                .build();
    }

    @PutMapping("{footballer}")
    public ResponseEntity<Void> updateFootballer(@RequestBody UpdateFootballerRequest request, @PathVariable("footballer") Long id) {
        Optional<Footballer> footballer = footballerService.findById(id);
        if (footballer.isPresent()) {
            UpdateFootballerRequest.dtoToEntityUpdater(clubname -> clubService.findByName(clubname).orElseThrow()).apply(footballer.get(), request);
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
}
