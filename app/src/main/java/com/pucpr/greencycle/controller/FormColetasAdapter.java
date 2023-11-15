package com.pucpr.greencycle.controller;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pucpr.greencycle.R;
import com.pucpr.greencycle.model.Client;
import com.pucpr.greencycle.model.DataModel;

public class FormColetasAdapter extends RecyclerView.Adapter<FormColetasAdapter.ViewHolder>{
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvColetaName;
        TextView tvColetaCpf;
        TextView tvColetaEmail;
        TextView tvColetaPhone;
        TextView tvColetaAddress;
        TextView tvColetaZipcode;
        TextView tvColetaCity;
        TextView tvColetaState;
        //TextView tvColetaCountry;
        TextView tvColetaResiduo;
        public ViewHolder(View coletaView){
            super(coletaView);
            tvColetaName = coletaView.findViewById(R.id.tvColetaName);
            //tvColetaCpf = coletaView.findViewById(R.id.tvColetaCpf);
            tvColetaEmail = coletaView.findViewById(R.id.tvColetaEmail);
            tvColetaPhone = coletaView.findViewById(R.id.tvColetaPhone);
            tvColetaAddress = coletaView.findViewById(R.id.tvColetaEndereco);
            tvColetaZipcode = coletaView.findViewById(R.id.tvColetaCep);
            tvColetaCity = coletaView.findViewById(R.id.tvColetaCidade);
            tvColetaState = coletaView.findViewById(R.id.tvColetaEstado);
            //tvColetaCountry = coletaView.findViewById(R.id.tvColetaCountry);
            tvColetaResiduo = coletaView.findViewById(R.id.tvColetaResiduo);

        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View coletaView = inflater.inflate(
                R.layout.recycler_view_coletas,
                parent,
                false);
        return new ViewHolder(coletaView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Client c = DataModel.getInstance().getClient(position);
        holder.tvColetaName.setText(c.getName());
        //holder.tvColetaCpf.setText(c.getCpf());
        holder.tvColetaEmail.setText(c.getEmail());
        holder.tvColetaPhone.setText(c.getPhone());
        holder.tvColetaAddress.setText(c.getAddress());
        holder.tvColetaZipcode.setText(c.getZipcode());
        holder.tvColetaCity.setText(c.getCity());
        holder.tvColetaState.setText(c.getState());
        //holder.tvColetaCountry.setText(c.getCountry());
        holder.tvColetaResiduo.setText(c.getResiduo());
        Log.v("Coleta: ", "Pos["+position+"]:"+c.getName());
    }

    @Override
    public int getItemCount() {
        return DataModel.getInstance().getClientSize();
    }
}
