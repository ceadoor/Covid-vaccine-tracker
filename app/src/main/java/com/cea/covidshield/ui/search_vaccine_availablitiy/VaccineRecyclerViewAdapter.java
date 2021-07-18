package com.cea.covidshield.ui.search_vaccine_availablitiy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cea.covidshield.R;
import com.cea.covidshield.model.CalenderPinApiModels.Center;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VaccineRecyclerViewAdapter extends RecyclerView.Adapter<VaccineRecyclerViewAdapter.ViewHolder> {

    List<Center> centersList;

    public VaccineRecyclerViewAdapter(List<Center> centersList) {
        this.centersList = centersList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.vaccine_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Center centerDetail = centersList.get(position);

        holder.centerName.setText(centerDetail.getName());
        holder.centerAddress.setText(centerDetail.getAddress() + centerDetail.getDistrictName() + centerDetail.getStateName() + centerDetail.getPincode());

        if (centerDetail.getFeeType().equals("Paid")){
            holder.feePaidTv.setVisibility(View.VISIBLE);
        }else {
            holder.feePaidTv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return centersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTv;
        TextView centerName;
        TextView feePaidTv;
        TextView centerAddress;
        TextView bookedStatTv;
        LinearLayout covishelldLL;
        LinearLayout covaxinLL;
        LinearLayout sputnikvLL;
        TextView d1Covisheld;
        TextView d2Covisheld;
        TextView d1Covaxin;
        TextView d2Covacin;
        TextView d1SputnikV;
        TextView d2SputnikV;






        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            dateTv = itemView.findViewById(R.id.date_tv);
            centerName = itemView.findViewById(R.id.centre_name);
             feePaidTv = itemView.findViewById(R.id.fee_paid);
             centerAddress = itemView.findViewById(R.id.center_address);
             bookedStatTv = itemView.findViewById(R.id.bookedStatTv);
             covishelldLL = itemView.findViewById(R.id.covishield_vacc_LL);
             covaxinLL = itemView.findViewById(R.id.covaxin_vacc_LL);
             sputnikvLL = itemView.findViewById(R.id.sputnikv_vacc_LL);
             d1Covisheld = itemView.findViewById(R.id.d1_covisheld);
             d2Covisheld = itemView.findViewById(R.id.d2_covisheld);
             d1Covaxin = itemView.findViewById(R.id.d1_covaxin);
             d2Covacin = itemView.findViewById(R.id.d2_covaxin);
             d1SputnikV = itemView.findViewById(R.id.d1_sputnikv);
             d2SputnikV = itemView.findViewById(R.id.d2_sputnikv);

        }
    }
}
