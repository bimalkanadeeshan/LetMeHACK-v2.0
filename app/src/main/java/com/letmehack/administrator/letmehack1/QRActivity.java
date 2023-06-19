package com.letmehack.administrator.letmehack1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
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
import com.koushikdutta.ion.Ion;

public class QRActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar mtoolBar;

    private DrawerLayout mainDrawer;
    public Button btnAboutUs;
    public Button btnContactUs;
    public Button btnSponsor;
    public Button btnLogout;
    public Button btnMap;
    ImageView ionImage2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        overridePendingTransition(0, 0);

        ionImage2 = findViewById(R.id.profile_image);
        //get shared preference data
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        String qr_code = pref.getString("qr_code", "error");
        //Log.e("Qr_Code ",qr_code);

        //put toast as a message to user
        if (!pref.contains("name"))
            Toast.makeText(this, "Please Sign In to get your QR!!!", Toast.LENGTH_SHORT).show();

        else {
            String photo = pref.getString("photo", null);
            //Log.e("Photo :",photo);

            //get member name
            String name = pref.getString("name", null);
            //Log.e("Name :",name);

            //get team name
            String team = pref.getString("team", null);
            //Log.e("Team :",team);

            //get university name
            String university = pref.getString("university", null);
            //Log.e("University :",university);


            //text update name
            TextView txtName = findViewById(R.id.txtName);
            txtName.setText(name);

            //text update team
            TextView txtTeam = findViewById(R.id.txtTeam);
            txtTeam.setText(team);

            //text update team
            TextView txtUniversity = findViewById(R.id.txtUniversity);
            txtUniversity.setText(university);


            //image Load with Ion
            if (checkInternetConnection(QRActivity.this)) {
                ImageView ionImage = (ImageView) findViewById(R.id.imgQR);
                Glide.with(this).load(qr_code).into(ionImage);


                Glide.with(this).load(photo).asBitmap().centerCrop().into(new BitmapImageViewTarget(ionImage2) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(QRActivity.this.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        ionImage2.setImageDrawable(circularBitmapDrawable);
                    }

                });
            } else
                Toast.makeText(QRActivity.this, "No Internet Connection", Toast.LENGTH_LONG).show();


        }


        //image Load with Ion
        //   ImageView ionImage = (ImageView) findViewById(R.id.imgQR);
//        Ion.with(ionImage)
//                //.placeholder(R.drawable.placeholder_image)
//                //.error(R.drawable.error_image)
//                .load(qr_code);


        //Drawer settings
        //get shared preference data

        //ger qr icon


        btnAboutUs = findViewById(R.id.btnAboutUs);
        btnAboutUs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(QRActivity.this, AboutUs.class);
                startActivity(i);

            }
        });

        btnMap = findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent( QRActivity.this, MapActivity.class);
                startActivity(i);

            }
        });
        btnContactUs = findViewById(R.id.btnContactUs);
        btnContactUs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(QRActivity.this, ContactUs.class);
                startActivity(i);

            }
        });
        btnSponsor = findViewById(R.id.btnSponsor);
        btnSponsor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(QRActivity.this, SponsoreActivity.class);
                startActivity(i);

            }
        });
        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE).edit().clear().apply();

                TextView txtName = findViewById(R.id.txtName);
                txtName.setText("Name");

                //text update team
                TextView txtTeam = findViewById(R.id.txtTeam);
                txtTeam.setText("Team");

                //text update team
                TextView txtUniversity = findViewById(R.id.txtUniversity);
                txtUniversity.setText("University");

                ionImage2.setImageResource(R.mipmap.usericon);
                NavigationView drawer = findViewById(R.id.mainNavigationView);
                mainDrawer.closeDrawer(drawer);
                Toast.makeText(QRActivity.this,"logout successfull",Toast.LENGTH_SHORT).show();

            }
        });


        //change text,icon and select icons


        View topToolbar = findViewById(R.id.top_toolbar_QR); // root View id from that link
        Button ttB = topToolbar.findViewById(R.id.btn_account);
        ttB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(QRActivity.this, SignupActivity.class);
                startActivity(i);
                finish();
            }
        });
        View bottomToolbar = findViewById(R.id.bottom_toolbar_QR); // root View id from that link
        TextView tv = topToolbar.findViewById(R.id.textView);
        tv.setText("QR Code");
        Button ib = bottomToolbar.findViewById(R.id.btnQR);
        ib.setBackgroundResource(R.drawable.qr_hover_icon);

        //open drawer
        Button btnDrawer = topToolbar.findViewById(R.id.btn_home);
        mainDrawer = findViewById(R.id.drawer_layout);
        btnDrawer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mainDrawer.openDrawer(Gravity.START);

            }
        });

        //Buttons..............................................................................................................................
        View BottomToolbar = findViewById(R.id.bottom_toolbar_QR); // root View id from that link

        Button btnage = BottomToolbar.findViewById(R.id.btnAgenda);
        btnage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(QRActivity.this, AgendaActivity.class);
                startActivity(i);
                finish();
            }
        });
        Button btnhome = BottomToolbar.findViewById(R.id.btnHome);
        btnhome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(QRActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });
        Button btnQR = BottomToolbar.findViewById(R.id.btnQR);
        btnQR.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(QRActivity.this, QRActivity.class);
                startActivity(i);
                finish();
            }
        });
        Button btnLeaderBoard = BottomToolbar.findViewById(R.id.btnLeaderBoard);
        btnLeaderBoard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(QRActivity.this, LeaderBoard.class);
                startActivity(i);
                finish();
            }
        });
        Button btnFaq = BottomToolbar.findViewById(R.id.btnFAQ);
        btnFaq.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(QRActivity.this, FAQActivity.class);
                startActivity(i);
                finish();
            }
        });
        //Buttons..............................................................................................................................End

    }

    public static boolean checkInternetConnection(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else if (Build.VERSION.SDK_INT >= 21) {
            Network[] info = connectivity.getAllNetworks();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i] != null && connectivity.getNetworkInfo(info[i]).isConnected()) {
                        return true;
                    }
                }
            }
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
            final NetworkInfo activeNetwork = connectivity.getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnected()) {
                return true;
            }
        }

        return false;


    }

}

