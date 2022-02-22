package com.example.smartcard.fragments.add;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.smartcard.R;
import com.example.smartcard.database.dao.CurriculumDAO;
import com.example.smartcard.database.dao.PortfolioDAO;
import com.example.smartcard.interfaces.UpdateCounts;
import com.example.smartcard.model.CVModel;
import com.example.smartcard.model.ProjectModel;
import com.example.smartcard.model.UserModel;
import com.google.android.material.textfield.TextInputLayout;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class AddProjectFragment extends DialogFragment {
    private TextInputLayout txtTitle;
    private TextInputLayout txtDescription;
    private TextInputLayout txtLink;
    private Button btnAdd;

    private UserModel user;
    private UpdateCounts update;


    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            dismiss();

            return false;
        }
    });


    //Constructor
    public AddProjectFragment(UserModel user, UpdateCounts update){
        this.user = user;
        this.update = update;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_project, container, false);

        txtTitle = rootView.findViewById(R.id.txt_add_project_title);
        txtDescription = rootView.findViewById(R.id.txt_add_project_description);
        txtLink = rootView.findViewById(R.id.txt_add_project_link);
        btnAdd = rootView.findViewById(R.id.btn_add_project);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = txtTitle.getEditText().getText().toString();
                String description = txtDescription.getEditText().getText().toString();
                String link = txtLink.getEditText().getText().toString();

                //Call the DAO to add the Experience
                if (!title.equals("") && !description.equals("") && !link.equals("")){

                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            //Update DB
                            PortfolioDAO.addProject(title, description, link);

                            handler.sendEmptyMessage(0);
                        }
                    };
                    Thread threadDB = new Thread(runnable);
                    threadDB.start();

                    //Update User Model
                    ProjectModel project = new ProjectModel(title, description, link);
                    List<ProjectModel> projects = user.getProjects();

                    if(projects == null) projects = new ArrayList<>();
                    projects.add(0, project);

                    user.setProjects(projects);
                    update.updatePortfolio();
                }
                else {
                    Toast.makeText(getContext(), "Empty Fields!", Toast.LENGTH_SHORT).show();
                }

                //Close the Fragment
                //dismiss();
            }
        });

        return rootView;
    }
}