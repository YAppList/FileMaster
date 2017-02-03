package com.ejia.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.ejia.adapter.SignListAdapter;
import com.ejia.entity.EJAMessage;
import com.ejia.entity.Sign;
import com.ejia.presenter.ISignPresenter;
import com.ejia.presenter.SignPresenter;
import com.ejia.view.ISignView;
import com.example.yangzhongyu.myapplication.R;
import com.rupeng.view.utility.SharePreferenceUtil;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by yangzhongyu on 2017/1/22.
 */
public class RecommendSignListFrament extends Fragment implements ISignView {

    private ISignPresenter mSignPresenter;

    private ListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.frament_self,null,false);
        mListView = (ListView) view.findViewById(R.id.list_view_recommend);
        mSignPresenter = new SignPresenter(this);
        return  view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mSignPresenter.queryRecommendSignListByPhone(SharePreferenceUtil.getUserPhone(getContext()));
    }

    @Override
    public void onQuerySignList(List<Sign> signs) {

    }

    @Override
    public void onQueryRecommendSignList(List<Sign> signs) {
          Log.i("yzy","onQueryRecommendSignList"+signs);
        mListView.setAdapter(new SignListAdapter(getContext(),signs));
    }

    @Override
    public void onApplySign(EJAMessage message) {

    }
}
