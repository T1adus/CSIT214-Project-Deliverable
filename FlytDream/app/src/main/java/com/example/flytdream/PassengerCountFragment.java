package com.example.flytdream;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PassengerCountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PassengerCountFragment extends Fragment {
    private CoreActivity activity;
    private BookingSession aBookingSession;
    private TextView adultCount;
    private ImageButton adultAdd;
    private ImageButton adultMinus;
    private TextView childCount;
    private ImageButton childAdd;
    private ImageButton childMinus;
    private TextView infantCount;
    private ImageButton infantAdd;
    private ImageButton infantMinus;
    private ImageButton confirmButton;

    private int adult;
    private int child;
    private int infant;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PassengerCountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PassengerCountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PassengerCountFragment newInstance(String param1, String param2) {
        PassengerCountFragment fragment = new PassengerCountFragment();
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
        View view = inflater.inflate(R.layout.fragment_passenger_count, container, false);
        setHasOptionsMenu(true);
        activity = (CoreActivity) getActivity();
        aBookingSession = activity.getBookingSession();

        int[] passengers = aBookingSession.getPassenger();

        adult = passengers[0];
        child = passengers[1];
        infant = passengers[2];

        adultCount = view.findViewById(R.id.adultCount);
        adultAdd = view.findViewById(R.id.adultAdd);
        adultMinus = view.findViewById(R.id.adultMinus);
        adultAdd.setOnClickListener(clickListener);
        adultMinus.setOnClickListener(clickListener);

        childCount = view.findViewById(R.id.childCount);
        childAdd = view.findViewById(R.id.childAdd);
        childMinus = view.findViewById(R.id.childMinus);
        childAdd.setOnClickListener(clickListener);
        childMinus.setOnClickListener(clickListener);

        infantCount = view.findViewById(R.id.infantCount);
        infantAdd = view.findViewById(R.id.infantAdd);
        infantMinus = view.findViewById(R.id.infantMinus);
        infantAdd.setOnClickListener(clickListener);
        infantMinus.setOnClickListener(clickListener);

        adultCount.setText(Integer.toString(adult));
        childCount.setText(Integer.toString(child));
        infantCount.setText(Integer.toString(infant));

        confirmButton = view.findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(clickListener);

        setHasOptionsMenu(true);

        return view;
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.adultAdd) {
                adult += 1;
                adultCount.setText(Integer.toString(adult));
            } else if (v.getId() == R.id.adultMinus) {
                if (adult > 0) {
                    adult -= 1;
                    adultCount.setText(Integer.toString(adult));
                }
            } else if (v.getId() == R.id.childAdd) {
                child += 1;
                childCount.setText(Integer.toString(child));
            } else if (v.getId() == R.id.childMinus) {
                if (child > 0) {
                    child -= 1;
                    childCount.setText(Integer.toString(child));
                }
            } else if (v.getId() == R.id.infantAdd) {
                infant += 1;
                infantCount.setText(Integer.toString(infant));
            } else if (v.getId() == R.id.infantMinus) {
                if (infant > 0) {
                    infant -= 1;
                    infantCount.setText(Integer.toString(infant));
                }
            } else if (v.getId() == R.id.confirmButton) {
                aBookingSession.setPassenger(adult, child, infant);
                activity.loadFragment(new TripFragment());
            }
        }
    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.top_nav_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        if (item.getItemId() == R.id.action_profile) {
            Toast.makeText(this.getActivity(), "Yo!", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == android.R.id.home) {
            activity.loadFragment(new TripFragment());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}