package com.example.smartcard.fragments.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.smartcard.R;
import com.example.smartcard.model.ProjectModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

public class PortfolioFragmentDetails extends BottomSheetDialogFragment {
    //Attributes
    private ImageView imgPortfolio;
    private TextView title,description;

    //Constructor
    public PortfolioFragmentDetails() { }

    //Methods
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portfolio_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgPortfolio = view.findViewById(R.id.portfolio_details_img);
        title = view.findViewById(R.id.portfolio_details_title);
        description = view.findViewById(R.id.portfolio_details_desc);

        Bundle bundle = getArguments();
        ProjectModel item = (ProjectModel) bundle.getSerializable("object");

        loadPortfolioData(item);
    }

    void loadPortfolioData(ProjectModel item) {
        Picasso.get().load(item.getImageLink()).error(R.drawable.userspace).into(imgPortfolio);
        title.setText(item.getTitle());
        description.setText(item.getDescription());
    }
}