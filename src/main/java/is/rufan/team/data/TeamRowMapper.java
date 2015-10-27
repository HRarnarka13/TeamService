package is.rufan.team.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import is.rufan.team.domain.Team;
import is.rufan.team.service.TeamService;
import is.ruframework.data.RuDataAccessFactory;
import is.ruframework.domain.RuException;
import org.springframework.jdbc.core.RowMapper;

public class TeamRowMapper implements RowMapper<Team>
{

    RuDataAccessFactory factory;
    VenueDataGateway venueDataGateway;

    public TeamRowMapper() throws RuException {
        factory = RuDataAccessFactory.getInstance("teamdata.xml");
        venueDataGateway = (VenueDataGateway) factory.getDataAccess("venueData");
    }

    public Team mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        Team team = new Team();
        team.setTeamId(rs.getInt("teamid"));
        team.setLocation(rs.getString("location"));
        team.setAbbreviation(rs.getString("abbreviation"));
        team.setDisplayName(rs.getString("location"));
        team.setVenue(venueDataGateway.getVenue(rs.getInt("venueid")));
        return team;
    }
}