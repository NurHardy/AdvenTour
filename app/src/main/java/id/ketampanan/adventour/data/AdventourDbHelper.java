package id.ketampanan.adventour.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Calendar;

import id.ketampanan.adventour.data.AdventourContract.TravelJournal;

/**
 * Created by aadddrr on 09/06/2016.
 */

/**
 * Class: AdventourDbHelper
 * Responsibility: Menangani database local Adventour
 */

public class AdventourDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Adventour.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String NUMBER_TYPE = " INTEGER";
    private static final String UNIQUE = " UNIQUE";
    private static final String NOT_NULL = " NOT NULL";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_TRAVEL_JOURNAL =
            "CREATE TABLE " + TravelJournal.TABLE_NAME + " (" +
                    TravelJournal._ID + " INTEGER PRIMARY KEY," +
                    TravelJournal.COLUMN_NAME_ID_TRAVELJOURNAL + NUMBER_TYPE + UNIQUE + COMMA_SEP +
                    TravelJournal.COLUMN_NAME_USER + TEXT_TYPE + COMMA_SEP +
                    TravelJournal.COLUMN_NAME_ID_LAYOUT + NUMBER_TYPE + COMMA_SEP +
                    TravelJournal.COLUMN_NAME_ORIGN + TEXT_TYPE + COMMA_SEP +
                    TravelJournal.COLUMN_NAME_DATE_ORIGN + TEXT_TYPE + COMMA_SEP +
                    TravelJournal.COLUMN_NAME_DESTINATION + TEXT_TYPE + COMMA_SEP +
                    TravelJournal.COLUMN_NAME_DATE_RETURN + TEXT_TYPE + COMMA_SEP +
                    TravelJournal.COLUMN_NAME_DATE_CREATED + TEXT_TYPE + COMMA_SEP +
                    TravelJournal.COLUMN_NAME_TOTAL_BUDGET + NUMBER_TYPE + COMMA_SEP +
                    TravelJournal.COLUMN_NAME_FIRST_PAGE + TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_TRAVEL_JOURNAL =
            "DROP TABLE IF EXISTS " + TravelJournal.TABLE_NAME;

    public AdventourDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.v("DbHelper", "new");
    }
    public void onCreate(SQLiteDatabase db) {
        Log.v("DbHelper", "onCreate");
        Log.v("DbHelper", "SQL Create" + SQL_CREATE_TRAVEL_JOURNAL);
        db.execSQL(SQL_CREATE_TRAVEL_JOURNAL);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_TRAVEL_JOURNAL);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /**
     * Method: testAdventour
     * Fungsi: Menguji CRUD database local Adventour
     */
    public void testAdventour() {

        Calendar calendar = Calendar.getInstance();

        Integer id_travel_journal = 1;
        Integer id_user = 1;
        Integer id_layout = 1;
        String origin = "origin";
        String destination = "destination";
        String date_created = calendar.getTime().toString();
        Integer budget = 1;
        String first_page = "first_page";

        long rowId;

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        Log.v("DbHelper", " "+ db.getVersion());

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(TravelJournal.COLUMN_NAME_ID_TRAVELJOURNAL, id_travel_journal);
        values.put(TravelJournal.COLUMN_NAME_USER, id_user);
        values.put(TravelJournal.COLUMN_NAME_ID_LAYOUT, id_layout);
        values.put(TravelJournal.COLUMN_NAME_ORIGN, origin);
        values.put(TravelJournal.COLUMN_NAME_DESTINATION, destination);
        values.put(TravelJournal.COLUMN_NAME_DATE_CREATED, date_created);
        values.put(TravelJournal.COLUMN_NAME_TOTAL_BUDGET, budget);
        values.put(TravelJournal.COLUMN_NAME_FIRST_PAGE, first_page);

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                TravelJournal.TABLE_NAME,
                null,
                values);


        rowId = newRowId;

        SQLiteDatabase dbr = this.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                TravelJournal._ID,
                TravelJournal.COLUMN_NAME_ID_TRAVELJOURNAL,
                TravelJournal.COLUMN_NAME_USER,
                TravelJournal.COLUMN_NAME_ID_LAYOUT,
                TravelJournal.COLUMN_NAME_ORIGN,
                TravelJournal.COLUMN_NAME_DESTINATION,
                TravelJournal.COLUMN_NAME_DATE_CREATED,
                TravelJournal.COLUMN_NAME_TOTAL_BUDGET,
                TravelJournal.COLUMN_NAME_FIRST_PAGE,
        };

        // How you want the results sorted in the resulting Cursor
        //String sortOrder = TravelJournal.COLUMN_NAME_UPDATED + " DESC";

        Cursor cursor = dbr.query(
                TravelJournal.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        cursor.moveToFirst();
        long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(TravelJournal._ID));
        String itemOrign = cursor.getString(cursor.getColumnIndexOrThrow(TravelJournal.COLUMN_NAME_ORIGN));

        Log.v("Item Id:", itemId+"");
        Log.v("Item Origin:", itemOrign+"");

        SQLiteDatabase dbu = this.getReadableDatabase();

        // New value for one column
        ContentValues updateValues = new ContentValues();
        updateValues.put(TravelJournal.COLUMN_NAME_ORIGN, "origin2");

        // Which row to update, based on the ID
        String selection = TravelJournal.COLUMN_NAME_ID_TRAVELJOURNAL + " LIKE ?";
        String[] selectionArgs = {String.valueOf(rowId)};

        int count = dbu.update(
                TravelJournal.TABLE_NAME,
                updateValues,
                selection,
                selectionArgs);


        /*
        SQLiteDatabase dbd = this.getWritableDatabase();

        // Define 'where' part of query.
        String selection = TravelJournal.COLUMN_NAME_ID_TRAVELJOURNAL + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { String.valueOf(rowId) };
        // Issue SQL statement.
        dbd.delete(TravelJournal.TABLE_NAME, selection, selectionArgs); */
    }

    public void insertTravelJournals(ContentValues values){
        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        Log.v("DbHelper", " "+ db.getVersion());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                TravelJournal.TABLE_NAME,
                null,
                values);
    }

    public Cursor readTravelJournals(){
        SQLiteDatabase dbr = this.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                TravelJournal._ID,
                TravelJournal.COLUMN_NAME_ID_TRAVELJOURNAL,
                TravelJournal.COLUMN_NAME_USER,
                TravelJournal.COLUMN_NAME_ID_LAYOUT,
                TravelJournal.COLUMN_NAME_ORIGN,
                TravelJournal.COLUMN_NAME_DATE_ORIGN,
                TravelJournal.COLUMN_NAME_DESTINATION,
                TravelJournal.COLUMN_NAME_DATE_RETURN,
                TravelJournal.COLUMN_NAME_DATE_CREATED,
                TravelJournal.COLUMN_NAME_TOTAL_BUDGET,
                TravelJournal.COLUMN_NAME_FIRST_PAGE,
        };

        // How you want the results sorted in the resulting Cursor
        //String sortOrder = TravelJournal.COLUMN_NAME_UPDATED + " DESC";

        Cursor cursor = dbr.query(
                TravelJournal.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        /*cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(TravelJournal._ID));
            long itemIdTravelJornal = cursor.getLong(cursor.getColumnIndexOrThrow(TravelJournal.COLUMN_NAME_ID_TRAVELJOURNAL));
            String itemPicLink = cursor.getString(cursor.getColumnIndexOrThrow(TravelJournal.COLUMN_NAME_FIRST_PAGE));
            String itemOrign = cursor.getString(cursor.getColumnIndexOrThrow(TravelJournal.COLUMN_NAME_ORIGN));

            Log.v("DbRead", "Id:" + itemId);
            Log.v("DbRead", "IdTravelJournal:" + itemIdTravelJornal);
            Log.v("DbRead", "LinkGambar:" + itemPicLink);
            Log.v("DbRead", "Orign:" + itemOrign);

            cursor.moveToNext();
        }*/

        return cursor;
    }
}