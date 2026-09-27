package bracket.domain;

import java.time.LocalDate;
import java.util.*;

public class TournamentSession {

    private TournamentSessionState sessionState;
    private final Tournament tournament;

    private Team winner;

    private LocalDate startDate;
    private LocalDate endDate;

    private List<Team> teamsList;
    private List<Team> roundWinners;

    private List<Match> rounds = new ArrayList<>();
    private int numberTeams;

    private TournamentSession(Tournament tournament, LocalDate startDate, LocalDate endDate) {

        this.tournament = tournament;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sessionState = TournamentSessionState.DRAFTED;
        this.teamsList = new ArrayList<>();
        this.roundWinners = new ArrayList<>();
        this.numberTeams = 0;

    }

    public static TournamentSession of(Tournament tournament, LocalDate startDate, LocalDate endDate){

        if(tournament == null){

            throw new IllegalArgumentException("Torneio inválido");
        }

        if(startDate.isAfter(endDate)){

            throw new IllegalArgumentException("Data inicial é após a data final");

        }

        if(startDate.isBefore(LocalDate.now())){

            throw new IllegalArgumentException("Data inicial colocada encontra-se inválida");

        }

        return new TournamentSession(tournament, startDate, endDate);

    }

    public void setSessionState(TournamentSessionState state){

        this.sessionState = state;

    }

    public void addTeam(Team team){

        if(numberTeams + 1 > tournament.getMaxTeams()){

            throw new IllegalArgumentException("Ultrapassa o número de equipas definido.");
        }

        if(teamsList.contains(team)){

            throw new IllegalArgumentException("Equipa já pertence ao torneio!");

        }

        teamsList.add(team);
        numberTeams++;
    }

    public void runTournament(){

        int number = teamsList.size();

        if(number < 2 || !isPowerOfTwoUsingBitwiseOperation(number)){

            throw new IllegalArgumentException("Número de equipas necessita de ser uma potencia de 2.");

        }

        List<Team> temporary = new ArrayList<>(teamsList);

        int numberRounds = number / 2;
        Collections.shuffle(temporary);

        this.rounds.clear();

        for(int i = 0; i < numberRounds; i++){

            Team firstTeam = temporary.removeLast();
            Team secondTeam = temporary.removeLast();
            this.rounds.add(Match.of(firstTeam, secondTeam, null, null));

        }

        setSessionState(TournamentSessionState.IN_PROGRESS);
    }

    public void nextRound(Match match) {
        if (!rounds.contains(match)) {
            throw new IllegalArgumentException("Este jogo não pertence à ronda atual!");
        }

        if (match.getWinningTeam() == null) {
            throw new IllegalStateException("O jogo ainda não tem um vencedor definido!");
        }

        this.roundWinners.add(match.getWinningTeam());

        if (this.roundWinners.size() == this.rounds.size()) {
            advanceToNextRound();
        }
    }

    public void advanceToNextRound(){

        if(rounds.size() == 1){

            System.out.println("O torneio chegou ao fim!");
            this.winner = rounds.getFirst().getWinningTeam();
            this.sessionState = TournamentSessionState.CONCLUDED;

            return;

        }

        List<Match> nextRoundMatches = new ArrayList<>();

        for(int i = 0; i < roundWinners.size(); i += 2){

            Team teamA = roundWinners.get(i);
            Team teamB = roundWinners.get(i + 1);
            nextRoundMatches.add(Match.of(teamA, teamB, null, null));

        }

        this.rounds = nextRoundMatches;
        this.roundWinners.clear();
    }

    public TournamentSessionState getSessionState(){

        return sessionState;

    }

    public List<Match> getRounds(){

        return rounds;

    }

    public int getNumberRounds(){

        return rounds.size();
    }

    public int getMatchesInCurrentRound(){

        return roundWinners.size() + 1;
    }

    public Team getWinner(){

        return winner;

    }

    //Utility Method --> Straight from W2Schools LMAO

    boolean isPowerOfTwoUsingBitwiseOperation(int n) {
        return (n != 0) && ((n & (n - 1)) == 0);
    }

}
