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

/**
 * Created by rohit on 5/25/15.
 */
public class RemainingAdapter extends ArrayAdapter <GS> {

    ArrayList<GS> workList;
    private DatabaseHandler db;
    private Context context;

    public RemainingAdapter(Context context, ArrayList<GS> list) {
        super(context, R.layout.remaining_list_item, list);
        db = new DatabaseHandler(context);
        this.workList = list;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if(row==null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.remaining_list_item, parent, false);
        }
        TextView tv_title = (TextView) row.findViewById(R.id.tv_title);
        TextView tv_detail = (TextView) row.findViewById(R.id.tv_detail);

        GS obj = workList.get(position);
        String title = obj.getTitle();
        String detail = obj.getDetail();

        tv_title.setText(title);
        tv_detail.setText(detail);

        return row;
    }
}
