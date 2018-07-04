import java.util.Scanner;

class Jogador {
    private String nome;
    private int vitorias;
    private int derrotas;
    private int id_jogador;

    static int id_global = 1;


    public Jogador(String nome) {
        this.nome = nome;
        this.vitorias = 0;
        this.derrotas = 0;
        this.id_jogador = retornaID();
    }

    private static int retornaID() {
        int aux = id_global;
        id_global = id_global+1;
        return aux;
    }

    public int getVitorias() {
        return this.vitorias;
    }
    public int getDerrotas() {
        return this.derrotas;
    }
    public int getID() {
        return this.id_jogador;
    }
    public String getNome() {
        return this.nome;
    }


    public int getJogadaY() {
        Scanner sc1 = new Scanner(System.in);
        System.out.printf("%s escolha a coluna: ", this.nome);
        int a = sc1.nextInt();

        while(a>2 || a<0) {
            System.out.printf("Jogada inválida. %s, por favor, repita o movimento desejado\n", this.getNome());
            a = sc1.nextInt();
        }
        return a;
    }

    public int getJogadaX() {
        Scanner sc1 = new Scanner(System.in);
        System.out.printf("%s escolha a linha: ", this.nome);
        int a = sc1.nextInt();

        while(a>2 || a<0) {
            System.out.printf("Jogada inválida. %s, por favor, repita o movimento desejado\n", this.getNome());
            a = sc1.nextInt();
        }
        return a;
    }

}
