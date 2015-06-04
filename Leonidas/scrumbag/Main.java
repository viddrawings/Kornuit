<<<<<<< HEAD
package scrumbag;

public class Main {
	private static Controller controller;

	public static void main(String[] args) {

		String accessToken = "CAAVvLje0a38BAH80qwafgbqAP2X1nSZAYTlWCnCpdwn1UJtb"
				+ "cTOCPUg1YsGiJCzpT1nq1gx4ZBaC7ZCl5ssLdAei4Ql56UZBdEYzy7uVDnQD"
				+ "oCSDOuWRDk8r76ZCglV4tWLh5ns0mdnNLcuMjcczC0PmnzWcnSVPcHmziHxq"
				+ "xwdknTdjE5jKE8ZAPOEhKak8zLITef4Pb0CoHZAaZCkO11vo";

		controller = new Controller();
		String winner = controller.makeSuggestion(accessToken).getName();
		System.out.println("suggestion: " + winner);
	}
}
=======
package scrumbag;

import java.io.FileNotFoundException;

public class Main {
	private static Controller controller;

	public static void main(String[] args) throws FileNotFoundException {
		String accessToken = "CAAVvLje0a38BAFN0lOQ6EVCaBb2fAnJ9zYsTGRXZCdkOSWFZAtZBHgzHrjUq1KRWz9OMEuhZCY5kJpufZBL0ADPfMMkr5hJoZC6HFaHqtB9Ge7229ivPylvnS3Ddy7Ajbku8I6yfZCTaWdp3HaYaUakbsnCOD1b4qQgLj3nkAGuJaffiSZC4hEP53430ZAfnZAd5i1Qp5n03D6owqWkdmqeZAB5";
		controller = new Controller();		
		String winner = controller.makeSuggestion(accessToken).getName();		System.out.println("suggestion: " + winner);
		controller.writeToFile();
		
	}
}
>>>>>>> 5c7e126b334cd885a5154cf42e7a4662abf935b2
