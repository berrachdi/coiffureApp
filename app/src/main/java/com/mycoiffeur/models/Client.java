package com.mycoiffeur.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.logging.Logger;
import com.mycoiffeur.interfaces.LoginScreenActivity;
import com.mycoiffeur.logger.LoggerMsg;
import com.mycoiffeur.services.authentification.Authentification;
import com.mycoiffeur.api.APIurls;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Client extends User implements Authentification {

    // For local user login data storage
    private SharedPreferences sharedPreferences;

    public Client(String nom, String prenom, String email, String passwordHash, String userType) {
        super(nom, prenom, email, passwordHash, userType);
    }

    @Override
    public Boolean signIn(String email, String passwordHash, Context context) {


        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, APIurls.URL_LOGIN, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        if(response == null){

                        }else{

                            // Success login > returned the TOKEN AND USER-ID or USER-EMAIL
                            // SAVE USER DATA IN LOCAL STORAGE
                            SharedPreferences sharedPreferences= context.getSharedPreferences("USER_LOGIN_DATA",Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            try {

                                editor.putString("USEREMAIL", response.get("userId").toString());
                                editor.putString("USERTOKEN", response.get("userToken").toString());
                                editor.putString("USERTYPE", response.get("usertype").toString());
                                editor.apply();

                            } catch (JSONException e) {
                                Log.d("SharedPreferences>Signin(): ",e.getMessage());
                            }


                            LoggerMsg.status = true;
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(LoggerMsg.INTERNET_ERROR,error.getMessage());
                    }


                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> params = new HashMap<String,String>();
                        params.put("email",email);
                        params.put("password",passwordHash);

                        return params;
                    }

                };


        return LoggerMsg.status;
    }

    @Override
    public String signUp(Client client, Context context) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, APIurls.URL_REGISTER, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }


                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();
                params.put("email",client.getEmail());
                params.put("password",client.getPasswordHash());
                params.put("nom",client.getNom());
                params.put("prenom",client.getPrenom());
                params.put("userType",client.getUserType());
                return params;
            }

        };
        return null;
    }

    @Override
    public String signOut() {
        return null;
    }


}
