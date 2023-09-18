/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pendujpo;

/**
 * /!\ Il n'y a aucun bug dans ce fichier, ne rien modifier
 * 
 * @author teori
 */
public class Affichage {

    static String[] pendu0 = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
    static String[] pendu1 = {" ", " ", " ", " ", " ", " ", " ", " ", " ", "============"};
    static String[] pendu2 = {" ", "||", "||", "||", "||", "||", "||", "||", "||", "============"};
    static String[] pendu3 = {",==========Y===", "||", "||", "||", "||", "||", "||", "||", "||", "============"};
    static String[] pendu4 = {",==========Y===", "|| /", "||/", "||", "||", "||", "||", "||", "||", "============"};
    static String[] pendu5 = {",==========Y===", "|| /       |", "||/", "||", "||", "||", "||", "||", "||", "============"};
    static String[] pendu6 = {",==========Y===", "|| /       |", "||/        |", "||", "||", "||", "||", "||", "||", "============"};
    static String[] pendu7 = {",==========Y===", "|| /       |", "||/        |", "||         O", "||", "||", "||", "||", "||", "============"};
    static String[] pendu8 = {",==========Y===", "|| /       |", "||/        |", "||         O", "||        /|\\", "||", "||", "||", "||", "============"};
    static String[] pendu9 = {",==========Y===", "|| /       |", "||/        |", "||         O", "||        /|\\", "||         |", "||", "||", "||", "============"};
    static String[] pendu10 = {",==========Y===", "|| /       |", "||/        |", "||         O", "||        /|\\", "||         |", "||       _/ \\_", "||          ", "||          ", "============"};

    static String[] pendu(int nbErreur) {
        String[] erreur = {" ", " ", " ", " ", "erreur", "d'affichage ", " ", " ", " ", " "};
        switch (nbErreur) {
            case 0:
                return pendu0;
            case 1:
                return pendu1;
            case 2:
                return pendu2;
            case 3:
                return pendu3;
            case 4:
                return pendu4;
            case 5:
                return pendu5;
            case 6:
                return pendu6;
            case 7:
                return pendu7;
            case 8:
                return pendu8;
            case 9:
                return pendu9;
            case 10:
                return pendu10;
        }
        return erreur;
    }

    static char[][] mauvaiseLettre(char[] t, int mot, int erreur) {
        char[][] retour = new char[6][(mot * 2)];
        for (int i = 0; i < 6; i++) {
            for (int k = 0; k < (mot * 2); k++) {
                retour[i][k] = ' ';
            }
        }
        System.arraycopy(t, 0, retour[0], 0, erreur);
        return retour;
    }
}
