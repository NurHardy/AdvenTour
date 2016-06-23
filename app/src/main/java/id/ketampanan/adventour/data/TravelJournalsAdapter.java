package id.ketampanan.adventour.data;

/**
 * Created by aadddrr on 17/06/2016 for AdvenTour.
 */

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import id.ketampanan.adventour.data.AdventourContract.TravelJournal;

import id.ketampanan.adventour.R;

/**
 * Class: TravelJournalsAdapter
 * Responsibility: Meletakkan tiap travel journal ke view-nya.
 */

public class TravelJournalsAdapter extends CursorRecyclerViewAdapter<TravelJournalsAdapter.ViewHolder> {

    private Context mContext;

    public TravelJournalsAdapter(Context context, Cursor cursor) {
        super(context, cursor);

        this.mContext = context;
    }

    /**
     * Class: ViewHolder
     * Responsibility: Memudahkan akses ke tiap elemen view
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageFirstPage;
        public TextView textTotalBudget;
        public TextView textDateOrign;
        public TextView textUser;
        public TextView textDateCreated;

        public ViewHolder(View view) {
            super(view);
            imageFirstPage = (ImageView) itemView.findViewById(R.id.travel_journals_first_page);
            textTotalBudget = (TextView) itemView.findViewById(R.id.travel_journals_total_budget);
            textDateOrign = (TextView) itemView.findViewById(R.id.travel_journals_date_orign);
            textUser = (TextView) itemView.findViewById(R.id.travel_journals_user);
            textDateCreated = (TextView) itemView.findViewById(R.id.travel_journals_date_created);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.travel_journals_grid_item, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    /**
     * Method: onBindViewHolder
     * Fungsi: meletakkan data dari cursor ke view yang terkait
     *          Image di-load menggunakan Glide
     * @param viewHolder
     * @param cursor
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {

        //String url = myUrls.get(position);

        Glide
                .with(this.mContext)
                .load(R.drawable.placeholder)
                .fitCenter()
                //.placeholder(R.drawable.loading_spinner)
                .crossFade()
                .into(viewHolder.imageFirstPage);

        viewHolder.textTotalBudget.setText(cursor.getString(cursor.getColumnIndexOrThrow(TravelJournal.COLUMN_NAME_TOTAL_BUDGET)));
        viewHolder.textDateOrign.setText(cursor.getString(cursor.getColumnIndexOrThrow(TravelJournal.COLUMN_NAME_DATE_ORIGN)));
        viewHolder.textUser.setText(cursor.getString(cursor.getColumnIndexOrThrow(TravelJournal.COLUMN_NAME_USER)));
        viewHolder.textDateCreated.setText(cursor.getString(cursor.getColumnIndexOrThrow(TravelJournal.COLUMN_NAME_DATE_CREATED)));
    }
}