import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CNP {

    public static final DateTimeFormatter CNP_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyMMdd");
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

        if (restulImpartirii == 10) {
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
        return sexSiSecol +
                CNP_DATE_FORMATTER.format(dataNasterii) +
                judet.getCodJudet() +
                codPersoana;
    }
}
