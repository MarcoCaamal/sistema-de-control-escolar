/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ce22test;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author marco
 */
public class App {
    // ANSI escape codes for colors
    public static final String RESET = "\033[0m";  // Text Reset
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE
    
    public void iniciaAplicacion() {
        var teclado = new Scanner(System.in);
        int opt;
        do {            
            clearConsole();
            System.out.println(GREEN + "BIENVENIDO AL MENU DE SISTEMA ESCOLAR" + RESET);
            System.out.println(GREEN + "1.- GESTIONAR CICLOS" + RESET);
            System.out.println(GREEN + "2.- GESTIONAR CUATRIMESTRES" + RESET);
            System.out.println(GREEN + "3.- GESTIONAR CICLOS CUATRIMESTRES" + RESET);
            System.out.println(GREEN + "4.- GESTIONAR CARRERAS" + RESET);
            System.out.println(GREEN + "5.- GESTIONAR PROFESORES" + RESET);
            System.out.println(GREEN + "6.- GESTIONAR MATERIAS" + RESET);
            System.out.println(GREEN + "0.- SALIR" + RESET);
            System.out.print(GREEN + "Ingrese una opción: " + RESET);
            opt = teclado.nextInt();
            teclado.nextLine();
            
            switch (opt) {
                case 0 -> System.exit(0);
                case 1 -> new GestorCiclos().menu();
                case 2 -> new GestorCuatrimestres().menu();
                case 3 -> new GestorCicloCuatri().menu();
                case 4 -> new GestorCarreras().menu();
                case 5 -> new GestorProfesores().menu();
                case 6 -> new GestorMaterias().menu();
                default -> System.out.println("Opción no encontrada");
            }
        } while (opt != 0);
    }
    
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void pauseConsole() {
        var teclado = new Scanner(System.in);
        System.out.println(RED + "Precione ENTER PARA CONTINUAR..." + RESET);
        teclado.nextLine();
    }
    
}
