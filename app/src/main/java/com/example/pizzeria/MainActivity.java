package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText firstname, lastname, phone;
    Button buttonNext, buttonQuit;
    CheckBox menu1, menu2, menu3;

    public int totalCost = 0;
    public final int priceMenu1 = 10;
    public final int priceMenu2 = 15;
    public final int priceMenu3 = 12;

    public String menuSelected = "";

    public static final String EXTRA_NOM = "com.example.pizzeria.nom";
    public static final String EXTRA_PRENOM = "com.example.pizzeria.prenom";
    public static final String EXTRA_PHONE = "com.example.pizzeria.phone";
    public static final String EXTRA_TOTAL_COST = "com.example.pizzeria.totalcost";
    public static final String EXTRA_MENU_SELECTED = "com.example.pizzeria.menuSelected";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Elements de la partie graphique
        // EditText
        firstname = (EditText) findViewById(R.id.editTextFirstname);
        lastname = (EditText) findViewById(R.id.editTextLastname);
        phone = (EditText) findViewById(R.id.editTextPhone);
        //Checkbox
        menu1 = (CheckBox) findViewById(R.id.checkBoxMenu1);
        menu2 = (CheckBox) findViewById(R.id.checkBoxMenu2);
        menu3 = (CheckBox) findViewById(R.id.checkBoxMenu3);
        // Bouton
        buttonNext = (Button) findViewById(R.id.buttonNext);
        buttonQuit = (Button) findViewById(R.id.buttonQuit);

        //Si la checkbox menu1 est cliqué
        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((CheckBox)view).isChecked();
                if(checked){
                    totalCost =+ priceMenu1;
                    menuSelected = menuSelected.concat("Menu 1 : 10 €\n");
                }
            }
        });

        //Si la checkbox menu2 est cliqué
        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((CheckBox)view).isChecked();
                    if (checked) {
                        totalCost += priceMenu2;
                        menuSelected = menuSelected.concat("Menu 2 : 15 €\n");
                    }
                }
        });

        // Si la checkbox menu3 est cliqué
        menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((CheckBox)view).isChecked();
                if (checked) {
                    totalCost += priceMenu3;
                    menuSelected = menuSelected.concat("Menu 3 : 12 €\n");
                }
            }
        });

        // Si le bouton confirmez est cliqué
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewConfirmPage = new Intent(MainActivity.this, ConfirmActivity.class);

                String nomValue = lastname.getText().toString();
                String prenomValue = firstname.getText().toString();
                String phoneValue = phone.getText().toString();

                int totalCostValue = totalCost;
                String sCostValue = String.valueOf(totalCostValue);

                viewConfirmPage.putExtra(EXTRA_NOM, nomValue);
                viewConfirmPage.putExtra(EXTRA_PRENOM, prenomValue);
                viewConfirmPage.putExtra(EXTRA_PHONE, phoneValue);
                viewConfirmPage.putExtra(EXTRA_TOTAL_COST, sCostValue);
                viewConfirmPage.putExtra(EXTRA_MENU_SELECTED, menuSelected);

                startActivity(viewConfirmPage);
            }
        });

        // Si le bouton quitter est cliqué
        buttonQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}