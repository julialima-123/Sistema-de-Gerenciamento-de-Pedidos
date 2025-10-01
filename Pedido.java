package SistemaRestaurante;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    //atributos de um pedido: nome do cliente, número do pedido e os itens do pedido
    private String cliente;
    private int numero;
    private List<Item> itens;

    public Pedido(String cliente, int numero) {
        this.cliente = cliente;
        this.numero = numero;
        this.itens = new ArrayList<>(); //lista vazia de itens
    }

    public String getCliente() {
        return cliente;
    }

    public int getNumero() {
        return numero;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void adicionarItem(Item item) {
        this.itens.add(item); //Adiciona cada item na lista
    }

    public double calcularTotal() {
        double total = 0.0;
        for (Item item : itens) { //Percorre os itens
            total += item.getPreco(); //Adiciona o preço de cada item a soma total
        }
        return total;
    }
}