package internet;

import javax.ws.rs.core.MediaType;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String x = CurrencyResource.getResult("C", "usd", Integer.toString(10), MediaType.APPLICATION_XML);
		System.out.println(x);
	}

}
