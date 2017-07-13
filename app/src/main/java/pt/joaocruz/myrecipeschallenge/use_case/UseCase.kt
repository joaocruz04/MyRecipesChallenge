package de.geschenkidee.geschenkidee.usecase

import io.reactivex.Observable

/**
 * Created by jcruz on 04.04.17.
 */

interface UseCase {
    fun build(): Observable<*>
}
