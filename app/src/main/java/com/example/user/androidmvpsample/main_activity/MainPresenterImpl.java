package com.example.user.androidmvpsample.main_activity;


import com.example.user.androidmvpsample.model.Notice;

import java.util.ArrayList;

/**
 * Created by bpn on 12/7/17.
 */

public class MainPresenterImpl implements MainContract.presenter, MainContract.GetNoticeIntractor.OnFinishedListener {

    private MainContract.MainView mainView;
    private MainContract.GetNoticeIntractor noticeIntractor;

    public MainPresenterImpl(MainContract.MainView mainView, MainContract.GetNoticeIntractor noticeIntractor) {
        this.mainView = mainView;
        this.noticeIntractor = noticeIntractor;
    }

    @Override
    public void onDestroy() {

        mainView = null;

    }

    @Override
    public void onRefreshButtonClick() {

        if(mainView != null){
            mainView.showProgress();
        }
        noticeIntractor.getNoticeArrayList(this);

    }

    @Override
    public void requestDataFromServer() {
        noticeIntractor.getNoticeArrayList(this);
    }


    @Override
    public void onFinished(ArrayList<Notice> noticeArrayList) {
        if(mainView != null){
            mainView.setDataToRecyclerView(noticeArrayList);
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(mainView != null){
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
    }
}
