package edu.ntnu.idatt1002.k01g08.fta.util;

import edu.ntnu.idatt1002.k01g08.fta.objects.Player;
import edu.ntnu.idatt1002.k01g08.fta.objects.Team;
import edu.ntnu.idatt1002.k01g08.fta.registers.TeamRegister;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.List;

public class FileManager {
    static Player readPlayer(JsonObject json) {
        Player player = new Player(json.getString("name"), json.getInt("number"));
        for (String key : json.keySet()) {
            switch (key) {
                case "goals":
                    player.increaseGoals(json.getInt(key));
                    break;
                case "assists":
                    player.increaseAssists(json.getInt(key));
                    break;
                case "red-cards":
                    player.increaseRedCards(json.getInt(key));
                    break;
                case "yellow-cards":
                    player.increaseYellowCards(json.getInt(key));
                    break;
            }
        }
        return player;
    }

    static Team readTeam(JsonObject json) {
        Team team = new Team(json.getString("name"));
        List<JsonObject> players = json.getJsonArray("players").getValuesAs(JsonObject.class);
        for (JsonObject player : players)
        team.addPlayer(readPlayer(player));
        return team;
    }

    static TeamRegister readTeamRegister(JsonArray json) {
        TeamRegister teamRegister = new TeamRegister();
        List<JsonObject> teams = json.getValuesAs(JsonObject.class);
        for (JsonObject team : teams) {
            teamRegister.addTeam(readTeam(team));
        }
        return teamRegister;
    }
}
