package com.example.assignment_9_1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment_9_1.databinding.FragmentMainBinding;

import java.io.Serializable;
import java.util.List;

public class MainFragment extends Fragment {
    private static final String ARG_PARAM_LOGS = "ARG_PARAM_LOGS";
    private List<Log> mlogs;
    FragmentMainBinding binding;
    RecyclerView recyclerView;
    LogAdapter logAdapter;


    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(List<Log> logs) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_LOGS, (Serializable) logs);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mlogs = (List<Log>) getArguments().getSerializable(ARG_PARAM_LOGS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Logs");

        recyclerView = binding.recyclerView;
        logAdapter = new LogAdapter(mlogs);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(logAdapter);

        binding.buttonAddLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call the mListener for the addLog method
            }
        });

        binding.buttonViewProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call the mListener for the viewProgress method
            }
        });
    }
}