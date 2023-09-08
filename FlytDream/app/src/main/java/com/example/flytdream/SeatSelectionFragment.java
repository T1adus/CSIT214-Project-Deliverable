package com.example.flytdream;

import android.graphics.Color;
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

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SeatSelectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SeatSelectionFragment extends Fragment {

    CoreActivity coreActivity;
    ImageButton seatConfirm;
    TextView seat1A,seat2A,seat3C,seat4D,seat2E,seat3F,seat1G,
            departureCode,destinationCode,departure,destination,classLounge;
    private int seat1ACounter,seat2ACounter,seat3CCounter,seat4DCounter,seat2ECounter,seat3FCounter,seat1GCounter;
    public ArrayList<String> seats;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SeatSelectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SeatSelectionFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static SeatSelectionFragment newInstance(String param1, String param2) {
        SeatSelectionFragment fragment = new SeatSelectionFragment();
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
        View view = inflater.inflate(R.layout.fragment_seat_selection, container, false);
        coreActivity = (CoreActivity) getActivity();
        seats = new ArrayList<String>();
        String from = coreActivity.getBookingSession().getDepartCity().getCityName();
        String to = coreActivity.getBookingSession().getArriveCity().getCityName();
        String fromAlias = coreActivity.getBookingSession().getDepartCity().getCityAlias();
        String toAlias = coreActivity.getBookingSession().getArriveCity().getCityAlias();
        String flightClass = coreActivity.getBookingSession().getClassSelected();

        seat1A = view.findViewById(R.id.seat1A);
        seat2A = view.findViewById(R.id.seat2A);
        seat3C = view.findViewById(R.id.seat3C);
        seat4D = view.findViewById(R.id.seat4D);
        seat2E = view.findViewById(R.id.seat2E);
        seat3F = view.findViewById(R.id.seat3F);
        seat1G = view.findViewById(R.id.seat1G);
        seatConfirm = view.findViewById(R.id.seat_select_confirm);
        departureCode = view.findViewById(R.id.departureCode);
        departure = view.findViewById(R.id.departure);
        destinationCode = view.findViewById(R.id.destinationCode);
        destination = view.findViewById(R.id.destination);
        classLounge = view.findViewById(R.id.classLounge);

        seat1A.setOnClickListener(clickListener);
        seat2A.setOnClickListener(clickListener);
        seat3C.setOnClickListener(clickListener);
        seat4D.setOnClickListener(clickListener);
        seat2E.setOnClickListener(clickListener);
        seat3F.setOnClickListener(clickListener);
        seat1G.setOnClickListener(clickListener);
        seatConfirm.setOnClickListener(clickListener);

        departureCode.setText(fromAlias);
        departure.setText(from);
        destinationCode.setText(toAlias);
        destination.setText(to);
        classLounge.setText(flightClass);

        setHasOptionsMenu(true);

        return view;
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
            coreActivity.loadFragment(new FlightSelectFragment());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.seat1A){
                seat1ACounter++;
                if(seat1ACounter % 2 != 0){
                    seat1A.setBackgroundResource(R.drawable.seat_selection_selected_state);
                    seat1A.setTextColor(Color.parseColor("#1B1A42"));
                    if(!seats.contains("1A")){
                        seats.add("1A");
                    }
                }
                else{
                    seat1A.setBackgroundResource(R.drawable.seat_selection_button_background);
                    seat1A.setTextColor(Color.parseColor("#FFFFFF"));
                    if(seats.contains("1A")){
                        seats.remove("1A");
                    }
                }
            }
            else if(view.getId() == R.id.seat2A){
                seat2ACounter++;
                if(seat2ACounter % 2 != 0){
                    seat2A.setBackgroundResource(R.drawable.seat_selection_selected_state);
                    seat2A.setTextColor(Color.parseColor("#1B1A42"));
                    if(!seats.contains("2A")){
                        seats.add("2A");
                    }
                }
                else{
                    seat2A.setBackgroundResource(R.drawable.seat_selection_button_background);
                    seat2A.setTextColor(Color.parseColor("#FFFFFF"));
                    if(seats.contains("2A")){
                        seats.remove("2A");
                    }
                }
            }
            else if(view.getId() == R.id.seat3C){
                seat3CCounter++;
                if(seat3CCounter % 2 != 0){
                    seat3C.setBackgroundResource(R.drawable.seat_selection_selected_state);
                    seat3C.setTextColor(Color.parseColor("#1B1A42"));
                    if(!seats.contains("3C")){
                        seats.add("3C");
                    }
                }
                else{
                    seat3C.setBackgroundResource(R.drawable.seat_selection_button_background);
                    seat3C.setTextColor(Color.parseColor("#FFFFFF"));
                    if(seats.contains("3C")){
                        seats.remove("3C");
                    }
                }
            }
            else if(view.getId() == R.id.seat4D){
                seat4DCounter++;
                if(seat4DCounter % 2 != 0){
                    seat4D.setBackgroundResource(R.drawable.seat_selection_selected_state);
                    seat4D.setTextColor(Color.parseColor("#1B1A42"));
                    if(!seats.contains("4D")){
                        seats.add("4D");
                    }
                }
                else{
                    seat4D.setBackgroundResource(R.drawable.seat_selection_button_background);
                    seat4D.setTextColor(Color.parseColor("#FFFFFF"));
                    if(seats.contains("4D")){
                        seats.remove("4D");
                    }
                }
            }
            else if(view.getId() == R.id.seat2E){
                seat2ECounter++;
                if(seat2ECounter % 2 != 0){
                    seat2E.setBackgroundResource(R.drawable.seat_selection_selected_state);
                    seat2E.setTextColor(Color.parseColor("#1B1A42"));
                    if(!seats.contains("2E")){
                        seats.add("2E");
                    }
                }
                else{
                    seat2E.setBackgroundResource(R.drawable.seat_selection_button_background);
                    seat2E.setTextColor(Color.parseColor("#FFFFFF"));
                    if(seats.contains("2E")){
                        seats.remove("2E");
                    }
                }
            }
            else if(view.getId() == R.id.seat3F){
                seat3FCounter++;
                if(seat3FCounter % 2 != 0){
                    seat3F.setBackgroundResource(R.drawable.seat_selection_selected_state);
                    seat3F.setTextColor(Color.parseColor("#1B1A42"));
                    if(!seats.contains("3F")){
                        seats.add("3F");
                    }
                }
                else{
                    seat3F.setBackgroundResource(R.drawable.seat_selection_button_background);
                    seat3F.setTextColor(Color.parseColor("#FFFFFF"));
                    if(seats.contains("3F")){
                        seats.remove("3F");
                    }
                }
            }
            else if(view.getId() == R.id.seat1G){
                seat1GCounter++;
                if(seat1GCounter % 2 != 0){
                    seat1G.setBackgroundResource(R.drawable.seat_selection_selected_state);
                    seat1G.setTextColor(Color.parseColor("#1B1A42"));
                    if(!seats.contains("1G")){
                        seats.add("1G");
                    }
                }
                else{
                    seat1G.setBackgroundResource(R.drawable.seat_selection_button_background);
                    seat1G.setTextColor(Color.parseColor("#FFFFFF"));
                    if(seats.contains("1G")){
                        seats.remove("1G");
                    }
                }
            }
            else if(view.getId() == R.id.seat_select_confirm){
                coreActivity.getBookingSession().setSeats(seats);
                coreActivity.loadFragment(new MealSelectionFragment());
            }
        }
    };
}