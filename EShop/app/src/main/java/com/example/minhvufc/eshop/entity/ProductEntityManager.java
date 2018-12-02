package com.example.minhvufc.eshop.entity;

import com.example.minhvufc.eshop.BkapConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by minhvufc on 12/10/2017.
 */

public class ProductEntityManager {
    public ArrayList<ProductEntity> lstUser = new ArrayList<>();

    public ProductEntityManager(String data) throws JSONException {
        JSONArray rootJSON = new JSONArray(data);
        for (int i = 0; i < rootJSON.length(); i++) {
            JSONObject user = rootJSON.getJSONObject(i);
            int id = Integer.parseInt(user.getString("id"));
            int idcat = Integer.parseInt(user.getString("idcat"));
            String name = user.getString("name");
            float price = Float.parseFloat(user.getString("price"));

            String description = user.getString("description");
            String detail = user.getString("detail");
            String imagelink = BkapConstant.HOSTING + user.getString("imagelink");
            String inputdate = user.getString("inputdate");
            int status = Integer.parseInt(user.getString("status"));

            ProductEntity entity = new ProductEntity(id, idcat, name, price, description, detail, imagelink, inputdate, status);
            lstUser.add(entity);
        }
    }
}
