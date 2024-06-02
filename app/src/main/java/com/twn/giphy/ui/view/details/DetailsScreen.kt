package com.twn.giphy.ui.view.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.twn.giphy.R
import com.twn.giphy.model.GifItem
import com.twn.giphy.ui.view.common.AsyncGIFLoader
import com.twn.giphy.ui.viewModel.SharedViewModel
import com.twn.giphy.ui.viewModel.ViewState

@Composable
fun DetailsScreen(sharedViewModel: SharedViewModel) {

    val state by sharedViewModel.viewState.collectAsState()

    Column(modifier = Modifier.padding(vertical = 40.dp, horizontal = 20.dp)) {
        when (val viewState = state) {
            ViewState.Error -> Text(
                text = LocalContext.current.getString(R.string.something_is_wrong),
                Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterHorizontally)
            )

            ViewState.Loading -> Text(
                text = LocalContext.current.getString(R.string.loading),
                Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
            )

            is ViewState.Content -> {
                ShowContent(viewState.data)
            }

            ViewState.NoContent -> Text(
                text = LocalContext.current.getString(R.string.something_is_wrong),
                Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
            )
        }


    }
}

@Composable
private fun ShowContent(gifItem: GifItem) {
    Column(modifier = Modifier) {
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncGIFLoader(
                gifItem.url,
                Modifier
                    .padding(30.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }

        Text(
            text = LocalContext.current.getString(
                R.string.gif_name,
                gifItem.title
            ),
            Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = LocalContext.current.getString(
                R.string.gif_author,
                gifItem.userName
            ),
            Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally)
        )
    }
}