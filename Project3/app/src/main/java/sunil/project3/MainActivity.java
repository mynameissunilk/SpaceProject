package sunil.project3;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    CursorAdapter mCursorAdapter;
    TextView mT1,mT2,mT3,mT4,mT5;
    ListView mListView;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = DBHelper.getInstance(this);

        ContentValues contentValues = new ContentValues();
        contentValues.put(ProviderContract.NYT.COL_1, "a");
        contentValues.put(ProviderContract.NYT.COL_2, "b");
        contentValues.put(ProviderContract.NYT.COL_3, "c");
        contentValues.put(ProviderContract.NYT.COL_4, "d");
        contentValues.put(ProviderContract.NYT.COL_5, "e");
        getContentResolver().insert(ProviderContract.NYT.CONTENT_URI, contentValues);
        dbHelper.addItems(contentValues);

        mListView = (ListView) findViewById(R.id.listView);

        Cursor cursor = DBHelper.getInstance(MainActivity.this).getAllItems();

        mCursorAdapter = new CursorAdapter(MainActivity.this,cursor,CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(MainActivity.this).inflate(R.layout.list_view_items, parent,false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {

                mT1 = (TextView) view.findViewById(R.id.text_view1);
                mT2 = (TextView) view.findViewById(R.id.text_view2);
                mT3 = (TextView) view.findViewById(R.id.text_view3);
                mT4 = (TextView) view.findViewById(R.id.text_view4);
                mT5 = (TextView) view.findViewById(R.id.text_view5);

                mT1.setText(cursor.getString(cursor.getColumnIndex(DBHelper.COL_1)));
                mT2.setText(cursor.getString(cursor.getColumnIndex(DBHelper.COL_2)));
                mT3.setText(cursor.getString(cursor.getColumnIndex(DBHelper.COL_3)));
                mT4.setText(cursor.getString(cursor.getColumnIndex(DBHelper.COL_4)));
                mT5.setText(cursor.getString(cursor.getColumnIndex(DBHelper.COL_5)));

                Log.i(TAG, "bindView: " + mT1.getText().toString());

            }
        };

        mListView.setAdapter(mCursorAdapter);


    }
}
