package com.mksoft.testrecfbapp.DI;


import com.mksoft.testrecfbapp.component.activity.fragment.FunctionAddPage.MakeFunctionFragment;
import com.mksoft.testrecfbapp.component.activity.fragment.FunctionAddPage.TitleAndHashTagOfFunctionFragment;
import com.mksoft.testrecfbapp.component.activity.LoginActivity.LoginPageFragment;
import com.mksoft.testrecfbapp.component.activity.fragment.ViewAllFunction.ViewAllFunctionFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Philippe on 02/03/2018.
 */

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract ViewAllFunctionFragment contributeViewAllFunctionFragment();

    @ContributesAndroidInjector
    abstract LoginPageFragment contributeLoginPageFragment();

    @ContributesAndroidInjector
    abstract TitleAndHashTagOfFunctionFragment contributeTAHTFunctionFragmentFragment();

    @ContributesAndroidInjector
    abstract MakeFunctionFragment contributeMakeFunctionFragment();


}
