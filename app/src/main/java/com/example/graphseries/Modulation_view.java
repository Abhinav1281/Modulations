package com.example.graphseries;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;


public class Modulation_view extends Fragment {

    int n=100;
    double[] t;
    double[] m;
    double[] c;
    double[] um=new double[n];
    double[] pm=new double[n];
    double[] om=new double[n];
    GraphView umgraph,pmgraph,omgraph;
    Button umshow,pmshow,omshow;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        m = getArguments().getDoubleArray("message");
        t = getArguments().getDoubleArray("time");
        c = getArguments().getDoubleArray("carrier");
        return inflater.inflate(R.layout.fragment_modulation_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        umgraph = getView().findViewById(R.id.UMgraph);
        pmgraph = getView().findViewById(R.id.PMgraph);
        omgraph = getView().findViewById(R.id.OMgraph);

        umshow=getView().findViewById(R.id.UMbutton);
        pmshow=getView().findViewById(R.id.PMbutton);
        omshow=getView().findViewById(R.id.OMbutton);

        umshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genUM();
            }
        });
        pmshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genPM();
            }
        });
        omshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genOM();
            }
        });
    }

    public DataPoint[] UMdata(){
        //to find out the no. of data-points
        DataPoint[] values = new DataPoint[n];     //creating an object of type DataPoint[] of size 'n'
        for(int i=0;i<n;i++){
            DataPoint v = new DataPoint(t[i],um[i]);
            values[i] = v;
        }
        return values;
    }
    public DataPoint[] PMdata(){
        //to find out the no. of data-points
        DataPoint[] values = new DataPoint[n];     //creating an object of type DataPoint[] of size 'n'
        for(int i=0;i<n;i++){
            DataPoint v = new DataPoint(t[i],pm[i]);
            values[i] = v;
        }
        return values;
    }

    public DataPoint[] OMdata(){
        //to find out the no. of data-points
        DataPoint[] values = new DataPoint[n];     //creating an object of type DataPoint[] of size 'n'
        for(int i=0;i<n;i++){
            DataPoint v = new DataPoint(t[i],om[i]);
            values[i] = v;
        }
        return values;
    }


    void genUM()
    {
        //Under-Modulated Signal
        double m1=0.5;
        for(int i=0;i<n;i++)
        {
            um[i]=1+m1*m[i];
            um[i]=um[i]*c[i];

        }
        umgraph.removeAllSeries();
        for(int i=0;i<n;i++){
            LineGraphSeries<DataPoint> series;       //an Object of the PointsGraphSeries for plotting scatter graphs
            series= new LineGraphSeries<>(UMdata());   //initializing/defining series to get the data from the method 'data()'
            umgraph.addSeries(series);                   //adding the series to the GraphView

        }

    }

    void genPM()
    {
        //Under-Modulated Signal
        double m1=1.0;
        for(int i=0;i<n;i++)
        {
            pm[i]=1+m1*m[i];
            pm[i]=pm[i]*c[i];

        }
        pmgraph.removeAllSeries();
        for(int i=0;i<n;i++){
            LineGraphSeries<DataPoint> series;       //an Object of the PointsGraphSeries for plotting scatter graphs
            series= new LineGraphSeries<>(PMdata());   //initializing/defining series to get the data from the method 'data()'
            pmgraph.addSeries(series);                   //adding the series to the GraphView

        }
    }

    void genOM()
    {
        //Under-Modulated Signal
        double m1=1.5;
        for(int i=0;i<n;i++)
        {
            om[i]=1+m1*m[i];
            om[i]=om[i]*c[i];

        }
        omgraph.removeAllSeries();
        for(int i=0;i<n;i++){
            LineGraphSeries<DataPoint> series;       //an Object of the PointsGraphSeries for plotting scatter graphs
            series= new LineGraphSeries<>(OMdata());   //initializing/defining series to get the data from the method 'data()'
            omgraph.addSeries(series);                   //adding the series to the GraphView

        }

    }
}