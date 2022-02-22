package com.example.smartcard.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.smartcard.R;
import com.example.smartcard.adapters.CVAdapter;
import com.example.smartcard.database.dao.CurriculumDAO;
import com.example.smartcard.model.CVModel;
import com.example.smartcard.model.UserModel;

import java.util.List;


public class CVFragment extends Fragment {
    //Attributes
    private UserModel user;

    //Constructor
    public CVFragment(UserModel user){
        this.user = user;
    }

    //Methods
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Get List of CV items
        List<CVModel> items = user.getCurriculum();

        //Set the Adapter
        CVAdapter adapter = new CVAdapter(items);

        //Recycler View
        RecyclerView RvCv = view.findViewById(R.id.recyclerview_cv);
        RvCv.setLayoutManager(new LinearLayoutManager(getContext()));
        RvCv.setAdapter(adapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cv, container, false);
    }
}
