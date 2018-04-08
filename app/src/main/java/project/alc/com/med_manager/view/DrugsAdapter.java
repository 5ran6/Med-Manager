package project.alc.com.med_manager.view;

/**
 * Created by ravi on 20/02/18.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import project.alc.com.med_manager.R;
import project.alc.com.med_manager.database.model.Note;

public class DrugsAdapter extends RecyclerView.Adapter<DrugsAdapter.MyViewHolder> {
//    ArrayList<Note> arrayList = new ArrayList<>();


    private Context context;
    private List<Note> notesList;

    DrugsAdapter(List<Note> notesList) {
        this.notesList = notesList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView note;
        public TextView dose;
        public TextView dot;
        public TextView timestamp;

        public MyViewHolder(View view) {
            super(view);
            note = (TextView) view.findViewById(R.id.note);
            dot = (TextView) view.findViewById(R.id.dot);
            timestamp = (TextView) view.findViewById(R.id.timestamp);
        }
    }


    public DrugsAdapter(Context context, List<Note> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Note note = notesList.get(position);

        holder.note.setText(note.getNote());
//        holder.dose.setText(note.getNote());

        // Displaying dot from HTML character code
        holder.dot.setText(Html.fromHtml("&#8226;"));


//for
//    +
//     holder.dot.setText(Html.fromHtml("&#8224;"));
//
        // Formatting and displaying timestamp
        holder.timestamp.setText("Added on " + formatDate(note.getTimestamp()) + " (" + note.getFrequency() + " time(s) a day)");
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    /**
     * Formatting timestamp to `MMM d` format
     * Input: 2018-02-21 00:15:42
     * Output: Feb 21
     */
    private String formatDate(String dateStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = fmt.parse(dateStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("MMM d, HH:mm");
            return fmtOut.format(date);
        } catch (ParseException e) {

        }

        return "";
    }

    public void setFilter(List<Note> newList) {
        notesList = new ArrayList<>();
        notesList.addAll(newList);
        notifyDataSetChanged();
    }
}
