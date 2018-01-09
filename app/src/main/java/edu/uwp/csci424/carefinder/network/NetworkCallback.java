package edu.uwp.csci424.carefinder.network;

/**
 * NetworkCallback is an interface for returning network responses as results objects and
 * error messages to the repositories
 *
 * Created by Jeremiah Van Offeren on 11/27/17.
 */

public interface NetworkCallback<T> {

    /**
     * Callback for successful network call
     *
     * @param result Object contained in the response of the network call
     */
    void onSuccess(T result);

    /**
     * Callback for failed network call
     *
     * @param errorCode http staus code of error
     * @param errorMessage http status message of error
     */
    void onFailure(int errorCode, String errorMessage);
}
