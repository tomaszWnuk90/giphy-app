package com.twn.giphy.ui.view.mainPage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.twn.giphy.R
import com.twn.giphy.navigation.screen.NavigationItem
import com.twn.giphy.ui.view.common.GifItemView
import com.twn.giphy.ui.viewModel.MainScreenModel
import com.twn.giphy.ui.viewModel.SharedViewModel
import com.twn.giphy.ui.viewModel.ViewState

@Composable
fun MainScreen(navController: NavHostController, sharedViewModel: SharedViewModel) {
    val viewModel = hiltViewModel<MainScreenModel>()
    val state by viewModel.viewState.collectAsState()

    var text by rememberSaveable { mutableStateOf("") }
    Column(modifier = Modifier.padding(vertical = 40.dp, horizontal = 20.dp)) {
        TextField(
            value = text,
            onValueChange = {
                text = it
                viewModel.textEntered(it)
            },
            placeholder = {
                Text(text = LocalContext.current.getString(R.string.search))
            },
            modifier = Modifier.fillMaxWidth()
        )
        when (val viewState = state) {
            ViewState.Error -> Text(
                text = LocalContext.current.getString(R.string.error_message),
                Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
            )

            ViewState.Loading -> Text(
                text = LocalContext.current.getString(R.string.loading),
                Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
            )

            is ViewState.Content ->
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(100.dp),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    items(viewState.data.size) {
                        GifItemView(viewState.data[it], onClick = {
                            sharedViewModel.setGifData(it)
                            navController.navigate(NavigationItem.Details.route)
                        })
                    }

                }

            ViewState.NoContent -> Text(
                text = LocalContext.current.getString(R.string.empty),
                Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
            )
        }
    }
}
