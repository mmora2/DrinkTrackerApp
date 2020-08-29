package org.games.drinktracker.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.games.drinktracker.R;
import org.games.drinktracker.controllers.RegistroController;
import org.games.drinktracker.model.Registro;

import java.util.List;

public class RegistroAdapter extends RecyclerView.Adapter<RegistroAdapter.RegistroViewHolder>
    implements View.OnClickListener{

    private View.OnClickListener listener;

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null) {
            listener.onClick(v);
        }
    }

    private List<Registro> lista;

    public RegistroAdapter(List<Registro> lista) {
        this.lista = lista;
    }

    public void refresh() {
        lista= RegistroController.getRegistros();
    }

    @NonNull
    @Override
    public RegistroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_registro,parent,false);
        vista.setOnClickListener(this);
        return new RegistroViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull RegistroViewHolder holder, int position) {
        holder.tvId_card.setText(""+lista.get(position).getId());
        holder.tvTipo_card.setText(""+lista.get(position).getTipo());
        holder.tvCantidad_card.setText(""+lista.get(position).getCantidad());
        holder.tvFecha_card.setText(""+lista.get(position).getFecha());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class RegistroViewHolder extends RecyclerView.ViewHolder {
        public TextView tvId_card;
        public TextView tvTipo_card;
        public TextView tvCantidad_card;
        public TextView tvFecha_card;

        public RegistroViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvId_card=itemView.findViewById(R.id.tvId_card);
            this.tvTipo_card=itemView.findViewById(R.id.tvTipo_card);
            this.tvCantidad_card=itemView.findViewById(R.id.tvCantidad_card);
            this.tvFecha_card=itemView.findViewById(R.id.tvFecha_card);
        }
    }
}
