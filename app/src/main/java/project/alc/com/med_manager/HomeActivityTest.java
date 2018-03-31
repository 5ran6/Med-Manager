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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import project.alc.com.med_manager.medication.Doctor;
import project.alc.com.med_manager.medication.Medications;
import project.alc.com.med_manager.others.About;
import project.alc.com.med_manager.others.Profile;
import project.alc.com.med_manager.reminder.Appointment;
import project.alc.com.med_manager.reminder.Medication;

public class HomeActivityTest extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ImageView curentCurrency;
    private TextView currentTextView;
    //  private SharedPreferences mSharedPreferences;


    RelativeLayout mainLay;
    Button btn_Add;
    ImageView img_cover;
    RadioButton radio_btc, radio_eth;
    SharedPreferences sharedPref = null;
    SharedPreferences.Editor editor = null;
    Spinner base_spinner;
    private LinearLayout mLWelcome;
    private int animDuration = 200;
    private float sD;
    Resources res;
    Fragment myFragment = null;

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

       // img_cover = (ImageView) findViewById(R.id.backdrop);

        mainLay = (RelativeLayout) findViewById(R.id.main);
        curentCurrency = (ImageView) img.findViewById(R.id.current_currency);
        currentTextView = (TextView) img.findViewById(R.id.currentText);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpDrawerContent(navigationView);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        // initCollapsingToolbar();

        //let's have an exit back button in our homePage
//        ActionBar ab = getSupportActionBar();
//        if (ab != null) {
//            ab.setDisplayHomeAsUpEnabled(true);
//        }


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
//    private void initCollapsingToolbar() {
//        final CollapsingToolbarLayout collapsingToolbar =
//                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbar.setTitle(" ");
//        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
//        appBarLayout.setExpanded(true);
//
//        // hiding & showing the title when toolbar expanded & collapsed
//        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            boolean isShow = false;
//            int scrollRange = -1;
//
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                if (scrollRange == -1) {
//                    scrollRange = appBarLayout.getTotalScrollRange();
//                }
//                if (scrollRange + verticalOffset == 0) {
//                    collapsingToolbar.setTitle(getString(R.string.app_name));
//                    isShow = true;
//                } else if (isShow) {
//                    collapsingToolbar.setTitle(" ");
//                    isShow = false;
//                }
//            }
//        });
//    }

    /**
     * Adding/Removing currency comparison menu
     */

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save the fragment's instance
//        final Fragment fragmentInFrame = getSupportFragmentManager().findFragmentById(R.id.flcontent);
//
//          getSupportFragmentManager().putFragment(outState, fragmentInFrame.getTag(), fragmentInFrame);
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
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.med:
                fragmentClass = Medication.class;
                curentCurrency.setImageResource(R.drawable.ic_home_black_24dp);
                currentTextView.setText("");
//                try {
//                    Glide.with(this).load(R.drawable.background_img).into(img_cover);
//                    base_spinner.setVisibility(View.GONE);
//                    btn_Add.setVisibility(View.GONE);
//                    radio_btc.setVisibility(View.GONE);
//                    radio_eth.setVisibility(View.GONE);
//                    recyclerView.setVisibility(View.GONE);
//
//                } catch (Exception e) {
//                }

                break;
            case R.id.doc_app:
                curentCurrency.setImageResource(R.drawable.doctor);
                currentTextView.setText("");
//                try {
//                    Glide.with(this).load(R.drawable.background_img).into(img_cover);
//                    base_spinner.setVisibility(View.GONE);
//                    btn_Add.setVisibility(View.GONE);
//                    radio_btc.setVisibility(View.GONE);
//                    radio_eth.setVisibility(View.GONE);
//                    recyclerView.setVisibility(View.GONE);
//
//                } catch (Exception e) {
//                }
                fragmentClass = Appointment.class;
                break;
            case R.id.meds:
                curentCurrency.setImageResource(R.drawable.doctor);
                currentTextView.setText("");
////              curentCurrency.setImageDrawable(ContextCompat.getDrawable(Launcher.this, R.drawable.eth_icon));
//                try {
////mainLay.setVisibility(View.GONE);
//                    Glide.with(this).load(R.drawable.background_img).into(img_cover);
//                    base_spinner.setVisibility(View.GONE);
//                    btn_Add.setVisibility(View.GONE);
//                    radio_btc.setVisibility(View.GONE);
//                    radio_eth.setVisibility(View.GONE);
//                    recyclerView.setVisibility(View.GONE);
//
//                } catch (Exception e) {
//                }
                fragmentClass = Medications.class;
                break;
            case R.id.doc:
                fragmentClass = Doctor.class;
                curentCurrency.setImageResource(R.drawable.doctor);
                currentTextView.setText("");
//                try {
//                    Glide.with(this).load(R.drawable.background_img).into(img_cover);
//                    base_spinner.setVisibility(View.GONE);
//                    btn_Add.setVisibility(View.GONE);
//                    radio_btc.setVisibility(View.GONE);
//                    radio_eth.setVisibility(View.GONE);
//                    recyclerView.setVisibility(View.GONE);
//
//                } catch (Exception e) {
//                }

                break;
            case R.id.profile:
                fragmentClass = Profile.class;
                curentCurrency.setImageResource(R.drawable.doctor);
                currentTextView.setText("");
//                try {
//                    Glide.with(this).load(R.drawable.background_img).into(img_cover);
//                    base_spinner.setVisibility(View.GONE);
//                    btn_Add.setVisibility(View.GONE);
//                    radio_btc.setVisibility(View.GONE);
//                    radio_eth.setVisibility(View.GONE);
//                    recyclerView.setVisibility(View.GONE);
//
//                } catch (Exception e) {
//                }

                break;
            case R.id.about:
                fragmentClass = About.class;
                curentCurrency.setImageResource(R.drawable.doctor);
                currentTextView.setText("");
//                try {
//                    Glide.with(this).load(R.drawable.background_img).into(img_cover);
//                    base_spinner.setVisibility(View.GONE);
//                    btn_Add.setVisibility(View.GONE);
//                    radio_btc.setVisibility(View.GONE);
//                    radio_eth.setVisibility(View.GONE);
//                    recyclerView.setVisibility(View.GONE);
//
//                } catch (Exception e) {
//                }

                break;
            case R.id.exit:
                finishAffinity();
                fragmentClass = Appointment.class;
//                curentCurrency.setImageResource(R.drawable.ic_home_black_24dp);
//                currentTextView.setText("");
//             //   Glide.with(this).load(R.drawable.background_img).into(img_cover);
//                base_spinner.setVisibility(View.GONE);
//                btn_Add.setVisibility(View.GONE);
//                radio_btc.setVisibility(View.GONE);
//                radio_eth.setVisibility(View.GONE);
//                //recyclerView.setVisibility(View.GONE);
//                //     Glide.with(this).onDestroy();

//
//                //mainLay.setBackground(getDrawable(this, R.drawable.background_img));
//                try {
//                    //                 Glide.with(this).load(R.drawable.background_img).into(img_cover);
//                    //mainLay.setBackground(getDrawable(getApplicationContext(), R.drawable.background_img));
//                    //      mainLay.setVisibility(View.GONE);
//                    base_spinner.setVisibility(View.GONE);
//                    btn_Add.setVisibility(View.GONE);
//                    radio_btc.setVisibility(View.GONE);
//                    radio_eth.setVisibility(View.GONE);
//                    recyclerView.setVisibility(View.GONE);
//                    Glide.with(this).onDestroy();
//
//
//                } catch (Exception e) {
//                }
                break;
            default:
                fragmentClass = HomeActivityTest.class;

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


    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


//        try {
//            myFragment = (Fragment) fragmentClass.newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.flcontent, myFragment).commit();
//        menuItem.setChecked(true);
//        setTitle(menuItem.getItemId());
//        mDrawerLayout.closeDrawers();

}

