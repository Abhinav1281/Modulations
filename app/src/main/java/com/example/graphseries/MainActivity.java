package com.example.graphseries;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
//    int n=200;
//    double[] t=new double[n];
//    double[] m=new double[n];
//    double[] c=new double[n];
//    double[] um=new double[n];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottom_nav=findViewById(R.id.bottom_nav);

       bottom_nav.setOnNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.Fragment_container,
                        new MCView()).commit();

        getSupportActionBar().hide();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.navigation_home:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Fragment_container,
                                new MCView()).commit();
                break;

                case R.id.navigation_FM:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.Fragment_container,
                                    new FreqModMain()).commit();
                    break;
        }
        return true;
    }


//
}