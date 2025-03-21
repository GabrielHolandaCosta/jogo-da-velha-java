import java.util.Scanner;

public class JogoDaVelha {

    private static char[][] tabuleiro = new char[3][3];
    private static char jogadorAtual = 'X';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inicializar o tabuleiro
        inicializarTabuleiro();

        while (true) {
            exibirTabuleiro();
            System.out.println("Jogador " + jogadorAtual + ", é sua vez de jogar!");
            int linha = obterEntrada("Digite a linha (1-3): ", scanner) - 1; // Subtrai 1 para adaptar a indexação
            int coluna = obterEntrada("Digite a coluna (1-3): ", scanner) - 1; // Subtrai 1 para adaptar a indexação

            // Verificar se a posição é válida
            if (fazerJogada(linha, coluna, jogadorAtual)) {
                if (verificarVitoria()) {
                    exibirTabuleiro();
                    System.out.println("Jogador " + jogadorAtual + " venceu!");
                    break;
                }
                if (verificarEmpate()) {
                    exibirTabuleiro();
                    System.out.println("O jogo empatou!");
                    break;
                }
                // Alternar o jogador
                jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
            } else {
                System.out.println("Movimento inválido. Tente novamente.");
            }
        }
        scanner.close();
    }

    // Método para inicializar o tabuleiro
    private static void inicializarTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = ' ';  // Espaço vazio
            }
        }
    }

    // Método para exibir o tabuleiro
    private static void exibirTabuleiro() {
        System.out.println("\nTabuleiro:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + tabuleiro[i][j] + " ");
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) {
                System.out.println("---+---+---");
            }
        }
    }

    // Método para obter entrada de linha ou coluna do usuário
    private static int obterEntrada(String mensagem, Scanner scanner) {
        int valor;
        while (true) {
            System.out.print(mensagem);
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                if (valor >= 1 && valor <= 3) {
                    return valor;
                } else {
                    System.out.println("Entrada inválida! Deve ser um número entre 1 e 3.");
                }
            } else {
                System.out.println("Entrada inválida! Deve ser um número.");
                scanner.next(); // Limpar o buffer
            }
        }
    }

    // Método para fazer a jogada
    private static boolean fazerJogada(int linha, int coluna, char simbolo) {
        if (tabuleiro[linha][coluna] == ' ') {
            tabuleiro[linha][coluna] = simbolo;
            return true;
        }
        return false;  // Posição já ocupada
    }

    // Método para verificar se houve vitória
    private static boolean verificarVitoria() {
        // Verificar linhas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == jogadorAtual && tabuleiro[i][1] == jogadorAtual && tabuleiro[i][2] == jogadorAtual) {
                return true;
            }
        }
        // Verificar colunas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[0][i] == jogadorAtual && tabuleiro[1][i] == jogadorAtual && tabuleiro[2][i] == jogadorAtual) {
                return true;
            }
        }
        // Verificar diagonais
        if (tabuleiro[0][0] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][2] == jogadorAtual) {
            return true;
        }
        if (tabuleiro[0][2] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][0] == jogadorAtual) {
            return true;
        }

        return false;
    }

    // Método para verificar empate
    private static boolean verificarEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false;  // Ainda há espaço disponível
                }
            }
        }
        return true;  // Não há mais espaços, então é empate
    }
}
