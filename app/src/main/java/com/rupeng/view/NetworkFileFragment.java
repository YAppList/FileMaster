package com.rupeng.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.yangzhongyu.myapplication.R;
import com.rupeng.view.constants.Constants;
import com.rupeng.view.service.HTTPService;
import com.rupeng.view.utility.Utility;


public class NetworkFileFragment extends Fragment {
    private static  boolean isStart = false;
    static NetworkFileFragment newInstance(String s){
        NetworkFileFragment f = new NetworkFileFragment();
        Bundle bundle = new Bundle();
        bundle.putString("args",s);
        f.setArguments(bundle);
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.server_mode,container,false);

        final Button btnStart = (Button) view.findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(!isStart){
                    Intent intent = new Intent(getActivity(), HTTPService.class);
                    getActivity().startService(intent);
                    isStart = true;
                    btnStart.setText("关闭远程文件访问");
                }else{
                    Intent intent = new Intent(getActivity(), HTTPService.class);
                    getActivity().stopService(intent);
                    isStart = true;
                    btnStart.setText("开启远程文件访问");
                }
            }
        });
        TextView tvTip = (TextView) view.findViewById(R.id.tv_ip);
        tvTip.setText(Utility.getIp(getContext()) +":"+ Constants.DEFAULT_SERVER_PORT );
        return  view;
    }
}