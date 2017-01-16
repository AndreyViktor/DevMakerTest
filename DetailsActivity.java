package br.com.andrey.devmakertest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView nameView = (TextView) findViewById(R.id.name_view);
        TextView emailView = (TextView) findViewById(R.id.email_view);
        TextView telephoneView = (TextView) findViewById(R.id.telephone_view);

        Intent intent = getIntent();
        Contact contact = (Contact) intent.getSerializableExtra("contact");

        nameView.setText(contact.getName());
        emailView.setText("Email: "+contact.getEmail());
        telephoneView.setText("Telephone: "+contact.getTelephone());

    }

}
