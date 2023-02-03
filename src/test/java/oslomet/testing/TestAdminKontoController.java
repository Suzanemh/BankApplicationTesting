package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKontoController;
import oslomet.testing.API.BankController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestAdminKontoController {

    @InjectMocks
    // denne skal testes
    private AdminKontoController adminController;

    @Mock
    // denne skal Mock'es
    private AdminRepository repository;

    @Mock
    // denne skal Mock'es
    private Sikkerhet sjekk;

    @Test
    public void hentAlleKonti(){


        Konto enKonto = new Konto("01234567897" , "12345678910", 720, "Lønnskonto", "NOK",null);
        List<Konto> list = new ArrayList<>();

        list.add(enKonto);

        when(sjekk.loggetInn()).thenReturn("01234567897");

        when(repository.hentAlleKonti()).thenReturn(list);

        List<Konto> resultat = adminController.hentAlleKonti();

        assertEquals(list, resultat);

    }

    @Test
    public void registrerKonto(){
        Konto konto = new Konto("01234567897" , "12345678910", 720, "Lønnskonto", "NOK",null);

        when(sjekk.loggetInn()).thenReturn("01234567897");

        when(repository.registrerKonto(any(Konto.class))).thenReturn("OK");

        String resultat = adminController.registrerKonto(konto);

        assertEquals("OK", resultat);

    }

    @Test
    public void endreKonto(){
        Konto konto = new Konto("01234567897" , "12345678910", 720, "Lønnskonto", "NOK",null);
        when(sjekk.loggetInn()).thenReturn("01234567897");
        when(repository.endreKonto(any(Konto.class))).thenReturn("OK");
        String resultat = adminController.endreKonto(konto);
        assertEquals("OK", resultat);
    }

    @Test
    public void slettKonto(){
        Konto konto = new Konto("01234567897" , "12345678910", 720, "Lønnskonto", "NOK",null);
        List<Konto> list = new ArrayList<>();

        list.add(konto);
        when(sjekk.loggetInn()).thenReturn("01234567897");
        when(repository.slettKonto(konto.getKontonummer())).thenReturn("OK");
        String resultat = adminController.slettKonto(konto.getKontonummer());
        assertEquals("OK", resultat);

    }

    @Test
    public void test_IkkeHentAlle(){
        Konto enkonto = new Konto("01234567897" , "12345678910", 720, "Lønnskonto", "NOK",null);
        List<Konto> list = new ArrayList<>();
        list.add(enkonto);
        when(sjekk.loggetInn()).thenReturn(null);
        List<Konto> resultat = adminController.hentAlleKonti();
        assertEquals(null, resultat);

    }

    @Test
    public void test_IkkeRegistrer(){
        Konto konto = new Konto("01234567897" , "12345678910", 720, "Lønnskonto", "NOK",null);
        when(sjekk.loggetInn()).thenReturn(null);

        String resultat = adminController.registrerKonto(konto);

        assertEquals("Ikke innlogget", resultat);
    }

    @Test
    public void test_IkkeEndreKonto(){
        Konto konto = new Konto("01234567897" , "12345678910", 720, "Lønnskonto", "NOK",null);
        when(sjekk.loggetInn()).thenReturn(null);
        String resultat = adminController.endreKonto(konto);
        assertEquals("Ikke innlogget", resultat);
    }

    @Test
   public void test_IkkeSlettKonto(){
        Konto konto = new Konto("01234567897", "12345678910", 720, "Lønnskonto", "NOK", null);
        when(sjekk.loggetInn()).thenReturn(null);
        String resultat = adminController.slettKonto(konto.getKontonummer());
        assertEquals("Ikke innlogget", resultat);
    }
}