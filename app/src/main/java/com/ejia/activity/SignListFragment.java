package com.ejia.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ejia.adapter.SignListAdapter;
import com.ejia.entity.EJAMessage;
import com.ejia.entity.Sign;
import com.ejia.presenter.ISignPresenter;
import com.ejia.presenter.SignPresenter;
import com.ejia.view.ISignView;
import com.example.yangzhongyu.myapplication.R;
import com.rupeng.view.utility.SharePreferenceUtil;

import java.util.List;

/**查询签单列表
 * Created by yangzhongyu on 2017/1/31.
 */
public class SignListFragment extends Fragment implements ISignView{

    private ISignPresenter mSignPresenter;

    private ListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.frament_sign_list,null,false);

        mListView = (ListView) view.findViewById(R.id.list_view_sign);

        mSignPresenter = new SignPresenter(this);
        mSignPresenter.querySignListByPhone(SharePreferenceUtil.getUserPhone(getContext()));
        return  view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mSignPresenter.querySignListByPhone(SharePreferenceUtil.getUserPhone(getContext()));
    }

    @Override
    public void onQuerySignList(List<Sign> signList) {
          mListView.setAdapter(new SignListAdapter(getContext(),signList));
    }

    @Override
    public void onQueryRecommendSignList(List<Sign> signs) {

    }

    @Override
    public void onApplySign(EJAMessage message) {

    }
}
