package nbp.client;

import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import nbp.model.CurrencyRates;

public class NBPClient {

	private static WebTarget webTarget;

	public NBPClient() {

		// utworzenie obiektu klasy Client
		Client client = ClientBuilder.newClient();

		// konstruowanie adresu URI
		URI uri = UriBuilder.fromUri("http://api.nbp.pl/api/exchangerates/rates/").queryParam("format", "xml").build();

		// reprezentacja zasobu
		webTarget = client.target(uri);
	}

	public String getXML(String table, String currencyCode, String topCount) {
		// ustawienie dalszej œcie¿ki do zasobu
		webTarget = webTarget.path(table).path(currencyCode).path("last").path(topCount);

		// wywo³anie ¿¹dania i odebranie odpowiedzi
		String xmlAnswer = webTarget.request().accept(MediaType.TEXT_XML).get(String.class);

		return xmlAnswer;
	}
	
	public CurrencyRates getCurrencyInstance(String table, String currencyCode, String topCount) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(nbp.model.CurrencyRates.class, nbp.model.Rate.class);
	    Unmarshaller unmarsh = jc.createUnmarshaller();
	    
	    String xml = this.getXML(table, currencyCode, topCount);
	    StringReader reader = new StringReader(xml);
	    return (CurrencyRates) unmarsh.unmarshal(reader);
	}

}
