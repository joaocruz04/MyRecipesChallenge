package pt.joaocruz.myrecipeschallenge.ui

import android.content.Context
import android.widget.Toast

/**
 * Created by joaocruz04 on 15/07/2017.
 */
class ToastAlert: AlertInterface {

    override fun showMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}