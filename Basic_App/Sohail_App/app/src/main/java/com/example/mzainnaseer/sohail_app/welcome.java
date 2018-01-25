package com.example.mzainnaseer.sohail_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class welcome extends AppCompatActivity {
    TextView logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        logout=(TextView)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHandler _ref=new dbHandler(getApplicationContext());
                Intent intent=new Intent(getApplicationContext(),sign_up.class);
                startActivity(intent);
                finish();
                _ref.truncate_user_table();

            }
        });

    }
}
