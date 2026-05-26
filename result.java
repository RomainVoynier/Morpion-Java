public record Result(ResultType type, String joueur) {

    public enum ResultType {
        VICTOIRE_1("Victoire du joueur 1", "Le joueur 1 a gagné la partie !"),
        VICTOIRE_2("Victoire du joueur 2", "Le joueur 2 a gagné la partie !"),
        MATCH_NUL("Match nul", "Match nul - Aucun joueur n'a pu gagner.");

        private final String msg;
        private final String details;

        ResultType(String msg, String details) {
            this.msg = msg;
            this.details = details;
        }
    }
    public Result(ResultType type, String joueur) {
        this(type, joueur == null ? null : joueur.toUpperCase());
    }

    public String details() {
        return type.details;
    }

    public void afficherResultat() {
        System.out.println(this);
    }

    public static void main(String[] args) {
        new Result(ResultType.VICTOIRE_1, "1").afficherResultat();
        System.out.println();
        new Result(ResultType.MATCH_NUL, null).afficherResultat();
        System.out.println();
        new Result(ResultType.VICTOIRE_2, "2").afficherResultat();
    }
}