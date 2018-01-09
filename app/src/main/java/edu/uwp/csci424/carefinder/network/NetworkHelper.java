package edu.uwp.csci424.carefinder.network;


import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.uwp.csci424.carefinder.BuildConfig;
import edu.uwp.csci424.carefinder.data.model.Hospital;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Network Helper is a Singleton class that makes network calls
 * and returns java objects to repositories
 *
 * Created by Jeremiah Van Offeren on 11/27/17.
 */

public class NetworkHelper {

    /**
     * Base URL of API
     */
    // emulator base url
//    private static final String BASE_URL = "http://10.0.2.2:3000";
    // whereAmI base url
    private static final String BASE_URL = "http://131.210.23.140:3000";
    // home base url
//    private static final String BASE_URL = "http://192.168.0.3:3000";

    /**
     * Hospitals Endpoint of API
     */
    private static final String HOSPITALS_ENDPOINT = "/hospitals";

    /**
     * Name Endpoint of API
     */
    private static final String NAME_ENDPOINT = HOSPITALS_ENDPOINT + "/name/";

    /**
     * City Endpoint of API
     */
    private static final String CITY_ENDPOINT = HOSPITALS_ENDPOINT + "/city/";

    /**
     * State Query Modifier
     */
    private static final String STATE_QUERY = "/state/";

    /**
     * State Endpoint of API
     */
    private static final String STATE_ENDPOINT = HOSPITALS_ENDPOINT + STATE_QUERY;

    /**
     * County Endpoint of API
     */
    private static final String COUNTY_ENDPOINT = HOSPITALS_ENDPOINT + "/county/";

    /**
     * APIKEY
     */
    private static final String APIKEY = "q5DaqfLzraH5T7d4sitldQ4FiYDOgOAEVXfEo4wj";

    /**
     * Singleton Instance of NetworkHelper
     */
    private static NetworkHelper myInstance;

    /**
     * Http client to make network calls with
     */
    private OkHttpClient client;

    /**
     * Getter method to get the Singleton Instance
     *
     * @return Singleton Instance of NetworkHelper
     */
    public static NetworkHelper getInstance() {
        if (myInstance == null) {
            myInstance = new NetworkHelper();
        }
        return myInstance;
    }

    /**
     * private constructor for creating Singleton Instance of NetworkHelper
     */
    private NetworkHelper(){
        if (BuildConfig.DEBUG) {
            client = new OkHttpClient.Builder()
                    .addInterceptor(new LoggingInterceptor())
                    .build();
        } else {
            client = new OkHttpClient.Builder()
                    .build();
        }
    }

    /**
     * helper method to create a request builder with base headers attached
     *
     * @param endpoint endpoint for request
     *
     * @return Request Builder object with base headers attached
     */
    private Request.Builder getBaseRequestBuilder(String endpoint) {
        return new Request.Builder()
                .url(BASE_URL + endpoint)
                .header("User-Agent", "carefinder-client")
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("Authorization", "ApiKey " + APIKEY);
    }

    /**
     * Getter method for getting all hospitals list from the API
     *
     * @param callback NetworkCallback that returns a list of all hospitals on success or error on failure
     */
    public void getAllHospitals(final NetworkCallback<List<Hospital>> callback) {

        Request request = getBaseRequestBuilder(HOSPITALS_ENDPOINT).build();

        client.newCall(request).enqueue(new HospitalCallback(callback));

    }

    /**
     * Getter method for getting all hospitals by name from API
     *
     * @param callback NetworkCallback that returns list of all hospitials containing the query string in the name field
     * @param query string to query the name field by
     */
    public void getHospitalsByName(final NetworkCallback<List<Hospital>> callback, String query) {

        Request request = getBaseRequestBuilder(NAME_ENDPOINT + query.trim()).build();

        client.newCall(request).enqueue(new HospitalCallback(callback));

    }

    /**
     * Getter method for getting all hospitals that have cities that match the query string
     *
     * @param callback NetworkCallback that returns the list of all hospitals with a matching name field
     * @param query string to match to hospitals city and optional ,state
     */
    public void getHospitalsByCity(final NetworkCallback<List<Hospital>> callback, String query) {

        String[] queryArray = query.split(",");
        Log.d("NetworkHelper", Arrays.toString(queryArray));

        Request request;

        if (queryArray.length > 1) {
            request = getBaseRequestBuilder(CITY_ENDPOINT + queryArray[0].trim() + STATE_QUERY + queryArray[1].trim()).build();
        } else {
            request = getBaseRequestBuilder(CITY_ENDPOINT + queryArray[0].trim()).build();
        }

        client.newCall(request).enqueue(new HospitalCallback(callback));
    }

    /**
     * Getter method for getting all the hospitals in a given state
     *
     * @param callback NetworkCallback that returns the list of all hospitals with a matching state
     * @param query string to match to hospitals state field
     */
    public void getHospitalsByState (final NetworkCallback<List<Hospital>> callback, String query) {

        Request request = getBaseRequestBuilder(STATE_ENDPOINT + query.trim()).build();

        client.newCall(request).enqueue(new HospitalCallback(callback));

    }

    /**
     * Getter method for getting all the hospitals in a given county
     *
     * @param callback NetworkCallback that returns the list of all hospitals with a matching county
     * @param query string to match to hospitals county field
     */
    public void getHospitalsByCounty (final NetworkCallback<List<Hospital>> callback, String query) {

        Request request = getBaseRequestBuilder(COUNTY_ENDPOINT + query.trim()).build();

        client.newCall(request).enqueue(new HospitalCallback(callback));

    }

    /**
     * Helper class that parses a json response and returns a list of hospitals
     */
    private class HospitalCallback implements Callback {

        private NetworkCallback<List<Hospital>> callback;

        HospitalCallback(NetworkCallback<List<Hospital>> callback) {
            this.callback = callback;
        }

        @Override
        public void onFailure(@NonNull Call call, @NonNull IOException e) {
            callback.onFailure(-1, e.getMessage());
        }

        @SuppressWarnings("ConstantConditions")
        @Override
        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

            //check for 200 level response code
            if (response.code() / 100 == 2) {
                //check for null body
                if (response.body() != null) {
                    try {
                        String responseString = response.body().string();
                        JSONObject responseJson = new JSONObject(responseString);
                        Log.d("Response", responseString);
                        Type collectionType = new TypeToken<List<Hospital>>() {
                        }.getType();
                        List<Hospital> hospitals = new Gson().fromJson(responseJson.get("data").toString(), collectionType);
                        callback.onSuccess(hospitals);
                    } catch (JSONException e) {
                        Log.e ("Response", e.getMessage());
                        callback.onFailure(-1, e.getMessage());
                    }
                } else {
                    callback.onSuccess(new ArrayList<Hospital>());
                }
            } else {
                callback.onFailure(response.code(), response.message());
            }
            response.close();
        }
    }
}
