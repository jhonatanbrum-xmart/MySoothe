package com.codelab.basiclayouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun SootheNavigationRail(
    isProfileButtonClicked: Boolean,
    onButtonClicked: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationRail(
        modifier = modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = modifier.fillMaxHeight(), Arrangement.Center
        ) {
            NavigationRailItem(icon = {
                Icon(
                    imageVector = Icons.Default.Spa, contentDescription = null
                )
            }, label = {
                Text(stringResource(R.string.bottom_navigation_home))
            }, selected = !isProfileButtonClicked, onClick = {
                onButtonClicked(false)
            })
            Spacer(modifier = Modifier.height(8.dp))
            NavigationRailItem(icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle, contentDescription = null
                )
            }, label = {
                Text(stringResource(R.string.bottom_navigation_profile))
            }, selected = isProfileButtonClicked, onClick = {
                onButtonClicked(true)
            })
        }
    }
}