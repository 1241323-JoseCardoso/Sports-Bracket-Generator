package main;

import bracket.domain.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BracketTest {

    private Tournament tournament;
    private Tournament tournament2;

    private TournamentSession session;
    private TournamentSession session2;

    private Team team1;
    private Team team2;
    private Team team3;
    private Team team4;
    private Team team5;
    private Team team6;
    private Team team7;
    private Team team8;

    @BeforeEach
    void setUp() {
        tournament = Tournament.of("Champions League", 4);
        tournament2 = Tournament.of("Test", 8);

        session = TournamentSession.of(tournament, LocalDate.now(), LocalDate.now().plusDays(7));
        session2 = TournamentSession.of(tournament2, LocalDate.now(), LocalDate.now().plusDays(7));

        team1 = Team.of("Benfica");
        team2 = Team.of("Porto");
        team3 = Team.of("Sporting");
        team4 = Team.of("Braga");
        team5 = Team.of("Vitória");
        team6 = Team.of("Casa Pia");
        team7 = Team.of("Desportivo das Aves");
        team8 = Team.of("Académia de Coimbra");
    }

    @Test
    void drafterTournament() {
        TournamentSessionState state = session.getSessionState();
        assertEquals(TournamentSessionState.DRAFTED, state);
    }

    @Test
    void validNumberOfTeams() {
        session.addTeam(team1);
        session.addTeam(team2);
        session.addTeam(team3);
        session.addTeam(team4);

        session.runTournament();

        assertEquals(TournamentSessionState.IN_PROGRESS, session.getSessionState());
    }

    @Test
    void invalidNumberOfTeams() {
        session.addTeam(team1);
        session.addTeam(team2);
        session.addTeam(team3);

        assertThrows(IllegalArgumentException.class, () -> {
            session.runTournament();
        });
    }

    @Test
    void addingExistingTeamIntoTournament() {
        session.addTeam(team1);
        session.addTeam(team2);
        session.addTeam(team3);

        assertThrows(IllegalArgumentException.class, () -> {
            session.addTeam(team3);
        });
    }

    @Test
    void numberLeftWith2Rounds() {
        session.addTeam(team1);
        session.addTeam(team2);
        session.addTeam(team3);
        session.addTeam(team4);

        session.runTournament();

        List<Match> rounds = session.getRounds();

        // First Match Set Up ======================//===================
        Match firstMatch = rounds.getFirst();
        MatchSession matchSession = new MatchSession(firstMatch);
        matchSession.setNumberGoalsA(5);
        matchSession.setNumberGoalsB(4);
        matchSession.setFinished();

        session.nextRound(firstMatch);

        // Second Match Set Up ======================//===================
        Match secondMatch = rounds.getLast();
        MatchSession secondSession = new MatchSession(secondMatch);
        secondSession.setNumberGoalsA(3);
        secondSession.setNumberGoalsB(2);
        secondSession.setFinished();

        session.nextRound(secondMatch);

        assertEquals(1, session.getMatchesInCurrentRound());
        assertEquals(TournamentSessionState.IN_PROGRESS, session.getSessionState());
    }

    @Test
    void finalRound() {
        session.addTeam(team1);
        session.addTeam(team2);

        session.runTournament();

        List<Match> rounds = session.getRounds();
        Match firstMatch = rounds.getFirst();

        MatchSession matchSession = new MatchSession(firstMatch);
        matchSession.setNumberGoalsA(5);
        matchSession.setNumberGoalsB(4);
        matchSession.setFinished();

        session.nextRound(firstMatch);

        assertEquals(TournamentSessionState.CONCLUDED, session.getSessionState());
        assertEquals(matchSession.getMatch().getWinningTeam(), session.getWinner());
    }

    @Test
    void numberRoundsWith8Teams() {
        session2.addTeam(team1);
        session2.addTeam(team2);
        session2.addTeam(team3);
        session2.addTeam(team4);
        session2.addTeam(team5);
        session2.addTeam(team6);
        session2.addTeam(team7);
        session2.addTeam(team8);

        session2.runTournament();

        assertEquals(4, session2.getNumberRounds());
    }
}