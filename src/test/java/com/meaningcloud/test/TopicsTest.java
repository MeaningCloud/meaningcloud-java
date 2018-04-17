package com.meaningcloud.test;

import com.meaningcloud.*;
import com.meaningcloud.test.junit.Throttle;
import com.meaningcloud.test.junit.Throttling;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Throttling.class)
public class TopicsTest extends TestSuper {


    /***** test Basic Expressions Topics *****/


    @Test
    public void testEntities1() {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\",\"remaining_credits\":\"39998\"},\"entity_list\":[{\"form\":\"Esto\",\"id\":\"__11834327657622400502\",\"sementity\":{\"class\":\"instance\",\"type\":\"Top\",\"confidence\":\"unknown\"},\"variant_list\":[{\"form\":\"Esto\",\"inip\":\"0\",\"endp\":\"3\"}],\"relevance\":\"100\"},{\"form\":\"UN\",\"official_form\":\"United Nations Organisation\",\"id\":\"219c220700\",\"sementity\":{\"class\":\"instance\",\"fiction\":\"nonfiction\",\"id\":\"ODENTITY_INTERNATIONAL_ORGANIZATION\",\"type\":\"Top>Organization>InternationalOrganization\"},\"semld_list\":[\"http:\\/\\/en.wikipedia.org\\/wiki\\/United_Nations\",\"http:\\/\\/ar.wikipedia.org\\/wiki\\/الأمم_المتحدة\",\"http:\\/\\/ca.wikipedia.org\\/wiki\\/Organització_de_les_Nacions_Unides\",\"http:\\/\\/cs.wikipedia.org\\/wiki\\/Organizace_spojených_národů\",\"http:\\/\\/da.wikipedia.org\\/wiki\\/Forenede_Nationer\",\"http:\\/\\/de.wikipedia.org\\/wiki\\/Vereinte_Nationen\",\"http:\\/\\/es.wikipedia.org\\/wiki\\/Organización_de_las_Naciones_Unidas\",\"http:\\/\\/fi.wikipedia.org\\/wiki\\/Yhdistyneet_kansakunnat\",\"http:\\/\\/fr.wikipedia.org\\/wiki\\/Organisation_des_Nations_unies\",\"http:\\/\\/he.wikipedia.org\\/wiki\\/האומות_המאוחדות\",\"http:\\/\\/hi.wikipedia.org\\/wiki\\/संयुक्त_राष्ट्र\",\"http:\\/\\/id.wikipedia.org\\/wiki\\/Perserikatan_Bangsa-Bangsa\",\"http:\\/\\/it.wikipedia.org\\/wiki\\/Organizzazione_delle_Nazioni_Unite\",\"http:\\/\\/ja.wikipedia.org\\/wiki\\/国際連合\",\"http:\\/\\/ko.wikipedia.org\\/wiki\\/유엔\",\"http:\\/\\/nl.wikipedia.org\\/wiki\\/Verenigde_Naties\",\"http:\\/\\/no.wikipedia.org\\/wiki\\/De_forente_nasjoner\",\"http:\\/\\/pl.wikipedia.org\\/wiki\\/Organizacja_Narodów_Zjednoczonych\",\"http:\\/\\/pt.wikipedia.org\\/wiki\\/Organização_das_Nações_Unidas\",\"http:\\/\\/ro.wikipedia.org\\/wiki\\/Organizația_Națiunilor_Unite\",\"http:\\/\\/ru.wikipedia.org\\/wiki\\/Организация_Объединённых_Наций\",\"http:\\/\\/sv.wikipedia.org\\/wiki\\/Förenta_nationerna\",\"http:\\/\\/th.wikipedia.org\\/wiki\\/สหประชาชาติ\",\"http:\\/\\/tr.wikipedia.org\\/wiki\\/Birleşmiş_Milletler\",\"http:\\/\\/zh.wikipedia.org\\/wiki\\/联合国\",\"http:\\/\\/d-nb.info\\/gnd\\/333-5\",\"http:\\/\\/data.nytimes.com\\/45429116372210537182\",\"http:\\/\\/sw.cyc.com\\/concept\\/Mx4rvVkMNpwpEbGdrcN5Y29ycA\",\"http:\\/\\/openei.org\\/resources\\/United_Nations\",\"http:\\/\\/www.bbc.co.uk\\/things\\/b79ae056-59b1-49c7-8630-50f7e223e906#id\",\"sumo:InternationalOrganization\"],\"semtheme_list\":[{\"id\":\"ODTHEME_SOCIAL_PROTECTION\",\"type\":\"Top>Society>SocialProtection\"}],\"variant_list\":[{\"form\":\"un\",\"inip\":\"8\",\"endp\":\"9\"}],\"relevance\":\"100\"}],\"concept_list\":[],\"time_expression_list\":[],\"money_expression_list\":[],\"quantity_expression_list\":[],\"other_expression_list\":[],\"quotation_list\":[],\"relation_list\":[]}";

        TopicsResponse r = TopicsResponse.from(response);

        assertEquals("OK", r.status.msg);
        assertEquals(2, r.getEntityList().size());
        assertEquals("United Nations Organisation", r.getEntityList().get(1).getOfficialForm());
        assertEquals("__11834327657622400502", r.getEntityList().get(0).getId());
    }


    @Test
    public void testConcepts1() {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\",\"remaining_credits\":\"39996\"},\"concept_list\":[{\"form\":\"home\",\"id\":\"144b5c0ed4\",\"sementity\":{\"class\":\"class\",\"fiction\":\"nonfiction\",\"id\":\"ODENTITY_FACILITY\",\"type\":\"Top>Location>Facility\"},\"semld_list\":[\"http:\\/\\/en.wikipedia.org\\/wiki\\/Home\",\"http:\\/\\/ar.wikipedia.org\\/wiki\\/بيت\",\"http:\\/\\/ca.wikipedia.org\\/wiki\\/Llar\",\"http:\\/\\/es.wikipedia.org\\/wiki\\/Hogar\",\"http:\\/\\/fi.wikipedia.org\\/wiki\\/Koti\",\"http:\\/\\/fr.wikipedia.org\\/wiki\\/Foyer_(logement)\",\"http:\\/\\/he.wikipedia.org\\/wiki\\/בית\",\"http:\\/\\/hi.wikipedia.org\\/wiki\\/घर\",\"http:\\/\\/it.wikipedia.org\\/wiki\\/Residenza_(diritto)\",\"http:\\/\\/ja.wikipedia.org\\/wiki\\/家庭\",\"http:\\/\\/ko.wikipedia.org\\/wiki\\/가정\",\"http:\\/\\/nl.wikipedia.org\\/wiki\\/Thuis_(woning)\",\"http:\\/\\/pt.wikipedia.org\\/wiki\\/Lar\",\"http:\\/\\/ro.wikipedia.org\\/wiki\\/Acasă\",\"http:\\/\\/sv.wikipedia.org\\/wiki\\/Hem\",\"http:\\/\\/zh.wikipedia.org\\/wiki\\/家\",\"sumo:FacilityPartOther\"],\"variant_list\":[{\"form\":\"casa\",\"inip\":\"12\",\"endp\":\"15\"}],\"relevance\":\"100\"}]}";

        TopicsResponse r = TopicsResponse.from(response);

        assertEquals(1, r.getConceptList().size());
        assertEquals("home", r.getConceptList().get(0).getForm());
        assertEquals("casa", r.getConceptList().get(0).getVariantList().get(0).getForm());
        assertEquals("class", r.getConceptList().get(0).getSementity().getSemEntityClass());
        assertEquals("Top>Location>Facility", r.getConceptList().get(0).getSementity().getType());
    }


    @Test
    public void testBasicTimeExpressionsTopics() {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\"},\"entity_list\":[],\"concept_list\":[],\"time_expression_list\":[{\"form\":\"Saturday\",\"normalized_form\":\"|||s|||||||\",\"actual_time\":\"2018-01-13\",\"precision\":\"weekday\",\"inip\":\"0\",\"endp\":\"7\"}],\"money_expression_list\":[],\"quantity_expression_list\":[],\"other_expression_list\":[],\"quotation_list\":[],\"relation_list\":[]}";

        TopicsResponse r = TopicsResponse.from(response);

        assertEquals("OK", r.status.msg);
        assertEquals(1,r.getTimeExpressionList().size());
        assertEquals("Saturday", r.getTimeExpressionList().get(0).getForm());
        assertEquals("|||s|||||||", r.getTimeExpressionList().get(0).getNormalizedForm());
        assertEquals("weekday", r.getTimeExpressionList().get(0).getPrecision());
        assertEquals(0, r.getTimeExpressionList().get(0).getInip());
        assertEquals(7, r.getTimeExpressionList().get(0).getEndp());
    }


    @Test
    public void testBasicMoneyExpressionsTopics() {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\"},\"entity_list\":[],\"concept_list\":[{\"form\":\"$\",\"id\":\"__9145003407816029121\",\"sementity\":{\"class\":\"class\",\"type\":\"Top>Unit>Currency\"},\"variant_list\":[{\"form\":\"$\",\"inip\":\"1\",\"endp\":\"1\"}],\"relevance\":\"100\"}],\"time_expression_list\":[],\"money_expression_list\":[{\"form\":\"2$ millions\",\"amount_form\":\"2\",\"numeric_value\":\"2\",\"currency\":\"USD\",\"inip\":\"0\",\"endp\":\"10\"}],\"quantity_expression_list\":[{\"form\":\"2$ millions\",\"amount_form\":\"2\",\"numeric_value\":\"2\",\"unit\":\"millions\",\"inip\":\"0\",\"endp\":\"10\"}],\"other_expression_list\":[],\"quotation_list\":[],\"relation_list\":[]}";

        TopicsResponse r = TopicsResponse.from(response);

        assertEquals("OK", r.status.msg);
        assertEquals(1,r.getMoneyExpressionList().size());
        assertEquals("2$ millions", r.getMoneyExpressionList().get(0).getForm());
        assertEquals("2", r.getMoneyExpressionList().get(0).getAmountForm());
        assertEquals("2", r.getMoneyExpressionList().get(0).getNumericValue());
        assertEquals("USD", r.getMoneyExpressionList().get(0).getCurrency());
        assertEquals(0, r.getMoneyExpressionList().get(0).getInip());
        assertEquals(10, r.getMoneyExpressionList().get(0).getEndp());
    }


    @Test
    public void testBasicQuantityExpressionTopics() {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\"},\"entity_list\":[],\"concept_list\":[{\"form\":\"cup\",\"id\":\"562f8c4537\",\"sementity\":{\"class\":\"class\",\"fiction\":\"nonfiction\",\"id\":\"ODENTITY_GAMES\",\"type\":\"Top>Event>Occasion>Games\"},\"semld_list\":[\"sumo:GameEvent\"],\"semtheme_list\":[{\"id\":\"ODTHEME_SPORT\",\"type\":\"Top>Sport\"}],\"variant_list\":[{\"form\":\"cup\",\"inip\":\"4\",\"endp\":\"6\"}],\"relevance\":\"100\"}],\"time_expression_list\":[],\"money_expression_list\":[],\"quantity_expression_list\":[{\"form\":\"one cup of water\",\"amount_form\":\"one\",\"numeric_value\":\"1\",\"unit\":\"cup\",\"inip\":\"0\",\"endp\":\"15\"}],\"other_expression_list\":[],\"quotation_list\":[],\"relation_list\":[]}";

        TopicsResponse r = TopicsResponse.from(response);

        assertEquals("OK", r.status.msg);
        assertEquals(1,r.getQuantityExpressionList().size());
        assertEquals("one cup of water", r.getQuantityExpressionList().get(0).getForm());
        assertEquals("one", r.getQuantityExpressionList().get(0).getAmountForm());
        assertEquals("1", r.getQuantityExpressionList().get(0).getNumericValue());
        assertEquals("cup", r.getQuantityExpressionList().get(0).getUnit());
        assertEquals(0, r.getQuantityExpressionList().get(0).getInip());
        assertEquals(15, r.getQuantityExpressionList().get(0).getEndp());
    }


    @Test
    public void testBasicOtherExpressionsTopics() {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\"},\"entity_list\":[{\"form\":\"DR2017\",\"id\":\"__17492606955877551602\",\"sementity\":{\"class\":\"instance\",\"type\":\"Top\",\"confidence\":\"unknown\"},\"variant_list\":[{\"form\":\"DR2017\",\"inip\":\"0\",\"endp\":\"5\"}],\"relevance\":\"100\"}],\"concept_list\":[],\"time_expression_list\":[],\"money_expression_list\":[],\"quantity_expression_list\":[],\"other_expression_list\":[{\"form\":\"DR2017\",\"type\":\"flight number\",\"inip\":\"0\",\"endp\":\"5\"}],\"quotation_list\":[],\"relation_list\":[]}";

        TopicsResponse r = TopicsResponse.from(response);

        assertEquals("OK", r.status.msg);
        assertEquals(1,r.getOtherExpressionList().size());
        assertEquals("DR2017", r.getOtherExpressionList().get(0).getForm());
        assertEquals("flight number", r.getOtherExpressionList().get(0).getType());
        assertEquals(0, r.getOtherExpressionList().get(0).getInip());
        assertEquals(5, r.getOtherExpressionList().get(0).getEndp());
    }


    @Test
    public void testBasicQuotationsExpressionTopics() {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\"},\"entity_list\":[],\"concept_list\":[],\"time_expression_list\":[],\"money_expression_list\":[],\"quantity_expression_list\":[],\"other_expression_list\":[],\"quotation_list\":[{\"form\":\"it was fantastic\",\"who\": {\"form\":\"He\",\"lemma\":\"he\"},\"verb\": {\"form\":\"said\",\"lemma\":\"say\"},\"inip\":\"8\",\"endp\":\"23\"}],\"relation_list\":[{\"form\":\"He said it was fantastic\",\"inip\":\"0\",\"endp\":\"23\",\"subject\":{\"form\":\"He\",\"lemma_list\":[\"he\"],\"sense_id_list\":[\"PRONHUMAN\"]},\"verb\":{\"form\":\"said\",\"lemma_list\":[\"say\"],\"sense_id_list\":[\"ODENTITY_COMMUNICATION_PROCESS\",\"ODENTITY_LINGUISTIC_COMMUNICATION\",\"ODENTITY_PROCESS\"]},\"complement_list\":[{\"form\":\"it was fantastic\",\"type\":\"isDirectObject\"}],\"degree\":\"1\"},{\"form\":\"He said it was fantastic\",\"inip\":\"8\",\"endp\":\"23\",\"subject\":{\"form\":\"it\",\"lemma_list\":[\"it\"],\"sense_id_list\":[\"PRONNONHUMAN\"]},\"verb\":{\"form\":\"was\",\"lemma_list\":[\"be\"]},\"complement_list\":[{\"form\":\"fantastic\",\"type\":\"isAttribute\"}],\"degree\":\"1\"}]}";

        TopicsResponse r = TopicsResponse.from(response);

        assertEquals("OK", r.status.msg);
        assertEquals(1,r.getQuotationList().size());
        assertEquals("it was fantastic", r.getQuotationList().get(0).getForm());
        assertEquals("he", r.getQuotationList().get(0).getQuote().getLemma());
        assertEquals("said", r.getQuotationList().get(0).getVerb().getForm());
        assertEquals(8, r.getQuotationList().get(0).getInip());
        assertEquals(23, r.getQuotationList().get(0).getEndp());
    }


    @Test
    public void testBasicRelationsTopics() {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\"},\"entity_list\":[],\"concept_list\":[],\"time_expression_list\":[],\"money_expression_list\":[],\"quantity_expression_list\":[],\"other_expression_list\":[],\"quotation_list\":[],\"relation_list\":[{\"form\":\"I have a lot of money\",\"inip\":\"0\",\"endp\":\"20\",\"subject\":{\"form\":\"I\",\"lemma_list\":[\"I\"],\"sense_id_list\":[\"PRONHUMAN\"]},\"verb\":{\"form\":\"have\",\"lemma_list\":[\"have\"]},\"complement_list\":[{\"form\":\"a lot of money\",\"type\":\"isDirectObject\"}],\"degree\":\"1\"}]}";

        TopicsResponse r = TopicsResponse.from(response);

        assertEquals("OK", r.status.msg);
        assertEquals(1,r.getRelationList().size());
        assertEquals("I have a lot of money", r.getRelationList().get(0).getForm());
        assertEquals(0, r.getRelationList().get(0).getInip());
        assertEquals(20, r.getRelationList().get(0).getEndp());
    }


    @Test
    public void testBellCharacter() {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\"},\"entity_list\":[{\"form\":\"London\",\"id\":\"01d0d69c7d\",\"sementity\":{\"class\":\"instance\",\"fiction\":\"nonfiction\",\"id\":\"ODENTITY_CITY\",\"type\":\"Top>Location>GeoPoliticalEntity>City\"},\"semgeo_list\":[{\"adm1\":{\"form\":\"England\",\"id\":\"98db781864\"},\"adm2\":{\"form\":\"Greater London\",\"id\":\"ed00f6dec4\"},\"continent\":{\"form\":\"Europe\",\"id\":\"0404ea4d6c\"},\"country\":{\"form\":\"United Kingdom\",\"id\":\"d29f412b4b\",\"standard_list\":[{\"id\":\"ISO3166-1-a2\",\"value\":\"GB\"},{\"id\":\"ISO3166-1-a3\",\"value\":\"GBR\"}]}}],\"semld_list\":[\"http:\\/\\/en.wikipedia.org\\/wiki\\/London\",\"http:\\/\\/ar.wikipedia.org\\/wiki\\/لندن\",\"http:\\/\\/ca.wikipedia.org\\/wiki\\/Londres\",\"http:\\/\\/cs.wikipedia.org\\/wiki\\/Londýn\",\"http:\\/\\/da.wikipedia.org\\/wiki\\/London\",\"http:\\/\\/de.wikipedia.org\\/wiki\\/London\",\"http:\\/\\/es.wikipedia.org\\/wiki\\/Londres\",\"http:\\/\\/fi.wikipedia.org\\/wiki\\/Lontoo\",\"http:\\/\\/fr.wikipedia.org\\/wiki\\/Londres\",\"http:\\/\\/he.wikipedia.org\\/wiki\\/לונדון\",\"http:\\/\\/hi.wikipedia.org\\/wiki\\/लंदन\",\"http:\\/\\/id.wikipedia.org\\/wiki\\/London\",\"http:\\/\\/it.wikipedia.org\\/wiki\\/Londra\",\"http:\\/\\/ja.wikipedia.org\\/wiki\\/ロンドン\",\"http:\\/\\/ko.wikipedia.org\\/wiki\\/런던\",\"http:\\/\\/nl.wikipedia.org\\/wiki\\/Londen\",\"http:\\/\\/no.wikipedia.org\\/wiki\\/London\",\"http:\\/\\/pl.wikipedia.org\\/wiki\\/Londyn\",\"http:\\/\\/pt.wikipedia.org\\/wiki\\/Londres\",\"http:\\/\\/ro.wikipedia.org\\/wiki\\/Londra\",\"http:\\/\\/ru.wikipedia.org\\/wiki\\/Лондон\",\"http:\\/\\/sv.wikipedia.org\\/wiki\\/London\",\"http:\\/\\/th.wikipedia.org\\/wiki\\/ลอนดอน\",\"http:\\/\\/tr.wikipedia.org\\/wiki\\/Londra\",\"http:\\/\\/zh.wikipedia.org\\/wiki\\/伦敦\",\"http:\\/\\/d-nb.info\\/gnd\\/4074335-4\",\"http:\\/\\/linkedgeodata.org\\/triplify\\/node107775\",\"http:\\/\\/linked-web-apis.fit.cvut.cz\\/resource\\/london_city\",\"http:\\/\\/linked-web-apis.fit.cvut.cz\\/resource\\/london_uk_city\",\"http:\\/\\/data.nytimes.com\\/14085781296239331901\",\"http:\\/\\/sw.cyc.com\\/concept\\/Mx4rvVjWPJwpEbGdrcN5Y29ycA\",\"http:\\/\\/umbel.org\\/umbel\\/rc\\/Location_Underspecified\",\"http:\\/\\/umbel.org\\/umbel\\/rc\\/PopulatedPlace\",\"http:\\/\\/umbel.org\\/umbel\\/rc\\/Village\",\"http:\\/\\/sws.geonames.org\\/2643743\\/\",\"@BBCLondres2012\",\"@LDN\",\"@OlimpicoCaracol\",\"@TelevisaLondres\",\"@TimeOutLondon\",\"@visitlondon\",\"sumo:City\"],\"variant_list\":[{\"form\":\"London\",\"inip\":\"0\",\"endp\":\"5\"}],\"relevance\":\"100\"},{\"form\":\"Madrid\",\"id\":\"3d0a16c68d\",\"sementity\":{\"class\":\"instance\",\"fiction\":\"nonfiction\",\"id\":\"ODENTITY_CITY\",\"type\":\"Top>Location>GeoPoliticalEntity>City\"},\"semgeo_list\":[{\"adm1\":{\"form\":\"Madrid\",\"id\":\"e9e123c4d2\"},\"continent\":{\"form\":\"Europe\",\"id\":\"0404ea4d6c\"},\"country\":{\"form\":\"Spain\",\"id\":\"2175fdcac2\",\"standard_list\":[{\"id\":\"ISO3166-1-a2\",\"value\":\"ES\"},{\"id\":\"ISO3166-1-a3\",\"value\":\"ESP\"}]}}],\"semld_list\":[\"http:\\/\\/en.wikipedia.org\\/wiki\\/Madrid\",\"http:\\/\\/ar.wikipedia.org\\/wiki\\/مدريد\",\"http:\\/\\/ca.wikipedia.org\\/wiki\\/Madrid\",\"http:\\/\\/cs.wikipedia.org\\/wiki\\/Madrid\",\"http:\\/\\/da.wikipedia.org\\/wiki\\/Madrid\",\"http:\\/\\/de.wikipedia.org\\/wiki\\/Madrid\",\"http:\\/\\/es.wikipedia.org\\/wiki\\/Madrid\",\"http:\\/\\/fi.wikipedia.org\\/wiki\\/Madrid\",\"http:\\/\\/fr.wikipedia.org\\/wiki\\/Madrid\",\"http:\\/\\/he.wikipedia.org\\/wiki\\/מדריד\",\"http:\\/\\/hi.wikipedia.org\\/wiki\\/मद्रिद\",\"http:\\/\\/id.wikipedia.org\\/wiki\\/Madrid\",\"http:\\/\\/it.wikipedia.org\\/wiki\\/Madrid\",\"http:\\/\\/ja.wikipedia.org\\/wiki\\/マドリード\",\"http:\\/\\/ko.wikipedia.org\\/wiki\\/마드리드\",\"http:\\/\\/nl.wikipedia.org\\/wiki\\/Madrid_(stad)\",\"http:\\/\\/no.wikipedia.org\\/wiki\\/Madrid\",\"http:\\/\\/pl.wikipedia.org\\/wiki\\/Madryt\",\"http:\\/\\/pt.wikipedia.org\\/wiki\\/Madrid\",\"http:\\/\\/ro.wikipedia.org\\/wiki\\/Madrid\",\"http:\\/\\/ru.wikipedia.org\\/wiki\\/Мадрид\",\"http:\\/\\/sv.wikipedia.org\\/wiki\\/Madrid\",\"http:\\/\\/th.wikipedia.org\\/wiki\\/มาดริด\",\"http:\\/\\/tr.wikipedia.org\\/wiki\\/Madrid\",\"http:\\/\\/zh.wikipedia.org\\/wiki\\/马德里\",\"http:\\/\\/d-nb.info\\/gnd\\/4036862-2\",\"http:\\/\\/linkedgeodata.org\\/triplify\\/node21068295\",\"http:\\/\\/linked-web-apis.fit.cvut.cz\\/resource\\/madrid_city\",\"http:\\/\\/data.nytimes.com\\/51083708004460018461\",\"http:\\/\\/sw.cyc.com\\/concept\\/Mx4rvVkLTpwpEbGdrcN5Y29ycA\",\"http:\\/\\/umbel.org\\/umbel\\/rc\\/Location_Underspecified\",\"http:\\/\\/umbel.org\\/umbel\\/rc\\/PopulatedPlace\",\"http:\\/\\/umbel.org\\/umbel\\/rc\\/Village\",\"http:\\/\\/sws.geonames.org\\/3117735\\/\",\"@realmadrid\",\"sumo:City\"],\"variant_list\":[{\"form\":\"Madrid\",\"inip\":\"7\",\"endp\":\"12\"}],\"relevance\":\"100\"}]}";

        TopicsResponse r = TopicsResponse.from(response);

        assertEquals("OK", r.status.msg);
        assertEquals(2,r.getEntityList().size());
        assertEquals("London", r.getEntityList().get(0).getForm());
        assertEquals("Madrid", r.getEntityList().get(1).getForm());
    }


    /***** test Topic Request *****/


    @Test @Throttle
    public void testTopicsRequest() throws IOException, Request.ParameterValidationException {
        TopicsResponse r = TopicsRequest
                .build(MEANINGCLOUD_KEY, Request.Language.ES)
                .withText("Hola, planeta!")
                .send();

        assertEquals(1, r.getConceptList().size());
        assertEquals("planet", r.getConceptList().get(0).getForm());
        assertEquals(100, r.getConceptList().get(0).getRelevance());
        assertEquals("planeta", r.getConceptList().get(0).getVariantList().get(0).getForm());
        assertEquals(6, r.getConceptList().get(0).getVariantList().get(0).getInip());
        assertEquals(12, r.getConceptList().get(0).getVariantList().get(0).getEndp());
        assertEquals("class", r.getConceptList().get(0).getSementity().getSemEntityClass());
        assertEquals("Top>Location>AstralBody>Planet", r.getConceptList().get(0).getSementity().getType());
        assertEquals("nonfiction", r.getConceptList().get(0).getSementity().getFiction());
        assertEquals("ODENTITY_PLANET", r.getConceptList().get(0).getSementity().getId());
        assertEquals("ODTHEME_ASTRONOMY", r.getConceptList().get(0).getSemThemeList().get(0).getId());
        assertEquals("Top>NaturalSciences>Astronomy", r.getConceptList().get(0).getSemThemeList().get(0).getType());
        assertEquals("http://en.wikipedia.org/wiki/PLANET", r.getConceptList().get(0).getSemldList().get(0));
        assertEquals("sumo:Planet", r.getConceptList().get(0).getSemldList().get(1));
    }


    @Test @Throttle
    public void testTopicsRequestInterfaceLanguage() throws IOException, Request.ParameterValidationException {
        TopicsResponse r = TopicsRequest
                .build(MEANINGCLOUD_KEY, Request.Language.ES)
                .withText("Londres")
                .withInterfaceLanguage(Request.Language.ES)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(1, r.getEntityList().size());
        assertEquals("Londres", r.getEntityList().get(0).getForm());
        assertEquals("Londres", r.getEntityList().get(0).getVariantList().get(0).getForm());
        assertEquals("Inglaterra", r.getEntityList().get(0).getSemGeoList().get(0).getAdm1().getForm());
        assertEquals("Greater London", r.getEntityList().get(0).getSemGeoList().get(0).getAdm2().getForm());
        assertEquals("0404ea4d6c", r.getEntityList().get(0).getSemGeoList().get(0).getContinent().getId());
        assertEquals("Reino Unido", r.getEntityList().get(0).getSemGeoList().get(0).getCountry().getForm());
        assertEquals("d29f412b4b", r.getEntityList().get(0).getSemGeoList().get(0).getCountry().getId());
        assertEquals("ISO3166-1-a2", r.getEntityList().get(0).getSemGeoList().get(0).getCountry().getStandardList().get(0).getId());
        assertEquals("GBR", r.getEntityList().get(0).getSemGeoList().get(0).getCountry().getStandardList().get(1).getValue());
    }

    @Test @Throttle
    public void testTopicsRequestLanguageAuto() throws IOException, Request.ParameterValidationException {
        TopicsResponse r = TopicsRequest
                .build(MEANINGCLOUD_KEY, Request.Language.AUTO)
                .withText("Londres")
                .withInterfaceLanguage(Request.Language.ES)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(1, r.getEntityList().size());
        assertEquals("Londres", r.getEntityList().get(0).getForm());
        assertEquals("Londres", r.getEntityList().get(0).getVariantList().get(0).getForm());
        assertEquals("Inglaterra", r.getEntityList().get(0).getSemGeoList().get(0).getAdm1().getForm());
        assertEquals("Greater London", r.getEntityList().get(0).getSemGeoList().get(0).getAdm2().getForm());
        assertEquals("0404ea4d6c", r.getEntityList().get(0).getSemGeoList().get(0).getContinent().getId());
        assertEquals("Reino Unido", r.getEntityList().get(0).getSemGeoList().get(0).getCountry().getForm());
        assertEquals("d29f412b4b", r.getEntityList().get(0).getSemGeoList().get(0).getCountry().getId());
        assertEquals("ISO3166-1-a2", r.getEntityList().get(0).getSemGeoList().get(0).getCountry().getStandardList().get(0).getId());
        assertEquals("GBR", r.getEntityList().get(0).getSemGeoList().get(0).getCountry().getStandardList().get(1).getValue());
    }


    @Test @Throttle
    public void testTopicsRequestNamedEntities() throws IOException, Request.ParameterValidationException {
        TopicsResponse r = TopicsRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("Star Trek is an American science fiction entertainment franchise based on the television series created by Gene Roddenberry")
                .withTopicsToDetect(Request.TopicType.NAMED_ENTITIES)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(2, r.getEntityList().size());
        assertEquals("Star Trek", r.getEntityList().get(0).getForm());
        assertEquals("Star Trek", r.getEntityList().get(0).getVariantList().get(0).getForm());
        assertEquals("instance", r.getEntityList().get(0).getSementity().getSemEntityClass());
        assertEquals("sumo:MotionPicture", r.getEntityList().get(0).getSemldList().get(0));
        assertEquals("ODTHEME_CINEMA", r.getEntityList().get(0).getSemThemeList().get(0).getId());
    }


    @Test @Throttle
    public void testTopicsRequestConcept() throws IOException, Request.ParameterValidationException {
        TopicsResponse r = TopicsRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("Star Trek is an American science fiction entertainment franchise based on the television series created by Gene Roddenberry")
                .withTopicsToDetect(Request.TopicType.CONCEPTS)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(5, r.getConceptList().size());
        assertEquals("American", r.getConceptList().get(0).getForm());
        assertEquals("class", r.getConceptList().get(0).getSementity().getSemEntityClass());
        assertEquals("America", r.getConceptList().get(0).getSemGeoList().get(0).getContinent().getForm());
        assertEquals("sumo:Entity", r.getConceptList().get(0).getSemldList().get(0));
        assertEquals("American", r.getConceptList().get(0).getVariantList().get(0).getForm());
        assertEquals(100, r.getConceptList().get(0).getRelevance());
    }


    @Test @Throttle
    public void testTopicsRequestTimeExpression() throws IOException, Request.ParameterValidationException {
        TopicsResponse r = TopicsRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("Saturday")
                .withTopicsToDetect(Request.TopicType.TIME_EXPRESSIONS)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(1, r.getTimeExpressionList().size());
        assertEquals("Saturday", r.getTimeExpressionList().get(0).getForm());
        assertEquals("|||s|||||||", r.getTimeExpressionList().get(0).getNormalizedForm());
        assertEquals("weekday", r.getTimeExpressionList().get(0).getPrecision());
        assertEquals(0, r.getTimeExpressionList().get(0).getInip());
        assertEquals(7, r.getTimeExpressionList().get(0).getEndp());
    }


    @Test @Throttle
    public void testTopicsRequestMoneyExpression() throws IOException, Request.ParameterValidationException {
        TopicsResponse r = TopicsRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("2$ millions")
                .withTopicsToDetect(Request.TopicType.MONEY_EXPRESSIONS)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(1, r.getMoneyExpressionList().size());
        assertEquals("2$ millions", r.getMoneyExpressionList().get(0).getForm());
        assertEquals("2", r.getMoneyExpressionList().get(0).getAmountForm());
        assertEquals("2", r.getMoneyExpressionList().get(0).getNumericValue());
        assertEquals("USD", r.getMoneyExpressionList().get(0).getCurrency());
        assertEquals(0, r.getMoneyExpressionList().get(0).getInip());
        assertEquals(10, r.getMoneyExpressionList().get(0).getEndp());
    }


    @Test @Throttle
    public void testTopicsRequestQuantityExpression() throws IOException, Request.ParameterValidationException {
        TopicsResponse r = TopicsRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("one cup of water")
                .withTopicsToDetect(Request.TopicType.QUANTITY_EXPRESSIONS)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(1, r.getQuantityExpressionList().size());
        assertEquals("one cup of water", r.getQuantityExpressionList().get(0).getForm());
        assertEquals("one", r.getQuantityExpressionList().get(0).getAmountForm());
        assertEquals("1", r.getQuantityExpressionList().get(0).getNumericValue());
        assertEquals("cup", r.getQuantityExpressionList().get(0).getUnit());
        assertEquals(0, r.getQuantityExpressionList().get(0).getInip());
        assertEquals(15, r.getQuantityExpressionList().get(0).getEndp());
    }


    @Test @Throttle
    public void testTopicsRequestOtherExpression() throws IOException, Request.ParameterValidationException {
        TopicsResponse r = TopicsRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("DR2017")
                .withTopicsToDetect(Request.TopicType.OTHER_EXPRESSIONS)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(1, r.getOtherExpressionList().size());
        assertEquals("DR2017", r.getOtherExpressionList().get(0).getForm());
        assertEquals("flight number", r.getOtherExpressionList().get(0).getType());
        assertEquals(0, r.getOtherExpressionList().get(0).getInip());
        assertEquals(5, r.getOtherExpressionList().get(0).getEndp());
    }


    @Test @Throttle
    public void testTopicsRequestQuotation() throws IOException, Request.ParameterValidationException {
        TopicsResponse r = TopicsRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("He said it was fantastic")
                .withTopicsToDetect(Request.TopicType.QUOTATIONS)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(1, r.getQuotationList().size());
        assertEquals("it was fantastic", r.getQuotationList().get(0).getForm());
        assertEquals(8, r.getQuotationList().get(0).getInip());
        assertEquals(23, r.getQuotationList().get(0).getEndp());
    }


    @Test @Throttle
    public void testTopicsRequestRelations() throws IOException, Request.ParameterValidationException {
        TopicsResponse r = TopicsRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("I have a lot of money")
                .withTopicsToDetect(Request.TopicType.RELATIONS)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(1, r.getRelationList().size());
        assertEquals("I have a lot of money", r.getRelationList().get(0).getForm());
        assertEquals(0, r.getRelationList().get(0).getInip());
        assertEquals(20, r.getRelationList().get(0).getEndp());
    }


    @Test @Throttle
    public void testTopicsRequestAll() throws IOException, Request.ParameterValidationException {
        TopicsResponse r = TopicsRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("London")
                .withTopicsToDetect(Request.TopicType.ALL)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(2, r.getEntityList().size());
        assertEquals("London", r.getEntityList().get(0).getForm());
        assertEquals("United Kingdom", r.getEntityList().get(0).getSemGeoList().get(0).getCountry().getForm());
        assertEquals("GBR", r.getEntityList().get(0).getSemGeoList().get(0).getCountry().getStandardList().get(1).getValue());
    }


    @Test @Throttle
    public void testTopicsRequestBellCharacter() throws IOException, Request.ParameterValidationException {
        TopicsResponse r = TopicsRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("London\\Madrid")
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(2, r.getEntityList().size());
        assertEquals("London", r.getEntityList().get(0).getForm());
        assertEquals("Madrid", r.getEntityList().get(1).getForm());
    }


    @Test @Throttle
    public void testTopicsRequestWhitFile() throws IOException, Request.ParameterValidationException {
        File file = createTemporaryFileWithContents("Hola Mundo.");

        TopicsResponse r = TopicsRequest
                .build(MEANINGCLOUD_KEY, Request.Language.ES)
                .withFile(file)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(1, r.getEntityList().size());
        assertEquals("Mundo", r.getEntityList().get(0).getForm());
        assertEquals("instance", r.getEntityList().get(0).getSementity().getSemEntityClass());
        assertEquals("Top>Person>LastName", r.getEntityList().get(0).getSementity().getType());
        assertEquals("sumo:LastName", r.getEntityList().get(0).getSemldList().get(0));
        assertEquals(5, r.getEntityList().get(0).getVariantList().get(0).getInip());
        assertEquals(100, r.getEntityList().get(0).getRelevance());
    }


    @Test @Throttle
    public void testTopicsRequestWhitURL() throws IOException, Request.ParameterValidationException {
        URL url = new URL("https://www.google.com");
        TopicsResponse r = TopicsRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withURL(url)
                .send();

        assertEquals("OK", r.status.msg);
        assertNotNull(r.getEntityList().get(0).getForm());
    }


    @Test
    public void testTopicsRequestWhitDictionary() throws IOException, Request.ParameterValidationException {
        class TestTransport implements Transport {
            @Override
            public String send(Endpoint endpoint, Map<String, String> params) throws IOException {
                assertEquals(params.get("ud"), "a|b|c");
                return "{}";
            }
        }
        TopicsResponse r = TopicsRequest
                .build(new TestTransport(), MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("Something")
                .withDictionary("a", "b", "c")
                .send();
    }

    @Test
    public void emptyLists() throws IOException, Request.ParameterValidationException {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\",\"remaining_credits\":\"39998\"}}";
        TopicsResponse r = TopicsResponse.from(response);
        assertNotNull(r.getEntityList());
        assertEquals(0, r.getEntityList().size());
    }
}
