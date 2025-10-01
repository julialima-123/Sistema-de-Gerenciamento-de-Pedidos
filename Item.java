package SistemaRestaurante;

public class Item { //Com atributos do item: nome e preço
    private String nomeItem;
    private double preco;

    //Construtor
    public Item(String nomeItem, double preco) { //Inicializa
        this.nomeItem = nomeItem;
        this.preco = preco;
    }

    //Encapsulamento - lê os valores
    public String getNomeItem() {
        return nomeItem;
    }

    public double getPreco() {
        return preco;
    }

    public String toString() {   //Formata a saída
        return nomeItem + " - R$ " + String.format("%.2f", preco);
    }
}
