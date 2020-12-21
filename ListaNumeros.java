
/**
 * La clase representa a una lista de 
 * números enteros
 * 
 * @author - Saúl Layos Iriso
 * 
 */
import java.util.Arrays;

public class ListaNumeros 
{
    // definir atributos
    private int[] lista;
    private int pos;

    /**
     * Constructor de la clase ListaNumeros 
     * Crea e inicializa adecuadamente los
     * atributos
     * 
     * @param n el tamaño máximo de la lista
     */
    public ListaNumeros(int n) {
        lista = new int[n];
        pos = 0;
    }

    /**
     * Añade un valor siempre al principio de la lista
     * 
     * @param numero el valor que se añade. No se hace nada si la lista está
     *               completa
     * @return true si se ha podido añadir, false en otro caso
     */
    public boolean addElemento(int numero) {
        if(!estaCompleta()){
            for(int i = pos; i > 0; i--){
                lista[i] = lista[i - 1];
            }
            lista[0] = numero;
            pos++;
            return true;
        }else{
            return false;
        }
    }

    /**
     * devuelve true si la lista está completa, false en otro caso
     * Hacer sin if
     */
    public boolean estaCompleta() {
        if (lista.length == pos){
            return true;
        }
        return false;
    }

    /**
     * devuelve true si la lista está vacía, false en otro caso. 
     * Hacer sin if
     */
    public boolean estaVacia() {
        if (pos == 0){
            return true;
        }
        return false;
    }

    /**
     * devuelve el nº de elementos realmente guardados en la lista
     */
    public int getTotalNumeros() {
        return pos;
    }

    /**
     * Vacía la lista
     */
    public void vaciarLista() {
        pos = 0;
    }

    /**
     * Representación textual de la lista de la forma indicada 
     * (leer enunciado)
     * 
     * Si la lista está vacía devuelve ""
     */
    public String toString() {
        if(estaVacia()){
            return "";
        }
        String valores = "";
        String indices = "";
        for(int i = 0; i< pos; i++){
            indices += String.format("%8d", i);
            valores += String.format("%8d", lista[i]);
        }

        valores = valores + "\n" + indices;
        return valores;
    }

    /**
     * Mostrar en pantalla la lista
     */
    public void escribirLista() {
        System.out.println(this.toString());
    }

    /**
     * Búsqueda lineal de numero en la lista
     * @param numero el nº a buscar
     * @return un array con las posiciones en las que se ha encontrado
     *  
     */
    public int[] buscarPosicionesDe(int numero) {
        int aux = 0;
        int[] posiciones = new int[pos];
        for(int i = 0; i< pos; i++){
            if(numero == lista[i]){
                posiciones[aux] = i;
                aux++;
            }
        }

        return Arrays.copyOf(posiciones, aux);
    }

    /**
     * Hace una búsqueda binaria del numero indicado devolviendo -1 si no se
     * encuentra o la posición en la que aparece
     * 
     * El array original lista no se modifica 
     * Para ello crea previamente una copia
     * de lista y trabaja con la copia
     * 
     * Usa exclusivamente métodos de la clase Arrays
     * 
     */
    public int buscarBinario(int numero) {
        int[] aux = Arrays.copyOf(lista, pos);
        Arrays.sort(aux);
        int p = Arrays.binarySearch(aux, numero);

        return p;
    }

    /**
     * borra el primer elemento de la lista
     */
    public void borrarPrimero() {
        pos--;
        for(int i = 0; i < pos; i++){
            lista[i] = lista[i + 1];
        }
    }

    /**
     *  Invierte cada uno de los grupos de n elementos que hay en lista
     *  
     *  Si el nº de elementos en lista no es divisible entre n los elementos restantes 
     *  quedan igual
     *  
     *  (leer enunciado)
     *  
     */
    public void invertir(int n) {
        int[] invertido = new int[pos];
        int grupos = lista.length/n;
        int resto = pos%n;
        int aux = 0;

        if((float)pos/n >= 1){
            for(int i = n; i <= pos; i += n){
                for(int j = i - 1; j >= i - n; j--){
                    invertido[aux] = lista[j];
                    aux ++;
                }
            }
            if(resto != 0){
                for(int k = pos - resto ; k < pos; k++){
                    invertido[k] = lista[k];
                }
            }
        }else{
            invertido = lista;
        }
        System.arraycopy(invertido, 0, lista, 0, aux);
    }

    /**
     * devuelve un ragged array de 2 dimensiones con tantas filas como valores
     * tenga el atributo lista y rellena el array de la forma indicada
     * (leer enunciado)
     * 
     */
    public int[][] toArray2D() {
        int[][] ragged = new int[lista.length][];
        for (int i  = 0; i<ragged.length; i++){
            ragged[i] = new int[i+1];
            ragged[i][0] = 1;
            ragged[i][i] = 1;
            for(int j = 1; j<i; j++){
                ragged[i][j]= ragged[i-1][j-1] + ragged[i-1][j];
            }
        }
        return ragged;
    }

    /**
     * Punto de entrada a la aplicación 
     * Contiene código para probar los métodos de ListaNumeros
     */
    public static void main(String[] args) {
        ListaNumeros lista = new ListaNumeros(20);

        System.out.println("-------- addElemento() y toString() --------");
        int[] valores = {21, -5, 6, -7, 21, -17, 21, 15, 22, 21, 77};
        for (int i = 0; i < valores.length; i++) {
            lista.addElemento(valores[i]);
        }
        System.out.println(lista.toString());
        System.out.println("\n");
        

        System.out.println("-------- buscarPosiciones() --------");
        int numero = 21;
        System.out.println(lista.toString());
        System.out.println("\t" + numero + " aparece en posiciones ");
        
        
        int[] busqueda = lista.buscarPosicionesDe(numero);
          for(int i = 0; i < busqueda.length; i++){
            System.out.print(" " + busqueda[i]);
        }
        System.out.println("\n");
        
        
        System.out.println("-------- buscarBinario() --------");
         if(lista.buscarBinario(numero) >= 0){
            System.out.println("El número está en la lista");
        }else{
            System.out.println("El número no está en la lista");
        }
        System.out.println("\n");
        

        System.out.println("-------- invertir() --------");
        System.out.println("Lista sin invertir:");
        System.out.println(lista.toString());
        System.out.println("Lista invertida con grupos de 4 elementos y " +
                       lista.getTotalNumeros() + " elementos en total: ");
        lista.invertir(4);
        System.out.println(lista.toString());
        System.out.println("\n");
        

        System.out.println("-------- toArray2D() --------");
        int[][] array2d = lista.toArray2D();
        for(int i = 0; i < array2d.length; i++){
            System.out.print("Fila " + (i + 1) + ":");
            for(int j = 0; j < array2d[i].length; j++){
                System.out.print(array2d[i][j] + " ");
            }
            System.out.println();
        }
    }
}
