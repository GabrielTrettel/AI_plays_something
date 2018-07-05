import java.util.Scanner;

/*
      Aqui queremos que a IA herde todos os atributos e comportamentos
  de Player, já que a IA é um player. Para isso usamos o extends
  que faz justamente essa extensão. A classe Player possui métodos
  para pegar os movimentos de linha e coluna (getMovesX e getMovesY)
  que precisarão ser reescritos nessa classe já que a IA não faz
  a jogada por input de teclado. Tirando isso, todos os outros atributos
  e métodos se manterão, em que o atributo nome será preenchido de acordo com
  a dificuldade (Pai do morty, Morty, ...). Dessa forma a classe Rules funcionará
  para ambas as classes Player e AI. Reforçando a idéia: Ai É um Player, pelas
  características de herança.



  Atributos herdados:
      protected String name;
      protected int wins;     //TODO
      protected int losses;     //TODO
      protected int player_id;
      protected static int global_id = 1;

  Métodos herdados:
      public Player()
      public void setName()
      public int getWins()
      public int getLosses()
      public int getID()
      public String getName()

  Métodos sobrescritos (polimorfismo):
      public int[] getMove(Board board)
      public void setName()

*/
public class Ai extends Player {



  // Sobrescrita de método para se aproveitar do polimorfismo de player e Ai

  //@Overide
  public int[] getMove(Board board) {
      int[] moves = new int[2];
      //TODO

      return moves;
  }

  //@Overide
  public void setName() {
      Scanner sc1 = new Scanner(System.in);
      System.out.printf("Escolha com quem você quer jogar %d: ", player_id);
      name = sc1.nextLine();
  }

  private int[] thinkMoves(Board board) {
      int[] moves = new int[2];
      //TODO

      return moves;
  }




}
