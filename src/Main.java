
import java.util.Scanner;

class Pais {

    String nomPais;
    String[] nomPaisVotat = new String[10];
    int votosRecibidos;
}

public class Main {

    public static void main(String[] args) {
        final int N = 26; //cantidad de paises participantes
        Pais[] paisos = new Pais[N]; //array de paises
        int opF;
        
        intro();

        //0 rellenar los nombres de los paises
        nombrarPaisos(paisos);

        do {

            votacioPais(paisos);

            ordenarPaisos(paisos);
            mostrarPaisos(paisos);

            opF = -1;
            do {

                opF = menuFinal(opF);
                switch (opF) {
                    case 0:
                        System.out.println("\t\t\tAdeú!");
                        break;
                    case 1://metodo despues de escoger los paises
                        buidarPuntuacions(paisos);
                        break;
                    case 2: // para escoger paises
                        buidarPaisos(paisos);
                        nombrarPaisos(paisos);
                        break;
                    case 3: // Donat el nom, els vots totals rebuts i a quins 
                            //(mostrar nom) ha votat i amb quina puntuació.
                        mostrarVotsFets(paisos);
                        opF = -1;
                        break;
                    case 4: //Donat el nom, mostrin els països (mostrar nom) que
                            //l’han votat i amb quina puntuació
                        mostrarVotsRebuts(paisos);
                        opF = -1;
                        break;
                    case 5://Donat el N vot,(mostrar nom de país) que ha obtingut més vots N
                        //mostrarPaisPerVot(paisos);
                        mostrarPaisPerVot(paisos);
                        opF = -1;
                        break;
                    default:
                        System.out.println("Opció incorrecta, proba una altre vegada!: ");
                }
                System.out.println("");
            } while (opF<0 || opF>2);

        } while (opF != 0);

    }

    /*
    Rellenara el array paisos con los nombres aleatorios de 50 paises europeos/candidatos que participaran, evitando que los
    nombres se repitan.
     */
    public static String[] seleccionar26Paises(String[] paises) {
        String[] paisesEscogidos = new String[26];
        int indiceAleatorio;

        int i = 0;
        while (i < 26) {
            indiceAleatorio = (int) (Math.random() * paises.length);
            if (!Main.existeixPais(paisesEscogidos, indiceAleatorio, paises, i)) {
                paisesEscogidos[i] = paises[indiceAleatorio];
                i++;
            }

        }
        
        return paisesEscogidos;
    }

    private static void nombrarPaisos(Pais[] paisos) {

        String[] paisesEuropeos = {"España", "Francia", "Alemania", "Italia", "Reino Unido",
            "Portugal", "Suecia", "Suiza", "Noruega", "Grecia",
            "Países Bajos", "Bélgica", "Austria", "Finlandia", "Dinamarca",
            "Polonia", "Hungría", "República Checa", "Croacia", "Rumanía",
            "Bulgaria", "Eslovaquia", "Eslovenia", "Irlanda", "Luxemburgo",
            "Estonia", "Letonia", "Lituania", "Chipre", "Malta",
            "Islandia", "Liechtenstein", "Mónaco", "San Marino", "Andorra",
            "Vaticano", "Moldavia", "Bielorrusia", "Ucrania", "Rusia",
            "Albania", "Macedonia del Norte", "Montenegro", "Serbia", "Bosnia y Herzegovina",
            "Kosovo", "Georgia", "Armenia", "Azerbaiyán", "Turquía", "Suiza", "Ucrania"};

        String[] paisesEscogidos = seleccionar26Paises(paisesEuropeos);

        for (int i = 0; i < paisos.length; i++) {
            Pais p = new Pais();
            p.nomPais = paisesEscogidos[i];
            
            p.votosRecibidos=0; //ampliacion 2
            
            paisos[i] = p;
        }
    }

    private static boolean existeixPais(String[] paisesEscogidos, int indiceAleatorio, String[] paises, int ind) {
        boolean salida = false;

        for (int i = 0; i < ind; i++) {
            if (paisesEscogidos[i].equals(paises[indiceAleatorio])) {
                salida = true;
            }
        }

        return salida;
    }

    /*
    Busca si existe el nombrePais del array paisos  dentro del array puntPerPais hasta el index "j" para evitar fallos de null
    */
    private static boolean existeixPais(Pais[] paisos, int paisVotante, int paisIndex, int paisVotado) {
        boolean trobat = false;
        
        int i = 0;
        trobat = paisos[paisVotante].nomPais.equals(paisos[paisIndex].nomPais);
        while ( i < paisVotado && !trobat) {

            //System.out.printf("esixteix i %d j %d p %s pn %s\n",i,paisVotado,paisos[paisVotante].nomPais,paisos[paisVotado].nomPaisVotat[i]);
            if (paisos[paisVotante].nomPaisVotat[i].equals(paisos[paisIndex].nomPais)) {
                trobat = true;
            } else {
                i++;
            }
        }

        return trobat;
    }
    
    /*
    
    */
    private static boolean existeixPais(Pais[] paisos, String nPais) {
        boolean trobat = false;
        
        int i = 0;
        while ( i < paisos.length && !trobat) {

            //System.out.printf("esixteix i %d j %d p %s pn %s\n",i,paisVotado,paisos[paisVotante].nomPais,paisos[paisVotado].nomPaisVotat[i]);
            if (paisos[i].nomPais.equals(nPais)) {
                trobat = true;
            } else {
                i++;
            }
        }

        return trobat;
    }
    

    /*
    Votacion de los paises del array paisos
     */
    private static void votacioPais(Pais[] paisos) {
        int paisIndexNom; //variables que almacenaran los datos del indice de pais y los puntos.
        boolean trobat; // variable 
        
        // paisos.length = 26       paisQueVotara.nomPaisVotat.length = 10
        for (int paisVotante = 0; paisVotante < paisos.length; paisVotante++) {
            
            int paisVotado = 0;
            for (paisVotado = 0; paisVotado < paisos[paisVotante].nomPaisVotat.length; paisVotado++) {
                //Pais p = new Pais();
                do {
                    paisIndexNom = (int) (Math.random() * paisos.length);//valor random de index del pais
                    //System.out.println("");
                    trobat = existeixPais(paisos, paisVotante, paisIndexNom, paisVotado);
                    //trobat = existeixPais(paisQueVotara, paisIndexNom, paisos, paisVotado);
                    if (!trobat) { //si no encuentra pais repetido i paisIndex diferente del pais votante
                        paisos[paisVotante].nomPaisVotat[paisVotado] = paisos[paisIndexNom].nomPais;
                    }
                    
                } while (trobat); //buscar index de pais que no este dentro del array i deferente de pVotante

                paisos[paisVotante].nomPaisVotat[paisVotado] = paisos[paisIndexNom].nomPais;
                
            }
            
            sumarVots(paisos, paisVotante);
            
        }
    }

    /*
    Sumara los vots dentro de 
     */
    private static void sumarVots(Pais[] paisos, int pVotante) {
        
        for (int i = 0; i < paisos[0].nomPaisVotat.length ; i++) {
            for (int j = 0; j < paisos.length; j++) {
                if(paisos[pVotante].nomPaisVotat[i].equals(paisos[j].nomPais)){
                    
                    //ampliacion 2
                    paisos[j].votosRecibidos += 1;
                }
            }
        }
    }
    

    /*
    Mostrara una lista de paises mediante el uso del array paisos mostrando de 
    forma decreciente los paises segun la cantidad de puntos acumulados
     */
    private static void mostrarPaisos(Pais[] paisos) {
        int count = 1,vtos=0;
        
        System.out.println("\t\t\t\t\tPuntuacions!!!\n");
        System.out.print("\t\t\t");
        for (int i = 0; i < 50; i++) System.out.print("-");
        System.out.println("");
        System.out.print("\t\t\t| Posició    |      Pais    |   Punts   |  Vots  |\n");
        System.out.print("\t\t\t");
        for (int i = 0; i < 50; i++) System.out.print("-");
        System.out.println("");
        for (int i = 0; i < paisos.length; i++) {
            System.out.printf("\t\t\t%5d)%20s%7d punts %7d\n", count+i,paisos[i].nomPais, contarPunts(paisos, i),paisos[i].votosRecibidos);
        
            //vtos += paisos[i].votosRecibidos;
        }
        System.out.print("\t\t\t");
        for (int i = 0; i < 50; i++) System.out.print("-");
        System.out.println("");
        
        //System.out.println("vtos "+vtos);
    }

    /*
    Funcion que contara los puntos acumulados de todos los paises que votaron a 
    "paisVotado"
    */
    private static int contarPunts(Pais[] paisos, int paisVotado) {
        int[] puntos = {1, 2, 3, 4, 5, 6, 7, 8, 10, 12};
        int punts = 0;
        
        for (int i = 0; i < paisos.length; i++) {
            for (int j = 0; j < paisos[i].nomPaisVotat.length; j++) {
                if(paisos[paisVotado].nomPais.equals(paisos[i].nomPaisVotat[j])){
                    punts += puntos[j];
                }
            }
        }
        
        return punts;
    }

    /*
    Ordenara los paises mendiante la array paisos ordenado de forma decreciente 
    los paises segun la suma de todos los votos recividos
     */
    private static void ordenarPaisos(Pais[] paisos) {
        Pais p = new Pais();

        int i = 0;
        while (i < paisos.length - 1) {
            if (contarPunts(paisos, i) < contarPunts(paisos, i+1)) {
                p = paisos[i];
                paisos[i] = paisos[i + 1];
                paisos[i + 1] = p;

                i = 0;
            } else {
                    i++;
            }
        }
        
        desempatador(paisos);
        
    }

    /*
    El menu mostrara las posibles interacciones disponibles dentro del programa 
    */
    public static int menuFinal(int p) {
        Scanner entrada = new Scanner(System.in);

        if (p < 0) {
            System.out.println("\n\t\t\t\t\tHas acabat el concurs!\n");
            System.out.println("\t\t\tIndica que vols fer ara:\n");
            System.out.println("\t\t\t1) Nou concurs amb els mateixos paisos");
            System.out.println("\t\t\t2) Nou concurs amb paisos diferents");
            System.out.println("\t\t\t3) Contar votos total recibidos y votos dados.");
            System.out.println("\t\t\t4) Votos recibidos de los paises.");
            System.out.println("\t\t\t5) Mostrar amb mes vots.");
            System.out.println("\t\t\t0) Sortir\n");
        }

        System.out.print("\t\t\tOpció: ");

        return entrada.nextInt();
    }

    /*
    Vaciara los nombres del array nomPaisVotat del objeto paisos dejadolos a null
    para una nueva votacion 
    */
    private static void buidarPuntuacions(Pais[] paisos) {
        for (int i = 0; i < paisos.length; i++) {
            for (int j = 0; j < paisos[i].nomPaisVotat.length; j++) {
                paisos[i].nomPaisVotat[j] = null;
            }
            paisos[i].votosRecibidos = 0;
        }
    }

    /*
    Vaciara los objetos pais del array paisos dejandolos todos a null
    para un posterior llenado de nuevos paises
    */
    private static void buidarPaisos(Pais[] paisos) {
        for (int i = 0; i < paisos.length; i++) {
            paisos[i] = null;
        }
    }

    private static void desempatador(Pais[] paisos) {
        Pais p = new Pais();

        int i = 0;
        while (i < paisos.length - 1) {
            if (contarPunts(paisos, i) == contarPunts(paisos, i+1)) {  //en caso de empate del primer y segundo lugar
                
                if(paisos[i+1].votosRecibidos>paisos[i].votosRecibidos){
                    p = paisos[i];
                    paisos[i] = paisos[i+1];
                    paisos[i+1] = p;
                    
                    i = 0;
                } else {
                    i++;
                }
            
            } else {
                i++;
            }
        }
    }

    private static void intro() {
        System.out.println(""
                + "..................................................................................................\n" +
"..................................................................................................\n" +
"......................................................=%@=.................++.....................\n" +
".........:-:.................................--....*%:...%@@...............:@@....................\n" +
".....*@@@@@@-.............................*@#...@+@.......%@@..............+@@#...................\n" +
"...*@@#:.................................@@......*........+@@...............::....................\n" +
"..#@...........@@..+#..#@@@@@@@=..@@@-..+@+...............%@@.#@..*@@@@@...@@:...-@@@.....@@+@@=..\n" +
"..@##%@@@@@#..*@@ .*@:.:@*....@%.@@@-@..#@-..............+@@:.@@:*@+.......@@%..@@@*@....@@@*#@@@.\n" +
".....@@@@+....%@*..%@*.:@+=%@@-.@@@...@.*@#.............@@@..%@@ .@@:......@@*.@@@..:@..-@@...-@@:\n" +
"...#@@........%@:..@@+.*@@@.....@@=...#%.@@*..........:@@*..-@@=.....:-@@.=@@..@@:...*@ +@@ ..:@@.\n" +
"..@@.........-#@..*@@..%@.@@#...@@-.. %@..%@@........@@#....%@@.....-@@@*.@@#..@@....*@.*@%....@@.\n" +
".:@........@@.:@@@@@-..%%..+@@..-@@:.@@=....%@@-...@@+......@@....@@@@....@@...%@*..+@#.+@=....@@.\n" +
"..@*...-@@@@....@@%....%.....%@...%@@%........:@@+@+........%:..@@+.......=+....-@@@%:...@......@.\n" +
"...=@@@@@-.......................................*................................................\n" +
"..................................................................................................\n" +
"...................*@=..:@@:..#..#:..#@=.....-@%...*@=..#-.-+.#### %###..%%:.%##%.................\n" +
"..................-@#:.#*..**.@%=@::@.=+:...%:....@...@ @*%=*. #-..@#*+.*@*...#+..................\n" +
".      ..   ..    =%-@..@++@..@.-@:.%%=@-. .:@+#*.%%=@=.@:.@*. #-..@+++.%++@..#+.       ..  .. ...\n" +
"..................................................................................................\n" +
"..................................................................................................\n" +
"");
    }

    private static void mostrarVotsFets(Pais[] paisos) {
        Scanner scanner = new Scanner(System.in);
        int[] punts = {1, 2, 3, 4, 5, 6, 7, 8, 10, 12};
        System.out.print("\n\t\t\tIndica nombre del país: ");
        String paisSeleccionado = scanner.nextLine();
        int votos = 0;
        System.out.print("\t\t\t");
        for (int i = 0; i < 50; i++) System.out.print("-");
        System.out.println("");
        
        if(existeixPais(paisos, paisSeleccionado)){
            for (Pais pais : paisos) {
                if (pais.nomPais.equals(paisSeleccionado)) {
                    System.out.printf("\t\t\tEl país %s ha recibido %d votos.\n", pais.nomPais, pais.votosRecibidos);
                    System.out.println("\t\t\tHa dado los siguientes votos:");
                    //usar puntos de contarpuntos para el valor a votos
                    for (int i = 0; i < pais.nomPaisVotat.length; i++) {
                        System.out.printf("\t\t\t%-20s: %20d votos\n", pais.nomPaisVotat[i], punts[i]);
                    }
                }
            }
        } else {
            System.out.println("\t\t\tPais no trobat!!");
        }
        
        System.out.print("\t\t\t");
        for (int i = 0; i < 50; i++) System.out.print("-");
        System.out.println("");
    }


    private static void mostrarVotsRebuts(Pais[] paisos) {
        Scanner entrada = new Scanner(System.in);
        System.out.print("\t\t\tIndica el país: ");
        String paisSeleccionado = entrada.nextLine();
        System.out.println("");

        System.out.print("\t\t\t");
        for (int i = 0; i < 50; i++) {
            System.out.print("-");
        }
        System.out.println("");
        
        if(existeixPais(paisos, paisSeleccionado)){
            for (Pais pais : paisos) {
                if (pais.nomPais.equals(paisSeleccionado)) {
                    System.out.printf("\t\t\t\t\t\t%s\n", paisSeleccionado);
                    System.out.printf("\t\t\t\t\tHa recibido %d votos.\n", pais.votosRecibidos);
                    System.out.println("\t\t\t\tHa sido votado por los siguientes países:\n");

                    for (int i = 0; i < paisos.length; i++) {
                        for (int j = 0; j < paisos[j].nomPaisVotat.length; j++) {
                            if (paisos[i].nomPaisVotat[j].equals(pais.nomPais)) {
                                System.out.print("\t\t\t\t\t");
                                System.out.printf("%s --> %d puntos\n", paisos[i].nomPais, obtenerValorVoto(j));
                            }
                        }

                    }
                }
            }
        } else {
            System.out.println("\t\t\tPais no trobat!!");
        }
        
        System.out.print("\t\t\t");
        for (int i = 0; i < 50; i++) {
            System.out.print("-");
        }
    }
    

    private static int obtenerValorVoto(int indice) {
        int[] valoresVoto = {1, 2, 3, 4, 5, 6, 7, 8, 10, 12};
        return valoresVoto[indice];
    }

    private static void mostrarPaisPerVot(Pais[] paisos) {
        Scanner sc = new Scanner(System.in);
        int voto, pMesVotat=0;
        int[] nVots = new int[paisos.length];
        
        do {
            System.out.print("Introdueix el vot: ");
            voto = sc.nextInt();
        } while (buscarVot(voto)<0);
        
        
        for (int i = 0; i < paisos.length; i++) {
            
            nVots[buscarIndexVot(paisos,i,voto)] += 1;
        }
        
        for (int i = 0; i < paisos.length; i++) {
            System.out.printf("%s %d\n",paisos[i].nomPais,nVots[i]);
        }
        
        for (int i = 0; i < paisos.length; i++) {
            if(nVots[i]>nVots[pMesVotat]){
                pMesVotat= i;
            }
        }
        
        System.out.printf("El pais amb mes vots de %d es %s",voto,paisos[pMesVotat].nomPais);
        
    }

    private static int buscarIndexVot(Pais[] paisos, int i, int voto) {
        int index =-1;
        
        int j = 0;
        while (j<paisos.length && index<0) {          
            if(paisos[j].nomPais.equals(paisos[i].nomPaisVotat[buscarVot(voto)])){
                index = j;
            } else {
                j++;
            }
        }
        
        
        return index;
    }
    
    private static int buscarVot(int voto) {
        boolean trobat= false;
        int[] valoresVoto = {1, 2, 3, 4, 5, 6, 7, 8, 10, 12};
        int index = -1;
        
        int i = 0;
        while (i<valoresVoto.length && !trobat) {            
            if(voto==valoresVoto[i]){
                trobat= true;
                index = i;
                //System.out.println("voto "+voto+" inde "+index+" val "+valoresVoto[i]);
            } else {
                i++;
            }
        }
        
        return index;
    }

    private static void determinarMesVots(Pais[] paisos)  {        
        Scanner scanner = new Scanner(System.in);
        int puntuacio;

        do {
            System.out.print("\t\t\tIntrodueix la puntuació (1, 2, 3, 4, 5, 6, 7, 8, 10, o 12): ");

            while (!scanner.hasNextInt()) {
                System.out.println("\t\t\tError: Introdueix un valor numèric enter.");
                scanner.nextLine(); // Limpia el buffer de entrada
                System.out.print("\t\t\tIntrodueix la puntuació (1, 2, 3, 4, 5, 6, 7, 8, 10, o 12): ");
            }

            puntuacio = scanner.nextInt();

            if (puntuacio < 1 || puntuacio > 8 || puntuacio == 9 || puntuacio == 11 || puntuacio > 12) {
                System.out.println("\t\t\tError: Introdueix una puntuació vàlida.");
            }

        } while (puntuacio < 1 || puntuacio > 8 || puntuacio == 9 || puntuacio == 11 || puntuacio > 12);

        System.out.println("punt "+puntuacio);
        
        String paisMesVots = null;
        int mesVots = -1;

        for (int i = 0; i < paisos.length; i++) {
            int votsPais = 0;
            
            for (int j = 0; j < paisos[i].nomPaisVotat.length; j++) {
                if (obtenerValorVoto(j) == puntuacio) {
                    votsPais++;
                }
            }
            
            

            if (votsPais > mesVots) {
                mesVots = votsPais;
                paisMesVots = paisos[i].nomPais;
            }
            System.out.println(paisMesVots+" "+mesVots);
        }

        if (paisMesVots != null) {
            System.out.printf("\t\t\tEl país amb més vots (%d punts) és: %s amb %d vots.\n", puntuacio, paisMesVots, mesVots);
        } else {
            System.out.printf("\t\t\tNo hi ha països amb vots per %d punts.\n", puntuacio);
        }

        // Después de leer un entero, limpiar el buffer de entrada
        scanner.nextLine();
    }

}
