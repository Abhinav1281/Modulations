package com.example.graphseries;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DecimalFormat;

public class FreqModMain extends Fragment {

    int n=100;
    double f1=0.0,f2=0.0;
    double[] t=new double[n];
    double[] m=new double[n];
    double[] c=new double[n];
    GraphView graph,Cgraph;
    EditText mf,cf;
    Button mgen,cgen;
    DecimalFormat df = new DecimalFormat("0.00");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_freq_mod_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        graph = getView().findViewById(R.id.Mgraph);
        Cgraph = getView().findViewById(R.id.Cgraph);
        Button button=getView().findViewById(R.id.Modulatebutton);
        mf=getView().findViewById(R.id.mEdit);
        cf=getView().findViewById(R.id.cEdit);
        mgen=getView().findViewById(R.id.MShow);
        cgen=getView().findViewById(R.id.CShow);


        double x=0.01;
        for(int i=0;i<n;i++)
        {
            t[i]=x;
            x+=0.01;
        }

        mgen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genM();
            }
        });
        cgen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genC();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(f1==0 || f2==0)
                {
                    Toast.makeText(getContext(),"First Generate Wavefroms"
                            ,Toast.LENGTH_SHORT).show();
                }
                else {
                    Bundle bundle = new Bundle();
                    bundle.putDouble("messageF", f1);
                    bundle.putDoubleArray("time", t);
                    bundle.putDouble("carrierF", f2);
                    FModView fragInfo = new FModView();
                    fragInfo.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.Fragment_container,
                            fragInfo).addToBackStack("BACK")
                            .commit();
                }
            }
        });
    }

    public DataPoint[] Mdata(){
        //to find out the no. of data-points
        DataPoint[] values = new DataPoint[n];     //creating an object of type DataPoint[] of size 'n'
        for(int i=0;i<n;i++){
            DataPoint v = new DataPoint(t[i],m[i]);
            values[i] = v;
        }
        return values;
    }

    public DataPoint[] Cdata(){
        //to find out the no. of data-points
        DataPoint[] values = new DataPoint[n];     //creating an object of type DataPoint[] of size 'n'
        for(int i=0;i<n;i++){
            DataPoint v = new DataPoint(t[i],c[i]);
            values[i] = v;
        }
        return values;
    }

    void getmF()
    {
        f1=Double.parseDouble(mf.getText().toString());
    }
    void getcF()
    {
        f2=Double.parseDouble(cf.getText().toString());
    }

    void genM()
    {
        getmF();
        for(int i=0;i<n;i++)
        {
            m[i]=2*3.14*f1*t[i];
            m[i]= Math.sin(m[i]);
            Log.i("Message info "+i,m[i]+"");
        }
        graph.removeAllSeries();
        for(int i=0;i<n;i++)
        {
            LineGraphSeries<DataPoint> series;
            series= new LineGraphSeries<>(Mdata());
            graph.addSeries(series);

        }
    }

    void genC()
    {
        getcF();
        for(int i=0;i<n;i++)
        {
            c[i]=2*3.14*f2*t[i];
            c[i]= Math.sin(c[i]);
            Log.i("Carrier info "+i,m[i]+"");
        }
        Cgraph.removeAllSeries();
        for(int i=0;i<n;i++){
            LineGraphSeries<DataPoint> series;
            series= new LineGraphSeries<>(Cdata());
            Cgraph.addSeries(series);

        }
    }
}