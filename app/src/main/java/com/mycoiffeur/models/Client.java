package com.mycoiffeur.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.logging.Logger;
import com.mycoiffeur.interfaces.LoginScreenActivity;
import com.mycoiffeur.logger.LoggerMsg;
import com.mycoiffeur.services.authentification.Authentification;
import com.mycoiffeur.api.APIurls;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Client extends User implements Authentification {

    // For local user login data storage
    private SharedPreferences sharedPreferences;

    public Client(String nom, String prenom, String email, String passwordHash, String userType) {
        super(nom, prenom, email, passwordHash, userType);
    }

    @Override
    public void signIn(String email, String passwordHash, Context context,final VolleyCallBack callBack) {






    }

    @Override
    public void signUp(Client client, Context context,final VolleyCallBack callBack) {



        try{
            RequestQueue requestQueue = Volley.newRequestQueue(context);

            JSONObject jsonBody = new JSONObject();
            jsonBody.put("lastName",client.getNom());
            jsonBody.put("email",client.getEmail());
            jsonBody.put("passWord",client.getPasswordHash());
            jsonBody.put("firstName",client.getPrenom());
            jsonBody.put("userType",client.getUserType());
            jsonBody.put("address","Nador");

            final String requestBody = jsonBody.toString();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, APIurls.URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.onSuccess(response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    // can get more details such as response.headers
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

            requestQueue.add(stringRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }



    @Override
    public String signOut() {
        return null;
    }


}
