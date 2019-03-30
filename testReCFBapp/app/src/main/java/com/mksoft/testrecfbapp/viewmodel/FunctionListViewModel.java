package com.mksoft.testrecfbapp.viewmodel;


import com.mksoft.testrecfbapp.Repository.APIRepo;
import com.mksoft.testrecfbapp.Repository.Data.FunctionArray;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by Philippe on 02/03/2018.
 */

public class FunctionListViewModel extends ViewModel {

    private LiveData<ArrayList<FunctionArray>> functionList;
    private APIRepo APIRepo;

    @Inject
    public FunctionListViewModel(APIRepo APIRepo) {

        this.APIRepo = APIRepo;
    }

    // ----

    public void init(String authorization) {
        if (this.functionList != null) {
            return;
        }
        functionList = APIRepo.getAllFunctionLiveData(authorization);
    }

    public LiveData<ArrayList<FunctionArray>> getFunctionList() {
        return this.functionList;
    }


}
