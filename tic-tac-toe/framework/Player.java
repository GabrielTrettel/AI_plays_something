import java.util.Scanner;
/*

Variáveis(e métodos) marcadas com o quantificador "static" possuem escopo de classe
e não de objeto, como o usual. Com isso fazemos com que todos os objetos da
classe jogador possuam o mesmo valor dessa variável independente do instante
que os objetos são instanciados.
Fazemos isso para definir de forma automatica um ID único para cada objeto
(jogador) conforme eles vão sendo instanciados. O método "retornaID" pega
o valor de global_id no instante que ele foi executado (lembrando que todos
as instancias tem essa variável com o mesmo valor) e guarda numa variavel
'aux'iliar. Então incrementamos o valor do global_id (que é atualizado para
todos os objetos) e depois retornamos o valor de aux para ser
armazenado no player_id, este sendo único e de escopo de objeto.

A idéia do "vitorias" e "derrotas" é montar algum tipo de ranking entre
todos os jogadores distintos que já foram instanciados. Para isso o jogo
escreveria algumas informações num arquivo de log(??) e conforme os players
forem ganhando e perdendo esses dados seríam atualizados. Também podemos
oferecer alguma sessão de "estatísticas" do desempenho de cada um.

*/

public class Player {
    protected String name;
    protected int wins;     //TODO
    protected int losses;     //TODO
    protected int player_id;
    protected boolean is_ai;
    private static int global_id = 1;


    public Player() {
        this.wins = 0;
        this.losses = 0;
        this.is_ai = false;
        this.name = "Random";
        this.player_id = returnID();

    }


    private static int returnID() {
        int aux = global_id;
        global_id = global_id+1;
        return aux;
    }

    public void setName() {
        Scanner sc1 = new Scanner(System.in);
        System.out.printf("Entre com o nome do jogador %d: ", this.player_id);
        this.name = sc1.nextLine();
    }

    public boolean getIsAi() {
        return this.is_ai;
    }


    public int getWins() {
        return this.wins;
    }
    public int getLosses() {
        return this.losses;
    }
    public int getID() {
        return this.player_id;
    }
    public String getName() {
        return this.name;
    }

    public int[] getMove(Board b) {
        int[] moves = new int[2];
        moves[0] = this.getMoveX();
        moves[1] = this.getMoveY();
        return moves;
    }

    private int getMoveY() {
        Scanner sc1 = new Scanner(System.in);
        System.out.printf("%s escolha a coluna: ", this.name);
        int a = sc1.nextInt();
        System.out.print("\33[1A\33[2K");


        while(a>2 || a<0) {
            System.out.printf("Jogada inválida. %s, por favor, repita o movimento desejado\n", this.getName());
            a = sc1.nextInt();
            System.out.print("\33[1A\33[2K");
        }
        return a;
    }

    private int getMoveX() {
        Scanner sc1 = new Scanner(System.in);
        System.out.printf("%s escolha a linha: ", this.name);
        int a = sc1.nextInt();
        System.out.print("\33[1A\33[2K");


        while(a>2 || a<0) {
            System.out.printf("Jogada inválida. %s, por favor, repita o movimento desejado\n", this.getName());
            a = sc1.nextInt();
            System.out.print("\33[1A\33[2K");
        }
        return a;
    }

}
