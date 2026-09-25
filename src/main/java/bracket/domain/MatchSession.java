package bracket.domain;

public class MatchSession {

    private Team teamA;
    private Team teamB;
    private Match match;
    private boolean finished;

    private int numberGoalsA;
    private int numberGoalsB;

    public MatchSession(Team teamA, Team teamB, int numberGoalsA, int numberGoalsB, Match match){

        this.teamA = teamA;
        this.teamB = teamB;
        this.numberGoalsA = numberGoalsA;
        this.numberGoalsB = numberGoalsB;
        this.match = match;
        this.finished = false;

    }

    public void setNumberGoalsA(int number){

        numberGoalsA = number;

    }

    public void setNumberGoalsB(int number){

        numberGoalsB = number;

    }



}
