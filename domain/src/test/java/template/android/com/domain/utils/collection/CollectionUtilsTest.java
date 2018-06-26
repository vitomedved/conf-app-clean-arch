package template.android.com.domain.utils.collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public final class CollectionUtilsTest {

    private CollectionUtils collectionUtils;

    @Before
    public void setUp() throws Exception {
        collectionUtils = new CollectionUtilsImpl();
    }

    @Test
    public void testShouldReturnFalseWhenNullListIsPassed() {
        Assert.assertTrue(collectionUtils.isEmpty(null));
    }

    @Test
    public void testShouldReturnTrueWhenListIsEmpty() {
        Assert.assertTrue(collectionUtils.isEmpty(new ArrayList<>()));
    }

    @Test
    public void testShouldReturnFalseWhenListIsNotEmpty() {
        Assert.assertFalse(collectionUtils.isEmpty(Arrays.asList(new Object(), new Object())));
    }
}
