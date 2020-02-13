package com.example.wholerecords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.SignOn);
        Button button2 = findViewById(R.id.Register);
        Button button3 = findViewById(R.id.Login);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        DatabaseH myDB = new DatabaseH(this);

        //getting the input fields
        EditText text1 = findViewById(R.id.Name);
        EditText text2 = findViewById(R.id.Password);
        //making input fields into strings
        String Name = text1.getText().toString();
        String Password = text2.getText().toString();


        switch (v.getId())
        {
            case R.id.SignOn:
                SignOn();
                break;

            case R.id.Register:
                Register();
                break;

            case R.id.Login:

                boolean exists = myDB.UserMatch(Name, Password);
                myDB.close();

                if(exists == true)
                {
                    User.setName(Name);
                    Login();
                }
                else
                {
                    Toast toast = Toast.makeText(this,
                            "User name and password don't match",
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
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
    }
}
