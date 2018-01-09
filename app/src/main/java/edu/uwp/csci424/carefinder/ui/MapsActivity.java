package edu.uwp.csci424.carefinder.ui;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import edu.uwp.csci424.carefinder.R;
import edu.uwp.csci424.carefinder.data.local.HospitalRepository;
import edu.uwp.csci424.carefinder.data.local.HospitalUpdateCallback;
import edu.uwp.csci424.carefinder.data.model.Hospital;
import edu.uwp.csci424.carefinder.helper.CustomInfoWindowGoogleMap;

import static com.google.android.gms.maps.GoogleMap.*;
import static edu.uwp.csci424.carefinder.data.local.HospitalRepository.*;

/**
 * MapsActivity displays all hospitals on a Google Map and a HospitalsListFragment
 *
 * Created by Jeremiah Van Offeren on 11/27/17.
 */
public class MapsActivity extends AppCompatActivity implements
        OnMapReadyCallback, HospitalUpdateCallback, HospitalsListFragment.OnListFragmentInteractionListener,
        SearchView.OnQueryTextListener {

    /**
     * TAG for logging
     */
    private static final String TAG = "MapsActivity";

    /**
     * Google Map to display hospitals
     */
    private GoogleMap mMap;

    /**
     * Repository storing the hospitals
     */
    private HospitalRepository hospitalRepository;

    /**
     * List of hospitals to display
     */
    private List<Hospital> hospitals;

    /**
     * List of markers currently displayed
     */
    private List<Marker> markers;

    /**
     * Fragment for displaying the hospitals list
     */
    private HospitalsListFragment mHospitalsListFragment;

    /**
     * Menu Item holding the search view
     */
    private MenuItem searchMenuItem;

    /**
     * Search view for searching hospitals
     */
    private SearchView searchView;

    /**
     * Type of search to preform on hospitals
     */
    private SearchType searchType;

    /**
     * location of the center of the usa
     */
    private LatLng usa = new LatLng(37.0902, -95.7129);

    /**
     * Progress bar to notify a user a network call is loading
     */
    private ProgressBar progressBar;


    /**
     * Runs when a new activity is created. Creates hospital repository, map and hospitals list fragment
     *
     * @param savedInstanceState states to restore in the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        progressBar = findViewById(R.id.progressBar);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        hospitalRepository = getInstance();
        hospitalRepository.addUpdateCallback(this);
        hospitals = hospitalRepository.getHospitals();

        FragmentManager fragmentManager = getSupportFragmentManager();

        // get fragment from container
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        // if no fragment exists create one and add it
        if (fragment == null) {
            mHospitalsListFragment = HospitalsListFragment.newInstance();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, mHospitalsListFragment)
                    .commit();
        }
    }

    /**
     * Adds the filter and search icons to the options menu
     *
     * @param menu to add the items to
     * @return boolean all actions handled
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.map_menu, menu);

        SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);
        searchMenuItem = menu.findItem(R.id.search);
        searchMenuItem.setVisible(false);
        searchView = (SearchView) searchMenuItem.getActionView();
        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.
                    getSearchableInfo(getComponentName()));
        }
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    /**
     * Handles menu item click events. Filter Items clicks set search type and open search view if needed
     *
     * @param item clicked
     * @return all actions handled
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.name:
                Log.d(TAG, "name selected");
                searchMenuItem.setVisible(true);
                searchView.setQueryHint("Hospital Name");
                searchView.setQuery("", false);
                searchView.setIconified(false);
                searchType = SearchType.NAME;
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(usa, 0f));
                break;
            case R.id.city:
                Log.d(TAG, "city selected");
                searchMenuItem.setVisible(true);
                searchView.setQueryHint("Hospital City");
                searchView.setQuery("", false);
                searchView.setIconified(false);
                searchType = SearchType.CITY;
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(usa, 0f));
                break;
            case R.id.state:
                Log.d(TAG, "state selected");
                searchMenuItem.setVisible(true);
                searchView.setQueryHint("Hospital State");
                searchView.setQuery("", false);
                searchView.setIconified(false);
                searchType = SearchType.STATE;
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(usa, 0f));
                break;
            case R.id.county:
                Log.d(TAG, "county selected");
                searchMenuItem.setVisible(true);
                searchView.setQueryHint("Hospital County");
                searchView.setQuery("", false);
                searchView.setIconified(false);
                searchType = SearchType.COUNTY;
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(usa, 0f));
                break;
            case R.id.none:
                Log.d(TAG, "none selected");
                searchMenuItem.setVisible(false);
                searchView.setQuery("", false);
                searchView.setIconified(true);
                searchView.clearFocus();
                searchType = SearchType.NONE;
                hospitalRepository.searchHospitals(searchType, getString(R.string.none));
                progressBar.setVisibility(View.VISIBLE);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(usa, 0f));
                break;
            default:
                super.onOptionsItemSelected(item);
        }
        return false;
    }

    /**
     * Map is ready add hospital list items and add onclick listeners
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setTiltGesturesEnabled(true);
        markers = new ArrayList<>();
        CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(this);
        mMap.setInfoWindowAdapter(customInfoWindow);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(usa));
        for (Hospital hospital: hospitals) {
            LatLng latLng = new LatLng(hospital.getLocation().getLatitude(), hospital.getLocation().getLongitude());
            MarkerOptions marker = new MarkerOptions()
                    .position(latLng)
                    .title(hospital.getHospitalName())
                    .snippet(hospital.getAddress() + "\n" + hospital.getCity() + ", " + hospital.getState() + " " + hospital.getZipCode());
            Marker mapMarker = mMap.addMarker(marker);
            markers.add(mapMarker);
        }
        mMap.setOnMarkerClickListener(new OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                updateLocation(marker);
                return true;
            }
        });
        mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("geo:" + marker.getPosition().latitude + "," + marker.getPosition().longitude +
                                        "?q=" + marker.getTitle()));
                startActivity(intent);
            }
        });
    }

    /**
     * hospital update completed succesfully update the ui
     */
    @Override
    public void onHospitalUpdateComplete() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                if (mMap != null) {
                    try {
                        if (mHospitalsListFragment != null) {
                            mHospitalsListFragment.onHospitalUpdateComplete();
                        }
                        markers.clear();
                        mMap.clear();
                        for (Hospital hospital : hospitals) {
                            LatLng latLng = new LatLng(hospital.getLocation().getLatitude(), hospital.getLocation().getLongitude());
                            MarkerOptions marker = new MarkerOptions()
                                    .position(latLng)
                                    .title(hospital.getHospitalName())
                                    .snippet(hospital.getAddress() + "\n" + hospital.getCity() + ", " + hospital.getState() + " " + hospital.getZipCode());
                            Marker mapMarker = mMap.addMarker(marker);
                            markers.add(mapMarker);
                        }
                    } catch (ConcurrentModificationException e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
            }
        });
    }

    /**
     * Hospital update wasnt successful update UI and notify user of the error
     *
     * @param errorCode of error
     * @param errorMessage of error
     */
    @Override
    public void onHospitalUpdateError(final int errorCode, final String errorMessage) {
        final Context context = this;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                if (mMap != null) {
                    try {
                        if (mHospitalsListFragment != null) {
                            mHospitalsListFragment.onHospitalUpdateComplete();
                        }
                        markers.clear();
                        mMap.clear();

                        if (errorCode == -1) {
                            Toast.makeText(context, "Please Connect to the Internet", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();
                        }
                    } catch (ConcurrentModificationException e) {
                        Log.e(TAG, e.getMessage());
                    }
                }

            }
        });
    }

    /**
     * Hospitalfragment item was selected
     *
     * @param position of selected hospital
     */
    @Override
    public void onListFragmentInteraction(int position) {
        updateLocation(markers.get(position));
    }

    /**
     * Searchview query submitted search repository for hospitals
     *
     * @param query query string for search
     *
     * @return boolean all actions handled
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.d(TAG, "Search: " + query);
        progressBar.setVisibility(View.VISIBLE);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(usa, 0f));
        hospitalRepository.searchHospitals(searchType, query);
        searchView.clearFocus();
        return true;
    }

    /**
     * Searchview query string changed
     *
     * @param newText new query
     * @return all actions handled
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    /**
     * Update the center and zoom level of map for the selected location
     *
     * @param marker that was selected
     */
    private void updateLocation(Marker marker) {
        marker.showInfoWindow();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 15f));
    }

    /**
     * Activity has been destroyed close the repository
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        hospitalRepository.close();
        hospitalRepository = null;
    }
}
