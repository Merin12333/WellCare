package com.ust.ocs.util;

import com.ust.ocs.bean.DoctorBean;
import com.ust.ocs.dao.AdministratorDAO;

public class DoctorData {

    public static void loadSampleDoctors(AdministratorDAO adminDao) {

        DoctorBean d1 = new DoctorBean(
                "DOC101", "Dr. Priya", "12-05-1985", "10-01-2010",
                "Female", "MBBS", "Cardiology", 12,
                "12/45 Anna Street", "Nungambakkam", "Chennai",
                "TN", "600034", "9876543210", "priya@wellcare.com"
        );

        DoctorBean d2 = new DoctorBean(
                "DOC102", "Dr. Arjun", "22-11-1980", "15-03-2008",
                "Male", "MD", "Dermatology", 15,
                "89/2 MG Road", "Indiranagar", "Bangalore",
                "KA", "560038", "9988776655", "arjun@wellcare.com"
        );

        adminDao.addDoctor(d1);
        adminDao.addDoctor(d2);
    }
}
