package is.rufan.team.data;

import is.rufan.team.domain.Venue;

public interface VenueDataGateway
{
  public void addVenue(Venue venue);
  public Venue getVenue(int venueId);
}
