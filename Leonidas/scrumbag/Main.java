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
