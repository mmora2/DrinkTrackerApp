package org.games.drinktracker.ui.registro;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import org.games.drinktracker.R;
import org.games.drinktracker.adapter.RegistroAdapter;
import org.games.drinktracker.controllers.RegistroController;
import org.games.drinktracker.model.Registro;

public class RegistroFragment extends Fragment {
    private RecyclerView recyclerView;
    private RegistroAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_registro, container, false);

        recyclerView=root.findViewById(R.id.reciclador);
        recyclerView.setHasFixedSize(true);

        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Registro> lista= RegistroController.getRegistros();
        adapter= new RegistroAdapter(lista);

        final ArrayList<Registro> listaFinal;
        listaFinal=lista;
        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicion=recyclerView.getChildAdapterPosition(v);
                Toast.makeText(getContext(),
                        "Agregando consumo: ", Toast.LENGTH_SHORT).show();

                Registro registro = listaFinal.get(posicion);
                Intent intent = new Intent(getContext(),RegistroActivity.class);
                intent.putExtra("tipo",2);
                intent.putExtra("registro",registro);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.refresh();
        adapter.notifyDataSetChanged();
    }
}