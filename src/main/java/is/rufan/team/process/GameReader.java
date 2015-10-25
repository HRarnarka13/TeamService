package is.rufan.team.process;

import is.rufan.team.domain.Game;
import is.rufan.team.domain.Team;
import is.rufan.team.domain.Venue;
import is.ruframework.reader.RuAbstractReader;

import is.ruframework.reader.RuJsonUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GameReader extends RuAbstractReader
{
  public Object parse(String content)
  {
    String tmp;
    List<Game> games = new ArrayList<Game>();

    String fake="[{\"EventId\":1546789,\"StartTime\":\"2015-08-14T18:45:00\",\"Home\":{\"Id\":6144,\"Name\":\"Aston Villa\"},\"Away\":{\"Id\":6157,\"Name\":\"Manchester United\"},\"Venue\":{\"Id\":5194,\"Name\":\"Villa Park\"}},\n" +
        "{\"EventId\":1546807,\"StartTime\":\"2015-08-15T11:45:00\",\"Home\":{\"Id\":7128,\"Name\":\"Southampton\"},\"Away\":{\"Id\":6149,\"Name\":\"Everton\"},\"Venue\":{\"Id\":5246,\"Name\":\"St. Mary's Stadium\"}},{\"EventId\":1546811,\"StartTime\":\"2015-08-15T14:00:00\",\"Home\":{\"Id\":7129,\"Name\":\"Sunderland\"},\"Away\":{\"Id\":7132,\"Name\":\"Norwich City\"},\"Venue\":{\"Id\":5210,\"Name\":\"Stadium Of Light\"}},{\"EventId\":1546815,\"StartTime\":\"2015-08-15T14:00:00\",\"Home\":{\"Id\":8248,\"Name\":\"Swansea City\"},\"Away\":{\"Id\":6151,\"Name\":\"Newcastle United\"},\"Venue\":{\"Id\":6132,\"Name\":\"Liberty Stadium\"}},{\"EventId\":1546818,\"StartTime\":\"2015-08-15T14:00:00\",\"Home\":{\"Id\":6154,\"Name\":\"Tottenham Hotspur\"},\"Away\":{\"Id\":8246,\"Name\":\"Stoke City\"},\"Venue\":{\"Id\":5211,\"Name\":\"White Hart Lane\"}},{\"EventId\":1546821,\"StartTime\":\"2015-08-15T14:00:00\",\"Home\":{\"Id\":6148,\"Name\":\"Watford\"},\"Away\":{\"Id\":7139,\"Name\":\"West Bromwich Albion\"},\"Venue\":{\"Id\":5569,\"Name\":\"Vicarage Road\"}},{\"EventId\":1546825,\"StartTime\":\"2015-08-15T14:00:00\",\"Home\":{\"Id\":6143,\"Name\":\"West Ham United\"},\"Away\":{\"Id\":7127,\"Name\":\"Leicester City\"},\"Venue\":{\"Id\":5212,\"Name\":\"Upton Park\"}},{\"EventId\":1546791,\"StartTime\":\"2015-08-16T12:30:00\",\"Home\":{\"Id\":7131,\"Name\":\"Crystal Palace\"},\"Away\":{\"Id\":6145,\"Name\":\"Arsenal\"},\"Venue\":{\"Id\":6122,\"Name\":\"Selhurst Park\"}},{\"EventId\":1546804,\"StartTime\":\"2015-08-16T15:00:00\",\"Home\":{\"Id\":6158,\"Name\":\"Manchester City\"},\"Away\":{\"Id\":6159,\"Name\":\"Chelsea\"},\"Venue\":{\"Id\":5435,\"Name\":\"Etihad Stadium\"}},{\"EventId\":1546793,\"StartTime\":\"2015-08-17T19:00:00\",\"Home\":{\"Id\":6140,\"Name\":\"Liverpool\"},\"Away\":{\"Id\":8260,\"Name\":\"Bournemouth\"},\"Venue\":{\"Id\":5204,\"Name\":\"Anfield\"}}]";

    // Root object
    JSONArray jGames = (JSONArray)JSONValue.parse(fake);

    for (int i=0; i < jGames.size(); i++)
    {
      JSONObject jGame = (JSONObject)jGames.get(i);

      Game game = new Game();

      // Event id
      game.setGameId(RuJsonUtil.getInt(jGame, "EventId"));
      game.setStartTime(convertDate((String) jGame.get("StartTime")));

      // Home team
      JSONObject jHomeTeam = (JSONObject)jGame.get("Home");
      Team teamHome = new Team();
      teamHome.setTeamId(RuJsonUtil.getInt(jHomeTeam, "Id"));
      game.setTeamHome(teamHome);

      // Away team
      JSONObject jAwayTeam = (JSONObject)jGame.get("Away");
      Team teamAway = new Team();
      teamAway.setTeamId(RuJsonUtil.getInt(jAwayTeam, "Id"));
      game.setTeamAway(teamAway);

      // Venue
      JSONObject jVenue = (JSONObject)jGame.get("Venue");
      Venue venue = new Venue();
      venue.setVenueId(RuJsonUtil.getInt(jVenue, "Id"));
      game.setVenue(venue);

      games.add(game);
      readHandler.read(i, game);
    }


    return games;
  }

  // 2015-08-21T16:19:30.3373863Z
  private  Date convertDate(String strDate)
  {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd\'T\'HH:mm:ss", Locale.ENGLISH);
    Date date = null;

    try
    {
      date = format.parse(strDate);
    }
    catch (ParseException var4)
    {
      System.out.println("FAIL");
    }
    return date;
  }
}
