package com.meaningcloud.test;

import com.meaningcloud.*;
import com.meaningcloud.test.junit.Throttle;
import com.meaningcloud.test.junit.Throttling;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Throttling.class)
public class SentimentTest extends TestSuper {


    /***** test Basic Sentiment *****/


    @Test
    public void testBasicSentiment() {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\"},\"model\":\"general_es\",\"score_tag\":\"NONE\",\"agreement\":\"AGREEMENT\",\"subjectivity\":\"OBJECTIVE\",\"confidence\":\"100\",\"irony\":\"NONIRONIC\",\"sentence_list\":[{\"text\":\"test\",\"inip\":\"0\",\"endp\":\"3\",\"bop\":\"y\",\"confidence\":\"100\",\"score_tag\":\"NONE\",\"agreement\":\"AGREEMENT\",\"segment_list\":[{\"text\":\"test\",\"segment_type\":\"secondary\",\"inip\":\"0\",\"endp\":\"3\",\"confidence\":\"100\",\"score_tag\":\"NONE\",\"agreement\":\"AGREEMENT\",\"polarity_term_list\":[],\"sentimented_concept_list\":[{\"form\":\"examen\",\"id\":\"d01b3dc623\",\"variant\":\"test\",\"inip\":\"0\",\"endp\":\"3\",\"type\":\"Top>Event\",\"score_tag\":\"NONE\"}]}],\"sentimented_entity_list\":[],\"sentimented_concept_list\":[{\"form\":\"examen\",\"id\":\"d01b3dc623\",\"type\":\"Top>Event\",\"score_tag\":\"NONE\"}]}],\"sentimented_entity_list\":[],\"sentimented_concept_list\":[{\"form\":\"examen\",\"id\":\"d01b3dc623\",\"type\":\"Top>Event\",\"score_tag\":\"NONE\"}]}";

        SentimentResponse r = SentimentResponse.from(response);

        assertEquals("OK", r.status.msg);
        assertEquals("test", r.getSentenceList().get(0).getText());
        assertEquals(0, r.getSentenceList().get(0).getInip());
        assertEquals(3, r.getSentenceList().get(0).getEndp());
        assertEquals("y", r.getSentenceList().get(0).getBop());
        assertEquals(100, r.getSentenceList().get(0).getConfidence());
        assertEquals("NONE", r.getSentenceList().get(0).getScoreTag());
        assertEquals("AGREEMENT", r.getSentenceList().get(0).getAgreement());
        assertEquals("test", r.getSentenceList().get(0).getSegmentList().get(0).getText());
        assertEquals("secondary", r.getSentenceList().get(0).getSegmentList().get(0).getSegmentType());
        assertEquals(0, r.getSentenceList().get(0).getSegmentList().get(0).getInip());
        assertEquals(3, r.getSentenceList().get(0).getSegmentList().get(0).getEndp());
        assertEquals(100, r.getSentenceList().get(0).getSegmentList().get(0).getConfidence());
        assertEquals("NONE", r.getSentenceList().get(0).getSegmentList().get(0).getScoreTag());
        assertEquals("AGREEMENT", r.getSentenceList().get(0).getSegmentList().get(0).getAgreement());
        assertEquals("examen", r.getSentenceList().get(0).getSegmentList().get(0).getSentimentedConceptList().get(0).getForm());
        assertEquals("d01b3dc623", r.getSentenceList().get(0).getSegmentList().get(0).getSentimentedConceptList().get(0).getId());
        assertEquals("test", r.getSentenceList().get(0).getSegmentList().get(0).getSentimentedConceptList().get(0).getVariant());
        assertEquals(0, r.getSentenceList().get(0).getSegmentList().get(0).getSentimentedConceptList().get(0).getInip());
        assertEquals(3, r.getSentenceList().get(0).getSegmentList().get(0).getSentimentedConceptList().get(0).getEndp());
        assertEquals("Top>Event", r.getSentenceList().get(0).getSegmentList().get(0).getSentimentedConceptList().get(0).getType());
        assertEquals("NONE", r.getSentenceList().get(0).getSegmentList().get(0).getSentimentedConceptList().get(0).getScoreTag());
    }


    @Test
    public void testBasicSentimentPolarity() {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\"},\"model\":\"general_en\",\"score_tag\":\"P\",\"agreement\":\"AGREEMENT\",\"subjectivity\":\"OBJECTIVE\",\"confidence\":\"100\",\"irony\":\"NONIRONIC\",\"sentence_list\":[{\"text\":\"Star Trek is an American science fiction entertainment\",\"inip\":\"0\",\"endp\":\"53\",\"bop\":\"y\",\"confidence\":\"100\",\"score_tag\":\"P\",\"agreement\":\"AGREEMENT\",\"segment_list\":[{\"text\":\"Star Trek is an American science fiction entertainment\",\"segment_type\":\"main\",\"inip\":\"0\",\"endp\":\"53\",\"confidence\":\"100\",\"score_tag\":\"P\",\"agreement\":\"AGREEMENT\",\"polarity_term_list\":[{\"text\":\"entertainment\",\"inip\":\"41\",\"endp\":\"53\",\"confidence\":\"100\",\"score_tag\":\"P\",\"sentimented_entity_list\":[{\"form\":\"Star Trek\",\"id\":\"d3560f7525\",\"variant\":\"Star Trek\",\"inip\":\"0\",\"endp\":\"8\",\"type\":\"Top>Product>CulturalProduct>Movie\",\"score_tag\":\"P\"}],\"sentimented_concept_list\":[{\"form\":\"American\",\"id\":\"0863fc32fb\",\"variant\":\"American\",\"inip\":\"16\",\"endp\":\"23\",\"type\":\"Top\",\"score_tag\":\"P\"},{\"form\":\"American\",\"id\":\"593cbceb1f\",\"variant\":\"American\",\"inip\":\"16\",\"endp\":\"23\",\"type\":\"Top\",\"score_tag\":\"P\"},{\"form\":\"science fiction\",\"id\":\"f11d891073\",\"variant\":\"science fiction\",\"inip\":\"25\",\"endp\":\"39\",\"type\":\"Top\",\"score_tag\":\"P\"},{\"form\":\"entertainment\",\"id\":\"d80a2f37ad\",\"variant\":\"entertainment\",\"inip\":\"41\",\"endp\":\"53\",\"type\":\"Top\",\"score_tag\":\"P\"}]}]}],\"sentimented_entity_list\":[{\"form\":\"Star Trek\",\"id\":\"d3560f7525\",\"type\":\"Top>Product>CulturalProduct>Movie\",\"score_tag\":\"P\"}],\"sentimented_concept_list\":[{\"form\":\"American\",\"id\":\"0863fc32fb\",\"type\":\"Top\",\"score_tag\":\"P\"},{\"form\":\"American\",\"id\":\"593cbceb1f\",\"type\":\"Top\",\"score_tag\":\"P\"},{\"form\":\"entertainment\",\"id\":\"d80a2f37ad\",\"type\":\"Top\",\"score_tag\":\"P\"},{\"form\":\"science fiction\",\"id\":\"f11d891073\",\"type\":\"Top\",\"score_tag\":\"P\"}]}],\"sentimented_entity_list\":[{\"form\":\"Star Trek\",\"id\":\"d3560f7525\",\"type\":\"Top>Product>CulturalProduct>Movie\",\"score_tag\":\"P\"}],\"sentimented_concept_list\":[{\"form\":\"American\",\"id\":\"0863fc32fb\",\"type\":\"Top\",\"score_tag\":\"P\"},{\"form\":\"American\",\"id\":\"593cbceb1f\",\"type\":\"Top\",\"score_tag\":\"P\"},{\"form\":\"entertainment\",\"id\":\"d80a2f37ad\",\"type\":\"Top\",\"score_tag\":\"P\"},{\"form\":\"science fiction\",\"id\":\"f11d891073\",\"type\":\"Top\",\"score_tag\":\"P\"}]}";

        SentimentResponse r = SentimentResponse.from(response);

        assertEquals("OK", r.status.msg);
        assertEquals("entertainment", r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getText());
        assertEquals(41, r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getInip());
        assertEquals(53, r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getEndp());
        assertEquals(100, r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getConfidence());
        assertEquals("P", r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getScoreTag());
        assertEquals("P", r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getScoreTag());
    }


    /***** test Sentiment Request *****/


    @Test @Throttle
    public void testSentimentRequestFr() throws IOException, Request.ParameterValidationException {
        SentimentResponse r = SentimentRequest
                .build(MEANINGCLOUD_KEY, Request.Language.FR)
                .withText("LE SCAN SPORT - L'unique exemplaire de la célèbre Alpine ayant participé au Dakar en 1981 a été vendue aux enchères pour 72.000 euros.\n" +
                        "\n" +
                        "Si elle a forgé une partie de sa réputation au Championnat du monde des rallye au début des années 1970, la marque Alpine n'a laissé qu'une trace symbolique dans l'histoire des rallyes-raid et de son fleuron, le Dakar. Les modèles du constructeur français n'étaient pas taillés pour affronter les déserts du nord de l'Afrique mais une Alpine A 310 a tout de même tenté sa chance durant l'hiver 1981. Cette année-là, Thierry Reverchon et Georges Vaills ont tenté le pari un peu fou en partant sans assistance et sans l'étiquette officielle du constructeur de Dieppe. Sans grande surprise, le valeureux équipage a été contraint à l'abandon suite à des problèmes de surchauffe à 5 000 km de l'arrivée.")
                .withModel("general_fr")
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals("general_fr", r.getModel());
        assertEquals("N", r.getScoreTag());
        assertEquals("DISAGREEMENT", r.getAgreement());
        assertEquals("OBJECTIVE", r.getSubjectivity());
        assertEquals(86, r.getConfidence());
        assertEquals("NONIRONIC", r.getIrony());

        assertEquals("LE SCAN SPORT - L'unique exemplaire de la célèbre Alpine ayant participé au Dakar en 1981 a été vendue aux enchères pour 72.000 euros.", r.getSentenceList().get(0).getText());
        assertEquals(0, r.getSentenceList().get(0).getInip());
        assertEquals(133, r.getSentenceList().get(0).getEndp());
        assertEquals("y", r.getSentenceList().get(0).getBop());
        assertEquals(100, r.getSentenceList().get(0).getConfidence());
        assertEquals("NONE", r.getSentenceList().get(0).getScoreTag());
        assertEquals("AGREEMENT", r.getSentenceList().get(0).getAgreement());

        assertEquals("LE SCAN SPORT - L'unique exemplaire de la célèbre Alpine ayant participé au Dakar en 1981 a été vendue aux enchères pour 72.000 euros", r.getSentenceList().get(0).getSegmentList().get(0).getText());
        assertEquals("secondary", r.getSentenceList().get(0).getSegmentList().get(0).getSegmentType());
        assertEquals(0, r.getSentenceList().get(0).getSegmentList().get(0).getInip());
        assertEquals(132, r.getSentenceList().get(0).getSegmentList().get(0).getEndp());
        assertEquals(100, r.getSentenceList().get(0).getSegmentList().get(0).getConfidence());
        assertEquals("NONE", r.getSentenceList().get(0).getSegmentList().get(0).getScoreTag());
        assertEquals("AGREEMENT", r.getSentenceList().get(0).getSegmentList().get(0).getAgreement());

        assertEquals("Dakar", r.getSentenceList().get(0).getSentimentedEntityList().get(0).getForm());
        assertEquals("a9b1635831", r.getSentenceList().get(0).getSentimentedEntityList().get(0).getId());
        assertEquals("Top>Location>GeoPoliticalEntity>City", r.getSentenceList().get(0).getSentimentedEntityList().get(0).getType());
        assertEquals("NONE", r.getSentenceList().get(0).getSentimentedEntityList().get(0).getScoreTag());


        assertEquals("Alpine", r.getSentenceList().get(0).getSentimentedConceptList().get(0).getForm());
        //assertEquals("6a909cfc4e", r.getSentenceList().get(0).getSentimentedConceptList().get(0).getId());
        assertEquals("Top", r.getSentenceList().get(0).getSentimentedConceptList().get(0).getType());
        assertEquals("NONE", r.getSentenceList().get(0).getSentimentedConceptList().get(0).getScoreTag());

        /*
        assertEquals("Afrique", r.getSentimentedEntityList().get(0).getForm());
        assertEquals("8c8adb23a1", r.getSentimentedEntityList().get(0).getId());
        assertEquals("Top>Location>GeoPoliticalEntity>Continent", r.getSentimentedEntityList().get(0).getType());
        assertEquals("N+", r.getSentimentedEntityList().get(0).getScoreTag());


        assertEquals("championnat", r.getSentimentedConceptList().get(0).getForm());
        assertEquals("0690231143", r.getSentimentedConceptList().get(0).getId());
        assertEquals("Top>Event>Occasion>Games", r.getSentimentedConceptList().get(0).getType());
        assertEquals("NONE", r.getSentimentedConceptList().get(0).getScoreTag());
         */
    }


    @Test @Throttle
    public void testSentimentRequestEn() throws IOException, Request.ParameterValidationException {
        SentimentResponse r = SentimentRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("UK negotiators have been warned that the EU draft withdrawal agreement will stipulate that Northern Ireland will, in effect, remain in the customs union and single market after Brexit to avoid a hard border.\n" +
                        "\n" +
                        "The uncompromising legal language of the draft agreement is likely to provoke a major row, something all parties to the negotiations have been trying to avoid.\n" +
                        "\n" +
                        "British officials negotiating in Brussels were told by their counterparts that there could be a “sunset clause” included in the legally binding text, which is due to be published in around two weeks. Such a legal device would make the text null and void at a future date should an unexpectedly generous free trade deal, or a hitherto unimagined technological solution emerge that could be as effective as the status quo in avoiding the need for border infrastructure.")
                .withModel("general")
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals("general_en", r.getModel());
        assertEquals("N", r.getScoreTag());
        assertEquals("DISAGREEMENT", r.getAgreement());
        assertEquals("SUBJECTIVE", r.getSubjectivity());
        assertEquals(94, r.getConfidence());
        assertEquals("NONIRONIC", r.getIrony());

        assertEquals("UK negotiators have been warned that the EU draft withdrawal agreement will stipulate that Northern Ireland will, in effect, remain in the customs union and single market after Brexit to avoid a hard border.", r.getSentenceList().get(0).getText());
        assertEquals(0, r.getSentenceList().get(0).getInip());
        assertEquals(206, r.getSentenceList().get(0).getEndp());
        assertEquals("y", r.getSentenceList().get(0).getBop());
        assertEquals(100, r.getSentenceList().get(0).getConfidence());
        assertEquals("N", r.getSentenceList().get(0).getScoreTag());
        assertEquals("AGREEMENT", r.getSentenceList().get(0).getAgreement());

        assertEquals("UK negotiators have been warned that the EU draft withdrawal agreement will stipulate that Northern Ireland will, in effect, remain in the customs union and single market after Brexit to avoid a hard border", r.getSentenceList().get(0).getSegmentList().get(0).getText());
        assertEquals("main", r.getSentenceList().get(0).getSegmentList().get(0).getSegmentType());
        assertEquals(0, r.getSentenceList().get(0).getSegmentList().get(0).getInip());
        assertEquals(205, r.getSentenceList().get(0).getSegmentList().get(0).getEndp());
        assertEquals(100, r.getSentenceList().get(0).getSegmentList().get(0).getConfidence());
        assertEquals("N", r.getSentenceList().get(0).getSegmentList().get(0).getScoreTag());
        assertEquals("AGREEMENT", r.getSentenceList().get(0).getSegmentList().get(0).getAgreement());


        assertEquals("avoid", r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getText());
        assertEquals(184, r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getInip());
        assertEquals(191, r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getEndp());
        assertEquals(100, r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getConfidence());
        assertEquals("N", r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getScoreTag());

        assertEquals("United Kingdom", r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getSentimentedEntityList().get(0).getForm());
        assertEquals("d29f412b4b", r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getSentimentedEntityList().get(0).getId());
        assertEquals("UK", r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getSentimentedEntityList().get(0).getVariant());
        assertEquals(0, r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getSentimentedEntityList().get(0).getInip());
        assertEquals(1, r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getSentimentedEntityList().get(0).getEndp());
        assertEquals("Top>Location>GeoPoliticalEntity>Country", r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getSentimentedEntityList().get(0).getType());
        assertEquals("N", r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getSentimentedEntityList().get(0).getScoreTag());

        /*
        assertEquals("negotiator", r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getSentimentedConceptList().get(0).getForm());
        assertEquals("a12b87eaf9", r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getSentimentedConceptList().get(0).getId());
        assertEquals("negotiators", r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getSentimentedConceptList().get(0).getVariant());
        assertEquals(3, r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getSentimentedConceptList().get(0).getInip());
        assertEquals(13, r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getSentimentedConceptList().get(0).getEndp());
        assertEquals("Top>Person", r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getSentimentedConceptList().get(0).getType());
        assertEquals("N", r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getSentimentedConceptList().get(0).getScoreTag());
        */

        assertEquals("UK negotiators have been warned that the EU draft withdrawal agreement will stipulate that Northern Ireland will, in effect, remain in the customs union and single market after Brexit to avoid a hard border",r.getSentenceList().get(0).getSegmentList().get(0).getText());
        assertEquals("main",r.getSentenceList().get(0).getSegmentList().get(0).getSegmentType());
        assertEquals(0,r.getSentenceList().get(0).getSegmentList().get(0).getInip());
        assertEquals(205,r.getSentenceList().get(0).getSegmentList().get(0).getEndp());
        assertEquals(100,r.getSentenceList().get(0).getSegmentList().get(0).getConfidence());
        assertEquals("N",r.getSentenceList().get(0).getSegmentList().get(0).getScoreTag());
        assertEquals("AGREEMENT",r.getSentenceList().get(0).getSegmentList().get(0).getAgreement());

        assertEquals("Brexit",r.getSentimentedEntityList().get(0).getForm());
        assertEquals("3e256845d0",r.getSentimentedEntityList().get(0).getId());
        //assertEquals("Top>Process>IntentionalProcess>OrganizationalProcess>LeavingAnOrganization",r.getSentimentedEntityList().get(0).getType());
        assertEquals("N",r.getSentimentedEntityList().get(0).getScoreTag());

        /*
        assertEquals("language",r.getSentimentedConceptList().get(0).getForm());
        assertEquals("0482bd2d76",r.getSentimentedConceptList().get(0).getId());
        assertEquals("Top>OtherEntity>Language",r.getSentimentedConceptList().get(0).getType());
        assertEquals("NEU",r.getSentimentedConceptList().get(0).getScoreTag());
        */
    }


    @Test @Throttle
    public void testSentimentRequestPolarityEnabled() throws IOException, Request.ParameterValidationException {
        SentimentResponse r = SentimentRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("Star Trek is an American science fiction entertainment")
                .withPolarity(true)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals("general_en", r.getModel());
        assertEquals("P", r.getScoreTag());
        assertEquals("AGREEMENT", r.getAgreement());
        assertEquals("OBJECTIVE", r.getSubjectivity());
        assertEquals(100, r.getConfidence());
        assertEquals("NONIRONIC", r.getIrony());

        assertEquals("Star Trek is an American science fiction entertainment", r.getSentenceList().get(0).getText());
        assertEquals(0, r.getSentenceList().get(0).getInip());
        assertEquals(53, r.getSentenceList().get(0).getEndp());
        assertEquals("y", r.getSentenceList().get(0).getBop());
        assertEquals(100, r.getSentenceList().get(0).getConfidence());
        assertEquals("P", r.getSentenceList().get(0).getScoreTag());
        assertEquals("AGREEMENT", r.getSentenceList().get(0).getAgreement());

        assertEquals("entertainment", r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getText());
        assertEquals(41, r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getInip());
        assertEquals(53, r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getEndp());
        assertEquals(100, r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getConfidence());
        assertEquals("P", r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getScoreTag());
        assertEquals("P", r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getScoreTag());
    }


    @Test @Throttle
    public void testSentimentRequestVerbose() throws IOException, Request.ParameterValidationException {
        SentimentResponse r = SentimentRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("Star Trek is an American science fiction entertainment franchise based on the television series created by Gene Roddenberry")
                .withModel("general")
                .withVerbose(true)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals("P", r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getTagStack());
    }

    @Test @Throttle
    public void testSentimentRequestAuto() throws IOException, Request.ParameterValidationException {
        SentimentResponse r = SentimentRequest
                .build(MEANINGCLOUD_KEY, Request.Language.AUTO)
                .withText("Star Trek is an American science fiction entertainment franchise based on the television series created by Gene Roddenberry")
                .withModel("general")
                .withVerbose(true)
                .send();

        assertEquals("OK", r.status.msg);
    }


    @Test @Throttle
    public void testSentimentRequestMarkup() throws IOException, Request.ParameterValidationException {
        SentimentResponse r = SentimentRequest
                .build(MEANINGCLOUD_KEY, Request.Language.ES)
                .withText("<html>\n" +
                        "<head>\n" +
                        "<title>Ejemplo de etiqueta em y strong</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<p>El lenguaje HTML permite marcar algunos segmentos de texto \n" +
                        "como <em>muy importantes</em> y otros segmentos como <strong>los \n" +
                        "más importantes</strong>.</p>\n" +
                        "</body>\n" +
                        "</html>")
                .withModel("general_es")
                .withTxtf(Request.TextFormat.MARKUP)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals("general_es", r.getModel());
        assertEquals("P", r.getScoreTag());
        assertEquals("AGREEMENT", r.getAgreement());
        assertEquals("OBJECTIVE", r.getSubjectivity());
        assertEquals(100, r.getConfidence());
        assertEquals("NONIRONIC", r.getIrony());

        assertEquals("El lenguaje HTML permite marcar algunos segmentos de texto como muy importantes y otros segmentos como los más importantes.", r.getSentenceList().get(0).getText());
        assertEquals("main", r.getSentenceList().get(0).getSegmentList().get(0).getSegmentType());
        assertEquals("(permitir) importantes", r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getText());
        assertEquals("Top>OtherEntity>Language", r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getSentimentedConceptList().get(0).getType());
        assertEquals(".html", r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getSentimentedConceptList().get(1).getForm());
        assertEquals("HTML", r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getSentimentedConceptList().get(2).getVariant());
        assertEquals("Top>OtherEntity>MethodSystem", r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getSentimentedConceptList().get(2).getType());
    }


    @Test @Throttle
    public void testSentimentRequestWithFile() throws IOException, Request.ParameterValidationException {
        File file = createTemporaryFileWithContents("El polaco Zygmunt Bauman, sociólogo de cabecera para entender la sociedad actual, pensaba que vivíamos en una era tan cambiante que aquel \"para siempre\", que los muros tradicionales que siempre hemos construido para articular nuestra existencia, simplemente dejaba de tener sentido. Una nueva forma de entender nuestros ser que afecta a todos los aspectos que podamos llegar a imaginar, desde el amor hasta, como no, la propia transformación digital.Y es que ese principio de que todo lo que creíamos sólido pasa a ser ahora líquido puede trasladarse perfectamente al ámbito tecnológico. Antaño, cuando una compañía instalaba un mainframe o implementaba una herramienta de gestión de recursos empresariales (ERP), lo hacía tras meses o años de planificación, con otros tantos de desarrollo y con la previsión de que su obra perdurara al menos un par de décadas. Hoy, el escenario es diametralmente opuesto: las exigencias del mercado requieren metodologías ágiles y flexibles, con tiempos de desarrollo de horas y en los que no solo interviene un tipo de perfil concreto como hace unos cuantos cursos: ahora se busca que no haya muros entre todos los agentes que deambulan por estos lares.");

        SentimentResponse r = SentimentRequest
                .build(MEANINGCLOUD_KEY, Request.Language.ES)
                .withFile(file)
                .withModel("general")
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals("general_es", r.getModel());
        assertEquals("P", r.getScoreTag());
        assertEquals("AGREEMENT", r.getAgreement());
        assertEquals("SUBJECTIVE", r.getSubjectivity());
        assertEquals(100, r.getConfidence());
        assertEquals("NONIRONIC", r.getIrony());

        assertEquals("El polaco Zygmunt Bauman, sociólogo de cabecera para entender la sociedad actual, pensaba que vivíamos en una era tan cambiante que aquel \"para siempre\", que los muros tradicionales que siempre hemos construido para articular nuestra existencia, simplemente dejaba de tener sentido.", r.getSentenceList().get(0).getText());
        assertEquals("main", r.getSentenceList().get(0).getSegmentList().get(0).getSegmentType());
        assertEquals("Zygmunt Bauman", r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getSentimentedEntityList().get(0).getForm());
        /*
        assertEquals("polaco", r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getSentimentedConceptList().get(0).getForm());
        assertEquals("sociólogo", r.getSentenceList().get(0).getSegmentList().get(0).getPolarityTermList().get(0).getSentimentedConceptList().get(2).getForm());
        */
    }

    @Test @Throttle
    public void testSentimentRequestWithURL() throws IOException, Request.ParameterValidationException{
        URL url = new URL("https://www.google.com");
        SentimentResponse r = SentimentRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withURL(url)
                .send();

        assertEquals("OK", r.status.msg);
        assertNotNull(r.getSentenceList().get(0).getText());
    }

    @Test
    public void testSentimentRequestWithDictionaries() throws IOException, Request.ParameterValidationException{
        class TestTransport implements Transport {
            @Override
            public String send(Endpoint endpoint, Map<String, String> params) throws IOException {
                assertEquals(params.get("ud"), "a|b");
                return "{}";
            }
        }
        SentimentResponse r = SentimentRequest
                .build(new TestTransport(), MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("Something")
                .withDictionaries("a", "b")
                .send();
    }
}
