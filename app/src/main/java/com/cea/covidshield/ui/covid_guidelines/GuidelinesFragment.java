package com.cea.covidshield.ui.covid_guidelines;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;



import com.cea.covidshield.databinding.FragmentCvdGdBinding;

public class GuidelinesFragment extends Fragment {

    private FragmentCvdGdBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCvdGdBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView textView = binding.textView9;

        String s ="Guidelines page!!!";

        textView.setText(s);

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
