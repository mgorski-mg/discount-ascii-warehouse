package com.mgorski.discountasciiwarehouse.di;

import com.mgorski.discountasciiwarehouse.di.component.DaggerTestComponent;
import com.mgorski.discountasciiwarehouse.network.AsciiWarehouseService;
import com.mgorski.discountasciiwarehouse.network.model.WebAsciiItem;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import javax.inject.Inject;

import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

public class AsciiWarehouseServiceIntegrationTest {

    @Inject
    AsciiWarehouseService service;

    @Before
    public void setUp() {
        DaggerTestComponent.create().inject(this);
    }

    @Test
    public void shouldDownloadAsciiItemsWithNoError() {
        // Given
        TestSubscriber<List<WebAsciiItem>> testSubscriber = new TestSubscriber<>();

        // When
        service.getAsciiItems(null, null, null, null).subscribeOn(Schedulers.io())
                .subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();
    }

    @Test
    public void shouldDownloadOneAsciiItem() {
        // Given
        TestSubscriber<List<WebAsciiItem>> testSubscriber = new TestSubscriber<>();

        // When
        service.getAsciiItems(1, null, null, null).subscribeOn(Schedulers.io())
                .subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();

        // Then
        testSubscriber.assertNoErrors();
        List<WebAsciiItem> webAsciiItems = testSubscriber.getOnNextEvents().get(0);

        MatcherAssert.assertThat(webAsciiItems,
                Matchers.hasSize(1));
    }

    @Test
    public void shouldDownloadOnlyInStockAsciiItems() {
        // Given
        TestSubscriber<List<WebAsciiItem>> testSubscriber = new TestSubscriber<>();

        // When
        service.getAsciiItems(null, null, null, 1).subscribeOn(Schedulers.io())
                .subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();

        // Then
        testSubscriber.assertNoErrors();
        List<WebAsciiItem> webAsciiItems = testSubscriber.getOnNextEvents().get(0);

        MatcherAssert.assertThat(webAsciiItems,
                Matchers.everyItem(
                        Matchers.<WebAsciiItem>hasProperty("stock",
                                Matchers.greaterThan(0))));
    }

    @Test
    public void shouldDownloadOnlyAsciiItemsWithTagHappy() {
        // Given
        TestSubscriber<List<WebAsciiItem>> testSubscriber = new TestSubscriber<>();

        // When
        service.getAsciiItems(null, null, "happy", null).subscribeOn(Schedulers.io())
                .subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();

        // Then
        testSubscriber.assertNoErrors();
        List<WebAsciiItem> webAsciiItems = testSubscriber.getOnNextEvents().get(0);

        MatcherAssert.assertThat(webAsciiItems,
                Matchers.everyItem(
                        Matchers.<WebAsciiItem>hasProperty("tags",
                                Matchers.hasItem("happy"))));
    }
}