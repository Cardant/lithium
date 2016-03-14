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
        public static final String TABLE_NAME = "floor";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NUMBER = "number";
        public static final String COLUMN_BUILDING = "building";
    }
    public abstract class Room implements BaseColumns {
        public static final String TABLE_NAME = "room";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_CODE = "code";
        public static final String COLUMN_FLOOR = "floor";

    }
    public abstract class Event implements BaseColumns {
        public static final String TABLE_NAME = "event";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_START = "start";
        public static final String COLUMN_END = "end";
        public static final String COLUMN_CAPTION = "caption";
        public static final String COLUMN_LOCATION = "location";
        public static final String COLUMN_DESCRIPTION = "description";
    }
}
