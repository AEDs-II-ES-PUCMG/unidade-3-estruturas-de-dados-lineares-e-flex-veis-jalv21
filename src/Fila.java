import java.util.NoSuchElementException;

public class Fila<E> {
    private Celula<E> frente;
    private Celula<E> tras;

    public Fila() {
        Celula<E> sentinela = new Celula<E>();
        frente = tras = sentinela;
    }

    public boolean vazia() {
        return (frente == tras);
    }

    public void enfileirar(E item) {
        Celula<E> novaCelula = new Celula<E>(item);
        tras.setProximo(novaCelula);
        tras = tras.getProximo();
    }

    public E desenfileirar() {
        if(vazia())
            throw new IllegalStateException("A fila está vazia!");

        E item = null;
        Celula<E> primeiro;

        item = consultarPrimeiro();

        primeiro = frente.getProximo();
        frente.setProximo(primeiro.getProximo());

        primeiro.setProximo(null);

        if(primeiro == tras)
            tras = frente;

        return item;
    }

    public E consultarPrimeiro() {
        if(vazia())
            throw new NoSuchElementException("não há item.");
        
        return frente.getProximo().getItem();
    }

    public void imprimir() {
        Celula<E> aux;

        if(vazia())
            System.out.println("A fila está vazia!");
        else {
            aux = this.frente.getProximo();
            while(aux != null) {
                System.out.println(aux.getItem());
                aux = aux.getProximo();
            }
        }
    }

    public int contarOcorrencias(E item) {
        int contador = 0;
        Celula<E> aux;

        if(vazia())
            throw new IllegalStateException("A fila está vazia!");
        else {
            aux = this.frente.getProximo();
            while (aux != null) {
                if(aux.getItem().equals(item))
                    contador++;

                aux = aux.getProximo();
            }
        }

        return contador;
    }
    
    public Fila<E> extrairLote(int numItens) {
        int cont  = 0;

        Fila<E> lote = new Fila<>();

        while(!vazia() && cont <= numItens) {
            lote.enfileirar(this.desenfileirar());
        }
        
        return lote;
    }
}
