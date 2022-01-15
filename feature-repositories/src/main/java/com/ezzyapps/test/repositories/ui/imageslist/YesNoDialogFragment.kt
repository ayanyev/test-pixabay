package com.ezzyapps.test.repositories.ui.imageslist

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.ezzyapps.test.pixabay.common.ActivityDelegate
import com.ezzyapps.test.repositories.R
import com.ezzyapps.test.repositories.ui.ImageModuleNavEvents
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class YesNoDialogFragment : DialogFragment() {

    @Inject
    lateinit var delegate: ActivityDelegate

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.details_dialog_title))
            .setPositiveButton(getString(R.string.positive_button)) { _, _ ->
                delegate.navigate(ImageModuleNavEvents.ImageSelectionConfirmedEvent)
            }
            .setNegativeButton(getString(R.string.negative_button)) { _, _ -> dismiss()}
            .create()

    companion object {
        const val TAG = "PurchaseConfirmationDialog"
    }
}