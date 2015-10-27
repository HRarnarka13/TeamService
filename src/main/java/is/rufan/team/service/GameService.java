package is.rufan.team.service;

import is.rufan.team.domain.Game;

import java.util.List;

public interface GameService
{
  public void addGame(Game game) throws GameServiceException;
  public Game getGame(int gameid);
  public List<Game> getGames();
}
