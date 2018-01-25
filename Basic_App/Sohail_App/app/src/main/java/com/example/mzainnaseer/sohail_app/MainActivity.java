package com.example.mzainnaseer.sohail_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button login,signup,forgot_password;
    EditText email,password;
    String email_,pass_;
    public static dbHandler _ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _ref = new dbHandler(getApplicationContext());
        login=(Button)findViewById(R.id.login_btn_button_client_login_xml);
        signup=(Button)findViewById(R.id.signup_btn_button_client_login_xml);
        email=(EditText)findViewById(R.id.email_editText_client_login_xml);
        password=(EditText)findViewById(R.id.password_editText_client_login_xml);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),sign_up.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email_=email.getText().toString().trim();
                if(email_.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                pass_=password.getText().toString().trim();
                if(pass_.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(_ref.getUserTableCount(email_,pass_))
                {
                    Intent intent=new Intent(getApplicationContext(),welcome.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"You dont have Any Account",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

    }
}
