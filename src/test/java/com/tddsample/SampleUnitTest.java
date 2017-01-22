package com.tddsample;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by shekerama on 2017-01-21.
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = Config.class)
public class SampleUnitTest {

    @Mock
    private Person person;

    @Before
    public void setUp(){
        when(person.getName()).thenReturn("Mockito");
    }

    @Test
    public void testOnSuccess() {
        assertThat(person.getName()).isNotEqualTo("Seba");
    }

}
