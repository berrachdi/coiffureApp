package com.mycoiffeur.interfaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.mycoiffeur.R;

public class CompleteProfile extends AppCompatActivity {

    private EditText firstname,lastname;
    private int[][] coiffeurServices = {{0,0},{0,0}};
    private CardView cardShower,cardCut,cardVisage,cardChich;
    private AppCompatButton btnEnrigister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);

        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        cardShower = findViewById(R.id.serviceShower);
        cardCut = findViewById(R.id.serviceCut);
        cardVisage = findViewById(R.id.serviceMassageVisage);
        cardChich = findViewById(R.id.serviceChich);
        btnEnrigister = findViewById(R.id.profilecompliter);


        btnEnrigister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });









    }
}