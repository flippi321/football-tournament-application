package edu.ntnu.idatt1002.k01g08.fta.util;

import edu.ntnu.idatt1002.k01g08.fta.objects.*;
import edu.ntnu.idatt1002.k01g08.fta.registers.TeamRegister;

import javax.json.*;
import java.io.*;
import java.util.*;

/**
 * Contains static methods for reading and writing teams and tournaments to JSON files.
 */
public class FileManager {
    private static final String PLAYER_NAME_KEY = "name";
    private static final String PLAYER_NUMBER_KEY = "number";
    private static final String PLAYER_GOALS_KEY = "goals";
    private static final String PLAYER_ASSISTS_KEY = "assists";
    private static final String PLAYER_RED_CARDS_KEY = "redCards";
    private static final String PLAYER_YELLOW_CARDS_KEY = "yellowCards";

    private static final String TEAM_NAME_KEY = "name";
    private static final String TEAM_PLAYERS_KEY = "players";

    private static final String TOURNAMENT_NAME_KEY = "name";
    private static final String TOURNAMENT_PRIZE_KEY = "prize";
    private static final String TOURNAMENT_FORMAT_KEY = "format";
    private static final String TOURNAMENT_START_DATE_KEY = "startDate";
    private static final String TOURNAMENT_MATCH_LENGTH_KEY = "matchLength";
    private static final String TOURNAMENT_UPCOMING_MATCHES_KEY = "upcomingMatches";
    private static final String TOURNAMENT_PREVIOUS_MATCHES_KEY = "previousMatches";
    private static final String TOURNAMENT_TEAMS_KEY = "teams";

    private static final String MATCH_HOME_TEAM_KEY = "homeTeam";
    private static final String MATCH_AWAY_TEAM_KEY = "awayTeam";
    private static final String MATCH_EVENTS_KEY = "events";

    private static final String GAME_EVENT_HOME_TEAM_KEY = "homeTeam";
    private static final String GAME_EVENT_PLAYER_NUMBER_KEY = "player";
    private static final String GAME_EVENT_TIMESTAMP_KEY = "timestamp";
    private static final String GAME_EVENT_TYPE_KEY = "type";

    private static final String GAME_EVENT_SUBSTITUTION_TYPE = "substitution";
    private static final String GAME_EVENT_GOAL_TYPE = "goal";
    private static final String GAME_EVENT_SELF_GOAL_TYPE = "selfGoal";
    private static final String GAME_EVENT_FOUL_TYPE = "foul";

    private static final String FOUL_TAG_KEY = "foulTag";
    private static final String FOUL_GIVE_CARD_KEY = "giveCard";

    private static final String SUBSTITUTION_PLAYER_IN_KEY = "playerIn";
    private static final String SUBSTITUTION_PLAYER_OUT_KEY = "playerOut";

    private static final String GOAL_ASSISTING_KEY = "assisting";

    /**
     * Reads and returns a JSON structure from a file.
     * @param file a file to read from
     * @return a JSON structure read from the file
     * @throws IOException if the file for some reason could not be opened for reading
     * @throws JsonException if a JSON object or array cannot be created due to incorrect representation
     */
    static JsonStructure loadJson(File file) throws IOException {
        try (FileReader fileReader = new FileReader(file);
             JsonReader jsonReader = Json.createReader(fileReader)) {
                return jsonReader.read();
        }
    }

    /**
     * Reads and returns a JSON array from a file.
     * @param file a file to read from
     * @return a JSON structure read from the file
     * @throws IOException if the file for some reason could not be opened for reading
     * @throws JsonException if a JSON array cannot be created due to incorrect representation
     */
    static JsonArray loadJsonArray(File file) throws IOException {
        return (JsonArray) loadJson(file);
    }

    /**
     * Reads and returns a JSON object from a file.
     * @param file a file to read from
     * @return a JSON structure read from the file
     * @throws IOException if the file for some reason could not be opened for reading
     * @throws JsonException if a JSON object cannot be created due to incorrect representation
     */
    static JsonObject loadJsonObject(File file) throws IOException {
        return (JsonObject) loadJson(file);
    }

    /**
     * Saves a JSON structure to a file.
     * @param json A JSON structure to save to a file
     * @param file the file to save the JSON object to
     * @throws IOException if file for some reason could not be saved
     */
    static void saveJson(JsonStructure json, File file) throws IOException {
        try (FileWriter fileWriter = new FileWriter(file);
             JsonWriter jsonWriter = Json.createWriter(fileWriter)) {
                jsonWriter.write(json);
        }
    }

    /**
     * Saves a JSON structure to a file, if the file does not already exist. Returns true if successful.
     * @param json A JSON structure to save to a file
     * @param file the file to save the JSON object to
     * @return true if the file was created
     * @throws IOException if file for some reason could not be saved
     */
    static boolean saveIfAbsent(JsonObject json, File file) throws IOException {
        if (file.exists()) return false;
        saveJson(json, file);
        return true;
    }

    /**
     * Returns a player parsed from a JSON object.
     * @param json a JSON object to parse
     * @return a player parsed from the specified JSON object
     */
    static Player parsePlayer(JsonObject json) {
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

    /**
     * Returns a team parsed from a JSON object.
     * @param json a JSON object to parse
     * @return a team parsed from the specified JSON object
     */
    static Team parseTeam(JsonObject json) {
        Team team = new Team(json.getString(TEAM_NAME_KEY));
        List<JsonObject> players = json.getJsonArray(TEAM_PLAYERS_KEY).getValuesAs(JsonObject.class);
        for (JsonObject player : players)
        if (!team.addPlayer(parsePlayer(player))) throw new RuntimeException("player number already registered");
        return team;
    }

    /**
     * Returns a team register parsed from a JSON array.
     * @param json a JSON array to parse
     * @return a team register parsed from the specified JSON array
     */
    static TeamRegister parseTeamRegister(JsonArray json) {
        TeamRegister teamRegister = new TeamRegister();
        List<JsonObject> teams = json.getValuesAs(JsonObject.class);
        for (JsonObject team : teams) {
            teamRegister.addTeam(parseTeam(team));
        }
        return teamRegister;
    }

    /**
     * Returns a match parsed from a JSON object, using teams pulled from the given team register.
     * @param json a JSON object to parse
     * @param register a register to pull teams from
     * @return a match parsed from the specified JSON object
     */
    static Match parseMatch(JsonObject json, TeamRegister register) {
        JsonValue homeTeamName = json.get(MATCH_HOME_TEAM_KEY);
        JsonValue awayTeamName = json.get(MATCH_AWAY_TEAM_KEY);
        Match match = new Match();
        if (homeTeamName instanceof JsonString) {
            Team homeTeam = register.getTeam(((JsonString) homeTeamName).getString());
            match.setHomeTeam(homeTeam);
        }
        if (awayTeamName instanceof JsonString) {
            Team awayTeam = register.getTeam(((JsonString) awayTeamName).getString());
            match.setAwayTeam(awayTeam);
        }

        if (json.containsKey(MATCH_EVENTS_KEY)) {
            match.end();

            for (JsonObject eventJson : json.getJsonArray(MATCH_EVENTS_KEY).getValuesAs(JsonObject.class)) {
                boolean isHomeTeam = eventJson.getBoolean(GAME_EVENT_HOME_TEAM_KEY);
                String timestamp = eventJson.getString(GAME_EVENT_TIMESTAMP_KEY);
                int player;

                switch (eventJson.getString(GAME_EVENT_TYPE_KEY)) {
                    case GAME_EVENT_GOAL_TYPE:
                        player = eventJson.getInt(GAME_EVENT_PLAYER_NUMBER_KEY);
                        JsonNumber assistingValue = eventJson.getJsonNumber(GOAL_ASSISTING_KEY);
                        int assisting = 0;
                        if (assistingValue != null) assisting = assistingValue.intValue();
                        match.addGoal(isHomeTeam, player, assisting, timestamp);
                        break;
                    case GAME_EVENT_SELF_GOAL_TYPE:
                        player = eventJson.getInt(GAME_EVENT_PLAYER_NUMBER_KEY);
                        match.addSelfGoal(isHomeTeam, player, timestamp);
                        break;
                    case GAME_EVENT_FOUL_TYPE:
                        player = eventJson.getInt(GAME_EVENT_PLAYER_NUMBER_KEY);
                        int giveCard = 0;
                        String foulTag = null;
                        if (eventJson.containsKey(FOUL_GIVE_CARD_KEY)) giveCard = eventJson.getInt(FOUL_GIVE_CARD_KEY);
                        if (eventJson.containsKey(FOUL_TAG_KEY)) foulTag = eventJson.getString(FOUL_TAG_KEY);
                        match.addFoul(isHomeTeam, player, foulTag, giveCard, timestamp);
                        break;
                    case GAME_EVENT_SUBSTITUTION_TYPE:
                        int playerIn = eventJson.getInt(SUBSTITUTION_PLAYER_IN_KEY);
                        int playerOut = eventJson.getInt(SUBSTITUTION_PLAYER_OUT_KEY);
                        match.addSubstitution(isHomeTeam, playerIn, playerOut, timestamp);
                        break;
                }
            }
        }
        return match;
    }

    static Tournament parseTournament(JsonObject json, TeamRegister register) {
        String name = "";
        String format = "";
        int matchLength = 90;
        int prize = 0;
        String startDate = "";
        ArrayList<Team> teams = new ArrayList<>();
        List<Match> upcoming = new ArrayList<>();
        List<Match> previous = new ArrayList<>();

        for (String key : json.keySet()) {
            switch (key) {
                case TOURNAMENT_FORMAT_KEY:
                    format = json.getString(key);
                    break;
                case TOURNAMENT_NAME_KEY:
                    name = json.getString(key);
                    break;
                case TOURNAMENT_MATCH_LENGTH_KEY:
                    matchLength = json.getInt(key);
                    break;
                case TOURNAMENT_PRIZE_KEY:
                    prize = json.getInt(key);
                    break;
                case TOURNAMENT_START_DATE_KEY:
                    startDate = json.getString(key);
                    break;
                case TOURNAMENT_TEAMS_KEY:
                    for (JsonString team : json.getJsonArray(key).getValuesAs(JsonString.class)) {
                        teams.add(register.getTeam(team.getString()));
                    }
                    break;
                case TOURNAMENT_UPCOMING_MATCHES_KEY:
                    for (JsonObject match : json.getJsonArray(key).getValuesAs(JsonObject.class)) {
                        upcoming.add(parseMatch(match, register));
                    }
                    break;
                case TOURNAMENT_PREVIOUS_MATCHES_KEY:
                    for (JsonObject match : json.getJsonArray(key).getValuesAs(JsonObject.class)) {
                        previous.add(parseMatch(match, register));
                    }
                    break;
            }
        }

        Tournament tournament;

        switch (format) {
            case "KnockOut":
                tournament = new KnockOut(teams, name, prize, startDate, matchLength);
                break;
            default:
                throw new RuntimeException("format type not recognised");
        }

        tournament.getUpcomingMatches().clear();
        tournament.getUpcomingMatches().addAll(upcoming);
        tournament.getMatches().addAll(previous);

        return tournament;
    }

    /**
     * Loads a team register from a file. Convenience method for parseTeamRegister(loadJsonArray(file)).
     * @param file a file to load a team register from
     * @return a team register parsed from the contents of the file
     * @throws IOException if the file for some reason could not be opened for reading
     */
    static TeamRegister loadTeamRegister(File file) throws IOException {
        return parseTeamRegister(loadJsonArray(file));
    }

    /**
     * Loads a tournament from a file, pulling teams from a team register.
     * Convenience method for parseTournament(loadJsonObject(file), teamRegister).
     * @param file a file to load a tournament from
     * @return a tournament parsed from the contents of the file
     * @throws IOException if the file for some reason could not be opened for reading
     */
    static Tournament loadTournament(File file, TeamRegister teamRegister) throws IOException {
        return parseTournament(loadJsonObject(file), teamRegister);
    }

    /**
     * Returns a JSON object representation of the specified player.
     * @param player a player to get the JSON representation of
     * @return a JSON object representation of the player
     */
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

    /**
     * Returns a JSON object representation of the specified team.
     * @param team a team to get the JSON representation of
     * @return a JSON object representation of the team
     */
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

    /**
     * Returns a JSON object representation of the specified game event.
     * @param event a game event to get the JSON representation of
     * @param homeTeam the home team of the match
     * @return a JSON object representation of the game event
     */
    static JsonObject toJson(GameEvent event, Team homeTeam) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add(GAME_EVENT_HOME_TEAM_KEY, event.getTeam() == homeTeam);
        objectBuilder.add(GAME_EVENT_TIMESTAMP_KEY, event.getTimeStampOfMatchTime());
        Player player = event.getPlayer();
        if (player != null) {
            objectBuilder.add(GAME_EVENT_PLAYER_NUMBER_KEY, player.getNumber());
        }
        if (event.getClass() == Foul.class) objectBuilder.addAll(toJson((Foul) event));
        else if (event.getClass() == Goal.class) objectBuilder.addAll(toJson((Goal) event, homeTeam));
        else if (event.getClass() == Substitution.class) objectBuilder.addAll(toJson((Substitution) event));
        return objectBuilder.build();
    }

    /**
     * Returns a JSON object builder containing fields specific to the Foul subclass.
     * @param foul a foul the convert to JSON
     * @return a JSON object builder containing fields specific to the Foul subclass
     */
    static JsonObjectBuilder toJson(Foul foul) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add(GAME_EVENT_TYPE_KEY, GAME_EVENT_FOUL_TYPE);
        String foulTag = foul.getFoulTag();
        if (foulTag != null) builder.add(FOUL_TAG_KEY, foulTag);
        if (foul.getRedCard() > 0) builder.add(FOUL_GIVE_CARD_KEY, 2);
        else if (foul.getYellowCard() > 0) builder.add(FOUL_GIVE_CARD_KEY, 1);
        return builder;
    }

    /**
     * Returns a JSON object builder containing fields specific to the Goal subclass.
     * @param goal a goal the convert to JSON
     * @return a JSON object builder containing fields specific to the Goal subclass
     */
    static JsonObjectBuilder toJson(Goal goal, Team homeTeam) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        Player player = goal.getPlayer();
        if (goal.getTeam().getPlayer(player.getNumber()) == player) {
            builder.add(GAME_EVENT_TYPE_KEY, GAME_EVENT_GOAL_TYPE);
        } else {
            builder.add(GAME_EVENT_TYPE_KEY, GAME_EVENT_SELF_GOAL_TYPE);
            builder.add(GAME_EVENT_HOME_TEAM_KEY, goal.getTeam() != homeTeam);
        }
        if (goal.getAssistingPlayer() != null) builder.add(GOAL_ASSISTING_KEY, goal.getAssistingPlayer().getNumber());
        return builder;
    }

    /**
     * Returns a JSON object builder containing fields specific to the Substitution subclass.
     * @param substitution a foul the convert to JSON
     * @return a JSON object builder containing fields specific to the Substitution subclass
     */
    static JsonObjectBuilder toJson(Substitution substitution) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add(GAME_EVENT_TYPE_KEY, GAME_EVENT_SUBSTITUTION_TYPE);
        builder.add(SUBSTITUTION_PLAYER_IN_KEY, substitution.getPlayerIn().getNumber());
        builder.add(SUBSTITUTION_PLAYER_OUT_KEY, substitution.getPlayerOut().getNumber());
        return builder;
    }

    /**
     * Returns a JSON object representation of the specified match.
     * @param match a match to get the JSON representation of
     * @return a JSON object representing the specified match
     */
    static JsonObject toJson(Match match) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        Team homeTeam = match.getHomeTeam();
        Team awayTeam = match.getAwayTeam();
        if (homeTeam != null) {
            objectBuilder.add(MATCH_HOME_TEAM_KEY, homeTeam.getName());
        }
        if (awayTeam != null) {
            objectBuilder.add(MATCH_AWAY_TEAM_KEY, awayTeam.getName());
        }

        Iterator<GameEvent> eventIterator = match.iterator();

        if (eventIterator.hasNext()) {
            JsonArrayBuilder eventArrayBuilder = Json.createArrayBuilder();
            for (GameEvent event : match) {
                eventArrayBuilder.add(toJson(event, homeTeam));
            }
            objectBuilder.add(MATCH_EVENTS_KEY, eventArrayBuilder.build());
        }

        return objectBuilder.build();
    }

    /**
     * Returns a JSON object representation of the specified tournament.
     * @param tournament a match to get the JSON representation of
     * @return a JSON object representing the specified tournament
     */
    static JsonObject toJson(Tournament tournament) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add(TOURNAMENT_FORMAT_KEY, tournament.getClass().getSimpleName());
        objectBuilder.add(TOURNAMENT_NAME_KEY, tournament.getTournamentName());

        if (tournament.getFirstPrize() > 0) {
            objectBuilder.add(TOURNAMENT_PRIZE_KEY, tournament.getFirstPrize());
        }

        if (tournament.getStartDate() != null) {
            objectBuilder.add(TOURNAMENT_START_DATE_KEY, tournament.getStartDate());
        }
        objectBuilder.add(TOURNAMENT_MATCH_LENGTH_KEY, tournament.getMatchLength());

        JsonArrayBuilder teamsBuilder = Json.createArrayBuilder();
        for (Team team : tournament.getTeams()) {
            teamsBuilder.add(team.getName());
        }
        objectBuilder.add(TOURNAMENT_TEAMS_KEY, teamsBuilder.build());

        JsonArrayBuilder upcomingBuilder = Json.createArrayBuilder();
        if (tournament.getUpcomingMatches() != null && !tournament.getUpcomingMatches().isEmpty()) {
            for (Match match : tournament.getUpcomingMatches()) {
                upcomingBuilder.add(toJson(match));
            }
        }
        objectBuilder.add(TOURNAMENT_UPCOMING_MATCHES_KEY, upcomingBuilder.build());

        JsonArrayBuilder previousBuilder = Json.createArrayBuilder();
        if (tournament.getMatches() != null && !tournament.getMatches().isEmpty()) {
            for (Match match : tournament.getMatches()) {
                previousBuilder.add(toJson(match));
            }
        }
        objectBuilder.add(TOURNAMENT_PREVIOUS_MATCHES_KEY, previousBuilder.build());

        return objectBuilder.build();
    }
}
