package pt.joaocruz.myrecipeschallenge.use_case

import de.geschenkidee.geschenkidee.usecase.UseCase
import io.reactivex.Observable
import pt.joaocruz.myrecipeschallenge.App
import pt.joaocruz.myrecipeschallenge.model.User
import pt.joaocruz.myrecipeschallenge.network.ServicesManager

/**
 * Created by jcruz on 13.07.17.
 */
open class LoginUseCase : UseCase {

    var sm : ServicesManager
    var email: String?=null
    var password: String?=null


    constructor(servicesManager: ServicesManager) {
        this.sm = servicesManager
    }

    fun withEmailAndPassword(email: String, password: String): LoginUseCase {
        this.email = email
        this.password = password
        return this
    }


    override fun build(): Observable<User> {
        App.getInstance().appComponent.inject(this)
        return sm.login(email?:"", password?:"");
    }


}