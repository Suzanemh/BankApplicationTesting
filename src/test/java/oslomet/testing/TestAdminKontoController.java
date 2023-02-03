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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
