package pt.joaocruz.myrecipeschallenge.use_case

import de.geschenkidee.geschenkidee.usecase.UseCase
import io.reactivex.Observable
import pt.joaocruz.myrecipeschallenge.App
import pt.joaocruz.myrecipeschallenge.model.User
import pt.joaocruz.myrecipeschallenge.network.ServicesManager

/**
 * Created by jcruz on 13.07.17.
 */
class LoginUseCase : UseCase {

    var sm : ServicesManager
    var email: String?=null
    var password: String?=null


    constructor(servicesManager: ServicesManager, email: String, password: String) {
        this.email = email
        this.password = password
        this.sm = servicesManager
    }


    override fun build(): Observable<User> {
        App.getInstance().appComponent.inject(this)
        return sm.login(email?:"", password?:"");
    }


}