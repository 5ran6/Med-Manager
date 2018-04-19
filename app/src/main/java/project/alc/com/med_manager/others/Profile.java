package project.alc.com.med_manager.others;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.io.ByteArrayOutputStream;

import project.alc.com.med_manager.HomeActivity;
import project.alc.com.med_manager.R;
import project.alc.com.med_manager.database.DatabaseHelperProfile;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Profile.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    private static final int REQ_CODE = 9001;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static DatabaseHelperProfile sQliteHelper;
    GoogleSignInAccount account;
    String Name;
    String Email;
    View view;
    private LinearLayout profile_section;
    private Button SignOut, done;
    private SignInButton SignIn;
    private TextView name, email, currentTextView;
    private ImageView passport, profile;
    private GoogleApiClient googleApiClient;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Profile");
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        sQliteHelper = new DatabaseHelperProfile(getContext(), "profile.sqlite", null, 1);
        //       sQliteHelper.onCreate("CREATE TABLE IF NOT EXISTS PROFILE (Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, email VARCHAR, image BLOB)");

        profile_section = (LinearLayout) view.findViewById(R.id.profile_section);
        currentTextView = (TextView) getActivity().findViewById(R.id.currentText);

        name = (TextView) view.findViewById(R.id.name);
        email = (TextView) view.findViewById(R.id.email);
        SignIn = (SignInButton) view.findViewById(R.id.sign_in_button);
        SignOut = (Button) view.findViewById(R.id.sign_out_button);
        done = (Button) view.findViewById(R.id.done);
        passport = (ImageView) view.findViewById(R.id.profile_pic);

        profile = (ImageView) getActivity().findViewById(R.id.current_currency);

        passport.setDrawingCacheEnabled(true);
        passport.buildDrawingCache();
        done.setOnClickListener(this);
        SignIn.setOnClickListener(this);
        SignOut.setOnClickListener(this);
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(getActivity().getApplicationContext()).enableAutoManage((FragmentActivity) getContext(), this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();
        profile_section.setVisibility(View.GONE);
        return view;


    }

    public void signIn() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REQ_CODE);
    }

    public void signOut() {

        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });
    }

    public void handleResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            try {
                account = result.getSignInAccount();
                Name = account.getDisplayName();
                Email = account.getEmail();
                String img_url = account.getPhotoUrl().toString();
                Glide.with(this).load(img_url).asBitmap().into(new BitmapImageViewTarget(passport) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        super.onResourceReady(resource, glideAnimation);
                        //convert the drawable to byte[] image and save to database
                        Drawable d = passport.getDrawable().getCurrent();
                        BitmapDrawable bitmapDrawable = ((BitmapDrawable) d);
                        Bitmap bitmap = bitmapDrawable.getBitmap();

                        //set the profile picture
                        profile.setImageBitmap(bitmap);
                        currentTextView.setText(Name + "\n" + Email);

                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                        byte[] imageInByte = byteArrayOutputStream.toByteArray();

                        //add to database
                        sQliteHelper.insertData(Name.toString(), Email.toString(), imageInByte);
                        //     Toast.makeText(getContext(), "Added to db successfully!", Toast.LENGTH_SHORT).show();
                    }
                });
                name.setText(Name);
                email.setText(Email);
                updateUI(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            updateUI(false);
        }
    }

    public void updateUI(boolean isLogin) {
//close googlee api
//        googleApiClient.disconnect();
        if (isLogin) {
//            progressDialog.dismiss();
            profile_section.setVisibility(View.VISIBLE);
            SignIn.setVisibility(View.GONE);
            try {
                //add to database
                //        sQliteHelper.insertData(Name, Email, imageViewToByte(passport));
                //     Toast.makeText(getContext(), "Added to db successfully!", Toast.LENGTH_SHORT).show();

            } catch (SQLiteCantOpenDatabaseException d) {
                d.printStackTrace();

            }
        } else {

            profile_section.setVisibility(View.GONE);
            SignIn.setVisibility(View.VISIBLE);
            sQliteHelper.deleteAll();
            //  Toast.makeText(getContext(), "Dropped Table successfully!", Toast.LENGTH_SHORT).show();

        }

    }

    private byte[] imageViewToByte(ImageView image) {
        Bitmap bm = passport.getDrawingCache();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
        final byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
            result.getSignInAccount().getPhotoUrl();

//            progressDialog.setMessage("Logging in");
//            progressDialog.setCancelable(false);
//            progressDialog.setTitle("Processing");
//            progressDialog.show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.sign_out_button:
                signOut();
                break;
            case R.id.done:
                Intent i = new Intent(getContext(), HomeActivity.class);
                startActivity(i);
                getActivity().finish();
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        googleApiClient.stopAutoManage(getActivity());
        googleApiClient.disconnect();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
