package by.v.ch.services.impl;

import by.v.ch.bean.CarPurpose;
import by.v.ch.dao.PurposeDao;
import by.v.ch.dao.factory.DaoFactory;
import by.v.ch.services.TruckService;

public class TruckServiceImpl implements TruckService {
    @Override
    public CarPurpose getCarPurposeById(int id) {
        DaoFactory daoFactory=DaoFactory.getInstance();
        PurposeDao purposeDao = daoFactory.getPurposeDao();

        return purposeDao.getById(id);

    }

    @Override
    public CarPurpose[] getAllCarPurposes() {
        DaoFactory daoFactory=DaoFactory.getInstance();
        PurposeDao purposeDao = daoFactory.getPurposeDao();
        return purposeDao.getAllPurposes();
    }


}
