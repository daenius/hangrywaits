package com.boolong.hangrywaits;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.boolong.hangrywaits.dataprovider.DataProvider;

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

        ListView homeListView = (ListView) rootView.findViewById(R.id.home_list_view);
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
                    animateX.setDuration(350);
                    animateY.setDuration(350);
                    searchPanel.setVisibility(View.VISIBLE);
                } else {
                    animateX = ObjectAnimator.ofFloat(searchPanel, "translationX", v.getRootView().getLeft(), v.getRootView().getRight() - v.getWidth()/2);
                    animateY = ObjectAnimator.ofFloat(searchPanel, "translationY", v.getRootView().getTop(), v.getRootView().getBottom() - v.getHeight()/2);
                    animateX.setDuration(200);
                    animateY.setDuration(200);
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
}
