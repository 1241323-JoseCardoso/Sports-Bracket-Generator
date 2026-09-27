package bracket.domain;

import java.util.Objects;

public class Match {

    private Team teamOne;
    private Team teamSecond;
    private Team winningTeam;
    private Team loserTeam;

    private Match(Team teamOne, Team teamSecond, Match leftRound, Match rightRound){

        this.teamOne = teamOne;
        this.teamSecond = teamSecond;

    }

    public static Match of(Team teamOne, Team teamSecond, Match leftRound, Match rightRound){

        return new Match(teamOne, teamSecond, leftRound, rightRound);

    }

    public void setWinningTeam(Team winner){

        if(!winner.equals(teamOne) && !winner.equals(teamSecond)){

                throw new IllegalArgumentException("Equipa inválida");
        }

        this.winningTeam = winner;

        winner.incrementWin();

        if(winner.equals(teamOne)){

            this.loserTeam = teamSecond;

        }else if(winner.equals(teamSecond)){

            this.loserTeam = teamOne;

        }

        loserTeam.incrementLoss();

    }

    public Team teamOne(){

        return teamOne;

    }

    public Team teamSecond(){

        return teamSecond;

    }

    public Team getWinningTeam(){

        return winningTeam;

    }



}
