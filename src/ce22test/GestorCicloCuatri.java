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
public class GestorCicloCuatri extends ModeloCicloCuatri {
    
    public void menu() {
        Scanner teclado = new Scanner(System.in);
        int opt;
        do {
            App.clearConsole();
            System.out.println(App.BLUE + "MENU DE CICLO CUATRI" + App.RESET);
            System.out.println(App.BLUE +"1.- CONSULTAR CICLOS CUATRI" + App.RESET);
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
    
    private void index() {
        App.clearConsole();
        System.out.println(App.PURPLE + "LISTANDO CICLOS CUATRI...");
        ArrayList<CicloCuatri> cuatrimestres = this.consulta();
        System.out.println(AsciiTable.getTable(cuatrimestres, Arrays.asList(
                new Column().header("ID").with(a -> Integer.toString(a.getId())),
                new Column().header("CICLO").with(a -> a.getCiclo().getNombre()),
                new Column().header("CUATRI").with(a -> a.getCuatri().getNombre())
        )));
        App.pauseConsole();
    }
    
    private void search() {
        App.clearConsole();
        Scanner teclado = new Scanner(System.in);
        System.out.print(App.PURPLE + "Ingrese un nombre de ciclo(deje en blanco si no quiere buscar por ciclo): ");
        String nombreCiclo = teclado.nextLine();
        System.out.print(App.PURPLE + "Ingrese un nombre de cuatri(deje en blanco si no quiere buscar por cuatri): ");
        String nombreCuatri = teclado.nextLine();
        ArrayList<CicloCuatri> cuatrimestres = this.buscar(nombreCiclo, nombreCuatri);
        System.out.println(AsciiTable.getTable(cuatrimestres, Arrays.asList(
                new Column().header("ID").with(a -> Integer.toString(a.getId())),
                new Column().header("CICLO").with(a -> a.getCiclo().getNombre()),
                new Column().header("CUATRI").with(a -> a.getCuatri().getNombre())
        )));
        App.pauseConsole();
    }

    private void create() {
        App.clearConsole();
        Scanner teclado = new Scanner(System.in);
        
        GestorCiclos gCiclos = new GestorCiclos();
        gCiclos.index();
        System.out.print("Ingrese el id del ciclo: ");
        int idCiclo = teclado.nextInt();
        teclado.nextLine();
        if(!gCiclos.existe(idCiclo)) {
            System.out.println(App.RED + "EL ID del ciclo ingresado no existe.");
            App.pauseConsole();
            return;
        }
        
        GestorCuatrimestres gCuatrimestres = new GestorCuatrimestres();
        gCuatrimestres.index();
        System.out.print("Ingrese el id del cuatrimestre: ");
        int idCuatri = teclado.nextInt();
        teclado.nextLine();
        if(!gCuatrimestres.existe(idCuatri)) {
            System.out.println(App.RED + "EL ID del cuatrimestre ingresado no existe.");
            App.pauseConsole();
            return;
        }
        CicloCuatri cicloCuatriCreado = this.crear(idCiclo, idCuatri);
        List<CicloCuatri> listaCicloCuatri = Arrays.asList(cicloCuatriCreado
        );
        System.out.println("El ciclo cuatri creado es: ");
        System.out.println(AsciiTable.getTable(listaCicloCuatri, Arrays.asList(
                new Column().header("ID").with(a -> Integer.toString(a.getId())),
                new Column().header("CICLO").with(a -> a.getCiclo().getNombre()),
                new Column().header("CUATRI").with(a -> a.getCuatri().getNombre())
        )));
        App.pauseConsole();
    }
    
    private void update() {
        App.clearConsole();
        this.index();
        
        Scanner teclado = new Scanner(System.in);
        System.out.print(App.PURPLE + "Ingrese le ID del ciclo cuatri a actualizar: ");
        int id = teclado.nextInt();
        teclado.nextLine();
        
        if(!this.existe(id)) {
            App.clearConsole();
            System.out.println("El cuatrimestre con el ID " + id + " no existe.");
            App.pauseConsole();
            return;
        }
        
        GestorCiclos gCiclos = new GestorCiclos();
        gCiclos.index();
        System.out.print("Ingrese el id del nuevo ciclo: ");
        int idCiclo = teclado.nextInt();
        teclado.nextLine();
        if(!gCiclos.existe(idCiclo)) {
            System.out.println(App.RED + "EL ID del ciclo ingresado no existe.");
            App.pauseConsole();
            return;
        }
        
        GestorCuatrimestres gCuatrimestres = new GestorCuatrimestres();
        gCuatrimestres.index();
        System.out.print("Ingrese el id del nuevo cuatrimestre: ");
        int idCuatri = teclado.nextInt();
        teclado.nextLine();
        if(!gCuatrimestres.existe(idCuatri)) {
            System.out.println(App.RED + "EL ID del cuatrimestre ingresado no existe.");
            App.pauseConsole();
            return;
        }
        
        CicloCuatri cicloCuatriActualizado = this.actualizar(id, idCiclo, idCuatri);
        
        
        List<CicloCuatri> listaCicloCuatri = Arrays.asList(cicloCuatriActualizado
        );
        System.out.println("El ciclo cuatri actualizado es: ");
        System.out.println(AsciiTable.getTable(listaCicloCuatri, Arrays.asList(
                new Column().header("ID").with(a -> Integer.toString(a.getId())),
                new Column().header("CICLO").with(a -> a.getCiclo().getNombre()),
                new Column().header("CUATRI").with(a -> a.getCuatri().getNombre())
        )));
        App.pauseConsole();
    }
    
    private void delete() {
        App.clearConsole();
        this.index();
        
        Scanner teclado = new Scanner(System.in);
        System.out.print(App.PURPLE + "Ingrese le ID del ciclo cuatri a eliminar: ");
        int id = teclado.nextInt();
        teclado.nextLine();
        
        if(!this.existe(id)) {
            App.clearConsole();
            System.out.println("El ciclo cuatri con el ID " + id + " no existe.");
            App.pauseConsole();
            return;
        }
        
        boolean correcto = this.eliminar(id);
        
        if(correcto) {
            System.out.println("Ciclo cuatri eliminado correctamente");
        } else {
            System.out.println("Ocurrio un error al intentar eliminar el ciclo cuatri.");
        }
        
        App.pauseConsole();
    }
} 
