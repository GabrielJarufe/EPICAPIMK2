package com.example.epicapi.activities.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static  int VERSION = 1;
    public static  String NOME_DB = "DB_NASA";
    public static  String TABELA_POSICOES = "TB_Posicoess";
    public static  String IDPOS = "IdPos";
    public static  String POSICAOX = "pX";
    public static  String POSICAOY = "pY";
    public static  String POSICAOZ = "pZ";
    public static  String DATAPESQ = "Datapesq";


    public DbHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " +TABELA_POSICOES+
                "("+IDPOS+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                " "+POSICAOX+" TEXT NOT NULL," +
                " "+POSICAOY+" TEXT NOT NULL," +
                " "+POSICAOZ+" TEXT NOT NULL," +
                " "+DATAPESQ+" TEXT NOT NULL"+
                ")";

        try{
            db.execSQL(query);
            Log.i("INFO DB","Sucesso ao criar a tabela");
        }catch(Exception e){
            Log.i("INFO DB","Falha ao criar a tabela");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = "DROP TABLE " +TABELA_POSICOES+";";
        try{
            db.execSQL(query);
            Log.i("INFO DB","Sucesso ao atualizar tabela");
        }catch(Exception e){
            Log.i("INFO DB","Falha ao atualizar tabela");
        }
    }
}
