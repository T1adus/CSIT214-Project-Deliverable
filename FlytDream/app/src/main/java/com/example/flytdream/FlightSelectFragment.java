package com.example.flytdream;

import android.media.Image;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FlightSelectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FlightSelectFragment extends Fragment {
    //Field
    CoreActivity activity;
    BookingSession aBookingSession;
    RecyclerView flightDisplay;
    ImageButton confirmButton;
    int currentFlightPosition;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FlightSelectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FlightSelectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FlightSelectFragment newInstance(String param1, String param2) {
        FlightSelectFragment fragment = new FlightSelectFragment();
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
        View view = inflater.inflate(R.layout.fragment_flight_select, container, false);
        activity = (CoreActivity) getActivity();
        aBookingSession = activity.getBookingSession();
        currentFlightPosition = -1;
        flightDisplay = view.findViewById(R.id.flightDisplay);
        populateFlightDisplay();
        confirmButton = view.findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(clickListener);
        setHasOptionsMenu(true);
        return view;
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.confirmButton) {
                if (currentFlightPosition != -1) {
                    aBookingSession.getFlight().add(activity.flights.get(currentFlightPosition));
                    activity.currentPassenger = 0;
                    activity.createPassengerList();
                    if (aBookingSession.flightType.equals("Round Trip")) {
                        activity.loadFragment(new FlightReturnSelectFragment());
                    } else {
                        activity.loadFragment(new CustomerInfoFragment());
                    }
                } else {
                   Toast.makeText(getActivity(), "Please choose a flight to proceed!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    //Get the adapter from Core, which is now populated with an array of flight to be select
    public void populateFlightDisplay() {
        flightDisplay.setLayoutManager(new LinearLayoutManager(getActivity()));
        FlightAdapter adapter = activity.createFlight(aBookingSession.getDepartCity().getCityAlias(), aBookingSession.getArriveCity().getCityAlias());
        adapter.setOnItemClickListener(new FlightAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                currentFlightPosition = position;
            }
        });
        flightDisplay.setAdapter(adapter);
    }

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
            if (aBookingSession.getFlight().isEmpty() == false) {
                aBookingSession.getFlight().remove(0);
            }
            activity.loadFragment(new TripFragment());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}