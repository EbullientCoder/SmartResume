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
import com.example.smartcard.adapters.TeamAdapter;
import com.example.smartcard.model.TeamMemberModel;
import com.example.smartcard.model.UserModel;

import java.util.List;


public class TeamFragment extends Fragment {
    //Attributes
    private UserModel user;

    //Constructor
    public TeamFragment(UserModel user) {
        this.user = user;
    }

    //Methods
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_team, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //List
        List<TeamMemberModel> members = user.getTeam();

        //Adapter
        TeamAdapter adapter = new TeamAdapter(members);

        //Recycler View
        RecyclerView RvTeam = view.findViewById(R.id.rv_team);
        RvTeam.setLayoutManager(new LinearLayoutManager(getContext()));
        RvTeam.setAdapter(adapter);
    }
}
