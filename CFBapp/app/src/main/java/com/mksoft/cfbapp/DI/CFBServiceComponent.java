package com.mksoft.cfbapp.DI;



import com.mksoft.cfbapp.Repository.API.CFBServiceClass;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = CFBServiceModule.class)
public interface CFBServiceComponent {
    CFBServiceClass makeCFBService();
}
