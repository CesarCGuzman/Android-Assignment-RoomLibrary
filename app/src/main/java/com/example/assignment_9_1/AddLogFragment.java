package com.example.assignment_9_1;

import static java.lang.Integer.parseInt;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.format.DateFormat;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.assignment_9_1.databinding.FragmentAddLogBinding;

import java.util.Calendar;

public class AddLogFragment extends DialogFragment implements NumberPickerDialogFragment.NumberPickerListener {
    FragmentAddLogBinding binding;
    private static String selectedDate;

    public AddLogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddLogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getChildFragmentManager(), "datePicker");
            }
        });

        binding.buttonSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getChildFragmentManager(), "timePicker");
            }
        });

        binding.buttonSelectHrsSlept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSleepHoursPicker();
            }
        });

        binding.buttonSelectSlepQual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSleepQualityPicker();
            }
        });

        binding.buttonSelectHrsRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExerciseTimePicker();
            }
        });

        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something when the user clicks the button
                mListener.back();
            }
        });

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = selectedDate;
                String time = binding.textViewTime.getText().toString();
                String sleepHrs = binding.textViewSleep.getText().toString();
                String sleepQual = binding.textView6.getText().toString();
                String exerciseTime = binding.textViewHrsExercised.getText().toString();
                String weight = binding.editTextNumberDecimalWeight.getText().toString();

                if (date.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                    builder.setTitle("Error")
                            .setMessage("Please select a date")
                            .setPositiveButton("OK", null)
                            .show();
                } else if (time.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                    builder.setTitle("Error")
                            .setMessage("Please select a time")
                            .setPositiveButton("OK", null)
                            .show();
                } else if (sleepHrs.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                    builder.setTitle("Error")
                            .setMessage("Please select the number of hours slept")
                            .setPositiveButton("OK", null)
                            .show();
                } else if (sleepQual.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                    builder.setTitle("Error")
                            .setMessage("Please select the sleep quality")
                            .setPositiveButton("OK", null)
                            .show();
                } else if (exerciseTime.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                    builder.setTitle("Error")
                            .setMessage("Please select the number of hours exercised")
                            .setPositiveButton("OK", null)
                            .show();
                } else if (weight.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                    builder.setTitle("Error")
                            .setMessage("Please enter your weight")
                            .setPositiveButton("OK", null)
                            .show();
                } else {
                    // Create a new calendar using the date and time
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(parseInt(date.substring(6)), parseInt(date.substring(0, 2)), parseInt(date.substring(3, 5)),
                            parseInt(time.substring(0, 2)), parseInt(time.substring(3, 5)));
                    Log log = new Log(calendar, sleepHrs, sleepQual, exerciseTime, weight);
                    android.util.Log.d("demo", "onClick: " + log.getDateTimeString());
                    selectedDate = "";
                    mListener.addLog(log);
                }
            }
        });
    }

    private void showSleepHoursPicker(){
        NumberPickerDialogFragment sleepHoursPicker = NumberPickerDialogFragment.newInstance(
                "Sleep Hours", 0.5, 15, 0.5f);

        sleepHoursPicker.setListener(new NumberPickerDialogFragment.NumberPickerListener() {

            @Override
            public void onValueSelected(double value) {
                // Do something when the user selects a value
                binding.textViewSleep.setText(String.valueOf(value));
            }
        });

        sleepHoursPicker.show(getChildFragmentManager(), "sleepHoursPicker");
    }

    private void showSleepQualityPicker() {
        NumberPickerDialogFragment sleepQualityPicker = NumberPickerDialogFragment.newInstance(
                "Sleep Quality", 1, 5, 1f);

        sleepQualityPicker.setListener(new NumberPickerDialogFragment.NumberPickerListener() {
            @Override
            public void onValueSelected(int value) {
                // I forgot to name it lol
                binding.textView6.setText(String.valueOf(value));
            }
        });

        sleepQualityPicker.show(getChildFragmentManager(), "sleepQualityPicker");
    }

    private void showExerciseTimePicker() {
        NumberPickerDialogFragment exerciseMinutesPicker = NumberPickerDialogFragment.newInstance(
                "Exercise Hours", 0.5, 15, 0.5f);

        exerciseMinutesPicker.setListener(new NumberPickerDialogFragment.NumberPickerListener() {
            @Override
            public void onValueSelected(double value) {
                binding.textViewHrsExercised.setText(String.valueOf(value));
            }
        });

        exerciseMinutesPicker.show(getChildFragmentManager(), "exerciseMinutesPicker");
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker.
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it.
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Change the time textView to the selected time - 12 hour format
            AddLogFragment addLogFragment = (AddLogFragment) getParentFragment();
            // Format the time to 12 hour format
            String timeFormat = String.format("%02d:%02d %s",
                    (hourOfDay % 12 == 0) ? 12 : hourOfDay % 12,
                    minute,
                    (hourOfDay < 12) ? "AM" : "PM");

            addLogFragment.binding.textViewTime.setText(timeFormat);
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker.
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it.
            return new DatePickerDialog(requireContext(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Change the date textView to the selected date
            AddLogFragment addLogFragment = (AddLogFragment) getParentFragment();
            // Format the date to MM/DD/YYYY
            String dateFormat = String.format("%02d/%02d/%04d", month + 1, day, year);
            selectedDate = String.format("%02d/%02d/%04d", month, day, year);

            addLogFragment.binding.textViewDate.setText(dateFormat);
        }
    }

    AddLogListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (AddLogListener) context;
    }

    public interface AddLogListener {
        void back();
        void addLog(Log log);
    }
}