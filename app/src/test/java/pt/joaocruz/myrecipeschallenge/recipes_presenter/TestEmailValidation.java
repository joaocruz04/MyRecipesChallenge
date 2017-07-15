package pt.joaocruz.myrecipeschallenge.recipes_presenter;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.schedulers.Schedulers;
import pt.joaocruz.myrecipeschallenge.data.DataManager;
import pt.joaocruz.myrecipeschallenge.data.DataManagerImpl;
import pt.joaocruz.myrecipeschallenge.network.ServicesImpl;
import pt.joaocruz.myrecipeschallenge.network.ServicesManager;
import pt.joaocruz.myrecipeschallenge.recipes_screen.RecipesPresenterImpl;
import pt.joaocruz.myrecipeschallenge.use_case.LoginUseCase;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by joaocruz04 on 12/07/2017.
 */

public class TestEmailValidation {

    private RecipesPresenterImpl presenter;

    @Before
    public void initialSetup() {
        ServicesImpl services = mock(ServicesImpl.class);
        DataManagerImpl dataManager = mock(DataManagerImpl.class);
        LoginUseCase loginUC = mock(LoginUseCase.class);
        presenter = new RecipesPresenterImpl(services, dataManager, loginUC, Schedulers.trampoline(), Schedulers.trampoline());
    }


    @Test
    public void emptyEmail() throws Exception {
        boolean valid = presenter.validEmail("");
        assertEquals(valid, false);
    }

    @Test
    public void nullEmail() throws Exception {
        boolean valid = presenter.validEmail(null);
        assertEquals(valid, false);
    }

    @Test
    public void goodEmail() throws Exception {
        boolean valid = presenter.validEmail("some@one.pt");
        assertEquals(valid, true);
    }

    @Test
    public void badEmail() throws Exception {
        boolean valid = presenter.validEmail("some.pt");
        assertEquals(valid, false);

        valid = presenter.validEmail("some@pt");
        assertEquals(valid, false);

        valid = presenter.validEmail("@some.pt");
        assertEquals(valid, false);

        valid = presenter.validEmail("aaaa");
        assertEquals(valid, false);
    }

}
