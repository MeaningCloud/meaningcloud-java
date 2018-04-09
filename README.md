## MeaningCloud for java

This is MeaningCloud's java client, designed to enable you to use MeaningCloud's
services easily from your own applications.

## Getting started

### Configuration
The only thing you need to start using MeaningCloud's APIs is to log into MeaningCloud
(by registering or using other services to log in). Once you've done that, you will be given a
license key (https://www.meaningcloud.com/developer/account/subscription). Copy it and paste it in
the corresponding place in the code, select the API you want to use and the parameters you want to use,
and that's it.

You can find all the technical documentation about the APIs in the API section of the website:
 https://www.meaningcloud.com/developer/apis

## Building the library

This library uses [Gradle](https://gradle.org) as a build tool. To build the project in Linux/OS X:

```
$ ./gradlew clean build -x test
```

In Windows Command Prompt, `gradlew.bat` must be used instead of `gradlew`.

Files are written in UTF-8. If your terminal uses a different character set, the build tool will complain
about _unmappable characters_. In this case, make sure your terminal uses UTF-8 encoding, or add the
following flag to the build commands:

```
-Dfile.encoding=UTF8
```

To execute all tests, you will need your API key specified:

```
$ ./gradlew clean test -DMEANINGCLOUD_KEY=[your API KEY]
```


### Functionality
This SDK currently contains the following:

- Response: models a generic response from the MeaningCloud API.
  - TopicsResponse: models a response from the Topic Extraction API, providing auxiliary functions to work
with the response, extracting the different types of topics and some of the most used fields in them.
  - ClassResponse: models a response from the Text Classification API, providing auxiliary functions to work
with the response and extract the different fields in each category.
  - SentimentResponse: models a response from the Sentiment Analysis API, providing auxiliary functions to work
with the response and extract the sentiment detected at different levels and for different elements.
  - LangResponse: models a response from the Language Identification API, providing auxiliary functions to work
with the response and extract the language detected.
  - ParserResponse: models a response from the Lemmatization, PoS and Parsing API, providing auxiliary functions to work
with the response and extract the different basic linguistic modules.
  - DeepCatResponse: models a response from the Deep Categorization API, providing auxiliary functions to work
with the response and extract the different categories to a text.

- Request: to easily create a request to any of MeaningCloud's APIS. Providing Requests to work:
  - TopicsRequest
  - ClassRequest
  - SentimentRequest
  - LangRequest
  - ParserRequest
  - DeepCatRequest

### Usage
This is an example on how to use this client (also included in the example folder).
This code makes to requests, once to the Language Identification API, another
one to the Topic Extraction API using the language detected in the first request and another one
to the Lemmatization PoS and Parsing API using the language detected in the first request.
The results of both requests are printed in the standard output:

```java
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
```
