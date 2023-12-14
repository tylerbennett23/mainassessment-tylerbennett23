package com.example.restester;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    //takes you the main page once signed in
    public void mainpage(View view){

        //gets input username
        EditText username = (EditText)findViewById(R.id.UserTxtBox);
        String Username = username.getText().toString();

        //gets input password
        EditText password = (EditText)findViewById(R.id.PassTxtBox);
        String Password = password.getText().toString();

        //for incorrect credentials
        TextView credentials = (TextView)findViewById(R.id.IncorrectCretxt);


        //checks to see if inputs are correct
        if(Username == "username1"){
            if(Password == "password1"){
                setContentView(R.layout.fragment_gallery);
            }else{
                credentials.setVisibility(View.VISIBLE);
            }
        }else{
            credentials.setVisibility(View.VISIBLE);
        }
    }

    //takes you to the create account page
    public void createaccountpge(View view){
        setContentView(R.layout.fragment_slideshow);
    }
}
