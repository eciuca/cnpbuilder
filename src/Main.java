import java.time.format.DateTimeFormatter;

import static java.time.LocalDate.parse;

public class Main {

    public static final DateTimeFormatter FORMAT_DATA_NASTERE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final int BARBAT_SEC_XXI = 1;
    public static final int FEMEIE_SEC_XXI = 2;

    public static void main(String[] args) {
        DefaultCNPBuilder constructorCNP = new DefaultCNPBuilder();

        constructorCNP
                .withSexSiSecol(BARBAT_SEC_XXI)
                .withDataNasterii(parse("23/02/1991", FORMAT_DATA_NASTERE))
                .withJudet(Judet.ALBA);

        CNP cnp = constructorCNP.creeazaCNP();

        System.out.println(cnp);

    }


}
