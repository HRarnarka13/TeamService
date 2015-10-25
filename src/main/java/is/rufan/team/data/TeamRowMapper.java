package is.rufan.team.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import is.rufan.team.domain.Team;
import org.springframework.jdbc.core.RowMapper;

public class TeamRowMapper implements RowMapper<Team>
{
  public Team mapRow(ResultSet rs, int rowNum) throws SQLException
  {
    Team team = new Team();
    team.setTeamId(rs.getInt("teamid"));
    team.setLocation(rs.getString("location"));
    team.setAbbreviation(rs.getString("abbreviation"));
    team.setDisplayName(rs.getString("location"));
    return team;
  }
}