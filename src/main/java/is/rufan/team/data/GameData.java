package is.rufan.team.data;

import is.rufan.team.domain.Game;
import is.rufan.team.service.GameServiceException;
import is.ruframework.data.RuData;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by arnarkari on 24/10/15.
 *
 * @author arnarkari
 */
public class GameData extends RuData implements GameDataGateway {

    public void addGame(Game game) throws GameServiceException {
        SimpleJdbcInsert insertGame =
                new SimpleJdbcInsert(getDataSource())
                .withTableName("games");
        Map<String, Object> gameParameters = new HashMap<String, Object>(5);
        gameParameters.put("gameid", game.getGameId());
        gameParameters.put("startTime", game.getStartTime());
        gameParameters.put("teamHomeid", game.getTeamHome().getTeamId());
        gameParameters.put("teamAwayid", game.getTeamAway().getTeamId());
        gameParameters.put("venueid", game.getVenue().getVenueId());

        try {
            insertGame.execute(gameParameters);
        } catch (DataIntegrityViolationException divex) {
            log.warning("Duplicate entry");
            throw new GameServiceException(divex.getMessage(), divex);
        }
    }

    public Game getGame(int gameId) {

        String sql = "select * from games where id = ?";
        JdbcTemplate queryTeam = new JdbcTemplate(getDataSource());
        Game game = queryTeam.queryForObject(sql, new Object[] { gameId },
                new GameRowMapper());
        return game;
    }
}
