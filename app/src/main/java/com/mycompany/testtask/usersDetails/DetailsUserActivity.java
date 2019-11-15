package com.mycompany.testtask.usersDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.mycompany.testtask.POJO.User;
import com.mycompany.testtask.R;


public class DetailsUserActivity extends AppCompatActivity {


    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_user);

        TextView textViewName, textViewEmail, textViewPhone;
        textViewName = findViewById(R.id.name);
        textViewEmail = findViewById(R.id.email);
        textViewPhone = findViewById(R.id.phone);
        WebView webView = findViewById(R.id.webView);


        user = getIntent().getParcelableExtra("user");

        textViewName.setText(new StringBuilder("Name: ").append(user.getName()));
        textViewEmail.setText(new StringBuilder("Email: ").append(user.getEmail()));
        textViewPhone.setText(new StringBuilder("Phone: ").append(user.getPhone()));

//        webView.loadUrl("https://www." + user.getWebsite() + "/");
        webView.loadUrl("https://www.linkedin.com/");
    }
}
