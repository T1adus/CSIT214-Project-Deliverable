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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TripFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TripFragment extends Fragment {
    CoreActivity activity;
    private BookingSession aBookingSession;
    private TextView onewayTextView;
    private TextView roundtripTextView;
    private TextView multicityTextView;
    private TextView userDepartCity;
    private TextView userArriveCity;
    private ImageView citySwitch;
    private Spinner classSelect;
    private ImageButton searchFlightButton;
    private TextView dateSelection;
    private TextView returnselection;
    private TextView passengerInput;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public TripFragment() {
        // Required empty public constructor
    }

    public static TripFragment newInstance(String param1, String param2) {
        TripFragment fragment = new TripFragment();
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
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_trip, container, false);
        activity = (CoreActivity) getActivity();
        aBookingSession = activity.getBookingSession();

        onewayTextView = view.findViewById(R.id.onewayTextView);
        onewayTextView.setOnClickListener(clickListener);
        roundtripTextView = view.findViewById(R.id.roundtripTextView);
        roundtripTextView.setOnClickListener(clickListener);
        multicityTextView = view.findViewById(R.id.multicityTextView);
        multicityTextView.setOnClickListener(clickListener);

        userDepartCity = view.findViewById(R.id.userDepartCity);
        userDepartCity.setOnClickListener(clickListener);
        userArriveCity = view.findViewById(R.id.userArriveCity);
        userArriveCity.setOnClickListener(clickListener);

        citySwitch = view.findViewById(R.id.citySwitch);
        citySwitch.setOnClickListener(clickListener);

        classSelect = view.findViewById(R.id.classSelect);
        classSelect.setOnItemSelectedListener(itemClickListener);

        dateSelection = view.findViewById(R.id.dateSelection);
        dateSelection.setOnClickListener(clickListener);
        returnselection = view.findViewById(R.id.returnSelection);
        returnselection.setOnClickListener(clickListener);
        multicityTextView = view.findViewById(R.id.multicityTextView);
        multicityTextView.setOnClickListener(clickListener);

        passengerInput = view.findViewById(R.id.passengerInput);
        passengerInput.setOnClickListener(clickListener);
        searchFlightButton = view.findViewById(R.id.searchFlightButton);
        searchFlightButton.setOnClickListener(clickListener);

        inflateSpinner();
        getData();

        return view;
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.searchFlightButton) {
                if (validateData() == true) {
                    activity.loadFragment(new FlightSelectFragment());
                }
            } else if (v.getId() == R.id.citySwitch) {
                if (!userDepartCity.getText().equals("") && !userArriveCity.getText().equals("")){
                    switchCity();
                }
            } else if (v.getId() == R.id.dateSelection) {
                activity.loadFragment(new CalendarFragment());
            } else if (v.getId() == R.id.passengerInput) {
                activity.loadFragment(new PassengerCountFragment());
            } else if (v.getId() == R.id.userDepartCity) {
                activity.citySelectScreenController = 0;
                activity.loadFragment(new CitySelectFragment());
            } else if (v.getId() == R.id.userArriveCity) {
                activity.citySelectScreenController = 1;
                activity.loadFragment(new CitySelectFragment());
            } else if (v.getId() == R.id.onewayTextView) {
                onewayTextView.setBackgroundResource(R.drawable.selected_button_background);
                onewayTextView.setTextColor(getResources().getColor(R.color.white));
                roundtripTextView.setBackgroundResource(R.drawable.unselected_button_background);
                roundtripTextView.setTextColor(getResources().getColor(R.color.black));
                returnselection.setText("Not Selectable");
                aBookingSession.flightType = "One Way";
            } else if (v.getId() == R.id.roundtripTextView) {
                roundtripTextView.setBackgroundResource(R.drawable.selected_button_background);
                roundtripTextView.setTextColor(getResources().getColor(R.color.white));
                onewayTextView.setBackgroundResource(R.drawable.unselected_button_background);
                onewayTextView.setTextColor(getResources().getColor(R.color.black));
                returnselection.setText("Select a date");
                aBookingSession.flightType = "Round Trip";
            } else if (v.getId() == R.id.multicityTextView) {
                Toast.makeText(getActivity(), "Not Applicable", Toast.LENGTH_SHORT).show();
            }
        }
    };

    AdapterView.OnItemSelectedListener itemClickListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            aBookingSession.setClassId(position);
            String classSelected = classSelect.getSelectedItem().toString();
            aBookingSession.setClass(classSelected);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    public void getData() {
        String departCity = aBookingSession.getDepartCity().getCityAlias();
        String arriveCity = aBookingSession.getArriveCity().getCityAlias();

        if (!departCity.equals("0")) {
            userDepartCity.setText(departCity);
        }

        if (!arriveCity.equals("0")) {
            userArriveCity.setText(arriveCity);
        }

        String date = aBookingSession.getDate();
        if (!date.equals("")) {
            dateSelection.setText(date);
        }

        int classId = aBookingSession.getClassId();
        classSelect.setSelection(classId);

        int[] passenger = aBookingSession.getPassenger();
        int adult = passenger[0];
        int child = passenger[1];
        int infant = passenger[2];

        if (adult != 0 || child != 0 || infant != 0) {
            String adultCount = "";
            String childCount = "";
            String infantCount = "";

            if (adult > 0) {
                if (child > 0 || infant > 0) {
                    adultCount = String.format("%d Adults,", adult);
                } else {
                    adultCount = String.format("%d Adults", adult);
                }
            }

            if (child > 0) {
                if (infant > 0) {
                    childCount = String.format("%d Kids,", child);
                } else {
                    childCount = String.format("%d Kids", child);
                }
            }

            if (infant > 0) {
                infantCount = String.format("%d Infants", infant);
            }

            String passengerDisplay = String.format("%s %s %s", adultCount, childCount, infantCount);
            passengerInput.setText(passengerDisplay);
        }
    }

    public boolean validateData() {
        if (aBookingSession.getDepartCity().getCityAlias().equals("0")) {
            Toast.makeText(getActivity(), "Please select a city to depart!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (aBookingSession.getArriveCity().getCityAlias().equals("0")) {
            Toast.makeText(getActivity(), "Please select a city to arrive!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!aBookingSession.getDepartCity().getCityAlias().equals("0")) {
            if (aBookingSession.getDepartCity().getCityAlias().equals(aBookingSession.getArriveCity().getCityAlias())) {
                Toast.makeText(getActivity(), "Destination can not be similar to departure!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        if (aBookingSession.getDate().equals("")) {
            Toast.makeText(getActivity(), "Please select a depart date!", Toast.LENGTH_SHORT).show();
            return false;
        }

        int[] passengers = aBookingSession.getPassenger();
        if (passengers[0] == 0 && passengers[1] == 0 && passengers[2] == 0) {
            Toast.makeText(getActivity(), "Please input number of passengers!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (aBookingSession.getClassId() == 0) {
            Toast.makeText(getActivity(), "Please select a class!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void switchCity() {
        City depart = aBookingSession.getDepartCity();
        City arrive = aBookingSession.getArriveCity();
        City temp = depart;
        aBookingSession.setDepartCity(arrive);
        aBookingSession.setArriveCity(temp);

        String departCity = aBookingSession.getDepartCity().getCityAlias();
        String arriveCity = aBookingSession.getArriveCity().getCityAlias();

        if (!departCity.equals("0")) {
            userDepartCity.setText(departCity);
        }

        if (!arriveCity.equals("0")) {
            userArriveCity.setText(arriveCity);
        }

        String date = aBookingSession.getDate();
        if (!date.equals("")) {
            dateSelection.setText(date);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.top_nav_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    public void inflateSpinner() {
        String[] classList = new String[] {"Class", "Economy", "Premium Economy", "Business"};

        ArrayAdapter<String> classes = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                classList
        );

        classes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSelect.setAdapter(classes);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        if (item.getItemId() == R.id.action_profile) {
            Toast.makeText(this.getActivity(), "Yo!", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == android.R.id.home) {
            Toast.makeText(this.getActivity(), "Goodbye!", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}