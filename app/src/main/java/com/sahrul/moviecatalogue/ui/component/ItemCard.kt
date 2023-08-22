package com.sahrul.moviecatalogue.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    image: Painter,
    rating: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .width(110.dp)
            .height(180.dp)
            .padding(4.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(
            contentAlignment = Alignment.TopStart
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = image,
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )
            Row(modifier = Modifier.padding(4.dp)) {
                Icon(imageVector = Icons.Default.Star, contentDescription = "Star rating")
                Text(text = rating)
            }
        }
    }
}