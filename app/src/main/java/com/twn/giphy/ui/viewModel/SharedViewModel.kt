package com.twn.giphy.ui.viewModel

import androidx.lifecycle.ViewModel
import com.twn.giphy.model.GifItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {

    private val _viewState =
        MutableStateFlow<ViewState<GifItem>>(value = ViewState.NoContent)

    val viewState = _viewState.asStateFlow()

    fun setGifData(gifItem: GifItem) {
        _viewState.update {
            ViewState.Content(gifItem)
        }
    }
}