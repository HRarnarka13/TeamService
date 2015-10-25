package is.rufan.team.service;

import is.rufan.team.domain.Game;

public interface GameService
{
  public void addGame(Game game) throws GameServiceException;
}
