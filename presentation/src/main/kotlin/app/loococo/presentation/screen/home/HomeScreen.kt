package app.loococo.presentation.screen.home

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.loococo.domain.model.StoreItem
import app.loococo.presentation.R
import app.loococo.presentation.component.CircularProgressBar
import app.loococo.presentation.component.HeightSpacer
import app.loococo.presentation.component.ImageListAsyncCircleImage
import app.loococo.presentation.component.ImageListAsyncImage
import app.loococo.presentation.component.ImageListIconButton
import app.loococo.presentation.component.ImageListTitleEllipsisText
import app.loococo.presentation.component.WidthSpacer
import app.loococo.presentation.theme.White
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
internal fun HomeRoute(onHomeTitle: (String) -> Unit) {
    HomeScreen(onHomeTitle)
}

@Composable
fun HomeScreen(onHomeTitle: (String) -> Unit) {
    val viewModel: HomeViewModel = hiltViewModel()
    val state by viewModel.collectAsState()
    val context = LocalContext.current

    viewModel.collectSideEffect {
        when (it) {
            is HomeSideEffect.HomeTitle -> {
                onHomeTitle(it.title)
            }

            is HomeSideEffect.ShowToast -> {
                Toast.makeText(context, it.res, Toast.LENGTH_SHORT).show()
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        HomeList(
            stores = state.stores,
            onEventSent = viewModel::onEventReceived
        )
    }

    CircularProgressBar(state.isLoading)
}


@Composable
fun HomeList(
    stores: List<StoreItem>,
    onEventSent: (HomeEvent) -> Unit
) {
    AnimatedVisibility(
        visible = stores.isNotEmpty(),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(stores) {
                HomeListItem(
                    storeItem = it,
                    onEventSent = onEventSent
                )
            }
        }
    }
}

@Composable
fun HomeListItem(
    storeItem: StoreItem,
    onEventSent: (HomeEvent) -> Unit
) {
    if (storeItem.code.isBlank()) return

    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4 / 3f)
                .background(White)
        ) {
            ImageListAsyncImage(storeItem.thumbnailUrl)
            Box(modifier = Modifier.padding(15.dp)) {
                ImageListAsyncCircleImage(
                    modifier = Modifier.size(50.dp),
                    image = storeItem.iconImageUrl
                )
            }
        }
        HeightSpacer(10)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageListTitleEllipsisText(
                modifier = Modifier.weight(1f),
                text = storeItem.name.ifBlank { stringResource(R.string.default_name) },
                color = White
            )
            WidthSpacer(5)
            ImageListIconButton(
                selected = storeItem.selected,
                onClick = { onEventSent(HomeEvent.OnFavoriteClicked(storeItem)) }
            )
        }
    }
}
