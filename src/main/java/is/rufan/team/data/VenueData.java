package is.rufan.team.data;

import is.rufan.team.domain.Venue;
import is.ruframework.data.RuData;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.Map;

public class VenueData extends RuData implements VenueDataGateway
{
  public void addVenue(Venue venue)
  {
    SimpleJdbcInsert insertVenue =
        new SimpleJdbcInsert(getDataSource())
            .withTableName("venues");

    Map<String, Object> venueParameters = new HashMap<String, Object>(3);
    venueParameters.put("venueid", venue.getVenueId());
    venueParameters.put("name", venue.getName());
    venueParameters.put("city", venue.getCity());

    try
    {
      insertVenue.execute(venueParameters);
    }
    catch (DataIntegrityViolationException divex)
    {
      log.warning("Duplicate entry");
    }
  }

  public Venue getVenue(int venueId)
  {
    String sql = "select * from venues where venueid = ?";
    JdbcTemplate queryVenue= new JdbcTemplate(getDataSource());

    try
    {
      Venue venue = queryVenue.queryForObject(sql, new Object[]{venueId},
          new VenueRowMapper());
      return venue;
    }
    catch(EmptyResultDataAccessException erdax)
    {
      return null;
    }
  }



}
