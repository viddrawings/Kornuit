package scrumbag;


public class Main {
	private static Controller controller;

	public static void main(String[] args) {

			String accessToken = "CAAVvLje0a38BAKNteNeKUxI8Bhyg3NoCdxW2K2oyuRdgZAm2h5hqH"
					+ "QX4XsRHS0AZABWCwQ7icbDp6gEkfU0XJHVooVH6belNfXPlayBZCZCQv7Yb7lJP"
					+ "cvyp3ge8QNGcfC2k8ZAqgVOaRLtIWAJjoVGPUgvYqIMFkpnjJdx4KMfSmVovZCT"
					+ "64N3aL9YUJZCvq3ud7ZABSC9qFck9QlQG0wEZB";

		controller = new Controller();
		String winner = controller.makeSuggestion(accessToken);
		System.out.println("suggestion: " + winner);
	}
}
