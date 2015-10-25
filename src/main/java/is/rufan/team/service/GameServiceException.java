package is.rufan.team.service;

public class GameServiceException extends RuntimeException
{
  public GameServiceException()
  {
  }

  public GameServiceException(String message)
  {
    super(message);
  }

  public GameServiceException(String message, Throwable cause)
  {
    super(message, cause);
  }
}
