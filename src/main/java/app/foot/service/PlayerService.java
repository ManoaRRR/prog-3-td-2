package app.foot.service;

import app.foot.model.Match;
import app.foot.repository.MatchRepository;
import app.foot.repository.PlayerRepository;
import app.foot.repository.entity.PlayerEntity;
import app.foot.repository.mapper.MatchMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class PlayerService {

    private final PlayerRepository repository;

    public PlayerEntity getPlayerById(int id){
        return repository.getById(id);
    }

}

