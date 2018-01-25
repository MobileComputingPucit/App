package com.example.mzainnaseer.sohail_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class sign_up extends AppCompatActivity {
    EditText email,password,phone,confirm_password;
    TextView sign_up;
    String email_,pass_,ph_,confirm_;
    dbHandler _ref;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        _ref=new dbHandler(getApplicationContext());
        email=(EditText)findViewById(R.id.email_editText_client_signup_xml);
        phone=(EditText)findViewById(R.id.phone_editText_client_signup_xml);
        password=(EditText)findViewById(R.id.password_editText_client_signup_xml);
        confirm_password=(EditText)findViewById(R.id.confirm_password_editText_client_signup_xml);
        sign_up=(TextView)findViewById(R.id.next_btn_button_client_signup_xml);
        user=new User();
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email_=email.getText().toString().trim();
                confirm_=confirm_password.getText().toString().trim();
                ph_=phone.getText().toString().trim();
                pass_=password.getText().toString().trim();

                if(email_.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(ph_.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter Phone",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(confirm_.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Confirm Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pass_.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                user.setUserEmail(email_);
                user.setUserPassword(pass_);
                user.setUserPhone(ph_);
                if(_ref.add_users_data(user))
                {
                    Intent intent=new Intent(getApplicationContext(),welcome.class);
                    startActivity(intent);
                    finish();
                }

            }
        });


    }
}
