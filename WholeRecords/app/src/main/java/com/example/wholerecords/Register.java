package com.example.wholerecords;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Button button1 = findViewById(R.id.SignOn);
        Button button2 = findViewById(R.id.Register);
        Button button3 = findViewById(R.id.Submit);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        DatabaseH myDB = new DatabaseH(this);
        //deleteDatabase("WholeR");

        //getting the input fields
        EditText text1 = findViewById(R.id.Name);
        EditText text2 = findViewById(R.id.Password);
        EditText text3 = findViewById(R.id.email);
        EditText text4 = findViewById(R.id.phone);
        EditText text5 = findViewById(R.id.address);
        //making input fields into strings
        String Name = text1.getText().toString();
        String Password = text2.getText().toString();
        String Email = text3.getText().toString();
        String Phone = text4.getText().toString();
        String Address = text5.getText().toString();


        switch (v.getId())
        {
            case R.id.SignOn:
                SignOn();
                break;

            case R.id.Register:
                Register();
                break;

            case R.id.Submit:

                boolean exists = myDB.finduser(Name);

                if(exists == false)
                {
                    if(TextUtils.isEmpty(Name) || TextUtils.isEmpty(Password) || TextUtils.isEmpty(Email) || TextUtils.isEmpty(Phone) || TextUtils.isEmpty(Address))
                    {
                        Toast toast = Toast.makeText(this,
                                "One or more fields were left empty",
                                Toast.LENGTH_SHORT);

                        toast.show();
                    }
                    else
                    {
                        //adding a new user to the database
                        myDB.adduser(Name, Password, Email, Phone, Address);
                        myDB.close();

                        Toast toast = Toast.makeText(this,
                                "Registration Successful",
                                Toast.LENGTH_SHORT);

                        toast.show();

                        Login();
                    }
                }
                else
                {
                    Toast toast = Toast.makeText(this,
                            "User name already in use",
                            Toast.LENGTH_SHORT);

                    toast.show();
                }
                break;
        }
    }

    public void SignOn()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void Register()
    {
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }

    public void Login()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
