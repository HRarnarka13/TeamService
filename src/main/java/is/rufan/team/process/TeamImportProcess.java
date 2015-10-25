package is.rufan.team.process;

import is.rufan.team.domain.Team;
import is.rufan.team.service.TeamService;
import is.rufan.team.service.TeamServiceException;
import is.ruframework.process.RuAbstractProcess;
import is.ruframework.reader.RuReadHandler;
import is.ruframework.reader.RuReader;
import is.ruframework.reader.RuReaderException;
import is.ruframework.reader.RuReaderFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.logging.Logger;

public class TeamImportProcess extends RuAbstractProcess implements RuReadHandler
{
  private TeamService teamService;
  MessageSource msg;
  Logger logger = Logger.getLogger(this.getClass().getName());

  @Override
  public void beforeProcess()
  {
    ApplicationContext applicationContext = new FileSystemXmlApplicationContext("classpath:teamapp.xml");
    teamService = (TeamService)applicationContext.getBean("teamService");
    msg = (MessageSource)applicationContext.getBean("messageSource");
    logger.info("processbefore: " + getProcessContext().getProcessName());
  }

  @Override
  public void startProcess()
  {
    RuReaderFactory factory = new RuReaderFactory("teamprocess.xml");
    RuReader reader = factory.getReader("teamReader");

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
    Team team = (Team)object;
    try
    {
      teamService.addTeam(team);
    }
    catch(TeamServiceException se)
    {
      logger.warning("Team id " + team.getTeamId() + " not added " + se.getMessage());
    }
  }
}
