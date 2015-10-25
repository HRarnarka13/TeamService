package is.rufan.team.process;

import is.rufan.team.domain.Team;
import is.rufan.team.domain.Venue;
import is.ruframework.reader.RuAbstractReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.ArrayList;
import java.util.List;

public class TeamReader extends RuAbstractReader
{
  public Object parse(String content)
  {
    String tmp;

    // Root object
    JSONObject jsonObject = (JSONObject) JSONValue.parse(content);

    // Get apiResults, this is an array so get the first (and only) item
    JSONArray apiResults = (JSONArray)jsonObject.get("apiResults");
    JSONObject jtmp = (JSONObject)apiResults.get(0);

    List<Team> teams = new ArrayList<Team>();

    // League
    JSONObject jleague = (JSONObject)jtmp.get("league");

    // Season
    JSONObject jseason = (JSONObject)jleague.get("season");

    // Seasons have conferenes, which is array, so get first item
    JSONArray conferances = (JSONArray)jseason.get("conferences");
    jtmp = (JSONObject)conferances.get(0);

    // Conferences have divisions, which is array, so get first item
    JSONArray divisions = (JSONArray)jtmp.get("divisions");
    jtmp= (JSONObject)divisions.get(0);

    // Teams
    JSONArray jTeams = (JSONArray)jtmp.get("teams");

    // Iterate through the array, and display teams and venue
    JSONObject jteam, jvenue;

    Team team;
    Venue venue;
    for (int i=0; i < jTeams.size(); i++)
    {
      team = new Team();
      jteam = (JSONObject)jTeams.get(i);
      team.setTeamId(((Long) jteam.get("teamId")).intValue());
      team.setLocation((String) jteam.get("location"));
      team.setAbbreviation((String) jteam.get("abbreviation"));
      team.setDisplayName((String) jteam.get("displayName"));

      venue = new Venue();
      jvenue = (JSONObject)jteam.get("venue");
      venue.setVenueId(((Long) jvenue.get("venueId")).intValue());
      venue.setName(((String) jvenue.get("name")));
      venue.setCity(((String) jvenue.get("city")));

      team.setVenue(venue);
      teams.add(team);

      readHandler.read(i, team);
    }

    return teams;
  }
}
