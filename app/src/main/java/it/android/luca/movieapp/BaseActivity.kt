package it.android.luca.movieapp

import android.support.v7.app.AppCompatActivity
import android.widget.Toast

open class BaseActivity: AppCompatActivity(), BasePresenterView {

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}