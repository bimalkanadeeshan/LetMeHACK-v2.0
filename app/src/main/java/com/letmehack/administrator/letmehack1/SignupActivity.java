package com.letmehack.administrator.letmehack1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class SignupActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnBack;
    ProgressBar progressBar;

    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        overridePendingTransition(0, 0);

        email = findViewById(R.id.lblEmail);
        progressBar = findViewById(R.id.progressBar2);

        //back activity
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignupActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });

        //load qr and sharedPreferences thing
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//https://letmehack.lk/letmeapp/api/stats
                String url = "https://letmehack.lk/letmeapp/api/members?email=" + email.getText().toString();
                Log.e("Email: ", url);

                if (checkInternetConnection(SignupActivity.this)) {
                    try {
                        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressBar.setVisibility(View.VISIBLE);
                        Ion.with(SignupActivity.this)
                                .load(url).progressBar(progressBar)
                                .asJsonObject()
                                .setCallback(new FutureCallback<JsonObject>() {
                                    @Override
                                    public void onCompleted(Exception e, JsonObject result) {
                                        progressBar.setVisibility(View.INVISIBLE);
                                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                        // do stuff with the result or error
                                        if (e == null) {
                                            Log.e("JSON", result.toString());
                                            if (result.has("success")) {
                                                if (result.get("success").getAsString().contentEquals("false")) {
                                                    Toast.makeText(SignupActivity.this, "Error..Please Check your Email Again!.", Toast.LENGTH_LONG).show();
                                                }

                                            } else if (result.has("name")) {

                                                //setting sharedpreferences
                                                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                                                SharedPreferences.Editor editor = pref.edit();

                                                //Store Values
                                                editor.putString("name", result.get("name").getAsString());
                                                editor.putString("email", result.get("email").getAsString());
                                                editor.putString("phone", result.get("phone").getAsString());
                                                editor.putString("team", result.get("team").getAsString());
                                                editor.putString("university", result.get("university").getAsString());
                                                editor.putString("tshirt_size", result.get("tshirt_size").getAsString());
                                                editor.putString("meal_type", result.get("meal_type").getAsString());
                                                editor.putString("qr_code", result.get("qr_code").getAsString());
                                                editor.putString("photo", result.get("photo").getAsString());
                                                editor.putBoolean("registered", result.get("registered").getAsBoolean());
                                                editor.commit();
                                                String qr_code = pref.getString("qr_code", null);
                                                Log.e("Qr_Code ", qr_code);


                                                Toast.makeText(SignupActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                                Intent i = new Intent(SignupActivity.this, HomeActivity.class);
                                                startActivity(i);
                                                finish();
                                            }

                                        } else

                                        {
                                            Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                            Log.e("Exception", e.getMessage());
                                        }

                                    }
                                });
                    }catch (Exception e){
                        Log.e("Exception = ", e.getMessage());
                    }
                } else
                    Toast.makeText(SignupActivity.this, "No Internet Connection", Toast.LENGTH_LONG).

                            show();


            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(SignupActivity.this, HomeActivity.class);
        startActivity(i);
        finish();
    }

//    private class DownloadFilesTask extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//        }
//
//
//        @Override
//        protected Void doInBackground(Void... voids) {
////            JsonObjectRequest jsObjRequest = new JsonObjectRequest
////                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
////
////                        @Override
////                        public void onResponse(JSONObject response) {
////                            mTxtDisplay.setText("Response: " + response.toString());
////                        }
////                    }, new Response.ErrorListener() {
////
////                        @Override
////                        public void onErrorResponse(VolleyError error) {
////                            // TODO Auto-generated method stub
////
////                        }
////                    });
//
//            return null;
//        }
//    }


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
