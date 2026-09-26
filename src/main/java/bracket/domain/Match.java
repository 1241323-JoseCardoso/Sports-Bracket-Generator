package bracket.domain;

import java.util.Objects;

public class Match {

    private Team teamOne;
    private Team teamSecond;
    private Team winningTeam;
    private Team loserTeam;

    private Match leftRound; //Adversário jogo 1
    private Match rightRound; //Adversário jogo 2
    private Match parent; //Próximo Jogo


    private Match(Team teamOne, Team teamSecond, Match leftRound, Match rightRound){

        this.teamOne = teamOne;
        this.teamSecond = teamSecond;
        this.leftRound = leftRound;
        this.rightRound = rightRound;

        if(leftRound != null) leftRound.parent = this;
        if(rightRound != null) rightRound.parent = this;

    }

    public static Match of(Team teamOne, Team teamSecond, Match leftRound, Match rightRound){

        return new Match(teamOne, teamSecond, leftRound, rightRound);

    }

    //verificar se a node é uma leaf (se tem filhos)

    public boolean isLeaf(){

        return this.leftRound == null && this.rightRound == null;

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

    protected Team teamOne(){

        return teamOne;

    }

    protected Team teamSecond(){

        return teamSecond;

    }

    protected Team getWinningTeam(){

        return winningTeam;

    }



}
