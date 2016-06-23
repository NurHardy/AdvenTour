package id.ketampanan.adventour.data;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class: FetchTravelJournalTask
 * Responsibility: Mengunduh travel journal
 */
public class FetchTravelJournalsTask extends AsyncTask<String, Void, String[]> {

    String splitter = "---";
    private Context mContext;

    public FetchTravelJournalsTask(Context context) {
        this.mContext = context;
    }

    private final String LOG_TAG = FetchTravelJournalsTask.class.getSimpleName();

    /**
     * Method: getTravelJournalsDataFromJson
     * Fungsi: mengekstrak value dari Json yang diunduh dan
     *          memasukkannya ke dalam database lokal
     * @param travelJournalsJsonStr
     * @return resultStrs: String[]
     * @throws JSONException
     */
    private String[] getTravelJournalsDataFromJson(String travelJournalsJsonStr)
            throws JSONException {

        // These are the names of the JSON objects that need to be extracted.
        final String A_TJ_ID_TRAVELJOURNAL = "id_traveljournal";
        final String A_TJ_USER = "id_user";
        final String A_TJ_ID_LAYOUT = "id_layout";
        final String A_TJ_ORIGIN = "orign";
        final String A_TJ_DATE_ORIGN = "date_orign";
        final String A_TJ_DESTINATION = "destination";
        final String A_TJ_DATE_RETURN = "date_return";
        final String A_TJ_DATE_CREATED = "date_created";
        final String A_TJ_TOTAL_BUDGET = "total_budget";
        final String A_TJ_FIRST_PAGE = "link_gambar";

            /*JSONObject travelJournalsJson = new JSONObject(travelJournalsJsonStr);
            //JSONArray travelJournalsArray = travelJournalsJson.getJSONArray();
            JSONArray travelJournalsArray = new JSONArray();
            travelJournalsJson.toJSONArray(travelJournalsArray);*/

        JSONArray travelJournalsArray = new JSONArray(travelJournalsJsonStr);

        String[] resultStrs = new String[travelJournalsArray.length()];
        for(int i = 0; i < travelJournalsArray.length(); i++) {

            String id_traveljournal;
            String user;
            String id_layout;
            String orign;
            String date_orign;
            String destination;
            String date_return;
            String date_created;
            String total_budget;
            String first_page;

            // Get the JSON object representing the day
            JSONObject travelJournalsItem = travelJournalsArray.getJSONObject(i);

            id_traveljournal = travelJournalsItem.getString(A_TJ_ID_TRAVELJOURNAL);
            user = travelJournalsItem.getString(A_TJ_USER);
            id_layout = travelJournalsItem.getString(A_TJ_ID_LAYOUT);
            orign = travelJournalsItem.getString(A_TJ_ORIGIN);
            date_orign = travelJournalsItem.getString(A_TJ_DATE_ORIGN);
            destination = travelJournalsItem.getString(A_TJ_DESTINATION);
            date_return = travelJournalsItem.getString(A_TJ_DATE_RETURN);
            date_created = travelJournalsItem.getString(A_TJ_DATE_CREATED);
            total_budget = travelJournalsItem.getString(A_TJ_TOTAL_BUDGET);
            first_page = travelJournalsItem.getString(A_TJ_FIRST_PAGE);

            resultStrs[i] = id_traveljournal + splitter +
                    user + splitter +
                    id_layout + splitter +
                    orign + splitter +
                    date_orign + splitter +
                    destination + splitter +
                    date_return + splitter +
                    date_created + splitter +
                    total_budget + splitter +
                    first_page;

            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(AdventourContract.TravelJournal.COLUMN_NAME_ID_TRAVELJOURNAL, id_traveljournal);
            values.put(AdventourContract.TravelJournal.COLUMN_NAME_USER, user);
            values.put(AdventourContract.TravelJournal.COLUMN_NAME_ID_LAYOUT, id_layout);
            values.put(AdventourContract.TravelJournal.COLUMN_NAME_ORIGN, orign);
            values.put(AdventourContract.TravelJournal.COLUMN_NAME_DATE_ORIGN, date_orign);
            values.put(AdventourContract.TravelJournal.COLUMN_NAME_DESTINATION, destination);
            values.put(AdventourContract.TravelJournal.COLUMN_NAME_DATE_RETURN, date_return);
            values.put(AdventourContract.TravelJournal.COLUMN_NAME_DATE_CREATED, date_created);
            values.put(AdventourContract.TravelJournal.COLUMN_NAME_TOTAL_BUDGET, total_budget);
            values.put(AdventourContract.TravelJournal.COLUMN_NAME_FIRST_PAGE, first_page);

            AdventourDbHelper adventourDbHelper = new AdventourDbHelper(this.mContext);
            adventourDbHelper.insertTravelJournals(values);

        }

        for (String s : resultStrs) {
            Log.v(LOG_TAG, "TravelJournal entry: " + s);
        }
        return resultStrs;

    }

    /**
     * Method: doInBackground
     * Fungsi: Mengunduh Json dari REST,
     *      jika berhasil, mengembalikan nilai berupa String[]
     *      yang berisi value yang sudah diekstrak dari Json
     * @param params
     * @return
     */
    @Override
    protected String[] doInBackground(String... params) {

        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String travelJournalJsonStr = null;

        try {
            final String REST_URL =  "http://192.168.1.13/adventourrestserver/index.php/traveljournal_rest/traveljournals";

            Uri builtUri = Uri.parse(REST_URL).buildUpon().build();

            URL url = new URL(builtUri.toString());

            Log.v(LOG_TAG, "Built URI " + builtUri.toString());


                /*String formData = "username="+ BuildConfig.ADVENTOUR_API_USERNAME +
                        "&password=" + BuildConfig.ADVENTOUR_API_PASSWORD +
                        "&grant_type=password";
                String header = "Basic " + Base64.("<client_id>:<client_secret>");

                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setDoOutput(true);
                urlConnection.addRequestProperty("Authorization", header);
                urlConnection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("charset", "utf-8");
                urlConnection.setRequestProperty("Content-Length", Integer.toString(formData.length()));*/

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            travelJournalJsonStr = buffer.toString();

            Log.v(LOG_TAG, "TravelJournal string: " + travelJournalJsonStr);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attemping
            // to parse it.
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        try {
            return getTravelJournalsDataFromJson(travelJournalJsonStr);
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String[] result) {
        AdventourDbHelper adventourDbHelper = new AdventourDbHelper(this.mContext);
        adventourDbHelper.readTravelJournals();
    }
}