/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pendujpo;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author pascal
 */
public class PenduJPO {

    static final char CHAR_AFFICHAGE_HORIZONTAL = '-';
    static final char CHAR_AFFICHAGE_VERTICAL = '|';
    static final char CHAR_LETTRE_CACHEE = '_';
    static char ancienneLettre = '_';
    static int nbrPartieJoue = 0;
    static int nbrPartieGagne = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        menuPendu();

    }

    /**
     * Fonction qui choisis un mot aléatoire parmis l'énumération Mots.java et
     * return le mot en tableau 1d de caractère
     *
     * @return
     */
    static char[] selectionMot() {
        Mots[] valeurs = Mots.values();
        Random random = new Random();
        Mots motAléatoire = valeurs[random.nextInt(valeurs.length)];
        return motAléatoire.toString().toCharArray();
    }

    /**
     * Permet de créer un mot "caché", c'est à dire créer un mot remplie de
     * crochet qui fait la même taille que le mot rentré en paramètre
     *
     * @param mot
     * @return
     */
    static char[] creationMotCache(char[] mot) {
        char[] motcache = new char[mot.length];
        for (int i = 0; i < mot.length; i++) {
            if (mot[i] != ' ') {
                motcache[i] = CHAR_LETTRE_CACHEE;
            }
        }
        return motcache;
    }

    /**
     * Fonction qui permet de choisir une lettre minuscule avec la fonction
     * scanner, renvoit le caractère rentré que si c'est une lettre minuscule
     *
     * @return
     */
    static char selectionLettre() {
        System.out.println("Choisis une lettre");
        Scanner scanner = new Scanner(System.in);
        String lettre = scanner.next();
        while (!lettre.matches("[a-zéèëêàâùûüïîô]")) {
            System.out.println("Lettre invalide, choisis uniquement une lettre en minuscule");
            System.out.println("Choisis une lettre");
            lettre = scanner.next();
        }
        return lettre.charAt(0);
    }

    /**
     * CODE DU JEU PRINCIPAL :
     *
     * Les mots ne fonctionne pas avec des Strings mais avec des tableaux de
     * caractère, des tableaux 1D
     *
     * il y a un tableau de caractère pour : le mots aléatoire, choisis dans
     * l'énumération Mots.java
     *
     * On retourne un booleen, VRAI si il veut quitter le jeu et FAUX si il veut
     * retourner au menu
     */
    static boolean jeuPrincipal(int niveau) {
        nbrPartieJoue++;
        boolean fin;
        char[] motAleatoire = selectionMot();// La fonction selectionMot() permet de choisir aléatoirement un mot parmis une liste de mots
        char[] motCache = creationMotCache(motAleatoire);//Permet de créer un mot caché en transformant les lettres du mot mit en paramètres, par des tiré du 8
        char lettrePropose;
        char[] mauvaiseLettres = new char[10];//Max 10 erreurs
        mauvaiseLettres = convertionPointExclamation(mauvaiseLettres);//Pour l'affichage, le tableau d'erreurs doit être remplie de !
        boolean lettreBonne = false;
        int score = 0;// score = nombre de mauvaise lettres proposé 
        if (niveau == 1) {
            System.out.println(Afficher(motCache, mauvaiseLettres, 1, ancienneLettre));
        } else {
            String[] lignes = Afficher(motCache, mauvaiseLettres, 1,
                    ancienneLettre).split("\n");
            for (String ligne : lignes) {
                System.out.println("\u001B[31m" + ligne + "\u001B[0m");
            }
        }
        while (score < 10 && !comparaison(motAleatoire, motCache)) {//la fonction comparaison renvoie vrai si les 2 mots sont les mêmes
            lettrePropose = selectionLettre();//la fonction selectionLettre() permet de choisir une lettre entre a et z et les accents compris
            for (int i = 0; i < motAleatoire.length; i++) {
                if (motAleatoire[i] == lettrePropose) {
                    motCache[i] = lettrePropose;
                    lettreBonne = true;
                }

            }
            if (!lettreBonne) {//si la lettre n'est pas bonne elle est rajouté dans le tableau de caractère a l'indice score
                mauvaiseLettres[score] = lettrePropose;
                score++;
                if (niveau == 1) {
                    System.out.println(Afficher(motCache, mauvaiseLettres, niveau, ancienneLettre));
                } else {
                    String[] lignes = Afficher(motCache, mauvaiseLettres, niveau, ancienneLettre).split("\n");
                    for (String ligne : lignes) {
                        System.out.println("\u001B[31m" + ligne + "\u001B[0m");
                    }
                }
            } else {
                if (niveau == 1) {
                    System.out.println(Afficher(motCache, mauvaiseLettres, niveau, lettrePropose));
                } else {
                    String[] lignes = Afficher(motCache, mauvaiseLettres, niveau, lettrePropose).split("\n");
                    for (String ligne : lignes) {
                        System.out.println("\u001B[31m" + ligne + "\u001B[0m");
                    }
                }
                ancienneLettre = lettrePropose;
            }
            lettreBonne = false;//réinitialisation du booléen pour la prochaine lettre
        }

        if (score < 10) { // permet d'identifier si on doit afficher une victoire ou une défaite, et d'enregistrer son choix si il veut rejouer
            fin = ecranVictoire(score, motAleatoire);
            nbrPartieGagne++;
        } else {
            fin = ecranDefaite(score, motAleatoire);
        }
        ancienneLettre = '_';//TOUCHE PAS

        return fin;
    }

    /**
     * renvoit vrai si le joueur veut arrêter la partie
     *
     * @param score
     * @param motAleatoire
     * @return si le joueur veut rejouer ou non
     */
    static boolean ecranVictoire(int score, char[] motAleatoire) {
        boolean quitter = false;
        char reponse;
        System.out.print("---------------------------------------------------------------\n"
                + "                           GAGNÉ\n"
                + "---------------------------------------------------------------\n"
                + "\n"
                + "                Ton nombre d'erreur est de : " + score + "\n"
                + "                Le mot était : ");
        afficher(motAleatoire);
        System.out.println("");
        System.out.print("                  Quitter le jeu ? y/n\n" + "\n");
        reponse = selectionLettre();
        while (reponse != 'y' && reponse != 'n') {
            System.out.println("Veuillez choisir un y ou un n (un Y pour oui, et un N pour non)");
            reponse = selectionLettre();
        }
        if (reponse == 'y') {
            quitter = true;
            ecranFinDuJeu();
        }

        return quitter;
    }

    /**
     * renvoit vrai si le joueur veut arrêter la partie
     *
     * @param score
     * @param motAleatoire
     * @return si le joueur veut rejouer ou non
     */
    static boolean ecranDefaite(int score, char[] motAleatoire) {
        boolean quitter = false;
        char reponse;
        System.out.print("---------------------------------------------------------------\n"
                + "                           PERDU\n"
                + "---------------------------------------------------------------\n"
                + "\n"
                + "       Dommage tu as perdu (fallait pas dépasser 10 erreurs)   \n"
                + "                  Le mot final était : ");
        afficher(motAleatoire);
        System.out.println("");
        System.out.print("                  Quitter le jeu ? y/n\n" + "\n");
        reponse = selectionLettre();
        while (reponse != 'y' && reponse != 'n') {
            System.out.println("Veuillez choisir un y ou un n (un Y pour oui, et un N pour non)");
            reponse = selectionLettre();
        }
        if (reponse == 'y') {
            quitter = true;
            ecranFinDuJeu();
        }

        return quitter;
    }

    /**
     * La fonction va comparer les 2 mots mis en paramètres, si les 2 mots sont
     * les mêmes renvoies "vrai" sinon renvoie "faux"
     *
     * @param mot1
     * @param mot2
     * @return
     */
    static boolean comparaison(char[] mot1, char[] mot2) {
        boolean bonnelettre = true;
        int i = 0;
        while (bonnelettre && i < mot1.length && i < mot2.length) {
            if (mot1[i] != mot2[i]) {
                bonnelettre = false;
            } else {
                i++;
            }
        }
        return bonnelettre;
    }

    /**
     * A pour paramètres min et max, et permet de choisir un chiffre parmis
     * entre min et max, et return la réponse que si le nombre est correct
     *
     * @param min
     * @param max
     * @return
     */
    static int saisirNombre(int min, int max) {
        int nombre;
        do {
            System.out.println("Choisis un nombre entre " + min + " et " + max + " pour choisir parmis le menu");
            nombre = saisirNombreReponse();
        } while (nombre < min || nombre > max);
        return nombre;
    }

    /**
     * Permet d'afficher un tableau de caractères
     *
     * @param mot
     */
    static void afficher(char[] mot) {
        for (int i = 0; i < mot.length; i++) {
            System.out.print(mot[i]);
        }
        System.out.println("");
    }

    /**
     * Remplie un tableau de caractères de !
     *
     * @param tab
     * @return
     */
    static char[] convertionPointExclamation(char[] tab) {
        for (int i = 0; i < tab.length; i++) {
            tab[i] = '!';
        }
        return tab;
    }

    /**
     * Permet de d'appeller la fonction scanner et retourne le numéro mit dans
     * la console
     *
     * @return
     */
    static int saisirNombreReponse() {
        Scanner sc = new Scanner(System.in);
        int reponse = sc.nextInt();
        return reponse;
    }

    static void menuPendu() {
        boolean finDuJeu = false;
        do {
            afficherMenuPendu();
            int choix = saisirNombre(1, 3);
            switch (choix) {
                case 1:
                    afficherRegles();
                    break;
                case 2:
                    finDuJeu = jeuPrincipal(choix_niveau());
                    break;
                case 3: {
                    ecranFinDuJeu();
                    finDuJeu = true;
                    break;
                }
            }
        } while (!finDuJeu);
    }

    static void ecranFinDuJeu() {
        System.out.println("/----------------------------------------------------------------\\" + "\n"
                + "|                           FIN DU JEU                           |" + "\n"
                + "\\----------------------------------------------------------------/" + "\n"
                + "\n"
                + "                 +-----------------------+" + "\n"
                + "                 |Nombre de partie gagné |      " + nbrPartieGagne + "\n"
                + "                 +-----------------------+" + "\n"
                + "\n"
                + "                 +-------------------------+" + "\n"
                + "                 |Nombre de partie jouée(s)|      " + nbrPartieJoue + "\n"
                + "                 +-------------------------+" + "\n");
    }

    static int choix_niveau() {
        System.out.println("/----------------------------------\\" + "\n"
                + "|      Choix de la difficulté      |" + "\n"
                + "\\----------------------------------/" + "\n"
                + "\n"
                + "       +---+" + "\n"
                + "       | 1 |    <- Basique" + "\n"
                + "       +---+" + "\n"
                + "\n"
                + "       +---+" + "\n"
                + "       | 2 |    <- Experte" + "\n"
                + "       +---+" + "\n");
        return saisirNombreReponse();
    }

    /**
     * Une simple fonction qui affiche le menu du jeu
     */
    static void afficherMenuPendu() {
        System.out.println(" /---------------------------------------------------------------------------------------------\\" + "\n"
                + "|                                         JEU DU PENDU                                          |" + "\n"
                + " \\---------------------------------------------------------------------------------------------/" + "\n"
                + "\n"
                + "                                   +------------------------+" + "\n"
                + "                                1. |  Règles et difficulté  |" + "\n"
                + "                                   +------------------------+" + "\n"
                + "\n"
                + "                                   +------------------------+" + "\n"
                + "                                2. |    Commencer le jeu    |" + "\n"
                + "                                   +------------------------+" + "\n"
                + "\n"
                + "                                   +------------------------+" + "\n"
                + "                                3. |        Quitter         |" + "\n"
                + "                                   +------------------------+" + "\n");
    }

    /**
     * Une simple fonction qui affiche les règles du jeu
     */
    static void afficherRegles() {
        System.out.println(
                " /---------------------------------------------------------------------------------------------\\" + "\n"
                + "|                                            Règles                                             |" + "\n"
                + " \\---------------------------------------------------------------------------------------------/" + "\n"
                + "                     Votre but est de trouver les lettres derrières les _" + "\n"
                + "                                  pour former le mot mystère." + "\n"
                + "\n"
                + "                         Vous avez le droit à 10 erreurs, sinon vous perdez." + "\n"
                + "\n"
                + "                             Lorsqu'une lettre possède un accent," + "\n"
                + "                            il faut rentré la lettre avec l'accent." + "\n"
                + "\n"
                + "                      Il y a 2 difficultés disponible, la classique (débutante)" + "\n"
                + "                                         et l'experte." + "\n"
                + "                  Dans le niveau expert, les lettres non trouvées, vont être remplacées" + "\n"
                + "                          par la dernière lettre trouvée, excepté les" + "\n"
                + "                               lettres correctes déjà présentes." + "\n");
    }

    /**
     * Fonction pour afficher le menu du pendu
     * /!\ NE PAS TOUCHER
     * @param mot
     * @param lettre
     * @return le menu en ascii art
     */
    static String Afficher(char[] mot, char[] lettre, int niveau, char let) {
        int nbErreur = nb_Erreur(lettre), forlength = mot.length * 2;
        char[][] letttre = Affichage.mauvaiseLettre(lettre, mot.length, nbErreur);
        String affiche = Ligne(mot);
        for (int i = 0; i < 10; i++) {
            if (i < 3 && i != 1) {
                affiche += CHAR_AFFICHAGE_VERTICAL;
                for (int j = 0; j < forlength + 1; j++) {
                    affiche += ' ';
                }
                affiche += CHAR_AFFICHAGE_VERTICAL;
            } else if (i == 3) {
                affiche += "+";
                for (int j = 0; j < forlength + 1; j++) {
                    affiche += CHAR_AFFICHAGE_HORIZONTAL;
                }
                affiche += '+';
            } else if (i == 1) {
                affiche += CHAR_AFFICHAGE_VERTICAL + " ";
                for (int j = 0; j < forlength / 2; j++) {
                    if (niveau == 2) {
                        if (mot[j] == '_') {
                            affiche += let + " ";
                        } else {
                            affiche += mot[j] + " ";
                        }
                    } else {
                        affiche += mot[j] + " ";
                    }
                }
                affiche += CHAR_AFFICHAGE_VERTICAL;
            } else if (i > 3) {
                affiche += CHAR_AFFICHAGE_VERTICAL + " ";
                for (int j = 0; j < forlength - 1; j++) {
                    affiche += letttre[i - 4][j];
                }
                affiche += " " + CHAR_AFFICHAGE_VERTICAL;
            }
            affiche += String.format("%-17s", " " + Affichage.pendu(nbErreur)[i]) + CHAR_AFFICHAGE_VERTICAL + "\n";
        }
        affiche += Ligne(mot);
        return affiche;
    }

    static int nb_Erreur(char[] lettre) {
        int nbErreur = 0;
        while (lettre[nbErreur] != '!') {
            nbErreur++;
            if (nbErreur == 10) {
                break;
            }
        }
        return nbErreur;
    }

    static String Ligne(char[] mot) {
        String affiche = "+";
        for (int i = 0; i < mot.length * 2 + 1; i++) {
            affiche += CHAR_AFFICHAGE_HORIZONTAL;
        }
        affiche += "+";
        for (int i = 0; i < 17; i++) {
            affiche += CHAR_AFFICHAGE_HORIZONTAL;
        }
        affiche += "+\n";

        return affiche;
    }

}
