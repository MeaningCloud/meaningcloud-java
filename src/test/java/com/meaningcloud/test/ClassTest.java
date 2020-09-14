package com.meaningcloud.test;

import com.meaningcloud.ClassRequest;
import com.meaningcloud.ClassResponse;
import com.meaningcloud.Request;
import com.meaningcloud.test.junit.Throttle;
import com.meaningcloud.test.junit.Throttling;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(Throttling.class)
public class ClassTest extends TestSuper {


    /***** test Basic Class *****/


    /*
     * models for text classification:
     * 'BusinessRep_es',
     * 'EUROVOC_es_ca',
     * 'IPTC_ca',
     * 'IPTC_en',
     * 'IPTC_es',
     * 'IPTC_fr',
     * 'IPTC_it',
     * 'IPTC_pt',
     * 'SocialMedia_en',
     * 'SocialMedia_es',
     */


    @Test
    public void testBasicClassIPTC_en() {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\"},\"category_list\":[{\"code\":\"01005000\",\"label\":\"arts, culture and entertainment - cinema\",\"abs_relevance\":\"0.30986246\",\"relevance\":\"100\"}]}";

        ClassResponse r = ClassResponse.from(response);

        assertEquals("OK", r.status.msg);
        assertEquals("01005000", r.getCategoryList().get(0).getCode());
        assertEquals("arts, culture and entertainment - cinema", r.getCategoryList().get(0).getLabel());
        assertEquals(100, r.getCategoryList().get(0).getRelevance(), 0);
    }


    @Test
    public void testBasicClassIPTC_es() {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\"},\"category_list\":[{\"code\":\"04004000\",\"label\":\"economía, negocios y finanzas - construcción e inmobiliaria\",\"abs_relevance\":\"2.1708984\",\"relevance\":\"100\"}]}";

        ClassResponse r = ClassResponse.from(response);

        assertEquals("OK", r.status.msg);
        assertEquals("04004000", r.getCategoryList().get(0).getCode());
        assertEquals("economía, negocios y finanzas - construcción e inmobiliaria", r.getCategoryList().get(0).getLabel());
        assertEquals(100, r.getCategoryList().get(0).getRelevance(), 0);
    }


    @Test
    public void testBasicClassIPTC_ca() {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\"},\"category_list\":[{\"code\":\"15054000\",\"label\":\"esport - futbol\",\"abs_relevance\":\"4.0622873\",\"relevance\":\"100\"}]}";

        ClassResponse r = ClassResponse.from(response);

        assertEquals("OK", r.status.msg);
        assertEquals("15054000", r.getCategoryList().get(0).getCode());
        assertEquals("esport - futbol", r.getCategoryList().get(0).getLabel());
        assertEquals(100, r.getCategoryList().get(0).getRelevance(), 0);
    }


    @Test
    public void testBasicClassIPTC_fr() {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\"},\"category_list\":[{\"code\":\"03010001\",\"label\":\"Désastres et accidents - Accident de transport - Accident de la route\",\"abs_relevance\":\"1.1279346\",\"relevance\":\"100\"}]}";

        ClassResponse r = ClassResponse.from(response);

        assertEquals("OK", r.status.msg);
        assertEquals("03010001", r.getCategoryList().get(0).getCode());
        assertEquals("Désastres et accidents - Accident de transport - Accident de la route", r.getCategoryList().get(0).getLabel());
        assertEquals(100, r.getCategoryList().get(0).getRelevance(), 0);
    }


    @Test
    public void testBasicClassIPTC_it() {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\"},\"category_list\":[{\"code\":\"03004000\",\"label\":\"Disastri, Incidenti - Incendio\",\"abs_relevance\":\"0.64581525\",\"relevance\":\"100\"},{\"code\":\"04001005\",\"label\":\"Economia, affari e finanza - Agricoltura - Viticoltura\",\"abs_relevance\":\"0.33156806\",\"relevance\":\"51\"}]}";

        ClassResponse r = ClassResponse.from(response);

        assertEquals("OK", r.status.msg);
        assertEquals("03004000", r.getCategoryList().get(0).getCode());
        assertEquals("Disastri, Incidenti - Incendio", r.getCategoryList().get(0).getLabel());
        assertEquals(100, r.getCategoryList().get(0).getRelevance(), 0);
        assertEquals("04001005", r.getCategoryList().get(1).getCode());
        assertEquals("Economia, affari e finanza - Agricoltura - Viticoltura", r.getCategoryList().get(1).getLabel());
        assertEquals(51, r.getCategoryList().get(1).getRelevance(), 0);
    }


    @Test
    public void testBasicClassIPTC_pt() {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\"},\"category_list\":[{\"code\":\"01005000\",\"label\":\"arte, cultura e espetáculos - cinema\",\"abs_relevance\":\"1.1455679\",\"relevance\":\"100\"}]}";

        ClassResponse r = ClassResponse.from(response);

        assertEquals("OK", r.status.msg);
        assertEquals("01005000", r.getCategoryList().get(0).getCode());
        assertEquals("arte, cultura e espetáculos - cinema", r.getCategoryList().get(0).getLabel());
        assertEquals(100, r.getCategoryList().get(0).getRelevance(), 0);
    }


    @Test
    public void testBasicSocialMedia_en() {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\"},\"category_list\":[{\"code\":\"01\",\"label\":\"art and culture\",\"abs_relevance\":\"16.825678\",\"relevance\":\"100\"},{\"code\":\"05\",\"label\":\"education\",\"abs_relevance\":\"13.941779\",\"relevance\":\"83\"}]}";

        ClassResponse r = ClassResponse.from(response);

        assertEquals("OK", r.status.msg);
        assertEquals("01", r.getCategoryList().get(0).getCode());
        assertEquals("art and culture", r.getCategoryList().get(0).getLabel());
        assertEquals(100, r.getCategoryList().get(0).getRelevance(), 0);
        assertEquals("05", r.getCategoryList().get(1).getCode());
        assertEquals("education", r.getCategoryList().get(1).getLabel());
        assertEquals(83, r.getCategoryList().get(1).getRelevance(), 0);
    }


    @Test
    public void testBasicSocialMedia_es() {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\"},\"category_list\":[{\"code\":\"06\",\"label\":\"medio ambiente, meteorología y energía\",\"abs_relevance\":\"14.654539\",\"relevance\":\"100\"},{\"code\":\"12\",\"label\":\"política\",\"abs_relevance\":\"11.541282\",\"relevance\":\"79\"}]}";

        ClassResponse r = ClassResponse.from(response);

        assertEquals("OK", r.status.msg);
        assertEquals("06", r.getCategoryList().get(0).getCode());
        assertEquals("medio ambiente, meteorología y energía", r.getCategoryList().get(0).getLabel());
        assertEquals(100, r.getCategoryList().get(0).getRelevance(), 0);
        assertEquals("12", r.getCategoryList().get(1).getCode());
        assertEquals("política", r.getCategoryList().get(1).getLabel());
        assertEquals(79, r.getCategoryList().get(1).getRelevance(), 0);
    }


    @Test
    public void testBasicBusinessRep_es() {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\"},\"category_list\":[{\"code\":\"71\",\"label\":\"Situación financiera - Potencial de crecimiento futuro\",\"abs_relevance\":\"2.984079\",\"relevance\":\"100\"},{\"code\":\"73\",\"label\":\"Situación financiera - Resultados financieros\",\"abs_relevance\":\"1.6437786\",\"relevance\":\"55\"},{\"code\":\"52\",\"label\":\"Innovación y flexibilidad - Adaptabilidad al cambio\",\"abs_relevance\":\"1.171431\",\"relevance\":\"39\"},{\"code\":\"51\",\"label\":\"Innovación y flexibilidad - Innovación\",\"abs_relevance\":\"1.080382\",\"relevance\":\"36\"},{\"code\":\"43\",\"label\":\"Estrategia y liderazgo - Visión y estrategia\",\"abs_relevance\":\"0.9820231\",\"relevance\":\"33\"}]}";

        ClassResponse r = ClassResponse.from(response);

        assertEquals("OK", r.status.msg);
        assertEquals("71", r.getCategoryList().get(0).getCode());
        assertEquals("Situación financiera - Potencial de crecimiento futuro", r.getCategoryList().get(0).getLabel());
        assertEquals(100, r.getCategoryList().get(0).getRelevance(), 0);
        assertEquals("73", r.getCategoryList().get(1).getCode());
        assertEquals("Situación financiera - Resultados financieros", r.getCategoryList().get(1).getLabel());
        assertEquals(55, r.getCategoryList().get(1).getRelevance(), 0);
    }


    @Test
    public void testBasicEuroVOC_es_ca() {
        String response = "{\"status\":{\"code\":\"0\",\"msg\":\"OK\",\"credits\":\"1\"},\"category_list\":[{\"code\":\"6912\",\"label\":\"túnel* (ca) \\/ túnel (es)\",\"abs_relevance\":\"16.0\",\"relevance\":\"100\"},{\"code\":\"0914\",\"label\":\"europa central i oriental* (ca) \\/ europa central y oriental (es)\",\"abs_relevance\":\"10.0\",\"relevance\":\"63\"},{\"code\":\"0653\",\"label\":\"economia internacional* (ca) \\/ economía internacional (es)\",\"abs_relevance\":\"8.0\",\"relevance\":\"50\"},{\"code\":\"4520\",\"label\":\"transport internacional per carretera* (ca) \\/ transporte internacional por carretera (es)\",\"abs_relevance\":\"6.0\",\"relevance\":\"38\"},{\"code\":\"0469\",\"label\":\"dispositiu de senyalització* (ca) \\/ dispositivo de señalización (es)\",\"abs_relevance\":\"4.0\",\"relevance\":\"25\"},{\"code\":\"0648\",\"label\":\"economia del transport* (ca) \\/ economía del transporte (es)\",\"abs_relevance\":\"4.0\",\"relevance\":\"25\"},{\"code\":\"0864\",\"label\":\"regions d'españa* (ca) \\/ regiones de españa (es)\",\"abs_relevance\":\"4.0\",\"relevance\":\"25\"},{\"code\":\"2506\",\"label\":\"política governamental (ca) \\/ política gubernamental (es)\",\"abs_relevance\":\"4.0\",\"relevance\":\"25\"},{\"code\":\"3483\",\"label\":\"sanció internacional* (ca) \\/ sanción internacional (es)\",\"abs_relevance\":\"4.0\",\"relevance\":\"25\"},{\"code\":\"4519\",\"label\":\"transport internacional* (ca) \\/ transporte internacional (es)\",\"abs_relevance\":\"4.0\",\"relevance\":\"25\"},{\"code\":\"4533\",\"label\":\"transport per carretera (ca) \\/ transporte por carretera (es)\",\"abs_relevance\":\"4.0\",\"relevance\":\"25\"},{\"code\":\"5334\",\"label\":\"comunicació de dades* (ca) \\/ comunicación de datos (es)\",\"abs_relevance\":\"4.0\",\"relevance\":\"25\"},{\"code\":\"0909\",\"label\":\"europa* (ca) \\/ europa (es)\",\"abs_relevance\":\"2.0\",\"relevance\":\"13\"},{\"code\":\"2854\",\"label\":\"província* (ca) \\/ provincia (es)\",\"abs_relevance\":\"2.0\",\"relevance\":\"13\"}]}";

        ClassResponse r = ClassResponse.from(response);

        assertEquals("OK", r.status.msg);
        assertEquals("6912", r.getCategoryList().get(0).getCode());
        assertEquals("túnel* (ca) / túnel (es)", r.getCategoryList().get(0).getLabel());
        assertEquals(100, r.getCategoryList().get(0).getRelevance(), 0);
        assertEquals("0914", r.getCategoryList().get(1).getCode());
        assertEquals("europa central i oriental* (ca) / europa central y oriental (es)", r.getCategoryList().get(1).getLabel());
        assertEquals(63, r.getCategoryList().get(1).getRelevance(), 0);
    }


    /***** test Class Request *****/

    private Set<String> getCategories(ClassResponse r) {
        return r.getCategoryList().stream().map(ClassResponse.Category::getCode).collect(Collectors.toSet());
    }


    @Test @Throttle
    public void testClassRequestIPTC_en() throws IOException, Request.ParameterValidationException {
        ClassResponse r = ClassRequest
                .build(MEANINGCLOUD_KEY, "IPTC_en")
                .withText("LOS ANGELES — After black actors and films that focused on black characters were overlooked for Oscar nominations in 2015 and 2016, the #OscarsSoWhite social media outcry was so fierce that Hollywood was forced to listen. The Academy of Motion Picture Arts and Sciences began a determined diversification effort, and last year there were six black acting nominees, a record.")
                .send();

        assertEquals("OK", r.status.msg);
        Set<String> categories = getCategories(r);
        assertTrue(categories.contains("01005000"));
        assertTrue(categories.contains("01005001"));
    }


    @Test @Throttle
    public void testClassRequestIPTC_es() throws IOException, Request.ParameterValidationException {
        ClassResponse r = ClassRequest
                .build(MEANINGCLOUD_KEY, "IPTC_es")
                .withText("Desde que nos levantamos hasta que nos acostamos, siempre y cuando no haya imprevistos, podríamos ejecutar todos nuestros actos de consumo a través de internet, sin necesidad de personarnos físicamente en el lugar concreto. Por ejemplo, podemos encargar desayuno, comida y cena a plataformas como Deliveroo o Glovo y teletrabajar. La moda también se consume online, igual que el ocio. Podemos ver una peli en Netflix sin necesidad de ir al cine, comprar en Zara desde el salón de casa o pedir la compra del supermercado a Amazon. Por poder, podemos hasta entrenar online sin tener que ir al gimnasio o incluso encontrar pareja a la carta a través de las distintas aplicaciones que existen.«Cualquier producto físico que sea tangible se puede adquirir por internet y recibir en mano y casi todos los servicios, desde un hotel a una peluquera, se pueden reservar o pagar online, aunque estos no siempre se puedan disfrutar desde el ordenador, el móvil o desde el dispositivo correspondiente», señala Ignacio Ochoa, consejero delegado de la consultora Branward.")
                .send();

        assertEquals("OK", r.status.msg);
        Set<String> categories = getCategories(r);
        assertTrue(categories.contains("01027000"));
        assertTrue(categories.contains("04003005"));
    }


    @Test @Throttle
    public void testClassRequestIPTC_ca() throws IOException, Request.ParameterValidationException {
        ClassResponse r = ClassRequest
                .build(MEANINGCLOUD_KEY, "IPTC_ca")
                .withText("Després de cada partit com a local del Barça Lassa a l’Eurolliga, els seguidors de la Penya Meritxell van a sopar al Yaya, un bar situat a la cantonada dels carrers Villarroel i París que tanca la cuina a les tres del matí. Una de les més xerraires del grup és l’Adela Marco, que acostuma a demanar-se un entrepà de truita a la francesa. “Amb poc pa, que si no no me l’acabo”. Menja poc, però la gana pel bàsquet la té intacta.")
                .send();

        assertEquals("OK", r.status.msg);
        Set<String> categories = getCategories(r);
        assertTrue(categories.contains("15008000"));
    }


    @Test @Throttle
    public void testClassRequestIPTC_fr() throws IOException, Request.ParameterValidationException {
        ClassResponse r = ClassRequest
                .build(MEANINGCLOUD_KEY, "IPTC_fr")
                .withText("Le mur entre jeu vidéo et animation ne penche plus, il tombe. Avec la sortie de Dragon Ball FighterZ sur consoles et PC vendredi 26 janvier, spectaculaire adaptation de l’animé culte de la Toei, le monde de la manette se rapproche encore un peu plus d’un des univers qui l’a le plus influencé, bien plus encore que le cinéma : le dessin animé.")
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(1, r.getCategoryList().size());
        Set<String> categories = getCategories(r);
        assertTrue(categories.contains("01025000"));
    }


    @Test @Throttle
    public void testClassRequestIPTC_it() throws IOException, Request.ParameterValidationException {
        ClassResponse r = ClassRequest
                .build(MEANINGCLOUD_KEY, "IPTC_it")
                .withText("Particolarmente affascinante, la Lucca antica custodisce splendidi capolavori.  Svariati sono gli accessi alla città vecchia che consegnano, chi vi acceda, al suo intreccio di strade e vicoli, alle tante piazze e piazzette, ai suoi cortili, ai caffè ed ai ristoranti. E’ da via Vittorio Emanuele II che affondiamo nel cuore della città ed iniziamo la visita al suo centro. Tra edifici storici e botteghe giungiamo nell’alberata piazza Napoleone, la principale della città, nata nel 1806 durante la dominazione napoleonica del Principato di Lucca da parte di Elisa Bonaparte Baciocchi, sorella di Napoleone. Per questo la piazza porta il nome dell’imperatore. Sulla piazza s’affaccia il Palazzo Ducale, detto anche Palazzo Pubblico, che ospita il Museo del Risorgimento, fondato nel 1925 come “Museo della Guerra”. Al suo interno sono custoditi la bandiera del 1821 della Carboneria ed oggetti legati a Giuseppe Mazzini e Giuseppe Garibaldi. Interessante anche l’armeria dove vi sono armi di differenti nazioni e periodi storici. Poi oggetti d’uso quotidiano, quadri, lettere ed abiti.")
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(1, r.getCategoryList().size());
        Set<String> categories = getCategories(r);
        assertTrue(categories.contains("01009000"));
    }


    @Test @Throttle
    public void testClassRequestIPTC_pt() throws IOException, Request.ParameterValidationException {
        ClassResponse r = ClassRequest
                .build(MEANINGCLOUD_KEY, "IPTC_pt")
                .withText("A Direção-Geral de Orçamento (DGO) divulga hoje a síntese de execução orçamental do conjunto de 2017, sendo que o Governo estima que o défice tenha ficado abaixo de 1,4% do PIB, em contabilidade nacional.\n" +
                        "\n" +
                        "Até novembro, o défice das Administrações Públicas foi de 2.083,6 milhões de euros, o que se traduziu na melhoria de 2.325,6 milhões de euros face ao registado em igual período de 2016, quando o défice ficou nos 4.409,2 milhões de euros.\n" +
                        "\n" +
                        "Esta melhoria deveu-se ao efeito conjugado do aumento de 4,3% da receita, que foi superior ao crescimento de 0,8% verificado na despesa.")
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(2, r.getCategoryList().size());
        Set<String> categories = getCategories(r);
        assertTrue(categories.contains("11006000"));
    }


    @Test @Throttle
    public void testClassRequestBusinessRep_es() throws IOException, Request.ParameterValidationException {
        ClassResponse r = ClassRequest
                .build(MEANINGCLOUD_KEY, "BusinessRep_es")
                .withText("9 de cada 10 empresas familiares andaluzas mantiene o aumenta su plantilla en 2017. Además, un 85% de las compañías afirma que realizará inversiones para mejorar la competitividad de su negocio en 2018. Estos son algunos de los datos recogidos en el Barómetro de la Empresa Familiar de Andalucía presentado por CESUR y elaborado por KPMG.")
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(1, r.getCategoryList().size());
        Set<String> categories = getCategories(r);
        assertTrue(categories.contains("71"));
    }


    @Test @Throttle
    public void testClassRequestEUROVOC_es_ca() throws IOException, Request.ParameterValidationException {
        ClassResponse r = ClassRequest
                .build(MEANINGCLOUD_KEY, "EUROVOC_es_ca")
                .withText("9 de cada 10 empresas familiares andaluzas mantiene o aumenta su plantilla en 2017. Además, un 85% de las compañías afirma que realizará inversiones para mejorar la competitividad de su negocio en 2018. Estos son algunos de los datos recogidos en el Barómetro de la Empresa Familiar de Andalucía presentado por CESUR y elaborado por KPMG.")
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(3, r.getCategoryList().size());
        Set<String> categories = getCategories(r);
        assertTrue(categories.contains("0821"));
    }


    @Test @Throttle
    public void testClassRequestSocialMedia_es() throws IOException, Request.ParameterValidationException {
        ClassResponse r = ClassRequest
                .build(MEANINGCLOUD_KEY, "SocialMedia_es")
                .withText("9 de cada 10 empresas familiares andaluzas mantiene o aumenta su plantilla en 2017. Además, un 85% de las compañías afirma que realizará inversiones para mejorar la competitividad de su negocio en 2018. Estos son algunos de los datos recogidos en el Barómetro de la Empresa Familiar de Andalucía presentado por CESUR y elaborado por KPMG.")
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(1, r.getCategoryList().size());
        Set<String> categories = getCategories(r);
        assertTrue(categories.contains("04"));
    }


    @Test @Throttle
    public void testClassRequestSocialMedia_en() throws IOException, Request.ParameterValidationException {
        ClassResponse r = ClassRequest
                .build(MEANINGCLOUD_KEY, "SocialMedia_en")
                .withText("Mark Summers QC told the judge Emma Arbuthnot at Westminster magistrates court on Friday that now that the Swedish case had been dropped the warrant had “lost its purpose and its function”.")
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(1, r.getCategoryList().size());
        Set<String> categories = getCategories(r);
        assertTrue(categories.contains("02"));
    }


    @Test @Throttle
    public void testClassRequestVerboseY() throws IOException, Request.ParameterValidationException {
        ClassResponse r = ClassRequest
                .build(MEANINGCLOUD_KEY, "IPTC_en")
                .withText("The Chloe Grace Moretz film “The Miseducation of Cameron Post” has won the Sundance Film Festival’s grand jury prize.\n" +
                        "\n" +
                        "Awards were announced Saturday evening in Park City, Utah.\n" +
                        "\n" +
                        "Adapted from a 2012 novel by Emily M. Danforth, “The Miseducation of Cameron Post” is about a teenage girl who is sent to a gay conversion center. Moretz dedicated the award to LGBTQ survivors of sexual conversion therapy.")
                .withVerbose(true)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(3, r.getCategoryList().size());
        Set<String> categories = getCategories(r);
        assertTrue(categories.contains("01005000"));
    }


    @Test @Throttle
    public void testClassRequestVerboseN() throws IOException, Request.ParameterValidationException {
        ClassResponse r = ClassRequest
                .build(MEANINGCLOUD_KEY, "SocialMedia_en")
                .withText("Mark Summers QC told the judge Emma Arbuthnot at Westminster magistrates court on Friday that now that the Swedish case had been dropped the warrant had “lost its purpose and its function”.")
                .withVerbose(false)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(1, r.getCategoryList().size());
        Set<String> categories = getCategories(r);
        assertTrue(categories.contains("02"));
    }


    @Test @Throttle
    public void testClassRequestVerboseEmpty() throws IOException, Request.ParameterValidationException {
        ClassResponse r = ClassRequest
                .build(MEANINGCLOUD_KEY, "SocialMedia_en")
                .withText("Mark Summers QC told the judge Emma Arbuthnot at Westminster magistrates court on Friday that now that the Swedish case had been dropped the warrant had “lost its purpose and its function”.")
                .withVerbose(true)
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(1, r.getCategoryList().size());
        Set<String> categories = getCategories(r);
        assertTrue(categories.contains("02"));
    }


    @Test @Throttle
    public void testClassRequestCategoryFilter() throws IOException, Request.ParameterValidationException {
        ClassResponse r = ClassRequest
                .build(MEANINGCLOUD_KEY, "IPTC_es")
                .withText("Avanzaba la tarde y el Rey FelipeVI tenía ya previsto salir de Davos para no volver de madrugada a Zarzuela, pero esperaba pacientemente al presidente francés, Emmanuel Macron, una de las estrellas del Foro Económico Mundial, que tardó bastante más de la cuenta en acudir. Eso dio tiempo holgado al ministro Alfonso Dastis a recuperarse de su impresionante lipotimia de horas antes y la espera mereció la pena. En el tema clave en las relaciones entre ambos países, la angustiosa carencia de interconexiones eléctricas, Macron fue claro en su reunión del 24: «Yo soy un convencido». Prometió ayudar.")
                .withCategoryFilter("04017000")
                .send();

        assertEquals("OK", r.status.msg);
        assertEquals(1, r.getCategoryList().size());
        Set<String> categories = getCategories(r);
        assertTrue(categories.contains("04017000"));
    }


    @Test @Throttle
    public void testClassRequestWithFile() throws IOException, Request.ParameterValidationException {
        File file = createTemporaryFileWithContents("LOS ANGELES — After black actors and films that focused on black characters were overlooked for Oscar nominations in 2015 and 2016, the #OscarsSoWhite social media outcry was so fierce that Hollywood was forced to listen. The Academy of Motion Picture Arts and Sciences began a determined diversification effort, and last year there were six black acting nominees, a record.");

        ClassResponse r = ClassRequest
                .build(MEANINGCLOUD_KEY, "IPTC_en")
                .withFile(file)
                .send();


        assertEquals("OK", r.status.msg);
        assertEquals(2, r.getCategoryList().size());
        Set<String> categories = getCategories(r);
        assertTrue(categories.contains("01005000"));
        assertTrue(categories.contains("01005001"));
    }


    @Test @Throttle
    public void testClassRequestWhitURL() throws IOException, Request.ParameterValidationException {
        URL url = new URL("https://www.nytimes.com/2018/01/25/science/plastic-coral-reefs.html?partner=rss&emc=rss");
        ClassResponse r = ClassRequest
                .build(MEANINGCLOUD_KEY, "IPTC_en")
                .withURL(url)
                .send();

        assertEquals("OK", r.status.msg);
        assertNotNull(r.getCategoryList().get(0).getCode());
    }

    /**
     * IAB is not a Class model anymore, but a Deep Categorization one.
     */
    @Test @Throttle
    public void testIABNotAvailableInClass20() throws IOException, Request.ParameterValidationException {
        ClassResponse r = ClassRequest
                .build(MEANINGCLOUD_KEY, "IAB_en")
                .withText("Hello, world!")
                .send();

        assertEquals("Resource not supported", r.status.msg);
    }

}
