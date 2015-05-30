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
