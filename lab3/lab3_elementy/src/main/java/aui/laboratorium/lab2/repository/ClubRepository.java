package aui.laboratorium.lab2.repository;

import aui.laboratorium.lab2.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, String> {

    @Modifying
    @Query("update Footballer f set f.club = null where f.club = :club")
    void removeClubFromFootballers(@Param("club")Club club);
}
