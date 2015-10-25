package is.rufan.team.service;

import is.rufan.team.domain.Team;

import java.util.List;

public interface TeamService
{
  public void addTeam(Team team) throws TeamServiceException;
  public List<Team> getTeams();
  public Team getTeamByAbbrivation(String abbrivation);
}
