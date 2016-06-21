package com.ibm.afcodetest.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ibm.afcodetest.utils.AFDataListener;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

/**
 * Created by sheldongabriel on 6/16/16.
 */
public class DBHandler extends SQLiteOpenHelper implements AFDataListener {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "abercrombie.db";
    private static final String TABLE_NAME = "abercrombie_table";
    private static final String KEY_ID = "_id";

    public static final String KEY_BACKROUND_URL = "_backgroundurl";
    public static final String KEY_TOP_DESC = "_topdesc";
    public static final String KEY_TITLE = "_title";
    public static final String KEY_PROMO = "_promo";
    public static final String KEY_BOTTOM_DESC = "_bottomdesc";
    public static final String KEY_CONTENT_TITLE = "_contenttitle";
    public static final String KEY_CONTENT_TARGET = "_contenttarget";

    public static final String KEY_CONTENT_ARRAY = "_contentarray";

    //String CREATE_TABLE = "CREATE TABLE " +TABLE_NAME+ " ("+KEY_ID+" INTEGER PRIMARY KEY,"+KEY_BACKROUND_URL+" TEXT,"
      //      +KEY_TOP_DESC+" TEXT," +KEY_TITLE + " TEXT," +KEY_PROMO+ " TEXT,"+KEY_BOTTOM_DESC + " TEXT,"+KEY_CONTENT_TARGET+" TEXT,"+KEY_CONTENT_TITLE+" TEXT)";

    String CREATE_TABLE = "CREATE TABLE " +TABLE_NAME+ " ("+KEY_ID+" INTEGER PRIMARY KEY,"+KEY_BACKROUND_URL+" TEXT,"
          +KEY_TOP_DESC+" TEXT," +KEY_TITLE + " TEXT," +KEY_PROMO+ " TEXT,"+KEY_BOTTOM_DESC + " TEXT,"+KEY_CONTENT_ARRAY+" TEXT)";
    String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    public DBHandler(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DROP_TABLE);
        onCreate(db);
    }


    @Override
    public void addAFData(AFData data) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {

            ContentValues values = new ContentValues();
            values.put(KEY_BACKROUND_URL,data.getBackGroundUrl());
            values.put(KEY_TOP_DESC,data.getTopDesc());
            values.put(KEY_TITLE,data.getTitle());
            values.put(KEY_PROMO,data.getPromoMsg());
            values.put(KEY_BOTTOM_DESC,data.getBottomDesc());
            values.put(KEY_CONTENT_ARRAY,data.getContentArray());

            //values.put(KEY_CONTENT_TARGET,data.getContentTarget());
            //values.put(KEY_CONTENT_TITLE,data.getContentTitle());

            db.insert(TABLE_NAME,null,values);
            db.close();
        }
        catch (Exception ex) {
            StringWriter sw = new StringWriter();

            ex.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            System.out.println("ERROR INSERTING INTO DATABASE: " +exceptionAsString  );
        }


    }

    @Override
    public ArrayList<AFData> getAllAFData() {

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<AFData> dataList = null;

        try {
            dataList = new ArrayList<AFData>();
            String QUERY = "SELECT * FROM "+TABLE_NAME;
            Cursor cursor = db.rawQuery(QUERY,null);

            if(!cursor.isLast()) {

                while (cursor.moveToNext()) {
                    AFData data = new AFData();
                    data.setId(cursor.getInt(0));
                    data.setBackGroundUrl(cursor.getString(1));
                    data.setTopDesc(cursor.getString(2));
                    data.setTitle(cursor.getString(3));
                    data.setPromoMsg(cursor.getString(4));
                    data.setBottomDesc(cursor.getString(5));
                    data.setContentArray(cursor.getString(6));
                    //data.setContentTarget(cursor.getString(6));
                    //data.setContentTitle(cursor.getString(7));
                    dataList.add(data);
                }
            }
            db.close();
        }
        catch (Exception ex) {

            StringWriter sw = new StringWriter();

            ex.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            System.out.println("ERROR GETTING ALL VALUES FROM DATABASE: " +exceptionAsString  );
        }

        return dataList;
    }

    @Override
    public int getAFDataCount() {

        int num = 0;
        SQLiteDatabase db = this.getReadableDatabase();

        try {
            String QUERY = "SELECT * FROM " +TABLE_NAME;
            Cursor cursor = db.rawQuery(QUERY,null);
            num = cursor.getCount();
            db.close();

            return num;
        }
        catch (Exception ex) {

            StringWriter sw = new StringWriter();

            ex.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            System.out.println("ERROR GETTING DATA COUNT FROM DATABASE: " +exceptionAsString  );
        }

        return 0;
    }
}
