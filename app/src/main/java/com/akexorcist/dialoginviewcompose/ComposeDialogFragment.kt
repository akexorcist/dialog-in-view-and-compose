package com.akexorcist.dialoginviewcompose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.fragment.app.Fragment
import com.akexorcist.dialoginviewcompose.databinding.FragmentDialogComposeBinding

class ComposeDialogFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDialogComposeBinding.inflate(inflater, container, false).apply {
            composeView.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent(content())
            }
        }.root
    }

    private fun content(): @Composable () -> Unit = {
        var showDialog by remember { mutableStateOf(false) }
        if (showDialog) {
            CustomDialog(
                onDismissed = { showDialog = false },
            ) {
                Box(
                    modifier = Modifier.size(240.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Compose-based Dialog",
                        fontSize = 18.sp,
                    )
                }
            }
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopCenter,
        ) {
            Button(onClick = { showDialog = true }) {
                Text("Compose")
            }
        }
    }
}

@Composable
fun CustomDialog(
    cancelable: Boolean = true,
    onDismissed: () -> Unit,
    content: @Composable () -> Unit,
) {
    BackHandler {
        if (cancelable) {
            onDismissed()
        }
    }
    Dialog(
        properties = DialogProperties(
            dismissOnBackPress = cancelable,
            dismissOnClickOutside = cancelable,
        ),
        onDismissRequest = onDismissed,
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp),
                )
                .padding(32.dp),
        ) {
            content()
        }
    }
}
