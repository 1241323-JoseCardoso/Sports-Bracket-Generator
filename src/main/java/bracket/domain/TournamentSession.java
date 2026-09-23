package bracket.domain;

import java.time.LocalDate;

public class TournamentSession {

    private TournamentSessionState sessionState;
    private final Tournament tournament;
    private LocalDate startDate;
    private LocalDate endDate;

    private TournamentSession(Tournament tournament, LocalDate startDate, LocalDate endDate) {

        this.tournament = tournament;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sessionState = TournamentSessionState.DRAFTED;

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

}
