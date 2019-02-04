package com.mksoft.loginproject.DI;

import com.mksoft.loginproject.Repository.LoginService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = LoginServiceModule.class)

public interface LoginServiceComponent {
    LoginService makeLoginService();
}
