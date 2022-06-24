package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmActivity extends AppCompatActivity {

    public final String strDest = "0641206582";

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
                try {
                    String strSms = "Un client au nom de " + prenomValue + " " + nomValue
                            + " a fait la commande suivante : " + menuSelectedValue + ".";
                    SmsManager smgr = SmsManager.getDefault();
                    smgr.sendTextMessage(strDest, null, strSms, null, null);
                } catch (Exception e){
                    Toast.makeText(ConfirmActivity.this, "Failed to Send to " + strDest +". Try again", Toast.LENGTH_SHORT).show();
                }
                startActivity(viewLastPage);
            }
        });
    }
}