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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MealSelectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MealSelectionFragment extends Fragment {

    CoreActivity coreActivity;
    TextView lunch,breakfast,drinks;
    private int lunchButtonCounter,breakfastButtonCounter,snackButtonCounter;
    ImageButton checkout;
    ViewFlipper viewFlipper;
    LinearLayout eggSalad,grilledSalmon,laksa,friedRice,cereal,sandwich,
                grilledSalmonLunch,friedRiceLunch,sandwichLunch,
                laksaBreakfast,sandwichBreakfast,cerealBreakfast,pm,oj,coke,fanta;
    private int eggSaladCounter,grilledSalmonCounter,laksaCounter,friedRiceCounter,cerealCounter,sandwichCounter,
            grilledSalmonLunchCounter,friedRiceLunchCounter,sandwichLunchCounter,
            laksaBreakfastCounter,sandwichBreakfastCounter,cerealBreakfastCounter,pmCounter,ojCounter,cokeCounter,fantaCounter;
    private ArrayList<Meal> meals;
    private Meal meal;
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
        drinks = view.findViewById(R.id.drinks);
        viewFlipper = view.findViewById(R.id.viewFlipper);
        checkout = view.findViewById(R.id.checkout_button);
        eggSalad = view.findViewById(R.id.egg_salad);
        grilledSalmon = view.findViewById(R.id.grilled_salmon);
        laksa = view.findViewById(R.id.laksa);
        friedRice = view.findViewById(R.id.fried_rice);
        cereal = view.findViewById(R.id.cereal);
        sandwich = view.findViewById(R.id.sandwich);
        grilledSalmonLunch = view.findViewById(R.id.grilled_salmon_lunch);
        friedRiceLunch = view.findViewById(R.id.fried_rice_lunch);
        sandwichLunch = view.findViewById(R.id.sandwich_lunch);
        laksaBreakfast = view.findViewById(R.id.laksa_breakfast);
        sandwichBreakfast = view.findViewById(R.id.sandwich_breakfast);
        cerealBreakfast = view.findViewById(R.id.cereal_breakfast);
        pm = view.findViewById(R.id.pink_lemonade);
        oj = view.findViewById(R.id.orange_juice);
        coke = view.findViewById(R.id.coke_can);
        fanta = view.findViewById(R.id.fanta_can);
        meals = new ArrayList<Meal>();

        lunch.setOnClickListener(clickListener);
        breakfast.setOnClickListener(clickListener);
        drinks.setOnClickListener(clickListener);
        eggSalad.setOnClickListener(clickListener1);
        grilledSalmon.setOnClickListener(clickListener1);
        laksa.setOnClickListener(clickListener1);
        friedRice.setOnClickListener(clickListener1);
        cereal.setOnClickListener(clickListener1);
        sandwich.setOnClickListener(clickListener1);
        grilledSalmonLunch.setOnClickListener(clickListener1);
        friedRiceLunch.setOnClickListener(clickListener1);
        sandwichLunch.setOnClickListener(clickListener1);
        laksaBreakfast.setOnClickListener(clickListener1);
        sandwichBreakfast.setOnClickListener(clickListener1);
        cerealBreakfast.setOnClickListener(clickListener1);
        pm.setOnClickListener(clickListener1);
        oj.setOnClickListener(clickListener1);
        coke.setOnClickListener(clickListener1);
        fanta.setOnClickListener(clickListener1);

        checkout.setOnClickListener(clickListener);

        setHasOptionsMenu(true);
        return view;
    }
    //set layout changes when meal icons are clicked
    View.OnClickListener clickListener1 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.egg_salad) {
                eggSaladCounter++;
                if (eggSaladCounter % 2 != 0) {
                    eggSalad.setBackgroundResource(R.drawable.meal_shape_selected);
                    meal = new Meal("Egg Salad", 5.0, 1);
                    meals.add(meal);
                } else {
                    for (Meal meal : meals) {
                        if (meal.getName() == "Egg Salad") {
                            meals.remove(meal);
                        }
                    }
                    eggSalad.setBackgroundResource(R.drawable.meal_shape);
                }
            } else if ((view.getId() == R.id.grilled_salmon) || (view.getId() == R.id.grilled_salmon_lunch)) {
                grilledSalmonCounter++;
                grilledSalmonLunchCounter++;
                if ((grilledSalmonCounter % 2 != 0) || (grilledSalmonLunchCounter % 2 != 0)) {
                    grilledSalmon.setBackgroundResource(R.drawable.meal_shape_selected);
                    grilledSalmonLunch.setBackgroundResource(R.drawable.meal_shape_selected);
                    meal = new Meal("Grilled Salmon", 15.0, 1);
                    meals.add(meal);
                } else {
                    for (Meal meal : meals) {
                        if (meal.getName() == "Grilled Salmon") {
                            meals.remove(meal);
                        }
                    }
                    grilledSalmon.setBackgroundResource(R.drawable.meal_shape);
                    grilledSalmonLunch.setBackgroundResource(R.drawable.meal_shape);
                }
            } else if ((view.getId() == R.id.laksa) || (view.getId() == R.id.laksa_breakfast)) {
                laksaCounter++;
                laksaBreakfastCounter++;
                if ((laksaCounter % 2 != 0) || (laksaBreakfastCounter % 2 != 0)) {
                    laksa.setBackgroundResource(R.drawable.meal_shape_selected);
                    laksaBreakfast.setBackgroundResource(R.drawable.meal_shape_selected);
                    meal = new Meal("Laksa", 10.0, 1);
                    meals.add(meal);
                } else {
                    for (Meal meal : meals) {
                        if (meal.getName() == "Laksa") {
                            meals.remove(meal);
                        }
                    }
                    laksa.setBackgroundResource(R.drawable.meal_shape);
                    laksaBreakfast.setBackgroundResource(R.drawable.meal_shape);
                }
            } else if ((view.getId() == R.id.fried_rice) || (view.getId() == R.id.fried_rice_lunch)) {
                friedRiceCounter++;
                friedRiceLunchCounter++;
                if ((friedRiceCounter % 2 != 0) || (friedRiceLunchCounter % 2 != 0)) {
                    friedRice.setBackgroundResource(R.drawable.meal_shape_selected);
                    friedRiceLunch.setBackgroundResource(R.drawable.meal_shape_selected);
                    meal = new Meal("Fried Rice", 10.0, 1);
                    meals.add(meal);
                } else {
                    for (Meal meal : meals) {
                        if (meal.getName() == "Fried Rice") {
                            meals.remove(meal);
                        }
                    }
                    friedRice.setBackgroundResource(R.drawable.meal_shape);
                    friedRiceLunch.setBackgroundResource(R.drawable.meal_shape);
                }
            } else if ((view.getId() == R.id.sandwich) || (view.getId() == R.id.sandwich_breakfast) || (view.getId()==R.id.sandwich_lunch)) {
                sandwichCounter++;
                sandwichBreakfastCounter++;
                sandwichLunchCounter++;
                if ((sandwichCounter % 2 != 0) || (sandwichLunchCounter % 2 != 0) || (sandwichBreakfastCounter % 2 != 0)) {
                    sandwich.setBackgroundResource(R.drawable.meal_shape_selected);
                    sandwichLunch.setBackgroundResource(R.drawable.meal_shape_selected);
                    sandwichBreakfast.setBackgroundResource(R.drawable.meal_shape_selected);
                    meal = new Meal("Sandwich", 5.0, 1);
                    meals.add(meal);
                } else {
                    for (Meal meal : meals) {
                        if (meal.getName() == "Sandwich") {
                            meals.remove(meal);
                        }
                    }
                    sandwich.setBackgroundResource(R.drawable.meal_shape);
                    sandwichLunch.setBackgroundResource(R.drawable.meal_shape);
                    sandwichBreakfast.setBackgroundResource(R.drawable.meal_shape);
                }
            } else if ((view.getId() == R.id.cereal) || (view.getId() == R.id.cereal_breakfast)) {
                cerealCounter++;
                cerealBreakfastCounter++;
                if ((cerealCounter % 2 != 0) || (cerealBreakfastCounter % 2 != 0)) {
                    cereal.setBackgroundResource(R.drawable.meal_shape_selected);
                    cerealBreakfast.setBackgroundResource(R.drawable.meal_shape_selected);
                    meal = new Meal("Cereal", 10.0, 1);
                    meals.add(meal);
                } else {
                    for (Meal meal : meals) {
                        if (meal.getName() == "Cereal") {
                            meals.remove(meal);
                        }
                    }
                    cereal.setBackgroundResource(R.drawable.meal_shape);
                    cerealBreakfast.setBackgroundResource(R.drawable.meal_shape);
                }
            } else if ((view.getId() == R.id.pink_lemonade)) {
                pmCounter++;
                if ((pmCounter % 2 != 0)) {
                    pm.setBackgroundResource(R.drawable.meal_shape_selected);
                    meal = new Meal("Pink Lemonade", 7.0, 1);
                    meals.add(meal);
                } else {
                    for (Meal meal : meals) {
                        if (meal.getName() == "Pink Lemonade") {
                            meals.remove(meal);
                        }
                    }
                    pm.setBackgroundResource(R.drawable.meal_shape);
                }
            } else if ((view.getId() == R.id.orange_juice)) {
                ojCounter++;
                if ((ojCounter % 2 != 0)) {
                    oj.setBackgroundResource(R.drawable.meal_shape_selected);
                    meal = new Meal("Orange Juice", 6.0, 1);
                    meals.add(meal);
                } else {
                    for (Meal meal : meals) {
                        if (meal.getName() == "Orange Juice") {
                            meals.remove(meal);
                        }
                    }
                    oj.setBackgroundResource(R.drawable.meal_shape);
                }
            } else if ((view.getId() == R.id.coke_can)) {
                cokeCounter++;
                if ((cokeCounter % 2 != 0)) {
                    coke.setBackgroundResource(R.drawable.meal_shape_selected);
                    meal = new Meal("Coke", 5.0, 1);
                    meals.add(meal);
                } else {
                    for (Meal meal : meals) {
                        if (meal.getName() == "Coke") {
                            meals.remove(meal);
                        }
                    }
                    coke.setBackgroundResource(R.drawable.meal_shape);
                }
            } else if ((view.getId() == R.id.fanta_can)) {
                fantaCounter++;
                if ((fantaCounter % 2 != 0)) {
                    fanta.setBackgroundResource(R.drawable.meal_shape_selected);
                    meal = new Meal("Fanta", 5.0, 1);
                    meals.add(meal);
                } else {
                    for (Meal meal : meals) {
                        if (meal.getName() == "Fanta") {
                            meals.remove(meal);
                        }
                    }
                    fanta.setBackgroundResource(R.drawable.meal_shape);
                }
            }
        }
    };
    //meal categories buttons functionalities
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

                    drinks.setBackgroundResource(R.drawable.meal_button_shape);
                    drinks.setTypeface(null, Typeface.NORMAL);
                    drinks.setTextColor(Color.parseColor("#000000"));

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

                    drinks.setBackgroundResource(R.drawable.meal_button_shape);
                    drinks.setTypeface(null, Typeface.NORMAL);
                    drinks.setTextColor(Color.parseColor("#000000"));

                    viewFlipper.setDisplayedChild(2);
                } else {
                    breakfast.setBackgroundResource(R.drawable.meal_button_shape);
                    breakfast.setTypeface(null, Typeface.NORMAL);
                    breakfast.setTextColor(Color.parseColor("#000000"));

                    viewFlipper.setDisplayedChild(0);
                }
            } else if(view.getId() == R.id.drinks){
                snackButtonCounter++;
                breakfastButtonCounter = 0;
                lunchButtonCounter = 0;
                if(snackButtonCounter%2 != 0){
                    drinks.setBackgroundResource(R.drawable.meal_button_shape_selected);
                    drinks.setTypeface(null, Typeface.BOLD);
                    drinks.setTextColor(Color.parseColor("#FFFFFF"));

                    breakfast.setBackgroundResource(R.drawable.meal_button_shape);
                    breakfast.setTypeface(null, Typeface.NORMAL);
                    breakfast.setTextColor(Color.parseColor("#000000"));

                    lunch.setBackgroundResource(R.drawable.meal_button_shape);
                    lunch.setTypeface(null, Typeface.NORMAL);
                    lunch.setTextColor(Color.parseColor("#000000"));

                    viewFlipper.setDisplayedChild(3);
                } else {
                    drinks.setBackgroundResource(R.drawable.meal_button_shape);
                    drinks.setTypeface(null, Typeface.NORMAL);
                    drinks.setTextColor(Color.parseColor("#000000"));

                    viewFlipper.setDisplayedChild(0);
                }
            } else if (view.getId() == R.id.checkout_button) {
                if(meals.size() > 0){
                    coreActivity.getBookingSession().setMeals(meals);
                }
                coreActivity.getBookingSession().calculatePrice();
                coreActivity.loadFragment(new OrderReviewFragment());
            }
        }
    };
    //set tool bar
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.top_nav_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    //set tool bar options
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        if (item.getItemId() == R.id.action_profile) {
            Toast.makeText(this.getActivity(), "Yo!", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == android.R.id.home) {
            ArrayList<Meal> mea = new ArrayList<>();
            coreActivity.getBookingSession().setMeals(mea);
            coreActivity.getBookingSession().setTotalCost(0);
            coreActivity.loadFragment(new SeatSelectionFragment());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}