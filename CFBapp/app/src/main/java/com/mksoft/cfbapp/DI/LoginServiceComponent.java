package com.mksoft.cfbapp.DI;

import com.mksoft.cfbapp.Repository.OauthServiceClass;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = LoginServiceModule.class)

public interface LoginServiceComponent {
    OauthServiceClass makeLoginService();
}
