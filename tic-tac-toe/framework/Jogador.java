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


    public getJogada() {
        Scanner sc1 = new Scanner(System.in);
        String textoString = "";
        Scanner sc2 = new Scanner(textoString);
    }

}
