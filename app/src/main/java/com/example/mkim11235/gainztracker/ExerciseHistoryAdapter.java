package com.example.mkim11235.gainztracker;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Michael on 10/17/2016.
 */

public class ExerciseHistoryAdapter extends CursorAdapter {
    public ExerciseHistoryAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_exercise_history, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewWeight = (TextView) view.findViewById(R.id.list_item_exercise_history_weight);
        TextView textViewReps = (TextView) view.findViewById(R.id.list_item_exercise_history_reps);
        TextView textViewDate = (TextView) view.findViewById(R.id.list_item_exercise_history_date);

        textViewWeight.setText(getWeightFromCursor(cursor));
        textViewReps.setText(getRepsFromCursor(cursor));
        textViewDate.setText(getDateFromCursor(cursor));
    }


    private String getWeightFromCursor(Cursor cursor) {
        return cursor.getString(ExerciseActivity.COL_EXERCISE_HISTORY_WEIGHT);
    }

    private String getRepsFromCursor(Cursor cursor) {
        return cursor.getString(ExerciseActivity.COL_EXERCISE_HISTORY_REPS);
    }

    private String getDateFromCursor(Cursor cursor) {
        return cursor.getString(ExerciseActivity.COL_EXERCISE_HISTORY_DATE);
    }
}
