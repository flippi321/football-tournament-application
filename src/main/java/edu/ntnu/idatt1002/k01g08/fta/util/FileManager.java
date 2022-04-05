package edu.ntnu.idatt1002.k01g08.fta.util;

import edu.ntnu.idatt1002.k01g08.fta.objects.Player;
import edu.ntnu.idatt1002.k01g08.fta.objects.Team;
import edu.ntnu.idatt1002.k01g08.fta.registers.TeamRegister;

import javax.json.*;
import java.util.List;

public class FileManager {
    private static final String PLAYER_NAME_KEY = "name";
    private static final String PLAYER_NUMBER_KEY = "number";
    private static final String PLAYER_GOALS_KEY = "goals";
    private static final String PLAYER_ASSISTS_KEY = "assists";
    private static final String PLAYER_RED_CARDS_KEY = "redCards";
    private static final String PLAYER_YELLOW_CARDS_KEY = "yellowCards";

    private static final String TEAM_NAME_KEY = "name";
    private static final String TEAM_PLAYERS_KEY = "players";

    static Player readPlayer(JsonObject json) {
        Player player = new Player(json.getString(PLAYER_NAME_KEY), json.getInt(PLAYER_NUMBER_KEY));
        for (String key : json.keySet()) {
            switch (key) {
                case PLAYER_GOALS_KEY:
                    player.increaseGoals(json.getInt(key));
                    break;
                case PLAYER_ASSISTS_KEY:
                    player.increaseAssists(json.getInt(key));
                    break;
                case PLAYER_RED_CARDS_KEY:
                    player.increaseRedCards(json.getInt(key));
                    break;
                case PLAYER_YELLOW_CARDS_KEY:
                    player.increaseYellowCards(json.getInt(key));
                    break;
            }
        }
        return player;
    }

    static Team readTeam(JsonObject json) {
        Team team = new Team(json.getString(TEAM_NAME_KEY));
        List<JsonObject> players = json.getJsonArray(TEAM_PLAYERS_KEY).getValuesAs(JsonObject.class);
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

    static JsonObject toJson(Player player) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add(PLAYER_NAME_KEY, player.getName());
        builder.add(PLAYER_NUMBER_KEY, player.getNumber());
        builder.add(PLAYER_GOALS_KEY, player.getGoals());
        builder.add(PLAYER_ASSISTS_KEY, player.getAssists());
        builder.add(PLAYER_YELLOW_CARDS_KEY, player.getYellowCards());
        builder.add(PLAYER_RED_CARDS_KEY, player.getRedCards());
        return builder.build();
    }

    static JsonObject toJson(Team team) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Player player : team) {
            arrayBuilder.add(toJson(player));
        }
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add(TEAM_NAME_KEY, team.getName());
        objectBuilder.add(TEAM_PLAYERS_KEY, arrayBuilder.build());
        return objectBuilder.build();
    }
}
