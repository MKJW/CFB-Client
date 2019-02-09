package com.mksoft.loginproject.DI;

import com.mksoft.loginproject.Repository.OauthServiceClass;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = LoginServiceModule.class)

public interface LoginServiceComponent {
    OauthServiceClass makeLoginService();
}
