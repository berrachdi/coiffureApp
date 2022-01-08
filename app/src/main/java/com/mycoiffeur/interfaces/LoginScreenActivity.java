

package com.mycoiffeur.interfaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mycoiffeur.R;
import com.mycoiffeur.interfaces.RegisterScreenActivity;
import com.mycoiffeur.logger.LoggerMsg;
import com.mycoiffeur.models.Client;
import com.mycoiffeur.models.VolleyCallBack;

import org.json.JSONException;
import org.json.JSONObject;

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
        btnConnexion = findViewById(R.id.btn_connexion);
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

               client.signIn(email, hashPassword, getApplicationContext(), new VolleyCallBack() {
                    @Override
                    public void onSuccess(String response) {


                    }

                       @Override
                       public void onSuccess(JSONObject response) {
                        try{
                            if(response.get("userId") !=null ){
                                LoggerMsg.status = false;
                                // Success login > returned the TOKEN AND USER-ID or USER-EMAIL
                                // SAVE USER DATA IN LOCAL STORAGE
                                SharedPreferences sharedPreferences= getApplicationContext().getSharedPreferences("USER_LOGIN_DATA",Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putString("USEREMAIL", response.get("userId").toString());
                                editor.putString("USERTOKEN", response.get("userToken").toString());
                                editor.putString("USERTYPE", response.get("usertype").toString());
                                editor.apply();

                                // Navigator to HomeScreenActivity
                                navCtrl( getApplicationContext(),
                                        HomeScreenActivity.class );}

                        }catch (JSONException e){
                            Log.d("client.signIn: ",e.getMessage());
                        }

                       }
               });


            }
        });
    }


    private void navCtrl(Context context, Class<?>cls ){

        Intent intent = new Intent( context,
                cls );

        startActivity(intent);

    }
}