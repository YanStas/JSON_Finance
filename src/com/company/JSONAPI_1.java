/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;
import com.company.utils.JSONHelper;
import com.company.utils.NetworkUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author student
 */
public class JSONAPI_1 {

   
   
    public static void main(String[] args) {
        String address = "http://resources.finance.ua/ua/public/currency-cash.json";
        
        String res = NetworkUtils.get(address);
        
        System.out.println("result-is - "+res);
        
        HashMap<String,Object> resultMap = new HashMap<>();
        
       try {
          JSONObject object = new JSONObject(res);
          ArrayList<String> keys = JSONHelper.getKeys(object);
          System.out.println("keys - " + keys);
          JSONObject currencyObject = object.getJSONObject(keys.get(6));
          
          System.out.println("currency keys - "+JSONHelper.getKeys(currencyObject));
          
          JSONArray organizations = object.getJSONArray(keys.get(5));
          
          System.out.println(organizations.get(0));
          
          double minCurrency = Double.parseDouble(((JSONObject)  organizations.get(0))
                    .getJSONObject("currencies")
                    .getJSONObject("USD")
                    .getString("bid"));
          
          resultMap = JSONHelper.createMapFromJSON((JSONObject) organizations.get(0));
          
          
          
          System.out.println("start min " + minCurrency);
          
          for (int i = 0; i<organizations.length(); i++){
             JSONObject org = (JSONObject) organizations.get(i);
             try{
             System.out.println(org.getString("title"));
             
             JSONObject curr = org.getJSONObject("currencies");
             System.out.println("EUR - "+curr.getString("EUR"));
             System.out.println("USD - "+curr.getString("USD"));
             System.out.println("-----------------------------------------------------------------------");

             
             String bidUSDCurrency = curr.getJSONObject("USD").getString("bid");
             if(Double.parseDouble(bidUSDCurrency)<minCurrency){
                 resultMap = JSONHelper.createMapFromJSON(org);
                minCurrency = Double.parseDouble(bidUSDCurrency);
             }  
             }
             catch(JSONException e){
                System.out.println(org.getString("title")+"error!!!");
             }
          }
             
          System.out.println("min currency - " +resultMap.toString());
          
       } catch (JSONException ex) {
          Logger.getLogger(JSONAPI_1.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
}
