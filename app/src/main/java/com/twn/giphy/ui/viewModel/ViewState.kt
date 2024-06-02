package com.twn.giphy.ui.viewModel

import com.twn.giphy.model.GifItem

sealed interface ViewState<out T> {
    data class Content<T>(val data: T): ViewState<T>
    data object NoContent: ViewState<Nothing>
    data object Loading: ViewState<Nothing>
    data object Error: ViewState<Nothing>
}