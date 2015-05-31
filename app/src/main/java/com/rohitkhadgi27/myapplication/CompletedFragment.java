package com.rohitkhadgi27.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.rohitkhadgi27.myapplication.adapters.CompletedAdapter;
import com.rohitkhadgi27.myapplication.tabs.SlidingTabLayout;

import java.util.ArrayList;

public class CompletedFragment extends ListFragment implements SlidingTabLayout.FragmentRefreshListener {

    DatabaseHandler db;
    ArrayList<GS> completedList;
    private ListView lv;
    private CompletedAdapter adapter;

    public CompletedFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_completed, container, false);

        db = new DatabaseHandler(getActivity());
        completedList = new ArrayList<>();
        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        displayList();
        lv = getListView();
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                deleteDialog(position);
                return false;
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
                GS obj = completedList.get(position);
                db.removeItem(obj.getId());
                completedList.remove(position);
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


    public void displayList() {
        completedList = db.completedGS();
        adapter = new CompletedAdapter(getActivity(), completedList);
        setListAdapter(adapter);

    }

    public void refreshFragment() {
        completedList.clear();
        displayList();
    }

    @Override
    public void onResume() {
        super.onResume();
        completedList.clear();
        displayList();
    }
}
