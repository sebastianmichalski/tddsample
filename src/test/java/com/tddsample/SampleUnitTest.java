package com.tddsample;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.jayway.awaitility.Awaitility.await;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by shekerama on 2017-01-21.
 */
@RunWith(MockitoJUnitRunner.class)
public class SampleUnitTest {

    @Mock
    private Person person;

    @Before
    public void setUp() {
        when(person.getName()).thenReturn("Mockito");
    }

    @Test
    public void testOnSuccess() {
        assertThat(person.getName()).isNotEqualTo("Seba");
    }

    @Test
    public void testScheduledExecutorService() throws InterruptedException {
        AtomicInteger i = new AtomicInteger(0);
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            i.incrementAndGet();
            if (i.get() == 10) {
                executorService.shutdown();
            }
        }, 0, 1, TimeUnit.SECONDS);

        await().atMost(10, TimeUnit.SECONDS).until(() -> i.get() == 10);
        assertTrue(executorService.isShutdown());
    }

    @Test
    public void testSingleThreadExecutor() throws InterruptedException {
        AtomicInteger i = new AtomicInteger(0);
        Future future = Executors.newSingleThreadExecutor().submit(() -> {
            while (i.incrementAndGet() < 10) ;
        });

        await().atMost(1, TimeUnit.SECONDS).until(() -> i.get() == 10);
        assertTrue(future.isDone());
    }


}
