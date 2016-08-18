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

    public static final class NYT implements BaseColumns {
        public static final String Table_NYT = "nyt_table";
        public static final String COL_1 = "url";
        public static final String COL_2 = "snippet";
        public static final String COL_3 = "lead_paragraph";
        public static final String COL_4 = "image_url";
        public static final String COL_5 = "headline";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, Table_NYT);

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
                + "/sunil.project3.nyt_table";

        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
                + "/sunil.project3.nyt_table";


    }

}
