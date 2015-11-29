package com.dnd.diarynoteday.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.x;


public abstract class BaseFragment extends Fragment {

    public View view;
    public Context context;

    private boolean injected = false;

    /**
     * 初始化UI
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        injected = true;
        view = initUI();

        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!injected) {
            x.view().inject(this, this.getView());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        context = getActivity();
        super.onCreate(savedInstanceState);
    }


    /**
     * 填充数据
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        initData();
        super.onActivityCreated(savedInstanceState);

    }

    public void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
    }


    /**
     * 初始化界面
     *
     * @return
     */
    public abstract View initUI();

    /**
     * 填充数据
     */
    public abstract void initData();

}
