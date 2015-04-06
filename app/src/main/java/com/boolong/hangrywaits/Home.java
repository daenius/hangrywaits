package com.boolong.hangrywaits;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.boolong.hangrywaits.dataprovider.DataProvider;

import java.util.List;


public class Home extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.home, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class HomeFragment extends Fragment {
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
            final ImageButton searchButton = (ImageButton) rootView.findViewById(R.id.search_button);

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
                    ObjectAnimator animateX = ObjectAnimator.ofFloat(searchPanel, "translationX", v.getRootView().getRight(), v.getRootView().getLeft());
                    ObjectAnimator animateY = ObjectAnimator.ofFloat(searchPanel, "translationY", v.getRootView().getBottom(), v.getRootView().getTop());
                    animateX.setDuration(350);
                    animateY.setDuration(350);
                    if (searchPanel.getVisibility() == View.GONE) {
                        searchPanel.setVisibility(View.VISIBLE);
                    } else {
                        animateX = ObjectAnimator.ofFloat(searchPanel, "translationX", v.getRootView().getLeft(), v.getRootView().getRight());
                        animateY = ObjectAnimator.ofFloat(searchPanel, "translationY", v.getRootView().getTop(), v.getRootView().getBottom());
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
}
