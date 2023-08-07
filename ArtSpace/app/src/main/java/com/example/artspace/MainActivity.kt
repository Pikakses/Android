package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.artspace.ui.theme.ArtSpaceTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

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
            contentDescription = artworkDescription
        )

        Box(modifier = modifier) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = artworkName)
                Text(text = artworkArtist)
            }
        }

        Row {
            Button(
                onClick = {
                    when(currentArtwork) {
                        in 1..4 -> currentArtwork--
                        else -> currentArtwork = 0
                    }
                },

            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(R.string.previous_artwork))
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