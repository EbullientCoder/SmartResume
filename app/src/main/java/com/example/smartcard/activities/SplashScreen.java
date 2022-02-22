package com.example.smartcard.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.smartcard.R;
import com.example.smartcard.database.dao.CurriculumDAO;
import com.example.smartcard.database.dao.PortfolioDAO;
import com.example.smartcard.database.dao.TeamDAO;
import com.example.smartcard.database.dao.UserDAO;
import com.example.smartcard.facade.GetUser;
import com.example.smartcard.model.CVModel;
import com.example.smartcard.model.ProjectModel;
import com.example.smartcard.model.TeamMemberModel;
import com.example.smartcard.model.UserModel;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SplashScreen extends AppCompatActivity {

    //Shared variable between threads
    private UserModel user;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            Intent intent = new Intent(getApplicationContext(), Main.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("User", user);

            startActivity(intent);

            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Window window = getWindow();

        //Make Status Bar Translucent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        //Remove Button Bottom Bar
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);




        //Allow JDBC usage
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Get the User from Online DB
        //The DB connection is Synchronous, so it block the calling thread until it finishes.
        //We don't want that to happen to the Main Thread, cause it has to run the UI.
        //If the Main Thread is stuck in an operation the UI will not respond.
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                GetUser getUser = new GetUser();
                user = getUser.getUser();

                handler.sendEmptyMessage(0);
            }
        };

        Thread threadDB = new Thread(runnable);
        threadDB.start();

        //Call the Main Activity
        /*new Handler().postDelayed(() -> {
            Intent intent = new Intent(getApplicationContext(), Main.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("User", user);

            startActivity(intent);
        }, 4000);*/
    }
}