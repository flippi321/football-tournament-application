package edu.ntnu.idatt1002.k01g08.fta.util;

import edu.ntnu.idatt1002.k01g08.fta.objects.*;
import edu.ntnu.idatt1002.k01g08.fta.registers.TeamRegister;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.json.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

import static edu.ntnu.idatt1002.k01g08.fta.util.FileManager.*;

public class FileManagerTest {
    static String[] teamNames = {"München", "Odd", "Brann", "Rosenborg", "Liverpool", "Manchester", "Pythons", "Sexy Computer Boys"};
    TeamRegister teamRegister;

    Team randomTeam(int size, String teamName) {
        Team team = new Team(teamName);
        Random rnd = new Random();
        String[] playerNames =
                {"Bjørn-Johnny", "Bendik", "John-Fredrik", "Magnus", "Teodor", "Christoffer", "Gunnar", "Ståle",
                        "Heinrich", "Ole-Gunnar", "Brage", "Elise", "Frida", "Lev", "Live", "Zlatan", "Martin"};
        int maxNumber = Math.max(size, 100);
        while (team.size() < size) {
            team.addPlayer(new Player(playerNames[rnd.nextInt(playerNames.length)], rnd.nextInt(maxNumber)));
        }
        return team;
    }

    Team randomTeam(int size) {
        Random rnd = new Random();
        return randomTeam(size, teamNames[rnd.nextInt(teamNames.length)]);
    }

    int randomPlayerNumber(Team team, Random random) {
        int player = random.nextInt(team.size());
        Iterator<Player> iterator = team.iterator();
        for (int i = 1; i < player; i++) {
            iterator.next();
        }
        return iterator.next().getNumber();
    }

    void addRandomMatchHistory(Match match) {
        match.start();
        String[] foulTags = {null, "hands", "bad language", "racism", "assaulting the referee"};
        Random rnd = new Random();
        for (int i = 1; i <= 45; i += 5) {
            boolean homeTeam = rnd.nextBoolean();
            Team team;
            if (homeTeam) team = match.getHomeTeam();
            else team = match.getAwayTeam();

            switch (rnd.nextInt(4)) {
                case 0:
                    match.addGoal(homeTeam, randomPlayerNumber(team, rnd), rnd.nextInt(4), ""+i);
                    break;
                case 1:
                    match.addSelfGoal(homeTeam, randomPlayerNumber(team, rnd), ""+i);
                    break;
                case 2:
                    match.addSubstitution(homeTeam, randomPlayerNumber(team, rnd), randomPlayerNumber(team, rnd), ""+i);
                    break;
                case 3:
                    match.addFoul(homeTeam, randomPlayerNumber(team, rnd), foulTags[rnd.nextInt(foulTags.length)], rnd.nextInt(3), ""+i);
                    break;
            }
        }
        match.end();
    }

    @Nested
    @DisplayName("General method tests")
    public class GeneralMethodTests {
        @Test
        @DisplayName("JSON file loads successfully")
        public void loadJsonTest() {
            try {
                JsonObject json = loadJsonObject(new File("src/test/resources/edu/ntnu/idatt2001/k01g08/fta/util/test_json.json"));
                assertEquals(4, json.getInt("field1"));
                assertEquals("a string", json.getString("field2"));

                JsonArray array = json.getJsonArray("field3");
                assertEquals("item1", array.getString(0));
                assertEquals(2, array.getInt(1));
                assertTrue(array.isNull(2));

            } catch (IOException e) {
                e.printStackTrace();
                fail();
            }
        }

        @Test
        @DisplayName("JSON is successfully saved")
        public void saveJsonTest() throws IOException {
            File file = new File("src/test/resources/edu/ntnu/idatt2001/k01g08/fta/util/test_save0.json");
            JsonObjectBuilder builder = Json.createObjectBuilder();
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                builder.add(""+random.nextInt(), random.nextInt());
            }
            JsonObject json = builder.build();
            saveJson(json, file);
            assertEquals(json, loadJsonObject(file));
        }

        @Test
        @DisplayName("JSON is saved if absent")
        public void saveIfAbsentTest() throws IOException {
            File file1 = new File("src/test/resources/edu/ntnu/idatt2001/k01g08/fta/util/test_save1.json");
            File file2 = new File("src/test/resources/edu/ntnu/idatt2001/k01g08/fta/util/test_save2.json");
            JsonObjectBuilder builder = Json.createObjectBuilder();
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                builder.add(""+random.nextInt(), random.nextInt());
            }
            JsonObject json = builder.build();
            assertFalse(saveIfAbsent(json, file1));
            assertNotEquals(json, loadJsonObject(file1));
            assertTrue(saveIfAbsent(json, file2));
            assertEquals(json, loadJsonObject(file2));
            assertTrue(file2.delete());
        }
    }

    @Nested
    @DisplayName("Parsing tests")
    public class ParseTests {
        @Test
        @DisplayName("Player is parsed successfully")
        public void parsePlayerTest() throws IOException {
            File file = new File("src/test/resources/edu/ntnu/idatt2001/k01g08/fta/util/test_player.json");
            JsonObject jsonObject;
            jsonObject = loadJsonObject(file);
            Player player = parsePlayer(jsonObject);

            assertEquals("Ole Gunnar Solskjær", player.getName());
            assertEquals(5, player.getNumber());
            assertEquals(6, player.getGoals());
            assertEquals(4, player.getAssists());
            assertEquals(0, player.getRedCards());
            assertEquals(1, player.getYellowCards());
        }

        @Test
        @DisplayName("Team is parsed successfully")
        public void parseTeamTest() throws IOException {
            File file = new File("src/test/resources/edu/ntnu/idatt2001/k01g08/fta/util/test_team.json");
            JsonObject json = loadJsonObject(file);
            Team team = parseTeam(json);
            assertEquals("(Rosenborg: [3 Bjarne Brøndbo, 5 Ole Gunnar Solskjær, 11 Petter Northug])", team.toString());
        }

        @Test
        @DisplayName("Team register is parsed successfully")
        public void parseTeamRegisterTest() throws IOException {
            File file = new File("src/test/resources/edu/ntnu/idatt2001/k01g08/fta/util/test_team_register.json");
            TeamRegister register = loadTeamRegister(file);
            assertEquals(6, register.getTeam("Rosenborg").size());
            assertEquals(6, register.getTeam("Sexy Computer Boys").size());
            assertEquals(1, register.getTeam("Manchester").size());
            assertEquals(4, register.getNumberOfTeams());
        }

        @Test
        @DisplayName("Upcoming matches parse successfully")
        public void parseUpcomingMatchTest() throws IOException {
            TeamRegister teamRegister = loadTeamRegister(
                    new File("src/test/resources/edu/ntnu/idatt2001/k01g08/fta/util/test_team_register.json"));
            File file = new File("src/test/resources/edu/ntnu/idatt2001/k01g08/fta/util/test_upcoming_match.json");
            JsonObject json = loadJsonObject(file);
            Match match = parseMatch(json, teamRegister);

            assertEquals("Liverpool", match.getHomeTeam().getName());
            assertEquals("Sexy Computer Boys", match.getAwayTeam().getName());
            assertFalse(match.isStarted());
        }

        @Test
        @DisplayName("Finished matches parse successfully")
        public void parseFinishedMatchTest() throws IOException {
            TeamRegister teamRegister = loadTeamRegister(
                    new File("src/test/resources/edu/ntnu/idatt2001/k01g08/fta/util/test_team_register.json"));
            File file = new File("src/test/resources/edu/ntnu/idatt2001/k01g08/fta/util/test_finished_match.json");
            JsonObject json = loadJsonObject(file);
            Match match = parseMatch(json, teamRegister);

            assertEquals("Rosenborg", match.getHomeTeam().getName());
            assertEquals("Sexy Computer Boys", match.getAwayTeam().getName());
            assertTrue(match.isFinished());

            GameEvent event = match.getGameEvent(0);
            assertEquals(Foul.class, event.getClass());

            event = match.getGameEvent(1);
            assertEquals(Substitution.class, event.getClass());

            event = match.getGameEvent(2);
            assertEquals(Goal.class, event.getClass());

            event = match.getGameEvent(3);
            assertEquals(Goal.class, event.getClass());

            assertEquals("Rosenborg", match.getWinner().getName());
        }

        @Test
        @DisplayName("Knockout tournament parses successfully")
        public void parseKnockOutTest() throws IOException {
            TeamRegister teamRegister = loadTeamRegister(
                    new File("src/test/resources/edu/ntnu/idatt2001/k01g08/fta/util/test_team_register.json"));
            File file = new File("src/test/resources/edu/ntnu/idatt2001/k01g08/fta/util/test_knockout.json");
            Tournament tournament = parseTournament(loadJsonObject(file), teamRegister);
            assertEquals("Power Cup", tournament.getTournamentName());
            assertEquals("12:05", tournament.getStartDate());
            assertEquals(200, tournament.getFirstPrize());
            assertEquals(4, tournament.getNumberOfTeams());
            assertEquals(1, tournament.getUpcomingMatches().size());
            assertEquals(2, tournament.getMatches().size());
        }
    }

    @Nested
    @DisplayName("To JSON conversion tests")
    public class ToJsonTests {
        @Test
        @DisplayName("Player successfully converted to JSON")
        public void playerToJsonTest() {
            Player player = new Player("Martin Ødegaard", 8);
            player.increaseGoals();
            player.increaseAssists(2);
            player.increaseRedCards(4);
            player.increaseYellowCards(3);
            JsonObjectBuilder builder = Json.createObjectBuilder();
            builder.add("name", "Martin Ødegaard").add("number", 8);
            builder.add("goals", 1).add("assists", 2).add("redCards", 4).add("yellowCards", 3);
            assertEquals(builder.build(),toJson(player));
        }

        @Test
        @DisplayName("Team successfully converted to JSON")
        public void teamToJsonTest() {
            Team team = new Team("Brann");
            team.addPlayer(new Player("Bjarne", 4));
            team.addPlayer(new Player("Bjørn-Johnny", 17));
            team.addPlayer(new Player("Bernhard", 12));
            JsonObject json = toJson(team);
            Team parsedTeam = parseTeam(json);
            assertEquals("Brann", parsedTeam.getName());
            assertEquals("Bjarne", parsedTeam.getPlayer(4).getName());
            assertEquals("Bjørn-Johnny", parsedTeam.getPlayer(17).getName());
            assertEquals("Bernhard", parsedTeam.getPlayer(12).getName());
        }

        @Test
        @DisplayName("Upcoming matches successfully converted to JSON")
        public void upcomingMatchToJsonTest() {
            Team team1 = randomTeam(5, "Odd");
            Team team2 = randomTeam(5, "Sexy Computer Boys");
            Team team3 = randomTeam(5, "Rosenborg");
            teamRegister = new TeamRegister();
            teamRegister.addTeam(team1);
            teamRegister.addTeam(team2);
            teamRegister.addTeam(team3);

            Match match = new Match(team1, team2);
            JsonObject matchJson = toJson(match);
            Match parsedMatch = parseMatch(matchJson, teamRegister);

            assertEquals(match.getHomeTeam(), parsedMatch.getHomeTeam());
            assertEquals(match.getAwayTeam(), parsedMatch.getAwayTeam());
            assertFalse(parsedMatch.isStarted());
        }

        @Test
        @DisplayName("Partially setup match successfully converted to JSON")
        public void partiallySetUpMatchToJsonTest() {
            Team team1 = randomTeam(5);
            Team team2;
            do {team2 = randomTeam(5);}
            while (team1.getName().equals(team2.getName()));
            teamRegister = new TeamRegister();
            teamRegister.addTeam(team1);
            teamRegister.addTeam(team2);

            Match match = new Match();
            match.setHomeTeam(team1);
            JsonObject matchJson = toJson(match);
            Match parsedMatch = parseMatch(matchJson, teamRegister);

            assertEquals(match.getHomeTeam(), parsedMatch.getHomeTeam());
            assertEquals(match.getAwayTeam(), parsedMatch.getAwayTeam());
            assertFalse(parsedMatch.isStarted());

            match = new Match();
            match.setAwayTeam(team1);
            matchJson = toJson(match);
            parsedMatch = parseMatch(matchJson, teamRegister);

            assertEquals(match.getHomeTeam(), parsedMatch.getHomeTeam());
            assertEquals(match.getAwayTeam(), parsedMatch.getAwayTeam());
            assertFalse(parsedMatch.isStarted());
        }

        @Test
        @DisplayName("Finished match successfully converted to JSON")
        public void finishedMatchToJsonTest() {
            Team team1 = randomTeam(5);
            Team team2;
            do {team2 = randomTeam(5);}
            while (team1.getName().equals(team2.getName()));
            teamRegister = new TeamRegister();
            teamRegister.addTeam(team1);
            teamRegister.addTeam(team2);

            Match match = new Match(team1, team2);
            addRandomMatchHistory(match);
            JsonObject matchJson = toJson(match);
            Match parsedMatch = parseMatch(matchJson, teamRegister);

            assertEquals(match.getHomeTeam(), parsedMatch.getHomeTeam());
            assertEquals(match.getAwayTeam(), parsedMatch.getAwayTeam());
            assertTrue(parsedMatch.isFinished());

            for (int i = 0; i < 9; i++) {
                assertEquals(match.getGameEvent(i).getEvent(), parsedMatch.getGameEvent(i).getEvent());
            }
        }

        @Test
        @DisplayName("Tournament successfully converted to JSON")
        public void tournamentToJsonTest() throws IOException {
            TeamRegister teamRegister = loadTeamRegister(
                    new File("src/test/resources/edu/ntnu/idatt2001/k01g08/fta/util/test_team_register.json"));
            File file = new File("src/test/resources/edu/ntnu/idatt2001/k01g08/fta/util/test_knockout.json");
            JsonObject jsonObject = loadJsonObject(file);
            Tournament tournament = parseTournament(jsonObject, teamRegister);
            assertEquals(jsonObject, toJson(tournament));
        }
    }
}
