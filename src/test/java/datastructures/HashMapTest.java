package datastructures;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HashMapTest {

    HashMap hashMap;

    @Before
    public void setUp() {
        hashMap = new HashMap(1024);
    }

    @Test
    public void newHashMapIsEmpty() {
        hashMap.put("koodi", "code");
        Assert.assertEquals("code", hashMap.get("koodi"));
    }

    @Test
    public void removeRemovesTheKeyValuePair() {
        hashMap.put("koodi", "code");
        Assert.assertTrue(hashMap.remove("koodi"));
        Assert.assertEquals(null, hashMap.get("koodi"));
    }

    @Test
    public void HashMapWorksWhenSameEntryIsSubmittedTwice() {
        hashMap.put("koodi", "code");
        hashMap.put("booli", "punch");
        hashMap.put("koodi", "code");
        Assert.assertEquals("code", hashMap.get("koodi"));
    }

    @Test
    public void keyCannotBeNull() {
        hashMap.put(null, "null");
        Assert.assertEquals(null, hashMap.get("null"));
    }

    @Test
    public void hashMapReturnsNullIfKeyNotAssociated() {
       Assert.assertEquals(null, hashMap.get("booli"));
    }

    @Test
    public void nullCanBePutAndRemoved() {
        hashMap.put("koodi", null);
        Assert.assertTrue(null, hashMap.remove("koodi"));
    }
}
