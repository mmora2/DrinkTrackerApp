package org.games.drinktracker.ui.perfil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.games.drinktracker.R;


public class perfil extends Fragment {
    private Button btBuscar;
    private ImageView imagen;
    private Button btSubir;
    private StorageReference mStorageRef;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        btBuscar = root.findViewById(R.id.btBuscar);
        imagen = root.findViewById(R.id.imagen);
        btSubir = root.findViewById(R.id.btSubir);

        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar();
            }
        });

        btSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subir();
            }
        });


        return root;
}
    private void buscar() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        startActivityForResult(intent,333);
    }

    private Uri uri;

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        uri = data.getData();
        imagen.setImageURI(uri);
    }

    private void subir() {
        String archivo = "fotoperfil";
        mStorageRef = FirebaseStorage
                .getInstance()
                .getReference()
                .child(archivo);
        mStorageRef.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Uri rutaUri = taskSnapshot.getUploadSessionUri();
                        Toast.makeText(getContext(), rutaUri.getPath(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Error subiendo...", Toast.LENGTH_SHORT).show();
                    }
                });
 }
}