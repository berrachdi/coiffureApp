package com.mycoiffeur.services.authentification;

import android.content.Context;

import com.mycoiffeur.models.Client;

public interface Authentification {

    public Boolean signIn(String email, String passwordHash, Context context);
    public String signUp(Client client, Context context);
    public String signOut();

}
