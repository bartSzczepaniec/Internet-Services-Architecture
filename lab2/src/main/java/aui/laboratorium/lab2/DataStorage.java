package aui.laboratorium.lab2;

import aui.laboratorium.lab2.entity.Club;
import aui.laboratorium.lab2.entity.Footballer;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataStorage {
    private Collection<Footballer> footballers = new HashSet<>();

    private Collection<Club> clubs = new HashSet<>();

    public synchronized List<Footballer> findAllFootballers() {
        return new ArrayList<>(footballers);
    }

    public synchronized List<Club> findAllClubs() { return new ArrayList<>(clubs);}

    public Optional<Club> findClub(String name) {
        return clubs.stream()
                .filter(club -> club.getName().equals(name))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public Optional<Footballer> findFootballer(long id) {
        return footballers.stream()
                .filter(footballer -> footballer.getId() == id)
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void deleteFootballer(long id) throws IllegalArgumentException {
        if (!findFootballer(id).isPresent()) {
            throw new IllegalArgumentException(String.format("The footballer with id \"%d\" does not exist", id));
        }
        else {
            footballers.remove(findFootballer(id).get());
        }
    }

    public synchronized void deleteClub(String name) throws IllegalArgumentException {
        if (!findClub(name).isPresent()) {
            throw new IllegalArgumentException(String.format("The club with name \"%d\" does not exist", name));
        }
        else {
            for(Footballer footballer : footballers) {
                if(footballer.getClub().equals(findClub(name).get())){
                    footballer.setClub(null);
                }
            }
            clubs.remove(findClub(name).get());
        }
    }
    public synchronized void saveNewFootballer(Footballer footballer) throws IllegalArgumentException {
        if (findFootballer(footballer.getId()).isEmpty()) {
            footballers.add(footballer);
        }
        else {
            throw new IllegalArgumentException(String.format("The Footballer id \"%s\" is not unique", footballer.getId()));
        }
    }

    public synchronized void saveNewClub(Club club) throws IllegalArgumentException {
        if (findClub(club.getName()).isEmpty()) {
            clubs.add(club);
        }
        else {
            throw new IllegalArgumentException(String.format("The Club's name \"%s\" is not unique", club.getName()));
        }
    }

}
