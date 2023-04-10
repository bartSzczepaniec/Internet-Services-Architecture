package aui.laboratorium.lab1.service;

import aui.laboratorium.lab1.entity.Club;
import aui.laboratorium.lab1.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClubService {
    private ClubRepository repository;

    @Autowired
    public ClubService(ClubRepository repository) { this.repository = repository; }

    public List<Club> findAll() {
        return repository.findAll();
    }

    public void saveNew(Club club) {
        repository.saveNew(club);
    }

    public Optional<Club> findByName(String name) { return repository.findByName(name); }

    public void deleteByName(String name) { repository.deleteByName(name); }
}
