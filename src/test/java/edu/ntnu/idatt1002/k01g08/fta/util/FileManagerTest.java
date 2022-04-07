package edu.ntnu.idatt1002.k01g08.fta.util;

import edu.ntnu.idatt1002.k01g08.fta.objects.*;
import edu.ntnu.idatt1002.k01g08.fta.registers.TeamRegister;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.json.*;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
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
        String[] foulTags = {"hands", "bad language", "racism", "assaulting the referee"};
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

    @Test
    public void readPlayerTest() {
        JsonReader reader = Json.createReader(new StringReader(
                "{" +
                        "\"name\": \"Ole Gunnar Solskjær\"," +
                        "\"number\": 5," +
                        "\"goals\": 6," +
                        "\"assists\": 4," +
                        "\"yellowCards\": 1" +
                    "}"
        ));
        JsonObject jsonObject = reader.readObject();
        Player player = parsePlayer(jsonObject);
        reader.close();
        assertEquals("Ole Gunnar Solskjær", player.getName());
        assertEquals(5, player.getNumber());
        assertEquals(6, player.getGoals());
        assertEquals(4, player.getAssists());
        assertEquals(0, player.getRedCards());
        assertEquals(1, player.getYellowCards());
    }

    @Test
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
    public void readTeamTest() {
        String string =
                "{" +
                    "\"name\": \"Rosenborg\"," +
                    "\"players\": [" +
                        "{\"name\": \"Ole Gunnar Solskjær\", \"number\": 5, \"goals\": 3}," +
                        "{\"name\": \"Bjarne Brøndbo\", \"number\": 3, \"goals\": 1}," +
                        "{\"name\": \"Petter Northug\", \"number\": 11, \"goals\": 5}" +
                    "]" +
                "}";
        JsonReader reader = Json.createReader(new StringReader(string));
        JsonObject json = reader.readObject();
        reader.close();
        Team team = parseTeam(json);
        assertEquals("(Rosenborg: [3 Bjarne Brøndbo, 5 Ole Gunnar Solskjær, 11 Petter Northug])", team.toString());
    }

    @Test
    public void teamToJsonTest() {
        Team team = new Team("Brann");
        team.addPlayer(new Player("Bjarne", 4));
        team.addPlayer(new Player("Bjørn-Johnny", 17));
        team.addPlayer(new Player("Bernhard", 12));
        System.out.println(toJson(team));
    }

    @Test
    public void matchToJsonTest() {
        Team team1 = randomTeam(5);
        Team team2 = randomTeam(5);
        teamRegister = new TeamRegister();
        teamRegister.addTeam(team1);
        teamRegister.addTeam(team2);

        Match match = new Match(team1, team2);
        addRandomMatchHistory(match);
        JsonStructure matchJson = toJson(match);
        File file = new File("src/test/resources/edu/ntnu/idatt2001/k01g08/fta/util/test_match_save.json");
        try {
            saveJson(matchJson, file);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void parseMatchTest() {
        Team team1 = randomTeam(5);
        Team team2;
        do {
            team2 = randomTeam(5);
        } while (team2.getName().equals(team1.getName()));
        teamRegister = new TeamRegister();
        teamRegister.addTeam(team1);
        teamRegister.addTeam(team2);

        Match match0 = new Match(team1, team2);
        addRandomMatchHistory(match0);
        JsonStructure matchJson = toJson(match0);
        File file = new File("src/test/resources/edu/ntnu/idatt2001/k01g08/fta/util/test_match_save.json");
        try {
            saveJson(matchJson, file);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
        try {
            Match match1 = parseMatch(loadJsonObject(new File("src/test/resources/edu/ntnu/idatt2001/k01g08/fta/util/test_match_save.json")), teamRegister);
            System.out.println(match1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveTournamentTest() {
        File file = new File("src/test/resources/edu/ntnu/idatt2001/k01g08/fta/util/test_tournament_save.json");
        ArrayList<Team> teams = new ArrayList<>();
        for (String name : teamNames) {
            Team rndTeam = randomTeam(5, name);
            System.out.println(rndTeam);
            teams.add(rndTeam);
        }
        Tournament tournament = new KnockOut("Power cup", teams);
        System.out.println(toJson(tournament));
    }

    @Test
    public void loadTeamRegisterTest() {
        File file = new File("src/test/resources/edu/ntnu/idatt2001/k01g08/fta/util/test_team_register.json");
        try {
            TeamRegister register = loadTeamRegister(file);
            for (Team team : register.getTeams().values()) {
                System.out.println(team);
            }
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }
}
