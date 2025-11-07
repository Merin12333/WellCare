package com.ust.ocs.service;

import java.util.ArrayList;

import com.ust.ocs.bean.DoctorBean;

public interface Administrator {


	 public String addDoctor(DoctorBean doctorBean); // Returns "SUCCESS" or "FAIL"

	    public Boolean modifyDoctor(DoctorBean doctorBean);

	    ArrayList<DoctorBean> viewAllDoctors(); // Using array instead of ArrayList

	    public int removeDoctor(String doctorID);

	    public DoctorBean[] suggestDoctors(String patientId, String date); // Using String for date

    // Skipping viewPatientsByDate(Date appointmentDate) since it uses Map


}
