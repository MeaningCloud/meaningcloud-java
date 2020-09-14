## MeaningCloud for java

This is MeaningCloud's java client, designed to enable you to use MeaningCloud's
services easily from your own applications.

## SDK versions

Starting from version 2.0 the classification API `class-1.1` is deprecated. Please refer to the
[class-1.1 to class-2.0 Model Migration Guide](https://www.meaningcloud.com/blog/text-classification-20-migration-guide)
to upgrade your classification models.

|                     | v0.9                   | v2.0                   |
|---------------------|------------------------|------------------------|
| Class               | class-1.1              | **class-2.0**          |
| Deep Categorization | deepcategorization-1.0 | deepcategorization-1.0 |
| Language Detection  | lang-2.0               | lang-2.0               |
| Parser              | parser-2.0             | parser-2.0             |
| Sentiment Analysis  | sentiment-2.1          | sentiment-2.1          |
| Topics Extraction   | topics-2.0             | topics-2.0             |


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

An example is available in the `example` folder. The code makes to requests, one to
the Language Identification API, another one to the Topic Extraction API using the language detected
in the first request, and another one to the Lemmatization PoS and Parsing API using the language
detected in the first request. The results of both requests are printed in the standard output.
