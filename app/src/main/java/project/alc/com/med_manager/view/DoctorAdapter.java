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
import project.alc.com.med_manager.database.model.Doctor;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.MyViewHolder> {
//    ArrayList<DoctorInfo> arrayList = new ArrayList<>();


    private Context context;
    private List<Doctor> doctorInfo;

    DoctorAdapter(List<Doctor> doctorInfo) {
        this.doctorInfo = doctorInfo;
    }

    public DoctorAdapter(Context context, List<Doctor> doctorInfo) {
        this.context = context;
        this.doctorInfo = doctorInfo;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Doctor doc = doctorInfo.get(position);

        holder.info.setText(doc.getName());


        // Displaying dot from HTML character code
        holder.dot.setText(Html.fromHtml("&#8226;"));


//for
//    +
//     holder.dot.setText(Html.fromHtml("&#8224;"));
//
        // Formatting and displaying timestamp
        holder.timestamp.setText("Phone Number: " + doc.getPhone() + " as at " + formatDate(doc.getTimestamp()));
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

    @Override
    public int getItemCount() {
        return doctorInfo.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView info;
        public TextView dot;
        public TextView timestamp;

        public MyViewHolder(View view) {
            super(view);
            info = (TextView) view.findViewById(R.id.note);
            dot = (TextView) view.findViewById(R.id.dot);
            timestamp = (TextView) view.findViewById(R.id.timestamp);
        }
    }

}
