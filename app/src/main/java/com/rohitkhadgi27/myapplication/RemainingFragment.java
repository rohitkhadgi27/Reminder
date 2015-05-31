package com.rohitkhadgi27.myapplication;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.rohitkhadgi27.myapplication.adapters.RemainingAdapter;
import com.rohitkhadgi27.myapplication.tabs.SlidingTabLayout;

import java.util.ArrayList;

public class RemainingFragment extends ListFragment implements SlidingTabLayout.FragmentRefreshListener {

    DatabaseHandler db;
    ArrayList<GS> workList;
    private ListView lv;
    RemainingAdapter adapter;

    public RemainingFragment() {
        //Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_remaining, container, false);
        //setHasOptionsMenu(true);

        db = new DatabaseHandler(getActivity());
        workList = new ArrayList<>();

        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        displayList();
        lv = getListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(), "clicked", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getActivity(), RemainingDetailActivity.class);
                GS obj = workList.get(position);
                i.putExtra("id", obj.getId());
                i.putExtra("title", obj.getTitle());
                i.putExtra("detail", obj.getDetail());
                startActivity(i);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                deleteDialog(position);
              return true;
            }
        });
    }

    private void deleteDialog(final int position) {
        AlertDialog.Builder alert = new AlertDialog.Builder(
                getActivity());
        alert.setTitle("Delete");
        alert.setMessage("Do you want to delete?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GS obj = workList.get(position);
                db.removeItem(obj.getId());
                workList.remove(position);
                adapter.notifyDataSetChanged();

            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }



    private void displayList() {
        workList = db.getGS();
        adapter = new RemainingAdapter(getActivity(), workList);
        setListAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        workList.clear();
        displayList();
    }

    @Override
    public void refreshFragment() {
        workList.clear();
        displayList();
    }
}
