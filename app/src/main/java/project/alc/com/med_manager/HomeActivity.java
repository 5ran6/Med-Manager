package project.alc.com.med_manager;

/**
 * Created by 5ran6 on 24/10/17.
 */

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import project.alc.com.med_manager.medication.Doctor;
import project.alc.com.med_manager.medication.Medications;
import project.alc.com.med_manager.others.About;
import project.alc.com.med_manager.others.Exit;
import project.alc.com.med_manager.others.Profile;
import project.alc.com.med_manager.reminder.Appointment;
import project.alc.com.med_manager.reminder.Medication;

public class HomeActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ImageView curentCurrency;
    private TextView currentTextView;
    //  private SharedPreferences mSharedPreferences;


    RelativeLayout mainLay;
    Button btn_Add;
    ImageView img_cover;
    FrameLayout frameLayout;
    //RadioButton radio_btc, radio_eth;
    SharedPreferences sharedPref = null;
    SharedPreferences.Editor editor = null;
    Spinner base_spinner;
    private LinearLayout mLWelcome;
    private int animDuration = 200;
    private float sD;
    Resources res;
    Class fragmentClass = Home.class;
    Fragment myFragment = (Fragment) fragmentClass.newInstance();

    public HomeActivity() throws IllegalAccessException, InstantiationException {
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("What do you want to do?")
                .setCancelable(false)
                .setPositiveButton("Close app", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //                       Instruction.this.finish();
                        finishAffinity();
                    }
                })
                .setNegativeButton("CONTINUE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Intent i = new Intent(getApplicationContext(), Login.class);
                        //                      startActivity(i);
                    }
                })
                .show();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (savedInstanceState != null) {
            //Restore the fragment's instance
//            myFragment = getSupportFragmentManager().getFragment(savedInstanceState, myFragment.toString());
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nv);
        View img = navigationView.getHeaderView(0);

        //img_cover = (ImageView) findViewById(R.id.backdrop);
        frameLayout = (FrameLayout) findViewById(R.id.flcontent);
        //mainLay = (RelativeLayout) findViewById(R.id.main);
        curentCurrency = (ImageView) img.findViewById(R.id.current_currency);
        currentTextView = (TextView) img.findViewById(R.id.currentText);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpDrawerContent(navigationView);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flcontent, myFragment).commit();

        sharedPref = getSharedPreferences(getString(R.string.shared_pref), Context.MODE_PRIVATE);
        editor = sharedPref.edit();


    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // handle the preference change here i.e on delete
//        loadArray();
//        saveArray();
//        adapter.notifyDataSetChanged();

    }


    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save the fragment's instance
        final Fragment fragmentInFrame = getSupportFragmentManager().findFragmentById(R.id.flcontent);
        getSupportFragmentManager().putFragment(outState, fragmentInFrame.getTag(), fragmentInFrame);
    }

    private void setUpDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);

                return true;
            }
        });
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void selectItemDrawer(MenuItem menuItem) {
        myFragment = null;
        switch (menuItem.getItemId()) {
            case R.id.home:
                fragmentClass = Home.class;
                curentCurrency.setImageResource(R.drawable.ic_home_black_24dp);
                currentTextView.setText("");
                break;
            case R.id.med:
                // frameLayout.setVisibility(View.GONE);
                fragmentClass = Medication.class;
                curentCurrency.setImageResource(R.drawable.ic_home_black_24dp);
                currentTextView.setText("");

                break;
            case R.id.doc_app:
                //frameLayout.setVisibility(View.GONE);
                curentCurrency.setImageResource(R.drawable.doctor);
                currentTextView.setText("");

                fragmentClass = Appointment.class;
                break;
            case R.id.meds:
                //frameLayout.setVisibility(View.GONE);
                curentCurrency.setImageResource(R.drawable.doctor);
                currentTextView.setText("");
                fragmentClass = Medications.class;
                break;
            case R.id.doc:
                //frameLayout.setVisibility(View.GONE);
                fragmentClass = Doctor.class;
                curentCurrency.setImageResource(R.drawable.doctor);
                currentTextView.setText("");
                break;
            case R.id.profile:
                //frameLayout.setVisibility(View.GONE);
                fragmentClass = Profile.class;
                curentCurrency.setImageResource(R.drawable.doctor);
                currentTextView.setText("");
                break;
            case R.id.about:
                //  frameLayout.setVisibility(View.GONE);
                fragmentClass = About.class;
                curentCurrency.setImageResource(R.drawable.doctor);
                currentTextView.setText("");
                break;
            case R.id.exit:
                fragmentClass = Exit.class;
                curentCurrency.setImageResource(R.drawable.ic_home_black_24dp);
                currentTextView.setText("");
                break;
            default:
                fragmentClass = HomeActivity.class;

        }
        try {
            myFragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flcontent, myFragment).commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getItemId());
        mDrawerLayout.closeDrawers();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

