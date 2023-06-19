package com.letmehack.administrator.letmehack1;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/*public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        overridePendingTransition(0,0);

        View topToolbar = findViewById( R.id.top_toolbar_notification ); // root View id from that link
        Button ttB = topToolbar.findViewById(R.id.btn_account);
        ttB.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent( NotificationActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });
        View bottomToolbar = findViewById( R.id.bottom_toolbar_notification ); // root View id from that link
        TextView tv = topToolbar.findViewById(R.id.textView);
        tv.setText("Notification");
        //Button ib = bottomToolbar.findViewById(R.id.btnNotification);
        //ib.setBackgroundResource(R.drawable.notification_icon_hover_icon);

        //Buttons..............................................................................................................................
        View BottomToolbar = findViewById( R.id.bottom_toolbar_notification ); // root View id from that link
       // Button btnnoti = BottomToolbar.findViewById(R.id.btnNotification);
       // btnnoti.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent( NotificationActivity.this, NotificationActivity.class);
                startActivity(i);
            }
        });
        Button btnage = BottomToolbar.findViewById(R.id.btnAgenda);
        btnage.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent( NotificationActivity.this, AgendaActivity.class);
                startActivity(i);
            }
        });
        Button btnhome = BottomToolbar.findViewById(R.id.btnHome);
        btnhome.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent( NotificationActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
        Button btnQR = BottomToolbar.findViewById(R.id.btnQR);
        btnQR.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent( NotificationActivity.this, QRActivity.class);
                startActivity(i);
            }
        });
        Button btnLeaderBoard = BottomToolbar.findViewById(R.id.btnLeaderBoard);
        btnLeaderBoard.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent( NotificationActivity.this, LeaderBoard.class);
                startActivity(i);
            }
        });

        Button btnFaq = BottomToolbar.findViewById(R.id.btnFAQ);
        btnFaq.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent( NotificationActivity.this, FAQActivity.class);
                startActivity(i);
            }
        });
        //Buttons..............................................................................................................................End


    }
}*/
