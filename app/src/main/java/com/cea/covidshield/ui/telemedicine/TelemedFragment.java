package com.cea.covidshield.ui.telemedicine;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cea.covidshield.databinding.FragmentTeleMedBinding;

public class TelemedFragment extends Fragment {

    private FragmentTeleMedBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentTeleMedBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setupHyperlink();

        return root;
    }

    private void setupHyperlink() {
        TextView linkTextView = binding.telemedHyperlink;
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}



