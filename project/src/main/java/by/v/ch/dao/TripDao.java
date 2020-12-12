package by.v.ch.dao;

import by.v.ch.bean.Trip;

public interface TripDao {

    public Trip getTripById(int id);

    public Trip[] getTrips(int maxCount);

    public boolean updateTrip(Trip trip);


}
