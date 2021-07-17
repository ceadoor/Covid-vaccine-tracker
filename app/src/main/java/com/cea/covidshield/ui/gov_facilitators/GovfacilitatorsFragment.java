package com.cea.covidshield.ui.gov_facilitators;


import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.cea.covidshield.databinding.FragmentGovfacBinding;

public class GovfacilitatorsFragment extends Fragment {

    private FragmentGovfacBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentGovfacBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        setupHyperlink();
        return root;
    }

    private void setupHyperlink() {
        TextView linkTextView_aSetu = binding.aSetuHyperlink;
        TextView linkTextView_cJagratha = binding.cJagrathaHyperlink;
        linkTextView_cJagratha.setMovementMethod(LinkMovementMethod.getInstance());
        linkTextView_aSetu.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}