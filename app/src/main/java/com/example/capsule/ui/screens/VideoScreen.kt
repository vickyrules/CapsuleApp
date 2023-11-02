package com.example.capsule.ui.screens

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.capsule.R

@Composable
fun VideoScreen() {
    VideoPlayer()
}

@Composable
fun VideoPlayer(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    // Create a MediaItem from a resource URI
    val mediaItem = remember {
        val rawResource = R.raw.blood
        val uri = "android.resource://${context.packageName}/$rawResource"
        androidx.media3.common.MediaItem.fromUri(uri)
    }


    // create our player
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            this.prepare()
            this.setMediaItems(listOf(mediaItem))
            this.prepare()
            this.playWhenReady = false
        }
    }
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (title, videoPlayer) = createRefs()

        // video title
        Text(
            text = "Red Blood Cells",
            color = Color.White,
            modifier =
            Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        // player view
        DisposableEffect(
            AndroidView(
                modifier =
                Modifier
                    .constrainAs(videoPlayer) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }.fillMaxHeight(.5f),
                factory = {

                    // exo player view for our video player
                    PlayerView(context).apply {
                        player = exoPlayer
                        layoutParams =
                            FrameLayout.LayoutParams(
                                ViewGroup.LayoutParams
                                    .MATCH_PARENT,
                                ViewGroup.LayoutParams
                                    .MATCH_PARENT
                            )
                    }
                }
            )
        ) {
            onDispose {
                // relase player when no longer needed
                exoPlayer.release()
            }
        }
    }
}
