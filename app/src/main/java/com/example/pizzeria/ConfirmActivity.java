package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmActivity extends AppCompatActivity {

    TextView firstname, lastname, phone, menuSelected, totalCost;
    Button buttonBack, buttonValidate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        //Elements de la partie graphique
        //Boutons
        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonValidate = (Button) findViewById(R.id.buttonValidate);
        // TextView
        firstname = (TextView) findViewById(R.id.textViewFirstname);
        lastname = (TextView) findViewById(R.id.textViewLastname);
        phone = (TextView) findViewById(R.id.textViewPhone);
        menuSelected = (TextView) findViewById(R.id.textViewSelected);
        totalCost = (TextView) findViewById(R.id.textViewTotal);

        // On récupère les valeurs des champs
        Intent confirm = getIntent();
        String nomValue = confirm.getStringExtra(MainActivity.EXTRA_NOM);
        String prenomValue = confirm.getStringExtra(MainActivity.EXTRA_PRENOM);
        String phoneValue = confirm.getStringExtra(MainActivity.EXTRA_PHONE);
        String totalCostValue = confirm.getStringExtra(MainActivity.EXTRA_TOTAL_COST);
        String menuSelectedValue = confirm.getStringExtra(MainActivity.EXTRA_MENU_SELECTED);

        // On envoie les valeurs
        firstname.setText(prenomValue);
        lastname.setText(nomValue);
        phone.setText(phoneValue);
        totalCost.setText(totalCostValue + " €");
        menuSelected.setText(menuSelectedValue);

        // Si le bouton retour est cliqué
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewMainPage = new Intent(ConfirmActivity.this, MainActivity.class);
                startActivity(viewMainPage);
            }
        });

        // Si le bouton valider est cliqué
        buttonValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewLastPage = new Intent(ConfirmActivity.this, LastActivity.class);
                startActivity(viewLastPage);
            }
        });
    }
}