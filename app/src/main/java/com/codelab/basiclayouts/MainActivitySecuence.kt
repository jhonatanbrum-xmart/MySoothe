package com.example.basicscodelab


import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.codelab.basiclayouts.ui.theme.BasicsCodelabTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.getInsetsController(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.systemBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        setContent {
            MyApp(modifier = Modifier.fillMaxSize())
        }

    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {

    Surface(modifier) {
        Greetings()

    }
}

@Composable
fun Greeting(
    name: String, expanded: Boolean, onExpandChanged: () -> Unit, modifier: Modifier = Modifier
) {
    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp, animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow
        )
    )
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Text(
                    text = name, style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                if (expanded) {
                    Text(
                        text = ("Composem ipsum color sit lazy, + padding theme elit, sed do bouncy.").repeat(
                            4
                        ),
                    )
                }
            }
            ElevatedButton(onClick = onExpandChanged) {
                Text(if (expanded) "Mostrar menos" else "Mostrar mas")
            }
        }
    }
}

@Composable
private fun Greetings(
    modifier: Modifier = Modifier, names: List<Int> = List(5) { it }
) {
    var theItemExpandeded by rememberSaveable { mutableStateOf<Int?>(null) }

    LazyColumn(modifier = modifier) {
        itemsIndexed(items = names) { index, name ->
            val isExpanded = theItemExpandeded == index
            Greeting(
                name = when (name) {
                    0 -> "Name"
                    1 -> "Email"
                    2 -> "Password"
                    else -> "In progress"
                }, expanded = isExpanded, onExpandChanged = {
                    theItemExpandeded = if (isExpanded) null else index
                })
        }
    }
}


@Preview(
    showBackground = false, uiMode = UI_MODE_NIGHT_YES, name = "GreetingPreviewDark"
)
@Composable
fun MyAppPreview() {
    BasicsCodelabTheme {
        MyApp(Modifier.fillMaxSize())
    }
}
