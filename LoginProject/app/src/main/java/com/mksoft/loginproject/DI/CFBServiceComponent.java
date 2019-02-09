package com.mksoft.loginproject.DI;




import com.mksoft.loginproject.Repository.CFBServiceClass;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = CFBServiceModule.class)
public interface CFBServiceComponent {
    CFBServiceClass makeCFBService();
}
