package com.ust.ocs.dao;

import java.util.ArrayList;
import com.ust.ocs.bean.CredentialsBean;

public class CredentialsDAO {

    private static ArrayList<CredentialsBean> users = new ArrayList<>();

    // ✅ Add a user
    public void addUser(CredentialsBean cb) {
        users.add(cb);
    }

    // ✅ Check login and return CredentialsBean
    public CredentialsBean validate(String userID, String password) {

        for (CredentialsBean cb : users) {
            if (cb.getUserID().equals(userID) && cb.getPassword().equals(password)) {

                cb.setLoginStatus(1);  // ✅ login success
                return cb;
            }
        }
        return null;  // login failed
    }
}
