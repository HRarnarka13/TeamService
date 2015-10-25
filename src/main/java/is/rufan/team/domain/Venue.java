package is.rufan.team.domain;

public class Venue
{
  private int venueId;
  private String name;
  private String city;

  public Venue()
  {
  }

  public Venue(int venueId, String name, String city)
  {
    this.venueId = venueId;
    this.name = name;
    this.city = city;
  }

  public int getVenueId()
  {
    return venueId;
  }

  public void setVenueId(int venueId)
  {
    this.venueId = venueId;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getCity()
  {
    return city;
  }

  public void setCity(String city)
  {
    this.city = city;
  }

}
