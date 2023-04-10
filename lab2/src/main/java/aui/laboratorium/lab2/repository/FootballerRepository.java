package aui.laboratorium.lab2.repository;

import aui.laboratorium.lab2.DataStorage;
import aui.laboratorium.lab2.entity.Club;
import aui.laboratorium.lab2.entity.Footballer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FootballerRepository extends JpaRepository<Footballer, Long> {
    List<Footballer> findAllByClub(Club club);

    Optional<Footballer> findByClubAndId(Club club, Long id);
}
