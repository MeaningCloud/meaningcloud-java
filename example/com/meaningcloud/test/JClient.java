package com.meaningcloud.test;

import com.meaningcloud.*;

import java.io.IOException;
import java.util.List;

import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

public class JClient {
    private static String MEANINGCLOUD_KEY;

    static {
        MEANINGCLOUD_KEY = (String)System.getProperties().get("MEANINGCLOUD_KEY");
        //MEANINGCLOUD_KEY = "...";

        if (MEANINGCLOUD_KEY == null || MEANINGCLOUD_KEY.length() == 0) {
            throw new RuntimeException("Please define the MEANINGCLOUD_KEY in your test (-DMEANINGCLOUD_KEY = ...)");
        }
    }

    public static String exampleLangRequest (String txt) throws IOException {
        LangResponse r = LangRequest
                .build(MEANINGCLOUD_KEY)
                .withText(txt)
                .send();

        String language = r.getLanguageList().get(0).getLanguage();
        String nameLang = r.getLanguageList().get(0).getName();

        System.out.println("Language detected: " + language);
        System.out.println("Name of the language: " + nameLang);

        return language;
    }

    public static void exampleTopicsRequest (String txt, Request.Language lang) throws IOException, Request.ParameterValidationException {
        TopicsResponse r = TopicsRequest
                .build(MEANINGCLOUD_KEY, lang)
                .withText(txt)
                .send();

        try{
            String concept = r.getConceptList().get(0).getForm();
            System.out.println("Concept detected: " + concept);

            String entity = r.getEntityList().get(0).getForm();
            String entity1 = r.getEntityList().get(1).getForm();
            System.out.println("Entity detected: " + entity);
            System.out.println("Entity detected: " + entity1);
        } catch (IndexOutOfBoundsException message) {
            System.out.println("The entity/concept does not exist");
        }
    }

    public static void exampleParserRequest (String txt, Request.Language lang) throws IOException, Request.ParameterValidationException {
        ParserResponse r = ParserRequest
                .build(MEANINGCLOUD_KEY, lang)
                .withText(txt)
                .send();

        List<ParserResponse.Lemma> lemmas = r.lemmatize();
        for (ParserResponse.Lemma lemma : lemmas) {
            System.out.println("Lemma:  " + lemma.getLemma());
            System.out.println("Tag:  " + lemma.getTag());
        }
    }

    public static void main(String[] args) throws IOException, Request.ParameterValidationException {
        String txt = "London is a very nice city but I also love Madrid.";
        String lang = exampleLangRequest(txt);

        if (lang.matches("auto")  || lang.matches("en") || lang.matches("es") || lang.matches("fr") || lang.matches("it") || lang.matches("pt") || lang.matches("ca")){
            Request.Language language = Request.Language.valueOf(toUpperCase(lang));

            exampleTopicsRequest(txt, language);
            exampleParserRequest(txt, language);

        } else {
            System.out.println("The language is not valid");
        }

    }
}
