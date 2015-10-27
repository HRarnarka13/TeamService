package is.rufan.team.data;

import is.rufan.team.domain.Game;
import is.rufan.team.service.GameServiceException;
import is.ruframework.data.RuData;
import is.ruframework.domain.RuException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.List;
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

        String sql = "select * from games where gameid = ?";
        JdbcTemplate queryTeam = new JdbcTemplate(getDataSource());
        Game game = null;
        try {
            game = queryTeam.queryForObject(sql, new Object[] { gameId }, new GameRowMapper());
        } catch (RuException ex) {
            return null;
        }
        return game;
    }

    public List<Game> getGames() {
        String sql = "select * from games";
        JdbcTemplate queryGames= new JdbcTemplate(getDataSource());
        try {
            List<Game> games = queryGames.query(sql,
                new GameRowMapper());
            return games;
        } catch (RuException ex) {
            return null;
        }
    }
}
