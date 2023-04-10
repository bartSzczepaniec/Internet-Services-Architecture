package aui.laboratorium.lab2.service;

import aui.laboratorium.lab2.entity.Club;
import aui.laboratorium.lab2.entity.Footballer;
import aui.laboratorium.lab2.repository.ClubRepository;
import aui.laboratorium.lab2.repository.FootballerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FootballerService {
    private FootballerRepository repository;

    private ClubRepository clubRepository;

    @Autowired
    public FootballerService(FootballerRepository repository, ClubRepository clubRepository) { this.repository = repository; this.clubRepository = clubRepository;}

    public List<Footballer> findAll() {
        return repository.findAll();
    }

    public List<Footballer> findAllByClub(Club club) {return repository.findAllByClub(club);}
    public void saveNew(Footballer footballer) {
        repository.save(footballer);
    }

    public Optional<Footballer> findById(Long id) { return repository.findById(id); }

    public Optional<Footballer> findByClubAndId(String clubname, Long id) {
        Optional<Club> club = clubRepository.findById(clubname);
        if (club.isPresent()) {
            return repository.findByClubAndId(club.get(), id);
        } else {
            return Optional.empty();
        }

    }

    @Transactional
    public void update(Footballer footballer) {
        repository.save(footballer);
    }
    @Transactional
    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("NOT EXISTING");
        }
    }

}
