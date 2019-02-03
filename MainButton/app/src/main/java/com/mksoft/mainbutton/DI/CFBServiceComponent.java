package com.mksoft.mainbutton.DI;


import com.mksoft.mainbutton.Repository.CFBService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = CFBServiceModule.class)
public interface CFBServiceComponent {
    CFBService makeCFBService();
}
