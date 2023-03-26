package org.example;

import exercici1.Month;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Exercici 1 --------------------------------------------------------------------------------------------------

        Month gener = new Month("gener");
        Month febrer = new Month("febrer");
        Month marc = new Month("marc");
        Month abril = new Month("abril");
        Month maig = new Month("maig");
        Month juny = new Month("juny");
        Month juliol = new Month("juliol");
        Month setembre = new Month("setembre");
        Month octubre = new Month("octubre");
        Month novembre = new Month("novembre");
        Month desembre = new Month("desembre");

        ArrayList<Month> listMesos = new ArrayList<>();

        HashSet<Month> hashSetMesos;

        Iterator<Month> monthIterator;

        listMesos.add(gener);
        listMesos.add(febrer);
        listMesos.add(marc);
        listMesos.add(abril);
        listMesos.add(maig);
        listMesos.add(juny);
        listMesos.add(juliol);
        listMesos.add(null);
        listMesos.add(setembre);
        listMesos.add(octubre);
        listMesos.add(novembre);
        listMesos.add(desembre);

        // Mostramos los meses para validar que el ArrayList muestra el orden correcto de inserción
        System.out.println("\nArrayList de mesos:\n" + listMesos);

        // Creamos el HashSet pasandole por el constructor la lista para que asi este tenga los valroes de la lista
        // y para probar que no acepta duplicados le añadiremos valores duplicadors y lo comprobaremos
        hashSetMesos = new HashSet<>(listMesos);

        hashSetMesos.add(gener);

        // Cuando mostramos por pantalla solo tenemos 1 mes llamado 'gener' ya que no acepta duplicados
        System.out.println("\nHashSet de mesos:\n" + hashSetMesos);

        // Recorremos las listas con un for y un iterador

        System.out.println("\nArrayList de mesos (Recorrido con un for):\n");

        for (Month mes : listMesos) {
            System.out.println(mes);
        }

        System.out.println("\nArrayList de mesos (Recorrido con un iterador):\n");

        monthIterator = listMesos.iterator();

        while (monthIterator.hasNext()) {

            System.out.println(monthIterator.next());

        }

        System.out.println("\nHashSet de mesos (Recorrido con un for):\n");

        for (Month mes : hashSetMesos) {
            System.out.println(mes);
        }

        System.out.println("\nHashSet de mesos (Recorrido con un iterador):\n");

        monthIterator = hashSetMesos.iterator();

        while (monthIterator.hasNext()) {

            System.out.println(monthIterator.next());

        }

        // Exercici 2 --------------------------------------------------------------------------------------------------

        // Crea i emplena un List<Integer>.
        List<Integer> numeros = new ArrayList<>();
        List<Integer> numerosInversos = new ArrayList<>();
        ListIterator<Integer> listIterator;

        numeros.add(1);
        numeros.add(2);
        numeros.add(3);
        numeros.add(4);
        numeros.add(5);

        System.out.println("\nArrayList números:\n" + numeros);

        // Crea un segon List<Integer> i insereix a la segona llista els elements de la primera en ordre invers.

        // Recorremos la lista que tenemos en inverso y los vamos añadiendo en la nueva lista al mismo tiempo
        for (int i = numeros.size() - 1; i >= 0; i--) {
            numerosInversos.add(numeros.get(i));
        }

        System.out.println("\nArrayList de números inversos:\n" + numerosInversos);

        // Empra els objectes ListIterator per a llegir els elements de la primera llista i inserir-los en la segona.
        listIterator = numeros.listIterator();

        while (listIterator.hasNext()) {
            numerosInversos.add(listIterator.next());
        }

        System.out.println("\nElementos de la primera lista añadidos en la segunda:\n" + numerosInversos);

        // Exercici 3 --------------------------------------------------------------------------------------------------

        HashMap<String, String> countries = new HashMap<>();
        File fileToRead = new File("Countries.txt");
        File fileWithScores = new File("classificacio.txt");
        FileWriter writer;
        String line, userName = "", country, answer;
        Scanner lector;
        String[] splittedCountries;
        Random random = new Random();
        List<String> countryNames;
        int score = 0, randomNumber;
        Set<Integer> generados = new HashSet<>();

        try {

            lector = new Scanner(fileToRead);

            while (lector.hasNextLine()) {

                line = lector.nextLine();

                // Esta comprobación se hace ya que la última línea del fichero está vacía y al hacer el split, peta.
                // De esta manera nos evitamos que falle por una línea vacía
                if (!line.isEmpty()) {

                    // Separamos las líneas por espacio guardándose en el 1er espacio el país y en el segundo la capital
                    splittedCountries = line.split(" ");

                    // Guardamos en el HashMap los paises por 'País' 'Capital'
                    countries.put(splittedCountries[0], splittedCountries[1]);

                }

            }

            lector.close();

        } catch (Exception e) {

            throw new RuntimeException("ERROR: " + e);

        }

        // Guardamos los paises en un arraylist
        countryNames = new ArrayList<>(countries.keySet());

        // Reutilizamos la variable lector para pedir el usuario por pantalla
        lector = new Scanner(System.in);

        // Pedimos el nombre del usuairo
        System.out.println("\nIntrodueix nom del usuari:");
        userName = lector.nextLine();

        System.out.println("\nBenvingut/a " + userName + ". A continuació es mostrarán 10 països i has d'acertar la seva capital. " +
                "Cada resposta correcta sumará 1 punt, fins a un maxim de 10 punts. Bona sort!\n");

        // Hacemos un bucle de 10 interacciones para ir preguntando las capitades de los paises
        for (int i = 0; i < 10; i++) {

            // Este while se hace para evitar repetir que se pregunte los mismos paises
            while (true) {

                randomNumber = random.nextInt(countryNames.size());

                if (generados.isEmpty()) {
                    generados.add(randomNumber);
                    break;
                }

                if (!generados.contains(randomNumber)) {
                    generados.add(randomNumber);
                    break;
                }

            }

            country = countryNames.get(randomNumber);

            System.out.println("La capital de " + country + " es:");
            answer = lector.nextLine();

            // Si la respuesta es igual al valor de la llave (si la capital es correcta respecto al pais), sumamos 1 punto
            if (answer.equals(countries.get(country))) {
                score++;
            }

        }

        System.out.println("Molt be " + userName + "! Has finalitzat el joc amb una puntuació de: " + score + "." +
                " Aquesta informació es guardará al fitxer 'classificacio.txt' dins d'aquest mateix projecte");

        try {

            // Creamos el fichero, si ya existe, no hará nada
            fileWithScores.createNewFile();

            // Le ponemos el append true para que escriba en el fichero y no lo sobreescriba
            writer = new FileWriter(fileWithScores, true);

            writer.write("Nom jugador: " + userName + ", puntuació: " + score + "\n");

            writer.close();

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }

}