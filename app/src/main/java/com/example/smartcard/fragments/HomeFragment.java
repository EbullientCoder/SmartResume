package com.example.smartcard.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smartcard.R;
import com.example.smartcard.model.UserModel;


public class HomeFragment extends Fragment {
    //Attributes
    private UserModel user;

    //Constructor
    public HomeFragment(UserModel user){this.user = user;}

    //Methods
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //User Name
        TextView fullName = view.findViewById(R.id.txt_home_fullname);
        fullName.setText(user.getFirstname() + " " + user.getLastname());
        //User Work
        TextView work = view.findViewById(R.id.work);
        work.setText(user.getWork());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }
}
