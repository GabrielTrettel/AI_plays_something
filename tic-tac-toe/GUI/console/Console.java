public class Console {

	public void imprimeJogo() {


		System.out.println("rodada x");


		System.out.printf("-------------------\n");
		System.out.printf("|  %c  |  %c  |  %c  |\n", 'X', ' ','O' );
		System.out.printf("-------------------\n");
		System.out.printf("|  %c  |  %c  |  %c  |\n", ' ', 'X','O');
		System.out.printf("-------------------\n");
		System.out.printf("|  %c  |  %c  |  %c  |\n", 'X', ' ','X');
		System.out.printf("-------------------\n");
	}
}
