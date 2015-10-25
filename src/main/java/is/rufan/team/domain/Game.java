package is.rufan.team.domain;

import java.util.Date;

public class Game
{
  int gameId;
  Date startTime;
  Team teamHome;
  Team teamAway;
  Venue venue;

  public Game()
  {
  }

  public Game(int gameId, Date startTime, Team teamHome, Team teamAway, Venue venue)
  {
    this.gameId = gameId;
    this.startTime = startTime;
    this.teamHome = teamHome;
    this.teamAway = teamAway;
    this.venue = venue;
  }

  public int getGameId()
  {
    return gameId;
  }

  public void setGameId(int gameId)
  {
    this.gameId = gameId;
  }

  public Date getStartTime()
  {
    return startTime;
  }

  public void setStartTime(Date startTime)
  {
    this.startTime = startTime;
  }

  public Team getTeamHome()
  {
    return teamHome;
  }

  public void setTeamHome(Team teamHome)
  {
    this.teamHome = teamHome;
  }

  public Team getTeamAway()
  {
    return teamAway;
  }

  public void setTeamAway(Team teamAway)
  {
    this.teamAway = teamAway;
  }

  public Venue getVenue()
  {
    return venue;
  }

  public void setVenue(Venue venue)
  {
    this.venue = venue;
  }

  @Override
  public String toString()
  {
    return "Game (" + getGameId() + ") " + getTeamHome().getTeamId() + " - " + getTeamAway().getTeamId() + " at " + getStartTime();
  }
}
