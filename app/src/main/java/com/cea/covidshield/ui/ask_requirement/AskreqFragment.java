package com.cea.covidshield.ui.ask_requirement;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cea.covidshield.databinding.FragmentAskreqBinding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AskreqFragment extends Fragment {


    private FragmentAskreqBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAskreqBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        final Button button = binding.button;


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String name = binding.names.getText().toString();
                final String phone = binding.Phone.getText().toString();
                final String email = binding.EmailAddress.getText().toString();
                final String address = binding.address.getText().toString();
                final String requirements = binding.requirements.getText().toString();




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
                else if( phone.length() < 10 )
                {
                    binding.Phone.requestFocus();
                    binding.Phone.setError("ENTER VALID PHONE NUMBER");
                }
                else if(email.length()!=0 && !email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
                    binding.EmailAddress.requestFocus();
                    binding.EmailAddress.setError("ENTER A VALID EMAIL ADDRESS");
                }
                else if(address.length()==0)
                {
                    binding.address.requestFocus();
                    binding.address.setError("FIELD CANNOT BE EMPTY");
                }
                else if(requirements.length()==0)
                {
                    binding.requirements.requestFocus();
                    binding.requirements.setError("FIELD CANNOT BE EMPTY");
                }
                else {
                    // add your code here
                    Toast.makeText(getActivity(), "Your Response has been saved", Toast.LENGTH_SHORT).show();

//                    Intent intent = get;
//                    getActivity().finish();
//                    startActivity(intent);
                }
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