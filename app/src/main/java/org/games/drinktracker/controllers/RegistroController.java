package org.games.drinktracker.controllers;

import android.content.ContentValues;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.games.drinktracker.data.AdminDB;
import org.games.drinktracker.model.Registro;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class RegistroController {
    private static AdminDB data = null;
    private static SQLiteDatabase conexion=null;

    public static void init (AdminDB data) {
        RegistroController.data=data;
    }

    private static String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static boolean inserta(Registro registro) {
        long valor=-1;
        if (registro!=null) {
            ContentValues datos = new ContentValues();
            datos.putNull("id");
            datos.put("tipo",registro.getTipo());
            datos.put("cantidad",registro.getCantidad());
            datos.put("fecha",getDateTime());
            conexion = data.getWritableDatabase();
            valor=conexion.insert("regdrink",null,datos);
            conexion.close();
        }
        return valor != -1;
    }

    public static Registro busca(int id) {
        Registro registro= null;
        conexion=data.getReadableDatabase();
        Cursor data = conexion.rawQuery("select * from regdrink where id=?", new String[]{""+id+""});
        if (data.moveToFirst()) { //Si lo encontró
            registro = new Registro(
                    id,
                    data.getString(1),
                    data.getInt(2),
                    data.getString(3)
            );
        }
        conexion.close();
        return registro;
    }

    public static boolean modifica(Registro registro) {
        long valor=-1;
        if (registro != null) {
            ContentValues datos = new ContentValues();
            datos.put("id",registro.getId());
            datos.put("tipo",registro.getTipo());
            datos.put("cantidad",registro.getCantidad());
            datos.put("fecha",getDateTime());
            conexion = data.getWritableDatabase();
            valor = conexion.update("regdrink",
                    datos,
                    "id=?",
                    new String[]{""+registro.getId()+""});
        }
        return valor== 1;
    }

    public static boolean elimina(int id) {
        conexion = data.getWritableDatabase();
        long valor = conexion.delete("regdrink",
                "id=?",
                new String[]{""+id+""});
        return valor==1;
    }

    public static ArrayList<Registro> getRegistros() {
        ArrayList<Registro> lista = new ArrayList<>();
        conexion = data.getReadableDatabase();
        Cursor data = conexion.rawQuery("select * from regdrink",null);
        while (data.moveToNext()) {
            lista.add(
                    new Registro(
                            data.getInt(0),
                            data.getString(1),
                            data.getInt(2),
                            data.getString(3)));
        }
        return lista;
    }
}