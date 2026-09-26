package bracket.domain;

import java.util.HashMap;

public class MatchSession {

    private Match match;
    private boolean finished;

    private HashMap<Team, Integer> teams = new HashMap<>();

    public MatchSession(Match match){

        if(match == null || match.teamOne() == null || match.teamSecond() == null){

            throw new IllegalArgumentException("Erro! Match Node inválido.");

        }

        this.match = match;
        this.teams.put(match.teamOne(), 0);
        this.teams.put(match.teamSecond(), 0);
        this.finished = false;

    }

    public boolean isFinished(){

        return finished;

    }

    public void setNumberGoalsA(int number){

        if(isFinished()) return;
        teams.put(match.teamOne(), number);

    }

    public void setNumberGoalsB(int number){

        teams.put(match.teamSecond(), number);

    }

    public void setFinished(){

        if(isFinished()) return;
        finished = true;

        int numberGoalsA = teams.get(match.teamOne());
        int numberGoalsB = teams.get(match.teamSecond());

        if(numberGoalsA > numberGoalsB){

            match.setWinningTeam(match.teamOne());

        }else if(numberGoalsA < numberGoalsB){

            match.setWinningTeam(match.teamSecond());

        }

    }
}
