package net.keecode.zanukamobile.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import net.keecode.zanukamobile.R;
import net.keecode.zanukamobile.manager.PermissionManager;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if(PermissionManager.areAllPermissionsGranted(this))
            launchApplication();
        else
            PermissionManager.requestPermissions(this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PermissionManager.REQUESTCODE: {
                // If request is cancelled, the result arrays are empty.
                boolean everythingGranted = true;

                for (int i : grantResults) {
                    if (i != PackageManager.PERMISSION_GRANTED)
                        everythingGranted = false;
                }
                if (grantResults.length > 0 && everythingGranted) {

                    if (snackbar != null)
                        snackbar.dismiss();
                    launchApplication();

                } else {
                    snackbar = Snackbar
                            .make(findViewById(R.id.splash_layout), "Berechtigungen erforderlich!", Snackbar.LENGTH_INDEFINITE)
                            .setAction("ERTEILEN", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    PermissionManager.requestPermissions(SplashScreen.this);
                                }
                            });

                    snackbar.show();
                }
                return;
            }
        }
    }


    private void launchApplication() {

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent intent = new Intent(SplashScreen.this.getApplicationContext(), SetupActivity.class);
                startActivity(intent);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);


    }
}
