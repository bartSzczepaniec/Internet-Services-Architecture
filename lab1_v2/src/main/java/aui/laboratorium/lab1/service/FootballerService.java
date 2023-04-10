package aui.laboratorium.lab1.service;

import aui.laboratorium.lab1.entity.Club;
import aui.laboratorium.lab1.entity.Footballer;
import aui.laboratorium.lab1.repository.ClubRepository;
import aui.laboratorium.lab1.repository.FootballerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FootballerService {
    private FootballerRepository repository;

    @Autowired
    public FootballerService(FootballerRepository repository) { this.repository = repository; }

    public List<Footballer> findAll() {
        return repository.findAll();
    }

    public void saveNew(Footballer footballer) {
        repository.saveNew(footballer);
    }

    public Optional<Footballer> findById(long id) { return repository.findById(id); }

    public void deleteById(long id) { repository.deleteById(id);}

}
