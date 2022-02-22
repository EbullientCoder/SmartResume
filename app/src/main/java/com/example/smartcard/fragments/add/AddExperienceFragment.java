package com.example.smartcard.fragments.add;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.smartcard.R;
import com.example.smartcard.database.dao.CurriculumDAO;
import com.example.smartcard.interfaces.UpdateCounts;
import com.example.smartcard.model.CVModel;
import com.example.smartcard.model.UserModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;


public class AddExperienceFragment extends DialogFragment {
    private TextInputLayout txtDate;
    private TextInputLayout txtDescription;
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
    public AddExperienceFragment(UserModel user, UpdateCounts update){
        this.user = user;
        this.update = update;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_experience, container, false);

        txtDate = rootView.findViewById(R.id.txt_add_experience_date);
        txtDescription = rootView.findViewById(R.id.txt_add_experience_description);
        btnAdd = rootView.findViewById(R.id.btn_add_exp);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = txtDate.getEditText().getText().toString();
                String description = txtDescription.getEditText().getText().toString();


                //Call the DAO to add the Experience
                if (!date.equals("") && !description.equals("")){

                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            //Update DB
                            CurriculumDAO.addExperience(date, description);

                            handler.sendEmptyMessage(0);
                        }
                    };
                    Thread threadDB = new Thread(runnable);
                    threadDB.start();

                    //Update User Model
                    CVModel item = new CVModel(date, description);
                    List<CVModel> experiences = user.getCurriculum();

                    if (experiences == null) experiences = new ArrayList<>();
                    experiences.add(0, item);

                    user.setCurriculum(experiences);
                    update.updateCV();
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