package com.eazzyapps.test

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.ContentLoadingProgressBar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.ezzyapps.test.pixabay.common.ActivityDelegate
import com.ezzyapps.test.pixabay.common.setVisible
import com.ezzyapps.test.images.ui.ImageModuleNavEvents
import com.ezzyapps.test.images.ui.imageslist.ConfirmationDialogDirections
import com.ezzyapps.test.images.ui.imageslist.PreviewListFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var delegate: ActivityDelegate

    private val disposables = CompositeDisposable()

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val progressBar = findViewById<ContentLoadingProgressBar>(R.id.loadingIndicator)

        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            navGraph = navController.graph,
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        disposables.addAll(

            delegate.navEvents.subscribeBy { event ->
                with(navController) {
                    when (event) {
                        is ImageModuleNavEvents.ImageSelectedEvent ->
                            navigate(PreviewListFragmentDirections.toDetailsConfirmationDialog(event.id))
                        is ImageModuleNavEvents.ImageSelectionConfirmedEvent ->
                            navigate(ConfirmationDialogDirections.toImageDetailsFragment(event.id))
                    }
                }
            },
            delegate.loadingEvents.subscribeBy { isLoading ->
                progressBar.setVisible(isLoading)
            },
            delegate.messagingEvents.subscribeBy { message ->
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }

        )

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
                || super.onSupportNavigateUp()
    }


    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}