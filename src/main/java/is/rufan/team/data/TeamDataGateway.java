package is.rufan.team.data;

import is.rufan.team.domain.Team;

import java.util.List;

public interface TeamDataGateway
{
  public void addTeam(Team team);
  public Team getTeam(int teamId);
  public List<Team> getTeams();
  public Team getTeamByAbbreviation(String abbreviation);
}
