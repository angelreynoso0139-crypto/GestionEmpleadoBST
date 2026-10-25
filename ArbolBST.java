/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructura;

import modelo.Empleado;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author lopez
 */

public class ArbolBST {
    private NodoBST raiz;

    public ArbolBST() {
    }

    public NodoBST getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoBST raiz) {
        this.raiz = raiz;
    }
    // 1. Insertar empleado
    public boolean insertar(Empleado empleado) {
        if (empleado == null) return false;
        if (buscar(empleado.getId()) != null) {
            throw new IllegalArgumentException("Ya existe un empleado con el ID: " + empleado.getId());
        }
        raiz = insertarRecursivo(raiz, empleado);
        return true;
    }

    private NodoBST insertarRecursivo(NodoBST actual, Empleado empleado) {
        if (actual == null) return new NodoBST(empleado);
        if (empleado.getId() < actual.getDato().getId()) {
            actual.setIzquierdo(insertarRecursivo(actual.getIzquierdo(), empleado));
        } else if (empleado.getId() > actual.getDato().getId()) {
            actual.setDerecho(insertarRecursivo(actual.getDerecho(), empleado));
        }
        return actual;
    }

    // 2. Buscar empleado por ID
    public Empleado buscar(Integer id) {
        return buscarRecursivo(raiz, id);
    }

    private Empleado buscarRecursivo(NodoBST actual, Integer id) {
        if (actual == null || id == null) return null;
        if (id.equals(actual.getDato().getId())) return actual.getDato();
        return id < actual.getDato().getId()
                ? buscarRecursivo(actual.getIzquierdo(), id)
                : buscarRecursivo(actual.getDerecho(), id);
    }

    // 3. Eliminar empleado
    public boolean eliminar(Integer id) {
        if (buscar(id) == null) return false;
        raiz = eliminarRecursivo(raiz, id);
        return true;
    }

    private NodoBST eliminarRecursivo(NodoBST actual, Integer id) {
        if (actual == null) return null;

        if (id < actual.getDato().getId()) {
            actual.setIzquierdo(eliminarRecursivo(actual.getIzquierdo(), id));
        } else if (id > actual.getDato().getId()) {
            actual.setDerecho(eliminarRecursivo(actual.getDerecho(), id));
        } else {
            if (actual.getIzquierdo() == null) return actual.getDerecho();
            if (actual.getDerecho() == null) return actual.getIzquierdo();

            NodoBST sucesor = encontrarMinimo(actual.getDerecho());
            actual.setDato(sucesor.getDato());
            actual.setDerecho(eliminarRecursivo(actual.getDerecho(), sucesor.getDato().getId()));
        }
        return actual;
    }

    private NodoBST encontrarMinimo(NodoBST actual) {
        while (actual.getIzquierdo() != null) {
            actual = actual.getIzquierdo();
        }
        return actual;
    }

    // 4. Recorridos
    public List<Empleado> mostrarInOrden() {
        List<Empleado> lista = new ArrayList<>();
        inOrdenRecursivo(raiz, lista);
        return lista;
    }

    private void inOrdenRecursivo(NodoBST nodo, List<Empleado> lista) {
        if (nodo != null) {
            inOrdenRecursivo(nodo.getIzquierdo(), lista);
            lista.add(nodo.getDato());
            inOrdenRecursivo(nodo.getDerecho(), lista);
        }
    }

    public List<Empleado> mostrarPreOrden() {
        List<Empleado> lista = new ArrayList<>();
        preOrdenRecursivo(raiz, lista);
        return lista;
    }

    private void preOrdenRecursivo(NodoBST nodo, List<Empleado> lista) {
        if (nodo != null) {
            lista.add(nodo.getDato());
            preOrdenRecursivo(nodo.getIzquierdo(), lista);
            preOrdenRecursivo(nodo.getDerecho(), lista);
        }
    }

    public List<Empleado> mostrarPostOrden() {
        List<Empleado> lista = new ArrayList<>();
        postOrdenRecursivo(raiz, lista);
        return lista;
    }

    private void postOrdenRecursivo(NodoBST nodo, List<Empleado> lista) {
        if (nodo != null) {
            postOrdenRecursivo(nodo.getIzquierdo(), lista);
            postOrdenRecursivo(nodo.getDerecho(), lista);
            lista.add(nodo.getDato());
        }
    }

    // 5. Profundidad
    public int profundidad() {
        return profundidadRecursiva(raiz);
    }

    private int profundidadRecursiva(NodoBST nodo) {
        if (nodo == null) return 0;
        return 1 + Math.max(profundidadRecursiva(nodo.getIzquierdo()), profundidadRecursiva(nodo.getDerecho()));
    }

    // 6. Amplitud
    public int amplitud() {
        if (raiz == null) return 0;
        int maxAncho = 0;
        Queue<NodoBST> cola = new LinkedList<>();
        cola.add(raiz);

        while (!cola.isEmpty()) {
            int nivelTamanio = cola.size();
            maxAncho = Math.max(maxAncho, nivelTamanio);

            for (int i = 0; i < nivelTamanio; i++) {
                NodoBST actual = cola.poll();
                if (actual.getIzquierdo() != null) cola.add(actual.getIzquierdo());
                if (actual.getDerecho() != null) cola.add(actual.getDerecho());
            }
        }
        return maxAncho;
    }

    // 7. Balancear
    public void balancear() {
        List<Empleado> empleadosOrdenados = mostrarInOrden();
        raiz = construirArbolBalanceado(empleadosOrdenados, 0, empleadosOrdenados.size() - 1);
    }

    private NodoBST construirArbolBalanceado(List<Empleado> lista, int inicio, int fin) {
        if (inicio > fin) return null;
        int medio = (inicio + fin) / 2;
        NodoBST nodo = new NodoBST(lista.get(medio));
        nodo.setIzquierdo(construirArbolBalanceado(lista, inicio, medio - 1));
        nodo.setDerecho(construirArbolBalanceado(lista, medio + 1, fin));
        return nodo;
    }

    // 8. Destruir árbol
    public void destruirArbol() {
        this.raiz = null;
    }
}
