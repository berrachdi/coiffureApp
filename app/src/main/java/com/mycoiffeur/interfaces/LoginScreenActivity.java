

package com.mycoiffeur.interfaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mycoiffeur.R;
import com.mycoiffeur.interfaces.RegisterScreenActivity;
import com.mycoiffeur.logger.LoggerMsg;
import com.mycoiffeur.models.Client;

public class LoginScreenActivity extends AppCompatActivity {

    private TextView btnCreateAccount, erreurMessage;
    private EditText textEmail,textPassword;
    private AppCompatButton btnConnexion;

    private String email,hashPassword;

    private Client client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_screen);

        // Find view
        erreurMessage = findViewById(R.id.erreur_msg);

        btnCreateAccount = findViewById(R.id.textbtn_create_new_account);
        textEmail = findViewById(R.id.email);
        textPassword = findViewById(R.id.password);

        // Create new account rederect
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navCtrl( getApplicationContext(),
                        RegisterScreenActivity.class );



            }
        });

        // Connexion setup
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = textEmail.getText().toString();
                hashPassword = textPassword.getText().toString();

                if( client.signIn(email,hashPassword,getApplicationContext()) ){
                    // Go to home page
                    LoggerMsg.status = false;
                    navCtrl( getApplicationContext(),
                            HomeScreenActivity.class );
                }else{
                    // Erreur message
                    erreurMessage.setText(LoggerMsg.LOGIN_ERREUR_MSG);

                }
            }
        });
    }


    private void navCtrl(Context context, Class<?>cls ){

        Intent intent = new Intent( context,
                cls );

        startActivity(intent);

    }
}