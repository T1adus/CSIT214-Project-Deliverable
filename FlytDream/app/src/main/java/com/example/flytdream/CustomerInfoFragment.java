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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomerInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerInfoFragment extends Fragment {
    CoreActivity activity;
    BookingSession aBookingSession;
    TextView passengerId;
    EditText nameEdit;
    EditText passportEdit;
    ImageButton nextButton;
    CheckBox extraBaggageStatus;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CustomerInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CustomerInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomerInfoFragment newInstance(String param1, String param2) {
        CustomerInfoFragment fragment = new CustomerInfoFragment();
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
        View view =  inflater.inflate(R.layout.fragment_customer_info, container, false);
        activity = (CoreActivity) getActivity();
        aBookingSession = activity.getBookingSession();
        passengerId = view.findViewById(R.id.passengerId);
        nameEdit = view.findViewById(R.id.nameEdit);
        passportEdit = view.findViewById(R.id.passportEdit);
        extraBaggageStatus = view.findViewById(R.id.extraBaggageStatus);
        nextButton = view.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(clickListener);
        setHasOptionsMenu(true);

        setPassengerData();
        return view;
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.nextButton) {
                if (validateData() == true) {
                    if (activity.currentPassenger < aBookingSession.getTotalPassenger() - 1) {
                        activity.currentPassenger += 1;
                        activity.loadFragment(new CustomerInfoFragment());
                    } else if (activity.currentPassenger == aBookingSession.getTotalPassenger() - 1) {
                        activity.loadFragment(new SeatSelectionFragment());
                    }
                }
            }
        }
    };

    public void setPassengerData() {
        Passenger aPassenger = aBookingSession.getPassengers().get(activity.currentPassenger);
        String currentPassenger = String.format("Passenger %d", activity.currentPassenger + 1);
        passengerId.setText(currentPassenger);
        nameEdit.setText(aPassenger.getPassengerName());
        passportEdit.setText(aPassenger.getPassengerPassport());
        extraBaggageStatus.setChecked(aPassenger.getExtraBaggageStatus());
    }
    public boolean validateData() {
        String passengerName = nameEdit.getText().toString();
        String passengerPassport = passportEdit.getText().toString();

        if (!passengerName.equals("")) {
            aBookingSession.getPassengers().get(activity.currentPassenger).setPassengerName(passengerName);
        } else {
            Toast.makeText(getActivity(), "Please input name!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!passengerName.equals("")) {
            aBookingSession.getPassengers().get(activity.currentPassenger).setPassengerPassport(passengerPassport);
        } else {
            Toast.makeText(getActivity(), "Please input passport!", Toast.LENGTH_SHORT).show();
            return false;
        }

        aBookingSession.getPassengers().get(activity.currentPassenger).setExtraBaggage(extraBaggageStatus.isChecked());

        return true;
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

        } else if (item.getItemId() == android.R.id.home) {
            if (activity.currentPassenger == 0) {
                if (aBookingSession.flightType.equals("One Way")) {
                    aBookingSession.getFlight().remove(0);
                    activity.loadFragment(new FlightSelectFragment());
                } else {
                    aBookingSession.getFlight().remove(1);
                    activity.loadFragment(new FlightReturnSelectFragment());
                }
            } else {
                activity.currentPassenger -= 1;
                activity.loadFragment(new CustomerInfoFragment());
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}