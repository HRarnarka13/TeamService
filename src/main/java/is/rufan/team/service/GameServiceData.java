package is.rufan.team.service;

import is.rufan.team.data.GameDataGateway;
import is.rufan.team.data.TeamDataGateway;
import is.rufan.team.data.VenueDataGateway;
import is.rufan.team.domain.Game;
import is.ruframework.data.RuDataAccessFactory;
import is.ruframework.domain.RuException;

import java.util.List;

public class GameServiceData implements GameService {
  RuDataAccessFactory factory;
  GameDataGateway gameDataGateway;
  TeamDataGateway teamDataGateway;
  VenueDataGateway venueDataGateway;

  public GameServiceData() throws RuException
  {
    factory = RuDataAccessFactory.getInstance("teamdata.xml");
    gameDataGateway = (GameDataGateway) factory.getDataAccess("gameData");
    teamDataGateway = (TeamDataGateway) factory.getDataAccess("teamData");
    venueDataGateway = (VenueDataGateway) factory.getDataAccess("venueData");
  }

  public void addGame(Game game) throws GameServiceException {
      gameDataGateway.addGame(game);
  }
}
