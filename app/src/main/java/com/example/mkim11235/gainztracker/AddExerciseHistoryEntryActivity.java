package com.example.mkim11235.gainztracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Michael on 10/17/2016.
 */

public class AddExerciseHistoryEntryActivity extends AppCompatActivity {
    // Is there a point for these ot be member variables?
    // mb put inside on create
    private long mExerciseId;
    private String mExerciseName;

    Button mAddExerciseHistoryEntryButton;
    EditText mWeightEditText;
    EditText mRepsEditText;
    EditText mDateEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise_history_entry);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get extras from bundle
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mExerciseId = extras.getLong(getString(R.string.EXTRA_EXERCISE_ID));
            mExerciseName = extras.getString(getString(R.string.EXTRA_EXERCISE_NAME));
        }

        setTitle("Add " + mExerciseName + " Entry");

        mWeightEditText = (EditText) findViewById(R.id.edittext_exercise_history_entry_weight);
        mRepsEditText = (EditText) findViewById(R.id.edittext_exercise_history_entry_reps);
        mDateEditText = (EditText) findViewById(R.id.edittext_exercise_history_entry_date);

        // When button clicked, create new entry in exercise history table, return to main
        mAddExerciseHistoryEntryButton = (Button) findViewById(R.id.button_add_exercise_history_entry_final);
        mAddExerciseHistoryEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Try converting all to long so asynctask can take params
                long weight = Integer.parseInt(String.valueOf(mWeightEditText.getText()));
                long reps = Integer.parseInt(String.valueOf(mRepsEditText.getText()));
                long date = Integer.parseInt(String.valueOf(mDateEditText.getText()));

                // Add new exercise history entry to DB
                AddExerciseHistoryDBTask dbTask = new AddExerciseHistoryDBTask(AddExerciseHistoryEntryActivity.this);
                dbTask.execute(mExerciseId, weight, reps, date);

                // Return to exercise activity
                Intent intent = new Intent(v.getContext(), ExerciseHistoryActivity.class)
                        .putExtra(Intent.EXTRA_TEXT, mExerciseId);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}