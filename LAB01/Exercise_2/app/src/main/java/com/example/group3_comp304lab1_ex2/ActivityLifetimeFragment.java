package com.example.group3_comp304lab1_ex2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class ActivityLifetimeFragment extends Fragment {

    private ActivityLifetimeFragment.OnLifetimeFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ActivityLifetimeFragment() {

    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ActivityLifetimeFragment newInstance(int columnCount) {
        ActivityLifetimeFragment fragment = new ActivityLifetimeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_lifecycle, container, false);

        return view;
    }

    public void updateText(String message)
    {
        TextView textView = (TextView)getActivity().findViewById(R.id.textView);

        String currentString = textView.getText().toString();

        currentString += message + "\n";

        textView.setText(currentString);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ActivityLifetimeFragment.OnLifetimeFragmentInteractionListener) {
            mListener = (ActivityLifetimeFragment.OnLifetimeFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnLifetimeFragmentInteractionListener {
        // TODO: Update argument type and name
        void onLifetimeFragmentInteraction(String item);
    }
}
