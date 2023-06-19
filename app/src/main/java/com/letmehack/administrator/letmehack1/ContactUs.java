package com.letmehack.administrator.letmehack1;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;

public class ContactUs extends AppCompatActivity {
    private ImageButton btnCall;
    private ImageButton btnEmail;
    private ImageButton btnWeb;
    private ImageButton btnFB;
    private ImageButton btnTwitter;
    private ImageButton btnInsta;
    private android.support.v7.widget.Toolbar mToolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        //Toolbar
        mToolBar = findViewById(R.id.register_toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("Contact Us");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Buttons
        btnCall=findViewById(R.id.btnCall);
        btnEmail=findViewById(R.id.btnMessage);
        btnWeb=findViewById(R.id.btnWeb);
        btnFB=findViewById(R.id.btnFacebook);
        btnTwitter=findViewById(R.id.btnTwitter);

        btnInsta=findViewById(R.id.btnInsta);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContactUs.this, "Call Us", Toast.LENGTH_SHORT).show();
                v.startAnimation(AnimationUtils.loadAnimation(ContactUs.this, R.anim.image_clic));
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0719796917"));
                startActivity(intent);

            }
        });
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContactUs.this, "Email Us", Toast.LENGTH_SHORT).show();
                v.startAnimation(AnimationUtils.loadAnimation(ContactUs.this, R.anim.image_clic));





                Intent intent=new Intent(Intent.ACTION_SEND);
                String[] recipients={"info@letmehack.lk"};
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
//                intent.putExtra(Intent.EXTRA_SUBJECT,"abc");
//                intent.putExtra(Intent.EXTRA_TEXT,"def");
//                intent.putExtra(Intent.EXTRA_CC,"ghi");
                intent.setType("text/html");
                startActivity(Intent.createChooser(intent, "Open Your Email App"));

            }
        });
        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContactUs.this, "Web", Toast.LENGTH_SHORT).show();
                v.startAnimation(AnimationUtils.loadAnimation(ContactUs.this, R.anim.image_clic));
                Uri uriUrl = Uri.parse("http://letmehack.lk/");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });
        btnTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContactUs.this, "Opening LetMeHACK on Twitter", Toast.LENGTH_SHORT).show();
                v.startAnimation(AnimationUtils.loadAnimation(ContactUs.this, R.anim.image_clic));

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("twitter://@letmehack2k18"));
                    startActivity(intent);
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://twitter.com/letmehack2k18")));
                }
            }
        });
        btnFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContactUs.this, "Opening LetMeHACK on Facebook", Toast.LENGTH_SHORT).show();
                v.startAnimation(AnimationUtils.loadAnimation(ContactUs.this, R.anim.image_clic));
                try {
                    String facebookId = "fb://page/925025550980059";
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(facebookId));
                    startActivity(intent);
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.facebook.com/arkverse")));
                }

            }
        });
        btnInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContactUs.this, "Opening LetMeHACK on Instagram", Toast.LENGTH_SHORT).show();
                v.startAnimation(AnimationUtils.loadAnimation(ContactUs.this, R.anim.image_clic));

                Uri uri = Uri.parse("https://www.instagram.com/letmehack2020/?hl=en");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.instagram.com/letmehack2020/")));
                }
            }
        });


    }
}
