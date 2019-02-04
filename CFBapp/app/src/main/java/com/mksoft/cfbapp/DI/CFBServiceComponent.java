package com.mksoft.cfbapp.DI;



import com.mksoft.cfbapp.Repository.API.CFBService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = CFBServiceModule.class)
public interface CFBServiceComponent {
    CFBService makeCFBService();
}
