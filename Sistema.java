package SistemaRestaurante;

import java.util.ArrayList;
import java.util.Comparator;
//import java.util.List;
import java.util.Scanner;

public class Sistema {
    private Scanner scanner;
    private ArrayList<Pedido> pedidos; //Lista dos pedidos registrados
    private int proximoNumeroPedido; //Contador da quant. dos pedidos

    public Sistema() {
        this.scanner = new Scanner(System.in);
        this.pedidos = new ArrayList<>();
        this.proximoNumeroPedido = 1;
    }

    //Menu
    public void menuPrincipal() {
        System.out.println("\nSistema de Pedidos");
        System.out.println("1. Fazer pedido");
        System.out.println("2. Listar pedidos");
        System.out.println("3. Remover pedido");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");
    }

    //Loop ate o usuário escolher sair
    public void escolhas() {
        while (true) {
            menuPrincipal();
            int opcao = scanner.nextInt(); //Recebe a entrada do usuário: 1,2,3,4
            scanner.nextLine(); //Quebra de linha

            switch (opcao){
                case 1:
                    fazerPedido();
                    break;
                case 2:
                    listarPedidos();
                    break;
                case 3:
                    removerPedido();
                    break;
                case 4:
                    System.out.println("Saindo do sistema. Até logo!");
                    return; //Encerra o programa
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    //---------- AÇÕES COM OS PEDIDOS -----------
    private void fazerPedido() {
        System.out.print("Nome:");
        String cliente = scanner.nextLine(); //Recebe o nome do cliente

        Pedido pedido = new Pedido(cliente, proximoNumeroPedido); //Cria o pedido
        proximoNumeroPedido++; //Contabiliza na quant. de pedidos

        String resposta; //correção
        do {
            System.out.print("Nome do item:");
            String nomeItem = scanner.nextLine();

            System.out.print("Preço do item:R$");
            double preco = scanner.nextDouble();
            scanner.nextLine();

            Item item = new Item(nomeItem, preco);
            pedido.adicionarItem(item);

            System.out.print("Deseja adicionar outro item? (s/n):");
            resposta = scanner.nextLine().toLowerCase();
        }
        while (resposta.equals("s")); //sim - adiciona novos itens

        pedidos.add(pedido); //Adiciona o pedido

        //Saída com as informações
        System.out.println("-------- Restaurante SCCP --------------");
        System.out.println("Cliente: " + pedido.getCliente());
        System.out.println("Número do pedido: " + pedido.getNumero());
        System.out.println("Itens do pedido:");
        for (Item item : pedido.getItens()) {
            System.out.println("- " + item.getNomeItem() + " - R$" + String.format("%.2f", item.getPreco()));
        }
        System.out.println("----------------------------------------");
        System.out.println("Total: R$" + String.format("%.2f", pedido.calcularTotal()));

    }

    private void removerPedido() {
        System.out.print("Digite o número do pedido a ser removido: ");
        int numero = scanner.nextInt(); //Recebe o número
        scanner.nextLine();

        Pedido pedidoEncontrado = null;
        for (Pedido p : pedidos) { //Percorre os pedidos até achar
            if (p.getNumero() == numero) { //Compara o número fornecido com o número do pedido
                pedidoEncontrado = p; //Achou
                break; //Encerra
            }
        }
        if (pedidoEncontrado != null) { //Se existe pedido a ser removido
            pedidos.remove(pedidoEncontrado); //Remove o pedido
        } else { //Se não existe
            System.out.println("Pedido não encontrado.");
        }
    }

    private void listarPedidos() {
        if (pedidos.isEmpty()) { //Caso não tenha pedidos
            System.out.println("Nenhum pedido foi feito.");
            return;
        }

       //Ordena os números dos pedidos (método get)
        pedidos.sort(Comparator.comparing(Pedido::getNumero)); //Recebe o número do pedido

        //Percorre cada pedido da lista e imprime seus detalhes
        for (Pedido p : pedidos) { //tipo variável:coleção
            System.out.println("\nPedido N° " + p.getNumero());
            System.out.println("Cliente: " + p.getCliente());
            System.out.println("Itens:");
            for (Item item : p.getItens()) { //Percorre os itens do pedido p

                System.out.println("  - " + item); //Imprime as infos formatadas pelo toString
            }
            System.out.println("Total: R$" + String.format("%.2f", p.calcularTotal()));
            System.out.println("-----------------------------");
        }
    }

    public static void main(String[] args) {
        Sistema sistema = new Sistema(); //Instância do sistema
        sistema.escolhas();
    }
}