/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.Scanner;

/**
 *
 * @author Santi
 */
public class TestInmobiliariaII {

    public static void main(String[] args) {
        Scanner pufu = new Scanner(System.in);
        Propiedad[] arreglo, nuevoArreglo;
        int indice, opcion, celdas;
        boolean continuar = true;
        boolean ordenado = false;

        indice = 10;
        arreglo = new Propiedad[indice];
        predefinirArreglo(arreglo);

        while (continuar) {
            menu();
            opcion = pufu.nextInt();
            switch (opcion) {
                case 1:
                    celdas = sumarCeldas();
                    nuevoArreglo = new Propiedad[indice + celdas];
                    agregarCeldas(arreglo, nuevoArreglo, nuevoArreglo.length - 1);
                    arreglo = nuevoArreglo;
                    indice = nuevoArreglo.length;
                    ordenado = false;
                    break;
                case 2:
                    listarDatos(arreglo);
                    break;
                case 3:
                    verificarCasa(arreglo);
                    break;
                case 4:
                    verificarDepto(arreglo);
                    break;
                case 5:
                    ordenarXPrecio(arreglo);
                    listarDatos(arreglo);
                    ordenado = true;
                    break;
                case 6:
                    contarPropiedades(arreglo);
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
            }
        }
    }

    public static void predefinirArreglo(Propiedad[] arreglo) {
        arreglo[0] = new Propiedad(00000, 'c', "San Martin", 3, 'v', 200, true, 150000);
        arreglo[1] = new Propiedad(11111, 'd', "Libertad", 4, 'a', 80, false, 16000);
        arreglo[2] = new Propiedad(22222, 'x', "Santa Fe", 2, 't', 74, true, 1300);
        arreglo[3] = new Propiedad(33333, 'x', "Avenida Olascoaga", 5, 'a', 96, true, 19500);
        arreglo[4] = new Propiedad(44444, 'd', "Santa Rosa", 7, 'v', 156, true, 1250000);
        arreglo[5] = new Propiedad(55555, 'c', "Tierra del Fuego", 5, 'a', 79, false, 32000);
        arreglo[6] = new Propiedad(66666, 'x', "Saturnino Torres", 6, 'a', 103, false, 25320);
        arreglo[7] = new Propiedad(77777, 'x', "Bahia Blanca", 4, 't', 125, true, 1250);
        arreglo[8] = new Propiedad(88888, 'd', "Elordi", 3, 'v', 86, true, 57890);
        arreglo[9] = new Propiedad(99999, 'c', "San Martin", 1, 't', 12, false, 1350);
    }

    public static void menu() {
        System.out.println("Ingrese la opcion a realizar");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.println("1: Cargar propiedades");
        System.out.println("2: Listar datos");
        System.out.println("3: Verificar si hay casas disponibles con mas de 100 m2 a la venta ");
        System.out.println("4: Verificar si hay departamentos en alquiler de 1 ambiente por menos de $20.000");
        System.out.println("5: Ordenar las propiedades por precio");
        System.out.println("6: Contar la cantidad de propiedades disponibles con una cantidad de ambientes determinada");
        System.out.println("7: Encontrar la casa en alquiler con la mayor superficie");
        System.out.println("8: Verificar la existencia de una casa con un precio dado");
        System.out.println("9: Ordenar las propiedades por superficie");
        System.out.println("0: Finalizar");
        System.out.println("--------------------------------------------------------------------------------------------------------");
    }

    public static int sumarCeldas() {
        Scanner pufu = new Scanner(System.in);
        int celdas;
        System.out.println("Ingrese la cantidad de propiedades que va a agregar");
        celdas = pufu.nextInt();
        return celdas;
    }

    public static void agregarCeldas(Propiedad[] arreglo, Propiedad[] nuevoArreglo, int longitud) {
        Propiedad p;
        if (longitud == 0) {
            nuevoArreglo[longitud] = arreglo[longitud];
        } else {
            agregarCeldas(arreglo, nuevoArreglo, longitud - 1);
            if (arreglo.length <= longitud) {
                p = cargarDatos(longitud, nuevoArreglo);
                nuevoArreglo[longitud] = p;
            } else {
                nuevoArreglo[longitud] = arreglo[longitud];
            }
        }
    }

    public static Propiedad cargarDatos(int longitud, Propiedad[] nuevoArreglo) {
        boolean esValido = false;
        Propiedad p;
        int codigo = 0, cantAmbientes, superficie, precio;
        String direccion;
        char tipo, operacion;
        boolean disponibilidad;

        //Repite hasta que el codigo ingresado sea valido
        while (!esValido) {
            codigo = cargarCodigo();
            esValido = verificarCodigo(nuevoArreglo, codigo, longitud - 1);//Llama al modulo que verifica la validez del codigo
        }
        tipo = cargarTipo();
        direccion = cargarDireccion();
        cantAmbientes = cargarAmbientes();
        operacion = cargarOperacion();
        superficie = cargarSuperficie();
        disponibilidad = cargarDisponibilidad();
        precio = cargarPrecio();

        p = new Propiedad(codigo, tipo, direccion, cantAmbientes, operacion, superficie, disponibilidad, precio);
        return p;
    }

//Este modulo se encarga de solicitar que se ingrese un codigo para luego verificarlo
    public static int cargarCodigo() {
        Scanner pufu = new Scanner(System.in);
        boolean continuar = true;
        int codigo = 0;
        char opcion;

        while (continuar != false) {
            System.out.println("Ingrese el codigo");
            codigo = pufu.nextInt();
            System.out.println("¿El codigo ingresado es correcto? s/n");
            opcion = pufu.next().charAt(0);
            switch (opcion) {
                case 's':
                    continuar = false;
                    break;
                case 'n':
                    continuar = true;
                    break;
                default:
                    continuar = true;
            }
        }
        return codigo;
    }

    public static boolean verificarCodigo(Propiedad[] nuevoArreglo, int codigo, int longitud) {
        boolean retorno = true;
        Propiedad p = new Propiedad(codigo);

        if (longitud == 0) {
            if (nuevoArreglo[longitud].equals(p)) {
                retorno = false;
            }
        } else {
            if (nuevoArreglo[longitud].equals(p)) {
                retorno = false;
            } else {
                retorno = true && verificarCodigo(nuevoArreglo, codigo, longitud - 1);
            }
        }
        return retorno;
    }

    //Solicita el ingreso del tipo de Propiedad, verificando que sea un tipo valido
    public static char cargarTipo() {
        Scanner pufu = new Scanner(System.in);
        boolean continuar = true;
        char tipo = 0;

        while (continuar != false) {
            System.out.println("Ingrese el tipo");
            tipo = pufu.next().charAt(0);
            if (tipo == 'c' || tipo == 'd' || tipo == 'x') {
                continuar = false;
            }
        }
        return tipo;
    }

    //Solicita el ingreso de la direccion y pregunta si fue ingresada correctamente
    public static String cargarDireccion() {
        Scanner pufu = new Scanner(System.in);
        String direccion = "";
        boolean continuar = true;
        char opcion;

        while (continuar != false) {
            System.out.println("Ingrese la direccion");
            direccion = pufu.next();
            System.out.println("¿La direccion es correcta? s/n");
            opcion = pufu.next().charAt(0);

            switch (opcion) {
                case 's':
                    continuar = false;
                    break;
                case 'n':
                    continuar = true;
                    break;
                default:
                    continuar = true;
            }
        }
        return direccion;
    }

    //Solicita la cantidad de ambientes y pregunta si fue ingresada correctamente
    public static int cargarAmbientes() {
        Scanner pufu = new Scanner(System.in);
        boolean continuar = true;
        int cantAmbientes = 0;
        char opcion;

        while (continuar != false) {
            System.out.println("Ingrese la cantidad de ambientes");
            cantAmbientes = pufu.nextInt();
            System.out.println("¿La cantidad de ambientes es correcta? s/n");
            opcion = pufu.next().charAt(0);

            switch (opcion) {
                case 's':
                    continuar = false;
                    break;
                case 'n':
                    continuar = true;
                    break;
                default:
                    continuar = true;
            }
        }
        return cantAmbientes;
    }

    //Solicita la operacion de la Propiedad, verificando que sea una operacion valida
    public static char cargarOperacion() {
        Scanner pufu = new Scanner(System.in);
        boolean continuar = true;
        char operacion = 0;

        while (continuar != false) {
            System.out.println("Ingrese la operacion v/a/t");
            operacion = pufu.next().charAt(0);
            if (operacion == 'v' || operacion == 'a' || operacion == 't') {
                continuar = false;
            }
        }
        return operacion;
    }

    //Solicita la superficie y pregunta si fue ingresada correctamente
    public static int cargarSuperficie() {
        Scanner pufu = new Scanner(System.in);
        boolean continuar = true;
        char opcion;
        int superficie = 0;

        while (continuar != false) {
            System.out.println("Ingrese la superficie");
            superficie = pufu.nextInt();
            System.out.println("¿La superficie es correcta? s/n");
            opcion = pufu.next().charAt(0);

            switch (opcion) {
                case 's':
                    continuar = false;
                    break;
                case 'n':
                    continuar = true;
                    break;
                default:
                    continuar = true;
            }
        }
        return superficie;
    }

    //Solicita la disponibilidad de la Propiedad, verificando que sea una opcion valida
    public static boolean cargarDisponibilidad() {
        Scanner pufu = new Scanner(System.in);
        boolean continuar = true;
        char disp;
        boolean disponibilidad = false;
        while (continuar != false) {
            System.out.println("Ingrese la disponibilidad d/o");
            disp = pufu.next().charAt(0);
            switch (disp) {
                case 'd':
                    disponibilidad = true;
                    continuar = false;
                    break;
                case 'o':
                    disponibilidad = false;
                    continuar = false;
                    break;
                default:
                    continuar = true;
                    break;
            }
        }
        return disponibilidad;
    }

    //Solicita el precio de la Propiedad y pregunta si fue ingresado correctamente
    public static int cargarPrecio() {
        Scanner pufu = new Scanner(System.in);
        boolean continuar = true;
        char opcion;
        int precio = 0;

        while (continuar != false) {
            System.out.println("Ingrese el precio");
            precio = pufu.nextInt();
            System.out.println("¿El precio es correcto? s/n");
            opcion = pufu.next().charAt(0);

            switch (opcion) {
                case 's':
                    continuar = false;
                    break;
                case 'n':
                    continuar = true;
                    break;
                default:
                    continuar = true;
            }
        }
        return precio;
    }

    //Este modulo recorre el arreglo e imprime los datos de cada Propiedad por medio del metodo toString()
    public static void listarDatos(Propiedad[] arreglo) {
        for (int j = 0; j < arreglo.length; j++) {
            System.out.println(arreglo[j].toString());
        }
    }

    //Este modulo recorre el arreglo buscando todas las Propiedades que sean casas a la venta con una superficie superior a 100 y esten disponibles
    public static void verificarCasa(Propiedad[] arreglo) {
        char tipo, op;
        int sup, contador = 0;
        boolean disp;

        for (int j = 0; j < arreglo.length; j++) {
            tipo = arreglo[j].getTipo();
            op = arreglo[j].getOperacion();
            sup = arreglo[j].getSuperficie();
            disp = arreglo[j].getDisponibilidad();
            if (tipo == 'c' && op == 'v' && sup > 100 && disp) {
                System.out.println(arreglo[j].toString());
                contador++;
            }
        }
        if (contador == 0) {
            System.out.println("No hay propiedades que cumplan con las caracteristicas buscadas");
        }
    }

    //Este modulo recorre el arreglo buscando todas las Propiedades que sean departamentos en alquiler de un solo ambiente por menos de $20.000
    public static void verificarDepto(Propiedad[] arreglo) {
        char tipo, op;
        int cantAmbientes, precio, contador = 0;

        for (int j = 0; j < arreglo.length; j++) {
            tipo = arreglo[j].getTipo();
            op = arreglo[j].getOperacion();
            cantAmbientes = arreglo[j].getCantAmbientes();
            precio = arreglo[j].getPrecio();
            if (tipo == 'd' && op == 'a' && cantAmbientes == 1 && precio < 20000) {
                System.out.println(arreglo[j].toString());
                contador++;
            }
        }
        if (contador == 0) {
            System.out.println("No hay propiedades que cumplan con las caracteristicas buscadas");
        }
    }

    public static void ordenarXPrecio(Propiedad[] arreglo) {
        Scanner pufu = new Scanner(System.in);
        int opcion;

        System.out.println("Ingrese el tipo de ordenamiento que desea utilizar");
        System.out.println("1: Burbuja Mejorado");
        System.out.println("2: Seleccion");
        System.out.println("3: Insercion");
        opcion = pufu.nextInt();

        switch (opcion) {
            case 1:
                burbujaMejorado(arreglo);
                break;
            case 2:
                seleccion(arreglo);
                break;
            case 3:
                insercion(arreglo);
                break;
        }
    }

    public static void burbujaMejorado(Propiedad[] a) {
        int n = a.length, i = 0;
        boolean sinCambio = false;
        Propiedad p;

        while (i < n && !sinCambio) {
            sinCambio = true;
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j].getPrecio() > a[j + 1].getPrecio()) {
                    p = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = p;
                    sinCambio = false;
                }
            }
            i++;
        }
    }

    public static void seleccion(Propiedad[] a) {
        int i, j, min = 0;
        Propiedad p;

        for (i = 0; i < a.length - 1; i++) {
            min = i;

            for (j = i + 1; j < a.length; j++) {
                if (a[j].getPrecio() < a[min].getPrecio()) {
                    min = j;
                }
            }
        }
        p = a[i];
        a[i] = a[min];
        a[min] = p;
    }

    public static void insercion(Propiedad[] a) {
        int j;
        Propiedad p;

        for (int i = 1; i < a.length; i++) {
            p = a[i];
            j = i;
            while (j > 0 && p.getCodigo() < a[j - 1].getCodigo()) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = p;
        }
    }
    
    public static void contarPropiedades(Propiedad[] arreglo) {
        Scanner pufu = new Scanner(System.in);
        int cantPropiedades, cantAmbientes;
        
        System.out.println("Ingrese la cantidad de ambientes que desea buscar");
        cantAmbientes = pufu.nextInt();
        
        cantPropiedades = contar(arreglo, arreglo.length, cantAmbientes);
        System.out.println("Hay " + cantPropiedades + " con " + cantAmbientes + " ambientes");
    }
    
    public static int contar(Propiedad[] arreglo, int n, int buscado) {
        int retorno = 0;
        
        if(n == 0) {
            if(arreglo[n].getCantAmbientes() == buscado) {
                retorno = 1;
            }
        } else {
            if(arreglo[n].getCantAmbientes() == buscado) {
                retorno = 1 + contar(arreglo, n - 1, buscado);
            } else {
                retorno = contar(arreglo, n - 1, buscado);
            }
        }
        return retorno;
    }
    
    
}