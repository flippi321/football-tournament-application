package edu.ntnu.idatt1002.k01g08.fta;

import edu.ntnu.idatt1002.k01g08.fta.objects.Match;
import edu.ntnu.idatt1002.k01g08.fta.objects.Player;
import edu.ntnu.idatt1002.k01g08.fta.objects.Team;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@Disabled
@DisplayName("Match clock test")
public class MatchClockTest {
    Team team1;
    Team team2;
    Match match;

    @BeforeEach
    public void setUp() {
        team1 = new Team("Odd");
        team2 = new Team("München");
        for (int i = 1; i < 12; i++) {
            team1.addPlayer(new Player("Oddær nr. " + i, i));
            team2.addPlayer(new Player("Baron von " + i, i));
        }
        match = new Match(team1, team2);
    }

    @Test
    @DisplayName("Time starts at zero")
    public void timeStartsAtZero() {
        assertEquals("00:00", match.currentTime());
    }

    @Test
    @DisplayName("Minutes start at one")
    public void minutesStartAtOne() {
        match.start();
        assertEquals("01", match.currentMinute());
    }

    @Test
    @DisplayName("currentTime() returns seconds")
    public void currentTimeReturnsSeconds() {
        synchronized (this) {
            match.start();
            try {
                for (int i = 1; i < 3; i++) {
                    this.wait(1000);
                    assertEquals("00:0" + i, match.currentTime());
                }
                match.pause();
                match.start();
                for (int i = 1; i < 3; i++) {
                    this.wait(1000);
                    assertEquals("45:0" + i, match.currentTime());
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                fail();
            }
        }
    }

    @Test
    @DisplayName("Halves start counting from the right minute")
    public void halvesStartAtRightMinute() {
        match.start();
        assertEquals("01", match.currentMinute());
        match.pause();
        match.start();
        assertEquals("46", match.currentMinute());
        match.pause();
        match.start();
        assertEquals("91", match.currentMinute());
    }

    @Test
    @DisplayName("Halves start counting from the right second")
    public void halvesStartAtRightSecond() {
        match.start();
        assertEquals("00:00", match.currentTime());
        match.pause();
        match.start();
        assertEquals("45:00", match.currentTime());
        match.pause();
        match.start();
        assertEquals("90:00", match.currentTime());
    }

    @Test
    @DisplayName("Match clock counts extra minutes")
    public void matchCountsExtraMinutes() {
        match.setLengthOfHalf(1);
        synchronized (this) {
            try {
                match.start();
                for (int seconds = 0; seconds < 60; seconds++) {
                    this.wait(1000);
                    System.out.println(match.currentTime());
                }
                assertEquals("01+1", match.currentMinute());
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                fail();
            }
        }
    }
}
