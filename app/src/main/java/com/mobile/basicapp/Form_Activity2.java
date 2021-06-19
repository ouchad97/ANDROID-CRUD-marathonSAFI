package com.mobile.basicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Form_Activity2 extends AppCompatActivity {

    public EditText txtNom;
    public EditText txtEmail;
    public EditText txtTel;
    private Button btnValider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form2);

        txtNom = (EditText) findViewById(R.id.txtNom);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtTel = (EditText) findViewById(R.id.txtTel);
        btnValider = (Button) findViewById(R.id.btnValider);

        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyDatabase myDB = new MyDatabase(Form_Activity2.this);
                myDB.addUser(txtNom.getText().toString().trim(),
                        txtEmail.getText().toString().trim(),
                        Integer.valueOf(txtTel.getText().toString().trim()));

                openForm_info();
            }
        });
    }

    public void openForm_info(){
        Intent intent = new Intent (this, InfoActivity.class);
        startActivity(intent);
    }
}