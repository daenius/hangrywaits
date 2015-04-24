package com.boolong.hangrywaits;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.boolong.hangrywaits.dataprovider.DataProvider;
import com.boolong.hangrywaits.googleplaces.GooglePlaces;
import com.boolong.hangrywaits.googleplaces.Place;
import com.boolong.hangrywaits.googleplaces.PlacesList;

import java.util.ArrayList;
import java.util.List;

import at.markushi.ui.CircleButton;

/**
 * Created by daenius on 4/23/15.
 */
public class HomeFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    // Google Places
    GooglePlaces googlePlaces;

    // Places List
    PlacesList nearPlaces;

    //Home List View
    ListView homeListView;

    // KEY Strings
    public static String KEY_REFERENCE = "reference"; // id of the place
    public static String KEY_NAME = "name"; // name of the place
    public static String KEY_VICINITY = "vicinity"; // Place area name

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static HomeFragment newInstance(int sectionNumber) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        List<Business> stringsToPopulate = DataProvider.getProvider(getActivity())
                .getFavorites();

        homeListView = (ListView) rootView.findViewById(R.id.home_list_view);
        final CircleButton searchButton = (CircleButton) rootView.findViewById(R.id.search_button);

        HomeListAdapter arrayAdapter =
                new HomeListAdapter(this.getActivity(), R.layout.home_list_item, stringsToPopulate);
        // Set The Adapter
        homeListView.setAdapter(arrayAdapter);

        homeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view.findViewById(R.id.details).getVisibility() == View.VISIBLE) {
                    view.findViewById(R.id.details).setVisibility(View.GONE);
                } else {
                    view.findViewById(R.id.details).setVisibility(View.VISIBLE);
                }

            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View searchPanel = v.getRootView().findViewById(R.id.search_view);
                ObjectAnimator animateX;
                ObjectAnimator animateY;
                if (searchPanel.getVisibility() == View.GONE) {
                    animateX = ObjectAnimator.ofFloat(searchPanel, "translationX", v.getRootView().getRight() - v.getWidth()/2, v.getRootView().getLeft());
                    animateY = ObjectAnimator.ofFloat(searchPanel, "translationY", v.getRootView().getBottom() - v.getHeight()/2, v.getRootView().getTop());
                    animateX.setDuration(200);
                    animateY.setDuration(200);
                    searchPanel.setVisibility(View.VISIBLE);
                } else {
                    animateX = ObjectAnimator.ofFloat(searchPanel, "translationX", v.getRootView().getLeft(), v.getRootView().getRight() - v.getWidth() / 2);
                    animateY = ObjectAnimator.ofFloat(searchPanel, "translationY", v.getRootView().getTop(), v.getRootView().getBottom() - v.getHeight() / 2);
                    animateX.setDuration(200);
                    animateY.setDuration(200);
                    LoadPlaces loadPlaces = new LoadPlaces(getActivity());
                    loadPlaces.execute();
                    animateX.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            searchPanel.setVisibility(View.GONE);


                        }
                    });
                }
                animateX.start();
                animateY.start();
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((Home) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    class LoadPlaces extends AsyncTask<String, String, String> {

        private Context context;

        public LoadPlaces(Context context) {
            this.context = context;
        }


        /**
         * getting Places JSON
         */
        protected String doInBackground(String... args) {
            // creating Places class object
            googlePlaces = new GooglePlaces();

            try {
                // Separeate your place types by PIPE symbol "|"
                // If you want all types places make it as null
                // Check list of types supported by google
                //
                String types = "cafe|restaurant"; // Listing places only cafes, restaurants

                // Radius in meters - increase this value if you don't find any places
                double radius = 1000; // 1000 meters

                // get nearest places
                nearPlaces = googlePlaces.search(37.377, -122.031, radius, types);


            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * and show the data in UI
         * Always use runOnUiThread(new Runnable()) to update UI from background
         * thread, otherwise you will get error
         * *
         */
        protected void onPostExecute(String file_url) {
            // updating UI from Background Thread

            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                public void run() {
                    // UI code goes here
                    /**
                     * Updating parsed Places into LISTVIEW
                     * */
                    // Get json response status
                    String status = nearPlaces.status;

                    // Check for all possible status
                    if (status.equals("OK")) {
                        // Successfully got places details
                        if (nearPlaces.results != null) {
                            // loop through each place
                            List<Business> businesses = businessBuilder(nearPlaces);
                            // list adapter
                            HomeListAdapter arrayAdapter =
                                    new HomeListAdapter(context, R.layout.home_list_item, businesses);
                            // Adding data into listview
                            homeListView.setAdapter(arrayAdapter);
                        }
                    }
                }
            });


        }
    }

    public List<Business> businessBuilder(PlacesList placeList){
        List<Business> businesses = new ArrayList<Business>();
        for (int i = 0; i < placeList.results.size(); i++){
            Place place = placeList.results.get(i);
            Business bus = new Business(String.valueOf(i), place.id, place.name, 30, false, "1234567", place.vicinity);
            businesses.add(bus);
        }
        return businesses;
    }
}
