package aui.laboratorium.lab1.repository;

import aui.laboratorium.lab1.DataStorage;
import aui.laboratorium.lab1.entity.Club;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class ClubRepository {
    private DataStorage storage;

    @Autowired
    public ClubRepository(DataStorage storage) { this.storage = storage; }

    public List<Club> findAll() {
        return storage.findAllClubs();
    }

    public void saveNew(Club club) {
        try {
            storage.saveNewClub(club);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        };
    }

    public Optional<Club> findByName(String name) { return storage.findClub(name); }

    public void deleteByName(String name) {
        try {
            storage.deleteClub(name);
        } catch (IllegalArgumentException e){e.printStackTrace();};

    }
}
