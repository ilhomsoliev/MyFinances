package com.ilhomsoliev.myfinances.feature.goals.presentation.addGoal.photo

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilhomsoliev.myfinances.R
import com.ilhomsoliev.myfinances.shared.sharedUi.ImageBox

@Composable
fun GoalPhotoScreen(
    photoUrl: String?,
    uri: Uri?,
    onUriChange: (Uri?) -> Unit
) {
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            onUriChange(it)
        }
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = "Добавьте фото", style = MaterialTheme.typography.labelLarge.copy(
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 30.sp,
                lineHeight = 35.sp
            )
        )
        Text(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 14.dp, bottom = 60.dp),
            text = "Визуализация поможет быстрее накопить на цель",
            style = MaterialTheme.typography.labelMedium.copy(
                color = MaterialTheme.colorScheme.onTertiary,
            )
        )

        Photo(url = photoUrl, uri = uri) {
            singlePhotoPickerLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }

    }
}

@Composable
private fun Photo(
    url: String?,
    uri: Uri?,
    onPhotoClick: () -> Unit
) {
    ImageBox(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .height(260.dp)
                    .clickable { onPhotoClick() }
            ),
        imageHttp = url,
        uri = uri,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .height(260.dp)
                .clickable { onPhotoClick() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_camera),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .size(77.dp)
            )
        }
    }
}