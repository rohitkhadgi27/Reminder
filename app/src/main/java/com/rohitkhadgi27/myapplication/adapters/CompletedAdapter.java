package com.rohitkhadgi27.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rohitkhadgi27.myapplication.DatabaseHandler;
import com.rohitkhadgi27.myapplication.GS;
import com.rohitkhadgi27.myapplication.R;

import java.util.ArrayList;



public class CompletedAdapter extends ArrayAdapter<GS> {

    ArrayList<GS> completedList;
    private DatabaseHandler db;
    private Context context;

    public CompletedAdapter(Context context, ArrayList<GS> list) {
        super(context, R.layout.remaining_list_item, list);

        db = new DatabaseHandler(context);
        this.completedList = list;
        this.context = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if(row==null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.completed_list_item, parent, false);

            TextView tv_title = (TextView) row.findViewById(R.id.tv_title);
            TextView tv_detail = (TextView) row.findViewById(R.id.tv_detail);

            GS obj = completedList.get(position);
            String title = obj.getTitle();
            String detail = obj.getDetail();

            tv_title.setText(title);
            tv_detail.setText(detail);

        }
        return row;
    }
}
