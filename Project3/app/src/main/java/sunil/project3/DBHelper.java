package sunil.project3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by ander on 8/15/2016.
 */
public class DBHelper extends SQLiteOpenHelper{


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "spaceDB";

    // content provider
    public static final String TABLE_X = ProviderContract.X.TABLE_X;
    public static final String X_ID = BaseColumns._ID;
    public static final String COL_XX = ProviderContract.X.COL_X;
    public static final String COL_XY = ProviderContract.X.COL_Y;

    // normal
    public static final String TABLE_Y = "table_y";
    public static final String Y_ID = BaseColumns._ID;
    public static final String COL_YX = "column_yx";
    public static final String COL_YY = "column_yy";

    private static DBHelper instance;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DBHelper getInstance(Context context){
        if(instance == null){
            instance = new DBHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " +
        TABLE_X + "( "+
                X_ID + " INTEGER NOT NULL, " +
                COL_XX + " TEXT, " +
                COL_XY);
        sqLiteDatabase.execSQL("CREATE TABLE " +
                TABLE_Y + " INTEGER NOT NULL, " +
                COL_YX + " TEXT, " +
                COL_YY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_X);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_Y);
        onCreate(sqLiteDatabase);
    }

    public void 



}
