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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderReviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderReviewFragment extends Fragment {
    CoreActivity coreActivity;
    TextView passengerName,flightType,boardingTime,seatNumber;
    ImageButton confirmButton;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrderReviewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderReviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderReviewFragment newInstance(String param1, String param2) {
        OrderReviewFragment fragment = new OrderReviewFragment();
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
        View view = inflater.inflate(R.layout.fragment_order_review, container, false);
        coreActivity = (CoreActivity) getActivity();
        passengerName = view.findViewById(R.id.passenger_name);
        flightType = view.findViewById(R.id.flight_type);
        boardingTime = view.findViewById(R.id.boarding_time);
        seatNumber = view.findViewById(R.id.seat_number);
        confirmButton = view.findViewById(R.id.confirm_order);

        passengerName.setText(coreActivity.getBookingSession().checkOutPassengerName(coreActivity.getBookingSession().getPassengers()));
        flightType.setText(coreActivity.getBookingSession().getClassSelected());
        boardingTime.setText(coreActivity.getBookingSession().getFlight().getDepartTime());
        seatNumber.setText(coreActivity.getBookingSession().checkOutPassengerSeats(coreActivity.getBookingSession().getSeats()));
        confirmButton.setOnClickListener(clickListener);

        return view;
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

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
            coreActivity.loadFragment(new FlightSelectFragment());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}