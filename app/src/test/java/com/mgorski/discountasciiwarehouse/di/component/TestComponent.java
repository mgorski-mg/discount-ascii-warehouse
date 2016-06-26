package com.mgorski.discountasciiwarehouse.di.component;

import com.mgorski.discountasciiwarehouse.di.AsciiWarehouseServiceIntegrationTest;
import com.mgorski.discountasciiwarehouse.network.RetrofitModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = RetrofitModule.class)
public interface TestComponent {
    void inject(AsciiWarehouseServiceIntegrationTest asciiWarehouseServiceIntegrationTest);
}