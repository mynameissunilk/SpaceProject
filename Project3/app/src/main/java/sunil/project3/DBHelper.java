package sunil.project3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

import sunil.project3.CardObjects.CalendarEventObject;
import sunil.project3.CardObjects.CardObjSingleton;
import sunil.project3.CardObjects.CardObject;

/**
 * Created by ander on 8/15/2016.
 */
public class DBHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "spaceDB";

    // content provider
    public static final String Table_NYT = ProviderContract.NYT.Table_NYT;
    public static final String _ID = BaseColumns._ID;
    public static final String COL_1 = ProviderContract.NYT.COL_1;
    public static final String COL_2 = ProviderContract.NYT.COL_2;
    public static final String COL_3 = ProviderContract.NYT.COL_3;
    public static final String COL_4 = ProviderContract.NYT.COL_4;
    public static final String COL_5 = ProviderContract.NYT.COL_5;


    public static final String Table_Guardian = "GuardianTable";
    public static final String GUARDIAN_ID = "_id";
    public static final String GUARDIAN_TITLE_COL = "title";
    public static final String GUARDIAN_URL_COL = "url";

    public static final String Table_APOD = "APODTable";
    public static final String APOD_ID = "_id";
    public static final String APOD_TITLE_COL = "apodtitle";
    public static final String APOD_EXPLANATION_COL = "explanation";
    public static final String APOD_URL_COL = "image_url";

    public static final String Table_NPR = "NPRTable";
    public static final String NPR_ID = "_id";
    public static final String NPR_TITLE_COL = "nprtitle";
    public static final String NPR_ABSTRACT_COL = "abstract";
    public static final String NPR_DATE_COL = "date";
    public static final String NPR_URL_COL = "nprurl";

    public static final String Table_Astro_Events = "calendarTable";
    public static final String CAL_ID = "_id";
    public static final String CAL_TITLE_COL = "event_title";
    public static final String CAL_WEEKDAY_COL = "weekday";
    public static final String CAL_YEAR_COL = "year";
    public static final String CAL_MONTH_COL = "month";
    public static final String CAL_DAY_COL = "day";
    public static final String CAL_HOUR_COL = "hour";
    public static final String CAL_MINUTE_COL = "minute";
    public static final String CAL_URL_COL = "detailurl";


    private static DBHelper instance;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_APOD_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_CALENDAR_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_NPR_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_GUARDIAN_TABLE);

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
        sqLiteDatabase.execSQL(SQL_DELETE_APOD_TABLE);
        sqLiteDatabase.execSQL(SQL_DELETE_CALENDAR_TABLE);
        sqLiteDatabase.execSQL(SQL_DELETE_GUARDIAN_TABLE);
        sqLiteDatabase.execSQL(SQL_DELETE_NPR_TABLE);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_NYT);
        onCreate(sqLiteDatabase);
    }


    public static final String SQL_CREATE_GUARDIAN_TABLE = "CREATE TABLE " +
            Table_Guardian + "( " +
            GUARDIAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            GUARDIAN_TITLE_COL + " TEXT, " +
            GUARDIAN_URL_COL + " TEXT)";
    public static final String SQL_CREATE_APOD_TABLE = "CREATE TABLE " +
            Table_APOD + "( " +
            APOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            APOD_TITLE_COL + " TEXT, " +
            APOD_EXPLANATION_COL + " TEXT, " +
            APOD_URL_COL + " TEXT)";
    public static final String SQL_CREATE_NPR_TABLE = "CREATE TABLE " +
            Table_NPR + "( " +
            NPR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NPR_TITLE_COL + " TEXT, " +
            NPR_ABSTRACT_COL + " TEXT, " +
            NPR_DATE_COL + " TEXT, " +
            NPR_URL_COL + " TEXT)";
    public static final String SQL_CREATE_CALENDAR_TABLE = "CREATE TABLE " +
            Table_Astro_Events + "( " +
            CAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CAL_TITLE_COL + " TEXT, " +
            CAL_WEEKDAY_COL + " TEXT, " +
            CAL_YEAR_COL + " INT, " +
            CAL_MONTH_COL + " INT, " +
            CAL_DAY_COL + " INT, " +
            CAL_HOUR_COL + " INT, " +
            CAL_MINUTE_COL + " INT, " +
            CAL_URL_COL + " TEXT)";

    public static final String SQL_DELETE_GUARDIAN_TABLE = "DROP TABLE IF EXISTS " + Table_Guardian;
    public static final String SQL_DELETE_APOD_TABLE = "DROP TABLE IF EXISTS " + Table_APOD;
    public static final String SQL_DELETE_NPR_TABLE = "DROP TABLE IF EXISTS " + Table_NPR;
    public static final String SQL_DELETE_CALENDAR_TABLE = "DROP TABLE IF EXISTS " + Table_Astro_Events;


    public void addGuardianToTable(GuardianArticle g) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(GUARDIAN_TITLE_COL, g.getTitle());
        cv.put(GUARDIAN_URL_COL, g.getURL());
        db.insert(Table_Guardian, null, cv);
        db.close();
    }

    public void addListToTable(ArrayList<CardObject> list){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) instanceof NprArticle){
                NprArticle npr = (NprArticle) list.get(i);
                cv.put(NPR_TITLE_COL, npr.getTitle());
                cv.put(NPR_ABSTRACT_COL, npr.getParagraph());
                cv.put(NPR_DATE_COL, npr.getDate());
                cv.put(NPR_URL_COL, npr.getURL());
                db.insert(Table_NPR, null, cv);
            }
            else if(list.get(i) instanceof GuardianArticle){
                GuardianArticle g = (GuardianArticle) list.get(i);
                cv.put(GUARDIAN_TITLE_COL, g.getTitle());
                cv.put(GUARDIAN_URL_COL, g.getURL());
                db.insert(Table_Guardian, null, cv);
            }
        }
        db.close();
    }



    public void addAPODToTable(APOD a) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(APOD_TITLE_COL, a.getTitle());
        cv.put(APOD_EXPLANATION_COL, a.getExplanation());
        cv.put(APOD_URL_COL, a.getURL());
        db.insert(Table_APOD, null, cv);
        db.close();
    }





    public void addNPRToTable(NprArticle npr) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NPR_TITLE_COL, npr.getTitle());
        cv.put(NPR_ABSTRACT_COL, npr.getParagraph());
        cv.put(NPR_DATE_COL, npr.getDate());
        cv.put(NPR_URL_COL, npr.getURL());
        db.insert(Table_NPR, null, cv);
        db.close();
    }

    public void addCalendarToTable(CalendarEventObject o) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CAL_TITLE_COL, o.getmEventTitle());
        cv.put(CAL_WEEKDAY_COL, o.getmWeekDay());
        cv.put(CAL_YEAR_COL, o.getmYear());
        cv.put(CAL_MONTH_COL, o.getmMonth());
        cv.put(CAL_DAY_COL, o.getmDay());
        cv.put(CAL_HOUR_COL, o.getmHour());
        cv.put(CAL_MINUTE_COL, o.getmMinute());
        cv.put(CAL_URL_COL, o.getmDetailUrl());
        db.insert(Table_Astro_Events, null, cv);
        db.close();
    }



    public GuardianArticle createArticleFromTable() {
        //GuardianArticle guardianArticle;
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + Table_Guardian;
        Cursor cursor = db.rawQuery(query, null);
//        if(cursor.moveToFirst()){
//            while(!cursor.isAfterLast()){
//                String title = cursor.getString(cursor.getColumnIndex(GUARDIAN_TITLE_COL));
//                String url = (cursor.getString(cursor.getColumnIndex(GUARDIAN_URL_COL)));
//            }DO I NEED THIS LOOP ANYMORE?
//        }
//        cursor.close(); CLOSE THIS EVENTUALLY.......
        return new GuardianArticle(cursor.getString(cursor.getColumnIndex(GUARDIAN_TITLE_COL)), cursor.getString(cursor.getColumnIndex(GUARDIAN_URL_COL)));
    }

    public APOD createAPODFromTable() {
        APOD apod = new APOD();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + Table_APOD;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                apod.setTitle(cursor.getString(cursor.getColumnIndex(APOD_TITLE_COL)));
                apod.setExplanation(cursor.getString(cursor.getColumnIndex(APOD_EXPLANATION_COL)));
                apod.setURL(cursor.getString(cursor.getColumnIndex(APOD_URL_COL)));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return apod;
    }

    public boolean checkAPOD() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + Table_APOD, null);
        Boolean rowExists;
        if (mCursor.moveToFirst()){// do nothing
            return true;
        } else {// Table is Empty
             return false;
        }
    }

    public NprArticle createNPRFromTable() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + Table_APOD;
        Cursor cursor = db.rawQuery(query, null);
//        cursor.close(); CLOSE THIS EVENTUALLY.......
        return new NprArticle(cursor.getString(cursor.getColumnIndex(NPR_TITLE_COL)), cursor.getString(cursor.getColumnIndex(NPR_ABSTRACT_COL)), cursor.getString(cursor.getColumnIndex(NPR_DATE_COL)), cursor.getString(cursor.getColumnIndex(NPR_URL_COL)));
    }

    public ArrayList<CardObject> getGuardianListFromDb() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<CardObject> guardianList = new ArrayList<>();
        String query = "SELECT "+ GUARDIAN_TITLE_COL+", "+GUARDIAN_URL_COL+" FROM " + Table_Guardian;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                GuardianArticle g = new GuardianArticle(cursor.getString(cursor.getColumnIndex(GUARDIAN_TITLE_COL)),
                        cursor.getString(cursor.getColumnIndex(GUARDIAN_URL_COL)));
                guardianList.add(g);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return guardianList;
    }


    public boolean checkGuardian() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + Table_Guardian, null);
        Boolean rowExists;
        if (mCursor.moveToFirst()){// do nothing
            return true;
        } else {// Table is Empty
            return false;
        }
    }

    public ArrayList<CardObject> getNPRListFromDb() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<CardObject> nprList = new ArrayList<>();
        String query = "SELECT " + NPR_TITLE_COL+", "+NPR_ABSTRACT_COL+", "+NPR_DATE_COL+", "
               + NPR_URL_COL+ " FROM " + Table_NPR;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                NprArticle npr = new NprArticle(cursor.getString(cursor.getColumnIndex(NPR_TITLE_COL)),
                        cursor.getString(cursor.getColumnIndex(NPR_ABSTRACT_COL)),
                        cursor.getString(cursor.getColumnIndex(NPR_DATE_COL)),
                        cursor.getString(cursor.getColumnIndex(NPR_URL_COL)));
                nprList.add(npr);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return nprList;
    }

    public boolean checkNPR() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + Table_NPR, null);
        Boolean rowExists;
        if (mCursor.moveToFirst()){// do nothing
            return true;
        } else {// Table is Empty
            return false;
        }
    }


    //CALENDAR BUSINESS

    public void deleteCalendarTableContents() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Table_Astro_Events, null, null);
        db.execSQL("delete from " + Table_Astro_Events);
        db.close();
    }

    //this method seems to work
    //so in the main activity, i insert all the events into the db
    public ArrayList<CardObject> getEventListFromDb() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<CardObject> astroEvents = new ArrayList<>();
        String query = "SELECT * FROM " + Table_Astro_Events;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                CalendarEventObject event = new CalendarEventObject(cursor.getString(cursor.getColumnIndex(CAL_TITLE_COL)),
                        cursor.getString(cursor.getColumnIndex(CAL_WEEKDAY_COL)), cursor.getColumnIndex(CAL_YEAR_COL),
                        cursor.getColumnIndex(CAL_MONTH_COL), cursor.getColumnIndex(CAL_DAY_COL), cursor.getColumnIndex(CAL_HOUR_COL),
                        cursor.getColumnIndex(CAL_MINUTE_COL), cursor.getString(cursor.getColumnIndex(CAL_URL_COL)));
                astroEvents.add(event);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return astroEvents;
    }






    /** stuff we are not using just now */

//    public void addToTable(String url, String snippet, String para, String imgUrl, String headline) {
//        SQLiteDatabase db = getWritableDatabase();
//        db.execSQL("INSERT INTO " + Table_NYT
//                + "(" + COL_1 + ", " + COL_2 + ", " + COL_3 + ", " + COL_4 + ", " + COL_5 + ")"
//                + " VALUES " + "( " + url + ", " + snippet  + ", " + para + ", " + imgUrl + ", " + headline + ") ");
//        db.close();
//    }

    public Cursor getAllItems() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Table_NYT, null, null, null, null, null, null);
        return cursor;
    }

    public ArrayList<String> getAllItemsArrayList() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList array = new ArrayList();
        Cursor cursor = db.query(Table_NYT, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String c1 = cursor.getString(cursor.getColumnIndex(COL_1));
                String c2 = cursor.getString(cursor.getColumnIndex(COL_2));
                String c3 = cursor.getString(cursor.getColumnIndex(COL_3));
                String c4 = cursor.getString(cursor.getColumnIndex(COL_4));
                String c5 = cursor.getString(cursor.getColumnIndex(COL_5));
                array.add(c1);
                array.add(c2);
                array.add(c3);
                array.add(c4);
                array.add(c5);
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
            rowSelected = db.delete(Table_NYT, selection, selectionArgs);
        } else {
            rowSelected = db.delete(Table_NYT, "WHERE _ID = ?", selectionArgs);
        }
        return rowSelected;
    }

    public int updateProduct(ContentValues values, String selection, String[] selectionArgs, String id) {
        SQLiteDatabase db = getWritableDatabase();
        int rowSelected = 0;

        if (id == null) {
            rowSelected = db.update(Table_NYT, values, selection, selectionArgs);
        } else {
            rowSelected = db.update(Table_NYT, values, "WHERE _ID = ?", selectionArgs);
        }
        return rowSelected;
    }

    public Cursor findStocks(String selection, String[] selectionArgs, String sortOrder, String id) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = null;

        if (id == null) {
            cursor = db.query(Table_NYT, null, selection, selectionArgs, null, null, sortOrder);
        } else {
            cursor = db.query(Table_NYT, null, "WHERE _ID = ?", selectionArgs, null, null, sortOrder);
        }

        return cursor;
    }


}
