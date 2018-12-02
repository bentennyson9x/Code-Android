package com.example.minhvufc.eshop.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by minhvufc on 12/10/2017.
 */

public class UserEntityManager {
    public ArrayList<UserEntity> lstUser = new ArrayList<>();

    public UserEntityManager(String data) throws JSONException {
        JSONArray rootJSON = new JSONArray(data);
        for (int i = 0; i < rootJSON.length(); i++) {
            JSONObject user = rootJSON.getJSONObject(i);
            int id = Integer.parseInt(user.getString("id"));
            String username = user.getString("username");
            String password = user.getString("password");
            String fullname = user.getString("fullname");
            int gender = Integer.parseInt(user.getString("gender"));
            String email = user.getString("email");

            UserEntity entity = new UserEntity(id, username, password, fullname, gender, email);
            lstUser.add(entity);
        }
    }
}
