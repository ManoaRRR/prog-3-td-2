package app.foot.controller;

import app.foot.model.Match;
import app.foot.model.PlayerScorer;
import app.foot.repository.entity.MatchEntity;
import app.foot.repository.mapper.MatchMapper;
import app.foot.service.MatchService;
import app.foot.service.PlayerScorerService;
import app.foot.service.PlayerSocerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class MatchController {
    private final MatchService service;
    private MatchMapper matchMapper;

    @GetMapping("/matches")
    public List<Match> getMatches() {
        return service.getMatches();
    }

    // Request for post average
    @PostMapping("/matches/{matchId}/goals")
    public ResponseEntity<Match> addGoals(@PathVariable int matchId, @RequestBody List<PlayerScorer> playerScorers ) throws BadRequestException {
        PlayerScorerService.addGoals(playerScorers, matchId);
        for(PlayerScorer scorer : playerScorers){
            if(scorer.getPlayer().getIsGuardian()){
                throw new BadRequestException("goalkeeper can't score");
            }
            if (scorer.getMinute() < 1 || scorer.getMinute() > 90){
                throw new BadRequestException("The minute should be between 1 and 90");
            }
        }
        return  new ResponseEntity<>(matchMapper.toDomain(service.getMatchById(matchId)), HttpStatus.OK);
    }
}




