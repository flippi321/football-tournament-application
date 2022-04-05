package edu.ntnu.idatt1002.k01g08.fta.util;

import edu.ntnu.idatt1002.k01g08.fta.objects.Player;
import edu.ntnu.idatt1002.k01g08.fta.objects.Team;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
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
        System.out.println(toJson(player));
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
}
