package edu.uwp.csci424.carefinder.data.local;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.uwp.csci424.carefinder.data.model.Hospital;
import edu.uwp.csci424.carefinder.network.NetworkCallback;
import edu.uwp.csci424.carefinder.network.NetworkHelper;

/**
 * Singleton Class for storing and updating hospital information in the apps local memory.
 *
 * Created by Jeremiah Van Offeren on 11/27/17.
 */

public class HospitalRepository implements NetworkCallback<List<Hospital>> {
    /**
     * Singleton Instance of the repository
     */
    private static HospitalRepository mInstance;

    /**
     * TAG for logging data
     */
    private static String TAG = "HospitalRepository";

    /**
     * Enum for type of search of hospitals by the user
     */
    public enum SearchType {
        NAME,
        CITY,
        STATE,
        COUNTY,
        NONE
    }

    /**
     * Singleton getInstance method
     * @return Singleton Instance of HospitalRepository
     */
    public static HospitalRepository getInstance() {
        if (mInstance == null) {
            mInstance = new HospitalRepository();
        }
        return mInstance;
    }

    /**
     * List of hospitals
     */
    private List<Hospital> hospitals = new ArrayList<>();

    /**
     * Helper instance for network calls
     */
    private NetworkHelper networkHelper = NetworkHelper.getInstance();

    /**
     * user interface callbacks
     */
    private HospitalUpdateCallback updateCallback;

    /**
     * private constructor for the singleton instance
     */
    private HospitalRepository() {
        initHospitals();
    }

    /**
     * helper method for initializing the hospitals list
     */
    private void initHospitals() {
        networkHelper.getAllHospitals(this);
    }

    /**
     * setter method for user interface callbacks
     *
     * @param updateCallback called when the repository finished an update
     */
    public void addUpdateCallback(HospitalUpdateCallback updateCallback) {
        this.updateCallback = updateCallback;
    }

    /**
     * getter method for hospitals list
     *
     * @return hospitals stored in the repository
     */
    public List<Hospital> getHospitals() {
        return hospitals;
    }

    /**
     * search method for searching hospitals
     *
     * @param searchType type of search to preform
     * @param query string to query by
     */
    public void searchHospitals(SearchType searchType, String query) {

        switch (searchType) {

            case NAME:
                networkHelper.getHospitalsByName(this, query);
                break;
            case CITY:
                networkHelper.getHospitalsByCity(this, query);
                break;
            case STATE:
                networkHelper.getHospitalsByState(this, query);
                break;
            case COUNTY:
                networkHelper.getHospitalsByCounty(this, query);
                break;
            default:
                networkHelper.getAllHospitals(this);
        }
    }

    /**
     * network helper callback after network call finishes successfully
     *
     * @param result Object contained in the response of the network call
     */
    @Override
    public void onSuccess(final List<Hospital> result) {
        hospitals.clear();
        hospitals.addAll(result);
        if (updateCallback != null) {
            updateCallback.onHospitalUpdateComplete();
        }
    }

    /**
     * network callback after network call finishes with error
     *
     * @param errorCode http status code of error
     * @param errorMessage http status message of error
     */
    @Override
    public void onFailure(int errorCode, String errorMessage) {
        hospitals.clear();
        if (updateCallback != null) {
            updateCallback.onHospitalUpdateError(errorCode, errorMessage);
        }
        Log.e(TAG, errorMessage);
    }

    /**
     * method to close the repository
     */
    public void close() {
        mInstance = null;
    }

}
