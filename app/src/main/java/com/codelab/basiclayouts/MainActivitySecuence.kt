package com.example.basicscodelab


import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.ui.text.font.FontWeight
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
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier) {
        Greetings()

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {

        Row(modifier = Modifier.padding(24.dp)) {
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
                        text = ("Composem ipsum color sit lazy, " +
                                "padding theme elit, sed do bouncy. ").repeat(4),
                    )
                }
            }
            ElevatedButton(
                onClick = { expanded = !expanded }
            ) {
                Text(if (expanded) "Mostrar menos" else "Mostrar mas")

            }


        }
    }
}


@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(3) { "$it" }
) {
    LazyColumn(modifier = modifier) {
        items(items = names) { name ->
            Greeting(
                when (name) {
                    "0" -> "Name"
                    "1" -> "Email"
                    "2" -> "Password"
                    else -> {"no se"}
                }
            )
        }
    }
}

@Preview(
    showBackground = false,
    uiMode = UI_MODE_NIGHT_YES,
    name = "GreetingPreviewDark"
)
@Composable
fun MyAppPreview() {
    BasicsCodelabTheme {
        MyApp(Modifier.fillMaxSize())
    }
}
