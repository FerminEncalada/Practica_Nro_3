package controller.dao.services;

import controller.TDA.list.LinkedList;
import models.Hospital;
import controller.dao.HospitalDao;

public class HospitalServices {
    private HospitalDao obj;

    public HospitalServices() {
        obj = new HospitalDao();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public LinkedList<Hospital> listAll() {
        return obj.getListAll();
    }

    public Hospital getHospital() {
        return obj.getHospital();
    }

    public void setHospital(Hospital hospital) {
        obj.setHospital(hospital);
    }

    public Hospital get(Integer id) throws Exception {
        return obj.get(id);
    }

    public Boolean delete(Integer id) throws Exception {
        return obj.delete(id);
    }

   
}