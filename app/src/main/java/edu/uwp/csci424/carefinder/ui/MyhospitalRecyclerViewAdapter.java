package edu.uwp.csci424.carefinder.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import edu.uwp.csci424.carefinder.R;
import edu.uwp.csci424.carefinder.data.model.Hospital;
import edu.uwp.csci424.carefinder.ui.HospitalsListFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 *
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyhospitalRecyclerViewAdapter extends RecyclerView.Adapter<MyhospitalRecyclerViewAdapter.ViewHolder> {

    private final List<Hospital> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyhospitalRecyclerViewAdapter(List<Hospital> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hospital, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        String positionString = "" + mValues.get(position).getHospitalType();
        holder.mTypeView.setText(positionString);
        holder.mNameView.setText(mValues.get(position).getHospitalName());

        holder.mCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + mValues.get(holder.getAdapterPosition()).getPhoneNumber().getPhoneNumber()));
                view.getContext().startActivity(intent);
            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTypeView;
        public final TextView mNameView;
        public final ImageButton mCallButton;
        public Hospital mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTypeView = (TextView) view.findViewById(R.id.type);
            mNameView = (TextView) view.findViewById(R.id.name);
            mCallButton = (ImageButton) view.findViewById(R.id.call_button);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}
