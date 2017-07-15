package pt.joaocruz.myrecipeschallenge.recipes_presenter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import pt.joaocruz.myrecipeschallenge.data.DataManagerImpl;
import pt.joaocruz.myrecipeschallenge.model.User;
import pt.joaocruz.myrecipeschallenge.network.ServicesImpl;
import pt.joaocruz.myrecipeschallenge.recipes_screen.RecipesPresenterImpl;
import pt.joaocruz.myrecipeschallenge.recipes_screen.RecipesViewImpl;
import pt.joaocruz.myrecipeschallenge.use_case.LoginUseCase;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by joaocruz04 on 12/07/2017.
 */

public class TestLogin {

    private RecipesPresenterImpl presenter;

    @Before
    public void initialSetup() {
        ServicesImpl services = mock(ServicesImpl.class);
        DataManagerImpl dataManager = mock(DataManagerImpl.class);
        LoginUseCase loginUC = mock(LoginUseCase.class);
        RecipesViewImpl view = mock(RecipesViewImpl.class);
        User user = new User();
        user.setEmail("joao@cruz.pt");
        when(services.login(any(String.class),any(String.class))).thenReturn(Observable.just(user));
        when(loginUC.build()).thenReturn(Observable.just(user));
        presenter = new RecipesPresenterImpl(services, dataManager, loginUC, Schedulers.trampoline(), Schedulers.trampoline());
        presenter.registerView(view);
    }


    @Test
    public void correctArguments() throws Exception {
        presenter.loginWithEmailAndPassword("my@email.pt", "123456");
        verify(presenter.getView()).loginSuccess(any(User.class));
    }

    @Test
    public void incorrectArguments() throws Exception {
        presenter.loginWithEmailAndPassword("email.pt", "123456");
        verify(presenter.getView(), times(1)).showLoginParametersErrorMessage(any(String.class));
    }



}
