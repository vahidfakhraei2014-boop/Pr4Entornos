/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vahid.pr4entornos;

import java.util.ArrayList;

/**
 * clase cajero he refactorizado. laos cambios incluido nombre de varaiable, y parametros, los  processo encapsulada dentro de  methodos
 * @author alumno B
 */



public class Cajero {

   private String nombre;
   private int ticketsEmitidos;
   private double totalDia;
   private ArrayList<Producto> productLista;
   private static final double ivaPerciento = 0.21;

    public Cajero(String n) {
        this.nombre = n;
        this.ticketsEmitidos = 0;
        this.totalDia = 0;
        this.productLista = new ArrayList<>();
    }

    public void anadirProducto(Producto producto) {
        productLista.add(producto);
    }

    public void eliminarProducto(Producto producto) {
        productLista.remove(producto);
    }

    public void cobrar() {
        double subt = CalcularSubTotal();
        double iva = calcularIVA(subt);
        double tot = calcularPercioConIVA(subt, iva);

        imprimirTicket(subt, iva, tot);

        limpiarTicket(tot);
    }

    private void limpiarTicket(double tot) {
        ticketsEmitidos = ticketsEmitidos + 1;
        totalDia = totalDia + tot;
        productLista.clear();
    }

    private void imprimirTicket(double subt, double iva, double tot) {
        System.out.println("===== TICKET =====");
        System.out.println("Cajero: " + nombre);
        for (Producto p : productLista) {
            System.out.println(p.getNombre() + " x" + p.getCantidad()
                    + " = " + String.format("%.2f", p.calcularImporte()) + " EUR");
        }
        System.out.println("------------------");
        System.out.println("Subtotal: " + String.format("%.2f", subt) + " EUR");
        System.out.println("IVA (21%): " + String.format("%.2f", iva) + " EUR");
        System.out.println("TOTAL: " + String.format("%.2f", tot) + " EUR");
        System.out.println("==================");
    }

    private double CalcularSubTotal() {
        double subt = 0;
        for (Producto p : productLista) {
            subt = subt + p.calcularImporte();
        }
        return subt;
    }

    private double calcularPercioConIVA(double subt, double iva) {
        double tot = subt + iva;
        return tot;
    }

    private double calcularIVA(double subt) {
        double iva = subt * ivaPerciento;
        return iva;
    }
    

    public void cierreCaja() {
        double ivaRec = totalDia - (totalDia / (1 + ivaPerciento));

        System.out.println("===== CIERRE DE CAJA =====");
        System.out.println("Cajero: " + nombre);
        System.out.println("--------------------------");
        System.out.println("Tickets emitidos: " + ticketsEmitidos);
        System.out.println("Total facturado:  " + String.format("%.2f", totalDia) + " EUR");
        System.out.println("IVA recaudado:    " + String.format("%.2f", ivaRec) + " EUR");
        System.out.println("==========================");
    }

    public boolean ticketVacio() {
        return productLista.isEmpty();
    }

    public int getTicketsEmitidos() {
        return ticketsEmitidos;
    }

    public double getTotalDia() {
        return totalDia;
    }
}
