package org.games.drinktracker.ui.apoyo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.games.drinktracker.R;


public class apoyo extends Fragment {
    private Button btAqui;



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_apoyo, container, false);

        btAqui = root.findViewById(R.id.btAqui);
        btAqui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paginaWeb();
            }
        });

        return root;
    }

    private void paginaWeb(){
        Uri webpage = Uri.parse("http://aacostarica.org");
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }
}
