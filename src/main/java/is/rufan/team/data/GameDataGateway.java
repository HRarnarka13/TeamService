package is.rufan.team.data;

import is.rufan.team.domain.Game;
import is.rufan.team.service.GameServiceException;

/**
 * Created by arnarkari on 24/10/15.
 *
 * @author arnarkari
 */
public interface GameDataGateway {

    public void addGame(Game game) throws GameServiceException;
    public Game getGame(int gameid);
}
