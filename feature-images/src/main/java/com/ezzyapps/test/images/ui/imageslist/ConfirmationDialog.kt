package com.ezzyapps.test.images.ui.imageslist

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.ezzyapps.test.pixabay.common.ActivityDelegate
import com.ezzyapps.test.images.R
import com.ezzyapps.test.images.ui.ImageModuleNavEvents.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ConfirmationDialog : DialogFragment() {

    private val args: ConfirmationDialogArgs by navArgs()

    @Inject
    lateinit var delegate: ActivityDelegate

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.details_dialog_title))
            .setPositiveButton(getString(R.string.positive_button)) { _, _ ->
                delegate.navigate(ImageSelectionConfirmedEvent(args.imageId))
            }
            .setNegativeButton(getString(R.string.negative_button)) { _, _ -> dismiss() }
            .create()

}