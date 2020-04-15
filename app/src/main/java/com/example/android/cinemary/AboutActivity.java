package com.example.android.cinemary;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle(getString(R.string.about));

        LinearLayout apiLayout = findViewById(R.id.api_layout),
                sourceLayout = findViewById(R.id.source_layout),
                contactLayout = findViewById(R.id.contact_layout);

        apiLayout.setOnClickListener(this);
        sourceLayout.setOnClickListener(this);
        contactLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.api_layout:
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(getString(R.string.about_api_url)));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                break;
            case R.id.source_layout:
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(getString(R.string.about_source_url)));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                break;
            case R.id.contact_layout:
                String[] emailArray = {getString(R.string.about_contact_email)};
                intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"))
                        .putExtra(Intent.EXTRA_EMAIL, emailArray)
                        .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.about_contact_subject));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
