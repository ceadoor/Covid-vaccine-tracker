package com.cea.covidshield.ui.donation_portal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.cea.covidshield.databinding.FragmentDonPortBinding;

public class PortalFragment extends Fragment {

    private FragmentDonPortBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentDonPortBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final Button button = binding.button;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String name = binding.names.getText().toString();
                final String phone = binding.Phone.getText().toString();
                final String email = binding.EmailAddress.getText().toString();
                final String place = binding.places.getText().toString();
                final String address = binding.address.getText().toString();
                final String equipments = binding.equipments.getText().toString();
                final String materials = binding.materials.getText().toString();
                final String cash = binding.cash.getText().toString();
                final String others = binding.others.getText().toString();


                if(name.length()==0)
                {
                    binding.names.requestFocus();
                    binding.names.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!name.matches("[a-zA-Z ]+"))
                {
                    binding.names.requestFocus();
                    binding.names.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }
                else if(phone.length()==0)
                {
                    binding.Phone.requestFocus();
                    binding.Phone.setError("FIELD CANNOT BE EMPTY");
                }
                else if(place.length()==0)
                {
                    binding.places.requestFocus();
                    binding.places.setError("FIELD CANNOT BE EMPTY");
                }
                else
                    // add your code here
                    Toast.makeText(getActivity(), "Your Response has been saved", Toast.LENGTH_SHORT).show();

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




