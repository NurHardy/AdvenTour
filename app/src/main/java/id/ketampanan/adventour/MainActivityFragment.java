package id.ketampanan.adventour;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import id.ketampanan.adventour.data.AdventourContract;
import id.ketampanan.adventour.data.AdventourDbHelper;
import id.ketampanan.adventour.data.FetchTravelJournalsTask;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    public void updateTravelJournal() {
        FetchTravelJournalsTask fetchTravelJournalsTask = new FetchTravelJournalsTask(this.getContext());

        fetchTravelJournalsTask.execute();
    }

    @Override
    public void onStart() {
        super.onStart();

        updateTravelJournal();
    }
}
