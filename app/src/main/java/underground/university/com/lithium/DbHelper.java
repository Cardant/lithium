package underground.university.com.lithium;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//http://www.androidhive.info/2013/09/android-sqlite-database-with-multiple-tables/
//Administrateur:500::f656df5fd867591cf0dc9cf4ba44ab68:::
public class DbHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Lithium.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BUILDING);
        db.execSQL(CREATE_TABLE_FLOOR);
        db.execSQL(CREATE_TABLE_ROOM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Contract.Building.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Contract.Floor.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Contract.Room.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String CREATE_TABLE_BUILDING =
            "CREATE TABLE " + Contract.Building.TABLE_NAME + " (" +
                    Contract.Building._ID + " INTEGER PRIMARY KEY," +
                    Contract.Building.COLUMN_ID + TEXT_TYPE + COMMA_SEP +
                    Contract.Building.COLUMN_LETTER + TEXT_TYPE + " )";
    private static final String CREATE_TABLE_FLOOR =
            "CREATE TABLE " + Contract.Floor.TABLE_NAME + " (" +
                    Contract.Floor._ID + " INTEGER PRIMARY KEY," +
                    Contract.Floor.COLUMN_ID + TEXT_TYPE + COMMA_SEP +
                    Contract.Floor.COLUMN_NUMBER + INTEGER_TYPE + " )";
    private static final String CREATE_TABLE_ROOM =
            "CREATE TABLE " + Contract.Room.TABLE_NAME + " (" +
                    Contract.Room._ID +  " INTEGER PRIMARY KEY," +
                    Contract.Room.COLUMN_ID + TEXT_TYPE + COMMA_SEP +
                    Contract.Room.COLUMN_CODE + TEXT_TYPE + " )";
}
