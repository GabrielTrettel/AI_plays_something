import java.util.Scanner;

class Jogador {
    private String nome;
    private int vitorias;     //TODO
    private int derrotas;     //TODO
    private int id_jogador;
    private static int id_global = 1;

    /*

           Variáveis (e métodos) marcadas como "private" só podem ser acessadas
        por métodos de dentro da própria classe. Chamamos essa prática de encapsulamento
        e fazemos isso para que uma outra classe não consiga alterar dados sensíveis,
        causando algum tipo de transtorno na aplicação. Comumente, só não colocamos
        o private quando o método for necessariamente publico.

           Variáveis(e métodos) marcadas com o quantificador "static" possuem escopo de classe
        e não de objeto, como o usual. Com isso fazemos com que todos os objetos da
        classe jogador possuam o mesmo valor dessa variável independente do instante
        que os objetos são instanciados.
           Fazemos isso para definir de forma automatica um ID único para cada objeto
        (jogador) conforme eles vão sendo instanciados. O método "retornaID" pega
        o valor de id_global no instante que ele foi executado (lembrando que todos
        as instancias tem essa variável com o mesmo valor) e guarda numa variavel
        'aux'iliar. Então incrementamos o valor do id_global (que é atualizado para
        todos os objetos) e depois retornamos o valor de aux para ser
        armazenado no id_jogador, este sendo único e de escopo de objeto.

           A idéia do "vitorias" e "derrotas" é montar algum tipo de ranking entre
        todos os jogadores distintos que já foram instanciados. Para isso o jogo
        escreveria algumas informações num arquivo de log(??) e conforme os players
        forem ganhando e perdendo esses dados seríam atualizados. Também podemos
        oferecer alguma sessão de "estatísticas" do desempenho de cada um.

    */


    public Jogador() {
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
    public void setNome() {
        Scanner sc1 = new Scanner(System.in);
        System.out.printf("Entre com o nome do jogador %d: ", this.id_jogador);
        this.nome = sc1.nextLine();
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
        System.out.print("\33[1A\33[2K");


        while(a>2 || a<0) {
            System.out.printf("Jogada inválida. %s, por favor, repita o movimento desejado\n", this.getNome());
            a = sc1.nextInt();
            System.out.print("\33[1A\33[2K");
        }
        return a;
    }

    public int getJogadaX() {
        Scanner sc1 = new Scanner(System.in);
        System.out.printf("%s escolha a linha: ", this.nome);
        int a = sc1.nextInt();
        System.out.print("\33[1A\33[2K");


        while(a>2 || a<0) {
            System.out.printf("Jogada inválida. %s, por favor, repita o movimento desejado\n", this.getNome());
            a = sc1.nextInt();
            System.out.print("\33[1A\33[2K");
        }
        return a;
    }

}
