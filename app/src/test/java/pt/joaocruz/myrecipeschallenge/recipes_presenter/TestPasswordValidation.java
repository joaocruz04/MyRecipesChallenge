package pt.joaocruz.myrecipeschallenge.recipes_presenter;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.schedulers.Schedulers;
import pt.joaocruz.myrecipeschallenge.data.DataManager;
import pt.joaocruz.myrecipeschallenge.data.DataManagerImpl;
import pt.joaocruz.myrecipeschallenge.network.ServicesImpl;
import pt.joaocruz.myrecipeschallenge.network.ServicesManager;
import pt.joaocruz.myrecipeschallenge.recipes_screen.RecipesPresenterImpl;
import pt.joaocruz.myrecipeschallenge.recipes_screen.RecipesViewImpl;
import pt.joaocruz.myrecipeschallenge.use_case.LoginUseCase;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by joaocruz04 on 12/07/2017.
 */

public class TestPasswordValidation {

    private RecipesPresenterImpl presenter;

    @Before
    public void initialSetup() {
        ServicesImpl services = mock(ServicesImpl.class);
        DataManagerImpl dataManager = mock(DataManagerImpl.class);
        LoginUseCase loginUC = mock(LoginUseCase.class);
        presenter = new RecipesPresenterImpl(services, dataManager, loginUC, Schedulers.trampoline(), Schedulers.trampoline());
    }


    @Test
    public void emptyPassword() throws Exception {
        boolean valid = presenter.validPassword("");
        assertEquals(valid, false);
    }

    @Test
    public void nullPassword() throws Exception {
        boolean valid = presenter.validPassword(null);
        assertEquals(valid, false);
    }

    @Test
    public void goodPassword() throws Exception {
        boolean valid = presenter.validPassword("12345");
        assertEquals(valid, true);
    }

    @Test
    public void badPassword() throws Exception {
        boolean valid = presenter.validPassword("1234");
        assertEquals(valid, false);

        valid = presenter.validPassword("123");
        assertEquals(valid, false);

        valid = presenter.validPassword("1");
        assertEquals(valid, false);
    }

}
