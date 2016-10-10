

package com.example.android.Qualcomm_Temp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.Random;

public class UIBuild extends Fragment {

    private static final String LOG_TAG = UIBuild.class.getSimpleName();

    private ListView mListView;
    private TextView mTextView,mTextView1,mTextView2,mTextView3,mTextView4;
    private ArrayAdapter<String> mListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Notify the system to allow an options menu for this fragment.
        setHasOptionsMenu(false);
    }

    // BEGIN_INCLUDE (inflate_view)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sample, container, false);
        Random r = new Random();
        double randomValue = 10 + (35 - 10) * r.nextDouble();
        mTextView = (TextView) view.findViewById(R.id.temp);
        mTextView.setText(Double.toString(randomValue).substring(0,4)+" °C");
        mTextView1 = (TextView) view.findViewById(R.id.temp2);
        randomValue = 10 + (35 - 10) * r.nextDouble();
        mTextView1.setText(Double.toString(randomValue).substring(0,4)+" °C");
        mTextView2 = (TextView) view.findViewById(R.id.temp3);
        randomValue = 10 + (35 - 10) * r.nextDouble();
        mTextView2.setText(Double.toString(randomValue).substring(0,4)+" °C");
        mTextView3 = (TextView) view.findViewById(R.id.temp4);
        randomValue = 10 + (35 - 10) * r.nextDouble();
        mTextView3.setText(Double.toString(randomValue).substring(0,4)+" °C");
        mTextView4 = (TextView) view.findViewById(R.id.temp5);
        randomValue = 10 + (35 - 10) * r.nextDouble();
        mTextView4.setText(Double.toString(randomValue).substring(0,4)+" °C");
        // Retrieve the ListView
        mListView = (ListView) view.findViewById(android.R.id.list);
        mListView.setEnabled(false);


        return view;
    }
    // END_INCLUDE (inflate_view)

    // BEGIN_INCLUDE (setup_views)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /**
         * Create an ArrayAdapter to contain the data for the ListView. Each item in the ListView
         * uses the system-defined simple_list_item_1 layout that contains one TextView.
         */
        mListAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                Days.asList());


        // Set the adapter between the ListView and its backing data.
        mListView.setAdapter(mListAdapter);


    }
    // END_INCLUDE (setup_views)

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }

}
