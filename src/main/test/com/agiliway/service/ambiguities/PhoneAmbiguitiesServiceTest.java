package com.agiliway.service.ambiguities;

import com.agiliway.service.impl.ambiguities.PhoneAmbiguitiesService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class PhoneAmbiguitiesServiceTest {

    private PhoneAmbiguitiesService phoneAmbiguitiesService = new PhoneAmbiguitiesService();

    @Test
    public void testDividedBy10Amb() {
        String phone = "2 10 6 9 30 6 6 4";
        Set<String> phones = phoneAmbiguitiesService.processAmbiguities(phone);

        Assert.assertNotNull(phones);
        Assert.assertEquals(phones.size(), 2);
        Assert.assertTrue(phones.contains("2106930664"));
        Assert.assertTrue(phones.contains("210693664"));
    }

    @Test
    public void testBothAmb() {
        String phone = "2 10 69 30 6 6 4";
        Set<String> phones = phoneAmbiguitiesService.processAmbiguities(phone);

        Assert.assertNotNull(phones);
        Assert.assertEquals(phones.size(), 4);
        Assert.assertTrue(phones.contains("2106930664"));
        Assert.assertTrue(phones.contains("210693664"));
        Assert.assertTrue(phones.contains("2106093664"));
        Assert.assertTrue(phones.contains("21060930664"));
    }
}
