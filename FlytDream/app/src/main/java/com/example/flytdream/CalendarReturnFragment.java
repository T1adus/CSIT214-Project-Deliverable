package com.example.flytdream;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarReturnFragment extends Fragment {
    private CoreActivity activity;
    private BookingSession aBookingSession;
    private CalendarView returnCalendar;
    private ImageButton confirmButton;
    private String departDate;
    private TextView cityType;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalendarReturnFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalendarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalendarFragment newInstance(String param1, String param2) {
        CalendarFragment fragment = new CalendarFragment();
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
        departDate = "";
        setCurrentDate();
        View view = inflater.inflate(R.layout.fragment_calendar_return, container, false);
        activity = (CoreActivity) getActivity();
        aBookingSession = activity.getBookingSession();

        cityType = view.findViewById(R.id.cityReturnType);

        returnCalendar = view.findViewById(R.id.returnCalendar);
        Calendar calendar = Calendar.getInstance();
        returnCalendar.setMinDate(calendar.getTimeInMillis());
        returnCalendar.setOnDateChangeListener(calendarListener);
        confirmButton = view.findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(clickListener);

        setHasOptionsMenu(true);

        return view;
    }

    CalendarView.OnDateChangeListener calendarListener = new CalendarView.OnDateChangeListener() {
        @Override
        public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
            String inputDay;
            String inputMonth;
            String inputYear;
            if (dayOfMonth < 10) {
                inputDay = "0" + Integer.toString(dayOfMonth);
            } else {
                inputDay = Integer.toString(dayOfMonth);
            }

            month += 1;
            if (month < 10) {
                inputMonth = "0" + Integer.toString(month);
            } else {
                inputMonth = Integer.toString(month);
            }

            inputYear = Integer.toString(year);

            departDate = String.format("%s/%s/%s", inputDay, inputMonth, inputYear);
        }
    };

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.confirmButton) {
                if (!departDate.equals("")) {
                    aBookingSession.setReturnDate(departDate);
                    activity.loadFragment(new TripFragment());
                } else {
                    Toast.makeText(getActivity(), "Please select a date!", Toast.LENGTH_SHORT).show();
                }
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

    public void setCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());

        departDate = dateFormat.format(currentDate);
    }
}