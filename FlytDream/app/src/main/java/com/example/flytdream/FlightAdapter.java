package com.example.flytdream;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.ViewHolder> {
    private List<Flight> flights;
    private int selectedItem = -1;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public FlightAdapter(List<Flight> flights) {
        this.flights = flights;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.flight_objects, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Flight flight = flights.get(position);
        holder.departCity.setText(flight.getDepartCity());
        holder.departTime.setText(flight.getDepartTime());
        holder.flightTime.setText(flight.getFlightTime());
        holder.arriveCity.setText(flight.getArriveCity());
        holder.arriveTime.setText(flight.getArriveTime());
        int fCost = flight.getCost();
        String price = "";
        if (fCost != 0) {
            price = "$" + Integer.toString(fCost);
            holder.flightCost.setText(price);
        } else {
            holder.flightCost.setText("$TBA");
        }

        // Highlight the selected item
        holder.itemView.setSelected(position == selectedItem);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedPosition = holder.getAdapterPosition();
                setSelectedItem(selectedPosition);

                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(selectedPosition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return flights.size();
    }

    public void setSelectedItem(int position) {
        selectedItem = position;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView departCity;
        TextView departTime;
        TextView flightTime;
        TextView arriveCity;
        TextView arriveTime;
        TextView flightCost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            departCity = itemView.findViewById(R.id.departCity);
            departTime = itemView.findViewById(R.id.departTime);
            flightTime = itemView.findViewById(R.id.flightTime);
            arriveCity = itemView.findViewById(R.id.arriveCity);
            arriveTime = itemView.findViewById(R.id.arriveTime);
            flightCost = itemView.findViewById(R.id.flightCost);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelectedItem(getAdapterPosition());
                }
            });
        }
    }
}
