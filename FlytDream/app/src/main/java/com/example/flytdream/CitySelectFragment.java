package com.example.flytdream;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CitySelectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CitySelectFragment extends Fragment {
    //Field
    TextView cityType;
    CoreActivity activity;
    BookingSession aBookingSession;
    ArrayList<City> cities;
    ArrayList<String> cityInfo;
    EditText searchCity;
    ListView cityDisplay;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CitySelectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CitySelectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CitySelectFragment newInstance(String param1, String param2) {
        CitySelectFragment fragment = new CitySelectFragment();
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
        cities = new ArrayList<>();
        cityInfo = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_city_select, container, false);
        activity = (CoreActivity) getActivity();
        aBookingSession = activity.getBookingSession();

        cityType = view.findViewById(R.id.cityReturnType);
        if (activity.citySelectScreenController == 0) {
            cityType.setText("Depart");
        } else {
            cityType.setText("Arrive");
        }

        searchCity = view.findViewById(R.id.searchCity);
        searchCity.addTextChangedListener(changeListener);
        cityDisplay = view.findViewById(R.id.cityDisplay);
        cityDisplay.setOnItemClickListener(itemClickListener);
        populateCity("");

        setHasOptionsMenu(true);
        return view;
    }

    //Listen to the change in the user input to determine the city
    TextWatcher changeListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String input = editable.toString();
            populateCity(input);
        }
    };
    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            City city = cities.get(position);
            if (activity.citySelectScreenController == 0) {
                aBookingSession.setDepartCity(city);
                activity.loadFragment(new TripFragment());
            } else if (activity.citySelectScreenController == 1) {
                aBookingSession.setArriveCity(city);
                activity.loadFragment(new TripFragment());
            }
        }
    };

    //Populate the list with the city in the database based on the user input, default is all city
    public void populateCity(String input) {
        cities.clear();
        cityInfo.clear();
        Cursor res = activity.myDB.onSearchCity(input);
        while (res.moveToNext()) {
            City city = new City(res.getString(0), res.getString(1), res.getString(2));
            cities.add(city);
            cityInfo.add(city.getCityInfo());
        }
        ArrayAdapter<String> searchAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, cityInfo);
        cityDisplay.setAdapter(searchAdapter);
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
            activity.loadFragment(new TripFragment());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}