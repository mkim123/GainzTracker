package com.example.mkim11235.gainztracker.ExerciseHistoryEntryActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mkim11235.gainztracker.AddExerciseHistoryDBTask;
import com.example.mkim11235.gainztracker.ExerciseHistoryActivity;
import com.example.mkim11235.gainztracker.FetchMostRecentWeightRepsGivenExerciseIdTask;
import com.example.mkim11235.gainztracker.R;
import com.example.mkim11235.gainztracker.Utility;

/**
 * Created by Michael on 10/17/2016.
 */

public class AddExerciseHistoryEntryActivity extends ExerciseHistoryEntryActivity {
    /**
     * Initialize members with any extra args from bundle
     * @param bundle bundle containing args
     */
    @Override
    protected void initExtraArguments(Bundle bundle) {}

    /**
     * Set the default weight and rep texts to appropriate values
     */
    @Override
    protected void getAndSetDefaultWeightAndReps() {
        new FetchMostRecentWeightRepsGivenExerciseIdTask(this).execute(mExerciseId);
    }

    @Override
    protected  void setupFinalButtonText() {
        mExerciseHistoryFinalButton.setText(
                getString(R.string.button_add_exercise_history_entry_text_final));
    }

    @Override
    protected void setupFinalButtonOnClick() {
        mExerciseHistoryFinalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weightString = String.valueOf(mWeightEditText.getText());
                String repsString = String.valueOf(mRepsEditText.getText());
                String dateString = String.valueOf(mDateEditText.getText());

                // Validation check. all must be entered
                if (allValidEntries(weightString, repsString, dateString)) {
                    // Try converting all to long so asynctask can take params
                    long weight = Integer.parseInt(weightString);
                    long reps = Integer.parseInt(repsString);
                    // Format date string into DB format
                    dateString = Utility.formatDateReadableToDB(dateString);
                    long date = Integer.parseInt(dateString);

                    // Add new exercise history entry to DB
                    AddExerciseHistoryDBTask dbTask = new AddExerciseHistoryDBTask(AddExerciseHistoryEntryActivity.this);
                    dbTask.execute(mExerciseId, weight, reps, date);

                    // Return to exercise activity
                    Intent intent = new Intent(v.getContext(), ExerciseHistoryActivity.class)
                            .putExtra(Intent.EXTRA_TEXT, mExerciseId);
                    startActivity(intent);
                }
            }
        });
    }
}
