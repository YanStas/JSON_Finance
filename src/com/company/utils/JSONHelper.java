/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author student
 */
public class JSONHelper {
   
   public static ArrayList<String> getKeys(JSONObject object){
      ArrayList<String> result = new ArrayList<>();
      
      Iterator<?> keys = object.keys();
      while (keys.hasNext()){
         result.add((String) keys.next());
      }
      return result;
   }

   public static HashMap<String, Object> createMapFromJSON(JSONObject jsonObject) {
      
         HashMap<String,Object> resultMap = new HashMap<>();
        try {
         resultMap.put(Const.TITLE_KEY, jsonObject.getString("title"));
         resultMap.put(Const.PHONE_KEY, jsonObject.getString("phone"));
         resultMap.put(Const.ADDRESS_KEY, jsonObject.getString("address"));
         resultMap.put(Const.CURRENCY_KEY, jsonObject
                 .getJSONObject("currencies")
                 .getJSONObject("USD")
                 .getString("bid"));
      } catch (JSONException ex) {
         Logger.getLogger(JSONHelper.class.getName()).log(Level.SEVERE, null, ex);
      }
      return resultMap; 
   }
}
