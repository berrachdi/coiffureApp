
package com.mycoiffeur.interfaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.mycoiffeur.R;
import com.mycoiffeur.models.Client;
import com.mycoiffeur.securite.Securite;

public class RegisterScreenActivity extends AppCompatActivity {

    private Securite securite;

    private TextView textBtnConnextion;
    private EditText textEmail,textPassword,textConfirmePassword;
    private AppCompatButton btnInscription;
    private String email,password,confirmePassword,userType;
    private CheckBox checkBoxCoiffeur,checkBoxClient;

    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screen);


        textEmail = findViewById(R.id.upemail);
        textPassword = findViewById(R.id.uppassword);
        textConfirmePassword = findViewById(R.id.upconfirme_password);
        checkBoxClient = findViewById(R.id.checkbox_client);
        checkBoxCoiffeur = findViewById(R.id.checkbox_coiffeur);

        textBtnConnextion = findViewById(R.id.textbtn_connexion);
        btnInscription = findViewById(R.id.upinscription);

        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = textEmail.getText().toString();
                password = textPassword.toString().toString();
                confirmePassword = textConfirmePassword.getText().toString();

                    // Register setup 0: check user type (client or coiffeur)
                    if(checkBoxCoiffeur.isChecked()){
                        userType = "coiffeur";
                    }else {
                        userType = "client";
                    }

                    // Register setup 1: password confirmation
                        securite = new Securite();
                        if( securite.passwordConfirme(password,confirmePassword) ){
                            // setup 2: register
                            client = new Client("","",email,password,userType);
                            client.signUp(client,getApplicationContext());
                        }else{
                            // Show error message
                        }
            }
        });
        textBtnConnextion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navCtrl( getApplicationContext(),
                        LoginScreenActivity.class );



            }
        });
    }




    private void navCtrl(Context context, Class<?>cls ){

        Intent intent = new Intent( context,
                cls );

        startActivity(intent);

    }
}