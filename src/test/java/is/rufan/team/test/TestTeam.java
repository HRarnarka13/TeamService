package is.rufan.team.test;



import is.rufan.team.domain.Team;
import is.rufan.team.service.TeamService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:teamapptest.xml")

public class TestTeam extends TestCase
{
  Logger log = Logger.getLogger(TestTeam.class.getName());

  @Autowired
  private TeamService teamService;

  @Before
  public void Setup()
  {
  }

  @Test
  public void testAbbreviation()
  {
    String abbreviation = "ARS";

    Team arsenal = teamService.getTeamByAbbrivation(abbreviation);
    assertEquals(arsenal.getDisplayName(), "Arsenal");

    Team doesNotExist = teamService.getTeamByAbbrivation("WTF");
    assertNull(doesNotExist);
  }

  @Test
  public void testTeams()
  {
    List<Team> teams = teamService.getTeams();
    assertEquals(teams.size(), 20);
  }


}
