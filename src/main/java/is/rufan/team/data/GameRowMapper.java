package is.rufan.team.data;

import is.rufan.team.domain.Game;
import is.ruframework.data.RuDataAccessFactory;
import is.ruframework.domain.RuException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by arnarkari on 24/10/15.
 *
 * @author arnarkari
 */
public class GameRowMapper implements RowMapper<Game> {

    RuDataAccessFactory factory;
    TeamDataGateway teamDataGateway;
    VenueDataGateway venueDataGateway;

    public GameRowMapper() throws RuException {
        factory = RuDataAccessFactory.getInstance("teamdata.xml");
        teamDataGateway = (TeamDataGateway) factory.getDataAccess("teamData");
        venueDataGateway = (VenueDataGateway) factory.getDataAccess("venueData");
    }

    public Game mapRow(ResultSet rs, int rowNum) throws SQLException {

        Game game = new Game();
        game.setGameId(rs.getInt("gameid"));
        game.setStartTime(rs.getTimestamp("startTime"));
        game.setTeamHome(teamDataGateway.getTeam(rs.getInt("teamHomeid")));
        game.setTeamAway(teamDataGateway.getTeam(rs.getInt("teamAwayid")));
        game.setVenue(venueDataGateway.getVenue(rs.getInt("venueid")));
        return game;
    }
}
