package com.example.football.repository;

import com.example.football.models.dto.ExportPlayerDto;
import com.example.football.models.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//ToDo:
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findAllByEmail(String email);


    @Query("SELECT new com.example.football.models.dto.ExportPlayerDto " +
            "(p.firsName, p.lastName, p.position, t.name, t.stadiumName) " +
            "FROM Player p " +
            "JOIN Team t ON t.id = p.team.id " +
            "JOIN Stat s ON s.id = p.stat.id " +
            "WHERE year (p.birthDate) > 1995 AND year (p.birthDate) < 2003 " +
            "ORDER BY s.shooting DESC, s.passing DESC, s.endurance DESC , p.lastName")
    List<ExportPlayerDto> findAllByNamePositionStadiumTeam();

}
