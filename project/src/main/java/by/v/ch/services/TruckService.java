package by.v.ch.services;

import by.v.ch.bean.CarPurpose;

public interface TruckService {
    //todo: change to get list of purposes;
    //todo add cache
    public CarPurpose getCarPurposeById(int id);
}
