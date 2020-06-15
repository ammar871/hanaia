package com.example.hanaiabeauty.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.hanaiabeauty.model.Favoraite;

import java.util.ArrayList;

public class SqliteFavoraite extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 7;

    private static final String TABLE_FAV = "Favorite";
    private static final String DATABASE_NAME = "contact";


    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_SIZE = "size";
    private static final String COLUMN_COLOR = "color";
    private static final String COLUMN_NAME = "ProudectName";
    private static final String COLUMN_describtion = "descrb";
    private static final String USER_PHON = "userphone";


    public SqliteFavoraite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FAVE_TABLE = "CREATE TABLE " + TABLE_FAV + "(" + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + USER_PHON + " TEXT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_SIZE + " TEXT,"
                + COLUMN_COLOR + " TEXT,"
                + COLUMN_PRICE + " TEXT,"
                + COLUMN_describtion + " TEXT,"
                + COLUMN_IMAGE + " TEXT"
                + ")";


        db.execSQL(CREATE_FAVE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAV);
        onCreate(db);
    }
    public void AddFavorit(Favoraite food) {

        SQLiteDatabase db = getReadableDatabase();
        String Quary = String.format("INSERT INTO Favorite ("+ USER_PHON + ","
                        + COLUMN_NAME + ","  + COLUMN_SIZE + ","  + COLUMN_COLOR + "," + COLUMN_PRICE + ","
                        + COLUMN_describtion + "," + COLUMN_IMAGE +")" +
                        " VALUES ('%s','%s','%s','%s','%s','%s','%s');",
                food.getUserphone(),
                food.getFavname(),
                food.getSize(),
                food.getColore(),
                food.getFavprice(),
                food.getFavdesc(),
                food.getFacimage()
              );
        db.execSQL(Quary);
    }

    public void removeFavorit(String foodid, String userPhone) {
        SQLiteDatabase db = getReadableDatabase();

        String Quary = String.format("DELETE FROM Favorite WHERE "+COLUMN_NAME+"='%s' and "+USER_PHON+"='%s';", foodid, userPhone);
        db.execSQL(Quary);
    }
    public boolean isFavorit(String foodid, String userphone) {


        SQLiteDatabase db = getReadableDatabase();

        String Quary = String.format("SELECT * FROM Favorite WHERE " + COLUMN_NAME + "= '%s' and " + USER_PHON + "= '%s';", foodid, userphone);
        Cursor cursor = db.rawQuery(Quary, null);
        if (cursor.getCount() <= 0) {

            cursor.close();
            return false;
        }
        cursor.close();

        return true;
    }

    public ArrayList<Favoraite> getAllFavoraite(String userPhone) {

        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] clomuns = {USER_PHON, COLUMN_NAME,COLUMN_SIZE,COLUMN_COLOR, COLUMN_PRICE, COLUMN_describtion, COLUMN_IMAGE};

        String taqtable = TABLE_FAV;
        qb.setTables(taqtable);
        Cursor cursor = qb.query(db, clomuns, null, null, null, null, null);

        ArrayList<Favoraite> resulte = new ArrayList<>();
        //   Cursor cursor = db.rawQuery(sql, null);
        int id;
        if (cursor.moveToFirst()) {
            do {
                resulte.add(new Favoraite(

                        cursor.getString(cursor.getColumnIndex(USER_PHON)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_SIZE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_COLOR)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_PRICE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_describtion)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE))
                        ));


                //  storeContacts.add(new Contacts(id,image,name,phno));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return resulte;
    }

}