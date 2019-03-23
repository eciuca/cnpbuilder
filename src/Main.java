import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static java.time.LocalDate.parse;

public class Main {

    public static final DateTimeFormatter FORMAT_DATA_NASTERE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final int BARBAT_SEC_XXI = 1;
    public static final int FEMEIE_SEC_XXI = 2;

    public static void main(String[] args) {
        System.out.println(Judet.OLT);
        int x = new Random().nextInt(1000);
        System.out.println(String.format("%03d", x));

        RomanianCNPBuilder constructorCNP = new RomanianCNPBuilder();

        constructorCNP
                .withSexSiSecol(BARBAT_SEC_XXI)
                .withDataNasterii(parse("23/02/1991", FORMAT_DATA_NASTERE))
                .withJudet(Judet.ALBA);

        CNP cnp = constructorCNP.creeazaCNP();

        System.out.println(cnp);

    }



    static class CNP {
        private int sexSiSecol;
        private LocalDate dataNasterii;
        private Judet judet;
        private int codPersoana;
        private int cifraControl;

        public int getSexSiSecol() {
            return sexSiSecol;
        }

        public void setSexSiSecol(int sexSiSecol) {
            this.sexSiSecol = sexSiSecol;
        }

        public LocalDate getDataNasterii() {
            return dataNasterii;
        }

        public void setDataNasterii(LocalDate dataNasterii) {
            this.dataNasterii = dataNasterii;
        }

        public Judet getJudet() {
            return judet;
        }

        public void setJudet(Judet judet) {
            this.judet = judet;
        }

        public int getCodPersoana() {
            return codPersoana;
        }

        public void setCodPersoana(int codPersoana) {
            this.codPersoana = codPersoana;
        }

        public int getCifraControl() {
            return cifraControl;
        }

        public void setCifraControl(int cifraControl) {
            this.cifraControl = cifraControl;
        }

        public void calculeazaCifraControl() {
            Long checkNo = 279146358279L;
            Long deimpartit = 100000000000L;
            Long cnpFaraUltCifra = Long.parseLong(cnpFaraCifraDeControl());

            int sum = 0;
            for (int i = 0; i < 12; i++) {
                sum += checkNo / deimpartit + cnpFaraUltCifra / deimpartit;
                deimpartit /= 10;
                checkNo /= 10;
                cnpFaraUltCifra /= 10;
            }

            int restulImpartirii = sum % 11;

            if (restulImpartirii  == 10) {
                this.cifraControl = 1;
            } else {
                this.cifraControl = restulImpartirii;
            }
        }

        @Override
        public String toString() {
            return cnpFaraCifraDeControl() + cifraControl;
        }

        private String cnpFaraCifraDeControl() {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyMMdd");
            return sexSiSecol +
                    format.format(dataNasterii) +
                    judet.getCodJudet() +
                    codPersoana;
        }
    }

    interface ICNPBuilder {
        RomanianCNPBuilder withSexSiSecol(int sexSiSecol);
        RomanianCNPBuilder withDataNasterii(LocalDate dataNasterii);
        RomanianCNPBuilder withJudet(Judet judet);
        CNP creeazaCNP();
    }

    static class RomanianCNPBuilder implements ICNPBuilder {
        private int sexSiSecol;
        private LocalDate dataNasterii;
        private Judet judet;

        public int getSexSiSecol() {
            return sexSiSecol;
        }

        public RomanianCNPBuilder withSexSiSecol(int sexSiSecol) {
            this.sexSiSecol = sexSiSecol;
            return this;
        }

        public LocalDate getDataNasterii() {
            return dataNasterii;
        }

        public RomanianCNPBuilder withDataNasterii(LocalDate dataNasterii) {
            this.dataNasterii = dataNasterii;
            return this;
        }

        public Judet getJudet() {
            return judet;
        }

        public RomanianCNPBuilder withJudet(Judet judet) {
            this.judet = judet;
            return this;
        }

        @Override
        public CNP creeazaCNP() {
            CNP cnp = new CNP();
            cnp.setSexSiSecol(this.sexSiSecol);
            cnp.setDataNasterii(this.dataNasterii);
            cnp.setJudet(this.judet);
            cnp.setCodPersoana(new Random().nextInt(1000));
            cnp.calculeazaCifraControl();

            return cnp;
        }
    }

    enum Judet {
        OLT("28"),
        ALBA("01");

        private String codJudet;

        Judet(String codJudet) {
            this.codJudet = codJudet;
        }

        public String getCodJudet() {
            return codJudet;
        }


        @Override
        public String toString() {
            return this.name() + " " + codJudet;
        }
    }


}
