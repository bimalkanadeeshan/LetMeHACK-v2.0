package com.letmehack.administrator.letmehack1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.design.widget.NavigationView;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
//import com.bumptech.glide.request.RequestOptions;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FAQActivity extends AppCompatActivity {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;

    private android.support.v7.widget.Toolbar mtoolBar;

    private DrawerLayout mainDrawer;
    public Button btnAboutUs;
    public Button btnContactUs;
    public Button btnSponsor;
    public Button btnLogout;
    public  Button btnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        overridePendingTransition(0,0);

        listView = (ExpandableListView) findViewById(R.id.lvExp);
        initData();
        listAdapter = new ExpandableListAdapter(this,listDataHeader,listHash);
        listView.setAdapter(listAdapter);


        //Drawer settings
        //get shared preference data

        //ger qr icon
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        final ImageView ionImage =  findViewById(R.id.profile_image);

        if (!pref.contains("name")){
            // Toast.makeText(this, "Please SignIn to get your QR!!!", Toast.LENGTH_SHORT).show();
        }
        else {
            String photo=pref.getString("photo", null);
            // Log.e("Photo :",photo);

            //get member name
            String name=pref.getString("name", null);
            //Log.e("Name :",name);

            //get team name
            String team=pref.getString("team", null);
            //Log.e("Team :",team);

            //get university name
            String university=pref.getString("university", null);
            // Log.e("University :",university);

            //image Load with Ion


//        Ion.with(ionImage)
//                .placeholder(R.mipmap.usericon)
//                .error(R.mipmap.usericon)
//                .load(photo);
            //    Glide.with(this).load(photo).apply(RequestOptions.circleCropTransform()).into(ionImage);

            Glide.with(this).load(photo).asBitmap().centerCrop().into(new BitmapImageViewTarget(ionImage) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(FAQActivity.this.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    ionImage.setImageDrawable(circularBitmapDrawable);
                }

            });



            //text update name
            TextView txtName = findViewById(R.id.txtName);
            txtName.setText(name);

            //text update team
            TextView txtTeam = findViewById(R.id.txtTeam);
            txtTeam.setText(team);

            //text update team
            TextView txtUniversity = findViewById(R.id.txtUniversity);
            txtUniversity.setText(university);

        }

        btnAboutUs = findViewById(R.id.btnAboutUs);
        btnAboutUs.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent( FAQActivity.this, AboutUs.class);
                startActivity(i);

            }
        });

        btnContactUs = findViewById(R.id.btnContactUs);
        btnContactUs.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent( FAQActivity.this, ContactUs.class);
                startActivity(i);

            }
        });
        btnSponsor = findViewById(R.id.btnSponsor);
        btnSponsor.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent( FAQActivity.this, SponsoreActivity.class);
                startActivity(i);

            }
        });
        btnMap = findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent( FAQActivity.this, MapActivity.class);
                startActivity(i);

            }
        });
        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE).edit().clear().apply();

                TextView txtName = findViewById(R.id.txtName);
                txtName.setText("Name");

                //text update team
                TextView txtTeam = findViewById(R.id.txtTeam);
                txtTeam.setText("Team");

                //text update team
                TextView txtUniversity = findViewById(R.id.txtUniversity);
                txtUniversity.setText("University");

                ionImage.setImageResource(R.mipmap.usericon);
                NavigationView drawer = findViewById(R.id.mainNavigationView);
                mainDrawer.closeDrawer(drawer);
                Toast.makeText(FAQActivity.this,"logout successfull",Toast.LENGTH_SHORT).show();

            }
        });





        //change text,icon and select icons


        View topToolbar = findViewById( R.id.top_toolbar_faq ); // root View id from that link
        Button ttB = topToolbar.findViewById(R.id.btn_account);
        ttB.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent( FAQActivity.this, SignupActivity.class);
                startActivity(i);
                finish();
            }
        });
        View bottomToolbar = findViewById( R.id.bottom_toolbar_faq ); // root View id from that link
        TextView tv = topToolbar.findViewById(R.id.textView);
        tv.setText("FAQ");
        Button ib = bottomToolbar.findViewById(R.id.btnFAQ);
        ib.setBackgroundResource(R.drawable.faq_hover_icon);

        Button btnDrawer = topToolbar.findViewById(R.id.btn_home);
        mainDrawer =findViewById(R.id.drawer_layout);
        btnDrawer.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {

                mainDrawer.openDrawer(Gravity.START);

            }
        });


        //Buttons..............................................................................................................................
        View BottomToolbar = findViewById( R.id.bottom_toolbar_faq ); // root View id from that link

        Button btnage = BottomToolbar.findViewById(R.id.btnAgenda);
        btnage.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent( FAQActivity.this, AgendaActivity.class);
                startActivity(i);
                finish();
            }
        });
        Button btnhome = BottomToolbar.findViewById(R.id.btnHome);
        btnhome.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent( FAQActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });
        Button btnLeaderBoard = BottomToolbar.findViewById(R.id.btnLeaderBoard);
        btnLeaderBoard.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent( FAQActivity.this, LeaderBoard.class);
                startActivity(i);
                finish();
            }
        });
        Button btnQR = BottomToolbar.findViewById(R.id.btnQR);
        btnQR.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent( FAQActivity.this, QRActivity.class);
                startActivity(i);
                finish();
            }
        });
        Button btnFaq = BottomToolbar.findViewById(R.id.btnFAQ);
        btnFaq.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent( FAQActivity.this, FAQActivity.class);
                startActivity(i);
                finish();
            }
        });
        //Buttons..............................................................................................................................End

    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("How do I travel from Colombo to venue?");
        listDataHeader.add("What is the average bus ticket fair from Colombo?");
        listDataHeader.add("Average time from colombo to sabaragamuwa university by bus");
        listDataHeader.add("What time should I arrive?");
        listDataHeader.add("What do I bring?");
        listDataHeader.add("When will the registration started?");
        listDataHeader.add("When will be the closing ceremony of the event?");

        List<String> q1 = new ArrayList<>();
        q1.add("From Colombo get a 99 Colombo - badulla bus and get down from pambahinna junction. By there it is 5 minutes distance to the the university");

        List<String> q2 = new ArrayList<>();
        q2.add("Normal bus - 220/=");
        q2.add("Semi luxury - 320/=");

        List<String> q3 = new ArrayList<>();
        q3.add("5 hours and 30 minutes (nearly 162 km from colombo)");

        List<String> q4 = new ArrayList<>();
        q4.add("We are recommended to arrive university premises at least 8.30 am on 1st of feb");

        List<String> q5 = new ArrayList<>();
        q5.add("●Your laptop");
        q5.add("●Internet connection");
        q5.add("●Power extension");
        q5.add("●Chargers & other accessories");

        List<String> q6 = new ArrayList<>();
        q6.add("9.00am on  1st of february");

        List<String> q7 = new ArrayList<>();
        q7.add("The closing ceremony will be held on 2nd of february 2020 form 6.30am to 8.30 am");

        listHash.put(listDataHeader.get(0),q1);
        listHash.put(listDataHeader.get(1),q2);
        listHash.put(listDataHeader.get(2),q3);
        listHash.put(listDataHeader.get(3),q4);
        listHash.put(listDataHeader.get(4),q5);
        listHash.put(listDataHeader.get(5),q6);
        listHash.put(listDataHeader.get(6),q7);

    }
}
