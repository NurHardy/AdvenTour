package id.ketampanan.adventour.data;

import android.provider.BaseColumns;

/**
 * Created by aadddrr on 09/06/2016.
 */

/**
 * Class: AdventourContract
 * Responsibility: Berisi constant nama tabel dan column dalam database Adventour
 */

public final class AdventourContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public AdventourContract() {}

    /* Inner class that defines the table contents */
    public static abstract class TravelJournal implements BaseColumns {
        public static final String TABLE_NAME = "traveljournal";
        public static final String COLUMN_NAME_ID_TRAVELJOURNAL = "id_traveljournal";
        public static final String COLUMN_NAME_USER = "user";
        public static final String COLUMN_NAME_ID_LAYOUT = "id_layout";
        public static final String COLUMN_NAME_ORIGN = "orign";
        public static final String COLUMN_NAME_DATE_ORIGN = "date_orign";
        public static final String COLUMN_NAME_DESTINATION = "destination";
        public static final String COLUMN_NAME_DATE_RETURN = "date_return";
        public static final String COLUMN_NAME_DATE_CREATED = "date_created";
        public static final String COLUMN_NAME_TOTAL_BUDGET = "budget";
        public static final String COLUMN_NAME_FIRST_PAGE = "first_page";
    }
}
