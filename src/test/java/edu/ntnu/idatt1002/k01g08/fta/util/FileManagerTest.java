package edu.ntnu.idatt1002.k01g08.fta.util;

import edu.ntnu.idatt1002.k01g08.fta.objects.Match;
import edu.ntnu.idatt1002.k01g08.fta.objects.Player;
import edu.ntnu.idatt1002.k01g08.fta.objects.Team;
import edu.ntnu.idatt1002.k01g08.fta.registers.TeamRegister;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.Random;

import static edu.ntnu.idatt1002.k01g08.fta.util.FileManager.*;

public class FileManagerTest {
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
        Player player = readPlayer(jsonObject);
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
        Team team = readTeam(json);
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
        Team odd = new Team("Odd");
        Team munchen = new Team("München");
        odd.addPlayer(new Player("Kristoffær", 1));
        odd.addPlayer(new Player("Pettær", 2));
        odd.addPlayer(new Player("Gunnær", 3));
        munchen.addPlayer(new Player("Günther", 1));
        munchen.addPlayer(new Player("Pieter", 2));
        munchen.addPlayer(new Player("Adolf", 3));
        Match match = new Match(odd, munchen);
        match.start();
        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            switch (rnd.nextInt(4)) {
                case 0:
                    match.addGoal(rnd.nextBoolean(), rnd.nextInt(3)+1, rnd.nextInt(4), "0"+i);
                    break;
                case 1:
                    match.addSelfGoal(rnd.nextBoolean(), rnd.nextInt(3)+1, "0"+i);
                    break;
                case 2:
                    match.addSubstitution(rnd.nextBoolean(), rnd.nextInt(3)+1, rnd.nextInt(3)+1, "0"+i);
                    break;
                case 3:
                    match.addFoul(rnd.nextBoolean(), rnd.nextInt(3)+1, "tag", rnd.nextInt(3), "0"+i);
            }
        }
        match.end();
        System.out.println(toJson(match));
    }

    @Test
    public void loadTeamRegisterTest() {
        File file = new File("src/test/resources/edu/ntnu/idatt2001/k01g08/fta/util/test_team_register.json");
        try {
            TeamRegister register = loadTeamRegister(file);
            for (Team team : register.getTeams().values()) {
                System.out.println(team);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
