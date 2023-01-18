package app.foot.service;

import app.foot.model.PlayerScorer;
import app.foot.repository.PlayerScoreRepository;
import app.foot.repository.entity.MatchEntity;
import app.foot.repository.entity.PlayerEntity;
import app.foot.repository.entity.PlayerScoreEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PlayerScoreService {

    private final PlayerScorerRepository playerScorerRepository;
    private final PlayerService playerService;
    private final MatchService matchService;

    public List<PlayerScoreEntity> getAllPlayerScores(){
        return playerScorerRepository.findAll();
    }

    public PlayerScoreEntity getById(int id){
        return playerScoreRepository.getById(id) ;
    }

    public List<PlayerScoreEntity> addGoals(List<PlayerScorer> listToAdd, int idMatch){
        List<PlayerScoreEntity> list = new ArrayList<>();
        for (PlayerScorer entity : listToAdd){
            int playerId = entity.getPlayer().getId();
            PlayerEntity playerEntity = playerService.getPlayerById(playerId);
            MatchEntity matchEntity = matchService.getMatchById(idMatch);
            list.add(PlayerScoreEntity.builder()
                    .player(playerEntity)
                    .match(matchEntity)
                    .minute(entity.getMinute())
                    .ownGoal(entity.getIsOwnGoal())
                    .build());
        }
        return playerScoreRepository.saveAll(list);
    }

}


