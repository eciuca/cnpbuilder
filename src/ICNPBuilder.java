import java.time.LocalDate;

public interface ICNPBuilder {
    DefaultCNPBuilder withSexSiSecol(int sexSiSecol);

    DefaultCNPBuilder withDataNasterii(LocalDate dataNasterii);

    DefaultCNPBuilder withJudet(Judet judet);

    CNP creeazaCNP();
}