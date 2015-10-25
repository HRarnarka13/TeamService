package is.rufan.team.data;

import is.rufan.team.domain.Game;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by arnarkari on 24/10/15.
 *
 * @author arnarkari
 */
public class GameRowMapper implements RowMapper<Game> {

    public Game mapRow(ResultSet rs, int rowNum) throws SQLException {

        Game game = new Game();
        game.setGameId(rs.getInt("gameid"));
        game.setStartTime(rs.getTime("startTime"));
        return game;
    }
}
