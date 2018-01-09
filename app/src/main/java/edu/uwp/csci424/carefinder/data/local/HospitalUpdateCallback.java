package edu.uwp.csci424.carefinder.data.local;


/**
 * Hospital Repository Update Callback method to the User Interface
 *
 * Created by Jeremiah Van Offeren on 11/27/17.
 */

public interface HospitalUpdateCallback {
    /**
     * user interface callback called when repository completes an update successfully
     */
    void onHospitalUpdateComplete();

    /**
     * user interface callback called when repository completes update with error
     * @param errorCode of error
     * @param errorMessage of error
     */
    void onHospitalUpdateError(int errorCode, String errorMessage);
}
