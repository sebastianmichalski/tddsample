package com.tddsample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by shekerama on 2017-01-21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class SampleIntegrationTest {

    @Autowired
    private Person person;

    @Test
    public void testOnSuccess() {
        assertThat(person.getName()).isEqualTo("Seba");
    }

    @Test(expected = AssertionError.class)
    public void testOnFail() {
        assertThat(person.getName()).isEqualTo("John");
    }
}
