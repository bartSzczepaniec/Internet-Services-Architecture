package aui.laboratorium.lab1.repository;

import aui.laboratorium.lab1.DataStorage;
import aui.laboratorium.lab1.entity.Club;
import aui.laboratorium.lab1.entity.Footballer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class FootballerRepository {

    private DataStorage storage;

    @Autowired
    public FootballerRepository(DataStorage storage) { this.storage = storage; }

    public List<Footballer> findAll() {
        return storage.findAllFootballers();
    }

    public void saveNew(Footballer footballer) {
        try {
            storage.saveNewFootballer(footballer);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public Optional<Footballer> findById(long id) { return storage.findFootballer(id); }

    public void deleteById(long id) {
        try {
            storage.deleteFootballer(id);
        }
        catch (IllegalArgumentException e){e.printStackTrace();};
    }
}
