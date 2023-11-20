package com.pucpr.greencycle.controller;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pucpr.greencycle.R;
import com.pucpr.greencycle.model.Client;
import com.pucpr.greencycle.model.Company;
import com.pucpr.greencycle.model.DataModel;
import com.pucpr.greencycle.model.Request;

public class FormColetasAdapter extends RecyclerView.Adapter<FormColetasAdapter.ViewHolder>{
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    public interface OnItemLongClickListener{
        boolean onItemLongClick(View view,int position);
    }
    private FormColetasAdapter.OnItemClickListener clickListener;
    private FormColetasAdapter.OnItemLongClickListener longClickListener;
    public void setOnItemClickListener(FormColetasAdapter.OnItemClickListener clickListener){
        this.clickListener = clickListener;
    }
    public void setOnItemLongClickListener(FormColetasAdapter.OnItemLongClickListener longClickListener){
        this.longClickListener = longClickListener;
    }

    private Context context;
    int selectionPosition = -1, index;
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
        TextView tvColetaDescResiduo;
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
            tvColetaDescResiduo = coletaView.findViewById(R.id.tvColetaDescResiduo);

            coletaView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onItemClick(view, getAdapterPosition());
                        selectionPosition = getAdapterPosition();
                    }
                }
            });
            coletaView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (longClickListener != null){
                        return longClickListener.onItemLongClick(view, getAdapterPosition());

                    }
                    return false;
                }
            });

        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View coletaView = inflater.inflate(
                R.layout.recycler_view_client_requests_available,
                parent,
                false);
        return new ViewHolder(coletaView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Client c = DataModel.getInstance().getClient(position);
        Request r = DataModel.getInstance().getRequest(position);
        holder.tvColetaName.setText(r.getClient_name());
        //holder.tvColetaCpf.setText(c.getCpf());
        //holder.tvColetaCpf.setText(c.getCpf());
        holder.tvColetaEmail.setText(r.getClient_email());
        //holder.tvColetaPhone.setText(c.getPhone());
        //holder.tvColetaAddress.setText(c.getAddress());
        //holder.tvColetaZipcode.setText(c.getZipcode());
        //holder.tvColetaCity.setText(c.getCity());
        //holder.tvColetaState.setText(c.getState());
        //holder.tvColetaCountry.setText(c.getCountry());
        holder.tvColetaResiduo.setText(r.getResiduo());
        holder.tvColetaDescResiduo.setText(r.getDescresiduo());
        Log.v("Coleta: ", "Pos["+position+"]:"+r.getClient_name()+" Desc_Residuo: " +r.getDescresiduo());
        if (selectionPosition == position){
            //holder.itemView.setBackgroundColor(Color.rgb(106,189,33));
            holder.itemView.setBackgroundColor(Color.rgb(168,232,185));
        }else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        return DataModel.getInstance().getRequestSize();
    }
    public String getSelected(){
        if (selectionPosition != -1){
            Request c = DataModel.getInstance().getRequest(selectionPosition);
            return c.getClient_email();
        }
        return null;
    }


}
