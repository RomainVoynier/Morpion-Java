import java.util.Scanner;
// bibliothèque pour lire les entrées utilisateur

public class Script {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Result jeu = new Result();
        boolean jouerEncore = true;
        // boucle while pour permettre de rejouer après chaque partie
        while (jouerEncore) {
            jeu.initBoard();
            jeu.setCurrentPlayer('X');
            boolean partieActive = true;
            // boucle qui gère le déroulement de la partie
            while (partieActive) {
                System.out.println(jeu.afficherBoard());
                System.out.println("Joueur " + jeu.getCurrentPlayer() + ", entre un chiffre de 1 a 9 :");
                int position = lirePosition(scanner);
                // Place un pion et vérifie les conditions de victoire ou de match nul
                if (jeu.placerPion(position - 1)) {
                    if (jeu.verifierVictoire()) {
                        System.out.println(jeu.afficherBoard());
                        System.out.println("Le joueur " + jeu.getCurrentPlayer() + " a gagne !");
                        partieActive = false;
                    // renvoie le match nul si toutes les cases sont remplies sans gagnant
                    } else if (jeu.estMatchNul()) {
                        System.out.println(jeu.afficherBoard());
                        System.out.println("Match nul !");
                        partieActive = false;
                    } else {
                        jeu.changerJoueur();
                    }
                } else {
                // teste l'erreur d'une case déjà occupée
                    System.out.println("Case deja occupee. Choisis une autre case.");
                }
            }
            // Renvoie une demande pour rejouer
            System.out.println("Rejouer ? (O/N)");
            jouerEncore = lireRejouer(scanner);
        }

        scanner.close();
    }
    
    private static int lirePosition(Scanner scanner) {
        while (true) {
            String saisie = scanner.nextLine().trim();

            try {
                int position = Integer.parseInt(saisie);
                if (position >= 1 && position <= 9) {
                    return position;
                }
            } catch (NumberFormatException ignored) {
            }
            // Teste l'erreur
            System.out.println("Entree invalide. Entre un nombre entre 1 et 9 :");
        }
    }
    // Demande a l'utilisateur s'il veut rejouer et check les erreurs
    private static boolean lireRejouer(Scanner scanner) {
        while (true) {
            String saisie = scanner.nextLine().trim().toUpperCase();
            if (saisie.equals("O") || saisie.equals("OUI")) {
                return true;
            }
            if (saisie.equals("N") || saisie.equals("NON")) {
                return false;
            }
            System.out.println("Reponse invalide. Tape O pour oui ou N pour non :");
        }
    }
}
