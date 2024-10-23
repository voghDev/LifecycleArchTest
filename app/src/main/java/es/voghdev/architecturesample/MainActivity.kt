package es.voghdev.architecturesample

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import es.voghdev.architecturesample.viewmodel.MainViewModel
import es.voghdev.architecturesample.viewstate.MainViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.viewState.observe(this, Observer<MainViewState> { state ->
            when (state) {
                MainViewState.Loading -> setLoadingState()
                MainViewState.ContentLoaded -> setLoadedState()
                MainViewState.Error -> setErrorState()
            }
        })
    }

    private fun setErrorState() {
        val textView = findViewById<TextView>(R.id.textView)
        textView.text = "Error"
    }

    private fun setLoadedState() {
        val textView = findViewById<TextView>(R.id.textView)
        textView.text = "Loaded"
    }

    private fun setLoadingState() {
        val textView = findViewById<TextView>(R.id.textView)
        textView.text = "Loading"
    }
}