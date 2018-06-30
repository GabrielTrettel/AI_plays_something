public class Console {
	
	public void imprimeJogo(Tabuleiro t) {
		

		System.out.println("rodada x");


		System.out.printf("-------------------\n");
		System.out.printf("|  %c  |  %c  |  %c  |\n", t.getPos(0,0), ' ','O' );
		System.out.printf("-------------------\n");
		System.out.printf("|  %c  |  %c  |  %c  |\n", ' ', 'X','O');
		System.out.printf("-------------------\n");
		System.out.printf("|  %c  |  %c  |  %c  |\n", 'X', ' ','X');
		System.out.printf("-------------------\n");
	}
}

