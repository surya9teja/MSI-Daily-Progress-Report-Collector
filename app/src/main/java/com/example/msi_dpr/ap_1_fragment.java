package com.example.msi_dpr;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ap_1_fragment extends Fragment {

    public ap_1_fragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.ap_1_fragment,container,false);
        TextView txt;
        txt=(TextView)view.findViewById(R.id.ap_1_text);
        data_retrive.getdata out =new data_retrive.getdata();
        List<String> d=out.date;
        if(d!=null)
        {
            txt.setText(TextUtils.join("\n",d));
        }
        return view;
    }
}
