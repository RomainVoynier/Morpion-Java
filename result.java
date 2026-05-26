/**
 * Classe pour gérer les résultats du jeu Morpion
 */
public class Result {
    
    // Énumération des types de résultats
    public enum ResultType {
        VICTOIRE_1("Victoire du joueur 1"),
        VICTOIRE_2("Victoire du joueur 2"),
        MATCH_NUL("Match nul");
        
        private final String message;
        
        ResultType(String message) {
            this.message = message;
        }
        
        public String getMessage() {
            return message;
        }
    }
    
    private ResultType type;
    private String joueur;
    private String details;
    private long timestamp;
    
    /**
     * Constructeur pour créer un résultat
     * @param type Le type de résultat
     * @param joueur Le joueur gagnant (null pour match nul)
     */
    public Result(ResultType type, String joueur) {
        this.type = type;
        this.joueur = joueur;
        this.timestamp = System.currentTimeMillis();
        this.details = genererDetails();
    }
    
    /**
     * Génère une description détaillée du résultat
     */
    private String genererDetails() {
        switch(type) {
            case VICTOIRE_1:
                return "Le joueur 1 a gagné la partie!";
            case VICTOIRE_2:
                return "Le joueur 2 a gagné la partie!";
            case MATCH_NUL:
                return "Match nul - Aucun joueur n'a pu gagner.";
            default:
                return "";
        }
    }
    
    /**
     * Retourne le message du résultat
     */
    public String obtenirMessage() {
        return type.getMessage();
    }
    
    /**
     * Retourne les détails du résultat
     */
    public String obtenirDetails() {
        return details;
    }
    
    /**
     * Retourne le type de résultat
     */
    public ResultType obtenirType() {
        return type;
    }
    
    /**
     * Retourne le joueur gagnant
     */
    public String obtenirJoueur() {
        return joueur;
    }
    
    /**
     * Retourne l'heure du résultat
     */
    public long obtenirTimestamp() {
        return timestamp;
    }
    
    /**
     * Affiche le résultat complet
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== RÉSULTAT DE LA PARTIE ===\n");
        sb.append("Type: ").append(type.getMessage()).append("\n");
        if(joueur != null) {
            sb.append("Joueur gagnant: ").append(joueur.toUpperCase()).append("\n");
        }
        sb.append("Détails: ").append(details).append("\n");
        sb.append("Heure: ").append(new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
            .format(new java.util.Date(timestamp))).append("\n");
        sb.append("=============================");
        return sb.toString();
    }
    
    /**
     * Affiche le résultat dans la console
     */
    public void afficherResultat() {
        System.out.println(this.toString());
    }
    
    /**
     * Exemple d'utilisation
     */
    public static void main(String[] args) {
        // Exemple de résultat: Victoire du joueur 1
        Result resultat1 = new Result(ResultType.VICTOIRE_1, "1");
        resultat1.afficherResultat();
        
        System.out.println("\n");
        
        // Exemple de résultat: Match nul
        Result resultat2 = new Result(ResultType.MATCH_NUL, null);
        resultat2.afficherResultat();
        
        System.out.println("\n");
        
        // Exemple de résultat: Victoire du joueur 2
        Result resultat3 = new Result(ResultType.VICTOIRE_2, "2");
        resultat3.afficherResultat();
    }
}
