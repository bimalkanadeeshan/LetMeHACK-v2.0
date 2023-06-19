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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
//import com.bumptech.glide.request.RequestOptions;

public class AgendaActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar mtoolBar;
    private DrawerLayout mainDrawer;
    public Button btnAboutUs;
    public Button btnContactUs;
    public Button btnSponsor;
    public Button btnLogout;
    public Button btnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendaa);
        overridePendingTransition(0,0);

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
                            RoundedBitmapDrawableFactory.create(AgendaActivity.this.getResources(), resource);
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
                Intent i = new Intent( AgendaActivity.this, AboutUs.class);
                startActivity(i);

            }
        });

        btnMap = findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent( AgendaActivity.this, MapActivity.class);
                startActivity(i);

            }
        });
        btnContactUs = findViewById(R.id.btnContactUs);
        btnContactUs.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent( AgendaActivity.this, ContactUs.class);
                startActivity(i);

            }
        });
        btnSponsor = findViewById(R.id.btnSponsor);
        btnSponsor.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent( AgendaActivity.this, SponsoreActivity.class);
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
                Toast.makeText(AgendaActivity.this,"logout successfull",Toast.LENGTH_SHORT).show();
            }
        });





        //change text,icon and select icons

        View topToolbar = findViewById( R.id.top_toolbar_agenda); // root View id from that link
        Button ttB = topToolbar.findViewById(R.id.btn_account);
        ttB.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent( AgendaActivity.this, SignupActivity.class);
                startActivity(i);
                finish();
            }
        });
        View bottomToolbar = findViewById( R.id.bottom_toolbar_agenda ); // root View id from that link
        TextView tv = topToolbar.findViewById(R.id.textView);
        tv.setText("Agenda");
        Button ib = bottomToolbar.findViewById(R.id.btnAgenda);
        ib.setBackgroundResource(R.drawable.agenda_icon_hover_icon);

        //open drawer
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
        View BottomToolbar = findViewById( R.id.bottom_toolbar_agenda ); // root View id from that link

        Button btnage = BottomToolbar.findViewById(R.id.btnAgenda);
        btnage.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent( AgendaActivity.this, AgendaActivity.class);
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
                Intent i = new Intent( AgendaActivity.this, HomeActivity.class);
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
                Intent i = new Intent( AgendaActivity.this, QRActivity.class);
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
                Intent i = new Intent( AgendaActivity.this, LeaderBoard.class);
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
                Intent i = new Intent( AgendaActivity.this, FAQActivity.class);
                startActivity(i);
                finish();
            }
        });
        //Buttons..............................................................................................................................End

    }
}
