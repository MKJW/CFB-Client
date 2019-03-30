package com.mksoft.testrecfbapp.DI;



import com.mksoft.testrecfbapp.viewmodel.FactoryViewModel;
import com.mksoft.testrecfbapp.viewmodel.FunctionListViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(FunctionListViewModel.class)
    abstract ViewModel bindFunctionListViewModel(FunctionListViewModel functionListViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);
}
