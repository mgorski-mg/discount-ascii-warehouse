package com.mgorski.discountasciiwarehouse.di.component;

import com.mgorski.discountasciiwarehouse.AsciiWarehouseServiceIntegrationTest;
import com.mgorski.discountasciiwarehouse.di.module.TestModule;
import com.mgorski.discountasciiwarehouse.network.RetrofitModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RetrofitModule.class, TestModule.class})
public interface TestComponent {
    void inject(AsciiWarehouseServiceIntegrationTest asciiWarehouseServiceIntegrationTest);
}