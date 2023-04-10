package aui.laboratorium.lab2.repository;

import aui.laboratorium.lab2.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, String> {

}
