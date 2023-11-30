package com.example.assignment_9_1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class NumberPickerDialogFragment extends DialogFragment {

    private static final String ARG_TITLE = "title";
    private static final String ARG_MIN_VALUE = "minValue";
    private static final String ARG_MAX_VALUE = "maxValue";
    private static final String ARG_STEP_SIZE = "stepSize";

    private String title;
    private double minValue;
    private int maxValue;
    private double stepSize;
    private NumberPickerListener listener;

    public NumberPickerDialogFragment() {
        // Required empty public constructor
    }

    public static NumberPickerDialogFragment newInstance(String title, double minValue, int maxValue, double stepSize) {
        NumberPickerDialogFragment fragment = new NumberPickerDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putDouble(ARG_MIN_VALUE, minValue);
        args.putInt(ARG_MAX_VALUE, maxValue);
        args.putDouble(ARG_STEP_SIZE, stepSize);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
            minValue = getArguments().getDouble(ARG_MIN_VALUE);
            maxValue = getArguments().getInt(ARG_MAX_VALUE);
            stepSize = getArguments().getDouble(ARG_STEP_SIZE);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Context context = requireContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_number_picker_dialog, null);

        NumberPicker numberPicker = view.findViewById(R.id.numberPicker);
        Button buttonOk = view.findViewById(R.id.buttonOk);

        int minValueInt = (int) (minValue / stepSize);

        numberPicker.setMinValue(minValueInt);
        numberPicker.setMaxValue(maxValue);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker.setFormatter(value -> String.valueOf(value * stepSize));

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    double value = numberPicker.getValue() * stepSize;
                    int valueInt = numberPicker.getValue();
                    listener.onValueSelected(value);
                    listener.onValueSelected(valueInt);
                }
                dismiss();
            }
        });

        return new androidx.appcompat.app.AlertDialog.Builder(context)
                .setTitle(title)
                .setView(view)
                .create();
    }

    public void setListener(NumberPickerListener listener) {
        this.listener = listener;
    }

    public interface NumberPickerListener {
        // By default, the method does nothing, we will customize based on our usecase!
        default void onValueSelected(int value){
         // Do nothing
        }

        default void onValueSelected(double value) {
            // Do nothing
        }
    }
}
