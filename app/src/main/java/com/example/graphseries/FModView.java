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


public class FModView extends Fragment {
    int n=100;
    double[] t;
    double[] fm=new double[n];
    double f1,f2,mf=20;
    GraphView graph;
    Button show;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        f1 = getArguments().getDouble("messageF");
        t = getArguments().getDoubleArray("time");
        f2 = getArguments().getDouble("carrierF");
        return inflater.inflate(R.layout.fragment_f_mod_view, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        graph=getView().findViewById(R.id.FMgraph);
        show=getView().findViewById(R.id.FMbutton);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genFm();
            }
        });
    }

    public DataPoint[] FMdata(){
        //to find out the no. of data-points
        DataPoint[] values = new DataPoint[n];     //creating an object of type DataPoint[] of size 'n'
        for(int i=0;i<n;i++){
            DataPoint v = new DataPoint(t[i],fm[i]);
            values[i] = v;
        }
        return values;
    }

    void genFm()
    {
        //Under-Modulated Signal
        for(int i=0;i<n;i++)
        {
            fm[i]=2*3.14*f1*t[i];
            fm[i]=Math.sin(fm[i]);
            fm[i]=mf*fm[i];
            fm[i]+=2*3.14*f2*t[i];
            fm[i]=Math.sin(fm[i]);

        }
        graph.removeAllSeries();
        for(int i=0;i<n;i++){
            LineGraphSeries<DataPoint> series;       //an Object of the PointsGraphSeries for plotting scatter graphs
            series= new LineGraphSeries<>(FMdata());   //initializing/defining series to get the data from the method 'data()'
            graph.addSeries(series);                   //adding the series to the GraphView

        }
    }


}