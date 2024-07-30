/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce22test;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author marco
 */
public class GestorUnidades extends ModeloUnidades {
    private final String nombreMenu = "UNIDADES";
    private final String nombreSingular = "unidad";
    private final GestorMaterias gMaterias = new GestorMaterias();
    

    public void menu() {
        Scanner teclado = new Scanner(System.in);
        int opt;
        do {
            App.clearConsole();
            System.out.println(App.BLUE + "MENU DE " + this.nombreMenu + App.RESET);
            System.out.println(App.BLUE + "1.- CONSULTAR " + this.nombreMenu + App.RESET);
            System.out.println(App.BLUE + "2.- BUSQUEDA POR NOMBRE" + App.RESET);
            System.out.println(App.BLUE + "3.- CREAR" + App.RESET);
            System.out.println(App.BLUE + "4.- ACTUALIZAR" + App.RESET);
            System.out.println(App.BLUE + "5.- ELIMINAR" + App.RESET);
            System.out.println(App.BLUE + "0.- SALIR" + App.RESET);
            System.out.print(App.BLUE + "Ingrese una opcion: " + App.RESET);
            opt = teclado.nextInt();
            teclado.nextLine();

            switch (opt) {
                case 1 ->
                    this.index();
                case 2 ->
                    this.search();
                case 3 ->
                    this.create();
                case 4 ->
                    this.update();
                case 5 ->
                    this.delete();
            }
        } while (opt != 0);
    }

    public void index() {
        App.clearConsole();
        System.out.println(App.PURPLE + "LISTANDO " + this.nombreMenu + "...");
        var listaTemp = this.consulta();
        System.out.println(AsciiTable.getTable(listaTemp, Arrays.asList(
                new Column().header("ID").with(a -> Integer.toString(a.getId())),
                new Column().header("NOMBRE").with(a -> a.getNombre()),
                new Column().header("MATERIA").with(a -> a.getMateria().getNombre())
        )));
        App.pauseConsole();
    }

    private void search() {
        App.clearConsole();
        Scanner teclado = new Scanner(System.in);
        System.out.print(App.PURPLE + "Ingrese un nombre: ");
        String nombre = teclado.nextLine();
        var listaTemp = this.buscar(nombre);
        System.out.println(AsciiTable.getTable(listaTemp, Arrays.asList(
                new Column().header("ID").with(a -> Integer.toString(a.getId())),
                new Column().header("NOMBRE").with(a -> a.getNombre()),
                new Column().header("MATERIA").with(a -> a.getMateria().getNombre())
        )));
        App.pauseConsole();
    }

    private void create() {
        App.clearConsole();
        Scanner teclado = new Scanner(System.in);
        System.out.print(App.PURPLE + "Ingrese un nombre: ");
        String nombre = teclado.nextLine();
        
        this.gMaterias.index();
        System.out.print("Ingrese el id de la materia: ");
        int idMateria = teclado.nextInt();
        teclado.nextLine();
        if(!this.gMaterias.existe(idMateria)) {
            System.out.println(App.RED + "EL ID de la materia ingresada no existe.");
            App.pauseConsole();
            return;
        }
        
        var instanciaCreada = this.crear(nombre, idMateria);
        var listaTemp = Arrays.asList(instanciaCreada
        );
        System.out.println("La " + this.nombreSingular + " creada es: ");
        System.out.println(AsciiTable.getTable(listaTemp, Arrays.asList(
                new Column().header("ID").with(a -> Integer.toString(a.getId())),
                new Column().header("NOMBRE").with(a -> a.getNombre()),
                new Column().header("MATERIA").with(a -> a.getMateria().getNombre())
        )));
        App.pauseConsole();
    }

    private void update() {
        App.clearConsole();
        this.index();

        Scanner teclado = new Scanner(System.in);
        System.out.print(App.PURPLE + "Ingrese el ID de la " + this.nombreSingular + " a actualizar: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        if (!this.existe(id)) {
            App.clearConsole();
            System.out.println("La " + this.nombreSingular + " con el ID " + id + " no existe.");
            App.pauseConsole();
            return;
        }

        System.out.print("Ingrese el nuevo nombre de la " + this.nombreSingular + ": ");
        String nombre = teclado.nextLine();
        
        this.gMaterias.index();
        System.out.print("Ingrese el id de la nueva materia para la unidad: ");
        int idMateria = teclado.nextInt();
        teclado.nextLine();
        if(!this.gMaterias.existe(idMateria)) {
            System.out.println(App.RED + "EL ID de la materia ingresada no existe.");
            App.pauseConsole();
            return;
        }

        var registroActualizado = this.actualizar(id, nombre, idMateria);
        var listaTemp = Arrays.asList(registroActualizado
        );
        System.out.println("La " + this.nombreSingular + " actualizado es: ");
        System.out.println(AsciiTable.getTable(listaTemp, Arrays.asList(
                new Column().header("ID").with(a -> Integer.toString(a.getId())),
                new Column().header("NOMBRE").with(a -> a.getNombre()),
                new Column().header("MATERIA").with(a -> a.getMateria().getNombre())
        )));
        App.pauseConsole();
    }

    private void delete() {
        App.clearConsole();
        this.index();

        Scanner teclado = new Scanner(System.in);
        System.out.print(App.PURPLE + "Ingrese el ID de la " + this.nombreSingular + " a eliminar: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        if (!this.existe(id)) {
            App.clearConsole();
            System.out.println("La " + this.nombreSingular + " con el ID " + id + " no existe.");
            App.pauseConsole();
            return;
        }

        boolean correcto = this.eliminar(id);

        if (correcto) {
            System.out.println(this.nombreSingular + " eliminado correctamente");
        } else {
            System.out.println("Ocurrio un error al intentar eliminar la " + this.nombreSingular + ".");
        }

        App.pauseConsole();
    }
}
