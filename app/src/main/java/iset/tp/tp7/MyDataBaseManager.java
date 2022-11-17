package iset.tp.tp7;

import static iset.tp.tp7.MyDataBaseHelper.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyDataBaseManager {
    private MyDataBaseHelper dbHelper;
    private SQLiteDatabase db;
    private Context context;

    public MyDataBaseManager(Context context) {
        dbHelper=new MyDataBaseHelper(context);
        this.context=context;
    }

    void addResult(Resultat r){
        db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NOM, r.getNom());
        cv.put(COLUMN_PRENOM, r.getPrenom());
        cv.put(COLUMN_MOYENNE, r.getMoyenne());
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    List<Resultat> readAllResult(){
        List<Resultat> lesResultats=new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        db = dbHelper.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }

        if(cursor!=null) {
            while (cursor.moveToNext()) {
                int _id = cursor.getInt(0);
                String _nom = cursor.getString(1);
                String _prenom = cursor.getString(2);
                Float _moyenne = cursor.getFloat(3);

                Resultat res=new Resultat(_id,_nom,_prenom,_moyenne);
                lesResultats.add(res);

            }
        }
        return lesResultats;
    }

    void updateResult(Resultat res){
        db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NOM, res.getNom());
        cv.put(COLUMN_PRENOM, res.getPrenom());
        cv.put(COLUMN_MOYENNE, res.getMoyenne());

        long result = db.update(TABLE_NAME, cv, "id=?", new String[]{String.valueOf(res.getId())});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully! id:"+res.getId()+" ,nom:"+res.getNom(), Toast.LENGTH_SHORT).show();
        }

    }

    void deleteResult(String id){
        db = dbHelper.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id=?", new String[]{id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllResult(){
        db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}



