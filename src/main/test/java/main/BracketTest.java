package main;

import bracket.domain.Team;
import bracket.domain.Tournament;
import bracket.domain.TournamentSession;
import bracket.domain.TournamentSessionState;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BracketTest{

    private Tournament tournament;
    private TournamentSession session;
    private Team team1;
    private Team team2;
    private Team team3;
    private Team team4;

    @BeforeEach
    void setUp() {
        tournament = Tournament.of("Champions League", 4);
        session = TournamentSession.of(tournament, LocalDate.now(), LocalDate.now().plusDays(7));

        team1 = Team.of("Benfica");
        team2 = Team.of("Porto");
        team3 = Team.of("Sporting");
        team4 = Team.of("Braga");
    }

    @Test
    void validNumberOfTeams(){

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
}





