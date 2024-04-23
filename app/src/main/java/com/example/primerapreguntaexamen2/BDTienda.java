package com.example.primerapreguntaexamen2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDTienda extends SQLiteOpenHelper {

    public BDTienda(Context context) {
        super(context, "Tienda.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Compras(nrocompra INTEGER PRIMARY KEY, nomproducto TEXT,cantidad INTEGER,unidadmed TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Compras");
    }
    //INSERT METHODE FOR COMPRAS
    public Boolean insertuserdata(int nrocompra, String nomproducto, int cantidad, String unidadmed ){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("nrocompra",nrocompra);
        contentValues.put("nomproducto",nomproducto);
        contentValues.put("cantidad",cantidad);
        contentValues.put("unidadmed",unidadmed);

        long result= db.insert("Compras",null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    //VIEWDATA METHOD FOR COMPRAS WITH PARAMETERS
    public Cursor getdata(String nomproducto) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM Compras WHERE nomproducto = ?";
        Cursor cursor = db.rawQuery(query, new String[]{nomproducto});
        return cursor;

    }
    //VIEWDATA METHOD FOR COMPRAS
    public Cursor getdatas() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT* FROM Compras", null);
        return cursor;

    }





}
