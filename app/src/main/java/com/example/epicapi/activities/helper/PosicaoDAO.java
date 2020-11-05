package com.example.epicapi.activities.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.epicapi.activities.Models.Posicao;

import java.util.ArrayList;
import java.util.List;

import static com.example.epicapi.activities.helper.DbHelper.POSICAOX;
import static com.example.epicapi.activities.helper.DbHelper.POSICAOY;
import static com.example.epicapi.activities.helper.DbHelper.POSICAOZ;
import static com.example.epicapi.activities.helper.DbHelper.DATAPESQ;
import static com.example.epicapi.activities.helper.DbHelper.POSICAOX;

public class PosicaoDAO implements IPosicaoDAO{

    private SQLiteDatabase escreve;
    private SQLiteDatabase ler;

    public PosicaoDAO(Context context) {
        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        ler = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Posicao posicao) {
        ContentValues cv = new ContentValues();
        cv.put(POSICAOX,posicao.getPx());
        cv.put(POSICAOY,posicao.getPy());
        cv.put(POSICAOZ,posicao.getPz());
        cv.put(DATAPESQ,posicao.getDatapesq());
        try{
            escreve.insert(DbHelper.TABELA_POSICOES,null,cv);
            Log.i("INFO","Tarefa salva com sucesso!");

        }catch(Exception e){
            Log.i("INFO","Erro ao salvar tarefa");
            return false;
        }

        return true;
    }

    @Override
    public List<Posicao> listar() {

        ArrayList<Posicao> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM " + DbHelper.TABELA_POSICOES+" ;";
        Cursor c = ler.rawQuery(sql,null);

        while(c.moveToNext()){
            Posicao posicao = new Posicao();
            Long id = c.getLong(c.getColumnIndex("IdPos"));
            String pX = c.getString(c.getColumnIndex("pX"));
            String pY = c.getString(c.getColumnIndex("pY"));
            String pZ = c.getString(c.getColumnIndex("pZ"));
            String data = c.getString(c.getColumnIndex("Datapesq"));
            posicao.setId(id);
            posicao.setPx(pX);
            posicao.setPx(pY);
            posicao.setPx(pZ);
            posicao.setDatapesq(data);

            tarefas.add(posicao);
        }
        return tarefas;
    }
}
