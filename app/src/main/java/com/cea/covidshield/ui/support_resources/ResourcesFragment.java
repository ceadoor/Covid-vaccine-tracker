package com.cea.covidshield.ui.support_resources;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;



import com.cea.covidshield.databinding.FragmentResourcesBinding;


public class ResourcesFragment extends Fragment{

    private FragmentResourcesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentResourcesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView textView = binding.textView3;

        String s ="Resources page!!!";

        textView.setText(s);

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
