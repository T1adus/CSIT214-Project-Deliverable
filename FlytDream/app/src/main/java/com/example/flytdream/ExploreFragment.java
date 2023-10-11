package com.example.flytdream;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExploreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExploreFragment extends Fragment {

    private ImageButton nextMangeButton;
    private EditText referenceEdit;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ExploreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExploreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExploreFragment newInstance(String param1, String param2) {
        ExploreFragment fragment = new ExploreFragment();
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
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        setHasOptionsMenu(true);
        nextMangeButton = view.findViewById(R.id.nextManageButton);
        nextMangeButton.setOnClickListener(clickListener);
        referenceEdit = view.findViewById(R.id.referenceEdit);
        return view;
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.nextManageButton) {
                displayFlight();
            }
        }
    };

    public void displayFlight() {
        String flightCode = "";
        String departCityAlias = "";
        String departTime = "";
        String flightTime = "";
        String arriveCityAlias = "";
        String arriveTime = "";
        String passenger = "";
        String flightType = "";
        String flightClass = "";
        String flightSeat = "";
        String board = "";
        String price = "";
        String departDate = "";

        if (!referenceEdit.getText().toString().equals("")) {
            String inputCode = referenceEdit.getText().toString();
            CoreActivity activity = (CoreActivity) getActivity();
            Cursor res = activity.myDB.getFlight(inputCode);
            if (res.moveToFirst()) {
                flightCode = res.getString(0);
                departCityAlias = res.getString(1);
                departTime = res.getString(2);
                flightTime = res.getString(3);
                arriveCityAlias = res.getString(4);
                arriveTime = res.getString(5);
                passenger = res.getString(6);
                flightType = res.getString(7);
                flightClass = res.getString(8);
                flightSeat = res.getString(9);
                board = res.getString(10);
                price = res.getString(11);
                departDate = res.getString(12);
            } else {
                Toast.makeText(getActivity(), "Invalid Flight Code!", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            Toast.makeText(getActivity(), "Please Enter A Code!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(getActivity(),DownloadInvoiceActivity.class);
        intent.putExtra("flightCode", flightCode);
        intent.putExtra("passenger name",passenger);
        intent.putExtra("flight class",flightClass);
        intent.putExtra("flight type",flightType);
        intent.putExtra("boarding time",board);
        intent.putExtra("price",price);
        intent.putExtra("gate","A5");
        intent.putExtra("terminal","T2");
        intent.putExtra("seat number",flightSeat);
        intent.putExtra("depart city alias",departCityAlias);
        intent.putExtra("depart city name","Sydney");
        intent.putExtra("depart time",departTime);
        intent.putExtra("flight time",flightTime);
        intent.putExtra("arrival city alias",arriveCityAlias);
        intent.putExtra("arrival city name","Hanoi");
        intent.putExtra("arrive time",arriveTime);
        intent.putExtra("departDate", departDate);
        intent.putExtra("context", "0");
        startActivity(intent);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.top_nav_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }
}