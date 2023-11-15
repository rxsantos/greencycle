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
import com.pucpr.greencycle.model.Company;
import com.pucpr.greencycle.model.DataModel;

public class FormPedidosAdapter extends RecyclerView.Adapter<FormPedidosAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvPedidoName;
        TextView tvPedidoCnpj;
        TextView tvPedidoEmail;
        TextView tvPedidoPhone;
        TextView tvPedidoAddress;
        TextView tvPedidoZipcode;
        TextView tvPedidoCity;
        TextView tvPedidoState;
        TextView tvPedidoRegion;
        TextView tvPedidoResiduo;
        public ViewHolder(View pedidosView){
            super(pedidosView);
            tvPedidoName = pedidosView.findViewById(R.id.tvPedidoName);
            //tvPedidoCnpj = pedidosView.findViewById(R.id.tvPedidoCnpj);
            //tvePedidoEmail = pedidosView.findViewById(R.id.tvPedidoEmail);
            tvPedidoPhone = pedidosView.findViewById(R.id.tvPedidoPhone);
            tvPedidoAddress = pedidosView.findViewById(R.id.tvPedidoEndereco);
            //tvPedidoZipcode = pedidosView.findViewById(R.id.tvPedidoZipcode);
            //tvPedidoCity = pedidosView.findViewById(R.id.tvPedidoCity);
            //tvPedidoState = pedidosView.findViewById(R.id.tvPedidoState);
            tvPedidoRegion = pedidosView.findViewById(R.id.tvPedidoRegion);
            tvPedidoResiduo = pedidosView.findViewById(R.id.tvPedidoResiduo);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View pedidosView = inflater.inflate(
                R.layout.recycler_view_pedidos,
                parent,
                false);

        return new ViewHolder(pedidosView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Company c = DataModel.getInstance().getCompany(position);
        holder.tvPedidoName.setText(c.getName());
        //holder.tvPedidoCnpj.setText(c.getCnpj());
        //holder.tvPedidoEmail.setText(c.getEmail());
        holder.tvPedidoPhone.setText(c.getPhone());
        holder.tvPedidoAddress.setText(c.getAddress());
        //holder.tvPedidoZipcode.setText(c.getZipcode());
        //holder.tvPedidoCity.setText(c.getCity());
        //holder.tvPedidoState.setText(c.getState());
        holder.tvPedidoRegion.setText(c.getRegion());
        holder.tvPedidoResiduo.setText(c.getResiduo());
        Log.v("Company: ", "Pos["+position+"]:"+c.getName());
    }

    @Override
    public int getItemCount() {
        return DataModel.getInstance().getCompanySize();
    }
}
