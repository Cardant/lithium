package underground.university.com.lithium;

import android.provider.BaseColumns;

public final class Contract {
    public Contract() {}

    public abstract class Building implements BaseColumns {
        public static final String TABLE_NAME = "building";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_LETTER = "letter";
    }
    public abstract class Floor implements BaseColumns {
        public static final String TABLE_NAME = "building";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NUMBER = "number";
    }
    public abstract class Room implements BaseColumns {
        public static final String TABLE_NAME = "building";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_CODE = "code";
    }
}
