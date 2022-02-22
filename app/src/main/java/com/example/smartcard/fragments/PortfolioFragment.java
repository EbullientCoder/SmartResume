package com.example.smartcard.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.smartcard.R;
import com.example.smartcard.adapters.PortfolioAdapter;
import com.example.smartcard.fragments.details.PortfolioFragmentDetails;
import com.example.smartcard.interfaces.PortfolioCallback;
import com.example.smartcard.model.ProjectModel;
import com.example.smartcard.model.UserModel;

import java.util.List;


public class PortfolioFragment extends Fragment implements PortfolioCallback {
    //Attributes
    private List<ProjectModel> projects;

    private UserModel user;

    //Constructor
    public PortfolioFragment(UserModel user) {
        this.user = user;
    }

    //Methods
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_portfolio, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //List
        projects = user.getProjects();

        //Adapter
        PortfolioAdapter portfolioAdapter = new PortfolioAdapter(projects,this);

        //Recycler View
        RecyclerView rv_portfolio = view.findViewById(R.id.rv_portfolio);
        rv_portfolio.setLayoutManager(new GridLayoutManager(getActivity(),3));
        rv_portfolio.setAdapter(portfolioAdapter);
    }

    @Override
    public void onPortfolioItemClick(int pos) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("object", projects.get(pos));

        PortfolioFragmentDetails portfolioFragmentDetails = new PortfolioFragmentDetails();
        portfolioFragmentDetails.setArguments(bundle);
        portfolioFragmentDetails.show(getActivity().getSupportFragmentManager(),"tagname");
    }
}
