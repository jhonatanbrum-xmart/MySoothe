package com.codelab.basiclayouts

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun SootheBottomNavigation(
    buttonClicked: Boolean,
    onButtonClicked: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    NavigationBar(
        modifier = modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            NavigationBarItem(icon = {
                Icon(
                    imageVector = Icons.Default.Spa, contentDescription = null
                )
            }, label = {
                Text(stringResource(R.string.bottom_navigation_home))
            }, selected = !buttonClicked, onClick = {
                onButtonClicked(false)
            })
            NavigationBarItem(icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle, contentDescription = null
                )
            }, label = {
                Text(stringResource(R.string.bottom_navigation_profile))
            }, selected = buttonClicked, onClick = {
                onButtonClicked(true)
            })
        }
    }
}