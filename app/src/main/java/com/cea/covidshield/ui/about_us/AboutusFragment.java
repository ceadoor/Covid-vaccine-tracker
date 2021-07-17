package com.cea.covidshield.ui.about_us;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.cea.covidshield.databinding.FragmentAboutUsBinding;


public class AboutusFragment extends Fragment {

    private FragmentAboutUsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAboutUsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView textView = binding.developerCredits;

        String s ="Developer Credits placed here!!!";

        textView.setText(s);

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

