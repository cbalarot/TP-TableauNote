import clavier.In;

import java.text.DecimalFormat;

class Ihm {
    public void main(String[] args) {
        int coefCulGeneral = 2;
        int coefAnglais = 3;
        int coefMaths = 2;
        int coefNetworking = 4;
        int coefMaintenance = 3;
        int coefCyber = 7;

        int totalCoef = coefCulGeneral + coefAnglais + coefMaths + coefNetworking + coefMaintenance + coefCyber;
        int noteMinBTS = totalCoef * 10;

        float[] notes = {0, 0, 0, 0, 0, 0, 0};
        float noteTotal, moyenne;

        boolean hasOptLang = false;

        String mention = "";
        DecimalFormat df = new DecimalFormat("#.00");

//
//
//        String space = "-".repeat(100);
//        System.out.println("Épreuve"+"\t".repeat(8)+"Durée\t\t\t\t\t\tCoefficient\t\t\tType d’épreuve");
//        System.out.println("=".repeat(100));
//        System.out.println("Culture générale et expression\t\t4 heures\t\t\t\t\t"+coefCulGeneral+"\t\t\t\t\tÉcrit");
//        System.out.println(space);
//
//        System.out.println("Anglais\t\t\t\t\t\t\t\tCompréhension:\t\t\t\t"+coefAnglais+"\t\t\t\t\tCCF 2 Situations");
//        System.out.println("\t\t\t\t\t\t\t\t\t30 min sans préparation");
//        System.out.println("\t\t\t\t\t\t\t\t\tExpression :");
//        System.out.println("\t\t\t\t\t\t\t\t\t15 min sans préparation");
//        System.out.println(space);
//
//        System.out.println("Mathématiques\t\t\t\t\t\t1 h par CCF\t\t\t\t\t"+coefMaths+"\t\t\t\t\tCCF 2 situations");
//        System.out.println(space);
//
//        System.out.println("Étude et conception");
//        System.out.println("de réseaux\t\t\t\t\t\t\t6 heures\t\t\t\t\t"+coefNetworking+"\t\t\t\t\tÉcrit");
//        System.out.println("informatiques");
//        System.out.println(space);
//
//        System.out.println("Exploitation et");
//        System.out.println("maintenance des réseaux\t\t\t\t\t\t\t\t\t\t\t"+coefMaintenance+"\t\t\t\t\tCCF");
//        System.out.println("informatiques");
//        System.out.println(space);
//
//        System.out.println("Valorisation de la");
//        System.out.println("donnée et\t\t\t\t\t\t\t1 heure\t\t\t\t\t\t"+coefCyber+"\t\t\t\t\tOral");
//        System.out.println("cybersécurité");
//        System.out.println(space);
//
//        System.out.println("Langue vivante\t\t\t\t\t\t15 minutes\t\t\t\t\tPoints au\t\t\tOral");
//        System.out.println("facultative\t\t\t\t\t\t\t\t\t\t\t\t\t\tdessus de 10");


        //Affichage
        String[][] content = {
                {"Epreuve", "Durée", "Coefficient", "Type d'épreuve", ""},
                {"Culture générale et expression", "4 heures", String.valueOf(coefCulGeneral), "Écrit", "Votre note : "},
                {"Anglais", "Compréhension: 30 min sans prép & Expression 15 min sans prép", String.valueOf(coefAnglais), "CCF 2 Situations", "Votre note : "},
                {"Mathématiques", "1 h par CCF", String.valueOf(coefMaths), "CCF 2 situations", "Votre note : "},
                {"Étude et conception de réseaux informatique", "6 heures", String.valueOf(coefNetworking), "Écrit", "Votre note : "},
                {"Exploitation et maintenance de réseaux informatiques", "", String.valueOf(coefMaintenance), "CCF", "Votre note : "},
                {"Valorisation de la donnée et cybersécurité", "1 heure", String.valueOf(coefCyber), "Oral", "Votre note : "},
                {"Langue vivante facultative", "15 minutes", "/", "Oral", "Option ? [y/n] : "},
                {"", "", "", ""},
                {"", "", "Total", "Note Min BTS", "Votre note total"},
                {"", "", String.valueOf(totalCoef), String.valueOf(noteMinBTS)},
        };
        int[] colone_size = this.columMaxSize(content);
        int i, j;

        System.out.println();

        //Dessin de notre tableau
        i = 0;
        for (String[] row : content) {
            j = 0;
            for (String col : row) {
                System.out.print(col + " ".repeat(colone_size[j] + 5 - col.length()));
                j++;
            }

            // Met a la ligne si pas d'input. Dans le cas d'une input le user fait deja entré pour valider ce qui crée automatiquement une nouvelle ligne
            if (i == 7) {
                hasOptLang = In.readChar() == 'y';
            } else if ((i < 8) && (i != 0)) {
                notes[i - 1] = In.readFloat();
            } else if (i + 1 < content.length) {
                System.out.println();
                if (hasOptLang) {
                    System.out.print("Quelle est ta note de langue vivante facultative? : ");
                    notes[6] = In.readFloat();
                    hasOptLang = false;
                }
            }
            i++;
        }

        // Calcule de chaque note pondérée avec leur coef;
        noteTotal = notes[0] * coefCulGeneral;
        noteTotal += notes[1] * coefAnglais;
        noteTotal += notes[2] * coefMaths;
        noteTotal += notes[3] * coefNetworking;
        noteTotal += notes[4] * coefMaintenance;
        noteTotal += notes[5] * coefCyber;

        moyenne = noteTotal / totalCoef;

        // Ajout dans la note total les point bonus de la langue optionel
        if (notes[6] > 10) {
            noteTotal += notes[6] - 10;
        }

        System.out.print(noteTotal);
        System.out.println("\n");

        if (noteTotal >= noteMinBTS) { //Si BTS Obtenue =>
            if (moyenne >= 12) mention = " Félicitation vous avez la mention assez bien"; // On regarde les mentions
            if (moyenne >= 14) mention = " Félicitation vous avez la mention bien";
            if (moyenne >= 16) mention = " Félicitation vous avez la mention très bien";

            System.out.print("Bravos ! Vous avez les BTS avec une moyenne de " + df.format(moyenne) + mention);
        } else {
            System.out.printf("Désolé... Vous n'avez pas votre BTS. Vous avez tout de meme %s de moyenne.", df.format(moyenne));
        }

        if (moyenne > 20) System.out.println("C'est pas bien de tricher...");
        System.out.println();

        float moyenne_pro = notes[3] * coefNetworking + notes[4] * coefMaintenance + notes[5] * coefCyber;
        if (moyenne > 10 || moyenne < 8 || moyenne_pro < 10) {
            System.out.println("Pas de ratrappage possible ou pas nécessaire");
            return;
        }

        float angl_ratrapage = 0;
        float cult_ratrapage = 0;
        float maths_ratrapage = 0;


        System.out.print("Voulez vous ratrapez l'anglais ? [y/n] : ");
        if (In.readChar() == 'y') {
            System.out.print("Quelle est votre nouvelle note en Anglais? : ");
            angl_ratrapage = In.readFloat();
        }
        System.out.println();

        System.out.print("Voulez vous ratrapez la culture général ? [y/n] : ");
        if (In.readChar() == 'y') {
            System.out.print("Quelle est votre nouvelle note en culture général? : ");
            cult_ratrapage = In.readFloat();
        }
        System.out.println();

        System.out.print("Voulez vous ratrapez les mathématiques ? [y/n] : ");
        if (In.readChar() == 'y') {
            System.out.print("Quelle est votre nouvelle note en Mathématiques? : ");
            maths_ratrapage = In.readFloat();
        }
        System.out.println();

        boolean calculNeed = false;
        if (angl_ratrapage > notes[1]) {
            notes[1] = angl_ratrapage;
            calculNeed = true;
        }
        if (cult_ratrapage > notes[0]) {
            notes[0] = cult_ratrapage;
            calculNeed = true;
        }
        if (maths_ratrapage > notes[2]) {
            notes[2] = maths_ratrapage;
            calculNeed = true;
        }

        if (!calculNeed) {
            System.out.println("Vos notes ne se sont pas amélioré, cela ne sert a rien de recalculer une nouvelle moyennes vous n'avez toujours pas le BTS");
            return;
        }

        noteTotal = notes[0] * coefCulGeneral;
        noteTotal += notes[1] * coefAnglais;
        noteTotal += notes[2] * coefMaths;
        noteTotal += notes[3] * coefNetworking;
        noteTotal += notes[4] * coefMaintenance;
        noteTotal += notes[5] * coefCyber;

        moyenne = noteTotal / totalCoef;

        // Ajout dans la note total les point bonus de la langue optionel
        if (notes[6] > 10) {
            noteTotal += notes[6] - 10;
        }

        System.out.print(noteTotal);
        System.out.println("\n");

        if (noteTotal >= noteMinBTS) { //Si BTS Obtenue =>
            if (moyenne >= 12) mention = " Félicitation vous avez la mention assez bien"; // On regarde les mentions
            if (moyenne >= 14) mention = " Félicitation vous avez la mention bien";
            if (moyenne >= 16) mention = " Félicitation vous avez la mention très bien";

            System.out.print("Bravos ! Vous avez les BTS avec une moyenne de " + df.format(moyenne) + mention);
        } else {
            System.out.printf("Désolé... Vous n'avez pas votre BTS. Vous avez tout de meme %s de moyenne.", df.format(moyenne));
        }

        if (moyenne > 20) System.out.println("C'est pas bien de tricher...");


        System.out.println("\n");
    }

    /**
     * Renvoie la taille max dans un tablau de chaque collone
     * @param content
     * @return Renvoie une liste dans laquel est la taille max de chacune des collones
     */
    private int[] columMaxSize(String[][] content) {
        int i;
        int[] colSize = {0, 0, 0, 0, 0};
        for (String[] row : content) {
            i = 0;
            for (String col : row) {
                if (col.length() > colSize[i]) {
                    colSize[i] = col.length();
                }
                i++;
            }
        }
        return colSize;
    }
}
