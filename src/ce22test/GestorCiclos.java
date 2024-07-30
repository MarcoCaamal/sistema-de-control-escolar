/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce22test;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author marco
 */
public class GestorCiclos extends ModeloCiclo{
    
    public void menu() {
        Scanner teclado = new Scanner(System.in);
        int opt;
        do {
            App.clearConsole();
            System.out.println(App.BLUE + "MENU DE CICLOS" + App.RESET);
            System.out.println(App.BLUE +"1.- CONSULTAR CICLOS" + App.RESET);
            System.out.println(App.BLUE +"2.- BUSQUEDA POR NOMBRE" + App.RESET);
            System.out.println(App.BLUE +"3.- CREAR" + App.RESET);
            System.out.println(App.BLUE +"4.- ACTUALIZAR" + App.RESET);
            System.out.println(App.BLUE +"5.- ELIMINAR" + App.RESET);
            System.out.println(App.BLUE +"0.- SALIR" + App.RESET);
            System.out.print(App.BLUE + "Ingrese una opcion: " + App.RESET);
            opt = teclado.nextInt();
            teclado.nextLine();
            
            switch (opt) {
                case 1 -> this.index();
                case 2 -> this.search();
                case 3 -> this.create();
                case 4 -> this.update();
                case 5  -> this.delete();
            }
        } while (opt != 0);
    }
    
    public void index() {
        App.clearConsole();
        System.out.println(App.PURPLE + "LISTANDO CICLOS...");
        ArrayList<Ciclo> ciclos = this.consulta();
        System.out.println(AsciiTable.getTable(ciclos, Arrays.asList(
                new Column().header("ID").with(a -> Integer.toString(a.getId())),
                new Column().header("NOMBRE").with(a -> a.getNombre())
        )));
        App.pauseConsole();
    }
    
    private void search() {
        App.clearConsole();
        Scanner teclado = new Scanner(System.in);
        System.out.print(App.PURPLE + "Ingrese un nombre: ");
        String nombre = teclado.nextLine();
        ArrayList<Ciclo> ciclos = this.buscar(nombre);
        System.out.println(AsciiTable.getTable(ciclos, Arrays.asList(
                new Column().header("ID").with(a -> Integer.toString(a.getId())),
                new Column().header("NOMBRE").with(a -> a.getNombre())
        )));
        App.pauseConsole();
    }

    private void create() {
        App.clearConsole();
        Scanner teclado = new Scanner(System.in);
        System.out.print(App.PURPLE + "Ingrese un nombre: ");
        String nombre = teclado.nextLine();
        Ciclo cicloCreado = this.crear(nombre);
        List<Ciclo> listaCiclos = Arrays.asList(
                cicloCreado
        );
        System.out.println("El ciclo creado es: ");
        System.out.println(AsciiTable.getTable(listaCiclos, Arrays.asList(
                new Column().header("ID").with(a -> Integer.toString(a.getId())),
                new Column().header("NOMBRE").with(a -> a.getNombre())
        )));
        App.pauseConsole();
    }
    
    private void update() {
        App.clearConsole();
        this.index();
        
        Scanner teclado = new Scanner(System.in);
        System.out.print(App.PURPLE + "Ingrese le ID del ciclo a actualizar: ");
        int id = teclado.nextInt();
        teclado.nextLine();
        
        if(!this.existe(id)) {
            App.clearConsole();
            System.out.println("El ciclo con el ID " + id + " no existe.");
            App.pauseConsole();
            return;
        }
        
        System.out.print("Ingrese el nuevo nombre del ciclo: ");
        String nombre = teclado.nextLine();
        
        Ciclo cicloActualizado = this.actualizar(id, nombre);
        List<Ciclo> listaCiclos = Arrays.asList(
                cicloActualizado
        );
        System.out.println("El ciclo actualizado es: ");
        System.out.println(AsciiTable.getTable(listaCiclos, Arrays.asList(
                new Column().header("ID").with(a -> Integer.toString(a.getId())),
                new Column().header("NOMBRE").with(a -> a.getNombre())
        )));
        App.pauseConsole();
    }
    
    private void delete() {
        App.clearConsole();
        this.index();
        
        Scanner teclado = new Scanner(System.in);
        System.out.print(App.PURPLE + "Ingrese le ID del ciclo a eliminar: ");
        int id = teclado.nextInt();
        teclado.nextLine();
        
        if(!this.existe(id)) {
            App.clearConsole();
            System.out.println("El ciclo con el ID " + id + " no existe.");
            App.pauseConsole();
            return;
        }
        
        boolean correcto = this.eliminar(id);
        
        if(correcto) {
            System.out.println("Ciclo eliminado correctamente");
        } else {
            System.out.println("Ocurrio un error al intentar eliminar el ciclo.");
        }
        
        App.pauseConsole();
    }
} 
