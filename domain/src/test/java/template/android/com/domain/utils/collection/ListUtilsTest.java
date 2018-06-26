package template.android.com.domain.utils.collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public final class ListUtilsTest {

    @Mock
    private CollectionUtils mockCollectionUtils;

    private ListUtils listUtils;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        listUtils = new ListUtilsImpl(mockCollectionUtils);
    }

    @Test
    public void testShouldInvokeIsEmptyMethodOnCollectionUtils() {
        listUtils.isEmpty(null);

        Mockito.verify(mockCollectionUtils, Mockito.times(1)).isEmpty(null);
    }

    @Test(expected = NullPointerException.class)
    public void testShouldThrowNullPointerExceptionWhenReversingNullList() {
        listUtils.reverse(null);
    }

    @Test
    public void testShouldListRemainSameWhenReversingListWithOneItem() {
        final List<String> list = new ArrayList<>(Collections.singletonList(""));

        Assert.assertEquals(Collections.singletonList(""), listUtils.reverse(list));
    }

    @Test
    public void testShouldRevertListWhenTwoItemListIsPassed() {
        final List<String> list = new ArrayList<>(Arrays.asList("1", "2"));

        Assert.assertEquals(Arrays.asList("2", "1"), listUtils.reverse(list));
    }

    @Test(expected = NullPointerException.class)
    public void testShouldThrowNullPointerExceptionWhenTransformingNullEnumeration() {
        listUtils.toList(null);
    }

    @Test
    public void testShouldSplitEnumerationToList() {
        // This will generate string tokenizer which is splitting strings by whitespaces.
        final StringTokenizer stringTokenizer = new StringTokenizer("test string");

        Assert.assertEquals(Arrays.asList("test", "string"), listUtils.toList(stringTokenizer));
    }
}
