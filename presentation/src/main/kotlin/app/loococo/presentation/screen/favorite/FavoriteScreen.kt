package app.loococo.presentation.screen.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.loococo.domain.model.StoreItem
import app.loococo.presentation.R
import app.loococo.presentation.component.ImageListAsyncImage
import app.loococo.presentation.component.ImageListIconButton
import app.loococo.presentation.component.ImageListTitleEllipsisText
import app.loococo.presentation.component.WidthSpacer
import app.loococo.presentation.theme.White
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
internal fun FavoriteRoute() {
    FavoriteScreen()
}

@Composable
fun FavoriteScreen() {
    val viewModel: FavoriteViewModel = hiltViewModel()
    val state by viewModel.collectAsState()

    viewModel.collectSideEffect {
    }

    Column(modifier = Modifier.fillMaxSize()) {
        FavoriteList(
            stores = state.stores,
            onEventSent = viewModel::onEventReceived
        )
    }
}

@Composable
fun FavoriteList(
    stores: List<StoreItem>,
    onEventSent: (FavoriteEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(stores) {
            FavoriteListItem(
                storeItem = it,
                onEventSent = onEventSent
            )
        }
    }
}

@Composable
fun FavoriteListItem(
    storeItem: StoreItem,
    onEventSent: (FavoriteEvent) -> Unit
) {
    if (storeItem.code.isBlank()) return

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(modifier = Modifier.size(70.dp)) {
            ImageListAsyncImage(image = storeItem.thumbnailUrl)
        }

        WidthSpacer(10)

        ImageListTitleEllipsisText(
            modifier = Modifier.weight(1f),
            text = storeItem.name.ifBlank { stringResource(R.string.default_name) },
            color = White
        )
        WidthSpacer(10)

        ImageListIconButton(
            selected = true,
            onClick = { onEventSent(FavoriteEvent.OnFavoriteClicked(storeItem)) }
        )
    }
}