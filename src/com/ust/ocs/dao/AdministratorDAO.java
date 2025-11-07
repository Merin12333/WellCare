package com.ust.ocs.dao;

import java.util.ArrayList;

import com.ust.ocs.bean.DoctorBean;
import com.ust.ocs.service.Administrator;

public class AdministratorDAO implements Administrator {

    private static ArrayList<DoctorBean> doctorList = new ArrayList<>();

    @Override
    public String addDoctor(DoctorBean doctorBean) {
        try {
            doctorList.add(doctorBean);
            return "SUCCESS";

        } catch (Exception e) {
            return "FAIL";
        }
    }

    @Override
    public Boolean modifyDoctor(DoctorBean doctorBean) {

        for (int i = 0; i < doctorList.size(); i++) {
            if (doctorList.get(i).getDoctorID().equals(doctorBean.getDoctorID())) {
                doctorList.set(i, doctorBean);
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<DoctorBean> viewAllDoctors() {
        return doctorList;
    }

    @Override
    public int removeDoctor(String doctorID) {
        for (DoctorBean d : doctorList) {
            if (d.getDoctorID().equals(doctorID)) {
                doctorList.remove(d);
                return 1;
            }
        }
        return 0;
    }

    @Override
    public DoctorBean[] suggestDoctors(String patientId, String date) {
        return doctorList.toArray(new DoctorBean[0]);
    }
}
