package com.example.smartcard.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcard.R;
import com.example.smartcard.fragments.add.AddExperienceFragment;
import com.example.smartcard.fragments.add.AddProjectFragment;
import com.example.smartcard.interfaces.UpdateCounts;
import com.example.smartcard.model.UserModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Profile extends AppCompatActivity implements UpdateCounts {
    private UserModel user;
    //Language
    private TextView language;
    private TextView fullname;
    //Graph
    private LineChart lineChartDownFill;
    //Button
    private ImageButton btnGoBack;
    private Button btnEditProject;
    private Button btnEditExperience;
    private ImageView btnLinkedin;
    private ImageView btnGithub;
    private ImageView btnInstagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Get User
        user = (UserModel) getIntent().getExtras().getSerializable("User");

        fullname = findViewById(R.id.name_surname);
        fullname.setText(user.getFirstname() + " " + user.getLastname());

        //Language
        displayLanguage();

        //Graph
        createGraph();

        //Buttons
        setButtons();
    }



    //Check Language
    private void displayLanguage(){
        language = findViewById(R.id.txt_language);

        if(Locale.getDefault().getDisplayLanguage().equals("English")) language.setText("EN");
        else language.setText("IT");
    }


    //Buttons Method
    private void setButtons(){
        btnGoBack = findViewById(R.id.btn_goback);
        btnEditExperience = findViewById(R.id.btn_edit_experience);
        btnEditProject = findViewById(R.id.btn_edit_project);

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main.class);
                intent.putExtra("User", user);

                startActivity(intent);
            }
        });


        btnEditProject.setText(Integer.toString(user.getProjects().size()));
        btnEditProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProjectFragment();
            }
        });

        btnEditExperience.setText(Integer.toString(user.getCurriculum().size()));
        btnEditExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openExperienceFragment();
            }
        });

        //Social
        btnLinkedin = findViewById(R.id.linkedin);
        btnLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(browserIntent);
            }
        });

        btnGithub = findViewById(R.id.github);
        btnGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/EbullientCoder"));
                startActivity(browserIntent);
            }
        });

        btnInstagram = findViewById(R.id.instagram);
        btnInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(browserIntent);
            }
        });
    }

    //Open Fragment
    private void openProjectFragment(){
        AddProjectFragment fragment = new AddProjectFragment(user, this);
        fragment.show(getSupportFragmentManager(), "Add Project");
    }

    private void openExperienceFragment(){
        AddExperienceFragment fragment = new AddExperienceFragment(user, this);
        fragment.show(getSupportFragmentManager(), "Add Experience");
    }



    //Graph Methods
    private void createGraph(){
        lineChartDownFill = findViewById(R.id.profile_line_chart);

        lineChartDownFill.getAxisRight().setEnabled(false);
        YAxis leftAxis = lineChartDownFill.getAxisLeft();
        leftAxis.setEnabled(false);

        XAxis xAxis = lineChartDownFill.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);

        lineChartDownFill.setTouchEnabled(true);
        lineChartDownFill.setScaleEnabled(true);
        lineChartDownFill.setPinchZoom(true);

        lineChartDownFill.setMaxHighlightDistance(200);
        lineChartDownFill.setViewPortOffsets(0, 0, 0, 0);
        lineChartDownFillWithData();

        Legend legend = lineChartDownFill.getLegend();
        legend.setEnabled(false);
    }

    private void lineChartDownFillWithData() {
        Description description = new Description();
        description.setText("Completed Projects per Months  ");

        lineChartDownFill.setDescription(description);


        ArrayList<Entry> entryArrayList = new ArrayList<>();
        entryArrayList.add(new Entry(0, 7, "0"));
        entryArrayList.add(new Entry(1, 2, "0"));
        entryArrayList.add(new Entry(2, 4, "0"));
        entryArrayList.add(new Entry(3, 4, "0"));
        entryArrayList.add(new Entry(4, 1, "0"));
        entryArrayList.add(new Entry(5, 3, "0"));
        entryArrayList.add(new Entry(6, 5, "0"));
        entryArrayList.add(new Entry(7, 4, "0"));
        entryArrayList.add(new Entry(8, 6, "0"));
        entryArrayList.add(new Entry(9, 3, "0"));
        entryArrayList.add(new Entry(10, 4, "1"));
        entryArrayList.add(new Entry(11, 2, "2"));
        entryArrayList.add(new Entry(12, 2, "2"));
        entryArrayList.add(new Entry(13, 4, "3"));
        entryArrayList.add(new Entry(14, 4, "4"));
        entryArrayList.add(new Entry(15, 1, "5"));
        entryArrayList.add(new Entry(16, 3, "6"));
        entryArrayList.add(new Entry(17, 5, "7"));
        entryArrayList.add(new Entry(18, 4, "8"));
        entryArrayList.add(new Entry(19, 6, "9"));
        entryArrayList.add(new Entry(20, 3, "10"));
        entryArrayList.add(new Entry(21, 4, "11"));
        entryArrayList.add(new Entry(22, 2, "12"));


        //LineDataSet is the line on the graph
        LineDataSet lineDataSet = new LineDataSet(entryArrayList, "Monthly Projects");

        lineDataSet.setLineWidth(2f);

        //Line Color
        lineDataSet.setColor(getColor(R.color.light_blue));

        lineDataSet.setCircleColor(R.color.background_light);
        lineDataSet.setHighLightColor(Color.RED);
        lineDataSet.setDrawValues(true);

        //to make the smooth line as the graph is adrapt change so smooth curve
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        //to enable the cubic density : if 1 then it will be sharp curve
        lineDataSet.setCubicIntensity(0.2f);

        //to fill the below of smooth line in graph
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFillColor(R.color.background_light);
        //set the transparency
        lineDataSet.setFillAlpha(40);

        //set the gradiant then the above draw fill color will be replace
        Drawable drawable = getDrawable(R.drawable.gradiend_chart);
        lineDataSet.setFillDrawable(drawable);

        //to remove the cricle from the graph
        lineDataSet.setDrawCircles(true);

        ArrayList<ILineDataSet> iLineDataSetArrayList = new ArrayList<>();
        iLineDataSetArrayList.add(lineDataSet);

        //LineData is the data accord
        LineData lineData = new LineData(iLineDataSetArrayList);

        lineData.setValueFormatter(new MyValueFormatter());
        lineData.setValueTextSize(13f);
        lineData.setValueTextColor(Color.BLACK);


        lineChartDownFill.setData(lineData);
        lineChartDownFill.setVisibleXRangeMaximum(7);
        lineChartDownFill.moveViewToX(20f);
        lineChartDownFill.invalidate();
    }


    //Convert number from decimal to Integers
    private class MyValueFormatter extends ValueFormatter {

        private DecimalFormat mFormat;

        public MyValueFormatter() {
            mFormat = new DecimalFormat("#");
        }

        @Override
        public String getFormattedValue(float value) {
            return mFormat.format(value);
        }
    }


    //Interface methods to update counts of projects and experiences when an item has been added
    @Override
    public void updatePortfolio() {
        btnEditProject.setText(Integer.toString(user.getProjects().size()));
    }

    @Override
    public void updateCV() {
        btnEditExperience.setText(Integer.toString(user.getCurriculum().size()));
    }


}