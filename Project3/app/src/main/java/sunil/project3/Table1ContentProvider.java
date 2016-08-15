package sunil.project3;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by ander on 8/15/2016.
 */
public class Table1ContentProvider extends ContentProvider {

    private DBHelper mDBHelper;

    private static final String TAG = "Table1ContentProvider";
    public static final int AllValuesX = 1;
    public static final int _ID = 2;

    public static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(ProviderContract.AUTHORITY, DBHelper.Table_NYT, AllValuesX);
        uriMatcher.addURI(ProviderContract.AUTHORITY, DBHelper.Table_NYT + "/#", _ID);
    }

    @Override
    public boolean onCreate() {
        mDBHelper = DBHelper.getInstance(getContext());
        Log.d(TAG, "onCreate: getting instance");
        return false;
    }


    @Nullable
    @Override
    public String getType(Uri uri) {
        int uriType = uriMatcher.match(uri);
        switch (uriType) {
            case AllValuesX:
                return ProviderContract.NYT.CONTENT_TYPE;
            case _ID:
                return ProviderContract.NYT.CONTENT_ITEM_TYPE;
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        int uriType = uriMatcher.match(uri);
        long id = 0;
        switch (uriType) {
            case AllValuesX:
                DBHelper dbHelper = DBHelper.getInstance(getContext());
                id = dbHelper.addItems(contentValues);
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(ProviderContract.NYT.CONTENT_URI, id);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int uriType = uriMatcher.match(uri);
        Cursor cursor = null;

        switch (uriType) {
            case AllValuesX:
                cursor = mDBHelper.findStocks(selection, selectionArgs, sortOrder, null);
                break;
            case _ID:
                cursor = mDBHelper.findStocks(selection, selectionArgs, sortOrder, uri.getLastPathSegment());
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        int uriType = uriMatcher.match(uri);
        int rowsUpdated = 0;

        switch (uriType) {
            case AllValuesX:
                rowsUpdated = mDBHelper.updateProduct(values,selection,selectionArgs,null);
                break;
            case _ID:
                String id = uri.getLastPathSegment();
                rowsUpdated = mDBHelper.updateProduct(values,null,null,id);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int uriType = uriMatcher.match(uri);
        int rowsDeleted = 0;
        switch (uriType) {
            case AllValuesX:
                rowsDeleted = mDBHelper.deleteProduct(selection,selectionArgs,null);

                break;
            case _ID:
                String id = uri.getLastPathSegment();
                rowsDeleted = mDBHelper.deleteProduct(null,null,id);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }
}
