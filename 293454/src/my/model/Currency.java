package my.model;

import java.io.InputStream;
import java.io.StringWriter;

import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

@XmlRootElement(name = "Waluta")
public class Currency {
	private String name;
	private String code;
	private Double bidMean;
	private Double askMean;
	private Double midMean;

	public Currency(String name, String code, Double bidMean, Double askMean, Double midMean) {
		this.name = name;
		this.code = code;
		this.bidMean = bidMean;
		this.askMean = askMean;
		this.midMean = midMean;
	}

	public Currency() {
		this.name = null;
		this.code = null;
		this.bidMean = null;
		this.askMean = null;
		this.midMean = null;
	}

	@XmlElement(name = "Nazwa_waluty")
	public String getName() {
		return name;
	}

	@XmlElement(name = "Kod_waluty")
	public String getCode() {
		return code;
	}

	@XmlElement(name = "�redni_kurs_ceny_kupna")
	public Double getBidMean() {
		return bidMean;
	}

	@XmlElement(name = "�redni_kurs_ceny_sprzeda�y")
	public Double getAskMean() {
		return askMean;
	}
	
	@XmlElement(name = "�redni_kurs")
	public Double getMidMean() {
		return midMean;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setBidMean(double bidMean) {
		this.bidMean = bidMean;
	}

	public void setAskMean(double askMean) {
		this.askMean = askMean;
	}
	
	public void setMidMean(double midMean) {
		this.midMean = midMean;
	}

	public String toHTML() throws JAXBException, TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        InputStream resourceAsStream = Currency.class.getResourceAsStream("html_template.xsl");
        StreamSource xslt = new StreamSource(resourceAsStream);
        Transformer transformer = factory.newTransformer(xslt);

        JAXBContext jc = JAXBContext.newInstance(Currency.class);
        JAXBSource source = new JAXBSource(jc, this);
		StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);
		return writer.toString();
	}
	
	public String toChosenFormat(String mediaType) throws JAXBException, TransformerException {
		//JAXBContext jc = JAXBContext.newInstance(Currency.class);
		if (mediaType.equals(MediaType.TEXT_HTML)) {
			return toHTML();
		}
		Map<String, Object> properties = new HashMap<>();
		properties.put(JAXBContextProperties.MEDIA_TYPE, mediaType);
		properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);

		JAXBContext jc = JAXBContextFactory.createContext(new Class[] { Currency.class }, properties);

		Marshaller marsh = jc.createMarshaller();
		marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		StringWriter writer = new StringWriter();
		marsh.marshal(this, writer);
		return writer.toString();
	}



}
