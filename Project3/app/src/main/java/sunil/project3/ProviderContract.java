package sunil.project3;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by ander on 8/15/2016.
 */
public class ProviderContract {
    public static final String AUTHORITY = "sunil.project3.Table1ContentProvider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final class X implements BaseColumns {
        public static final String TABLE_X = "X_table";
        public static final String COL_X = "name";
        public static final String COL_Y = "description";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, TABLE_X);

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
                + "/sunil.project3.x_TABLE";

        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
                + "/sunil.project3.x_TABLE";


    }

//    public static final class Y implements BaseColumns {
//        public static final String TABLE_Y = "stock_table";
//        public static final String COL_X = "name";
//        public static final String COL_Y = "description";
//
//        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, TABLE_Y);
//    }

}
