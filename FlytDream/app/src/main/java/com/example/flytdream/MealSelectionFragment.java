package com.example.flytdream;

import android.graphics.Color;
import android.graphics.Typeface;
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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MealSelectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MealSelectionFragment extends Fragment {

    CoreActivity coreActivity;
    TextView lunch,breakfast,snack;
    private int lunchButtonCounter,breakfastButtonCounter,snackButtonCounter;
    ViewFlipper viewFlipper;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MealSelectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MealSelectionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MealSelectionFragment newInstance(String param1, String param2) {
        MealSelectionFragment fragment = new MealSelectionFragment();
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
        View view = inflater.inflate(R.layout.fragment_meal_selection, container, false);

        coreActivity = (CoreActivity) getActivity();
        lunch = view.findViewById(R.id.lunch);
        breakfast = view.findViewById(R.id.breakfast);
        snack = view.findViewById(R.id.snack);
        viewFlipper = view.findViewById(R.id.viewFlipper);

        lunch.setOnClickListener(clickListener);
        breakfast.setOnClickListener(clickListener);
        snack.setOnClickListener(clickListener);

        setHasOptionsMenu(true);
        return view;
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.lunch){
                lunchButtonCounter++;
                breakfastButtonCounter = 0;
                snackButtonCounter = 0;
                if(lunchButtonCounter%2 != 0){
                    lunch.setBackgroundResource(R.drawable.meal_button_shape_selected);
                    lunch.setTypeface(null, Typeface.BOLD);
                    lunch.setTextColor(Color.parseColor("#FFFFFF"));

                    breakfast.setBackgroundResource(R.drawable.meal_button_shape);
                    breakfast.setTypeface(null, Typeface.NORMAL);
                    breakfast.setTextColor(Color.parseColor("#000000"));

                    snack.setBackgroundResource(R.drawable.meal_button_shape);
                    snack.setTypeface(null, Typeface.NORMAL);
                    snack.setTextColor(Color.parseColor("#000000"));

                    viewFlipper.setDisplayedChild(1);
                } else {
                    lunch.setBackgroundResource(R.drawable.meal_button_shape);
                    lunch.setTypeface(null, Typeface.NORMAL);
                    lunch.setTextColor(Color.parseColor("#000000"));

                    viewFlipper.setDisplayedChild(0);
                }
            } else if(view.getId() == R.id.breakfast){
                breakfastButtonCounter++;
                lunchButtonCounter = 0;
                snackButtonCounter = 0;
                if(breakfastButtonCounter%2 != 0){
                    breakfast.setBackgroundResource(R.drawable.meal_button_shape_selected);
                    breakfast.setTypeface(null, Typeface.BOLD);
                    breakfast.setTextColor(Color.parseColor("#FFFFFF"));

                    lunch.setBackgroundResource(R.drawable.meal_button_shape);
                    lunch.setTypeface(null, Typeface.NORMAL);
                    lunch.setTextColor(Color.parseColor("#000000"));

                    snack.setBackgroundResource(R.drawable.meal_button_shape);
                    snack.setTypeface(null, Typeface.NORMAL);
                    snack.setTextColor(Color.parseColor("#000000"));

                    viewFlipper.setDisplayedChild(2);
                } else {
                    breakfast.setBackgroundResource(R.drawable.meal_button_shape);
                    breakfast.setTypeface(null, Typeface.NORMAL);
                    breakfast.setTextColor(Color.parseColor("#000000"));

                    viewFlipper.setDisplayedChild(0);
                }
            } else if(view.getId() == R.id.snack){
                snackButtonCounter++;
                breakfastButtonCounter = 0;
                lunchButtonCounter = 0;
                if(snackButtonCounter%2 != 0){
                    snack.setBackgroundResource(R.drawable.meal_button_shape_selected);
                    snack.setTypeface(null, Typeface.BOLD);
                    snack.setTextColor(Color.parseColor("#FFFFFF"));

                    breakfast.setBackgroundResource(R.drawable.meal_button_shape);
                    breakfast.setTypeface(null, Typeface.NORMAL);
                    breakfast.setTextColor(Color.parseColor("#000000"));

                    lunch.setBackgroundResource(R.drawable.meal_button_shape);
                    lunch.setTypeface(null, Typeface.NORMAL);
                    lunch.setTextColor(Color.parseColor("#000000"));

                    viewFlipper.setDisplayedChild(3);
                } else {
                    snack.setBackgroundResource(R.drawable.meal_button_shape);
                    snack.setTypeface(null, Typeface.NORMAL);
                    snack.setTextColor(Color.parseColor("#000000"));

                    viewFlipper.setDisplayedChild(0);
                }
            }
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