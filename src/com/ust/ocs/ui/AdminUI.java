package com.ust.ocs.ui;

import javax.swing.JOptionPane;

import com.ust.ocs.bean.CredentialsBean;
import com.ust.ocs.bean.DoctorBean;
import com.ust.ocs.dao.AdministratorDAO;
import com.ust.ocs.dao.CredentialsDAO;

public class AdminUI {

    public static void main(String[] args) {
    	JOptionPane.showMessageDialog(null, "Welcome to WELLCARE \n Your Health, Our Priority!\"");


        AdministratorDAO adminDao = new AdministratorDAO();

        CredentialsDAO credDao = new CredentialsDAO();

        CredentialsBean admin = new CredentialsBean();
        admin.setUserID("ADMIN001");
        admin.setPassword("ADMIN001");
        admin.setUserType("A");
        admin.setLoginStatus(0);
        credDao.addUser(admin);

        String user = JOptionPane.showInputDialog("Enter Username:");
        String pass = JOptionPane.showInputDialog("Enter Password:");

        CredentialsBean logged = credDao.validate(user, pass);

        if (logged == null) {
            JOptionPane.showMessageDialog(null, "Invalid Login!");
            return;
        }

        if (!logged.getUserType().equals("A")) {
            JOptionPane.showMessageDialog(null, "Access Denied! Only Admin Can Login.");
            return;
        }

        if (logged.getLoginStatus() == 1) {
            JOptionPane.showMessageDialog(null, "Login Successful!");
        }


            while (true) {
                String option = JOptionPane.showInputDialog(
                        "----- ADMIN MENU -----\n" +
                        "AD001. Add Doctor\n" +
                        "AD002. View All Doctors\n" +
                        "AD003. Modify Doctor\n" +
                        "AD004. Delete Doctor\n" +
                        "AD005. Exit\n\n" +
                        "Enter your choice:");

                if (option == null) break;

                switch (option) {
                    case "AD001": addDoctorUI(adminDao); break;
                    case "AD002": viewAllDoctorsUI(adminDao); break;
                    case "AD003": modifyDoctorUI(adminDao); break;
                    case "AD004": deleteDoctorUI(adminDao); break;
                    case "AD005": System.exit(0);
                    default: JOptionPane.showMessageDialog(null, "Invalid Option!");
                }
            }

        } 
    

    // ADD DOCTOR
    public static void addDoctorUI(AdministratorDAO admin) {

        String id = JOptionPane.showInputDialog("Enter Doctor ID:");
        String name = JOptionPane.showInputDialog("Enter Doctor Name:");
        String dob = JOptionPane.showInputDialog("Enter Date of Birth:");
        String doj = JOptionPane.showInputDialog("Enter Date of Joining:");
        String gender = JOptionPane.showInputDialog("Enter Gender:");
        String qualification = JOptionPane.showInputDialog("Enter Qualification:");
        String specialization = JOptionPane.showInputDialog("Enter Specialization:");
        int exp = Integer.parseInt(JOptionPane.showInputDialog("Enter Years of Experience:"));
        String street = JOptionPane.showInputDialog("Enter Street:");
        String location = JOptionPane.showInputDialog("Enter Location:");
        String city = JOptionPane.showInputDialog("Enter City:");
        String state = JOptionPane.showInputDialog("Enter State:");
        String pincode = JOptionPane.showInputDialog("Enter Pincode:");
        String contact = JOptionPane.showInputDialog("Enter Contact Number:");
        String email = JOptionPane.showInputDialog("Enter Email ID:");

        DoctorBean doctor = new DoctorBean(
                id, name, dob, doj, gender, qualification,
                specialization, exp, street, location, city,
                state, pincode, contact, email
        );

        if (admin.addDoctor(doctor).equals("SUCCESS"))
            JOptionPane.showMessageDialog(null, "Doctor Added Successfully!");
        else
            JOptionPane.showMessageDialog(null, "Failed to Add Doctor!");
    }

    // VIEW ALL DOCTORS
    public static void viewAllDoctorsUI(AdministratorDAO adminDao) {

        var list = adminDao.viewAllDoctors();
        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No Doctors Available!");
            return;
        }

        StringBuilder sb = new StringBuilder("---- Doctor List ----\n\n");

        for (DoctorBean d : list) {
            sb.append("ID: ").append(d.getDoctorID()).append("\n");
            sb.append("Name: ").append(d.getDoctorName()).append("\n");
            sb.append("Specialization: ").append(d.getSpecialization()).append("\n");
            sb.append("Experience: ").append(d.getYearsOfExperience()).append(" years\n\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // MODIFY DOCTOR
    public static void modifyDoctorUI(AdministratorDAO adminDao) {

        String id = JOptionPane.showInputDialog("Enter Doctor ID to Modify:");

        var list = adminDao.viewAllDoctors();
        DoctorBean old = null;

        for (DoctorBean d : list) {
            if (d.getDoctorID().equals(id)) {
                old = d;
                break;
            }
        }

        if (old == null) {
            JOptionPane.showMessageDialog(null, "Doctor Not Found!");
            return;
        }

        // Modify selected details
        old.setDoctorName(JOptionPane.showInputDialog("Enter Name:", old.getDoctorName()));
        old.setSpecialization(JOptionPane.showInputDialog("Enter Specialization:", old.getSpecialization()));
        old.setYearsOfExperience(Integer.parseInt(
                JOptionPane.showInputDialog("Enter Experience:", old.getYearsOfExperience())
        ));

        if (adminDao.modifyDoctor(old))
            JOptionPane.showMessageDialog(null, "Doctor Updated Successfully!");
        else
            JOptionPane.showMessageDialog(null, "Update Failed!");
    }

    // DELETE DOCTOR
    public static void deleteDoctorUI(AdministratorDAO adminDao) {

        String id = JOptionPane.showInputDialog("Enter Doctor ID to Delete:");

        if (adminDao.removeDoctor(id) == 1)
            JOptionPane.showMessageDialog(null, "Doctor Deleted Successfully!");
        else
            JOptionPane.showMessageDialog(null, "Doctor Not Found!");
    }
}
