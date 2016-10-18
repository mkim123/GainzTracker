package com.example.mkim11235.gainztracker;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.mkim11235.gainztracker.data.DatabaseContract;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int EXERCISE_LOADER = 0;

    private static final String[] EXERCISE_COLUMNS = {
            DatabaseContract.ExerciseEntry._ID,
            DatabaseContract.ExerciseEntry.COLUMN_NAME,
            DatabaseContract.ExerciseEntry.COLUMN_MUSCLE,
    };

    static final int COL_EXERCISE_ID = 0;
    static final int COL_EXERCISE_NAME = 1;
    static final int COL_EXERCISE_MUSCLE = 2;

    private ImageButton mAddExerciseButton;
    private ExerciseAdapter mExerciseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sets up loader for getting all exercises
        getSupportLoaderManager().initLoader(EXERCISE_LOADER, null, this);
        mExerciseAdapter = new ExerciseAdapter(this, null, 0);

        ListView listView = (ListView) findViewById(R.id.listview_exercises);
        listView.setAdapter(mExerciseAdapter);
        // On Click goes to exercise history activity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                long id = mExerciseAdapter.getItemId(position);
                Intent intent = new Intent(view.getContext(), ExerciseHistoryActivity.class).putExtra(Intent.EXTRA_TEXT, id);
                startActivity(intent);
            }
        });

        mAddExerciseButton = (ImageButton) findViewById(R.id.image_button_add_exercise);
        // Add Exercise Button onClick starts AddExercise Activity
        mAddExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddExerciseActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                DatabaseContract.ExerciseEntry.CONTENT_URI,
                EXERCISE_COLUMNS,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mExerciseAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mExerciseAdapter.swapCursor(null);
    }
}