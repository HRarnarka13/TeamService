package is.rufan.team.service;

import is.rufan.team.data.TeamDataGateway;
import is.rufan.team.data.VenueDataGateway;
import is.rufan.team.domain.Team;
import is.ruframework.data.RuDataAccessFactory;
import is.ruframework.domain.RuException;

import java.util.List;

public class TeamServiceData implements TeamService
{
  RuDataAccessFactory factory;
  TeamDataGateway teamDataGateway;
  VenueDataGateway venueDataGateway;


  public TeamServiceData() throws RuException
  {
    factory = RuDataAccessFactory.getInstance("teamdata.xml");
    teamDataGateway = (TeamDataGateway) factory.getDataAccess("teamData");
    venueDataGateway = (VenueDataGateway) factory.getDataAccess("venueData");

  }

  public void addTeam(Team team) throws TeamServiceException
  {
    teamDataGateway.addTeam(team);
    if(venueDataGateway.getVenue(team.getVenue().getVenueId()) == null)
    {
      venueDataGateway.addVenue(team.getVenue());
    }
  }

  public List<Team> getTeams()
  {
    return teamDataGateway.getTeams();
  }

  public Team getTeamByAbbrivation(String abbrivation)
  {
    return teamDataGateway.getTeamByAbbreviation(abbrivation);
  }
}
