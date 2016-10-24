package com.example.mkim11235.gainztracker.ExerciseEntryFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mkim11235.gainztracker.ExerciseEntryBaseFragment;
import com.example.mkim11235.gainztracker.R;

/**
 * Created by Michael on 10/22/2016.
 */

public abstract class ExerciseEntryFragment extends ExerciseEntryBaseFragment {
    private static final String EDIT_TEXT_ERROR = "Please enter a valid entry";

    protected Button mExerciseEntryButton;
    protected EditText mExerciseName;
    protected EditText mExerciseMuscle;

    //Todo: if implement options menu, uncomment line below
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
    }

    /*
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exercise_entry, container, false);

        Bundle args = getArguments();
        if (args != null) {
            setExtraMembersFromBundle(args);
        }

        // Initialize member variables
        mExerciseName = (EditText) rootView.findViewById(R.id.edittext_exercise_name);
        mExerciseMuscle = (EditText) rootView.findViewById(R.id.edittext_exercise_muscle);
        mExerciseEntryButton = (Button) rootView.findViewById(R.id.button_exercise_entry);

        setEditTextDefaults();
        setFinalButtonText();
        setFinalButtonOnClickListener();

        return rootView;
    }

    /**
     * Checks if name and muscle have length > 0
     * @param name exercise name to check
     * @param muscle exercise muscle to check
     * @return true if both name and muscle length > 0, otherwise false and sets errors on edittext
     */
    protected boolean allValidEntries(String name, String muscle) {
        boolean allValid = true;
        if (name.length() == 0) {
            mExerciseName.setError(EDIT_TEXT_ERROR);
            allValid = false;
        }

        if (muscle.length() == 0) {
            mExerciseMuscle.setError(EDIT_TEXT_ERROR);
            allValid = false;
        }

        return allValid;
    }

}