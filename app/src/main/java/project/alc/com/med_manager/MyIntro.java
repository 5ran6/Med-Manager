package project.alc.com.med_manager;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;

/**
 * Created by 5ran6 on 26/03/18.
 */
public class MyIntro extends AppIntro {
    private int PERMISSION = 1;

    // DID NOT override onCreate. Used init
    @Override
    public void init(Bundle savedInstanceState) {
        if (ContextCompat.checkSelfPermission(MyIntro.this, Manifest.permission.VIBRATE) == PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();

        } else {
            requestVibrationPermission();
        }

        //adding the three slides for introduction app you can ad as many you needed
        addSlide(AppIntroSlider.newInstance(R.layout.app_intro1));
        addSlide(AppIntroSlider.newInstance(R.layout.app_intro2));
        addSlide(AppIntroSlider.newInstance(R.layout.app_intro3));

        // Show and Hide Skip and Done buttons
        showStatusBar(false);
        showSkipButton(true);

        // Turn vibration on and set intensity
        // You will need to add VIBRATE permission in Manifest file
        setVibrate(true);
        setVibrateIntensity(30);

        //Add animation to the intro slider
        setDepthAnimation();
    }


    private void requestVibrationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.VIBRATE)) {
            new AlertDialog.Builder(this).setTitle("Permission needed").setCancelable(false).setMessage("Permission needed for this activity").setPositiveButton("Permit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(MyIntro.this, new String[]{Manifest.permission.VIBRATE}, PERMISSION);

                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.VIBRATE}, PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    public void onSkipPressed() {
        // Do something here when users click or tap on Skip button.

        finish();

//          MENU LOGIN
//          send the intent extras to the HomeActivity for opening login Fragment
//          Intent intent = new Intent(this, HomeActivity.class);
//          startActivity(intent);

    }

    @Override
    public void onNextPressed() {
        // Do something here when users click or tap on Next button.
    }

    @Override
    public void onDonePressed() {
        // Do something here when users click or tap tap on Done button.
        finish();
//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);
    }

    @Override
    public void onSlideChanged() {
        // Do something here when slide is changed
    }
}