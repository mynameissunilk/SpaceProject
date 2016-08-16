package sunil.project3;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;

import java.util.ArrayList;

/**
 * Created by ander on 8/15/2016.
 */
public class DBHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "spaceDB";

    // content provider
    public static final String Table_NYT = ProviderContract.NYT.Table_NYT;
    public static final String _ID = BaseColumns._ID;
    public static final String COL_1 = ProviderContract.NYT.COL_1;
    public static final String COL_2 = ProviderContract.NYT.COL_2;
    public static final String COL_3 = ProviderContract.NYT.COL_3;
    public static final String COL_4 = ProviderContract.NYT.COL_4;
    public static final String COL_5 = ProviderContract.NYT.COL_5;

    private static DBHelper instance;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " +
                Table_NYT + "( " +
                _ID + " INTEGER PRIMARY KEY, " +
                COL_1 + " TEXT, " +
                COL_2 + " TEXT, " +
                COL_3 + " TEXT, " +
                COL_4 + " TEXT, " +
                COL_5 + " TEXT " + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_NYT);
        onCreate(sqLiteDatabase);
    }

//    public void addToTable(String url, String snippet, String para, String imgUrl, String headline) {
//        SQLiteDatabase db = getWritableDatabase();
//        db.execSQL("INSERT INTO " + Table_NYT
//                + "(" + COL_1 + ", " + COL_2 + ", " + COL_3 + ", " + COL_4 + ", " + COL_5 + ")"
//                + " VALUES " + "( " + url + ", " + snippet  + ", " + para + ", " + imgUrl + ", " + headline + ") ");
//        db.close();
//    }

    public Cursor getAllItems() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Table_NYT, null,null,null,null,null,null);
        return cursor;
    }

    public ArrayList<String> getAllItemsArrayList() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList array = new ArrayList();
        Cursor cursor = db.query(Table_NYT, null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                String c1 = cursor.getString(cursor.getColumnIndex(COL_1));
                String c2 = cursor.getString(cursor.getColumnIndex(COL_2));
                String c3 = cursor.getString(cursor.getColumnIndex(COL_3));
                String c4 = cursor.getString(cursor.getColumnIndex(COL_4));
                String c5 = cursor.getString(cursor.getColumnIndex(COL_5));
                array.add(c1);array.add(c2);array.add(c3);array.add(c4);array.add(c5);
                cursor.moveToNext();
            }
        }
        return array;
    }

    public long addItems(ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        long insertedRow = db.insert(Table_NYT, null, values);
        db.close();
        return insertedRow;
    }

    public int deleteProduct(String selection, String[] selectionArgs, String id) {
        SQLiteDatabase db = getWritableDatabase();
        int rowSelected = 0;

        if (id == null) {
            rowSelected = db.delete(Table_NYT,selection,selectionArgs);
        }
        else {
            rowSelected = db.delete(Table_NYT,"WHERE _ID = ?", selectionArgs);
        }
        return rowSelected;
    }

    public int updateProduct(ContentValues values,String selection,String[] selectionArgs,String id) {
        SQLiteDatabase db = getWritableDatabase();
        int rowSelected = 0;

        if (id == null) {
            rowSelected = db.update(Table_NYT, values, selection, selectionArgs);
        } else {
            rowSelected = db.update(Table_NYT, values, "WHERE _ID = ?", selectionArgs);
        }
        return rowSelected;
    }

    public Cursor findStocks(String selection,String[] selectionArgs,String sortOrder, String id){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = null;

        if(id == null) {
            cursor = db.query(Table_NYT, null, selection, selectionArgs, null, null, sortOrder);
        }
        else{
            cursor = db.query(Table_NYT, null, "WHERE _ID = ?", selectionArgs, null,null,sortOrder);
        }

        return cursor;
    }



}
