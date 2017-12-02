package org.vlis.bloomfilter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import junit.framework.Assert;

import org.vlis.dog.bloomfilter.BloomFilter;
import org.vlis.dog.bloomfilter.impl.InMemoryBloomFilter;


/**
 * @author thinking_fioa
 * @createTime 2017/12/2
 * @description
 */


public class TestBloomFilter {

    private static final int MAX = 1000 * 1000;
    private static final double FPP = 0.01;

    public static void main(String [] args) {
        testDefaultFilter();
    }

    private static void testDefaultFilter() {
        BloomFilter<String> filter = new InMemoryBloomFilter<String>(10 * MAX, FPP);

        // 随机生成生成100万个数据，分别放在数组contained，unused
        List<String> contained = new ArrayList<String>();
        List<String> unused = new ArrayList<String>();
        for(int index = 0; index < MAX; index++) {
            contained.add(UUID.randomUUID().toString());
            unused.add(UUID.randomUUID().toString());
        }

        // 将包含的数据加到布隆过滤器中
        for(String uuid : contained) {
            filter.add(uuid);
        }

        // 核查成功率
        for(String uuid : contained) {
            Assert.assertTrue(filter.contains(uuid));
        }
        int fpp = 0;
        for(String uuid : unused) {
            boolean present = filter.contains(uuid);
            if(present) {
                // false positive
                Assert.assertEquals(false, contained.contains(uuid));
                fpp++;
            }
        }

        // 下面的代码再进行了一次。

        // 再随机生成100万个数据
        List<String> more = new ArrayList<String>();
        for(int index = 0; index < MAX; index++) {
            more.add(UUID.randomUUID().toString());
        }
        for(String uuid : more) {
            filter.add(uuid);
        }

        // check again
        for(String uuid : more) {
            Assert.assertTrue(filter.contains(uuid));
        }
        for(int index = 0; index < MAX; index++) {
            String uuid = UUID.randomUUID().toString();
            boolean present = filter.contains(uuid);
            if(present) {
                // false positive
                Assert.assertEquals(false, contained.contains(uuid));
                fpp++;
            }
        }
        System.out.println("False positives found in two millions: " + fpp);
    }
}
