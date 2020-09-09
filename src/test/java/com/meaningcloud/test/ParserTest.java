package com.meaningcloud.test;

import com.meaningcloud.ParserRequest;
import com.meaningcloud.ParserResponse;
import com.meaningcloud.Request;
import com.meaningcloud.test.junit.Throttle;
import com.meaningcloud.test.junit.Throttling;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Throttling.class)
public class ParserTest extends TestSuper {


    /***** test Basic Parser *****/


    @Test
    public void testBasicParser() {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\"},\"token_list\":[{\"type\":\"sentence\",\"id\":\"23\",\"inip\":\"0\",\"endp\":\"122\",\"style\":{\"isBold\":\"no\",\"isItalics\":\"no\",\"isUnderlined\":\"no\",\"isTitle\":\"no\"},\"separation\":\"A\",\"quote_level\":\"0\",\"affected_by_negation\":\"no\",\"token_list\":[{\"type\":\"phrase\",\"form\":\"Star Trek is an American science fiction entertainment franchise based on the television series created by Gene Roddenberry\",\"id\":\"35\",\"inip\":\"0\",\"endp\":\"122\",\"style\":{\"isBold\":\"no\",\"isItalics\":\"no\",\"isUnderlined\":\"no\",\"isTitle\":\"no\"},\"separation\":\"_\",\"quote_level\":\"0\",\"affected_by_negation\":\"no\",\"analysis_list\":[{\"tag\":\"Z-----------\",\"lemma\":\"*\",\"original_form\":\"Star Trek is an American science fiction entertainment franchise based on the television series created by Gene Roddenberry\"}],\"token_list\":[{\"type\":\"phrase\",\"form\":\"Star Trek\",\"id\":\"27\",\"inip\":\"0\",\"endp\":\"8\",\"style\":{\"isBold\":\"no\",\"isItalics\":\"no\",\"isUnderlined\":\"no\",\"isTitle\":\"no\"},\"separation\":\"_\",\"quote_level\":\"0\",\"affected_by_negation\":\"no\",\"head\":\"21\",\"syntactic_tree_relation_list\":[{\"id\":\"3\",\"type\":\"isSubject\"}],\"analysis_list\":[{\"tag\":\"GN-S3S--\",\"lemma\":\"Star Trek\",\"original_form\":\"Star Trek\"}],\"token_list\":[{\"form\":\"Star Trek\",\"id\":\"21\",\"inip\":\"0\",\"endp\":\"8\",\"style\":{\"isBold\":\"no\",\"isItalics\":\"no\",\"isUnderlined\":\"no\",\"isTitle\":\"no\"},\"separation\":\"_\",\"quote_level\":\"0\",\"affected_by_negation\":\"no\",\"analysis_list\":[{\"tag\":\"NP-S-N-\",\"lemma\":\"Star Trek\",\"original_form\":\"Star Trek\",\"sense_id_list\":[{\"sense_id\":\"d3560f7525\"}]}],\"sense_list\":[{\"id\":\"d3560f7525\",\"form\":\"Star Trek\",\"info\":\"sementity\\/class=instance@fiction=nonfiction@id=ODENTITY_MOVIE@type=Top>Product>CulturalProduct>Movie\\tsemld_list=sumo:MotionPicture\\tsemtheme_list\\/id=ODTHEME_CINEMA@type=Top>Arts>Cinema\"}]}]},{\"form\":\"is\",\"id\":\"3\",\"inip\":\"10\",\"endp\":\"11\",\"style\":{\"isBold\":\"no\",\"isItalics\":\"no\",\"isUnderlined\":\"no\",\"isTitle\":\"no\"},\"separation\":\"1\",\"quote_level\":\"0\",\"affected_by_negation\":\"no\",\"syntactic_tree_relation_list\":[{\"id\":\"27\",\"type\":\"iof_isSubject\"},{\"id\":\"28\",\"type\":\"iof_isAttribute\"}],\"analysis_list\":[{\"tag\":\"VI-S3PSA-N-N9\",\"lemma\":\"be\",\"original_form\":\"is\"}]},{\"type\":\"phrase\",\"form\":\"an American science fiction entertainment franchise based on the television series created by Gene Roddenberry\",\"id\":\"28\",\"inip\":\"13\",\"endp\":\"122\",\"style\":{\"isBold\":\"no\",\"isItalics\":\"no\",\"isUnderlined\":\"no\",\"isTitle\":\"no\"},\"separation\":\"1\",\"quote_level\":\"0\",\"affected_by_negation\":\"no\",\"head\":\"9\",\"syntactic_tree_relation_list\":[{\"id\":\"3\",\"type\":\"isAttribute\"}],\"analysis_list\":[{\"tag\":\"GN-S3A--\",\"lemma\":\"franchise\",\"original_form\":\"an American science fiction entertainment franchise\"}],\"token_list\":[{\"form\":\"an\",\"id\":\"4\",\"inip\":\"13\",\"endp\":\"14\",\"style\":{\"isBold\":\"no\",\"isItalics\":\"no\",\"isUnderlined\":\"no\",\"isTitle\":\"no\"},\"separation\":\"1\",\"quote_level\":\"0\",\"affected_by_negation\":\"no\",\"analysis_list\":[{\"tag\":\"QD-SPN8\",\"lemma\":\"an\",\"original_form\":\"an\"}]},{\"form\":\"American\",\"id\":\"5\",\"inip\":\"16\",\"endp\":\"23\",\"style\":{\"isBold\":\"no\",\"isItalics\":\"no\",\"isUnderlined\":\"no\",\"isTitle\":\"no\"},\"separation\":\"1\",\"quote_level\":\"0\",\"affected_by_negation\":\"no\",\"analysis_list\":[{\"tag\":\"AP-N-\",\"lemma\":\"American\",\"original_form\":\"American\",\"sense_id_list\":[{\"sense_id\":\"0863fc32fb\"},{\"sense_id\":\"593cbceb1f\"}]}],\"sense_list\":[{\"id\":\"0863fc32fb\",\"form\":\"American\",\"info\":\"sementity\\/class=class@fiction=nonfiction@id=ODENTITY_TOP@type=Top\\tsemgeo_list\\/continent=America#id:33fc13e6dd\\tsemld_list=sumo:Entity\"},{\"id\":\"593cbceb1f\",\"form\":\"American\",\"info\":\"sementity\\/class=class@fiction=nonfiction@id=ODENTITY_TOP@type=Top\\tsemgeo_list\\/continent=America#id:33fc13e6dd@country=United States#id:beac1b545b#ISO3166-1-a2:US#ISO3166-1-a3:USA\\tsemgeo_list\\/continent=America#id:33fc13e6dd\\tsemld_list=sumo:Entity\"}]},{\"form\":\"science fiction\",\"id\":\"20\",\"inip\":\"25\",\"endp\":\"39\",\"style\":{\"isBold\":\"no\",\"isItalics\":\"no\",\"isUnderlined\":\"no\",\"isTitle\":\"no\"},\"separation\":\"1\",\"quote_level\":\"0\",\"affected_by_negation\":\"no\",\"analysis_list\":[{\"tag\":\"NC-S-N-\",\"lemma\":\"science fiction\",\"original_form\":\"science fiction\",\"sense_id_list\":[{\"sense_id\":\"f11d891073\"}]}],\"sense_list\":[{\"id\":\"f11d891073\",\"form\":\"science fiction\",\"info\":\"sementity\\/class=class@fiction=nonfiction@id=ODENTITY_TOP@type=Top\\tsemld_list=http:\\/\\/en.wikipedia.org\\/wiki\\/Science_fiction|http:\\/\\/ar.wikipedia.org\\/wiki\\/خيال_علمي|http:\\/\\/ca.wikipedia.org\\/wiki\\/Ciència-ficció|http:\\/\\/cs.wikipedia.org\\/wiki\\/Science_fiction|http:\\/\\/da.wikipedia.org\\/wiki\\/Science_fiction|http:\\/\\/de.wikipedia.org\\/wiki\\/Science-Fiction|http:\\/\\/es.wikipedia.org\\/wiki\\/Ciencia_ficción|http:\\/\\/fi.wikipedia.org\\/wiki\\/Science_fiction|http:\\/\\/fr.wikipedia.org\\/wiki\\/Science-fiction|http:\\/\\/he.wikipedia.org\\/wiki\\/מדע_בדיוני|http:\\/\\/hi.wikipedia.org\\/wiki\\/विज्ञान_कथा_साहित्य|http:\\/\\/id.wikipedia.org\\/wiki\\/Fiksi_ilmiah|http:\\/\\/it.wikipedia.org\\/wiki\\/Fantascienza|http:\\/\\/ja.wikipedia.org\\/wiki\\/サイエンス・フィクション|http:\\/\\/ko.wikipedia.org\\/wiki\\/SF_(장르)|http:\\/\\/nl.wikipedia.org\\/wiki\\/Sciencefiction|http:\\/\\/no.wikipedia.org\\/wiki\\/Science_fiction|http:\\/\\/pl.wikipedia.org\\/wiki\\/Fantastyka_naukowa|http:\\/\\/pt.wikipedia.org\\/wiki\\/Ficção_científica|http:\\/\\/ro.wikipedia.org\\/wiki\\/Literatură_științifico-fantastică|http:\\/\\/ru.wikipedia.org\\/wiki\\/Научная_фантастика|http:\\/\\/sv.wikipedia.org\\/wiki\\/Science_fiction|http:\\/\\/th.wikipedia.org\\/wiki\\/บันเทิงคดีแนววิทยาศาสตร์|http:\\/\\/tr.wikipedia.org\\/wiki\\/Bilimkurgu|http:\\/\\/zh.wikipedia.org\\/wiki\\/科學幻想|http:\\/\\/d-nb.info\\/gnd\\/4054021-2|sumo:Entity\\tsemtheme_list\\/id=ODTHEME_CINEMA@type=Top>Arts>Cinema\"}]},{\"form\":\"entertainment\",\"id\":\"8\",\"inip\":\"41\",\"endp\":\"53\",\"style\":{\"isBold\":\"no\",\"isItalics\":\"no\",\"isUnderlined\":\"no\",\"isTitle\":\"no\"},\"separation\":\"1\",\"quote_level\":\"0\",\"affected_by_negation\":\"no\",\"analysis_list\":[{\"tag\":\"NC-S-N4\",\"lemma\":\"entertainment\",\"original_form\":\"entertainment\",\"sense_id_list\":[{\"sense_id\":\"d80a2f37ad\"}]}],\"sense_list\":[{\"id\":\"d80a2f37ad\",\"form\":\"entertainment\",\"info\":\"sementity\\/class=class@fiction=nonfiction@id=ODENTITY_TOP@type=Top\\tsemld_list=http:\\/\\/en.wikipedia.org\\/wiki\\/Entertainment|http:\\/\\/ar.wikipedia.org\\/wiki\\/ترفيه|http:\\/\\/ca.wikipedia.org\\/wiki\\/Entreteniment|http:\\/\\/cs.wikipedia.org\\/wiki\\/Zábava|http:\\/\\/da.wikipedia.org\\/wiki\\/Underholdning|http:\\/\\/es.wikipedia.org\\/wiki\\/Entretenimiento|http:\\/\\/fi.wikipedia.org\\/wiki\\/Viihde|http:\\/\\/fr.wikipedia.org\\/wiki\\/Divertissement|http:\\/\\/he.wikipedia.org\\/wiki\\/בידור|http:\\/\\/hi.wikipedia.org\\/wiki\\/मनोरंजन|http:\\/\\/id.wikipedia.org\\/wiki\\/Hiburan|http:\\/\\/it.wikipedia.org\\/wiki\\/Intrattenimento|http:\\/\\/ja.wikipedia.org\\/wiki\\/エンターテインメント|http:\\/\\/ko.wikipedia.org\\/wiki\\/오락|http:\\/\\/nl.wikipedia.org\\/wiki\\/Amusement|http:\\/\\/no.wikipedia.org\\/wiki\\/Underholdning|http:\\/\\/pl.wikipedia.org\\/wiki\\/Rozrywka|http:\\/\\/pt.wikipedia.org\\/wiki\\/Entretenimento|http:\\/\\/ro.wikipedia.org\\/wiki\\/Divertisment|http:\\/\\/ru.wikipedia.org\\/wiki\\/Развлечение|http:\\/\\/sv.wikipedia.org\\/wiki\\/Underhållning|http:\\/\\/th.wikipedia.org\\/wiki\\/การบันเทิง|http:\\/\\/tr.wikipedia.org\\/wiki\\/Eğlence|http:\\/\\/zh.wikipedia.org\\/wiki\\/娛樂|sumo:Entity\"}]},{\"form\":\"franchise\",\"id\":\"9\",\"inip\":\"55\",\"endp\":\"63\",\"style\":{\"isBold\":\"no\",\"isItalics\":\"no\",\"isUnderlined\":\"no\",\"isTitle\":\"no\"},\"separation\":\"1\",\"quote_level\":\"0\",\"affected_by_negation\":\"no\",\"analysis_list\":[{\"tag\":\"NC-S-N2\",\"lemma\":\"franchise\",\"original_form\":\"franchise\",\"sense_id_list\":[{\"sense_id\":\"750db1060d\"}]},{\"tag\":\"NC-S-N2\",\"lemma\":\"franchise\",\"original_form\":\"franchise\"}],\"sense_list\":[{\"id\":\"750db1060d\",\"form\":\"franchise\",\"info\":\"sementity\\/class=class@fiction=nonfiction@id=ODENTITY_TOP@type=Top\\tsemld_list=sumo:Entity\\tsemtheme_list\\/id=ODTHEME_ECONOMY@type=Top>SocialSciences>Economy\"}]},{\"type\":\"phrase\",\"form\":\"based on the television series created by Gene Roddenberry\",\"id\":\"34\",\"inip\":\"65\",\"endp\":\"122\",\"style\":{\"isBold\":\"no\",\"isItalics\":\"no\",\"isUnderlined\":\"no\",\"isTitle\":\"no\"},\"separation\":\"1\",\"quote_level\":\"0\",\"affected_by_negation\":\"no\",\"analysis_list\":[{\"tag\":\"ZA----PA----\",\"lemma\":\"*\",\"original_form\":\"based on the television series created by Gene Roddenberry\"}],\"token_list\":[{\"form\":\"based\",\"id\":\"10\",\"inip\":\"65\",\"endp\":\"69\",\"style\":{\"isBold\":\"no\",\"isItalics\":\"no\",\"isUnderlined\":\"no\",\"isTitle\":\"no\"},\"separation\":\"1\",\"quote_level\":\"0\",\"affected_by_negation\":\"no\",\"syntactic_tree_relation_list\":[{\"id\":\"32\",\"type\":\"iof_isComplement\"}],\"analysis_list\":[{\"tag\":\"VP---ASA-N-N5\",\"lemma\":\"base\",\"original_form\":\"based\",\"sense_id_list\":[{\"sense_id\":\"ODENTITY_INVESTIGATING\"},{\"sense_id\":\"ODENTITY_TRANSLOCATION\"},{\"sense_id\":\"ODENTITY_REASONING\"}]}],\"sense_list\":[{\"id\":\"ODENTITY_INVESTIGATING\",\"form\":\"base\",\"info\":\"sementity\\/id=ODENTITY_INVESTIGATING@type=Top>Process>IntentionalProcess>IntentionalPsychologicalProcess>Investigating\\tsemld_list=sumo:IntentionalProcess\"},{\"id\":\"ODENTITY_REASONING\",\"form\":\"base\",\"info\":\"sementity\\/id=ODENTITY_REASONING@type=Top>Process>IntentionalProcess>IntentionalPsychologicalProcess>Reasoning\\tsemld_list=sumo:IntentionalProcess\"},{\"id\":\"ODENTITY_TRANSLOCATION\",\"form\":\"base\",\"info\":\"sementity\\/id=ODENTITY_TRANSLOCATION@type=Top>Process>Motion>Translocation\\tsemld_list=sumo:Entity\"}]},{\"type\":\"phrase\",\"form\":\"on the television series created by Gene Roddenberry\",\"id\":\"32\",\"inip\":\"71\",\"endp\":\"122\",\"style\":{\"isBold\":\"no\",\"isItalics\":\"no\",\"isUnderlined\":\"no\",\"isTitle\":\"no\"},\"separation\":\"1\",\"quote_level\":\"0\",\"affected_by_negation\":\"no\",\"head\":\"11\",\"syntactic_tree_relation_list\":[{\"id\":\"10\",\"type\":\"isComplement\"}],\"analysis_list\":[{\"tag\":\"GY---C--\",\"lemma\":\"on\",\"original_form\":\"on the television series\"}],\"token_list\":[{\"form\":\"on\",\"id\":\"11\",\"inip\":\"71\",\"endp\":\"72\",\"style\":{\"isBold\":\"no\",\"isItalics\":\"no\",\"isUnderlined\":\"no\",\"isTitle\":\"no\"},\"separation\":\"1\",\"quote_level\":\"0\",\"affected_by_negation\":\"no\",\"analysis_list\":[{\"tag\":\"YN7\",\"lemma\":\"on\",\"original_form\":\"on\"}]},{\"type\":\"phrase\",\"form\":\"the television series\",\"id\":\"29\",\"inip\":\"74\",\"endp\":\"94\",\"style\":{\"isBold\":\"no\",\"isItalics\":\"no\",\"isUnderlined\":\"no\",\"isTitle\":\"no\"},\"separation\":\"1\",\"quote_level\":\"0\",\"affected_by_negation\":\"no\",\"head\":\"14\",\"analysis_list\":[{\"tag\":\"GN-S3---\",\"lemma\":\"serie\",\"original_form\":\"the television series\"},{\"tag\":\"GN-S3---\",\"lemma\":\"series\",\"original_form\":\"the television series\"},{\"tag\":\"GN-P3---\",\"lemma\":\"serie\",\"original_form\":\"the television series\"},{\"tag\":\"GN-P3---\",\"lemma\":\"series\",\"original_form\":\"the television series\"}],\"token_list\":[{\"form\":\"the\",\"id\":\"12\",\"inip\":\"74\",\"endp\":\"76\",\"style\":{\"isBold\":\"no\",\"isItalics\":\"no\",\"isUnderlined\":\"no\",\"isTitle\":\"no\"},\"separation\":\"1\",\"quote_level\":\"0\",\"affected_by_negation\":\"no\",\"analysis_list\":[{\"tag\":\"TD-SN9\",\"lemma\":\"the\",\"original_form\":\"the\"},{\"tag\":\"TD-PN9\",\"lemma\":\"the\",\"original_form\":\"the\"}]},{\"form\":\"television\",\"id\":\"13\",\"inip\":\"78\",\"endp\":\"87\",\"style\":{\"isBold\":\"no\",\"isItalics\":\"no\",\"isUnderlined\":\"no\",\"isTitle\":\"no\"},\"separation\":\"1\",\"quote_level\":\"0\",\"affected_by_negation\":\"no\",\"analysis_list\":[{\"tag\":\"NC-S-N5\",\"lemma\":\"television\",\"original_form\":\"television\"}]},{\"form\":\"series\",\"id\":\"14\",\"inip\":\"89\",\"endp\":\"94\",\"style\":{\"isBold\":\"no\",\"isItalics\":\"no\",\"isUnderlined\":\"no\",\"isTitle\":\"no\"},\"separation\":\"1\",\"quote_level\":\"0\",\"affected_by_negation\":\"no\",\"analysis_list\":[{\"tag\":\"NC-S-N6\",\"lemma\":\"series\",\"original_form\":\"series\",\"sense_id_list\":[{\"sense_id\":\"790684373b\"}]},{\"tag\":\"NC-P-N6\",\"lemma\":\"series\",\"original_form\":\"series\",\"sense_id_list\":[{\"sense_id\":\"790684373b\"}]},{\"tag\":\"NC-P-N-\",\"lemma\":\"serie\",\"original_form\":\"series\"}],\"sense_list\":[{\"id\":\"790684373b\",\"form\":\"series\",\"info\":\"sementity\\/class=class@fiction=nonfiction@id=ODENTITY_PERIOD@type=Top>Timex>Period\\tsemld_list=http:\\/\\/en.wikipedia.org\\/wiki\\/Series|http:\\/\\/ar.wikipedia.org\\/wiki\\/سلسلة|http:\\/\\/ca.wikipedia.org\\/wiki\\/Sèrie|http:\\/\\/cs.wikipedia.org\\/wiki\\/Série|http:\\/\\/da.wikipedia.org\\/wiki\\/Serie|http:\\/\\/de.wikipedia.org\\/wiki\\/Serie|http:\\/\\/es.wikipedia.org\\/wiki\\/Serie|http:\\/\\/fr.wikipedia.org\\/wiki\\/Série|http:\\/\\/it.wikipedia.org\\/wiki\\/Serie_(disambigua)|http:\\/\\/ja.wikipedia.org\\/wiki\\/シリーズ|http:\\/\\/ko.wikipedia.org\\/wiki\\/시리즈|http:\\/\\/nl.wikipedia.org\\/wiki\\/Serie|http:\\/\\/pt.wikipedia.org\\/wiki\\/Série|http:\\/\\/ro.wikipedia.org\\/wiki\\/Serie|http:\\/\\/ru.wikipedia.org\\/wiki\\/Ряд|http:\\/\\/sv.wikipedia.org\\/wiki\\/Serie|sumo:TimeInterval\"}]}]},{\"type\":\"phrase\",\"form\":\"created by Gene Roddenberry\",\"id\":\"33\",\"inip\":\"96\",\"endp\":\"122\",\"style\":{\"isBold\":\"no\",\"isItalics\":\"no\",\"isUnderlined\":\"no\",\"isTitle\":\"no\"},\"separation\":\"1\",\"quote_level\":\"0\",\"affected_by_negation\":\"no\",\"analysis_list\":[{\"tag\":\"ZA----PA----\",\"lemma\":\"*\",\"original_form\":\"created by Gene Roddenberry\"}],\"token_list\":[{\"form\":\"created\",\"id\":\"15\",\"inip\":\"96\",\"endp\":\"102\",\"style\":{\"isBold\":\"no\",\"isItalics\":\"no\",\"isUnderlined\":\"no\",\"isTitle\":\"no\"},\"separation\":\"1\",\"quote_level\":\"0\",\"affected_by_negation\":\"no\",\"syntactic_tree_relation_list\":[{\"id\":\"31\",\"type\":\"iof_isAgentComplement\"}],\"analysis_list\":[{\"tag\":\"VP---ASA-N-N6\",\"lemma\":\"create\",\"original_form\":\"created\",\"sense_id_list\":[{\"sense_id\":\"ODENTITY_CONTENT_DEVELOPMENT\"},{\"sense_id\":\"ODENTITY_INTENTIONAL_PROCESS\"},{\"sense_id\":\"ODENTITY_MAKING\"},{\"sense_id\":\"ODENTITY_PROCESS\"}]}],\"sense_list\":[{\"id\":\"ODENTITY_CONTENT_DEVELOPMENT\",\"form\":\"create\",\"info\":\"sementity\\/id=ODENTITY_CONTENT_DEVELOPMENT@type=Top>Process>IntentionalProcess>ContentDevelopment\\tsemld_list=sumo:IntentionalProcess\"},{\"id\":\"ODENTITY_INTENTIONAL_PROCESS\",\"form\":\"create\",\"info\":\"sementity\\/id=ODENTITY_INTENTIONAL_PROCESS@type=Top>Process>IntentionalProcess\\tsemld_list=sumo:IntentionalProcess\"},{\"id\":\"ODENTITY_MAKING\",\"form\":\"create\",\"info\":\"sementity\\/id=ODENTITY_MAKING@type=Top>Process>IntentionalProcess>Making\\tsemld_list=sumo:IntentionalProcess\"},{\"id\":\"ODENTITY_PROCESS\",\"form\":\"create\",\"info\":\"sementity\\/id=ODENTITY_PROCESS@type=Top>Process\\tsemld_list=sumo:Entity\"}]},{\"type\":\"phrase\",\"form\":\"by Gene Roddenberry\",\"id\":\"31\",\"inip\":\"104\",\"endp\":\"122\",\"style\":{\"isBold\":\"no\",\"isItalics\":\"no\",\"isUnderlined\":\"no\",\"isTitle\":\"no\"},\"separation\":\"1\",\"quote_level\":\"0\",\"affected_by_negation\":\"no\",\"head\":\"16\",\"syntactic_tree_relation_list\":[{\"id\":\"15\",\"type\":\"isAgentComplement\"}],\"analysis_list\":[{\"tag\":\"GY---G--\",\"lemma\":\"by\",\"original_form\":\"by Gene Roddenberry\"}],\"token_list\":[{\"form\":\"by\",\"id\":\"16\",\"inip\":\"104\",\"endp\":\"105\",\"style\":{\"isBold\":\"no\",\"isItalics\":\"no\",\"isUnderlined\":\"no\",\"isTitle\":\"no\"},\"separation\":\"1\",\"quote_level\":\"0\",\"affected_by_negation\":\"no\",\"analysis_list\":[{\"tag\":\"YN4\",\"lemma\":\"by\",\"original_form\":\"by\"}]},{\"type\":\"phrase\",\"form\":\"Gene Roddenberry\",\"id\":\"30\",\"inip\":\"107\",\"endp\":\"122\",\"style\":{\"isBold\":\"no\",\"isItalics\":\"no\",\"isUnderlined\":\"no\",\"isTitle\":\"no\"},\"separation\":\"1\",\"quote_level\":\"0\",\"affected_by_negation\":\"no\",\"head\":\"22\",\"analysis_list\":[{\"tag\":\"GNUU3---\",\"lemma\":\"Gene Roddenberry\",\"original_form\":\"Gene Roddenberry\"}],\"token_list\":[{\"type\":\"multiword\",\"form\":\"Gene Roddenberry\",\"id\":\"22\",\"inip\":\"107\",\"endp\":\"122\",\"style\":{\"isBold\":\"no\",\"isItalics\":\"no\",\"isUnderlined\":\"no\",\"isTitle\":\"no\"},\"separation\":\"1\",\"quote_level\":\"0\",\"affected_by_negation\":\"no\",\"head\":\"17\",\"analysis_list\":[{\"tag\":\"NPUU-N-\",\"lemma\":\"Gene Roddenberry\",\"original_form\":\"Gene Roddenberry\",\"sense_id_list\":[{\"sense_id\":\"__839835886671894845\"}]}],\"sense_list\":[{\"id\":\"__839835886671894845\",\"form\":\"Gene Roddenberry\",\"info\":\"sementity\\/class=instance@type=Top>Person>FullName@confidence=unknown\"}]}]}]}]}]}]}]}]}]}]}";

        ParserResponse r = ParserResponse.from(response);

        assertEquals("OK", r.status.msg);
        assertEquals("sentence", r.getTokenList().get(0).getType());
        assertEquals(23, r.getTokenList().get(0).getId());
        assertEquals(0, r.getTokenList().get(0).getInip());
        assertEquals(122, r.getTokenList().get(0).getEndp());
        assertEquals("no", r.getTokenList().get(0).getStyle().getIsBold());
        assertEquals("no", r.getTokenList().get(0).getStyle().getIsItalics());
        assertEquals("no", r.getTokenList().get(0).getStyle().getIsUnderlined());
        assertEquals("no", r.getTokenList().get(0).getStyle().getIsTitle());
        assertEquals("A", r.getTokenList().get(0).getSeparation());
        assertEquals(0, r.getTokenList().get(0).getQuoteLevel());
        assertEquals("no", r.getTokenList().get(0).getAffectedByNegation());

        assertEquals("phrase", r.getTokenList().get(0).getTokenList().get(0).getType());
        assertEquals("Star Trek is an American science fiction entertainment franchise based on the television series created by Gene Roddenberry", r.getTokenList().get(0).getTokenList().get(0).getForm());
        assertEquals(35, r.getTokenList().get(0).getTokenList().get(0).getId());
        assertEquals(0, r.getTokenList().get(0).getTokenList().get(0).getInip());
        assertEquals(122, r.getTokenList().get(0).getTokenList().get(0).getEndp());
        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getStyle().getIsBold());
        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getStyle().getIsItalics());
        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getStyle().getIsUnderlined());
        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getStyle().getIsTitle());
        assertEquals("_", r.getTokenList().get(0).getTokenList().get(0).getSeparation());
        assertEquals(0, r.getTokenList().get(0).getTokenList().get(0).getQuoteLevel());
        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getAffectedByNegation());

        assertEquals("Z-----------", r.getTokenList().get(0).getTokenList().get(0).getAnalysisList().get(0).getTag());
        assertEquals("*", r.getTokenList().get(0).getTokenList().get(0).getAnalysisList().get(0).getLemma());
        assertEquals("Star Trek is an American science fiction entertainment franchise based on the television series created by Gene Roddenberry", r.getTokenList().get(0).getTokenList().get(0).getAnalysisList().get(0).getOriginalForm());

        assertEquals("phrase", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getType());
        assertEquals("Star Trek", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getForm());
        assertEquals(27, r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getId());
        assertEquals(0, r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getInip());
        assertEquals(8, r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getEndp());

        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getStyle().getIsBold());
        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getStyle().getIsItalics());
        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getStyle().getIsUnderlined());
        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getStyle().getIsTitle());

        assertEquals("_", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getSeparation());
        assertEquals(0, r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getQuoteLevel());
        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getAffectedByNegation());
        assertEquals(21, r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getHead());

        assertEquals(3, r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getSyntacticTreeRelationList().get(0).getId());
        assertEquals("isSubject", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getSyntacticTreeRelationList().get(0).getType());

        assertEquals("GN-S3S--", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getAnalysisList().get(0).getTag());
        assertEquals("Star Trek", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getAnalysisList().get(0).getLemma());
        assertEquals("Star Trek", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getAnalysisList().get(0).getOriginalForm());

        assertEquals("Star Trek", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getForm());
        assertEquals(21, r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getId());
        assertEquals(0, r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getInip());
        assertEquals(8, r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getEndp());
        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getStyle().getIsBold());
        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getStyle().getIsItalics());
        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getStyle().getIsUnderlined());
        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getStyle().getIsTitle());
        assertEquals("_", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getSeparation());
        assertEquals(0, r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getQuoteLevel());
        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getAffectedByNegation());

        assertEquals("NP-S-N-", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getAnalysisList().get(0).getTag());
        assertEquals("Star Trek", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getAnalysisList().get(0).getLemma());
        assertEquals("Star Trek", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getAnalysisList().get(0).getOriginalForm());
        assertEquals("d3560f7525", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getAnalysisList().get(0).getSenseIdList().get(0).getSenseId());

        assertEquals("d3560f7525", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getSenseList().get(0).getId());
        assertEquals("Star Trek", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getSenseList().get(0).getForm());
        assertEquals("sementity/class=instance@fiction=nonfiction@id=ODENTITY_MOVIE@type=Top>Product>CulturalProduct>Movie\tsemld_list=sumo:MotionPicture\tsemtheme_list/id=ODTHEME_CINEMA@type=Top>Arts>Cinema", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getSenseList().get(0).getInfo());
    }


    /***** test Parser Request *****/


    @Test @Throttle
    public void testParserRequest() throws IOException, Request.ParameterValidationException {
        ParserResponse r = ParserRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("Star Trek is an American science fiction entertainment franchise based on the television series created by Gene Roddenberry")
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals("sentence", r.getTokenList().get(0).getType());
        assertEquals(23, r.getTokenList().get(0).getId());
        assertEquals(0, r.getTokenList().get(0).getInip());
        assertEquals(122, r.getTokenList().get(0).getEndp());
        assertEquals("no", r.getTokenList().get(0).getStyle().getIsBold());
        assertEquals("no", r.getTokenList().get(0).getStyle().getIsItalics());
        assertEquals("no", r.getTokenList().get(0).getStyle().getIsUnderlined());
        assertEquals("no", r.getTokenList().get(0).getStyle().getIsTitle());
        assertEquals("A", r.getTokenList().get(0).getSeparation());
        assertEquals(0, r.getTokenList().get(0).getQuoteLevel());
        assertEquals("no", r.getTokenList().get(0).getAffectedByNegation());

        assertEquals("phrase", r.getTokenList().get(0).getTokenList().get(0).getType());
        assertEquals("Star Trek is an American science fiction entertainment franchise based on the television series created by Gene Roddenberry", r.getTokenList().get(0).getTokenList().get(0).getForm());
        assertEquals(35, r.getTokenList().get(0).getTokenList().get(0).getId());
        assertEquals(0, r.getTokenList().get(0).getTokenList().get(0).getInip());
        assertEquals(122, r.getTokenList().get(0).getTokenList().get(0).getEndp());
        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getStyle().getIsBold());
        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getStyle().getIsItalics());
        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getStyle().getIsUnderlined());
        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getStyle().getIsTitle());
        assertEquals("_", r.getTokenList().get(0).getTokenList().get(0).getSeparation());
        assertEquals(0, r.getTokenList().get(0).getTokenList().get(0).getQuoteLevel());
        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getAffectedByNegation());

        assertEquals("Z-----------", r.getTokenList().get(0).getTokenList().get(0).getAnalysisList().get(0).getTag());
        assertEquals("*", r.getTokenList().get(0).getTokenList().get(0).getAnalysisList().get(0).getLemma());
        assertEquals("Star Trek is an American science fiction entertainment franchise based on the television series created by Gene Roddenberry", r.getTokenList().get(0).getTokenList().get(0).getAnalysisList().get(0).getOriginalForm());

        assertEquals("phrase", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getType());
        assertEquals("Star Trek", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getForm());
        assertEquals(27, r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getId());
        assertEquals(0, r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getInip());
        assertEquals(8, r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getEndp());

        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getStyle().getIsBold());
        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getStyle().getIsItalics());
        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getStyle().getIsUnderlined());
        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getStyle().getIsTitle());

        assertEquals("_", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getSeparation());
        assertEquals(0, r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getQuoteLevel());
        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getAffectedByNegation());
        //assertEquals(21, r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getHead());

        assertEquals(3, r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getSyntacticTreeRelationList().get(0).getId());
        assertEquals("isSubject", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getSyntacticTreeRelationList().get(0).getType());

        assertEquals("GN-S3S--", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getAnalysisList().get(0).getTag());
        assertEquals("Star Trek", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getAnalysisList().get(0).getLemma());
        assertEquals("Star Trek", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getAnalysisList().get(0).getOriginalForm());

        assertEquals("Star Trek", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getForm());
        //assertEquals(21, r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getId());
        assertEquals(0, r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getInip());
        assertEquals(8, r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getEndp());
        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getStyle().getIsBold());
        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getStyle().getIsItalics());
        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getStyle().getIsUnderlined());
        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getStyle().getIsTitle());
        assertEquals("_", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getSeparation());
        assertEquals(0, r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getQuoteLevel());
        assertEquals("no", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getAffectedByNegation());

        assertEquals("NP-S-N-", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getAnalysisList().get(0).getTag());
        assertEquals("Star Trek", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getAnalysisList().get(0).getLemma());
        assertEquals("Star Trek", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getAnalysisList().get(0).getOriginalForm());
        assertEquals("d3560f7525", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getAnalysisList().get(0).getSenseIdList().get(0).getSenseId());

        assertEquals("d3560f7525", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getSenseList().get(0).getId());
        assertEquals("Star Trek", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getSenseList().get(0).getForm());
        assertEquals("sementity/class=instance@fiction=nonfiction@id=ODENTITY_MOVIE@type=Top>Product>CulturalProduct>Movie\tsemld_list=sumo:MotionPicture\tsemtheme_list/id=ODTHEME_CINEMA@type=Top>Arts>Cinema", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getSenseList().get(0).getInfo());
    }

    
    @Test @Throttle
    public void testParserIlangRequest() throws IOException, Request.ParameterValidationException {
        ParserResponse r = ParserRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("Star Trek is an American science fiction entertainment franchise based on the television series created by Gene Roddenberry")
                .withInterfaceLanguage(Request.ILanguage.ES)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals("americano", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(2).getTokenList().get(1).getSenseList().get(0).getForm());
        assertEquals("sementity/class=class@fiction=nonfiction@id=ODENTITY_TOP@type=Top\tsemgeo_list/continent=América#id:33fc13e6dd\tsemld_list=sumo:Entity", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(2).getTokenList().get(1).getSenseList().get(0).getInfo());
        assertEquals("estadounidense", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(2).getTokenList().get(1).getSenseList().get(1).getForm());
        assertEquals("sementity/class=class@fiction=nonfiction@id=ODENTITY_TOP@type=Top\tsemgeo_list/continent=América#id:33fc13e6dd@country=Estados Unidos#id:beac1b545b#ISO3166-1-a2:US#ISO3166-1-a3:USA\tsemgeo_list/continent=América#id:33fc13e6dd\tsemld_list=sumo:Entity", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(2).getTokenList().get(1).getSenseList().get(1).getInfo());
    }

    @Test @Throttle
    public void testParserLangAutoRequest() throws IOException, Request.ParameterValidationException {
        ParserResponse r = ParserRequest
                .build(MEANINGCLOUD_KEY, Request.Language.AUTO)
                .withText("Star Trek is an American science fiction entertainment franchise based on the television series created by Gene Roddenberry")
                .withInterfaceLanguage(Request.ILanguage.ES)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals("americano", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(2).getTokenList().get(1).getSenseList().get(0).getForm());
        assertEquals("sementity/class=class@fiction=nonfiction@id=ODENTITY_TOP@type=Top\tsemgeo_list/continent=América#id:33fc13e6dd\tsemld_list=sumo:Entity", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(2).getTokenList().get(1).getSenseList().get(0).getInfo());
        assertEquals("estadounidense", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(2).getTokenList().get(1).getSenseList().get(1).getForm());
        assertEquals("sementity/class=class@fiction=nonfiction@id=ODENTITY_TOP@type=Top\tsemgeo_list/continent=América#id:33fc13e6dd@country=Estados Unidos#id:beac1b545b#ISO3166-1-a2:US#ISO3166-1-a3:USA\tsemgeo_list/continent=América#id:33fc13e6dd\tsemld_list=sumo:Entity", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(2).getTokenList().get(1).getSenseList().get(1).getInfo());
    }

    
    @Test @Throttle
    public void testParserVerboseRequest() throws IOException, Request.ParameterValidationException {
        ParserResponse r = ParserRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("Star Trek is an American science fiction entertainment franchise based on the television series created by Gene Roddenberry")
                .withVerbose(true)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals("SEG", r.getTokenList().get(0).getTokenList().get(0).getAnalysisList().get(0).getOrigin());
        assertEquals("Z-----------", r.getTokenList().get(0).getTokenList().get(0).getAnalysisList().get(0).getTag());
        assertEquals("*", r.getTokenList().get(0).getTokenList().get(0).getAnalysisList().get(0).getLemma());
        assertEquals("Star Trek is an American science fiction entertainment franchise based on the television series created by Gene Roddenberry", r.getTokenList().get(0).getTokenList().get(0).getAnalysisList().get(0).getOriginalForm());
        assertEquals("clause", r.getTokenList().get(0).getTokenList().get(0).getAnalysisList().get(0).getTagInfo());

        assertEquals("SEG", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(2).getTokenList().get(3).getAnalysisList().get(0).getOrigin());
        assertEquals("NC-S-N4", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(2).getTokenList().get(3).getAnalysisList().get(0).getTag());
        assertEquals("entertainment", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(2).getTokenList().get(3).getAnalysisList().get(0).getLemma());
        assertEquals("entertainment", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(2).getTokenList().get(3).getAnalysisList().get(0).getOriginalForm());
        assertEquals("noun, common, singular, medium-low frequency word", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(2).getTokenList().get(3).getAnalysisList().get(0).getTagInfo());

    }

    
    @Test @Throttle
    public void testParserTxtfRequest() throws IOException, Request.ParameterValidationException {
        ParserResponse r = ParserRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "  <head>\n" +
                        "    <title>This is a title</title>\n" +
                        "  </head>\n" +
                        "  <body>\n" +
                        "    <p>Hello world!</p>\n" +
                        "  </body>\n" +
                        "</html>")
                .withTextFormat(Request.TextFormat.MARKUP)
                .send();

        assertEquals("OK", r.status.msg);
        assertNotEquals("<!", r.getTokenList().get(0).getTokenList().get(0).getForm());
    }


    /*
     * List of topic types to extract.
     * Possible values are:
     * e named entities,
     * c concepts,
     * t time expressions,
     * m money expressions,
     * n quantity expressions,
     * o other expressions,
     * q quotations,
     * r relations,
     * a all
     */

    
    @Test @Throttle
    public void testParserRequestEntity() throws IOException, Request.ParameterValidationException {
        ParserResponse r = ParserRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("Star Trek is an American science fiction entertainment franchise based on the television series created by Gene Roddenberry")
                .withTopicsToDetect(Request.TopicType.NAMED_ENTITIES)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals("Star Trek", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTopicList().getEntityList().get(0).getForm());
        assertEquals("d3560f7525", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTopicList().getEntityList().get(0).getId());
        assertEquals("instance", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTopicList().getEntityList().get(0).getSementity().getSementityClass());
        assertEquals("nonfiction", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTopicList().getEntityList().get(0).getSementity().getFiction());
        assertEquals("ODENTITY_MOVIE", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTopicList().getEntityList().get(0).getSementity().getId());
        assertEquals("Top>Product>CulturalProduct>Movie", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTopicList().getEntityList().get(0).getSementity().getType());
        assertEquals("ODTHEME_CINEMA", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTopicList().getEntityList().get(0).getSemthemeList().get(0).getId());
        assertEquals("Top>Arts>Cinema", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTopicList().getEntityList().get(0).getSemthemeList().get(0).getType());
    }

    
    @Test @Throttle
    public void testParserRequestConcept() throws IOException, Request.ParameterValidationException {
        ParserResponse r = ParserRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("LOS ANGELES — “Black Panther,” Marvel’s African-oriented comic book adaptation, is on track to become the most successful movie at the box office for a film with a primarily black cast, generating an estimated $192 million over its three-day opening weekend.")
                .withTopicsToDetect(Request.TopicType.CONCEPTS)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals("panther", r.getTokenList().get(1).getTokenList().get(0).getTokenList().get(1).getTokenList().get(2).getTopicList().getConceptList().get(0).getForm());
        assertEquals("7b295537af", r.getTokenList().get(1).getTokenList().get(0).getTokenList().get(1).getTokenList().get(2).getTopicList().getConceptList().get(0).getId());
        assertEquals("class", r.getTokenList().get(1).getTokenList().get(0).getTokenList().get(1).getTokenList().get(2).getTopicList().getConceptList().get(0).getSementity().getSementityClass());
        assertEquals("nonfiction", r.getTokenList().get(1).getTokenList().get(0).getTokenList().get(1).getTokenList().get(2).getTopicList().getConceptList().get(0).getSementity().getFiction());
        assertEquals("ODENTITY_MAMMAL", r.getTokenList().get(1).getTokenList().get(0).getTokenList().get(1).getTokenList().get(2).getTopicList().getConceptList().get(0).getSementity().getId());
        assertEquals("Top>LivingThing>Animal>Vertebrate>Mammal", r.getTokenList().get(1).getTokenList().get(0).getTokenList().get(1).getTokenList().get(2).getTopicList().getConceptList().get(0).getSementity().getType());
        assertEquals("ODTHEME_ZOOLOGY", r.getTokenList().get(1).getTokenList().get(0).getTokenList().get(1).getTokenList().get(2).getTopicList().getConceptList().get(0).getSemthemeList().get(0).getId());
        assertEquals("Top>NaturalSciences>Zoology", r.getTokenList().get(1).getTokenList().get(0).getTokenList().get(1).getTokenList().get(2).getTopicList().getConceptList().get(0).getSemthemeList().get(0).getType());
    }

    
    @Test @Throttle
    public void testParserRequestTimeExpression() throws IOException, Request.ParameterValidationException {
        ParserResponse r = ParserRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("Saturday")
                .withTopicsToDetect(Request.TopicType.TIME_EXPRESSIONS)
                .send();

        assertEquals("OK", r.status.msg);
        ParserResponse.Time timeExpression  = r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTopicList().getTimeExpressionList().get(0);
        assertEquals("Saturday", timeExpression.getForm());
        assertEquals("|||s|||||||", timeExpression.getNormalizedForm());
        assertEquals("weekday", timeExpression.getPrecision());
        assertEquals(0, timeExpression.getInip());
        assertEquals(7, timeExpression.getEndp());
    }

    
    @Test @Throttle
    public void testParserRequestMoneyExpression() throws IOException, Request.ParameterValidationException {
        ParserResponse r = ParserRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("2$ millions")
                .withTopicsToDetect(Request.TopicType.MONEY_EXPRESSIONS)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals("2$ millions", r.getTokenList().get(0).getTokenList().get(0).getForm());
        assertEquals("2", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(1).getTopicList().getMoneyExpressionList().get(0).getAmountForm());
        assertEquals(2, r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(1).getTopicList().getMoneyExpressionList().get(0).getNumericValue());
        assertEquals("USD", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(1).getTopicList().getMoneyExpressionList().get(0).getCurrency());
        assertEquals(0, r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(1).getTopicList().getMoneyExpressionList().get(0).getInip());
    }

    
    @Test @Throttle
    public void testParserRequestQuantityExpression() throws IOException, Request.ParameterValidationException {
        ParserResponse r = ParserRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("one cup of water")
                .withTopicsToDetect(Request.TopicType.QUANTITY_EXPRESSIONS)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals("one cup of water", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTopicList().getQuantityExpressionList().get(0).getForm());
        assertEquals("one", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTopicList().getQuantityExpressionList().get(0).getAmountForm());
        assertEquals(1, r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTopicList().getQuantityExpressionList().get(0).getNumericValue());
        assertEquals("cup", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTopicList().getQuantityExpressionList().get(0).getUnit());
        assertEquals(0, r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTopicList().getQuantityExpressionList().get(0).getInip());
        assertEquals(15, r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getTopicList().getQuantityExpressionList().get(0).getEndp());
    }

    
    @Test @Throttle
    public void testParserRequestOtherExpression() throws IOException, Request.ParameterValidationException {
        ParserResponse r = ParserRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("DR2017")
                .withTopicsToDetect(Request.TopicType.OTHER_EXPRESSIONS)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals("DR2017", r.getTokenList().get(0).getTokenList().get(0).getTopicList().getOtherExpressionList().get(0).getForm());
        assertEquals("flight number", r.getTokenList().get(0).getTokenList().get(0).getTopicList().getOtherExpressionList().get(0).getType());
        assertEquals(0, r.getTokenList().get(0).getTokenList().get(0).getTopicList().getOtherExpressionList().get(0).getInip());
        assertEquals(5, r.getTokenList().get(0).getTokenList().get(0).getTopicList().getOtherExpressionList().get(0).getEndp());
    }

    
    @Test @Throttle
    public void testParserRequestQuotationsExpression() throws IOException, Request.ParameterValidationException {
        ParserResponse r = ParserRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("He said it was fantastic")
                .withTopicsToDetect(Request.TopicType.QUOTATIONS)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals("it was fantastic", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(2).getTokenList().get(0).getTokenList().get(0).getTopicList().getQuotationList().get(0).getForm());
        assertEquals("He", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(2).getTokenList().get(0).getTokenList().get(0).getTopicList().getQuotationList().get(0).getWho().getForm());
        assertEquals("he", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(2).getTokenList().get(0).getTokenList().get(0).getTopicList().getQuotationList().get(0).getWho().getLemma());
        assertEquals("said", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(2).getTokenList().get(0).getTokenList().get(0).getTopicList().getQuotationList().get(0).getVerb().getForm());
        assertEquals("say", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(2).getTokenList().get(0).getTokenList().get(0).getTopicList().getQuotationList().get(0).getVerb().getLemma());
        assertEquals(8, r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(2).getTokenList().get(0).getTokenList().get(0).getTopicList().getQuotationList().get(0).getInip());
        assertEquals(23, r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(2).getTokenList().get(0).getTokenList().get(0).getTopicList().getQuotationList().get(0).getEndp());
    }

    
    @Test @Throttle
    public void testParserRequestRelations() throws IOException, Request.ParameterValidationException {
        ParserResponse r = ParserRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("I have a lot of money")
                .withTopicsToDetect(Request.TopicType.RELATIONS)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals("I have a lot of money", r.getTokenList().get(0).getTokenList().get(0).getTopicList().getRelationList().get(0).getForm());
        assertEquals(0, r.getTokenList().get(0).getTokenList().get(0).getTopicList().getRelationList().get(0).getInip());
        assertEquals(20, r.getTokenList().get(0).getTokenList().get(0).getTopicList().getRelationList().get(0).getEndp());
        assertEquals("have", r.getTokenList().get(0).getTokenList().get(0).getTopicList().getRelationList().get(0).getVerb().getForm());
        assertEquals("a lot of money", r.getTokenList().get(0).getTokenList().get(0).getTopicList().getRelationList().get(0).getComplementList().get(0).getForm());
        assertEquals("isDirectObject", r.getTokenList().get(0).getTokenList().get(0).getTopicList().getRelationList().get(0).getComplementList().get(0).getType());
        assertEquals(1, r.getTokenList().get(0).getTokenList().get(0).getTopicList().getRelationList().get(0).getDegree());
    }

    
    @Test @Throttle
    public void testParserRequestAll() throws IOException, Request.ParameterValidationException {
        ParserResponse r = ParserRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("Since you’re here …\n" +
                        "… we have a small favour to ask. More people are reading the Guardian than ever but advertising revenues across the media are falling fast. And unlike many news organisations, we haven’t put up a paywall – we want to keep our journalism as open as we can. So you can see why we need to ask for your help. The Guardian’s independent, investigative journalism takes a lot of time, money and hard work to produce. But we do it because we believe our perspective matters – because it might well be your perspective, too." +
                        "Six winners were still sufficient for Elliott to edge out Willie Mullins, the top trainer in four of the previous five seasons, on a countback of placed horses. Punters will hope for a better showing from Elliott’s most fancied runners, however, with both Samcro, a 4-5 chance for the Ballymore Novice Hurdle, and Apple’s Jade, 4-6 for the Mares’ Hurdle, among the “30 to 40 horses” the trainer will send to the west country.")
                .withTopicsToDetect(Request.TopicType.ALL)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals("Since you’re here … … we have a small favour to ask", r.getTokenList().get(0).getTokenList().get(0).getForm());
        assertEquals("you", r.getTokenList().get(0).getTokenList().get(0).getTopicList().getRelationList().get(0).getSubject().getForm());
        assertEquals("isTimeComplement", r.getTokenList().get(0).getTokenList().get(0).getTopicList().getRelationList().get(0).getComplementList().get(0).getType());
        assertEquals(1, r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getHead());
        assertEquals("PP-S2NN8", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(1).getTokenList().get(0).getAnalysisList().get(0).getTag());
        assertEquals("PRONHUMAN", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(1).getTokenList().get(0).getAnalysisList().get(0).getSenseIdList().get(0).getSenseId());
        assertEquals("iof_isTimeComplement", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(2).getSyntacticTreeRelationList().get(0).getType());
        assertEquals("sementity/id=ODENTITY_LINGUISTIC_COMMUNICATION@type=Top>Process>ContentBearingProcess>CommunicationProcess>LinguisticCommunication\tsemld_list=sumo:Entity", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(8).getTokenList().get(3).getTokenList().get(0).getSenseList().get(0).getInfo());
    }

    
    @Test @Throttle
    public void testParserRequestTimeRef() throws IOException, Request.ParameterValidationException {
        ParserResponse r = ParserRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("A number of other flights from the west London hub were delayed. BA is offering passengers booked on short-haul flights between Monday and Friday the chance to re-book for travel on a later date up to 21 March free of charge.")
                .withTimeRef("21 March")
                .send();

        assertEquals("OK", r.status.msg);
    }


    @Test @Throttle
    public void testParserRequestWithFile() throws IOException, Request.ParameterValidationException {
        File file = createTemporaryFileWithContents("Star Trek is an American science fiction entertainment franchise based on the television series created by Gene Roddenberry");

        ParserResponse r = ParserRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withFile(file)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals("isSubject", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getSyntacticTreeRelationList().get(0).getType());
        assertEquals("Star Trek", r.getTokenList().get(0).getTokenList().get(0).getTokenList().get(0).getAnalysisList().get(0).getOriginalForm());
    }

    
    @Test @Throttle
    public void testParserRequestWithURL() throws IOException, Request.ParameterValidationException {
        URL url = new URL("https://www.google.com");
        ParserResponse r = ParserRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withURL(url)
                .send();

        assertEquals("OK", r.status.msg);
        assertNotNull(r.getTokenList().get(0).getTokenList().get(0).getForm());
    }

    
    @Test @Throttle
    public void testParserRequestTextFormat() throws IOException, Request.ParameterValidationException {

        ParserResponse r = ParserRequest
                .build(MEANINGCLOUD_KEY, Request.Language.ES)
                .withText("<b>Texto en negrita.</b>" +
                        "<i>Texto en cursiva.</i>" +
                        "<u>Texto subrayado.</u>" +
                        "<h1>Título</h1>")
                .withTextFormat(Request.TextFormat.MARKUP)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals("Texto en negrita", r.getTokenList().get(0).getTokenList().get(0).getForm());
        assertEquals("yes", r.getTokenList().get(0).getTokenList().get(0).getStyle().getIsBold());

        assertEquals("Texto en cursiva", r.getTokenList().get(1).getTokenList().get(0).getForm());
        assertEquals("yes", r.getTokenList().get(1).getTokenList().get(0).getStyle().getIsItalics());

        assertEquals("Texto subrayado", r.getTokenList().get(2).getTokenList().get(0).getForm());
        assertEquals("yes", r.getTokenList().get(2).getTokenList().get(0).getStyle().getIsUnderlined());

        assertEquals("Título", r.getTokenList().get(3).getTokenList().get(0).getForm());
        assertEquals("yes", r.getTokenList().get(3).getTokenList().get(0).getStyle().getIsTitle());
    }

    
    @Test @Throttle
    public void testLemmatization() throws IOException, Request.ParameterValidationException {
        ParserResponse r = ParserRequest
                .build(MEANINGCLOUD_KEY, Request.Language.EN)
                .withText("This is not okay")
                .send();

        List<ParserResponse.Lemma> lemmas = r.lemmatize();
        assertEquals(3, lemmas.size());
        assertEquals("this", lemmas.get(0).getLemma());
        assertEquals("DP-SN3", lemmas.get(0).getTag());
        assertEquals("be", lemmas.get(1).getLemma());
        assertEquals("VI-S3PSA-NNN9", lemmas.get(1).getTag());
        assertEquals("yes", lemmas.get(1).getToken().getAffectedByNegation());
        assertEquals("okay", lemmas.get(2).getLemma());
        assertEquals("AP-N4", lemmas.get(2).getTag());
    }
}
