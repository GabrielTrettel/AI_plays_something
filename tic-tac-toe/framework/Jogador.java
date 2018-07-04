class Jogador {
    private String nome;
    private int vitorias;
    private int derrotas;
    static int id;

    public Jogador(String nome) {
        this.nome = nome;
        this.vitorias = 0;
        this.derrotas = 0;
        this.id = this.id+1;
    }
}
