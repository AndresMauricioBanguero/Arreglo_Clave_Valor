package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase ManejoClientes que permite crear, consultar, listar, modificar y eliminar clientes.
 */
public class ManejoClientes {

    // Variables para la lectura de entrada por consola
    private InputStreamReader isr;
    private BufferedReader br;
    // Mapa para almacenar los clientes
    private Map<Integer, Cliente> mapaManejoClientes;

    /**
     * Constructor de la clase que inicializa las variables y llama al método menu.
     */
    public ManejoClientes() {
        isr = new InputStreamReader(System.in);
        br = new BufferedReader(isr);
        mapaManejoClientes = new HashMap<>();
        menu();
    }

    /**
     * Método que muestra el menú principal y permite realizar las distintas acciones relacionadas con los clientes.
     */
    private void menu() {
        int opcion = 0;
        do {
            System.out.println("\n\nMENU PRINCIPAL\n");
            System.out.println("1. Crear cliente");
            System.out.println("2. Consultar por identificacion");
            System.out.println("3. Listar clientes");
            System.out.println("4. Modificar cliente");
            System.out.println("5. Eliminar cliente");
            System.out.println("6. Salir");
            opcion = capturarEntero("ALTERNATIVA");
            switch (opcion) {
                case 1 -> crearCliente();
                case 2 -> consultarPorIdentificacion();
                case 3 -> listarClientes();
                case 4 -> modificarCliente();
                case 5 -> eliminarCliente();
                case 6 -> System.out.println("se acabo");
                default -> System.out.println("ingrese un numero entre los rangos del 1 al  6");
            }
        } while (opcion >= 0 && opcion <= 5);
    }

    /**
     * Método para eliminar un cliente en el sistema
     */
    private void eliminarCliente() {
        System.out.println("\nELIMINAR CLIENTE\n");
        int identificacion = capturarEntero("Ingrese identificacion");
        // Verificar si la identificacion ingresada existe en el mapa
        if (mapaManejoClientes.containsKey(identificacion)) {
            // Eliminar cliente
            mapaManejoClientes.remove(identificacion);
        } else {
            System.out.println("Identificacion no existe en el sistema");
        }
    }

    /**
     * Método para modificar la información de un cliente en el sistema
     */
    private void modificarCliente() {
        System.out.println("\nMODIFICAR CLIENTE\n");
        int identificacion = capturarEntero("Ingrese identificacion");
        // Verificar si la identificacion ingresada existe en el mapa
        if (mapaManejoClientes.containsKey(identificacion)) {
            Cliente cliente = null;
            System.out.println("\nDATOS ACTUALES: ");
            cliente = mapaManejoClientes.get(identificacion);
            System.out.println(cliente);
            System.out.println("\nNUEVOS DATOS: ");
            String nombre = capturarCadena("Ingrese nuevo nombre");
            String correo = capturarCadena("Ingrese nuevo correo");
            // Modificar la información del cliente
            cliente.setNombre(nombre);
            cliente.setCorreo(correo);
            // Actualizar el mapa con la información modificada del cliente
            mapaManejoClientes.put(identificacion, cliente);
        } else {
            System.out.println("Identificacion no existe en el sistema");
        }
    }

    /**
     * Método para listar todos los clientes registrados en el sistema
     */
    private void listarClientes() {
        System.out.println("\nLISTA DE CLIENTES\n");
        // Obtener el conjunto de claves en el mapa de manejo de clientes
        Iterator<Integer> keys = mapaManejoClientes.keySet().iterator();
        int cont = 0;
        // Recorrer el conjunto de claves
        while (keys.hasNext()) {
            cont++;
            Integer clave = keys.next();
            // Imprimir la información del cliente correspondiente a la clave
            System.out.println(mapaManejoClientes.get(clave));
        }
        if (cont == 0) {
            System.out.println("\n*** No hay clientes registrados ***\n");
        }
    }

    /**
     * Método para consultar un cliente en el sistema a partir de su identificación
     */
    private void consultarPorIdentificacion() {
        System.out.println("\nCONSULTAR POR IDENTIFICACION\n");
        // Capturar la identificación del cliente a consultar
        Integer identificacion = capturarEntero("Ingrese identificacion");
        // Verificar si existe un cliente con la identificación especificada
        if (mapaManejoClientes.containsKey(identificacion)) {
            // Imprimir la información del cliente
            System.out.println("Datos del cliente: ");
            System.out.println(mapaManejoClientes.get(identificacion));
        } else {
            // En caso de que no exista un cliente con la identificación especificada, imprimir un mensaje de error
            System.out.println("Identificacion no existe en el sistema");
        }
    }

    /**
     * Método para crear un nuevo cliente en el sistema
     */
    private void crearCliente() {
        System.out.println("\nCREAR CLIENTE\n");
        // Capturar la identificación del cliente
        int identificacion = capturarEntero("Ingrese identificacion");
        // Verificar si existe un cliente con la identificación especificada
        if (this.mapaManejoClientes.containsKey(identificacion)) {
            // En caso de que ya exista un cliente con la identificación especificada, imprimir un mensaje de error
            System.out.println("Error, numero de identificacion ya existe");
        } else {
            // Capturar los datos del cliente
            String nombre = capturarCadena("Ingrese nombre");
            String correo = capturarCadena("Ingrese correo");
            // Crear un objeto de tipo Cliente con los datos capturados
            Cliente cliente = new Cliente(identificacion, nombre, correo);
            // Agregar el cliente al mapa de manejo de clientes
            mapaManejoClientes.put(identificacion, cliente);
        }
    }

    /**
     * Método para capturar un valor entero de la entrada del usuario
     *
     * @param texto una cadena que se muestra como una solicitud para que el usuario ingrese el número entero
     * @return un valor entero capturado del usuario
     */
    private Integer capturarEntero(String texto) {
        Integer resp = 0;
        try {
            // Imprime el mensaje y lee la entrada del usuario
            System.out.print(texto + ": ");
            String aux = br.readLine();
            // Analiza la entrada del usuario como un entero
            resp = Integer.parseInt(aux);
        } catch (Exception ex) {
            // En caso de que la entrada del usuario no sea un entero válido, se muestra un mensaje de error
            System.out.println("Error, ingrese un valor entero");
        }
        return resp;
    }

    /**
     * Captura una cadena de texto ingresada por el usuario.
     *
     * @param texto el texto a mostrar al usuario
     * @return la cadena de texto ingresada por el usuario
     */
    private String capturarCadena(String texto) {
        try {
            System.out.print(texto + ": ");
            String aux = br.readLine();
            return aux;

        } catch (IOException ex) {
            Logger.getLogger(ManejoClientes.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}

