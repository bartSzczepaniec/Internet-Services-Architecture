package aui.laboratorium.lab2.service;

import aui.laboratorium.lab2.entity.Club;
import aui.laboratorium.lab2.event.repository.ClubEventRepository;
import aui.laboratorium.lab2.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClubService {
    private ClubRepository repository;

    private ClubEventRepository eventRepository;

    @Autowired
    public ClubService(ClubRepository repository, ClubEventRepository eventRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
    }

    public List<Club> findAll() {
        return repository.findAll();
    }

    public void saveNew(Club club) {
        repository.save(club);
        eventRepository.create(club);
    }

    public void create(Club club) {
        repository.save(club);
    }

    public Optional<Club> findByName(String name) { return repository.findById(name); }

    @Transactional
    public void update(Club club) {
        repository.save(club);
    }

    @Transactional
    public void deleteByName(String name) {
        try {
            Optional<Club> c = findByName(name);
            if (c.isPresent()){
                Club club = c.get();
                //repository.removeClubFromFootballers(club);
                eventRepository.delete(club);
            }
            repository.deleteById(name);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("NOT EXISTING");
        }
    }

}
