package com.cea.covidshield.ui.blood_donation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.cea.covidshield.R;
import com.cea.covidshield.databinding.FragmentBldDonBinding;



public class DonationFragment extends Fragment implements AdapterView.OnItemSelectedListener{


    private FragmentBldDonBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentBldDonBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final Button button = binding.button;

        String[] blood_donation_dropdown = root.getResources().getStringArray(R.array.blood_donation_dropdown);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.fragment_bld_don_dropdown_item, blood_donation_dropdown);
        binding.textView2.setAdapter(arrayAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String name = binding.names.getText().toString();
                final String phone = binding.Phone.getText().toString();
                final String email = binding.EmailAddress.getText().toString();
                final String address = binding.address.getText().toString();
                final String place = binding.place.getText().toString();

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
                else if(phone.length() < 10)
                {
                    binding.Phone.requestFocus();
                    binding.Phone.setError("FIELD CANNOT BE EMPTY");
                }
                else if(email.length()!=0 && !email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
                    binding.EmailAddress.requestFocus();
                    binding.EmailAddress.setError("ENTER A VALID EMAIL ADDRESS");
                }
                else if(place.length()==0)
                {
                    binding.place.requestFocus();
                    binding.place.setError("FIELD CANNOT BE EMPTY");
                }
                else
                    // add other functions here
                    Toast.makeText(getActivity(),"Your Response has been saved",Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String item = adapterView.getItemAtPosition(i).toString();

        Toast.makeText(getActivity(),item,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
