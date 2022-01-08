package com.mycoiffeur.services.authentification;

import android.content.Context;

import com.mycoiffeur.models.Client;
import com.mycoiffeur.models.VolleyCallBack;

public interface Authentification {

    public void signIn(String email, String passwordHash, Context context,VolleyCallBack callBack);
    public void signUp(Client client, Context context,VolleyCallBack callBack);
    public String signOut();

}
