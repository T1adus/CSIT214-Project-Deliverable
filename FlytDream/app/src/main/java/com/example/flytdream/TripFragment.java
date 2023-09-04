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
    private BookingSession aBookingSession;
    Spinner departureCitySelect;
    Spinner arrivalCitySelect;
    ImageView citySwitch;
    Spinner classSelect;
    ImageButton searchFlightButton;
    TextView dateSelection;
    TextView passengerInput;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TripFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TripFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        CoreActivity activity = (CoreActivity) getActivity();
        aBookingSession = activity.getBookingSession();
        departureCitySelect = view.findViewById(R.id.departureCitySelect);
        arrivalCitySelect = view.findViewById(R.id.arrivalCitySelect);
        citySwitch = view.findViewById(R.id.citySwitch);
        citySwitch.setOnClickListener(clickListener);
        classSelect = view.findViewById(R.id.classSelect);
        dateSelection = view.findViewById(R.id.dateSelection);
        passengerInput = view.findViewById(R.id.passengerInput);
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
                boolean status = setData();
                if (status == true) {

                }
            } else if (v.getId() == R.id.citySwitch) {
                switchCity();
            }
        }
    };

    public void getData() {
        long departureCityId = aBookingSession.getDepartureCityId();
        departureCitySelect.setSelection((int)departureCityId);

        long arrivalCityId = aBookingSession.getArrivalCityId();
        arrivalCitySelect.setSelection((int)arrivalCityId);

        String date = aBookingSession.getDate();
        if (!date.equals("")) {
            dateSelection.setText(date);
        }

        long classId = aBookingSession.getClassId();
        classSelect.setSelection((int)classId);

        int[] passenger = aBookingSession.getPassenger();
        int adult = passenger[0];
        int child = passenger[1];
        int infant = passenger[2];

        if (adult != 0 || child != 0 || infant != 0) {
            String passengerDisplay = String.format("%d Adults, %d Kids, %d Infants", adult, child, infant);
            passengerInput.setText(passengerDisplay);
        }
    }

    public boolean setData() {
        long departureCityId = departureCitySelect.getSelectedItemId();
        long arrivalCityId = arrivalCitySelect.getSelectedItemId();
        if (departureCityId != 0) {
            if (arrivalCityId != 0) {
                if (departureCityId != arrivalCityId) {
                    aBookingSession.setDepartureCity(departureCityId);
                    aBookingSession.setArrivalCityId(arrivalCityId);
                } else {
                    Toast.makeText(getActivity(), "Departure and arrival city can not be the same!", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {
                Toast.makeText(getActivity(), "Please select a city to arrive!", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(getActivity(), "Please select a city to depart!", Toast.LENGTH_SHORT).show();
            return false;
        }

        String date = dateSelection.getText().toString();
        if (date.equals("Select a date")) {
            Toast.makeText(getActivity(), "Please select a date!", Toast.LENGTH_SHORT).show();
            return false;
        }

        long classId = classSelect.getSelectedItemId();
        if (classId != 0) {
            aBookingSession.setClassId(classId);
            String classSelected = classSelect.getSelectedItem().toString();
            aBookingSession.setClass(classSelected);
        } else {
            Toast.makeText(getActivity(), "Please select a class!", Toast.LENGTH_SHORT).show();
            return false;
        }

        String passenger = passengerInput.getText().toString();
        if (passenger.equals("Input number of passenger")) {
            Toast.makeText(getActivity(), "Please input the number of passenger!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void switchCity() {
        long departureCityId = departureCitySelect.getSelectedItemId();
        long arrivalCityId = arrivalCitySelect.getSelectedItemId();
        long temp = departureCityId;
        departureCityId = arrivalCityId;
        arrivalCityId = temp;

        departureCitySelect.setSelection((int) departureCityId);
        arrivalCitySelect.setSelection((int) arrivalCityId);
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
        String[] cityList = new String[] {"Select a city","Sydney", "Melbourne", "Hanoi", "HCM"};

        ArrayAdapter<String> cities = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                cityList
        );

        cities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departureCitySelect.setAdapter(cities);
        arrivalCitySelect.setAdapter(cities);

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