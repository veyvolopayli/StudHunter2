package com.veyvolopayli.studhunter.presentation.custom_views.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.veyvolopayli.studhunter.R

@Composable
fun ComposeGradientButtonDefault(text: String, isClickable: Boolean, onClick: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(83.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .blur(16.dp)
                .padding(16.dp)
                .clickable {
                    if (isClickable) {
                        onClick()
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(51.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0, 178, 255), Color(230, 17, 235)
                            )
                        )
                    )
            )
        }
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(51.dp)
                .clip(shape = RoundedCornerShape(16.dp))
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0, 178, 255), Color(230, 17, 235)
                        )
                    )
                )
        )
        Text(
            text = text, style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.gilroy_regular)),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
            )
        )
    }

}

@Preview
@Composable
fun GradientButtonPreview() {
    ComposeGradientButtonDefault(text = "primary button", isClickable = true) {
        Log.e("TAG", "CLICKED")
    }
}