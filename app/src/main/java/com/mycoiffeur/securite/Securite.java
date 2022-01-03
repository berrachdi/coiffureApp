package com.mycoiffeur.securite;

public class Securite {

    public Boolean passwordConfirme(String passwordHash1,String passwordHash2){

        return passwordHash1==passwordHash2 ? true:false;

    }
}
