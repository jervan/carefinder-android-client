package edu.uwp.csci424.carefinder.helper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import edu.uwp.csci424.carefinder.R;

/**
 * Helper Class for creating a custom information window on google maps
 *
 * Created by Jeremiah Van Offeren on 12/7/17.
 */

public class CustomInfoWindowGoogleMap implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public CustomInfoWindowGoogleMap(Context context) {
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        @SuppressLint("InflateParams") View view = ((Activity)context).getLayoutInflater()
                .inflate(R.layout.custom_info_window, null);

        TextView name = view.findViewById(R.id.info_name);
        TextView address = view.findViewById(R.id.info_address);

        name.setText(marker.getTitle());
        address.setText(marker.getSnippet());

        return view;
    }
}
