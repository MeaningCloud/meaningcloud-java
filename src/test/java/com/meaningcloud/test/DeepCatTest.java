package com.meaningcloud.test;

import com.meaningcloud.DeepCatRequest;
import com.meaningcloud.DeepCatResponse;
import com.meaningcloud.Request;
import com.meaningcloud.test.junit.Throttle;
import com.meaningcloud.test.junit.Throttling;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(Throttling.class)
public class DeepCatTest extends TestSuper {

    
    /***** test Basic Expressions Topics *****/

    
    @Test
    public void testBasicDeepCatIAB_es() {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\"},\"category_list\":[{\"code\":\"Television\",\"label\":\"Televisión\",\"abs_relevance\":\"2\",\"relevance\":\"100\"},{\"code\":\"PopCulture\",\"label\":\"Cultura popular\",\"abs_relevance\":\"1\",\"relevance\":\"50\"},{\"code\":\"Movies\",\"label\":\"Películas\",\"abs_relevance\":\"1\",\"relevance\":\"50\"}]}";

        DeepCatResponse r = DeepCatResponse.from(response);

        assertEquals("OK", r.status.msg);
        assertEquals(3, r.getCategoryList().size());
        assertEquals("Television", r.getCategoryList().get(0).getCode());
        assertEquals("Televisión", r.getCategoryList().get(0).getLabel());
        assertEquals(2.0,r.getCategoryList().get(0).getAbsRelevance(), 0);
        assertEquals(100, r.getCategoryList().get(0).getRelevance(), 0);
        assertEquals("PopCulture", r.getCategoryList().get(1).getCode());
        assertEquals("Cultura popular", r.getCategoryList().get(1).getLabel());
        assertEquals(1, r.getCategoryList().get(1).getAbsRelevance(), 0);
        assertEquals(50, r.getCategoryList().get(1).getRelevance(), 0);
    }

    
    @Test
    public void testBasicDeepCatPolarityIAB_es() {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\"},\"category_list\":[{\"code\":\"Television\",\"label\":\"Televisión\",\"abs_relevance\":\"2\",\"relevance\":\"100\",\"polarity\":\"P\"},{\"code\":\"PopCulture\",\"label\":\"Cultura popular\",\"abs_relevance\":\"1\",\"relevance\":\"50\",\"polarity\":\"P\"}]}";

        DeepCatResponse r = DeepCatResponse.from(response);

        assertEquals("OK", r.status.msg);
        assertEquals(2, r.getCategoryList().size());
        assertEquals("Television", r.getCategoryList().get(0).getCode());
        assertEquals("Televisión", r.getCategoryList().get(0).getLabel());
        assertEquals(2, r.getCategoryList().get(0).getAbsRelevance(), 0);
        assertEquals(100, r.getCategoryList().get(0).getRelevance(), 0);
        assertEquals("P", r.getCategoryList().get(0).getPolarity());
        assertEquals("PopCulture", r.getCategoryList().get(1).getCode());
        assertEquals("Cultura popular", r.getCategoryList().get(1).getLabel());
        assertEquals(1, r.getCategoryList().get(1).getAbsRelevance(), 0);
        assertEquals(50, r.getCategoryList().get(1).getRelevance(), 0);
        assertEquals("P", r.getCategoryList().get(1).getPolarity());
    }

    
    @Test
    public void testBasicDeepCatVerboseIAB_es() {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\"},\"category_list\":[{\"code\":\"Television\",\"label\":\"Televisión\",\"abs_relevance\":\"2\",\"relevance\":\"100\",\"term_list\":[{\"form\":\"television\",\"abs_relevance\":\"1\",\"offset_list\":[{\"inip\":\"78\",\"endp\":\"87\"}]},{\"form\":\"series\",\"abs_relevance\":\"1\",\"offset_list\":[{\"inip\":\"89\",\"endp\":\"94\"}]}]},{\"code\":\"Arts&Entertainment>Movies\",\"label\":\"Arte y entretenimiento>Cine\",\"abs_relevance\":\"1\",\"relevance\":\"50\",\"term_list\":[{\"form\":\"Star Trek\",\"abs_relevance\":\"1\",\"offset_list\":[{\"inip\":\"0\",\"endp\":\"8\"}]}]}]}";

        DeepCatResponse r = DeepCatResponse.from(response);

        assertEquals("OK", r.status.msg);
        assertEquals(2, r.getCategoryList().size());
        assertEquals("Television", r.getCategoryList().get(0).getCode());
        assertEquals("Televisión", r.getCategoryList().get(0).getLabel());
        assertEquals(2, r.getCategoryList().get(0).getAbsRelevance(), 0);
        assertEquals(100, r.getCategoryList().get(0).getRelevance(), 0);
        assertEquals("television", r.getCategoryList().get(0).getTermList().get(0).getForm());
        assertEquals(1, r.getCategoryList().get(0).getTermList().get(0).getAbsRelevance(), 0);
        assertEquals(78, r.getCategoryList().get(0).getTermList().get(0).getOffsetList().get(0).getInip(), 0);
        assertEquals(87, r.getCategoryList().get(0).getTermList().get(0).getOffsetList().get(0).getEndp(), 0);
    }


    @Test @Throttle
    public void testRequestIAB_en() throws IOException, Request.ParameterValidationException {
        DeepCatResponse r = DeepCatRequest
                .build(MEANINGCLOUD_KEY, "IAB_2.0_en")
                .withText("Star Trek is an American science fiction entertainment franchise based on the television series created by Gene Roddenberry")
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(5, r.getCategoryList().size());
        assertTrue(detectsCode("Television>ScienceFictionTV", r));
        assertTrue(detectsLabel("Television>Science Fiction TV", r));
        assertEquals(1, r.getCategoryList().get(0).getAbsRelevance(), 0);
        assertEquals(100, r.getCategoryList().get(0).getRelevance(), 0);
    }

    
    @Test @Throttle
    public void testRequestIAB_es() throws IOException, Request.ParameterValidationException {
        DeepCatResponse r = DeepCatRequest
                .build(MEANINGCLOUD_KEY, "IAB_2.0_es")
                .withText("Star Trek is an American science fiction entertainment franchise based on the television series created by Gene Roddenberry")
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(3, r.getCategoryList().size());
        assertEquals("Television", r.getCategoryList().get(0).getCode());
        assertEquals("Televisión", r.getCategoryList().get(0).getLabel());
        assertEquals(2, r.getCategoryList().get(0).getAbsRelevance(), 0);
        assertEquals(100, r.getCategoryList().get(0).getRelevance(), 0);
    }

    boolean detectsCode(String code, DeepCatResponse r) {
        for (DeepCatResponse.Category cat : r.getCategoryList()) {
            if (cat.getCode().equals(code)) return true;
        }
        return false;
    }

    boolean detectsLabel(String label, DeepCatResponse r) {
        for (DeepCatResponse.Category cat : r.getCategoryList()) {
            if (cat.getLabel().equals(label)) return true;
        }
        return false;
    }

    
    @Test @Throttle
    public void testVerboseIAB_en() throws IOException, Request.ParameterValidationException {
        DeepCatResponse r = DeepCatRequest
                .build(MEANINGCLOUD_KEY, "IAB_2.0_en")
                .withText("Star Trek is an American science fiction entertainment franchise based on the television series created by Gene Roddenberry")
                .withVerbose(true)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(5, r.getCategoryList().size());
        assertTrue(detectsCode("Television>ScienceFictionTV", r));
        assertTrue(detectsLabel("Television>Science Fiction TV", r));
        assertEquals(1, r.getCategoryList().get(0).getAbsRelevance(), 0);
        assertEquals(100, r.getCategoryList().get(0).getRelevance(), 0);
        assertEquals(1, r.getCategoryList().get(0).getTermList().get(0).getAbsRelevance(), 0);
    }

    
    @Test @Throttle
    public void testRequestPolarityIAB_en() throws IOException, Request.ParameterValidationException {
        DeepCatResponse r = DeepCatRequest
                .build(MEANINGCLOUD_KEY, "IAB_2.0_en")
                .withText("Star Trek is an American science fiction entertainment franchise based on the television series created by Gene Roddenberry")
                .withPolarity(true)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(5, r.getCategoryList().size());
        assertTrue(detectsCode("Television>ScienceFictionTV", r));
        assertTrue(detectsLabel("Television>Science Fiction TV", r));
        assertEquals(1, r.getCategoryList().get(0).getAbsRelevance(), 0);
        assertEquals(100, r.getCategoryList().get(0).getRelevance(), 0);
        assertEquals("P", r.getCategoryList().get(0).getPolarity());
    }


    @Test @Throttle
    public void testRequestWithFile() throws IOException, Request.ParameterValidationException {
        File file = createTemporaryFileWithContents("Star Trek is an American science fiction entertainment franchise based on the television series created by Gene Roddenberry");

        DeepCatResponse r = DeepCatRequest
                .build(MEANINGCLOUD_KEY, "IAB_2.0_en")
                .withFile(file)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(5, r.getCategoryList().size());
        assertTrue(detectsCode("Television>ScienceFictionTV", r));
        assertTrue(detectsLabel("Television>Science Fiction TV", r));
        assertEquals(1, r.getCategoryList().get(0).getAbsRelevance(), 0);
        assertEquals(100, r.getCategoryList().get(0).getRelevance(), 0);
    }

    
    @Test @Throttle
    public void testsRequestWithURL() throws IOException, Request.ParameterValidationException {
        DeepCatResponse r = DeepCatRequest
                .build(MEANINGCLOUD_KEY, "IAB_2.0_en")
                .withURL(new URL("https://www.nytimes.com/2018/01/25/science/plastic-coral-reefs.html?partner=rss&emc=rss"))
                .send();

        assertEquals("OK", r.status.msg);
        assertNotNull(r.getCategoryList().get(0).getCode());
    }
}
