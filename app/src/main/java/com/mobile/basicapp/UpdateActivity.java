package com.mobile.basicapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class UpdateActivity extends AppCompatActivity {

    EditText name_input, email_input, tel_input;
    Button update_button, delete_button;

    String id, name, email, tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input = findViewById(R.id.name_input2);
        email_input = findViewById(R.id.email_input2);
        tel_input = findViewById(R.id.tel_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabase myDB = new MyDatabase(UpdateActivity.this);
                name = name_input.getText().toString().trim();
                email = email_input.getText().toString().trim();
                tel = tel_input.getText().toString().trim();
                myDB.updateData(id, name, email, tel);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("_id") && getIntent().hasExtra("nameUser") &&
                getIntent().hasExtra("emailUser") && getIntent().hasExtra("telUser")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("_id");
            name = getIntent().getStringExtra("nameUser");
            email = getIntent().getStringExtra("emailUser");
            tel = getIntent().getStringExtra("telUser");

            //Setting Intent Data
            name_input.setText(name);
            email_input.setText(email);
            tel_input.setText(tel);
            Log.d("stev", name+" "+email+" "+tel);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabase myDB = new MyDatabase(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}