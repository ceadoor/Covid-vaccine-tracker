package com.cea.covidshield.ui.helpline_numbers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.cea.covidshield.databinding.FragmentHelpLineBinding;


public class HelplineFragment extends Fragment{

    private FragmentHelpLineBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHelpLineBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        final Button buttonDisha = binding.dishaDial;
        buttonDisha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                phoneIntent.setData(Uri.parse("tel:1056"));
                startActivity(phoneIntent);
            }
        });

        final Button buttonPolice = binding.policeDial;
        buttonPolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                phoneIntent.setData(Uri.parse("tel:9846100100"));
                startActivity(phoneIntent);
            }
        });

        final Button buttonWomen = binding.womenDial;
        buttonWomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                phoneIntent.setData(Uri.parse("tel:1091"));
                startActivity(phoneIntent);
            }
        });

        final Button buttonChild = binding.childDial;
        buttonChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                phoneIntent.setData(Uri.parse("tel:1098"));
                startActivity(phoneIntent);
            }
        });

        final Button buttonFlood = binding.fldDial;
        buttonFlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                phoneIntent.setData(Uri.parse("tel:1070"));
                startActivity(phoneIntent);
            }
        });

        final Button buttonCyber = binding.cyberDial;
        buttonCyber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                phoneIntent.setData(Uri.parse("tel:04712322090"));
                startActivity(phoneIntent);
            }
        });

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
