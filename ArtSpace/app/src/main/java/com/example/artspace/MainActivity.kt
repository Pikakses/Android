package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout(
    modifier: Modifier = Modifier
) {
    var currentArtwork by remember { mutableStateOf(0) }

    val artworkResource = when(currentArtwork) {
        0 -> painterResource(id = R.drawable.basketball)
        1 -> painterResource(id = R.drawable.cyberpunk_city)
        2 -> painterResource(id = R.drawable.ship)
        3 -> painterResource(id = R.drawable.zeus)
        else -> painterResource(id = R.drawable.manequins)
    }
    val artworkDescription = when(currentArtwork) {
        0 -> stringResource(id = R.string.basketball_description)
        1 -> stringResource(id = R.string.cyberpunk_city_description)
        2 -> stringResource(id = R.string.ship_description)
        3 -> stringResource(id = R.string.zeus_description)
        else -> stringResource(id = R.string.mannequin_description)
    }
    val artworkName = when(currentArtwork) {
        0 -> stringResource(R.string.basketball)
        1 -> stringResource(R.string.cyberpunk_city)
        2 -> stringResource(R.string.ship)
        3 -> stringResource(R.string.zeus)
        else -> stringResource(R.string.mannequins)
    }
    val artworkArtist = when(currentArtwork) {
        in 0..3 -> stringResource(R.string.dall_e)
        else -> stringResource(id = R.string.dall_e)
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = artworkResource,
            contentDescription = artworkDescription,
            modifier = Modifier
                .padding(top = 18.dp, start = 18.dp, end = 18.dp)
                .weight(0.5f),
            contentScale = ContentScale.FillHeight

        )

        Spacer(
            modifier = Modifier
                .weight(0.05f)
        )

        Box(
            modifier = modifier

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = artworkName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Text(
                    text = artworkArtist,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(
            modifier = Modifier
                .weight(0.25f)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 60.dp, start = 30.dp, end = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    when(currentArtwork) {
                        in 1..4 -> currentArtwork--
                        else -> currentArtwork = 0
                    }
                },
                modifier = Modifier
                    .height(48.dp)
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(R.string.previous_artwork))
            }
            Button(
                onClick = {
                    when(currentArtwork) {
                        in 0..3 -> currentArtwork++
                        else -> currentArtwork = 4
                    }
                },
                modifier = Modifier
                    .height(48.dp)
            ) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = stringResource(R.string.next_artwork))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}