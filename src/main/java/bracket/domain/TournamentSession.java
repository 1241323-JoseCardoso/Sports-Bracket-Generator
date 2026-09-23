package bracket.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TournamentSession {

    private TournamentSessionState sessionState;
    private final Tournament tournament;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Team> teamsList;
    private int numberTeams;

    private TournamentSession(Tournament tournament, LocalDate startDate, LocalDate endDate) {

        this.tournament = tournament;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sessionState = TournamentSessionState.DRAFTED;
        this.teamsList = new ArrayList<>();
        this.numberTeams = 0;

    }

    public void of(Tournament tournament, LocalDate startDate, LocalDate endDate){

        if(tournament == null){

            throw new IllegalArgumentException("Torneio inválido");
        }

        if(startDate.isAfter(endDate)){

            throw new IllegalArgumentException("Data inicial é após a data final");

        }

        if(startDate.isBefore(LocalDate.now())){

            throw new IllegalArgumentException("Data inicial colocada encontra-se inválida");

        }

        new TournamentSession(tournament, startDate, endDate);

    }

    public void setSessionState(TournamentSessionState state){

        this.sessionState = state;

    }

    public void addTeam(Team team){

        if(numberTeams + 1 > tournament.getMaxTeams()){

            System.out.println("Lista cheia!");
            return;

        }

        teamsList.add(team);
        numberTeams++;
    }

}
