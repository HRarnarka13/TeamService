package is.rufan.team.process;

import is.rufan.team.domain.Game;
import is.rufan.team.service.GameService;
import is.rufan.team.service.GameServiceException;
import is.ruframework.process.RuAbstractProcess;
import is.ruframework.reader.RuReadHandler;
import is.ruframework.reader.RuReader;
import is.ruframework.reader.RuReaderException;
import is.ruframework.reader.RuReaderFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.logging.Logger;

public class GameImportProcess extends RuAbstractProcess implements RuReadHandler
{
  private GameService gameService;
  MessageSource msg;
  Logger logger = Logger.getLogger(this.getClass().getName());

  @Override
  public void beforeProcess()
  {
    ApplicationContext applicationContext = new FileSystemXmlApplicationContext("classpath:gameapp.xml");
    gameService = (GameService) applicationContext.getBean("gameService");
    msg = (MessageSource)applicationContext.getBean("messageSource");
    logger.info("processbefore: " + getProcessContext().getProcessName());
  }

  @Override
  public void startProcess()
  {
    RuReaderFactory factory = new RuReaderFactory("gameprocess.xml");
    RuReader reader = factory.getReader("gameReader");

    reader.setReadHandler(this);
    try
    {
      reader.read();
    }
    catch (RuReaderException e)
    {
      String errorMsg = "Unable to read specified file.";
      logger.severe(errorMsg);
    }
  }

  public void read(int count, Object object)
  {
    Game game = (Game)object;
    System.out.println(game);
    try {
      gameService.addGame(game);
    } catch (GameServiceException gsex) {
      logger.warning("Game id " + game.getGameId() + " not added " + gsex.getMessage());
    }
  }
}
