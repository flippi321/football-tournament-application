package edu.ntnu.idatt1002.k01g08.fta;

import edu.ntnu.idatt1002.k01g08.fta.objects.Match;
import edu.ntnu.idatt1002.k01g08.fta.objects.Player;
import edu.ntnu.idatt1002.k01g08.fta.objects.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchClockTest {
    Team team1;
    Team team2;

    @BeforeEach
    public void setUp() {
        team1 = new Team("Odd");
        team2 = new Team("München");
        for (int i = 1; i < 12; i++) {
            team1.addPlayer(new Player("Oddær nr. " + i, i));
            team2.addPlayer(new Player("Baron von " + i, i));
        }
    }

    @Test
    public void firstMinuteIsCorrect() {
        Match match = new Match(team1, team2);
        match.start();
        assertEquals("1", match.currentMatchTime());
        match.pause();
        assertEquals("45", match.currentMatchTime());
        match.start();
        assertEquals("46", match.currentMatchTime());
        match.pause();
        assertEquals("90", match.currentMatchTime());
    }

    @Test
    public void matchCountsMinutes() {
        Match match = new Match(team1, team2);
        match.setLengthOfHalf(2);
        synchronized (this) {
            for (int seconds = 0; seconds < 55; seconds++) {
                match.start();
                try {
                    this.wait(1000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                assertEquals("1", match.currentMatchTime());
                System.out.println(seconds);
            }

            try {
                this.wait(10000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            assertEquals("2", match.currentMatchTime());
        }
    }

    @Test
    public void matchCountsExtraMinutes() {
        Match match = new Match(team1, team2);
        match.setLengthOfHalf(1);
        synchronized (this) {
            for (int seconds = 0; seconds < 65; seconds++) {
                match.start();
                try {
                    this.wait(1000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println(seconds);
            }

            assertEquals("1+1", match.currentMatchTime());
        }
    }
}
