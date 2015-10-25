package is.rufan.team.data;

import is.rufan.team.domain.Venue;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class VenueRowMapper implements RowMapper<Venue>
{
  public Venue mapRow(ResultSet rs, int rowNum) throws SQLException
  {
    Venue venue = new Venue();
    venue.setVenueId(rs.getInt("venueid"));
    venue.setName(rs.getString("name"));
    venue.setCity(rs.getString("city"));

    return venue;
  }
}
