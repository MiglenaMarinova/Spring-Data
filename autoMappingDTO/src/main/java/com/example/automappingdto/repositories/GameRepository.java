package com.example.automappingdto.repositories;

import com.example.automappingdto.models.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Game findGamesByTitle(String title);
}
