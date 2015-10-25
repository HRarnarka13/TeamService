package is.rufan.team.data;

import is.rufan.team.domain.Team;
import is.ruframework.data.RuData;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamData extends RuData implements TeamDataGateway
{
  public void addTeam(Team team)
  {
    SimpleJdbcInsert insertTeam =
        new SimpleJdbcInsert(getDataSource())
            .withTableName("teams");

    Map<String, Object> teamParameters = new HashMap<String, Object>(5);
    teamParameters.put("teamid", team.getTeamId());
    teamParameters.put("location", team.getLocation());
    teamParameters.put("abbreviation", team.getAbbreviation());
    teamParameters.put("displayname", team.getDisplayName());
    teamParameters.put("venueid", team.getVenue().getVenueId());

    try
    {
      insertTeam.execute(teamParameters);
    }
    catch (DataIntegrityViolationException divex)
    {
      log.warning("Duplicate entry");
    }
  }

  public Team getTeam(int teamId)
  {
    String sql = "select * from teams where id = ?";
    JdbcTemplate queryTeam= new JdbcTemplate(getDataSource());
    Team team = queryTeam.queryForObject(sql, new Object[] { teamId },
        new TeamRowMapper());
    return team;
  }

  public Team getTeamByAbbreviation(String abbreviation)
  {
    String sql = "select * from teams where abbreviation = ?";
    JdbcTemplate queryTeam= new JdbcTemplate(getDataSource());

    try
    {
      Team team = queryTeam.queryForObject(sql, new Object[]{abbreviation},
          new TeamRowMapper());
      return team;
    }
    catch(EmptyResultDataAccessException erdax)
    {
      return null;
    }
  }

  public List<Team> getTeams()
  {
    String sql = "select * from teams";
    JdbcTemplate queryPosition= new JdbcTemplate(getDataSource());

    List<Team> teams = queryPosition.query(sql,
        new TeamRowMapper());

    return teams;
  }
}
