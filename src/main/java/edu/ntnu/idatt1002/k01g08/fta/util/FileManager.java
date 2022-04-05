package edu.ntnu.idatt1002.k01g08.fta.util;

import edu.ntnu.idatt1002.k01g08.fta.objects.Player;
import edu.ntnu.idatt1002.k01g08.fta.objects.Team;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.List;

public class FileManager {
    static Player readPlayer(JsonObject jsonObject) {
        Player player = new Player(jsonObject.getString("name"), jsonObject.getInt("number"));
        player.increaseGoals(jsonObject.getInt("goals"));
        player.increaseAssists(jsonObject.getInt("assists"));
        player.increaseRedCards(jsonObject.getInt("red-cards"));
        player.increaseYellowCards(jsonObject.getInt("yellow-cards"));
        return player;
    }

    static Team loadTeam(JsonObject jsonObject) {
        Team team = new Team(jsonObject.getString("name"));
        List<JsonObject> players = jsonObject.getJsonArray("players").getValuesAs(JsonObject.class);
        for (JsonObject player : players)
        team.addPlayer(readPlayer(player));
        return team;
    }
}
