package com.meaningcloud.test;

import com.meaningcloud.LangRequest;
import com.meaningcloud.LangResponse;
import com.meaningcloud.test.junit.Throttle;
import com.meaningcloud.test.junit.Throttling;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Throttling.class)
public class LangTest extends TestSuper {


    /***** test Basic Lang *****/


    @Test
    public void testBasicLang() {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\"},\"language_list\":[{\"language\":\"es\",\"relevance\":100,\"name\":\"Spanish\",\"iso639-3\":\"spa\",\"iso639-2\":\"es\"}]}";

        LangResponse r = LangResponse.from(response);

        assertEquals("OK", r.status.msg);
        assertEquals(1, r.getLanguageList().size());
        assertEquals("es", r.getLanguageList().get(0).getLanguage());
        assertEquals(100, r.getLanguageList().get(0).getRelevance());
        assertEquals("Spanish", r.getLanguageList().get(0).getName());
        assertEquals("spa", r.getLanguageList().get(0).getIso3());
        assertEquals("es", r.getLanguageList().get(0).getIso2());
    }


    @Test
    public void testBasicLangVerbose() {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\"},\"language_list\":[{\"language\":\"es\",\"relevance\":100,\"name\":\"Spanish\",\"iso639-3\":\"spa\",\"iso639-2\":\"es\",\"script\":\"Latin\",\"speakers\":\"332000000\"}]}";

        LangResponse r = LangResponse.from(response);

        assertEquals("OK", r.status.msg);
        assertEquals("es", r.getLanguageList().get(0).getLanguage());
        assertEquals("Spanish", r.getLanguageList().get(0).getName());
        assertEquals("Latin", r.getLanguageList().get(0).getScript());
        assertEquals(332000000, r.getLanguageList().get(0).getSpeakers());
        assertEquals("spa", r.getLanguageList().get(0).getIso3());
    }


    @Test
    public void testBasicMultiLang() {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\"},\"language_list\":[{\"language\":\"en\",\"relevance\":100,\"name\":\"English\",\"iso639-3\":\"eng\",\"iso639-2\":\"en\",\"script\":\"Latin\",\"speakers\":\"322000000\"},]}";

        LangResponse r = LangResponse.from(response);

        assertEquals("OK", r.status.msg);
        assertEquals("English", r.getLanguageList().get(0).getName());
        assertEquals("Latin", r.getLanguageList().get(0).getScript());
        assertEquals(322000000, r.getLanguageList().get(0).getSpeakers());
    }


    /***** test Lang Request *****/


    @Test @Throttle
    public void testLangRequest() throws IOException {
        LangResponse r = LangRequest
                .build(MEANINGCLOUD_KEY)
                .withText("Sarah Sanders (Aidy Bryant) is here, and she’s explaining the border wall: “It will be paid for by Mexico, with US taxpayer money”. Then she introduces Dr Ronny Jackson (Beck Bennett), to explain “just how not fat the president is”.")
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(1, r.getLanguageList().size());
        assertEquals("en", r.getLanguageList().get(0).getLanguage());
        assertEquals(100, r.getLanguageList().get(0).getRelevance());
        assertEquals("English", r.getLanguageList().get(0).getName());
        assertEquals("eng", r.getLanguageList().get(0).getIso3());
        assertEquals("en", r.getLanguageList().get(0).getIso2());
    }


    @Test @Throttle
    public void testLangRequestVerbose() throws IOException {
        LangResponse r = LangRequest
                .build(MEANINGCLOUD_KEY)
                .withText("Sarah Sanders (Aidy Bryant) is here, and she’s explaining the border wall: “It will be paid for by Mexico, with US taxpayer money”. Then she introduces Dr Ronny Jackson (Beck Bennett), to explain “just how not fat the president is”.")
                .withVerbose(true)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals("Latin", r.getLanguageList().get(0).getScript());
        assertEquals(322000000, r.getLanguageList().get(0).getSpeakers());
    }


    @Test @Throttle
    public void testLangRequestSelection() throws IOException {
        LangResponse r = LangRequest
                .build(MEANINGCLOUD_KEY)
                .withText("La multinacional española de la robótica GMV está trabajando en el desarrollo de un acelerador de partículas. El proyecto, dentro del programa ACTECA (Aceleradores y Tecnologías Asociadas para Grandes Instalaciones Científicas) junto con otras empresas españolas, tiene como objetivo la producción de flujos de neutrones de características similares a los que se prevén encontrar en una planta de fusión nuclear para el ensayo y cualificación de los materiales que se usarán en dichas instalaciones.\n" +
                        "\n" +
                        "While women and men are separated by the ultra-Orthodox Jewish authority that runs the plaza, both sexes are normally able to look into each other’s section. During previous visits by Donald Trump and Barack Obama, sexes were divided although female reporters and photographers were afforded an unobstructed view.")
                .withVerbose(true)
                .withSelection("es|en")
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(1, r.getLanguageList().size());
        assertEquals("es", r.getLanguageList().get(0).getLanguage());
        assertEquals("Latin", r.getLanguageList().get(0).getScript());
    }


    @Test @Throttle
    public void testLangRequestWrongSelection() throws IOException {
        LangResponse r = LangRequest
                .build(MEANINGCLOUD_KEY)
                .withText("La multinacional española de la robótica GMV está trabajando en el desarrollo de un acelerador de partículas. El proyecto, dentro del programa ACTECA (Aceleradores y Tecnologías Asociadas para Grandes Instalaciones Científicas) junto con otras empresas españolas, tiene como objetivo la producción de flujos de neutrones de características similares a los que se prevén encontrar en una planta de fusión nuclear para el ensayo y cualificación de los materiales que se usarán en dichas instalaciones.\n" +
                        "\n" +
                        "While women and men are separated by the ultra-Orthodox Jewish authority that runs the plaza, both sexes are normally able to look into each other’s section. During previous visits by Donald Trump and Barack Obama, sexes were divided although female reporters and photographers were afforded an unobstructed view.")
                .withVerbose(true)
                .withSelection("es|invented")
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(1, r.getLanguageList().size());
        assertEquals("es", r.getLanguageList().get(0).getLanguage());
    }



    @Test @Throttle
    public void testLangRequestFile() throws IOException {
        File file = createTemporaryFileWithContents("This is a sentence in English.");

        LangResponse r = LangRequest
                .build(MEANINGCLOUD_KEY)
                .withFile(file)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(1, r.getLanguageList().size());
        assertEquals("en", r.getLanguageList().get(0).getLanguage());
    }


    @Test @Throttle
    public void testLangRequestVerboseFile() throws IOException {
        File f = createTemporaryFileWithContents("This is a sentence in English.");

        LangResponse r = LangRequest
                .build(MEANINGCLOUD_KEY)
                .withFile(f)
                .withVerbose(true)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(1, r.getLanguageList().size());
        assertEquals("en", r.getLanguageList().get(0).getLanguage());
        assertEquals(322000000, r.getLanguageList().get(0).getSpeakers());
    }


    @Test @Throttle
    public void testLangRequestURL() throws IOException {
        LangResponse r = LangRequest
                .build(MEANINGCLOUD_KEY)
                .withURL(new URL("https://www.google.com"))
                .send();

        assertEquals("OK", r.status.msg);
        assertNotNull(r.getLanguageList().get(0).getLanguage());
    }
}
