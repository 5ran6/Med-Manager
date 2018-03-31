package project.alc.com.med_manager.others;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Exit.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Exit#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Exit extends Fragment {
    View view;
    FloatingActionButton home;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Exit() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Exit.
     */
    // TODO: Rename and change types and number of parameters
    public static Exit newInstance(String param1, String param2) {
        Exit fragment = new Exit();
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
        Context context = null;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        getActivity().setTitle("Leaving?");
//        view = inflater.inflate(R.layout.fragment_exit, container, false);
//
//        home = (FloatingActionButton) view.findViewById(R.id.home);
//        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nv);
//        View img = navigationView.getHeaderView(0);
//        Button yes = (Button) view.findViewById(R.id.yes);
//        Button no = (Button) view.findViewById(R.id.no);
//        final ImageView icon = (ImageView) img.findViewById(R.id.current_currency);
//
//        yes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //exits app
//                getActivity().finishAffinity();
//                Toast.makeText(getActivity(), "Bye", Toast.LENGTH_SHORT).show();
//            }
//        });
//        no.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////returns back to dashboard class
//                Toast.makeText(getActivity(), "Returning to Quick Exchange", Toast.LENGTH_LONG).show();
//
//                Dashboard dashboard = new Dashboard();
//                icon.setImageResource(R.drawable.coins);
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.flcontent, dashboard).commit();
//
//
//            }
//        });
//
//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getContext(), HomeActivity.class);
//                i.putExtra("done", "done");
//                startActivity(i);
//
//                getActivity().finish();
//            }
//        });
        getActivity().finish();
        return view;
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

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
