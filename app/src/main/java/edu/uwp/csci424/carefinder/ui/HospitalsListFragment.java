package edu.uwp.csci424.carefinder.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.uwp.csci424.carefinder.R;
import edu.uwp.csci424.carefinder.data.local.HospitalRepository;

/**
 * A fragment representing a list of hospitals.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class HospitalsListFragment extends Fragment {

    private OnListFragmentInteractionListener mListener;
    private MyhospitalRecyclerViewAdapter mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HospitalsListFragment() {
    }

    /**
     * Static method for creating a new HospitalsListFragment
     *
     * @return new HospitalListFragment
     */
    public static HospitalsListFragment newInstance() {
        return new HospitalsListFragment();
    }

    /**
     * runs when a new HospitalsListFragment is created
     *
     * @param savedInstanceState states to restore in the fragment
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Runs when the view needs to be created for the fragment
     *
     * @param inflater to inflate the view
     * @param container to hold the view
     * @param savedInstanceState states to restore in the view
     * @return view that was inflated
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hospital_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            HospitalRepository hospitalRepository = HospitalRepository.getInstance();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            if (mAdapter == null) {
                mAdapter = new MyhospitalRecyclerViewAdapter(hospitalRepository.getHospitals(), mListener);
            }
            recyclerView.setAdapter(mAdapter);

        }
        return view;
    }

    /**
     * Runs when the fragment is attached to a fragment manager
     *
     * @param context The fragment is attached to
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    /**
     * Runs when the fragment is detached from a fragment manager
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Method for updating the User Interface after updating the hospitals list
     */
    public void onHospitalUpdateComplete() {
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(int position);
    }
}
