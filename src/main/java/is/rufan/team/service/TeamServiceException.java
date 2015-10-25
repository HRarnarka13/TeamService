package is.rufan.team.service;

public class TeamServiceException extends RuntimeException
{
  public TeamServiceException()
  {
  }

  public TeamServiceException(String message)
  {
    super(message);
  }

  public TeamServiceException(String message, Throwable cause)
  {
    super(message, cause);
  }
}
